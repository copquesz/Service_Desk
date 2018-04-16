package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Repository
public class FilaDAO {

	@PersistenceContext
	EntityManager manager;

	/**
	 * Carrega uma fila utilizando persistencia de dados JPA Hibernate
	 * 
	 * @param id
	 * @return fila
	 * @throws IOException
	 */
	public Fila carregar(int id) throws IOException {
		return manager.find(Fila.class, id);
	}

	/**
	 * Busca uma lista de filas utilizando uma query personalizada JPA Hibernate
	 * 
	 * @return lista de filas
	 * @throws IOException
	 */
	public ArrayList<Fila> listar() throws IOException {
		return (ArrayList<Fila>) manager.createQuery("SELECT f FROM Fila f", Fila.class).getResultList();
	}

}
