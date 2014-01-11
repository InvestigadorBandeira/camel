package br.com.camel.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Mensagem {
    private final Component component;
    private final String titulo;

    public Mensagem(Component component, String titulo) {
	this.component = component;
	this.titulo = titulo;
    }

    public void info(String mensagem) {
	JOptionPane.showMessageDialog(component, mensagem, titulo,
		JOptionPane.INFORMATION_MESSAGE);
    }
}
