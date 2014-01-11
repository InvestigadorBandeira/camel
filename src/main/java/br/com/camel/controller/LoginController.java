package br.com.camel.controller;

import javax.persistence.EntityManager;

import br.com.camel.dao.UsuarioDao;
import br.com.camel.infra.UsuarioLogado;
import br.com.camel.model.Usuario;
import br.com.camel.util.Mensagem;
import br.com.camel.util.SimpleCrypt;
import br.com.camel.view.LoginView;

public class LoginController {
    private final LoginView view;
    private final UsuarioDao dao;
    private final UsuarioLogado logado;
    private final Mensagem mensagem;
    private int tentativa = 0;

    public LoginController(EntityManager em) {
	logado = UsuarioLogado.instance();
	view = new LoginView(this);
	view.setVisible(true);
	dao = new UsuarioDao(em);
	mensagem = new Mensagem(view, " ::  Login  ::");
    }

    public void logar(String login, String senha) {
	Usuario usuario = new Usuario();
	usuario.setLogin(login.trim());
	usuario.setSenha(new SimpleCrypt().crypt(senha.trim()));

	Usuario cadastrado = null;
	try {
	    cadastrado = dao.carrega(usuario);
	} catch (Exception ex) {
	    mensagem.info(ex.getMessage());
	}

	if (cadastrado == null) {
	    tentativas();
	} else {
	    logado.login(usuario);
	    mensagem.info("ID: " + cadastrado.getId() + "\nNome: "
		    + cadastrado.getNome() + "\nLogin: "
		    + cadastrado.getLogin() + "\nSenha: "
		    + cadastrado.getSenha());

	    view.dispose();
	}

    }

    private void tentativas() {
	tentativa++;

	if (tentativa > 2)
	    System.exit(0);
    }
}
