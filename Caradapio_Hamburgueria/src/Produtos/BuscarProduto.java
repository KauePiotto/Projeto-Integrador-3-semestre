package Produtos;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Cadapio_Pag_Inicial.Cardapio;

public class BuscarProduto extends JFrame {
	public BuscarProduto() {
		setTitle("Buscar Produto - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		Logo();
		BotaoVoltar();
		BuscarProduto();
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
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
	}

	public void BotaoVoltar() {
		ImageIcon voltarIcon = new ImageIcon("imagens\\seta-pequena-esquerda2.png");
		Image img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		JButton voltarButton = new JButton(voltarIcon);
		voltarButton.setBounds(35, 15, 30, 30);
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

			// Desenha o fundo do botão com bordas arredondadas
			g2.setColor(getBackground());
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), raio, raio));

			super.paintComponent(g);
			g2.dispose();
		}
	}

	public void BuscarProduto() {
		// Adiciona o Campo para buscar o nome do produto
		JLabel lblNomeBu = new JLabel("Nome");
		lblNomeBu.setBounds(50, 100, 80, 25);
		lblNomeBu.setForeground(Color.decode("#ffd96d"));
		lblNomeBu.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNomeBu);

		JTextField txtNomeBu = new JTextField();
		txtNomeBu.setBounds(105, 100, 305, 25);
		txtNomeBu.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNomeBu);

		// Adiciona o tipo de produto que deseja buscar
		JLabel lblTipoBu = new JLabel("Tipo de Produto");
		lblTipoBu.setBounds(50, 170, 180, 25);
		lblTipoBu.setForeground(Color.decode("#ffd96d"));
		lblTipoBu.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTipoBu);

		String[] TiposDeProduto = { "----------", "Lanche", "Bebida", "Porção" };
		JComboBox<String> cmbTipoProduto = new JComboBox<>(TiposDeProduto);
		cmbTipoProduto.setBounds(180, 170, 225, 25);
		cmbTipoProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		add(cmbTipoProduto);
		
		//Adiciona o botão para buscar o produto
		BotaoArredondado btnBucarProduto = new BotaoArredondado("Buscar Produto", 30);
		
		btnBucarProduto.setFont(new Font("Arial", Font.BOLD, 16));
		btnBucarProduto.setBounds(300, 230, 200, 40);
		
		add(btnBucarProduto);
		
		btnBucarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Produto encontrardo com sucesso!", "Busca Produto",
						JOptionPane.INFORMATION_MESSAGE);
				AlterarProduto alterarPro = new AlterarProduto();
				alterarPro.setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		BuscarProduto buscar = new BuscarProduto();
		buscar.setVisible(true);
	}
}