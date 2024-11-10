package entrada;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Cadapio_Pag_Inicial.Cardapio;

public class EntradaCardapio extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private Image logoImage;
	private ImageIcon resizedLogoIcon;
	private JLabel lblLogo;
	private PainelComFundo painel;
	public BotaoArredondado btnCardapio;

	public EntradaCardapio() {
		setTitle("Entrada - Byell Hambúrgueria");
		setSize(800, 600);
		setResizable(false);
		getContentPane().setLayout(null);

		Centralizar();
		PainelFundo();
		BotaoEntrada(painel);
		Logo(painel);

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

	public void Logo(JPanel painel) {
		logoIcon = new ImageIcon("imagens\\Logo2.png");

		logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		resizedLogoIcon = new ImageIcon(logoImage);
		lblLogo = new JLabel(resizedLogoIcon);
		lblLogo.setBounds(305, 155, 200, 100);

		painel.add(lblLogo);
	}

	public void PainelFundo() {
		painel = new PainelComFundo();
		painel.setLayout(null);
		setContentPane(painel);
	}

	public void BotaoEntrada(JPanel painel) {
		btnCardapio = new BotaoArredondado("Ver Cardápio", 30);
		
		btnCardapio.setForeground(Color.decode("#ffd96d"));
		btnCardapio.setFont(new Font("Arial", Font.BOLD, 16));
		btnCardapio.setBounds(280, 270, 250, 35);
		btnCardapio.setBackground(new Color(0, 0, 0));

		btnCardapio.setBorderPainted(false);
		btnCardapio.setFocusPainted(false);

		btnCardapio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				dispose();
			}
		});

		btnCardapio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCardapio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCardapio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnCardapio.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCardapio.setBackground(new Color(0, 0, 0));
			}
		});
		painel.add(btnCardapio);
	}

	public static void main(String[] args) {
		EntradaCardapio janela = new EntradaCardapio();
		janela.setVisible(true);
	}
}