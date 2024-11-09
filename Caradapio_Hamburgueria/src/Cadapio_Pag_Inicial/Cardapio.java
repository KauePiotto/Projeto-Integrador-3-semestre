package Cadapio_Pag_Inicial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import LoginECadastrar.Cadastrar;
import LoginECadastrar.Login;
import HubDeBaixo.*;

public class Cardapio extends JFrame {
	private JButton btnLogin;
	private JButton btnCadastrar;
	private JButton btnMenu;
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon ImagemFundo;
	private Image logoImage;
	private ImageIcon resizedLogoIcon;
	private JLabel lblLogo;
	private JPanel painel;
	private JPanel itemPainel;
	private String[] lanches = { "Hamburguer", "Cheeseburguer", "Vegetariano" };
	private String[] bebidas = { "Refrigerante", "Suco", "Água" };
	private String[] porcoes = { "Batata Frita", "Batata Rústica" };
	private JButton btnAll;
	private JButton btnLanches;
	private JButton btnBebidas;
	private JButton btnPorcoes;
	private JButton button;
	private ImageIcon menuicon;
	private Image img;
	private JPopupMenu menuPopup;
	private JMenuItem op1;
	private JMenuItem op2;
	private JMenuItem op3;
	private JMenuItem op4;

	public Cardapio() {
		setTitle("Cardápio - Byell Hambúrgueria");
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		Logo();
		BotaoLogin();
		BotaoCadastrar();
		CriarFiltros();
		BotaoMenu();
		FotoFundo();

		itemPainel = new JPanel();
		itemPainel.setLayout(new FlowLayout(FlowLayout.CENTER));
		itemPainel.setBounds(50, 170, 700, 300);
		add(itemPainel);

		NavigationPanel navPanel = new NavigationPanel();
		navPanel.setBounds(250, 515, 300, 40);
		add(navPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void ocultarBotoesLoginECadastrar() {
		btnLogin.setVisible(false);
		btnCadastrar.setVisible(false);
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
		logoIcon = new ImageIcon("imagens\\Logo2.png");

		logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		resizedLogoIcon = new ImageIcon(logoImage);
		lblLogo = new JLabel(resizedLogoIcon);
		lblLogo.setBounds(305, 3, 200, 100);

		add(lblLogo);
	}

	public void FotoFundo() {
		// Cria um JPanel com uma imagem de fundo
		painel = new JPanel() {
			// Sobrescreve o método paintComponent para desenhar a imagem
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImagemFundo = new ImageIcon("imagens\\Fundo.png");
				g.drawImage(ImagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		painel.setBounds(0, 0, 800, 105);

		add(painel);
	}

	class BotaoArredondado extends JButton {
		private int raio;

		public BotaoArredondado(String texto, int raio) {
			super(texto);
			this.raio = raio; // Define o raio das bordas arredondadas
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

	public void BotaoLogin() {
		btnLogin = new BotaoArredondado("Login", 30);

		btnLogin.setText("Login");
		btnLogin.setForeground(Color.decode("#ffd96d"));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogin.setBounds(565, 75, 100, 25);
		btnLogin.setBackground(new Color(73, 71, 71));

		// Adiciona o ActionListener para abrir a tela Login
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});

		// Configurações do MouseListener para mudar a cor e o cursor
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Muda o cursor para ícone de mão
				btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaura o cursor padrão
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

		// Abrir a tela Cadastrar
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cadastrar cadastrar = new Cadastrar();
				cadastrar.setVisible(true);
				dispose();
			}
		});

		// Configurações do MouseListener para mudar a cor e o cursor
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Muda o cursor para ícone de mão
				btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaura o cursor padrão
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

	class NavigationPanel extends JPanel {
		private Color backgorund = new Color(73, 71, 71);
		private int BorderRadius = 30;
		private JButton btnHome;
		private JButton btnCart;
		private JButton btnAccount;
		private JButton button;
		private ImageIcon icon;
		private Image img;

		public NavigationPanel() {
			setLayout(new FlowLayout());

			// Cria o botão para voltar à página principal
			btnHome = createIconButton("imagens\\casa2.png", "Página Principal");
			btnHome.addActionListener(e -> {
				// Lógica para voltar à página principal
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				dispose();
			});

			// Cria o botão para ir ao carrinho
			btnCart = createIconButton("imagens\\carrinho-de-compras2.png", "Carrinho");
			btnCart.addActionListener(e -> {
				Carrinho carrinho = new Carrinho();
				carrinho.setVisible(true);
				dispose();
			});

			// Cria o botão para mostrar a conta do usuário
			btnAccount = createIconButton("imagens\\perfil2.png", "Conta");
			btnAccount.addActionListener(e -> {
				// Lógica para mostrar a conta do usuário
				Perfil perfil = new Perfil();
				perfil.setVisible(true);
				dispose();
			});

			// Adiciona os botões ao painel
			add(btnHome);
			add(btnCart);
			add(btnAccount);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(backgorund);
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), BorderRadius, BorderRadius));

			g2.dispose();
		}

		// Método para mudar a cor de fundo, se necessário
		public void setBackgroundColor(Color color) {
			this.backgorund = color;
			repaint();
		}

