package dao;

import java.sql.*;

public class ConectaDao {

	private final static String url = "jdbc:mysql://localhost:3307/hamburgueria";
	private final static String username = "root";
	private final static String password = "p@$$w0rd";
	private static Connection con;
	private Statement stmt;
	private ResultSet rs;

	public static void main(String args[]) {
		ConectaDao b = new ConectaDao();
		b.openDB();
		b.closeDB();
		b.inserirUsuario("Nome", "Sobrenome", "email@exemplo.com", "senha123", "12345678900", "123456789", "12345-678",
				"Rua Exemplo", "123", "Bairro Exemplo", "Cidade Exemplo", "Estado Exemplo");

	}

	public Connection openDB() {
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("conexão estabelecida com sucesso!\n");
		} catch (Exception e) {
			System.out.println("Não foi possivel estabelecer conexão " + e + "\n");
			System.exit(1);
		}
		return con;
	}

	public void inserirUsuario(String nome, String sobrenome, String email, String senha, String cpf, String telefone,
			String cep, String rua, String numero, String bairro, String cidade, String estado) {
		String sql = "INSERT INTO usuarios (nome, sobrenome, email, senha, cpf, telefone, cep, rua, numero, bairro, cidade, estado) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, nome);
			stmt.setString(2, sobrenome);
			stmt.setString(3, email);
			stmt.setString(4, senha);
			stmt.setString(5, cpf);
			stmt.setString(6, telefone);
			stmt.setString(7, cep);
			stmt.setString(8, rua);
			stmt.setString(9, numero);
			stmt.setString(10, bairro);
			stmt.setString(11, cidade);
			stmt.setString(12, estado);

// Executa a inserção
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("Não foi possivel fechar conexão " + e + "\n");
			System.exit(1);
		}
	}

	public void closeDB(Connection cn, Statement st, ResultSet rs) throws SQLException {
		if (cn != null)
			cn.close();
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
	}

	public void closeDB(Statement st, ResultSet rs) throws SQLException {
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
	}
}