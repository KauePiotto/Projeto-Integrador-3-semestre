package Cadapio_Pag_Inicial;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Cardapio extends JFrame {
	public Cardapio() {
		setTitle("Cardápio - Byell Hambúrgueria");
		// Tamanho da Janela, primeiro largura depois Altura
		setSize(800, 600);
		getContentPane().setLayout(null);
		Centralizar();
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

	public static void main(String[] args) {
		Cardapio cardapio = new Cardapio();
		cardapio.setVisible(true);

	}
}