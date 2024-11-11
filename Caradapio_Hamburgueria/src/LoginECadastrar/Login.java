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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import CadapioPrincipal.Cardapio;
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
				conexao = new ConectaMySQL();
				Connection conn = conexao.openDB();

				if (conn == null) {
					JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados.");
					return;
				}

				email = txtEmail.getText();
				senha = new String(txtSenha.getPassword());

				String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, email);
					stmt.setString(2, senha);

					try (ResultSet rs = stmt.executeQuery()) {
						if (rs.next()) {
							String perfil = rs.getString("perfil");

							// Após login bem-sucedido, define o usuário como logado
							Cardapio.usuarioLogado = true;

							// Cria a tela do cardápio e faz ela visível
							Cardapio cardapio = new Cardapio();
							cardapio.setVisible(true);

							// Se for um administrador, mostra o menu de opções
							if ("admin".equals(perfil)) {
								cardapio.mostrarMenu();
							}

							// Chama o método para ocultar os botões de login e cadastro
							cardapio.ocultarBotoesLoginECadastrar();

							// Fecha a tela de login
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "E-mail/Senha incorreta ou não existe");
						}
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + ex.getMessage());
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