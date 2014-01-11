package br.com.camel.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import br.com.camel.view.components.DecimalFormattedField;

public class TestView extends JFrame {
    private final JFormattedTextField frmtdtxtfldValor;
    private final JFormattedTextField frmtdtxtfldDesconto;
    private JTextField txtValor;
    private final JButton btnLimpa;
    private final JButton btnSeta;
    private final JButton btnMostra;
    private final JComboBox comboBox;
    private final JScrollPane scrollPane;
    private final JTable table;

    public TestView() {
	setTitle("TestView");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 750, 460);
	getContentPane().setLayout(null);

	frmtdtxtfldValor = new JFormattedTextField();
	frmtdtxtfldValor.setHorizontalAlignment(SwingConstants.RIGHT);
	frmtdtxtfldValor.setBounds(64, 50, 138, 31);
	frmtdtxtfldValor.setDocument(new MonetarioDocument());
	getContentPane().add(frmtdtxtfldValor);

	frmtdtxtfldDesconto = new DecimalFormattedField(
		DecimalFormattedField.NUMERO);
	frmtdtxtfldDesconto.setBounds(61, 92, 141, 31);
	getContentPane().add(frmtdtxtfldDesconto);

	txtValor = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	// txtValor.setDocument(new MonetarioDocument());
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtValor.setBounds(64, 134, 130, 25);
	// txtValor.setColumns(10);
	getContentPane().add(txtValor);

	btnLimpa = new JButton("Limpa");
	btnLimpa.addActionListener(new BtnLimpaAction());
	btnLimpa.setBounds(64, 191, 89, 23);
	getContentPane().add(btnLimpa);

	btnSeta = new JButton("Seta");
	btnSeta.addActionListener(new BtnSetaAction());
	btnSeta.setBounds(163, 191, 89, 23);
	getContentPane().add(btnSeta);

	btnMostra = new JButton("Mostra");
	btnMostra.addActionListener(new BtnMostraAction());
	btnMostra.setBounds(262, 191, 89, 23);
	getContentPane().add(btnMostra);

	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] { "VISA",
		"MASTERCARD" }));
	comboBox.setBounds(302, 55, 102, 20);
	getContentPane().add(comboBox);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(249, 250, 422, 129);
	getContentPane().add(scrollPane);

	table = new JTable();
	table.setModel(new DefaultTableModel(new Object[][] {
		{ null, null, null, null, null },
		{ null, null, null, null, null },
		{ null, null, null, null, null },
		{ null, null, null, null, null }, }, new String[] {
		"New column", "New column", "New column", "New column",
		"New column" }));
	scrollPane.setViewportView(table);

	// others
	setLocationRelativeTo(null);
	setVisible(true);
	// limpaValor();
    }

    private void montaValor() {
	txtValor = new JTextField();
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setDocument(new MonetarioDocument());
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtValor.setBounds(64, 134, 130, 25);
	txtValor.setColumns(10);
	getContentPane().add(txtValor);

	getContentPane().repaint();
    }

    private void limpaValor() {

	txtValor.setText(null);
	try {
	    txtValor.getDocument()
		    .remove(0, txtValor.getDocument().getLength());
	    // txtValor.getDocument().insertString(0, null, null);
	} catch (BadLocationException e1) {
	    e1.printStackTrace();
	}
    }

    private class MonetarioDocument extends PlainDocument {
	public static final int NUMERO_DIGITOS_MAXIMO = 14;

	@Override
	public void insertString(int offs, String str, AttributeSet a)
		throws BadLocationException {

	    String texto = getText(0, getLength());

	    for (int i = 0; i < str.length(); i++) {
		char c = str.charAt(i);
		if (!Character.isDigit(c)) {
		    return;
		}
	    }

	    if (texto.length() < this.NUMERO_DIGITOS_MAXIMO) {
		super.remove(0, getLength());
		texto = texto.replace(".", "").replace(",", "");
		StringBuffer s = new StringBuffer(texto + str);

		if (s.length() > 0 && s.charAt(0) == '0') {
		    s.deleteCharAt(0);
		}

		if (s.length() < 3) {
		    if (s.length() < 1) {
			s.insert(0, "000");
		    } else if (s.length() < 2) {
			s.insert(0, "00");
		    } else {
			s.insert(0, "0");
		    }
		}

		s.insert(s.length() - 2, ",");

		if (s.length() > 6) {
		    s.insert(s.length() - 6, ".");
		}

		if (s.length() > 10) {
		    s.insert(s.length() - 10, ".");
		}

		super.insertString(0, s.toString(), a);
	    }
	}

	@Override
	public void remove(int offset, int length) throws BadLocationException {
	    super.remove(offset, length);
	    String texto = getText(0, getLength());
	    texto = texto.replace(",", "");
	    texto = texto.replace(".", "");
	    super.remove(0, getLength());
	    insertString(0, texto, null);
	}

    }

    private class BtnLimpaAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    // txtValor.setText(null);
	    limpaValor();
	}
    }

    private class BtnSetaAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    try {
		txtValor.getDocument().insertString(0, "10000000", null);
	    } catch (BadLocationException e1) {
		e1.printStackTrace();
	    }
	}
    }

    private class BtnMostraAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    JOptionPane.showMessageDialog(getRootPane(), txtValor.getText());
	}
    }
}
