package br.com.camel.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.camel.exception.CamelException;
import br.com.camel.model.Usuario;

public class UsuarioDao extends AbstractDao<Usuario> {
    public UsuarioDao(EntityManager em) {
	super(em);
    }

    public boolean existeUsuario(Usuario usuario) {
	// Usuario encontrado = (Usuario) session.createCriteria(Usuario.class)
	// .add(Restrictions.eq("login", usuario.getLogin()))
	// .uniqueResult();
	// return encontrado != null;

	Usuario encontrado = new Usuario();

	Query query = em
		.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");

	query.setParameter("login", usuario.getLogin());

	encontrado = (Usuario) query.getSingleResult();

	return encontrado != null;
    }

    public Usuario carrega(Usuario usuario) throws Exception {

	Usuario encontrado = null;

	Query query = em
		.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");

	query.setParameter("login", usuario.getLogin()).setParameter("senha",
		usuario.getSenha());

	try {
	    encontrado = (Usuario) query.getSingleResult();
	} catch (NoResultException ex) {
	    throw new CamelException("Usuário e/ou senha inválidos.");
	}

	return encontrado;
    }
}
