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

	public Entrada_Cardapio() {
		setTitle("Entrada - Byell Hambúrgueria");
		// Tamanho da Janela, primeiro largura depois Altura
		setSize(800, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		Centralizar();
		
		// Adiciona o painel com imagem de fundo
		PainelComFundo painel = new PainelComFundo();
		painel.setLayout(null); // Define o layout nulo para o painel
		setContentPane(painel);
		BotaoEntrada(painel);
		Logo(painel);
		
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

	// Classe interna para o painel com fundo
	class PainelComFundo extends JPanel {
		private Image imagemFundo;

		public PainelComFundo() {
			// Carrega a imagem de fundo
			imagemFundo = new ImageIcon("C:\\Users\\User\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Fundo.png")
					.getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Desenha a imagem de fundo
			g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public void Logo(JPanel painel) {
		ImageIcon logoIcon = new ImageIcon("C:\\Users\\User\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(305, 155, 200, 100);

		painel.add(logoLabel);
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

	public void BotaoEntrada(JPanel painel) {
		BotaoArredondado b = new BotaoArredondado("Ver Cardápio", 30);

		b.setText("Ver Cardápio");
		b.setForeground(Color.white);
		b.setFont(new Font("Arial", Font.BOLD, 16));
		b.setBounds(280, 270, 250, 35);
		b.setBackground(new Color(0, 0, 0));

		b.setBorderPainted(false);
		b.setFocusPainted(false);

		// Adiciona o ActionListener para abrir a tela Cardapio
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);

				dispose();
			}
		});

		// Configurações do MouseListener para mudar a cor e o cursor
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Muda o cursor para ícone de mão
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)); // Restaura o cursor padrão
			}

			@Override
			public void mousePressed(MouseEvent e) {
				b.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				b.setBackground(new Color(0, 0, 0));
			}
		});
		painel.add(b);
	}

	public static void main(String[] args) {
		Entrada_Cardapio janela = new Entrada_Cardapio();
		janela.setVisible(true);
	}
}