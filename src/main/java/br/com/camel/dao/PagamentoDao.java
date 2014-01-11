package br.com.camel.dao;

import javax.persistence.EntityManager;

import br.com.camel.model.Pagamento;

public class PagamentoDao extends AbstractDao<Pagamento> {

    public PagamentoDao(EntityManager em) {
	super(em);
    }

}