		// Método para alterar o raio do arredondamento, se necessário
		public void setCornerRadius(int radius) {
			this.BorderRadius = radius;
			repaint();
		}

		// Método auxiliar para criar um botão com ícone
		private JButton createIconButton(String iconPath, String tooltip) {
			button = new JButton();
			icon = new ImageIcon(iconPath);

			button.setToolTipText(tooltip);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setPreferredSize(new Dimension(90, 30));

			// Redimensiona a imagem
			img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(img));

			return button;
		}
	}

	public void CriarFiltros() {
		btnAll = createImageButton("imagens\\todos.png", "Todos");
		btnLanches = createImageButton("imagens\\hamburguer.png", "Lanches");
		btnBebidas = createImageButton("imagens\\refrigerantes.png", "Bebidas");
		btnPorcoes = createImageButton("imagens\\porcoes.png", "Porções");

		// Definindo ação para cada botão
		btnAll.addActionListener(e -> updateItems("all"));
		btnLanches.addActionListener(e -> updateItems("lanches"));
		btnBebidas.addActionListener(e -> updateItems("bebidas"));
		btnPorcoes.addActionListener(e -> updateItems("porcoes"));

		// Posicionamento e adição ao JFrame
		btnLanches.setBounds(170, 115, 100, 30);
		btnBebidas.setBounds(286, 115, 100, 30);
		btnPorcoes.setBounds(400, 115, 100, 30);
		btnAll.setBounds(510, 115, 100, 30);

		add(btnAll);
		add(btnLanches);
		add(btnBebidas);
		add(btnPorcoes);
	}

	private JButton createImageButton(String imagePath, String tooltip) {
		button = new JButton();

		button.setToolTipText(tooltip);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		// Carrega e redimensiona a imagem
		ImageIcon icon = new ImageIcon(imagePath);
		Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(img));

		return button;
	}

	private void updateItems(String filter) {
		itemPainel.removeAll();

		JLabel imageLabel = new JLabel();

		ImageIcon imageIcon = null;
		Image img = null;

		switch (filter) {
		case "lanches":

			imageIcon = new ImageIcon("imagens\\hamburguer.png");
			img = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(img));
			itemPainel.add(imageLabel);

			for (String lanche : lanches) {
				JLabel lancheLabel = new JLabel(lanche);
				itemPainel.add(lancheLabel);
			}
			break;
		case "bebidas":

			imageIcon = new ImageIcon("imagens\\refrigerantes.png");
			img = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(img));

			itemPainel.add(imageLabel);

			for (String bebida : bebidas) {
				JLabel bebidaLabel = new JLabel(bebida);
				itemPainel.add(bebidaLabel);
			}
			break;
		case "porcoes":

			imageIcon = new ImageIcon("imagens\\porcoes.png");
			img = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(img));

			itemPainel.add(imageLabel);

			for (String porcao : porcoes) {
				JLabel porcaoLabel = new JLabel(porcao);
				itemPainel.add(porcaoLabel);
			}
			break;
		default:

			imageIcon = new ImageIcon("imagens\\todos.png");
			img = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(img));

			itemPainel.add(imageLabel);

			for (String lanche : lanches) {
				JLabel lancheLabel = new JLabel(lanche);
				itemPainel.add(lancheLabel);
			}
			for (String bebida : bebidas) {
				JLabel bebidaLabel = new JLabel(bebida);
				itemPainel.add(bebidaLabel);
			}
			for (String porcao : porcoes) {
				JLabel porcaoLabel = new JLabel(porcao);
				itemPainel.add(porcaoLabel);
			}
			break;
		}
		itemPainel.revalidate();
		itemPainel.repaint();
	}

	public void BotaoMenu() {
		menuicon = new ImageIcon("imagens\\menu-hamburguer2.png");
		img = menuicon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		menuicon = new ImageIcon(img);

		btnMenu = new JButton(menuicon);
		btnMenu.setBounds(50, 50, 50, 25);
		btnMenu.setBackground(new Color(255, 255, 255));
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		// Comeca com o botao invisivel
		btnMenu.setVisible(false);

		menuPopup = new JPopupMenu();

		op1 = new JMenuItem("Cadastrar Produto");
		op2 = new JMenuItem("Alterar Produto");
		op3 = new JMenuItem("Buscar Produto");
		op4 = new JMenuItem("Excluir Produto");

		op1.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opção 2 selecionada"));
		op2.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opção 2 selecionada"));
		op3.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opção 3 selecionada"));
		op4.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opção 3 selecionada"));

		menuPopup.add(op1);
		menuPopup.add(op2);
		menuPopup.add(op3);
		menuPopup.add(op4);

		btnMenu.addActionListener(e -> menuPopup.show(btnMenu, 0, btnMenu.getHeight()));

		add(btnMenu);
	}

	// Método para tornar o botão de menu visível após o login do adm
	public void mostrarMenu() {
		btnMenu.setVisible(true);
	}

	public static void main(String[] args) {
		Cardapio cardapio = new Cardapio();
		cardapio.setVisible(true);
	}
}