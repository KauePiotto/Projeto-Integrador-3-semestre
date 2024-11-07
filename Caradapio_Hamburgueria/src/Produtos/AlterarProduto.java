package Produtos;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class AlterarProduto extends JFrame {
	public AlterarProduto() {
		setTitle("Aletrar Produto - Byell Hambúrgueria");
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		
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

	public static void main(String[] args) {
		AlterarProduto alterar = new AlterarProduto();
		alterar.setVisible(true);
	}
}
