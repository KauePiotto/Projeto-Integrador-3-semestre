package CadapioPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import HubDeBaixo.Carrinho;
import LoginECadastrar.Cadastrar;
import LoginECadastrar.Login;
import Produtos.Alterar_E_Excluir_Produto;
import Produtos.BuscarProduto;
import Produtos.CadastroProduto;
import Produtos.Produto;
import dao.ConectaMySQL;
import entrada.BotaoArredondado;
import entrada.PainelComFundo;

public class Cardapio extends JFrame {
	private JButton btnLogin;
	private JButton btnCadastrar;
	private JButton btnMenu;
	private JButton btnAll;
	private JButton btnLanches;
	private JButton btnBebidas;
	private JButton btnPorcoes;
	private JLabel lblLogo;
	private JPanel itemPainel;
	private JMenuItem op1;
	private JMenuItem op2;
	private JMenuItem op3;
	private JPopupMenu menuPopup;
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private ImageIcon menuicon;
	private Image logoImage;
	private Image img;
	private PainelComFundo painel;
	private NavigationPanel NavPanel;
	private ConectaMySQL conexao;
	public static boolean usuarioLogado = false;
	public static boolean adminLogado = false;
	public static String nomeUsuarioLogado;	

	public Cardapio() {
		setTitle("Cardápio - Byell Hambúrgueria");
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		conexao = new ConectaMySQL();

		Centralizar();
		Logo();
		BotaoLogin();
		BotaoCadastrar();
		CriarFiltros();
		BotaoMenu();
		NavPainel();
		PainelItem();
		FotoFundo();
		ocultarBotoesLoginECadastrar();

		// Adiciona o filtro "Todos" logo no início
		updateItems("all");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void NavPainel() {
		NavPanel = new NavigationPanel(this);
		NavPanel.setBounds(250, 515, 300, 40);

		add(NavPanel);
	}

	public void ocultarBotoesLoginECadastrar() {
	    if (usuarioLogado) {
	        btnLogin.setVisible(false);
	        btnCadastrar.setVisible(false);
	    }

	    // Exibir mensagem de boas-vindas
	    JLabel lblBemVindo = new JLabel("Bem-vindo, " + Cardapio.nomeUsuarioLogado + SwingConstants.RIGHT);
	    lblBemVindo.setFont(new Font("Arial", Font.BOLD, 16));
	    lblBemVindo.setForeground(Color.decode("#ffd96d"));
	    lblBemVindo.setBounds(565, 75, 200, 25); // Ajuste a posição conforme necessário

	    add(lblBemVindo);
	    
	    lblBemVindo.getParent().revalidate();
	    lblBemVindo.getParent().repaint();
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
		lblLogo = new JLabel(resizedLogoIcon);
		lblLogo.setBounds(305, 3, 200, 100);

		add(lblLogo);
	}

	public void FotoFundo() {
		painel = new PainelComFundo();

		painel.setLayout(null);
		painel.setBounds(0, 0, 800, 105);
		new ImageIcon("imagens/Fundo.png");

		add(painel);
	}

	public void BotaoLogin() {
		btnLogin = new BotaoArredondado("Login", 30);

		btnLogin.setForeground(Color.decode("#ffd96d"));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogin.setBounds(565, 75, 100, 25);
		btnLogin.setBackground(new Color(73, 71, 71));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnLogin.setBackground(new Color(73, 71, 71));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnLogin.setBackground(new Color(73, 71, 71));
			}
		});
		add(btnLogin);
	}

	public void BotaoCadastrar() {
		btnCadastrar = new BotaoArredondado("Cadastrar", 30);

		btnCadastrar.setText("Cadastrar");
		btnCadastrar.setForeground(Color.decode("#ffd96d"));
		btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastrar.setBounds(668, 75, 110, 25);
		btnCadastrar.setBackground(new Color(73, 71, 71));
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cadastrar cadastrar = new Cadastrar();
				cadastrar.setVisible(true);
				dispose();
			}
		});
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnCadastrar.setBackground(new Color(73, 71, 71));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCadastrar.setBackground(new Color(73, 71, 71));
			}
		});
		add(btnCadastrar);
	}

	public void CriarFiltros() {
		// Cria os botões de filtro
		btnAll = createImageButton("imagens/todos.png", "Todos");
		btnLanches = createImageButton("imagens/hamburguer.png", "Lanches");
		btnBebidas = createImageButton("imagens/refrigerantes.png", "Bebidas");
		btnPorcoes = createImageButton("imagens/porcoes.png", "Porções");

		// Método para adicionar MouseListener genérico aos botões
		addMouseListeners(btnAll);
		addMouseListeners(btnLanches);
		addMouseListeners(btnBebidas);
		addMouseListeners(btnPorcoes);

		// Adiciona os listeners de ação para os botões
		btnLanches.addActionListener(e -> updateItems("lanche"));
		btnBebidas.addActionListener(e -> updateItems("bebida"));
		btnPorcoes.addActionListener(e -> updateItems("Porção"));
		btnAll.addActionListener(e -> updateItems("all"));

		// Define a posição e adiciona os botões ao painel
		btnLanches.setBounds(170, 115, 100, 30);
		btnBebidas.setBounds(286, 115, 100, 30);
		btnPorcoes.setBounds(400, 115, 100, 30);
		btnAll.setBounds(510, 115, 100, 30);

		add(btnAll);
		add(btnLanches);
		add(btnBebidas);
		add(btnPorcoes);
	}

	private void addMouseListeners(JButton button) {
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public void PainelItem() {
		itemPainel = new JPanel();
		itemPainel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 colunas por linha, espaçamento maior

		JScrollPane scrollPane = new JScrollPane(itemPainel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25, 150, 730, 360);

		add(scrollPane);
	}

	private JButton createImageButton(String imagePath, String tooltip) {
		JButton button = new JButton();
		button.setToolTipText(tooltip);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		try {
			ImageIcon icon = new ImageIcon(imagePath);
			Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return button;
	}

	Carrinho carrinho = new Carrinho();

	private JPanel createProductPanel(String nome, String descricao, double preco, byte[] logo, Carrinho carrinho) {
		JPanel produtoPanel = new JPanel(new BorderLayout());
		produtoPanel.setPreferredSize(new Dimension(250, 100)); // Largura aumentada, altura reduzida
		produtoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		// Nome do produto
		JLabel lblNome = new JLabel("<html><b>" + (nome != null ? nome : "Produto") + "</b></html>",
				SwingConstants.CENTER);
		lblNome.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte um pouco maior para combinar com o layout

		// Descrição
		JLabel lblDescri = new JLabel("<html><i>" + (descricao != null ? descricao : "Sem descrição") + "</i></html>",
				SwingConstants.CENTER);
		lblDescri.setFont(new Font("Arial", Font.PLAIN, 12)); // Ajuste da fonte

		// Preço
		JLabel lblPreco = new JLabel("R$ " + String.format("%.2f", preco), SwingConstants.CENTER);
		lblPreco.setFont(new Font("Arial", Font.BOLD, 12));

		// Logo do produto
		JLabel imagemLabel = new JLabel();
		if (logo != null && logo.length > 0) {
			ImageIcon imagemIcon = new ImageIcon(logo);
			Image img = imagemIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Imagem mais larga
			imagemIcon = new ImageIcon(img);
			imagemLabel.setIcon(imagemIcon);
		} else {
			imagemLabel.setText("Sem imagem");
			imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}

		// Botão para adicionar ao carrinho
		JButton btnAddCarrinho = new BotaoArredondado("Adicionar ao carrinho", 20);
		btnAddCarrinho.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAddCarrinho.setForeground(Color.decode("#ffd96d"));
		btnAddCarrinho.setBackground(new Color(73, 71, 71));
		btnAddCarrinho.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, nome + " foi adicionado ao carrinho.");
		});
		// Painel de informações
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(lblNome);
		infoPanel.add(Box.createVerticalStrut(5));
		infoPanel.add(lblDescri);
		infoPanel.add(Box.createVerticalStrut(5));
		infoPanel.add(lblPreco);

		produtoPanel.add(infoPanel, BorderLayout.CENTER);
		produtoPanel.add(imagemLabel, BorderLayout.WEST);

		// Painel do botão
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(btnAddCarrinho);
		produtoPanel.add(buttonPanel, BorderLayout.SOUTH);

		return produtoPanel;
	}

	private void updateItems(String filtro) {
		itemPainel.removeAll();

		ConectaMySQL db = new ConectaMySQL();
		List<Produto> produtos = db.getProduto(filtro);

		if (produtos == null || produtos.isEmpty()) {
			JLabel vazio = new JLabel("Nenhum produto encontrado.");
			itemPainel.add(vazio);
		} else {
			for (Produto produto : produtos) {
				JPanel produtoPanel = createProductPanel(produto.getNome(), produto.getDescricao(), produto.getPreco(),
						produto.getLogo(), carrinho);
				itemPainel.add(produtoPanel);
			}
		}

		int numRows = (produtos.size() + 2) / 3;
		itemPainel.setPreferredSize(new Dimension(700, numRows * 120));

		itemPainel.revalidate();
		itemPainel.repaint();
	}

	public void BotaoMenu() {
		menuicon = new ImageIcon("imagens/menu-hamburguer2.png");
		img = menuicon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		menuicon = new ImageIcon(img);

		btnMenu = new JButton(menuicon);
		btnMenu.setBounds(50, 50, 50, 25);
		btnMenu.setBackground(new Color(255, 255, 255));
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		btnMenu.setVisible(false);

		menuPopup = new JPopupMenu();

		op1 = new JMenuItem("Cadastrar Produto");
		op2 = new JMenuItem("Alterar Produto ou Excluir");
		op3 = new JMenuItem("Buscar Produto");

		op1.addActionListener(e -> {
			CadastroProduto cadastro = new CadastroProduto();
			cadastro.setVisible(true);
			dispose();
		});
		op2.addActionListener(e -> {
			BuscarProduto buscar = new BuscarProduto();
			buscar.setVisible(true);
			dispose();
		});
		op3.addActionListener(e -> {
			Alterar_E_Excluir_Produto altEecl = new Alterar_E_Excluir_Produto();
			altEecl.setVisible(true);
			dispose();
		});

		menuPopup.add(op1);
		menuPopup.add(op2);
		menuPopup.add(op3);

		btnMenu.addActionListener(e -> menuPopup.show(btnMenu, 0, btnMenu.getHeight()));

		add(btnMenu);
	}

	public void mostrarMenu() {
		if (adminLogado) {
			btnMenu.setVisible(true);
			btnMenu.getParent().revalidate();
			btnMenu.getParent().repaint();
		}
	}

	public static void main(String[] args) {
		Cardapio cardapio = new Cardapio();
		cardapio.setVisible(true);
	}
}