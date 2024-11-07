package Produtos;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ExcluirProduto extends JFrame {
	public ExcluirProduto() {
		setTitle("Excluir Produto - Byell Hambúrgueria");
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
		ExcluirProduto excluir = new ExcluirProduto();
		excluir.setVisible(true);
	}
}
