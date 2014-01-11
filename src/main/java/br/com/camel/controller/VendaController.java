package br.com.camel.controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.camel.dao.CartaoDao;
import br.com.camel.dao.VendaDao;
import br.com.camel.model.Cartao;
import br.com.camel.model.Pagamento;
import br.com.camel.util.Mensagem;
import br.com.camel.view.PagamentoView;
import br.com.camel.view.VendaView;

public class VendaController {
    private final VendaView view;
    private final VendaDao dao;
    private final Mensagem mensagem;
    private final CartaoDao cartaoDao;

    public VendaController(EntityManager em) {
	view = new VendaView(this);
	dao = new VendaDao(em);
	cartaoDao = new CartaoDao(em);
	mensagem = new Mensagem(view, " :: Lançamento de Vendas ::");
	view.setVisible(true);
    }

    public void lancaPagamentos(List<Pagamento> pagamentos) {
	if (!view.finalizaVenda())
	    mensagem.info("Sem dados para finalizar venda.");

	else {
	    List<Cartao> cartoes = cartaoDao.findAll();

	    if (cartoes.isEmpty())
		mensagem.info("Não existe cartões cadastrados");

	    else if (!view.finalizaVenda())
		mensagem.info("Sem dados para finalizar venda.");

	    else {
		PagamentoView pgView = new PagamentoView(pagamentos);
		pgView.preencheComboCartao(cartoes);

		pgView.setVisible(true);
	    }
	}

    }
}
