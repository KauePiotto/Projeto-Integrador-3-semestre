package HubDeBaixo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.json.JSONObject;
import entrada.BotaoArredondado;
import CadapioPrincipal.Cardapio;
import dao.ConectaMySQL;

public class Perfil extends JFrame {
	private Dimension screen;
	private Dimension janela;
	private BotaoArredondado btnAlterar;
	private JLabel lblDados;
	private JSeparator linha;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblSobrenome;
	private JTextField txtSobrenome;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblSenha;
	private JPasswordField txtSenha;
	private JLabel lblTelefone;
	private MaskFormatter mascaraTelefone;
	private JFormattedTextField telefoneField;
	private JLabel lblCPF;
	private MaskFormatter mascaraCPF;
	private JFormattedTextField cpfField;
	private JLabel lblEndereco;
	private JSeparator linha2;
	private JLabel lblRua;
	private JTextField txtRua;
	private JLabel lblNum;
	private JTextField txtNum;
	private JLabel lblCEP;
	private MaskFormatter mascaraCEP;
	private JFormattedTextField cepField;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpf;
	private String telefone;
	private String cep;
	private String Rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private ImageIcon voltarIcon;
	private Image img;
	private JButton btnVoltar;
	private ConectaMySQL conn;
	private static boolean usuariologado = false;

	public void setTxtNome(String nome) {
		this.txtNome.setText(nome);
	}

	public void setTxtSobrenome(String sobrenome) {
		this.txtSobrenome.setText(sobrenome);
	}

	public void setTelefoneField(String telefone) {
		this.telefoneField.setText(telefone);
	}

	public void setCpfField(String cpf) {
		this.cpfField.setText(cpf);
	}

	public void setTxtRua(String endereco) {
		this.txtRua.setText(endereco);
	}

	public void setTxtNum(String num_casa) {
		this.txtNum.setText(num_casa);
	}

	public void setCepField(String cep) {
		this.cepField.setText(cep);
	}

	public void setTxtBairro(String bairro) {
		this.txtBairro.setText(bairro);
	}

	public void setTxtCidade(String cidade) {
		this.txtCidade.setText(cidade);
	}

	public void setTxtEstado(String estado) {
		this.txtEstado.setText(estado);
	}

	public Perfil() {

		setTitle("Perfil - Byell Hambúrgueria");
		getContentPane().setBackground(Color.decode("#1e1e1e"));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(800, 600);

		conn = new ConectaMySQL();

		Centralizar();
		PerfilUsuario();
		BotaoVoltar();
		RecuperarDadosUsuario();
		desabilitarCampos();

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

	// Esse método agora vai buscar os dados do usuário com base no email
	public void RecuperarDadosUsuario() {
		try {
			String sql = "SELECT * FROM usuarios WHERE email = ?";
			PreparedStatement stmt = conn.openDB().prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// Preenche os campos com os dados do banco
				txtNome.setText(rs.getString("nome"));
				txtSobrenome.setText(rs.getString("sobrenome"));
				txtEmail.setText(rs.getString("email"));
				txtSenha.setText(rs.getString("senha"));
				telefoneField.setText(rs.getString("telefone"));
				cpfField.setText(rs.getString("cpf"));
				txtRua.setText(rs.getString("endereco"));
				txtNum.setText(rs.getString("num_casa"));
				cepField.setText(rs.getString("cep"));
				txtBairro.setText(rs.getString("bairro"));
				txtCidade.setText(rs.getString("cidade"));
				txtEstado.setText(rs.getString("estado"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar dados do usuário: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Desabilitar os campos quando o usuário não estiver logado
	public void desabilitarCampos() {
		txtNome.setEnabled(false);
		txtSobrenome.setEnabled(false);
		txtEmail.setEnabled(false);
		txtSenha.setEnabled(false);
		telefoneField.setEnabled(false);
		cpfField.setEnabled(false);
		txtRua.setEnabled(false);
		txtNum.setEnabled(false);
		cepField.setEnabled(false);
		txtBairro.setEnabled(false);
		txtCidade.setEnabled(false);
		txtEstado.setEnabled(false);
		btnAlterar.setEnabled(false);
	}

	// Habilitar todos os campos para edição quando estiver logado
	public void habilitarCampos() {
		if (usuariologado) {
			txtNome.setEnabled(true);
			txtSobrenome.setEnabled(true);
			txtEmail.setEnabled(true);
			txtSenha.setEnabled(true);
			telefoneField.setEnabled(true);
			cpfField.setEnabled(true);
			txtRua.setEnabled(true);
			txtNum.setEnabled(true);
			cepField.setEnabled(true);
			txtBairro.setEnabled(true);
			txtCidade.setEnabled(true);
			txtEstado.setEnabled(true);
			btnAlterar.setEnabled(true);
		}

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

	public void PerfilUsuario() {
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
		btnAlterar = new BotaoArredondado("Salvar Alteração", 30);

		btnAlterar.setText("Salvar Alteração");
		btnAlterar.setBounds(300, 500, 200, 35);
		btnAlterar.setFont(new Font("Arial", Font.BOLD, 16));
		btnAlterar.setBackground(Color.gray);
		btnAlterar.setForeground(Color.decode("#ffd96d"));
		add(btnAlterar);

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Conta Alterada com sucesso");
			}
		});

	}

	public void BotaoVoltar() {
		voltarIcon = new ImageIcon("imagens\\seta-pequena-esquerda2.png");
		img = voltarIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		voltarIcon = new ImageIcon(img);

		btnVoltar = new JButton(voltarIcon);
		btnVoltar.setBounds(35, 15, 30, 30);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setContentAreaFilled(false);

		add(btnVoltar);

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cardapio cardapio = new Cardapio();
				cardapio.setVisible(true);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		Perfil perfil = new Perfil();
		perfil.setVisible(true);
	}
}