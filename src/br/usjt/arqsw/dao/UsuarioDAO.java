package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Repository
public class UsuarioDAO {

	private Connection conn;

	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException {

		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);

		}

	}

	/**
	 * Método que verifica a existência do usuário passado como parâmetro no BD
	 * 
	 * @param usuario
	 * @return boolean
	 * @throws IOException
	 */
	public boolean login(Usuario usuario) throws IOException {
		String sqlSelect = "SELECT * FROM usuario WHERE email_usuario = ? and senha_usuario = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			stm.setString(1, usuario.getEmail());
			stm.setString(2, usuario.getSenha());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id_usuario"));
					usuario.setEmail(rs.getString("email_usuario"));
					usuario.setSenha(rs.getString("senha_usuario"));

					return true;

				} else {
					return false;
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

}
