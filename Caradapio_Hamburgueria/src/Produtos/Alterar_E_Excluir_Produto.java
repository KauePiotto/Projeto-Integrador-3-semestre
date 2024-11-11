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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import dao.ConectaMySQL;
import entrada.BotaoArredondado;

public class Alterar_E_Excluir_Produto extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private ImageIcon voltarIcon;
	private Image logoImage;
	private Image img;
	private JLabel logoLabel;
	private JLabel lblIdProduto;
	private JLabel lblNomeProd;
	private JLabel lblFoto;
	private JLabel lblDescricao;
	private JLabel lblTipo;
	private JLabel lblPreco;
	private JButton btnVoltar;
	private JTextField txtIdProduto;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextArea txtDescricao;
	private JComboBox<String> cmbTipoProduto;
	private String[] TiposDeProduto = { "----------", "Lanche", "Bebida", "Porção" };
	private BotaoArredondado btnAlterarProduto;
	private BotaoArredondado btnExcluirProduto;
	private ConectaMySQL conexao;
	private JFileChooser fileChooser;
	private File selectedFile;
	private BufferedImage img2;
	private int result;
	private Image scaledImage;

	public JTextField getTxtIdProduto() {
		return txtIdProduto;
	}

	public void setTxtIdProduto(JTextField txtIdProduto) {
		this.txtIdProduto = txtIdProduto;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JTextField getTxtPreco() {
		return txtPreco;
	}

	public void setTxtPreco(JTextField txtPreco) {
		this.txtPreco = txtPreco;
	}

	public JTextArea getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(JTextArea txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public JComboBox<String> getCmbTipoProduto() {
		return cmbTipoProduto;
	}

	public void setCmbTipoProduto(JComboBox<String> cmbTipoProduto) {
		this.cmbTipoProduto = cmbTipoProduto;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public Alterar_E_Excluir_Produto() {
		conexao = new ConectaMySQL();

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

	public Alterar_E_Excluir_Produto(String id, String nome, String descricao, String tipo, String preco, byte[] logo) {
		
		this.getTxtIdProduto().setText(id);
		this.txtNome.setText(nome);
		this.txtDescricao.setText(descricao);
		this.cmbTipoProduto.setSelectedItem(tipo);
		this.txtPreco.setText(preco);

		// Exibindo a imagem, se houver
		if (logo != null && logo.length > 0) {
			ImageIcon productIcon = new ImageIcon(logo);
			Image img = productIcon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
					Image.SCALE_SMOOTH);
			lblFoto.setIcon(new ImageIcon(img));
			lblFoto.setText("");
		}
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
				BuscarProduto BuscarPro = new BuscarProduto();
				BuscarPro.setVisible(true);
				dispose();
			}
		});
	}

	public void AlteExc() {
		lblIdProduto = new JLabel("Id");

		lblIdProduto.setBounds(50, 90, 30, 25);
		lblIdProduto.setForeground(Color.decode("#ffd96d"));
		lblIdProduto.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblIdProduto);

		txtIdProduto = new JTextField();

		txtIdProduto.setBounds(105, 90, 100, 25);
		txtIdProduto.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtIdProduto);

		// Adiciona o Label Nome
		lblNomeProd = new JLabel("Nome");
		lblNomeProd.setBounds(50, 135, 80, 25);
		lblNomeProd.setForeground(Color.decode("#ffd96d"));
		lblNomeProd.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNomeProd);

		txtNome = new JTextField();
		txtNome.setBounds(105, 135, 305, 25);
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
				result = fileChooser.showOpenDialog(Alterar_E_Excluir_Produto.this);

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
						JOptionPane.showMessageDialog(Alterar_E_Excluir_Produto.this,
								"Não foi possível carregar a imagem", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Adiciona a descricao do produdo
		lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(50, 170, 80, 25);
		lblDescricao.setForeground(Color.decode("#ffd96d"));
		lblDescricao.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblDescricao);

		txtDescricao = new JTextArea();
		txtDescricao.setBounds(130, 180, 270, 90);
		txtDescricao.setFont(new Font("Arial", Font.BOLD, 16));
		txtDescricao.setLineWrap(true);
		txtDescricao.setWrapStyleWord(true);
		add(txtDescricao);
		// Adiciona o tipo de Produto
		lblTipo = new JLabel("Tipo de Produto");
		lblTipo.setBounds(50, 300, 180, 25);
		lblTipo.setForeground(Color.decode("#ffd96d"));
		lblTipo.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTipo);

		cmbTipoProduto = new JComboBox<>(TiposDeProduto);
		cmbTipoProduto.setBounds(180, 300, 225, 25);
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

		// Preço
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

		// Adiciona o Botao Alterar Porduto
		btnAlterarProduto = new BotaoArredondado("Alterar Produto", 30);
		btnAlterarProduto.setFont(new Font("Arial", Font.BOLD, 16));
		btnAlterarProduto.setBounds(150, 400, 200, 40);

		add(btnAlterarProduto);

		btnAlterarProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtIdProduto.getText();
				String nome = txtNome.getText();
				String descricao = txtDescricao.getText();
				String tipo = (String) cmbTipoProduto.getSelectedItem();
				String preco = txtPreco.getText().replace("R$ ", "");

				try (Connection conn = conexao.openDB()) {
					String sql = "UPDATE produto SET nome = ?, descricao = ?, tipo = ?, preco = ? WHERE id = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, nome);
					stmt.setString(2, descricao);
					stmt.setString(3, tipo);
					stmt.setString(4, preco);
					stmt.setString(5, id);

					int rowsUpdated = stmt.executeUpdate();
					if (rowsUpdated > 0) {
						JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!", "Alteração",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao alterar produto.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao alterar o produto: " + ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Adiciona o Botao Excluir Porduto
		btnExcluirProduto = new BotaoArredondado("Excluir Produto", 30);
		btnExcluirProduto.setFont(new Font("Arial", Font.BOLD, 16));
		btnExcluirProduto.setBounds(400, 400, 200, 40);

		add(btnExcluirProduto);

		btnExcluirProduto.addActionListener(new ActionListener() {
			String id = txtIdProduto.getText();

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtIdProduto.getText();
				try (Connection conn = conexao.openDB()) {
					String sql = "DELETE FROM produto WHERE id = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, id);

					int rowsDeleted = stmt.executeUpdate();
					if (rowsDeleted > 0) {
						JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!", "Exclusão",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao excluir produto.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao excluir o produto: " + ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		Alterar_E_Excluir_Produto alterar = new Alterar_E_Excluir_Produto();
		alterar.setVisible(true);
	}
}
