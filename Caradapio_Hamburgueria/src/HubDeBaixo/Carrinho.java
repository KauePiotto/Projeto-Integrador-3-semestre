package HubDeBaixo;

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
import Cadapio_Pag_Inicial.Cardapio;

public class Carrinho extends JFrame {
	public Carrinho() {
		setTitle("Carrinho - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		Logo();
		BotaoVoltar();
		CarrinhoPainel();
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
		ImageIcon logoIcon = new ImageIcon("C:\\Users\\kaue.plfreire\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
	}

	public void BotaoVoltar() {
		ImageIcon voltarIcon = new ImageIcon(
				"C:\\Users\\kaue.plfreire\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\seta-pequena-esquerda2.png");
		Image img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		JButton voltarButton = new JButton(voltarIcon);
		voltarButton.setBounds(35, 25, 30, 30);
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

	public void CarrinhoPainel() {
		PainelArredondado rightPanel = new PainelArredondado();
		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.BLACK);
		rightPanel.setBounds(550, 400, 220, 150);

		add(rightPanel);

		JLabel valorLabel = new JLabel("Valor Total: R$ ");
		valorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		valorLabel.setForeground(Color.white);
		valorLabel.setBounds(10, 30, 200, 30);

		BotaoArredondado botao = new BotaoArredondado("Enviar Pedido", 30);
		
		botao.setFont(new Font("Arial", Font.BOLD, 16));
		botao.setBounds(10, 90, 200, 40);
		botao.setForeground(Color.WHITE);

		rightPanel.add(valorLabel);
		//rightPanel.add(taxaEntregaLabel);
		rightPanel.add(botao);

		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"        Pedido enviado para cozinha!" + "\n O tempo de espera é de 30 a 60 minutos",
						"Pedido", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	class BotaoArredondado extends JButton {
		private int raio;

		public BotaoArredondado(String texto, int raio) {
			super(texto);
			this.raio = raio;
			setFocusPainted(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
		}

		protected void paintComponent(Graphics g) {
			if (getModel().isPressed()) {
				g.setColor(Color.DARK_GRAY);
			} else {
				g.setColor(Color.DARK_GRAY);
			}
			g.fillRoundRect(0, 0, getWidth(), getHeight(), raio, raio);
			super.paintComponent(g);
		}
	}

	class PainelArredondado extends JPanel {

		private int arcWidth = 20; 
		private int arcHeight = 20; 

		public PainelArredondado() {
			setOpaque(false); 
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			
			g2d.setColor(Color.GRAY);

			
			g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));
		}
	}

	public static void main(String[] args) {
		Carrinho carrinho = new Carrinho();
		carrinho.setVisible(true);
	}
}