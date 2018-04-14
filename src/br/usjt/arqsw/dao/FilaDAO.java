package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Repository
public class FilaDAO {

	private Connection conn;

	@Autowired
	public FilaDAO(DataSource dataSource) throws IOException {

		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);

		}

	}

	/**
	 * Método de pesquisa uma fila no BD
	 * 
	 * @param id
	 * @return fila
	 * @throws IOException
	 */
	public Fila carregar(int id) throws IOException {
		Fila fila = new Fila();
		fila.setId(id);
		String query = "SELECT * FROM fila WHERE fila.id_fila = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (PreparedStatement stm = conn.prepareStatement(query);) {
			stm.setInt(1, fila.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					fila.setNome(rs.getString("nm_fila"));
				} else {
					fila.setId(0);
					fila.setNome(null);
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			throw new IOException(e1);
		}
		return fila;
	}

	/**
	 * Método de listar filas do BD
	 * 
	 * @return filas
	 * @throws IOException
	 */
	public ArrayList<Fila> listar() throws IOException {
		String query = "SELECT ID_FILA, NM_FILA from FILA";
		ArrayList<Fila> lista = new ArrayList<>();

		try (PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Fila fila = new Fila();
				fila.setId(rs.getInt("ID_FILA"));
				fila.setNome(rs.getString("NM_FILA"));

				lista.add(fila);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}

}
