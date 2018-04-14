package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.dao.ConnectionFactory;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
public class ChamadoDAO {

	/**
	 * Método de criar chamado no BD
	 * 
	 * @param chamado
	 * @return chamado
	 * @throws IOException
	 */
	public Chamado criar(Chamado chamado) throws IOException {
		String query = "INSERT INTO chamado(descricao, status, dt_abertura, id_fila) VALUES (?, ?, ?, ?)";
		Connection conn = ConnectionFactory.obterConexao();
		// usando o try with resources do Java 7, que fecha o que abriu
		try (PreparedStatement stm = conn.prepareStatement(query);) {
			chamado.setAbertura(new java.util.Date());

			stm.setString(1, chamado.getDescricao());
			stm.setString(2, Chamado.ABERTO);
			stm.setDate(3, new Date(chamado.getAbertura().getTime()));
			stm.setInt(4, chamado.getFila().getId());
			stm.execute();
			String query1 = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(query1); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					chamado.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new IOException(e1);
		}
		return carregar(chamado.getId());
	}

	/**
	 * Método de pesquisar um chamado no BD
	 * 
	 * @param id
	 * @return chamado
	 * @throws IOException
	 */
	public Chamado carregar(int id) throws IOException {
		Chamado chamado = new Chamado();
		Fila fila = new Fila();
		chamado.setId(id);
		String query = "SELECT * FROM chamado inner join fila on fila.id_fila = chamado.id_fila WHERE chamado.id_chamado = ?;";
		Connection conn = ConnectionFactory.obterConexao();
		// usando o try with resources do Java 7, que fecha o que abriu
		try (PreparedStatement stm = conn.prepareStatement(query);) {
			stm.setInt(1, chamado.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					fila.setId(rs.getInt("id_fila"));
					fila.setNome(rs.getString("nm_fila"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setStatus(rs.getString("status"));
					chamado.setAbertura(rs.getDate("dt_abertura"));
					chamado.setFechamento(rs.getDate("dt_fechamento"));
					chamado.setFila(fila);
				} else {
					fila.setId(0);
					chamado.setDescricao(null);
					chamado.setStatus(null);
					chamado.setAbertura(null);
					chamado.setFechamento(null);
					chamado.setFila(null);
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			throw new IOException(e1);
		}
		return chamado;
	}

	/**
	 * Método de fechar um chamado no BD
	 * 
	 * @param chamado
	 * @throws IOException
	 */
	public void fecharChamado(Chamado chamado) throws IOException {
		String query = "UPDATE chamado SET status = ?, dt_fechamento = ? WHERE chamado.id_chamado = ?";
		Connection conn = ConnectionFactory.obterConexao();
		// usando o try with resources do Java 7, que fecha o que abriu
		try (PreparedStatement stm = conn.prepareStatement(query);) {
			chamado.setFechamento(new java.util.Date());

			stm.setString(1, Chamado.FECHADO);
			stm.setDate(2, new Date(chamado.getFechamento().getTime()));
			stm.setInt(3, chamado.getId());
			stm.execute();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	/**
	 * Método que lista chamados do BD
	 * 
	 * @param fila
	 * @return chamados
	 * @throws IOException
	 */
	public ArrayList<Chamado> listar(Fila fila) throws IOException {
		ArrayList<Chamado> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.status, c.dt_abertura, c.dt_fechamento, f.nm_fila "
				+ "from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=?";

		Connection conn = ConnectionFactory.obterConexao();
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, fila.getId());

			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					Chamado chamado = new Chamado();
					chamado.setId(rs.getInt("id_chamado"));
					chamado.setStatus(rs.getString("status"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setAbertura(rs.getDate("dt_abertura"));
					chamado.setFechamento(rs.getDate("dt_fechamento"));
					fila.setNome(rs.getString("nm_fila"));
					chamado.setFila(fila);
					lista.add(chamado);
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}

}
