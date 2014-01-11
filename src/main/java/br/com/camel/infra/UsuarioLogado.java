package br.com.camel.infra;

import br.com.camel.model.Usuario;

public class UsuarioLogado {
    private Usuario logado;
    private static UsuarioLogado instance;

    private UsuarioLogado() {
    }

    public static UsuarioLogado instance() {
	if (instance == null)
	    instance = new UsuarioLogado();
	return instance;
    }

    public void login(Usuario usuario) {
	this.logado = usuario;
    }

    public String getNome() {
	return logado.getNome();
    }

    public boolean isLogado() {
	return logado != null;
    }

    public void logout() {
	this.logado = null;
    }

}
