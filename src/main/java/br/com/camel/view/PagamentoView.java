package br.com.camel.view;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import br.com.camel.model.Cartao;
import br.com.camel.model.Pagamento;
import br.com.camel.view.components.DecimalFormattedField;
import br.com.camel.view.components.NumberDocument;
import br.com.camel.view.components.tables.PagamentoTableModel;

import com.toedter.calendar.JDateChooser;

public class PagamentoView extends JDialog {
    private final JLabel lblValorPagar;
    private final DecimalFormattedField txtValorPagar;
    private final JPanel pnFundo;
    private final DecimalFormattedField txtValor;
    private final JTextField txtAutorizacao;
    private final JTextField txtNsuCvDoc;
    private final JDateChooser txtDataPagamento;
    private final JComboBox cbCartao;
    private final JLabel lblValor;
    private final JLabel lblAutorizacao;
    private final JLabel lblNsuCvDoc;
    private final JLabel lblDataPagamento;
    private final JLabel lblCartao;
    private final JScrollPane scrollPane;
    private final JTable tblPagamentos;

    private final List<Pagamento> pagamentos;

    public PagamentoView(List<Pagamento> pagamentos) {
	setModal(true);
	setTitle("Pagamentos");
	setBounds(100, 100, 800, 402);
	getContentPane().setLayout(null);

	lblValorPagar = new JLabel("Valor a Pagar");
	lblValorPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblValorPagar.setBounds(10, 11, 120, 25);
	getContentPane().add(lblValorPagar);

	txtValorPagar = new DecimalFormattedField(DecimalFormattedField.REAL);
	txtValorPagar.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValorPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtValorPagar.setFocusable(false);
	txtValorPagar.setColumns(10);
	txtValorPagar.setBounds(140, 11, 130, 25);
	getContentPane().add(txtValorPagar);

	pnFundo = new JPanel();
	pnFundo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	pnFundo.setBounds(10, 47, 764, 247);
	getContentPane().add(pnFundo);
	pnFundo.setLayout(null);

	lblValor = new JLabel("Valor");
	lblValor.setHorizontalAlignment(SwingConstants.CENTER);
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblValor.setBounds(10, 11, 130, 25);
	pnFundo.add(lblValor);

	txtValor = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtValor.setColumns(10);
	txtValor.setBounds(10, 37, 130, 25);
	pnFundo.add(txtValor);

	lblAutorizacao = new JLabel("Autoriza\u00E7\u00E3o");
	lblAutorizacao.setHorizontalAlignment(SwingConstants.CENTER);
	lblAutorizacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblAutorizacao.setBounds(150, 11, 120, 25);
	pnFundo.add(lblAutorizacao);

	txtAutorizacao = new JTextField();
	txtAutorizacao.setHorizontalAlignment(SwingConstants.CENTER);
	txtAutorizacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtAutorizacao.setBounds(150, 37, 120, 25);
	txtAutorizacao.setDocument(new NumberDocument(15));
	pnFundo.add(txtAutorizacao);

	lblNsuCvDoc = new JLabel("NSU / CV / DOC");
	lblNsuCvDoc.setHorizontalAlignment(SwingConstants.CENTER);
	lblNsuCvDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblNsuCvDoc.setBounds(280, 11, 120, 25);
	pnFundo.add(lblNsuCvDoc);

	txtNsuCvDoc = new JTextField();
	txtNsuCvDoc.setHorizontalAlignment(SwingConstants.CENTER);
	txtNsuCvDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtNsuCvDoc.setBounds(280, 37, 120, 25);
	txtNsuCvDoc.setDocument(new NumberDocument(15));
	pnFundo.add(txtNsuCvDoc);

	lblDataPagamento = new JLabel("Data Pagamento");
	lblDataPagamento.setHorizontalAlignment(SwingConstants.CENTER);
	lblDataPagamento.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblDataPagamento.setBounds(410, 11, 130, 25);
	pnFundo.add(lblDataPagamento);

	txtDataPagamento = new JDateChooser();
	txtDataPagamento.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtDataPagamento.setBounds(410, 37, 130, 25);
	pnFundo.add(txtDataPagamento);

	lblCartao = new JLabel("Cart\u00E3o");
	lblCartao.setHorizontalAlignment(SwingConstants.CENTER);
	lblCartao.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblCartao.setBounds(550, 11, 150, 25);
	pnFundo.add(lblCartao);

	cbCartao = new JComboBox();
	cbCartao.setBounds(550, 37, 150, 25);
	pnFundo.add(cbCartao);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 74, 690, 123);
	pnFundo.add(scrollPane);

	tblPagamentos = new JTable();
	scrollPane.setViewportView(tblPagamentos);

	// others
	this.setLocationRelativeTo(null);
	this.pagamentos = pagamentos;

	populaTabelaPagamentos();
    }

    private void populaTabelaPagamentos() {
	PagamentoTableModel tableModel = new PagamentoTableModel(pagamentos);
	tblPagamentos.setModel(tableModel);
    }

    public void preencheComboCartao(List<Cartao> cartoes) {
	cbCartao.setModel(new DefaultComboBoxModel<>(cartoes.toArray()));
    }
}
