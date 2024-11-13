package Produtos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import CadapioPrincipal.Cardapio;
import dao.ConectaMySQL;
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
	private ConectaMySQL conexao;

	public JTextField getTxtNomeBu() {
		return txtNomeBu;
	}

	public void setTxtNomeBu(JTextField txtNomeBu) {
		this.txtNomeBu = txtNomeBu;
	}

	public JComboBox<String> getCmbTipoProduto() {
		return cmbTipoProduto;
	}

	public void setCmbTipoProduto(JComboBox<String> cmbTipoProduto) {
		this.cmbTipoProduto = cmbTipoProduto;
	}

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
				cardapio.adminLogado = Cardapio.adminLogado; 
				cardapio.mostrarMenu(); 
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
				conexao = new ConectaMySQL();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;

				try {
					conn = conexao.openDB();

					// Consulta SQL para buscar o produto pelo nome, tipo ou ambos
					String sql = "SELECT id,logo, nome, descricao, tipo, preco FROM produto WHERE 1=1";

					// Condições dinâmicas de acordo com os campos preenchidos
					if (!txtNomeBu.getText().trim().isEmpty()) {
						sql += " AND nome LIKE ?";
					}
					if (cmbTipoProduto.getSelectedIndex() > 0) { // Ignora o primeiro item "----------"
						sql += " AND tipo = ?";
					}

					stmt = conn.prepareStatement(sql);

					// Configura os parâmetros da consulta dinamicamente
					int paramIndex = 1;
					if (!txtNomeBu.getText().trim().isEmpty()) {
						stmt.setString(paramIndex++, "%" + txtNomeBu.getText().trim() + "%");
					}
					if (cmbTipoProduto.getSelectedIndex() > 0) {
						stmt.setString(paramIndex++, (String) cmbTipoProduto.getSelectedItem());
					}

					rs = stmt.executeQuery();

					if (rs.next()) {
						// Produto encontrado, passa os dados para a tela de Alterar_E_Excluir_Produto
						String id = rs.getString("id");
						String nome = rs.getString("nome");
						String descricao = rs.getString("descricao");
						String tipo = rs.getString("tipo");
						String preco = rs.getString("preco");

						// Recupera a imagem (logo)
						byte[] logo = rs.getBytes("logo"); // Correção aqui: logo em vez de foto
						if (logo != null && logo.length > 0) {
							// Converte os bytes para uma imagem
							ImageIcon fotoIcon = new ImageIcon(logo);
							Image img = fotoIcon.getImage();
							Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
							ImageIcon scaledIcon = new ImageIcon(scaledImg);

							JLabel fotoLabel = new JLabel(scaledIcon);
							fotoLabel.setBounds(50, 270, 200, 200); // Ajuste conforme necessário
							add(fotoLabel);
						}
						// Instancia e exibe a tela Alterar_E_Excluir_Produto passando os dados do
						// produto
						Alterar_E_Excluir_Produto alterarProduto = new Alterar_E_Excluir_Produto(id, nome, descricao,
								tipo, preco, logo);
						alterarProduto.setVisible(true);
						dispose(); // Fecha a tela de BuscarProduto

					} else {
						// Produto não encontrado, mostra mensagem de erro
						JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Busca Produto",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao buscar o produto: " + ex.getMessage(),
							"Erro de Conexão", JOptionPane.ERROR_MESSAGE);
				} finally {
					try {
						conexao.closeDB(conn, stmt, rs);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		BuscarProduto buscar = new BuscarProduto();
		buscar.setVisible(true);
	}
}