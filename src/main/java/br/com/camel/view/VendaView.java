package br.com.camel.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import br.com.camel.controller.VendaController;
import br.com.camel.model.Pagamento;
import br.com.camel.util.Mensagem;
import br.com.camel.view.components.DecimalFormattedField;
import br.com.camel.view.components.FixedLengthDocument;

import com.toedter.calendar.JDateChooser;

public class VendaView extends JFrame {

    private final VendaController controller;
    private final Mensagem mensagem;
    private final JLabel lblDataVenda;
    private final JDateChooser txtDataVenda;
    private final JLabel lblDescricao;
    private final JTextField txtDescricao;
    private final JLabel lblValor;
    private final JLabel lblDesconto;
    private final JLabel lblValorAPagar;
    private final JTextField txtValor;
    private final JTextField txtDesconto;
    private final JTextField txtValorPagar;
    private final JButton btnFinalizarVenda;
    private final List<Pagamento> pagamentos;

    public VendaView(VendaController controller) {
	setTitle("Lan\u00E7amento de Vendas");
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 729, 373);
	getContentPane().setLayout(null);

	lblDataVenda = new JLabel("Data da Venda");
	lblDataVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblDataVenda.setBounds(10, 30, 120, 25);
	getContentPane().add(lblDataVenda);

	txtDataVenda = new JDateChooser();
	txtDataVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtDataVenda.setBounds(140, 30, 130, 25);
	txtDataVenda.setDate(Calendar.getInstance().getTime());
	getContentPane().add(txtDataVenda);

	lblDescricao = new JLabel("Descri\u00E7\u00E3o");
	lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblDescricao.setBounds(10, 66, 120, 25);
	getContentPane().add(lblDescricao);

	txtDescricao = new JTextField();
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtDescricao.setBounds(140, 66, 573, 25);
	txtDescricao.setDocument(new FixedLengthDocument(40));
	getContentPane().add(txtDescricao);

	lblValor = new JLabel("Valor");
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblValor.setBounds(10, 102, 120, 25);
	getContentPane().add(lblValor);

	txtValor = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValor.addFocusListener(new TxtValorFocus());
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtValor.setBounds(140, 102, 130, 25);
	getContentPane().add(txtValor);
	txtValor.setColumns(10);

	lblDesconto = new JLabel("Desconto");
	lblDesconto.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblDesconto.setBounds(10, 138, 120, 25);
	getContentPane().add(lblDesconto);

	txtDesconto = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtDesconto.addFocusListener(new TxtDescontoFocus());
	txtDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
	txtDesconto.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtDesconto.setColumns(10);
	txtDesconto.setBounds(140, 138, 130, 25);
	getContentPane().add(txtDesconto);

	lblValorAPagar = new JLabel("Valor a Pagar");
	lblValorAPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblValorAPagar.setBounds(10, 174, 120, 25);
	getContentPane().add(lblValorAPagar);

	txtValorPagar = new DecimalFormattedField(DecimalFormattedField.REAL);
	txtValorPagar.setFocusable(false);
	txtValorPagar.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValorPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtValorPagar.setColumns(10);
	txtValorPagar.setBounds(140, 174, 130, 25);
	getContentPane().add(txtValorPagar);

	btnFinalizarVenda = new JButton("Finalizar Venda [F5]");
	btnFinalizarVenda.addActionListener(new BtnFinalizarVendaAction());
	btnFinalizarVenda.setBounds(10, 238, 160, 25);
	getContentPane().add(btnFinalizarVenda);

	// others
	setLocationRelativeTo(null);
	this.controller = controller;
	this.pagamentos = new ArrayList<>();
	mensagem = new Mensagem(this, " :: Lançamento de Vendas ::");

	teclas();
    }

    private void teclas() {
	KeyStroke stroke = KeyStroke.getKeyStroke("F5");
	Action actionF5 = new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		new BtnFinalizarVendaAction().actionPerformed(e);
	    }
	};

	InputMap inputMap = rootPane
		.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

	inputMap.put(stroke, "FINALIZAR");

	rootPane.getActionMap().put("FINALIZAR", actionF5);
    }

    private boolean calculaValorPagar() {
	BigDecimal resultado = new BigDecimal("0");

	String valor = txtValor.getText().trim().replace("-", "")
		.replace(".", "").replace(",", ".");

	txtValor.setText(valor);

	String desconto = txtDesconto.getText().trim().replace("-", "")
		.replace(".", "").replace(",", ".");

	txtDesconto.setText(desconto);

	if (Double.valueOf(valor) < Double.valueOf(desconto)
		|| Double.valueOf(valor) <= 0.0) {
	    txtValorPagar.setText("0,00");
	    return false;
	}

	resultado = resultado.add(new BigDecimal(valor));
	resultado = resultado.subtract(new BigDecimal(desconto));

	txtValorPagar.setText(resultado.toString());

	return true;
    }

    public boolean finalizaVenda() {
	if (txtDataVenda.getDate() == null)
	    return false;

	if (txtDescricao.getText().isEmpty())
	    return false;

	if (!calculaValorPagar())
	    return false;

	return true;
    }

    private class TxtValorFocus extends FocusAdapter {
	@Override
	public void focusLost(FocusEvent e) {
	    calculaValorPagar();
	}

    }

    private class TxtDescontoFocus extends FocusAdapter {
	@Override
	public void focusLost(FocusEvent e) {
	    calculaValorPagar();
	}
    }

    private class BtnFinalizarVendaAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    controller.lancaPagamentos(pagamentos);
	}
    }
}
