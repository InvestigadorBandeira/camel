package br.com.camel.view.components;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class RequiredTextField extends JTextField {

    public RequiredTextField(int fixedLenght) {
	super();
	addFocusListener(new ThisFocus());
	setDocument(new FixedLengthDocument(fixedLenght));
    }

    private class ThisFocus extends FocusAdapter {
	@Override
	public void focusLost(FocusEvent e) {
	    if (getText().trim().isEmpty())
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.RED,
			Color.RED));
	    else
		setBorder(UIManager.getBorder("TextField.border"));
	}
    }
}
