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

import CadapioPrincipal.Cardapio;
import entrada.BotaoArredondado;

public class BuscarProduto extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private ImageIcon voltarIcon;
	private Image logoImage;
	private Image img;
	private JLabel logoLabel;
	private JLabel lblTipoBu;
	private JLabel lblNomeBu;
	private JTextField txtNomeBu;
	private JButton btnVoltar;
	private JComboBox<String> cmbTipoProduto;
	private String[] TiposDeProduto = { "----------", "Lanche", "Bebida", "Porção" };
	private BotaoArredondado btnBucarProduto;

	public BuscarProduto() {
		setTitle("Buscar Produto - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 350);

		Centralizar();
		Logo();
		BotaoVoltar();
		BuscarProduto();

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
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
	}

	public void BotaoVoltar() {
		voltarIcon = new ImageIcon("imagens/seta-pequena-esquerda2.png");
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

	public void BuscarProduto() {
		// Adiciona o Campo para buscar o nome do produto
		lblNomeBu = new JLabel("Nome");
		lblNomeBu.setBounds(50, 100, 80, 25);
		lblNomeBu.setForeground(Color.decode("#ffd96d"));
		lblNomeBu.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNomeBu);

		txtNomeBu = new JTextField();
		txtNomeBu.setBounds(105, 100, 305, 25);
		txtNomeBu.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNomeBu);

		// Adiciona o tipo de produto que deseja buscar
		lblTipoBu = new JLabel("Tipo de Produto");
		lblTipoBu.setBounds(50, 170, 180, 25);
		lblTipoBu.setForeground(Color.decode("#ffd96d"));
		lblTipoBu.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTipoBu);

		cmbTipoProduto = new JComboBox<>(TiposDeProduto);
		cmbTipoProduto.setBounds(180, 170, 225, 25);
		cmbTipoProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		add(cmbTipoProduto);

		// Adiciona o botão para buscar o produto
		btnBucarProduto = new BotaoArredondado("Buscar Produto", 30);

		btnBucarProduto.setFont(new Font("Arial", Font.BOLD, 16));
		btnBucarProduto.setBounds(300, 230, 200, 40);

		add(btnBucarProduto);

		btnBucarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Produto encontrardo com sucesso!", "Busca Produto",
						JOptionPane.INFORMATION_MESSAGE);

				Alterar_E_Excluir_Produto AeEPro = new Alterar_E_Excluir_Produto();
				AeEPro.setVisible(true);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		BuscarProduto buscar = new BuscarProduto();
		buscar.setVisible(true);
	}
}