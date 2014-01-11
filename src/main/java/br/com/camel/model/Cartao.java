package br.com.camel.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cartao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(nullable = false)
    private BigDecimal taxa;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Tipo getTipo() {
	return tipo;
    }

    public void setTipo(Tipo tipo) {
	this.tipo = tipo;
    }

    public BigDecimal getTaxa() {
	return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
	this.taxa = taxa;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
	result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
	Cartao other = (Cartao) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (nome == null) {
	    if (other.nome != null)
		return false;
	} else if (!nome.equals(other.nome))
	    return false;
	if (taxa == null) {
	    if (other.taxa != null)
		return false;
	} else if (!taxa.equals(other.taxa))
	    return false;
	if (tipo != other.tipo)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return nome + " - " + tipo;
    }

}
