package br.com.camel.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.camel.controller.LoginController;
import br.com.camel.util.Mensagem;
import br.com.camel.view.components.RequiredPasswordField;
import br.com.camel.view.components.RequiredTextField;

public class LoginView extends JDialog {
    private final JLabel lblLogin;
    private final JLabel lblSenha;
    private final JButton btnOk;
    private final JButton btnCancelar;
    private final RequiredTextField txtLogin;
    private final RequiredPasswordField pwdSenha;

    private final LoginController controller;
    private final Mensagem mensagem;

    public LoginView(LoginController controller) {
	getContentPane().setBackground(new Color(255, 255, 204));
	setResizable(false);
	setIconImage(Toolkit.getDefaultToolkit().getImage(
		LoginView.class.getResource("/br/com/camel/imagens/app.png")));
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setTitle("Login");
	setBounds(100, 100, 250, 140);
	getContentPane().setLayout(null);

	lblLogin = new JLabel("Login:");
	lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblLogin.setBounds(10, 11, 46, 14);
	getContentPane().add(lblLogin);

	txtLogin = new RequiredTextField(20);
	txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
	txtLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtLogin.setBounds(66, 8, 150, 22);
	getContentPane().add(txtLogin);
	txtLogin.setColumns(20);

	lblSenha = new JLabel("Senha:");
	lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblSenha.setBounds(10, 38, 46, 14);
	getContentPane().add(lblSenha);

	btnOk = new JButton("Ok");
	btnOk.addActionListener(new BtnOkAction());

	pwdSenha = new RequiredPasswordField(20);
	pwdSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
	pwdSenha.setHorizontalAlignment(SwingConstants.CENTER);
	pwdSenha.setBounds(66, 36, 150, 22);
	getContentPane().add(pwdSenha);
	btnOk.setBounds(10, 78, 100, 23);
	getContentPane().add(btnOk);

	btnCancelar = new JButton("Cancelar");
	btnCancelar.addActionListener(new BtnCancelarAction());
	btnCancelar.setBounds(116, 78, 100, 23);
	getContentPane().add(btnCancelar);

	// outras configs
	this.controller = controller;
	setLocationRelativeTo(null);
	getRootPane().setDefaultButton(btnOk);
	mensagem = new Mensagem(this, " ::  Login  ::");

    }

    private class BtnOkAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    String login = txtLogin.getText().trim();
	    String senha = String.valueOf(pwdSenha.getPassword()).trim();

	    if (login.isEmpty()) {
		mensagem.info("Digite o login.");
		txtLogin.requestFocus();
	    } else if (senha.isEmpty()) {
		mensagem.info("Digite a senha.");
		pwdSenha.requestFocus();
	    } else
		controller.logar(login, senha);
	}
    }

    private class BtnCancelarAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    System.exit(0);
	}
    }

}
