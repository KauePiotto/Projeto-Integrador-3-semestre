package HubDeBaixo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import entrada.BotaoArredondado;
import CadapioPrincipal.Cardapio;

public class Perfil extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private BotaoArredondado btnAlterar;
	private JLabel lblDados;
	private JSeparator linha;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblSobrenome;
	private JTextField txtSobrenome;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblSenha;
	private JPasswordField txtSenha;
	private JLabel lblTelefone;
	private MaskFormatter mascaraTelefone;
	private JFormattedTextField telefoneField;
	private JLabel lblCPF;
	private MaskFormatter mascaraCPF;
	private JFormattedTextField cpfField;
	private JLabel lblEndereco;
	private JSeparator linha2;
	private JLabel lblRua;
	private JTextField txtRua;
	private JLabel lblNum;
	private JTextField txtNum;
	private JLabel lblCEP;
	private MaskFormatter mascaraCEP;
	private JFormattedTextField cepField;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpf;
	private String telefone;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private ImageIcon voltarIcon;
	private Image img;
	private JButton btnVoltar;

	
	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JTextField getTxtSobrenome() {
		return txtSobrenome;
	}

	public void setTxtSobrenome(JTextField txtSobrenome) {
		this.txtSobrenome = txtSobrenome;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JPasswordField getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(JPasswordField txtSenha) {
		this.txtSenha = txtSenha;
	}

	public JFormattedTextField getTelefoneField() {
		return telefoneField;
	}

	public void setTelefoneField(JFormattedTextField telefoneField) {
		this.telefoneField = telefoneField;
	}

	public JFormattedTextField getCpfField() {
		return cpfField;
	}

	public void setCpfField(JFormattedTextField cpfField) {
		this.cpfField = cpfField;
	}

	public JTextField getTxtRua() {
		return txtRua;
	}

	public void setTxtRua(JTextField txtRua) {
		this.txtRua = txtRua;
	}

	public JTextField getTxtNum() {
		return txtNum;
	}

	public void setTxtNum(JTextField txtNum) {
		this.txtNum = txtNum;
	}

	public JFormattedTextField getCepField() {
		return cepField;
	}

	public void setCepField(JFormattedTextField cepField) {
		this.cepField = cepField;
	}

	public JTextField getTxtBairro() {
		return txtBairro;
	}

	public void setTxtBairro(JTextField txtBairro) {
		this.txtBairro = txtBairro;
	}

	public JTextField getTxtCidade() {
		return txtCidade;
	}

	public void setTxtCidade(JTextField txtCidade) {
		this.txtCidade = txtCidade;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(JTextField txtEstado) {
		this.txtEstado = txtEstado;
	}

	public Perfil() {
		setTitle("Perfil - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		Centralizar();
		PerfilUsuario();
		BotaoVoltar();

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

	public void PerfilUsuario() {
		// Adiciona o Titulo
		lblDados = new JLabel("Dados Pessoais");
		lblDados.setBounds(25, 45, 160, 50);
		lblDados.setForeground(Color.decode("#ffd96d"));
		lblDados.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblDados);

		// Adicionar uma linha em baixo da JLabel Dados Pessoais
		linha = new JSeparator();
		linha.setBounds(20, 80, 160, 2);
		linha.setForeground(Color.decode("#ffd96d"));
		add(linha);

		// Adiciona o Nome
		lblNome = new JLabel("Nome");
		lblNome.setBounds(40, 100, 80, 25);
		lblNome.setForeground(Color.decode("#ffd96d"));
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(90, 100, 200, 25);
		txtNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNome);

		// Adiciona o Sobrenome
		lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(300, 100, 160, 25);
		lblSobrenome.setForeground(Color.decode("#ffd96d"));
		lblSobrenome.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblSobrenome);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(395, 100, 250, 25);
		txtSobrenome.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtSobrenome);

		// Adiciona o E-mail
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(40, 150, 80, 25);
		lblEmail.setForeground(Color.decode("#ffd96d"));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(90, 150, 220, 25);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtEmail);

		// Adiciona a senha
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(320, 150, 80, 25);
		lblSenha.setForeground(Color.decode("#ffd96d"));
		lblSenha.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(380, 150, 265, 25);
		txtSenha.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtSenha);

		// Adiciona o numero de telefone
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(40, 200, 80, 25);
		lblTelefone.setForeground(Color.decode("#ffd96d"));
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTelefone);

		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
			telefoneField = new JFormattedTextField(mascaraTelefone);
			telefoneField.setBounds(110, 200, 250, 25);
			telefoneField.setFont(new Font("Arial", Font.BOLD, 16));
			add(telefoneField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona o CPF
		lblCPF = new JLabel("CPF");
		lblCPF.setBounds(370, 200, 80, 25);
		lblCPF.setForeground(Color.decode("#ffd96d"));
		lblCPF.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCPF);

		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCPF.setPlaceholderCharacter('_');
			cpfField = new JFormattedTextField(mascaraCPF);
			cpfField.setBounds(410, 200, 235, 25);
			cpfField.setFont(new Font("Arial", Font.BOLD, 16));
			add(cpfField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona um SubTitulo
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(25, 225, 160, 50);
		lblEndereco.setForeground(Color.decode("#ffd96d"));
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblEndereco);

		// Adicionar uma linha em baixo da JLabel Endereco
		linha2 = new JSeparator();
		linha2.setBounds(20, 260, 110, 2);
		linha2.setForeground(Color.decode("#ffd96d"));
		add(linha2);

		// Adiciona o nome da rua
		lblRua = new JLabel("Endereço");
		lblRua.setBounds(40, 300, 80, 25);
		lblRua.setForeground(Color.decode("#ffd96d"));
		lblRua.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(120, 300, 250, 25);
		txtRua.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtRua);

		// Adiciona o numero da rua
		lblNum = new JLabel("Número");
		lblNum.setBounds(390, 300, 80, 25);
		lblNum.setForeground(Color.decode("#ffd96d"));
		lblNum.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNum);

		txtNum = new JTextField();
		txtNum.setBounds(460, 300, 100, 25);
		txtNum.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNum);

		// Adiciona o CEP
		lblCEP = new JLabel("Cep");
		lblCEP.setBounds(60, 350, 80, 25);
		lblCEP.setForeground(Color.decode("#ffd96d"));
		lblCEP.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCEP);

		try {
			// Máscara para o CEP
			mascaraCEP = new MaskFormatter("#####-###");
			mascaraCEP.setPlaceholderCharacter('_');
			cepField = new JFormattedTextField(mascaraCEP);
			cepField.setBounds(120, 350, 130, 25);
			cepField.setFont(new Font("Arial", Font.BOLD, 16));
			add(cepField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona o Bairro
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(270, 350, 80, 25);
		lblBairro.setForeground(Color.decode("#ffd96d"));
		lblBairro.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(330, 350, 200, 25);
		txtBairro.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtBairro);

		// Adiciona a cidade
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(45, 400, 80, 25);
		lblCidade.setForeground(Color.decode("#ffd96d"));
		lblCidade.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(105, 400, 200, 25);
		txtCidade.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtCidade);

		// Adiciona o Estado
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(320, 400, 80, 25);
		lblEstado.setForeground(Color.decode("#ffd96d"));
		lblEstado.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(390, 400, 200, 25);
		txtEstado.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtEstado);

		// Adiciona o Botao Cadastrar
		btnAlterar = new BotaoArredondado("Alterar", 30);

		btnAlterar.setText("Salvar");
		btnAlterar.setBounds(300, 500, 150, 30);
		btnAlterar.setFont(new Font("Arial", Font.BOLD, 16));
		btnAlterar.setBackground(Color.gray);
		btnAlterar.setForeground(Color.decode("#ffd96d"));
		add(btnAlterar);

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nome = txtNome.getText();
				sobrenome = txtSobrenome.getText();
				email = txtEmail.getText();
				senha = new String(txtSenha.getPassword());
				cpf = cepField.getText();
				telefone = txtNum.getText();
				cep = cepField.getText();
				rua = txtRua.getText();
				numero = txtNum.getText();
				bairro = txtBairro.getText();
				cidade = txtCidade.getText();
				estado = txtEstado.getText();

				if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty()
						|| telefone.isEmpty() || cep.isEmpty() || rua.isEmpty() || numero.isEmpty() || bairro.isEmpty()
						|| cidade.isEmpty() || estado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro de Alteração",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Alteração bem-sucedida!");
					dispose();

					Cardapio cardapio = new Cardapio();
					cardapio.setVisible(true);
				}
			}
		});
	}

	public void BotaoVoltar() {
		voltarIcon = new ImageIcon("imagens\\seta-pequena-esquerda2.png");
		img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		btnVoltar = new JButton(voltarIcon);
		btnVoltar.setBounds(35, 15, 30, 30);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setContentAreaFilled(false);

		add(btnVoltar);

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		Perfil perfil = new Perfil();
		perfil.setVisible(true);
	}
}