package br.com.camel.dao;

import javax.persistence.EntityManager;

import br.com.camel.model.Cartao;

public class CartaoDao extends AbstractDao<Cartao> {

    public CartaoDao(EntityManager em) {
	super(em);
    }

}
