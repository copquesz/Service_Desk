package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Repository
public class ChamadoDAO {

	@PersistenceContext
	EntityManager manager;

	/**
	 * Cria um chamado através da persistencia de dados com JPA Hibernate - persist
	 * 
	 * @param chamado
	 * @return método que carrega o ultimo chamado inserido
	 * @throws IOException
	 */
	public Chamado criar(Chamado chamado) throws IOException {
		manager.persist(chamado);
		return ultimoChamado();
	}

	/**
	 * Carrega um bean com uma query personalizada feita com JPA Hibernate
	 * 
	 * @return busca o último chamado inserido
	 * @throws IOException
	 */
	private Chamado ultimoChamado() throws IOException {
		return (Chamado) manager.createQuery("select chamado from Chamado chamado order by chamado.id desc")
				.setMaxResults(1).getSingleResult();
	}

	/**
	 * Carrega um bean passando um id como parâmetro
	 * 
	 * @param id
	 * @return chamado
	 * @throws IOException
	 */
	public Chamado carregar(int id) throws IOException {
		return manager.find(Chamado.class, id);
	}

	/**
	 * Faz um update no chamado utilizando persistência de dados com JPA Hibernate -
	 * merge
	 * 
	 * @param chamado
	 * @throws IOException
	 */
	public void fecharChamado(Chamado chamado) throws IOException {
		manager.merge(chamado);

	}

	/**
	 * Busca uma lista de chamados passando uma fila como parâmetro utilizando JPA
	 * Hibernate
	 * 
	 * @param fila
	 * @return lista de chamados
	 * @throws IOException
	 */
	public ArrayList<Chamado> listar(Fila fila) throws IOException {
		fila = manager.find(Fila.class, fila.getId());

		String sql = "SELECT c FROM Chamado c WHERE c.fila = :fila";

		Query query = manager.createQuery(sql, Chamado.class);
		query.setParameter("fila", fila);

		ArrayList<Chamado> lista = (ArrayList<Chamado>) query.getResultList();

		return lista;
	}

}
