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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import Cadapio_Pag_Inicial.Cardapio;


public class Alterar_E_Excluir_Produto extends JFrame{
	public Alterar_E_Excluir_Produto() {
		setTitle("Aletrar Produto - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		BotaoVoltar();
		Logo();
		AlteExc();
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
				BuscarProduto BuscarPro = new BuscarProduto();
				BuscarPro.setVisible(true);
				dispose();
			}
		});
	}

	public void Logo() {
		ImageIcon logoIcon = new ImageIcon("imagens\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
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

	public void AlteExc() {
		JLabel lblIdProduto = new JLabel("Id");

		lblIdProduto.setBounds(50, 100, 30, 25);
		lblIdProduto.setForeground(Color.decode("#ffd96d"));
		lblIdProduto.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblIdProduto);

		JTextField txtIdProduto = new JTextField();

		txtIdProduto.setBounds(75, 100, 100, 25);
		txtIdProduto.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtIdProduto);
		
		// Adiciona o Label Nome
				JLabel lblNomeProd = new JLabel("Nome");
				lblNomeProd.setBounds(50, 100, 80, 25);
				lblNomeProd.setForeground(Color.decode("#ffd96d"));
				lblNomeProd.setFont(new Font("Arial", Font.BOLD, 16));
				add(lblNomeProd);

				JTextField txtNome = new JTextField();
				txtNome.setBounds(105, 100, 305, 25);
				txtNome.setFont(new Font("Arial", Font.BOLD, 16));
				add(txtNome);

				// Adiciona a foto do produto
				JLabel lblFoto = new JLabel("Foto");
				lblFoto.setBounds(450, 90, 200, 150);
				lblFoto.setForeground(Color.decode("#ffd96d"));
				lblFoto.setFont(new Font("Arial", Font.BOLD, 16));
				lblFoto.setHorizontalAlignment(JLabel.CENTER);
				lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#ffd96d")));
				add(lblFoto);

				// Adiciona a descricao do produdo
				JLabel lblDescricao = new JLabel("Descrição");
				lblDescricao.setBounds(50, 150, 80, 25);
				lblDescricao.setForeground(Color.decode("#ffd96d"));
				lblDescricao.setFont(new Font("Arial", Font.BOLD, 16));
				add(lblDescricao);

				JTextArea txtDescricao = new JTextArea();
				txtDescricao.setBounds(130, 150, 280, 90);
				txtDescricao.setFont(new Font("Arial", Font.BOLD, 16));
				txtDescricao.setLineWrap(true);
				txtDescricao.setWrapStyleWord(true);
				add(txtDescricao);
				// Adiciona o tipo de Produto
				JLabel lblTipo = new JLabel("Tipo de Produto");
				lblTipo.setBounds(50, 260, 180, 25);
				lblTipo.setForeground(Color.decode("#ffd96d"));
				lblTipo.setFont(new Font("Arial", Font.BOLD, 16));
				add(lblTipo);

				String[] TiposDeProduto = { "----------", "Lanche", "Bebida", "Porção" };
				JComboBox<String> cmbTipoProduto = new JComboBox<>(TiposDeProduto);
				cmbTipoProduto.setBounds(180, 260, 225, 25);
				cmbTipoProduto.setFont(new Font("Arial", Font.PLAIN, 16));
				add(cmbTipoProduto);

				// Adiciona o Preco do Produto
				JLabel lblPreco = new JLabel("Preço");
				lblPreco.setBounds(430, 260, 180, 25);
				lblPreco.setForeground(Color.decode("#ffd96d"));
				lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
				add(lblPreco);

				JTextField txtPreco = new JTextField("R$ ");
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
				BotaoArredondado btnCadastrarProduto = new BotaoArredondado("Cadastrar Produto", 30);
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
		Alterar_E_Excluir_Produto alterar = new Alterar_E_Excluir_Produto();
		alterar.setVisible(true);
	}
}
