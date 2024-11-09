package entrada;

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
import Cadapio_Pag_Inicial.Cardapio;

public class Entrada_Cardapio extends JFrame {
	private Image imagemFundo;
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private Image logoImage;
	private ImageIcon resizedLogoIcon;
	private JLabel lblLogo;
	private BotaoArredondado botaoArredondado;

	public Entrada_Cardapio() {
		setTitle("Entrada - Byell Hambúrgueria");
		setSize(800, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		Centralizar();

		PainelComFundo painel = new PainelComFundo();
		painel.setLayout(null);
		setContentPane(painel);

		BotaoEntrada(painel);
		Logo(painel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Metodo para centralizar o JFrame
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

	// Classe interna para o painel com fundo
	class PainelComFundo extends JPanel {
		public PainelComFundo() {
			imagemFundo = new ImageIcon("imagens\\Fundo.png").getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Metodo para colocar a Logo Arredondada no JFrame
	public void Logo(JPanel painel) {
		logoIcon = new ImageIcon("imagens\\Logo2.png");

		logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		resizedLogoIcon = new ImageIcon(logoImage);
		lblLogo = new JLabel(resizedLogoIcon);
		lblLogo.setBounds(305, 155, 200, 100);

		painel.add(lblLogo);
	}

	// Classe interna para arredondar o Botao
	class BotaoArredondado extends JButton {
		private int raio;

		public BotaoArredondado(String texto, int raio) {
			super(texto);
			this.raio = raio;
			setFocusPainted(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), raio, raio));

			super.paintComponent(g);
			g2.dispose();
		}
	}

	// Metodo para adicionar o botao no JFrame
	public void BotaoEntrada(JPanel painel) {
		botaoArredondado = new BotaoArredondado("Ver Cardápio", 30);

		botaoArredondado.setText("Ver Cardápio");
		botaoArredondado.setForeground(Color.decode("#ffd96d"));
		botaoArredondado.setFont(new Font("Arial", Font.BOLD, 16));
		botaoArredondado.setBounds(280, 270, 250, 35);
		botaoArredondado.setBackground(new Color(0, 0, 0));

		botaoArredondado.setBorderPainted(false);
		botaoArredondado.setFocusPainted(false);

		botaoArredondado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				dispose();
			}
		});

		// Configurações do MouseListener para mudar a cor e o cursor
		botaoArredondado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botaoArredondado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botaoArredondado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				botaoArredondado.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				botaoArredondado.setBackground(new Color(0, 0, 0));
			}
		});
		painel.add(botaoArredondado);
	}

	public static void main(String[] args) {
		Entrada_Cardapio janela = new Entrada_Cardapio();
		janela.setVisible(true);
	}
}