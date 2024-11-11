package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConectaMySQL {

	private final static String url = "jdbc:mysql://localhost:3307/hamburgueria";
	private final static String username = "root";
	private final static String password = "p@$$w0rd";
	private static Connection con;
	private Statement stmt;
	private ResultSet rs;

	public static void main(String args[]) {
		ConectaMySQL db = new ConectaMySQL();
		db.openDB();
		db.closeDB();
	}
	
	public Connection openDB() {
		try {
			// Conectar ao banco de dados
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			System.out.println("Erro de conexão: " + ex.getMessage());
			return null;
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

	// Método para buscar os produtos por tipo
	public List<String> getProdutosByTipo(String tipo) {
		List<String> produtos = new ArrayList<>();
		String query = "SELECT nome FROM produto WHERE tipo = ?";

		// Garantir que a conexão esteja aberta antes de tentar executá-la
		try (Connection con = openDB(); PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, tipo);
			ResultSet rs = pstmt.executeQuery();

			// Recuperar os resultados
			while (rs.next()) {
				produtos.add(rs.getString("nome"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar produtos: " + e.getMessage());
		}
		return produtos;
	}
}