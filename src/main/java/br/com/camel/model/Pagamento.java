package br.com.camel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pagamento implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal valor;
    private String autorizacao;
    private String nsuCvDoc;

    @Temporal(TemporalType.DATE)
    private Calendar dataPagamento;

    @ManyToOne
    private Cartao cartao;

    @ManyToOne
    private Venda venda;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Calendar getDataPagamento() {
	return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
	this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public String getAutorizacao() {
	return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
	this.autorizacao = autorizacao;
    }

    public String getNsuCvDoc() {
	return nsuCvDoc;
    }

    public void setNsuCvDoc(String nsuCvDoc) {
	this.nsuCvDoc = nsuCvDoc;
    }

    public Cartao getCartao() {
	return cartao;
    }

    public void setCartao(Cartao cartao) {
	this.cartao = cartao;
    }

    public Venda getVenda() {
	return venda;
    }

    public void setVenda(Venda venda) {
	this.venda = venda;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((autorizacao == null) ? 0 : autorizacao.hashCode());
	result = prime * result + ((cartao == null) ? 0 : cartao.hashCode());
	result = prime * result
		+ ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result
		+ ((nsuCvDoc == null) ? 0 : nsuCvDoc.hashCode());
	result = prime * result + ((valor == null) ? 0 : valor.hashCode());
	result = prime * result + ((venda == null) ? 0 : venda.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pagamento other = (Pagamento) obj;
	if (autorizacao == null) {
	    if (other.autorizacao != null)
		return false;
	} else if (!autorizacao.equals(other.autorizacao))
	    return false;
	if (cartao == null) {
	    if (other.cartao != null)
		return false;
	} else if (!cartao.equals(other.cartao))
	    return false;
	if (dataPagamento == null) {
	    if (other.dataPagamento != null)
		return false;
	} else if (!dataPagamento.equals(other.dataPagamento))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (nsuCvDoc == null) {
	    if (other.nsuCvDoc != null)
		return false;
	} else if (!nsuCvDoc.equals(other.nsuCvDoc))
	    return false;
	if (valor == null) {
	    if (other.valor != null)
		return false;
	} else if (!valor.equals(other.valor))
	    return false;
	if (venda == null) {
	    if (other.venda != null)
		return false;
	} else if (!venda.equals(other.venda))
	    return false;
	return true;
    }

}
