package br.com.camel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venda implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal valor;
    private BigDecimal desconto;
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Calendar dataVenda;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "venda")
    private List<Pagamento> pagamentos;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public BigDecimal getDesconto() {
	return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
	this.desconto = desconto;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Calendar getDataVenda() {
	return dataVenda;
    }

    public void setDataVenda(Calendar dataVenda) {
	this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public List<Pagamento> getPagamentos() {
	return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
	this.pagamentos = pagamentos;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
	result = prime * result
		+ ((dataVenda == null) ? 0 : dataVenda.hashCode());
	result = prime * result
		+ ((desconto == null) ? 0 : desconto.hashCode());
	result = prime * result
		+ ((descricao == null) ? 0 : descricao.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result
		+ ((pagamentos == null) ? 0 : pagamentos.hashCode());
	result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
	Venda other = (Venda) obj;
	if (cliente == null) {
	    if (other.cliente != null)
		return false;
	} else if (!cliente.equals(other.cliente))
	    return false;
	if (dataVenda == null) {
	    if (other.dataVenda != null)
		return false;
	} else if (!dataVenda.equals(other.dataVenda))
	    return false;
	if (desconto == null) {
	    if (other.desconto != null)
		return false;
	} else if (!desconto.equals(other.desconto))
	    return false;
	if (descricao == null) {
	    if (other.descricao != null)
		return false;
	} else if (!descricao.equals(other.descricao))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (pagamentos == null) {
	    if (other.pagamentos != null)
		return false;
	} else if (!pagamentos.equals(other.pagamentos))
	    return false;
	if (valor == null) {
	    if (other.valor != null)
		return false;
	} else if (!valor.equals(other.valor))
	    return false;
	return true;
    }

}
