package br.com.camel.dao;

import javax.persistence.EntityManager;

import br.com.camel.model.Venda;

public class VendaDao extends AbstractDao<Venda> {

    public VendaDao(EntityManager em) {
	super(em);
    }

}
