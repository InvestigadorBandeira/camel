package br.com.camel.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.camel.controller.VendaController;
import br.com.camel.dao.CartaoDao;
import br.com.camel.dao.ClienteDao;
import br.com.camel.dao.VendaDao;
import br.com.camel.model.Cliente;
import br.com.camel.model.Pagamento;
import br.com.camel.model.Venda;
import br.com.camel.util.Conexao;

public class Main {
    public static void main(String[] args) {
	System.out.println("Iniciando o sistema.");

	new VendaController(Conexao.getInstance());
	// new TestView();

	// criaCliente(em);
	// criaVendas(em);

	// Conexao.getInstance().getTransaction().begin();
	// Conexao.getInstance().getTransaction().commit();
	// Conexao.getInstance().close();
	// emf.close();

	System.out.println("Finalizando o sistema.");

    }

    private static void criaVendas(EntityManager em) {
	VendaDao dao = new VendaDao(em);

	Venda venda = new Venda();
	venda.setCliente(new ClienteDao(em).findById(1L));
	venda.setDataVenda(Calendar.getInstance());
	venda.setDescricao("Celular Samsung Galaxy Ace Duos");
	venda.setValor(new BigDecimal("799.00"));

	venda = dao.findById(1L);
	venda.setDesconto(new BigDecimal("0"));

	List<Pagamento> pagamentos = new ArrayList<>();
	Pagamento p1 = new Pagamento();
	Pagamento p2 = new Pagamento();

	p1.setAutorizacao("123456789");
	p1.setCartao(new CartaoDao(em).findById(1L));
	p1.setDataPagamento(Calendar.getInstance());
	p1.setNsuCvDoc("405060");
	p1.setValor(new BigDecimal("399.5"));
	p1.setVenda(venda); // obrigatório

	p2.setAutorizacao("430896126");
	p2.setCartao(new CartaoDao(em).findById(2L));
	p2.setDataPagamento(Calendar.getInstance());
	p2.setNsuCvDoc("987654");
	p2.setValor(new BigDecimal("399.5"));
	p2.setVenda(venda); // obrigatório

	pagamentos.add(p1);
	pagamentos.add(p2);

	venda.setPagamentos(pagamentos);

	// dao.update(venda);
	// em.persist(p1);
	// em.persist(p2);
	// em.merge(p1);
	// em.merge(p2);

    }

    private static void criaCliente(EntityManager em) {
	ClienteDao dao = new ClienteDao(em);
	Cliente cliente = new Cliente();
	cliente.setNome("DIVERSOS");
	// dao.save(cliente);
    }
}
