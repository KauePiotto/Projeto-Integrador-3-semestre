package HubDeBaixo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CadapioPrincipal.Cardapio;
import entrada.BotaoArredondado;
import java.util.ArrayList;
import java.util.List;

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
	private double valorTotal;
	private ArrayList<ItemCarrinho> itensCarrinho;
	private JPanel CarrinhoPainel;

	public Carrinho() {
		setTitle("Carrinho - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		itensCarrinho = new ArrayList<>();
		valorTotal = 0.0;

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

		// Criação do painel para exibir os itens do carrinho
		CarrinhoPainel = new JPanel();
		CarrinhoPainel.setLayout(new BoxLayout(CarrinhoPainel, BoxLayout.Y_AXIS));
		CarrinhoPainel.setBounds(35, 100, 500, 300);
		CarrinhoPainel.setBackground(Color.decode("#1e1e1e"));
		add(CarrinhoPainel);
	}

	public void adicionarItemAoCarrinho(String nome, int quantidade, double valorUnitario) {
		ItemCarrinho item = new ItemCarrinho(nome, quantidade, valorUnitario);
		itensCarrinho.add(item);

		// Atualiza o valor total
		valorTotal += item.getValorTotal();

		// Atualiza o rótulo do valor total
		valorLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));

		// Atualiza o painel do carrinho
		atualizarPainelCarrinho();
	}

	public void atualizarPainelCarrinho() {
		CarrinhoPainel.removeAll();

		for (ItemCarrinho item : itensCarrinho) {
			CarrinhoPainel.add(createItemCarrinhoPanel(item));
		}

		CarrinhoPainel.revalidate();
		CarrinhoPainel.repaint();
	}

	// Método para criar o painel de cada item no carrinho
	private JPanel createItemCarrinhoPanel(ItemCarrinho item) {

		JPanel itemPanel = new PainelArredondado();
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS)); // Layout horizontal
		itemPanel.setBackground(Color.GRAY);
		itemPanel.setPreferredSize(new Dimension(480, 40)); // Tamanho do item

		// Criação dos rótulos para o item
		JLabel nomeLabel = new JLabel(item.getNome());
		nomeLabel.setForeground(Color.white);
		JLabel quantidadeLabel = new JLabel("x" + item.getQuantidade());
		quantidadeLabel.setForeground(Color.white);
		JLabel precoLabel = new JLabel("R$ " + String.format("%.2f", item.getValorTotal()));
		precoLabel.setForeground(Color.white);

		// Botão para remover o item do carrinho
		JButton removeButton = new JButton("Remover");
		removeButton.setBackground(Color.RED);
		removeButton.setForeground(Color.WHITE);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itensCarrinho.remove(item); // Remove o item da lista
				valorTotal -= item.getValorTotal(); // Atualiza o valor total
				valorLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal)); // Atualiza o valor total
				atualizarPainelCarrinho(); // Atualiza o painel de itens
			}
		});

		// Adiciona os rótulos ao painel do item
		itemPanel.add(nomeLabel);
		itemPanel.add(Box.createHorizontalStrut(10)); // Espaçamento
		itemPanel.add(quantidadeLabel);
		itemPanel.add(Box.createHorizontalStrut(10)); // Espaçamento
		itemPanel.add(precoLabel);
		itemPanel.add(Box.createHorizontalStrut(10)); // Espaçamento
		itemPanel.add(removeButton); // Adiciona o botão de remoção
		
		return itemPanel;
	}

	public static void main(String[] args) {
		Carrinho carrinho = new Carrinho();
		carrinho.setVisible(true);
	}
}