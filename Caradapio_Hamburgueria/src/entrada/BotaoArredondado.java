package entrada;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class BotaoArredondado extends JButton {
	private int raio;
	private String texto;

	public BotaoArredondado(String texto, int raio) {
		super(texto);
		this.raio = raio;
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), raio, raio));

		super.paintComponent(g);
		g2.dispose();
	}
}