package Cadapio_Pag_Inicial;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cardapio extends JFrame {
	public Cardapio() {
		setTitle("Cardápio - Byell Hambúrgueria");
		// Tamanho da Janela, primeiro largura depois Altura
		setSize(800, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		Centralizar();
		Logo();
		BotaoLogin();
		BotaoCadastrar();
		FotoFundo();
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

	public void FotoFundo() {
		// Cria um JPanel com uma imagem de fundo
		JPanel painel = new JPanel() {
			// Sobrescreve o método paintComponent para desenhar a imagem
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imagemFundo = new ImageIcon(
						"C:\\Users\\Kaue\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Fundo.png");
				g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		painel.setBounds(0, 0, 800, 105);

		add(painel);
	}

	public void Logo() {
		ImageIcon logoIcon = new ImageIcon("C:\\Users\\Kaue\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(305, 3, 200, 100);

		add(logoLabel);
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

	public void BotaoLogin() {
		BotaoArredondado btnLogin = new BotaoArredondado("Login", 30);

		btnLogin.setText("Login");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogin.setBounds(565, 75, 100, 25);
		btnLogin.setBackground(new Color(73, 71, 71));

		// Adiciona o ActionListener para abrir a tela Login
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});

		// Configurações do MouseListener para mudar a cor e o cursor
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Muda o cursor para ícone de mão
				btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaura o cursor padrão
				btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 0, 0));
			}
		});

		add(btnLogin);
	}

	public void BotaoCadastrar() {
		BotaoArredondado btnCadastrar = new BotaoArredondado("Cadastrar", 30);

		btnCadastrar.setText("Cadastrar");
		btnCadastrar.setForeground(Color.white);
		btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastrar.setBounds(668, 75, 110, 25);
		btnCadastrar.setBackground(new Color(73, 71, 71));

		// Abrir a tela Cadastrar
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});

		// Configurações do MouseListener para mudar a cor e o cursor
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Muda o cursor para ícone de mão
				btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaura o cursor padrão
				btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnCadastrar.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCadastrar.setBackground(new Color(0, 0, 0));
			}
		});

		add(btnCadastrar);
	}

	public static void main(String[] args) {
		Cardapio cardapio = new Cardapio();
		cardapio.setVisible(true);
	}
}