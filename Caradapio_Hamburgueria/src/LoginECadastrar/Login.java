package LoginECadastrar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import CadapioPrincipal.Cardapio;
import entrada.BotaoArredondado;
import entrada.PainelComFundo;

public class Login extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private Image logoImage;
	private ImageIcon resizedLogoIcon;
	private JLabel logoLabel;
	private PainelComFundo painel;
	private BotaoArredondado btnLogin;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblSenha;
	private JPasswordField txtSenha;
	private String email;
	private String senha;
	private ImageIcon voltarIcon;
	private Image img;
	private JButton voltarButton;

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
				email = txtEmail.getText();
				senha = new String(txtSenha.getPassword());

				if (email.equals("admin@admin.com") && senha.equals("123456")) {
					JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
					dispose();

					Cardapio cardapio = new Cardapio();
					cardapio.setVisible(true);
					cardapio.mostrarMenu();
					cardapio.ocultarBotoesLoginECadastrar();
				} else if (email.equals("exemplo@exmeploema.com") && senha.equals("12345")) {
					JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
					dispose();

					Cardapio cardapio = new Cardapio();
					cardapio.setVisible(true);
					cardapio.ocultarBotoesLoginECadastrar();
				} else {

					JOptionPane.showMessageDialog(null, "Email ou senha incorretos.", "Erro de Login",
							JOptionPane.ERROR_MESSAGE);
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
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}
}