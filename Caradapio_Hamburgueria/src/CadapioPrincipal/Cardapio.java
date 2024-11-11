package CadapioPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
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
	private JButton button;
	private JLabel lblLogo;
	private JLabel imageLabel;
	private JPanel itemPainel;
	private JMenuItem op1;
	private JMenuItem op2;
	private JMenuItem op3;
	private JPopupMenu menuPopup;
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private ImageIcon icon;
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
		btnAll = createImageButton("imagens/todos.png", "Todos");
		btnLanches = createImageButton("imagens/hamburguer.png", "Lanches");
		btnBebidas = createImageButton("imagens/refrigerantes.png", "Bebidas");
		btnPorcoes = createImageButton("imagens/porcoes.png", "Porções");

		btnLanches.addActionListener(e -> {
			updateItems("lanches");
		});
		btnBebidas.addActionListener(e -> {
			updateItems("bebidas");
		});
		btnPorcoes.addActionListener(e -> {
			updateItems("porcoes");
		});
		btnAll.addActionListener(e -> {
			updateItems("all");
		});

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
		itemPainel.setLayout(new FlowLayout());
		itemPainel.setBounds(50, 150, 700, 350);
		add(itemPainel);
	}

	private JButton createImageButton(String imagePath, String tooltip) {
		button = new JButton();

		button.setToolTipText(tooltip);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		icon = new ImageIcon(imagePath);
		img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(img));

		return button;
	}

	private JPanel createProductPanel(String nome, String descricao, double preco, byte[] logo) {
		JPanel produtoPanel = new JPanel(new BorderLayout());

		produtoPanel.setPreferredSize(new Dimension(280, 200));
		produtoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		// Criação dos rótulos para nome, descrição e preço
		JLabel nomeLabel = new JLabel("<html><b>" + nome + "</b></html>", SwingConstants.CENTER);
		JLabel descricaoLabel = new JLabel("<html><i>" + descricao + "</i></html>", SwingConstants.CENTER);
		JLabel precoLabel = new JLabel("R$ " + String.format("%.2f", preco), SwingConstants.CENTER);

		// Exibir imagem se o logo não for nulo
		JLabel imagemLabel = new JLabel();
		if (logo != null && logo.length > 0) {
			ImageIcon imagemIcon = new ImageIcon(logo); // Converte o byte[] para ImageIcon
			Image img = imagemIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			imagemIcon = new ImageIcon(img);
			imagemLabel.setIcon(imagemIcon);
		}

		JButton addCarrinhoButton = new JButton("Adicionar ao Carrinho");

		// Alteração: coloque o preço no centro para garantir que tenha espaço
		JPanel centroPanel = new JPanel();

		centroPanel.setLayout(new BorderLayout());
		centroPanel.add(nomeLabel, BorderLayout.NORTH);
		centroPanel.add(descricaoLabel, BorderLayout.CENTER);
		centroPanel.add(precoLabel, BorderLayout.SOUTH);
		centroPanel.add(addCarrinhoButton, BorderLayout.PAGE_END);

		produtoPanel.add(centroPanel, BorderLayout.CENTER);
		produtoPanel.add(imagemLabel, BorderLayout.WEST);

		return produtoPanel;
	}

	private void updateItems(String filtro) {
		itemPainel.removeAll();
		System.out.println("Filtro aplicado: " + filtro);

		ConectaMySQL db = new ConectaMySQL();
		List<Produto> produtos = db.getProdutos(filtro);

		for (Produto produto : produtos) {
			JPanel produtoPanel = createProductPanel(produto.getNome(), produto.getDescricao(), produto.getPreco(),
					produto.getLogo());
			itemPainel.add(produtoPanel);
		}

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