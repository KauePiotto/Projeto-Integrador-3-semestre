package Cadapio_Pag_Inicial;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cardapio extends JFrame {
	public Cardapio() {
		setTitle("Cardápio - Byell Hambúrgueria");
		// Tamanho da Janela, primeiro largura depois Altura
		setSize(800, 600);
		getContentPane().setLayout(null);
		Centralizar();
		Logo();
		Hub();
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

	public void Hub() {
		 // Cria um JPanel com uma imagem de fundo
        JPanel painel = new JPanel() {
            // Sobrescreve o método paintComponent para desenhar a imagem
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagemFundo = new ImageIcon("C:\\Users\\Kaue\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Fundo.png"); // Substitua pelo caminho da sua imagem
                g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        painel.setBounds(0, 0, 800, 105); // Define a posição e o tamanho do painel
        // Adiciona o painel ao JFrame
        add(painel);
	}
	
	public void Logo() {
		ImageIcon logoIcon = new ImageIcon("C:\\Users\\Kaue\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(305, 3, 200, 100);

		add(logoLabel);
	}
	
	public static void main(String[] args) {
		Cardapio cardapio = new Cardapio();
		cardapio.setVisible(true);
	}
}