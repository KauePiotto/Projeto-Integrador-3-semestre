package LoginECadastrar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import Cadapio_Pag_Inicial.Cardapio;

public class Cadastrar extends JFrame {
	public Cadastrar() {
		setTitle("Cadastrar - Byell Hambúrgueria");
		setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);
		Centralizar();
		Logo();
		CadastrarDados();
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

	public void Logo() {
		ImageIcon logoIcon = new ImageIcon("C:\\Users\\User\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\Logo2.png");

		Image logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
	}

	class BotaoArredondado extends JButton {
		private int raio;

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

			// Desenha o fundo do botão com bordas arredondadas
			g2.setColor(getBackground());
			g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), raio, raio));

			super.paintComponent(g);
			g2.dispose();
		}
	}

	public void CadastrarDados() {
		// Adiciona o Titulo
		JLabel DadosP = new JLabel("Dados Pessoais");
		DadosP.setBounds(25, 25, 160, 50);
		DadosP.setForeground(Color.decode("#ffd96d"));
		DadosP.setFont(new Font("Arial", Font.BOLD, 20));
		add(DadosP);

		// Adicionar uma linha em baixo da JLabel Dados Pessoais
		JSeparator linha = new JSeparator();
		linha.setBounds(20, 60, 160, 2);
		linha.setForeground(Color.decode("#ffd96d"));
		add(linha);

		// Adiciona o Nome
		JLabel NoLabel = new JLabel("Nome");
		NoLabel.setBounds(40, 70, 80, 25);
		NoLabel.setForeground(Color.decode("#ffd96d"));
		NoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(NoLabel);

		JTextField NoField = new JTextField();
		NoField.setBounds(90, 70, 200, 25);
		NoField.setFont(new Font("Arial", Font.BOLD, 16));
		add(NoField);

		// Adiciona o Sobrenome
		JLabel SoLabel = new JLabel("Sobrenome");
		SoLabel.setBounds(300, 70, 160, 25);
		SoLabel.setForeground(Color.decode("#ffd96d"));
		SoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(SoLabel);

		JTextField SoField = new JTextField();
		SoField.setBounds(395, 70, 250, 25);
		SoField.setFont(new Font("Arial", Font.BOLD, 16));
		add(SoField);

		// Adiciona o E-mail
		JLabel emLabel = new JLabel("E-mail");
		emLabel.setBounds(40, 120, 80, 25);
		emLabel.setForeground(Color.decode("#ffd96d"));
		emLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(emLabel);

		JTextField emaField = new JTextField();
		emaField.setBounds(90, 120, 220, 25);
		emaField.setFont(new Font("Arial", Font.BOLD, 16));
		add(emaField);

		// Adiciona a senha
		JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setBounds(320, 120, 80, 25);
		passwordLabel.setForeground(Color.decode("#ffd96d"));
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(passwordLabel);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(380, 120, 265, 25);
		passwordField.setFont(new Font("Arial", Font.BOLD, 16));
		add(passwordField);

		// Adiciona o numero de telefone
		JLabel Telelbl = new JLabel("Telefone");
		Telelbl.setBounds(40, 160, 80, 25);
		Telelbl.setForeground(Color.decode("#ffd96d"));
		Telelbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Telelbl);

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
			JFormattedTextField telefoneField = new JFormattedTextField(mascaraTelefone);
			telefoneField.setBounds(110, 160, 250, 25);
			telefoneField.setFont(new Font("Arial", Font.BOLD, 16));
			add(telefoneField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona o CPF
		JLabel CPFlbl = new JLabel("CPF");
		CPFlbl.setBounds(370, 160, 80, 25);
		CPFlbl.setForeground(Color.decode("#ffd96d"));
		CPFlbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(CPFlbl);

		try {
			MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCPF.setPlaceholderCharacter('_');
			JFormattedTextField cpfField = new JFormattedTextField(mascaraCPF);
			cpfField.setBounds(410, 160, 235, 25);
			cpfField.setFont(new Font("Arial", Font.BOLD, 16));
			add(cpfField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona um SubTitulo
		JLabel Ende = new JLabel("Endereço");
		Ende.setBounds(25, 200, 160, 50);
		Ende.setForeground(Color.decode("#ffd96d"));
		Ende.setFont(new Font("Arial", Font.BOLD, 20));
		add(Ende);

		// Adicionar uma linha em baixo da JLabel Endereco
		JSeparator linha2 = new JSeparator();
		linha2.setBounds(20, 235, 110, 2);
		linha2.setForeground(Color.decode("#ffd96d"));
		add(linha2);

		// Adiciona o nome da rua
		JLabel Rualbl = new JLabel("Endereço");
		Rualbl.setBounds(40, 250, 80, 25);
		Rualbl.setForeground(Color.decode("#ffd96d"));
		Rualbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Rualbl);

		JTextField Rualbl2 = new JTextField();
		Rualbl2.setBounds(120, 250, 250, 25);
		Rualbl2.setFont(new Font("Arial", Font.BOLD, 16));
		add(Rualbl2);

		// Adiciona o nome da rua
		JLabel Numlbl = new JLabel("Número");
		Numlbl.setBounds(390, 250, 80, 25);
		Numlbl.setForeground(Color.decode("#ffd96d"));
		Numlbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Numlbl);

		JTextField Numlbl2 = new JTextField();
		Numlbl2.setBounds(460, 250, 100, 25);
		Numlbl2.setFont(new Font("Arial", Font.BOLD, 16));
		add(Numlbl2);

		// Adiciona o CEP
		JLabel Ceplbl = new JLabel("Cep");
		Ceplbl.setBounds(60, 300, 80, 25);
		Ceplbl.setForeground(Color.decode("#ffd96d"));
		Ceplbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Ceplbl);

		try {
			// Máscara para o CEP
			MaskFormatter mascaraCEP = new MaskFormatter("#####-###");
			mascaraCEP.setPlaceholderCharacter('_');
			JFormattedTextField cepField = new JFormattedTextField(mascaraCEP);
			cepField.setBounds(120, 300, 130, 25);
			cepField.setFont(new Font("Arial", Font.BOLD, 16));
			add(cepField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona o Bairro
		JLabel Bairrolbl = new JLabel("Bairro");
		Bairrolbl.setBounds(270, 300, 80, 25);
		Bairrolbl.setForeground(Color.decode("#ffd96d"));
		Bairrolbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Bairrolbl);

		JTextField Bailbl = new JTextField();
		Bailbl.setBounds(330, 300, 200, 25);
		Bailbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Bailbl);

		// Adiciona a cidade
		JLabel Cidadelbl = new JLabel("Cidade");
		Cidadelbl.setBounds(45, 340, 80, 25);
		Cidadelbl.setForeground(Color.decode("#ffd96d"));
		Cidadelbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Cidadelbl);

		JTextField Cidlbl = new JTextField();
		Cidlbl.setBounds(105, 340, 200, 25);
		Cidlbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Cidlbl);

		// Adiciona o Estado
		JLabel Estadolbl = new JLabel("Estado");
		Estadolbl.setBounds(320, 340, 80, 25);
		Estadolbl.setForeground(Color.decode("#ffd96d"));
		Estadolbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Estadolbl);

		JTextField Estalbl = new JTextField();
		Estalbl.setBounds(390, 340, 200, 25);
		Estalbl.setFont(new Font("Arial", Font.BOLD, 16));
		add(Estalbl);

		// Adiciona o Botao Cadastrar
		BotaoArredondado btnCadastrar = new BotaoArredondado("Cadastrar", 30);

		btnCadastrar.setText("Cadastrar");
		btnCadastrar.setBounds(300, 430, 150, 30);
		btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastrar.setBackground(Color.gray);
		btnCadastrar.setForeground(Color.decode("#ffd96d"));
		add(btnCadastrar);

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pegando os valores dos campos
				String nome = NoField.getText();
				String sobrenome = SoField.getText();
				String email = emaField.getText();
				String senha = new String(passwordField.getPassword());
				String cpf = CPFlbl.getText();
				String telefone = Numlbl.getText();
				String cep = Ceplbl.getText();
				String rua = Rualbl2.getText();
				String numero = Numlbl2.getText();
				String bairro = Bailbl.getText();
				String cidade = Cidlbl.getText();
				String estado = Estalbl.getText();

				if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty()
						|| telefone.isEmpty() || cep.isEmpty() || rua.isEmpty() || numero.isEmpty() || bairro.isEmpty()
						|| cidade.isEmpty() || estado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro de Cadastro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cadastro bem-sucedido!");
					dispose();

					Cardapio cardapio = new Cardapio();
					cardapio.setVisible(true);
				}
			}
		});
	}

	public void BotaoVoltar() {
		ImageIcon voltarIcon = new ImageIcon(
				"C:\\Users\\User\\Desktop\\Projeto-Integrador-3-semestre\\Fotos\\seta-pequena-esquerda2.png");
		Image img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		JButton voltarButton = new JButton(voltarIcon);
		voltarButton.setBounds(35, 5, 30, 30);
		voltarButton.setBorderPainted(false);
		voltarButton.setFocusPainted(false);
		voltarButton.setContentAreaFilled(false);

		add(voltarButton);

		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		Cadastrar cadastrar = new Cadastrar();
		cadastrar.setVisible(true);
	}
}