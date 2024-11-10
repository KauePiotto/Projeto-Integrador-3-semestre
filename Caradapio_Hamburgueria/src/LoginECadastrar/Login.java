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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import CadapioPrincipal.Cardapio;

public class Login extends JFrame {
	public Login() {
		setTitle("Login - Byell Hambúrgueria");
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();

		PainelComFundo painel = new PainelComFundo();
		painel.setLayout(null);
		setContentPane(painel);

		Logo();
		BotaoVoltar();
		CampoLogin();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Centralizar() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension janela = getSize();

		if (janela.height > screen.height) {
			setSize(janela.height, screen.height);
		}
		if (janela.width > screen.width) {
			setSize(screen.width, janela.height);
		}
		setLocation((screen.width - janela.width) / 2, (screen.height - janela.height) / 2);
	}

	public void Logo() {
		ImageIcon logoIcon = new ImageIcon("imagens\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(305, 70, 200, 100);

		add(logoLabel);
	}

	class PainelComFundo extends JPanel {
		private Image imagemFundo;

		public PainelComFundo() {
			imagemFundo = new ImageIcon("imagens\\Fundo.png").getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
		}
	}

	class BotaoArredondado extends JButton {
		private int raio;

		public BotaoArredondado(String texto, int raio) {
			super(texto);
			this.raio = raio; // Define o raio das bordas arredondadas
			setFocusPainted(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Desenha o fundo do botão com bordas arredondadas
			g2.setColor(getBackground());
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), raio, raio));

			super.paintComponent(g);
			g2.dispose();
		}
	}

	public void CampoLogin() {

		JLabel emLabel = new JLabel("Email:");
		emLabel.setBounds(250, 200, 80, 25);
		emLabel.setForeground(Color.decode("#ffd96d"));
		emLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(emLabel);

		JTextField emaField = new JTextField();
		emaField.setBounds(310, 200, 200, 25);
		emaField.setFont(new Font("Arial", Font.BOLD, 16));
		add(emaField);

		JLabel passwordLabel = new JLabel("Senha:");
		passwordLabel.setBounds(250, 240, 80, 25);
		passwordLabel.setForeground(Color.decode("#ffd96d"));
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(passwordLabel);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(310, 240, 200, 25);
		passwordField.setFont(new Font("Arial", Font.BOLD, 16));
		add(passwordField);

		BotaoArredondado CampoLogin = new BotaoArredondado("Login", 30);

		CampoLogin.setText("Login");
		CampoLogin.setBounds(330, 290, 150, 30);
		CampoLogin.setFont(new Font("Arial", Font.BOLD, 16));
		CampoLogin.setBackground(Color.gray);
		CampoLogin.setForeground(Color.decode("#ffd96d"));
		add(CampoLogin);

		CampoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emaField.getText();
				String password = new String(passwordField.getPassword());

				if (email.equals("admin@admin.com") && password.equals("123456")) {
					JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
					dispose();

					Cardapio cardapio = new Cardapio();
					cardapio.setVisible(true);
					cardapio.mostrarMenu();
					cardapio.ocultarBotoesLoginECadastrar();
				} else if (email.equals("exemplo@exmeploema.com") && password.equals("12345")) {
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
		ImageIcon voltarIcon = new ImageIcon("imagens\\seta-pequena-esquerda2.png");
		Image img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		JButton voltarButton = new JButton(voltarIcon);
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