package br.com.camel.dao;

import javax.persistence.EntityManager;

import br.com.camel.model.Cliente;

public class ClienteDao extends AbstractDao<Cliente> {

    public ClienteDao(EntityManager em) {
	super(em);
    }

}
