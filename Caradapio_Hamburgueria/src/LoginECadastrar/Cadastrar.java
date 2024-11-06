package LoginECadastrar;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Cadastrar extends JFrame {
	private Login login;

	public Cadastrar() {
		setTitle("Cadastrar - Byell Hambúrgueria");
		setFont(new Font("Arial", Font.BOLD, 16));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
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
	
	public void aa() {
		
	}

	public static void main(String[] args) {
		Cadastrar cadastrar = new Cadastrar();
		cadastrar.setVisible(true);
	}
}
