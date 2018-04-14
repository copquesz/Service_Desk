package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
public class ConnectionFactory {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que estabelece a conexão com o BD
	 * 
	 * @return conn
	 * @throws IOException
	 */
	public static Connection obterConexao() throws IOException {
		String url = "jdbc:mysql://localhost/servicedesk?user=yabaconsultoria&password=yaba2389&useSSL=false";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return conn;
	}
}
