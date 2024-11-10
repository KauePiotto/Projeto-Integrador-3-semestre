package Produtos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import CadapioPrincipal.Cardapio;
import entrada.BotaoArredondado;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CadastroProduto extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private ImageIcon voltarIcon;
	private Image logoImage;
	private Image img;
	private Image scaledImage;
	private JLabel logoLabel;
	private JLabel lblFoto;
	private JLabel lblDescricao;
	private JLabel lblTipo;
	private JLabel lblNomeProd;
	private JLabel lblPreco;
	private JButton voltarButton;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextArea txtDescricao;
	private JFileChooser fileChooser;
	private File selectedFile;
	private BufferedImage img2;
	private int result;
	private JComboBox<String> cmbTipoProduto;
	private String[] TiposDeProduto = { "----------", "Lanche", "Bebida", "Porção" };
	private BotaoArredondado btnCadastrarProduto;

	public CadastroProduto() {
		setTitle("Cadastro Produto - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		Centralizar();
		Logo();
		BotaoVoltar();
		CadastrarProduto();

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

		voltarButton = new JButton(voltarIcon);
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

	public void CadastrarProduto() {
		// Adiciona o Label Nome
		lblNomeProd = new JLabel("Nome");
		lblNomeProd.setBounds(50, 100, 80, 25);
		lblNomeProd.setForeground(Color.decode("#ffd96d"));
		lblNomeProd.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNomeProd);

		txtNome = new JTextField();
		txtNome.setBounds(105, 100, 305, 25);
		txtNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNome);

		// Adiciona a foto do produto
		lblFoto = new JLabel("Foto");
		lblFoto.setBounds(450, 90, 200, 150);
		lblFoto.setForeground(Color.decode("#ffd96d"));
		lblFoto.setFont(new Font("Arial", Font.BOLD, 16));
		lblFoto.setHorizontalAlignment(JLabel.CENTER);
		lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#ffd96d")));
		add(lblFoto);

		lblFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fileChooser = new JFileChooser();
				result = fileChooser.showOpenDialog(CadastroProduto.this);

				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();

					try {
						img2 = ImageIO.read(selectedFile);
						scaledImage = img2.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
								Image.SCALE_SMOOTH);
						lblFoto.setIcon(new ImageIcon(scaledImage));
						lblFoto.setText("");
					} catch (IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(CadastroProduto.this, "Não foi possível carregar a imagem",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Adiciona a descricao do produdo
		lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(50, 150, 80, 25);
		lblDescricao.setForeground(Color.decode("#ffd96d"));
		lblDescricao.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblDescricao);

		txtDescricao = new JTextArea();
		txtDescricao.setBounds(130, 150, 280, 90);
		txtDescricao.setFont(new Font("Arial", Font.BOLD, 16));
		txtDescricao.setLineWrap(true);
		txtDescricao.setWrapStyleWord(true);
		add(txtDescricao);
		// Adiciona o tipo de Produto
		lblTipo = new JLabel("Tipo de Produto");
		lblTipo.setBounds(50, 260, 180, 25);
		lblTipo.setForeground(Color.decode("#ffd96d"));
		lblTipo.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTipo);

		cmbTipoProduto = new JComboBox<>(TiposDeProduto);
		cmbTipoProduto.setBounds(180, 260, 225, 25);
		cmbTipoProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		add(cmbTipoProduto);

		// Adiciona o Preco do Produto
		lblPreco = new JLabel("Preço");
		lblPreco.setBounds(430, 260, 180, 25);
		lblPreco.setForeground(Color.decode("#ffd96d"));
		lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblPreco);

		txtPreco = new JTextField("R$ ");
		txtPreco.setBounds(485, 260, 125, 25);
		txtPreco.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtPreco);

		((AbstractDocument) txtPreco.getDocument()).setDocumentFilter(new DocumentFilter() {
			private final String currencySymbol = "R$ ";

			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (string != null && string.matches("[0-9.]*")) {
					if (offset == 0) {
						fb.insertString(offset, currencySymbol + string, attr);
					} else {
						super.insertString(fb, offset, string, attr);
					}
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text != null && text.matches("[0-9.]*")) {
					if (offset == 0) {
						fb.replace(offset, length, currencySymbol + text, attrs);
					} else {
						super.replace(fb, offset, length, text, attrs);
					}
				}
			}

			@Override
			public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
				if (offset >= currencySymbol.length()) {
					super.remove(fb, offset, length);
				}
			}
		});

		// Adiciona o Botao Cadastrar Porduto
		btnCadastrarProduto = new BotaoArredondado("Cadastrar Produto", 30);

		btnCadastrarProduto.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastrarProduto.setBounds(300, 330, 200, 40);

		add(btnCadastrarProduto);

		btnCadastrarProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Cadastro Produto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public static void main(String[] args) {
		CadastroProduto cadastro = new CadastroProduto();
		cadastro.setVisible(true);
	}
}