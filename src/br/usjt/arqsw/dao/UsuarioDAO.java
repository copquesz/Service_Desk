package br.usjt.arqsw.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.usjt.arqsw.entity.Usuario;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Repository
public class UsuarioDAO {

	@PersistenceContext
	EntityManager manager;

	/**
	 * Busca um usuário no banco utilizando persistecia de dados JPA Hibernate
	 * 
	 * @param usuario
	 * @return
	 * @throws IOException
	 */
	public boolean login(Usuario usuario) throws IOException {
		String sql = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		Query query = manager.createQuery(sql, Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			usuario = null;
		}

		return (usuario != null);

	}
}
