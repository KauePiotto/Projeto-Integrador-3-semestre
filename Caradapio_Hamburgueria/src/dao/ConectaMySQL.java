package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Produtos.Produto;

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
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection(url, username, password);
			}
			return con;
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

	public List<Produto> getProduto(String tipo) {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM produto"; // Filtro padrão para todos os produtos

		// Se não for "all", adicione o filtro pelo tipo
		if (!tipo.equals("all")) {
			sql += " WHERE tipo = ?"; // Filtro para o tipo específico
		}

		try (Connection conn = openDB(); // Usar openDB() para a conexão
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Se o filtro não for "all", setar o tipo
			if (!tipo.equals("all")) {
				stmt.setString(1, tipo); // Define o tipo de filtro
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"), rs.getDouble("preco"),
						rs.getString("tipo"), rs.getBytes("logo"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}
}