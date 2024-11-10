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