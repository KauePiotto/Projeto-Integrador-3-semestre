package LoginECadastrar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import CadapioPrincipal.Cardapio;
import HubDeBaixo.Perfil;
import dao.ConectaMySQL;
import entrada.BotaoArredondado;
import entrada.PainelComFundo;

public class Login extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private ImageIcon voltarIcon;
	private Image logoImage;
	private Image img;
	private JLabel logoLabel;
	private JLabel lblEmail;
	private JLabel lblSenha;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private String email;
	private String senha;
	private JButton voltarButton;
	private PainelComFundo painel;
	private BotaoArredondado btnLogin;
	private ConectaMySQL conexao;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login() {
		setTitle("Login - Byell Hambúrgueria");
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		PainelFundo();
		Centralizar();
		Logo();
		BotaoVoltar();
		CampoLogin();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Centralizar() {
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		janela = getSize();

		if (janela.height > screen.height) {
			setSize(janela.height, screen.height);
		}
		if (janela.width > screen.width) {
			setSize(screen.width, janela.height);
		}
		setLocation((screen.width - janela.width) / 2, (screen.height - janela.height) / 2);
	}

	public void Logo() {
		logoIcon = new ImageIcon("imagens/Logo2.png");

		logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		resizedLogoIcon = new ImageIcon(logoImage);
		logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(305, 70, 200, 100);

		add(logoLabel);
	}

	public void PainelFundo() {
		painel = new PainelComFundo();
		painel.setLayout(null);
		setContentPane(painel);
	}

	public boolean ValidacaoEmail(String email) {
		// Expressão regular para validar um formato básico de e-mail
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public void CampoLogin() {

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(250, 200, 80, 25);
		lblEmail.setForeground(Color.decode("#ffd96d"));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(310, 200, 200, 25);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtEmail);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(250, 240, 80, 25);
		lblSenha.setForeground(Color.decode("#ffd96d"));
		lblSenha.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(310, 240, 200, 25);
		txtSenha.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtSenha);

		btnLogin = new BotaoArredondado("Login", 30);

		btnLogin.setBounds(330, 290, 150, 30);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogin.setBackground(Color.gray);
		btnLogin.setForeground(Color.decode("#ffd96d"));
		add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = txtEmail.getText();
				senha = new String(txtSenha.getPassword());

				// Verifica se os campos estão vazios
				if (email.isEmpty() || senha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
					return;
				}

				// Validar formato do email
				if (!ValidacaoEmail(email)) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um e-mail válido.");
					return;
				}

				// Criação da conexão com o banco
				conexao = new ConectaMySQL();
				Connection conn = null;

				try {
					// Tentando abrir a conexão com o banco de dados
					conn = conexao.openDB();

					if (conn == null) {
						JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados.");
						return;
					}

					// A consulta SQL
					String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

					// Preparando a consulta
					try (PreparedStatement stmt = conn.prepareStatement(sql)) {
						stmt.setString(1, email); // Passando o e-mail para o PreparedStatement
						stmt.setString(2, senha); // Passando a senha para o PreparedStatement

						// Executando a consulta
						try (ResultSet rs = stmt.executeQuery()) {
							if (rs.next()) {
								String perfilUsuario = rs.getString("perfil");

								// Alterar a variável estática para indicar que o usuário está logado
								Cardapio.usuarioLogado = true;

								// Cria a instância do cardápio e faz a transição
								Cardapio cardapio = new Cardapio();
								cardapio.setVisible(true); // Torna a tela do Cardápio visível
								cardapio.ocultarBotoesLoginECadastrar(); // Oculta os botões de login e cadastro

								Perfil perfil = new Perfil();
								perfil.habilitarCampos();

								// Se o usuário for administrador, adicionar o menu de administração
								if ("admin".equals(perfilUsuario)) {
									Cardapio.adminLogado = true;
									
									cardapio.mostrarMenu();
								}

								dispose(); // Fecha a tela de login
							} else {
								JOptionPane.showMessageDialog(null, "E-mail/Senha incorreta ou não existe");
							}
						}
					} catch (SQLException ex) {
						// Captura qualquer erro de execução da consulta SQL
						ex.printStackTrace(); // Exibe a pilha de erro no console
						JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + ex.getMessage());
					}
				} finally {
					// Fecha a conexão com o banco (se aberta)
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException ex) {
							ex.printStackTrace(); // Caso haja erro ao fechar a conexão
						}
					}
				}
			}
		});
	}

	public void BotaoVoltar() {
		voltarIcon = new ImageIcon("imagens/seta-pequena-esquerda2.png");
		img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		voltarButton = new JButton(voltarIcon);
		voltarButton.setBounds(35, 20, 30, 30);
		voltarButton.setBorderPainted(false);
		voltarButton.setFocusPainted(false);
		voltarButton.setContentAreaFilled(false);

		add(voltarButton);

		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				cardapio.ocultarBotoesLoginECadastrar();
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}
}