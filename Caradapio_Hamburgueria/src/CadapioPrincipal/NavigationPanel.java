package CadapioPrincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import HubDeBaixo.Carrinho;
import HubDeBaixo.Perfil;

public class NavigationPanel extends JPanel {
	private Color backgorund = new Color(73, 71, 71);
	private int BorderRadius = 30;
	private JButton btnHome;
	private JButton btnCart;
	private JButton btnAccount;
	private JButton button;
	private ImageIcon icon;
	private Image img;
	private Cardapio cardapio;

	public NavigationPanel(Cardapio cardapio) {
		this.cardapio = cardapio;
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

		btnHome = createIconButton("imagens/casa2.png", "Página Principal");
		btnHome.addActionListener(e -> {

			Cardapio newCardapio = new Cardapio();
			newCardapio.setVisible(true);
			cardapio.dispose();
		});

		btnCart = createIconButton("imagens/carrinho-de-compras2.png", "Carrinho");
		btnCart.addActionListener(e -> {
			Carrinho carrinho = new Carrinho();
			carrinho.setVisible(true);
			cardapio.dispose();
		});

		btnAccount = createIconButton("imagens/perfil2.png", "Conta");
		btnAccount.addActionListener(e -> {
			Perfil perfil = new Perfil();
			perfil.setVisible(true);
			cardapio.dispose();
		});

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

	public void setBackgroundColor(Color color) {
		this.backgorund = color;
		repaint();
	}

	public void setCornerRadius(int radius) {
		this.BorderRadius = radius;
		repaint();
	}

	private JButton createIconButton(String iconPath, String tooltip) {
		button = new JButton();
		icon = new ImageIcon(iconPath);

		button.setToolTipText(tooltip);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(90, 30));
		img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(img));

		return button;
	}
}