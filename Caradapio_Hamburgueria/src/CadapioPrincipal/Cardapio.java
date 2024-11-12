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

		// Adiciona os listeners de ação para os botões
		btnLanches.addActionListener(e -> {
			updateItems("lanche");
		});
		btnBebidas.addActionListener(e -> {
			updateItems("bebida");
		});
		btnPorcoes.addActionListener(e -> {
			updateItems("Porção");
		});
		btnAll.addActionListener(e -> {
			updateItems("all");
		});

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

	public void PainelItem() {
		itemPainel = new JPanel();
		itemPainel.setLayout(new GridLayout(0, 3, 5, 5)); // 3 colunas por linha

		// Supondo que você tenha uma lista de produtos que será obtida do banco de  dados
		
		List<Produto> produtos = getProdutos(); // Função que retorna os produtos cadastrados

		int numProdutos = produtos.size();

		// Calcule o número de produtos e ajuste a altura do painel de acordo
		int larguraPanel = 1000;
		int alturaPanel = (numProdutos / 3 + numProdutos % 3) * 200; // Cada linha terá 2 produtos e cada produto tem altura de 200px
		itemPainel.setPreferredSize(new Dimension(larguraPanel, alturaPanel));

		// Adicionar os produtos no painel
		for (Produto produto : produtos) {
			// Aqui você cria os componentes para exibir cada produto, como labels, botões,etc.
			JButton botaoProduto = new JButton(produto.getNome());
			itemPainel.add(botaoProduto); // Adiciona o botão (ou outro componente) ao painel
		}

		// Cria o JScrollPane para permitir a rolagem
		JScrollPane scrollPane = new JScrollPane(itemPainel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25, 150, 730, 360); // Define o tamanho da área de rolagem

		add(scrollPane);
	}

	private List<Produto> getProdutos() {
		// Simulação de uma função que retorna uma lista de produtos (no seu caso, seria
		// o banco de dados)
		return new ArrayList<>(); // Retorna a lista de produtos cadastrados
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

	// Criação do painel para o produto
	private JPanel createProductPanel(String nome, String descricao, double preco, byte[] logo, Carrinho carrinho) {
		JPanel produtoPanel = new JPanel(new BorderLayout());
		produtoPanel.setPreferredSize(new Dimension(350, 200));
		produtoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		// Criação dos rótulos para nome, descrição e preço
		JLabel lblNome = new JLabel("<html><b>" + nome + "</b></html>", SwingConstants.CENTER);
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblDescri = new JLabel("<html><i>" + descricao + "</i></html>", SwingConstants.CENTER);
		lblDescri.setFont(new Font("Arial", Font.BOLD, 14));

		JLabel lblPreco = new JLabel("R$ " + String.format("%.2f", preco), SwingConstants.CENTER);
		lblPreco.setFont(new Font("Arial", Font.BOLD, 16));

		// Exibir imagem se o logo não for nulo
		JLabel imagemLabel = new JLabel();
		if (logo != null && logo.length > 0) {
			ImageIcon imagemIcon = new ImageIcon(logo);
			img = imagemIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			imagemIcon = new ImageIcon(img);
			imagemLabel.setIcon(imagemIcon);
		}

		JButton btnAddCarrinho = new BotaoArredondado("Adicionar ao Carrinho", 30);
		btnAddCarrinho.setFont(new Font("Arial", Font.BOLD, 16));
		btnAddCarrinho.setForeground(Color.decode("#ffd96d"));
		btnAddCarrinho.setBackground(new Color(73, 71, 71));
		btnAddCarrinho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Adiciona o item ao carrinho quando o botão for clicado
				carrinho.adicionarItemAoCarrinho(nome, 1, preco); // Adiciona 1 unidade do item

				// Atualiza o painel do carrinho
				carrinho.atualizarPainelCarrinho();

				// Exibe uma mensagem de confirmação
				JOptionPane.showMessageDialog(null, nome + " foi adicionado ao carrinho.");
			}
		});

		// Painel central para organizar os textos e botão
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(lblNome);
		infoPanel.add(Box.createVerticalStrut(10)); // Espaçamento
		infoPanel.add(lblDescri);
		infoPanel.add(Box.createVerticalStrut(20)); // Espaçamento
		infoPanel.add(lblPreco);

		produtoPanel.add(infoPanel, BorderLayout.CENTER);
		produtoPanel.add(imagemLabel, BorderLayout.WEST);

		// Adicionar botão na parte inferior
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Centraliza o botão
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

		// Ajuste dinâmico da altura do painel de itens com base na quantidade de
		// produtos
		int numRows = (produtos.size() + 2) / 3; // Calculando o número de linhas com 3 colunas
		itemPainel.setPreferredSize(new Dimension(700, numRows * 250)); // Ajusta a altura com base no número de
																		// produtos

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
		btnMenu.setVisible(true);
	}

	public static void main(String[] args) {
		Cardapio cardapio = new Cardapio();
		cardapio.setVisible(true);
	}
}