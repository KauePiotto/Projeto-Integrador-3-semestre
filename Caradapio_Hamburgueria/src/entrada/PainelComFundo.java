package entrada;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelComFundo extends JPanel {
	private Image imagemFundo;

	public PainelComFundo() {
		imagemFundo = new ImageIcon("imagens/Fundo.png").getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
	}
}