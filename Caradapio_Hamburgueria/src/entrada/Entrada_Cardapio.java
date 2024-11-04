package entrada;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Entrada_Cardapio extends JFrame {

	public Entrada_Cardapio() {
		setTitle("Entrada - Byell Hambúrgueria");
		// Tamanho da Janela, primeiro largura depois Altura
		setSize(800, 600);
		getContentPane().setLayout(null);
		Centralizar();
		BotaoEntrada();
		Logo();
		getContentPane().setBackground(new Color(73, 71, 71));
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

	public void FotoFundo() {

	}

	public void Logo() {
		ImageIcon logoIcon = new ImageIcon("C:\\Users\\Kaue\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(305, 155, 200, 100);

		add(logoLabel);
	}

	public void BotaoEntrada() {
		JButton b = new JButton();

		b.setText("Entrar");
		b.setForeground(Color.white);
		b.setBounds(280, 270, 250, 35);
		b.setBackground(new Color(0, 0, 0));

		add(b);
	}

	public static void main(String[] args) {
		Entrada_Cardapio janela = new Entrada_Cardapio();
		janela.setVisible(true);
	}
}