package LoginECadastrar;

import java.text.ParseException;
import entrada.BotaoArredondado;
import CadapioPrincipal.Cardapio;
import dao.ConectaMySQL;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Cadastrar extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private ImageIcon logoIcon;
	private ImageIcon resizedLogoIcon;
	private Image logoImage;
	private Image img;
	private JLabel logoLabel;
	private JLabel lblDados;
	private JLabel lblNome;
	private JLabel lblSobrenome;
	private JLabel lblSenha;
	private JLabel lblRua;
	private JLabel lblEndereco;
	private JLabel lblCPF;
	private JLabel lblTelefone;
	private JLabel lblNum;
	private JLabel lblCEP;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JLabel lblEmail;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtEmail;
	private JTextField txtRua;
	private JTextField txtNum;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JPasswordField txtSenha;
	private MaskFormatter mascaraTelefone;
	private JFormattedTextField telefoneField;
	private MaskFormatter mascaraCPF;
	private JFormattedTextField cpfField;
	private MaskFormatter mascaraCEP;
	private JFormattedTextField cepField;
	private JSeparator linha;
	private JSeparator linha2;
	private JButton btnVoltar;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpf;
	private String telefone;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private ImageIcon voltarIcon;
	private BotaoArredondado btnCadastrar;
	private ConectaMySQL conexao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		txtNome.setText(nome);
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
		txtSobrenome.setText(sobrenome);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		txtEmail.setText(email);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
		txtSenha.setText(senha);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
		cpfField.setText(cpf);
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
		telefoneField.setText(telefone);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
		cepField.setText(cep);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
		txtRua.setText(rua);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
		txtNum.setText(numero);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
		txtBairro.setText(bairro);
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
		txtCidade.setText(cidade);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
		txtEstado.setText(estado);
	}

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
		logoIcon = new ImageIcon("imagens/Logo2.png");

		logoImage = logoIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		resizedLogoIcon = new ImageIcon(logoImage);
		logoLabel = new JLabel(resizedLogoIcon);
		logoLabel.setBounds(615, 10, 200, 100);

		add(logoLabel);
	}

	private void buscarCep(String cep) {
		try {
			String url = "https://viacep.com.br/ws/" + cep + "/json/";
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			JSONObject json = new JSONObject(response.toString());

			if (json.has("erro")) {
				JOptionPane.showMessageDialog(this, "CEP inválido ou não encontrado!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				txtRua.setText(json.optString("logradouro", ""));
				txtBairro.setText(json.optString("bairro", ""));
				txtCidade.setText(json.optString("localidade", ""));
				txtEstado.setText(json.optString("uf", ""));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao buscar o CEP: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean validarCPF(String cpf) {
		// Remove todos os caracteres não numéricos
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifica se o CPF possui 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verifica se todos os números são iguais (ex: 111.111.111-11)
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999")) {
			return false;
		}

		// Validação dos dois dígitos verificadores
		int soma = 0;
		int peso = 10;

		// Valida o primeiro dígito verificador
		for (int i = 0; i < 9; i++) {
			soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * peso--;
		}

		int digito1 = 11 - (soma % 11);
		if (digito1 == 10 || digito1 == 11) {
			digito1 = 0;
		}

		// Valida o segundo dígito verificador
		soma = 0;
		peso = 11;
		for (int i = 0; i < 10; i++) {
			soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * peso--;
		}

		int digito2 = 11 - (soma % 11);
		if (digito2 == 10 || digito2 == 11) {
			digito2 = 0;
		}

		// Compara os dígitos calculados com os fornecidos
		return digito1 == Integer.parseInt(String.valueOf(cpf.charAt(9)))
				&& digito2 == Integer.parseInt(String.valueOf(cpf.charAt(10)));
	}

	public void CadastrarDados() {
		// Adiciona o Titulo
		lblDados = new JLabel("Dados Pessoais");
		lblDados.setBounds(25, 45, 160, 50);
		lblDados.setForeground(Color.decode("#ffd96d"));
		lblDados.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblDados);

		// Adicionar uma linha em baixo da JLabel Dados Pessoais
		linha = new JSeparator();
		linha.setBounds(20, 80, 160, 2);
		linha.setForeground(Color.decode("#ffd96d"));
		add(linha);

		// Adiciona o Nome
		lblNome = new JLabel("Nome");
		lblNome.setBounds(40, 100, 80, 25);
		lblNome.setForeground(Color.decode("#ffd96d"));
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(90, 100, 200, 25);
		txtNome.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNome);

		// Adiciona o Sobrenome
		lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(300, 100, 160, 25);
		lblSobrenome.setForeground(Color.decode("#ffd96d"));
		lblSobrenome.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblSobrenome);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(395, 100, 250, 25);
		txtSobrenome.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtSobrenome);

		// Adiciona o E-mail
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(40, 150, 80, 25);
		lblEmail.setForeground(Color.decode("#ffd96d"));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(90, 150, 220, 25);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtEmail);

		// Adiciona a senha
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(320, 150, 80, 25);
		lblSenha.setForeground(Color.decode("#ffd96d"));
		lblSenha.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(380, 150, 265, 25);
		txtSenha.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtSenha);

		// Adiciona o numero de telefone
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(40, 200, 80, 25);
		lblTelefone.setForeground(Color.decode("#ffd96d"));
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblTelefone);

		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
			telefoneField = new JFormattedTextField(mascaraTelefone);
			telefoneField.setBounds(110, 200, 250, 25);
			telefoneField.setFont(new Font("Arial", Font.BOLD, 16));
			add(telefoneField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona o CPF
		lblCPF = new JLabel("CPF");
		lblCPF.setBounds(370, 200, 80, 25);
		lblCPF.setForeground(Color.decode("#ffd96d"));
		lblCPF.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCPF);

		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCPF.setPlaceholderCharacter('_');
			cpfField = new JFormattedTextField(mascaraCPF);
			cpfField.setBounds(410, 200, 235, 25);
			cpfField.setFont(new Font("Arial", Font.BOLD, 16));
			add(cpfField);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona um SubTitulo
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(25, 225, 160, 50);
		lblEndereco.setForeground(Color.decode("#ffd96d"));
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblEndereco);

		// Adicionar uma linha em baixo da JLabel Endereco
		linha2 = new JSeparator();
		linha2.setBounds(20, 260, 110, 2);
		linha2.setForeground(Color.decode("#ffd96d"));
		add(linha2);

		// Adiciona o nome da rua
		lblRua = new JLabel("Endereço");
		lblRua.setBounds(40, 300, 80, 25);
		lblRua.setForeground(Color.decode("#ffd96d"));
		lblRua.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(120, 300, 250, 25);
		txtRua.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtRua);

		// Adiciona o numero da rua
		lblNum = new JLabel("Número");
		lblNum.setBounds(390, 300, 80, 25);
		lblNum.setForeground(Color.decode("#ffd96d"));
		lblNum.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblNum);

		txtNum = new JTextField();
		txtNum.setBounds(460, 300, 100, 25);
		txtNum.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtNum);

		// Adiciona o CEP
		lblCEP = new JLabel("Cep");
		lblCEP.setBounds(60, 350, 80, 25);
		lblCEP.setForeground(Color.decode("#ffd96d"));
		lblCEP.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCEP);

		// Configuração do campo de CEP para disparar busca automática
		try {
			mascaraCEP = new MaskFormatter("#####-###");
			mascaraCEP.setPlaceholderCharacter('_');
			cepField = new JFormattedTextField(mascaraCEP);
			cepField.setBounds(120, 350, 130, 25);
			cepField.setFont(new Font("Arial", Font.BOLD, 16));
			add(cepField);

			JButton btnBuscarCep = new JButton("Buscar");
			btnBuscarCep.setBounds(260, 350, 100, 25);
			btnBuscarCep.setFont(new Font("Arial", Font.BOLD, 12));
			btnBuscarCep.setBackground(Color.decode("#ffd96d"));
			btnBuscarCep.setForeground(Color.BLACK);
			add(btnBuscarCep);

			btnBuscarCep.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cep = cepField.getText().replaceAll("[^0-9]", "");
					if (!cep.isEmpty() && cep.length() == 8) {
						buscarCep(cep);
					} else {
						JOptionPane.showMessageDialog(null, "Digite um CEP válido!", "Erro de Validação",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Adiciona o Bairro
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(370, 350, 80, 25);
		lblBairro.setForeground(Color.decode("#ffd96d"));
		lblBairro.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(425, 350, 200, 25);
		txtBairro.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtBairro);

		// Adiciona a cidade
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(45, 400, 80, 25);
		lblCidade.setForeground(Color.decode("#ffd96d"));
		lblCidade.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(105, 400, 200, 25);
		txtCidade.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtCidade);

		// Adiciona o Estado
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(320, 400, 80, 25);
		lblEstado.setForeground(Color.decode("#ffd96d"));
		lblEstado.setFont(new Font("Arial", Font.BOLD, 16));
		add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(390, 400, 60, 25);
		txtEstado.setFont(new Font("Arial", Font.BOLD, 16));
		add(txtEstado);

		// Adiciona o Botao Cadastrar
		btnCadastrar = new BotaoArredondado("Alterar", 30);

		btnCadastrar.setText("Salvar");
		btnCadastrar.setBounds(300, 500, 150, 30);
		btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastrar.setBackground(Color.gray);
		btnCadastrar.setForeground(Color.decode("#ffd96d"));
		add(btnCadastrar);

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexao = new ConectaMySQL();
				Connection conn = null;
				PreparedStatement stmt = null;

				nome = txtNome.getText();
				sobrenome = txtSobrenome.getText();
				email = txtEmail.getText();
				senha = new String(txtSenha.getPassword());
				cpf = cpfField.getText().replaceAll("[^0-9]", "");
				;
				telefone = telefoneField.getText();
				cep = cepField.getText().replaceAll("[^0-9]", "");
				rua = txtRua.getText();
				numero = txtNum.getText();
				bairro = txtBairro.getText();
				cidade = txtCidade.getText();
				estado = txtEstado.getText();

				// Validação de e-mail
				if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
					JOptionPane.showMessageDialog(null, "E-mail inválido.", "Erro de Validação",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica se o CPF é válido
				if (!validarCPF(cpf)) {
					JOptionPane.showMessageDialog(null, "CPF inválido.", "Erro de Validação",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validação de campos vazios
				if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty()
						|| telefone.isEmpty() || cep.isEmpty() || rua.isEmpty() || numero.isEmpty() || bairro.isEmpty()
						|| cidade.isEmpty() || estado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro Cadastrado",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						conn = conexao.openDB();

						String sql = "INSERT INTO usuarios (nome, sobrenome, email, senha, telefone, CPF, endereco, num_casa, cep, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

						stmt = conn.prepareStatement(sql);
						stmt.setString(1, nome);
						stmt.setString(2, sobrenome);
						stmt.setString(3, email);
						stmt.setString(4, senha);
						stmt.setString(5, telefone);
						stmt.setString(6, cpf);
						stmt.setString(7, rua);
						stmt.setString(8, numero);
						stmt.setString(9, cep);
						stmt.setString(10, bairro);
						stmt.setString(11, cidade);
						stmt.setString(12, estado);

						stmt.executeUpdate();

						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
						dispose();

						Cardapio cardapio = new Cardapio();
						cardapio.setVisible(true);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + ex.getMessage(),
								"Erro de Conexão", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public void BotaoVoltar() {
		voltarIcon = new ImageIcon("imagens/seta-pequena-esquerda2.png");
		img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		btnVoltar = new JButton(voltarIcon);
		btnVoltar.setBounds(35, 5, 30, 30);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setContentAreaFilled(false);

		add(btnVoltar);

		btnVoltar.addActionListener((ActionListener) new ActionListener() {
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