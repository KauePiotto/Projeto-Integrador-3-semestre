package Produtos;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Cadapio_Pag_Inicial.Cardapio;

public class Alterar_E_Excluir_Produto extends JFrame {
	public Alterar_E_Excluir_Produto() {
		setTitle("Aletrar Produto - Byell Hambúrgueria");
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		BotaoVoltar();
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

	public void BotaoVoltar() {
		ImageIcon voltarIcon = new ImageIcon("imagens\\seta-pequena-esquerda2.png");
		Image img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		JButton voltarButton = new JButton(voltarIcon);
		voltarButton.setBounds(35, 15, 30, 30);
		voltarButton.setBorderPainted(false);
		voltarButton.setFocusPainted(false);
		voltarButton.setContentAreaFilled(false);

		add(voltarButton);

		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BuscarProduto BuscarPro = new BuscarProduto();
				BuscarPro.setVisible(true);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		Alterar_E_Excluir_Produto alterar = new Alterar_E_Excluir_Produto();
		alterar.setVisible(true);
	}
}
