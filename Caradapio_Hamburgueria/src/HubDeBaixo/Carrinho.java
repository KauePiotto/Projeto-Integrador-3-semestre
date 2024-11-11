package HubDeBaixo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import CadapioPrincipal.Cardapio;
import entrada.BotaoArredondado;
import java.util.ArrayList;

public class Carrinho extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private Image logoImage;
	private ImageIcon resizedLogoIcon;
	private JLabel logoLabel;
	private ImageIcon voltarIcon;
	private Image img;
	private JButton voltarButton;
	private PainelArredondado rightPanel;
	private JLabel valorLabel;
	private BotaoArredondado botao;
	private ArrayList<ItemCarrinho> itensCarrinho;
	private double valorTotal;

	public Carrinho() {
		setTitle("Carrinho - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		Centralizar();
		Logo();
		BotaoVoltar();
		CarrinhoPainel();

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

	public void Logo() {
		logoIcon = new ImageIcon("imagens\\Logo2.png");

		logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		resizedLogoIcon = new ImageIcon(logoImage);
		logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
	}

	public void BotaoVoltar() {
		voltarIcon = new ImageIcon("imagens\\seta-pequena-esquerda2.png");
		img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

		voltarIcon = new ImageIcon(img);

		voltarButton = new JButton(voltarIcon);
		voltarButton.setBounds(35, 25, 30, 30);
		voltarButton.setBorderPainted(false);
		voltarButton.setFocusPainted(false);
		voltarButton.setContentAreaFilled(false);

		add(voltarButton);

		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				cardapio.ocultarBotoesLoginECadastrar();
				dispose();
			}
		});
	}

	public void CarrinhoPainel() {
		rightPanel = new PainelArredondado();

		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.BLACK);
		rightPanel.setBounds(550, 400, 220, 150);

		add(rightPanel);

		valorLabel = new JLabel("Valor Total: R$ ");
		valorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		valorLabel.setForeground(Color.white);
		valorLabel.setBounds(10, 30, 200, 30);

		botao = new BotaoArredondado("Enviar Pedido", 30);

		botao.setFont(new Font("Arial", Font.BOLD, 16));
		botao.setBounds(10, 90, 200, 40);
		botao.setForeground(Color.decode("#1e1e1e"));

		rightPanel.add(valorLabel);
		rightPanel.add(botao);

		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"        Pedido enviado para cozinha!" + "\n O tempo de espera é de 30 a 60 minutos", "Pedido",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	// Método para adicionar item ao carrinho
	public void adicionarItemAoCarrinho(String nome, int quantidade, double valorUnitario) {
		ItemCarrinho item = new ItemCarrinho(nome, quantidade, valorUnitario);
		itensCarrinho.add(item);

		// Atualiza o valor total
		valorTotal += item.getValorTotal();

		// Atualiza a label de valor total
		valorLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));

		// Atualiza o painel com informações do item (opcional, exemplo)
		atualizarPainelCarrinho();
	}

	// Método para atualizar o painel com informações dos itens no carrinho
	public void atualizarPainelCarrinho() {
		// Limpa o painel antes de adicionar os itens
		rightPanel.removeAll();

		valorLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
		rightPanel.add(valorLabel);

		// Adiciona os itens no painel (simplesmente listando os itens no painel)
		int yPosition = 60;
		for (ItemCarrinho item : itensCarrinho) {
			JLabel itemLabel = new JLabel(
					item.getNome() + " x" + item.getQuantidade() + " - R$ " + item.getValorTotal());
			itemLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			itemLabel.setForeground(Color.white);
			itemLabel.setBounds(10, yPosition, 200, 30);
			rightPanel.add(itemLabel);
			yPosition += 30; // Ajusta a posição dos itens listados
		}

		rightPanel.revalidate();
		rightPanel.repaint();
	}

	public static void main(String[] args) {
		Carrinho carrinho = new Carrinho();
		carrinho.setVisible(true);
	}
}