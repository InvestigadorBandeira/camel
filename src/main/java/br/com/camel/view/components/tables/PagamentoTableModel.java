package br.com.camel.view.components.tables;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.camel.model.Pagamento;

public class PagamentoTableModel extends AbstractTableModel {

    private final List<Pagamento> pagamentos;

    // Array com os nomes das colunas.
    private final String[] colunas = new String[] { "Valor",
	    "Autoriza\u00E7\u00E3o", "NSU / CV / DOC", "Data Pagamento",
	    "Cart\u00E3o" };

    // Constantes representando o índice das colunas
    private static final int VALOR = 0;
    private static final int AUTORIZACAO = 1;
    private static final int NSU_CV_DOC = 2;
    private static final int DATA_PAGAMENTO = 3;
    private static final int CARTAO = 4;

    public PagamentoTableModel() {
	pagamentos = Collections.EMPTY_LIST;
    }

    public PagamentoTableModel(List<Pagamento> pagamentos) {
	this.pagamentos = pagamentos;
    }

    @Override
    public int getColumnCount() {
	return colunas.length;
    }

    @Override
    public int getRowCount() {
	return pagamentos.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
	return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Pagamento pagamento = pagamentos.get(rowIndex);

	switch (columnIndex) {
	case VALOR:
	    return pagamento.getValor();

	case AUTORIZACAO:
	    return pagamento.getAutorizacao();

	case NSU_CV_DOC:
	    return pagamento.getNsuCvDoc();

	case DATA_PAGAMENTO:
	    return pagamento.getDataPagamento();

	case CARTAO:
	    return pagamento.getCartao();

	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (columnIndex) {
	case VALOR:
	    return BigDecimal.class;

	case AUTORIZACAO:
	    return String.class;

	case NSU_CV_DOC:
	    return String.class;

	case DATA_PAGAMENTO:
	    return Calendar.class;

	case CARTAO:
	    return String.class;

	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }

    public Pagamento getPagamento(int indiceLinha) {
	return pagamentos.get(indiceLinha);
    }

    public void addPagamento(Pagamento pagamento) {
	pagamentos.add(pagamento);

	// Pega a quantidade de registros e subtrai 1 para
	// achar o último índice. A subtração é necessária
	// porque os índices começam em zero.
	int ultimoIndice = getRowCount() - 1;

	// Notifica a mudança.
	fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removePagamento(int indiceLinha) {
	pagamentos.remove(indiceLinha);

	// Notifica a mudança.
	fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    // Remove todos os registros.
    public void limpar() {
	// Remove todos os elementos da lista de sócios.
	pagamentos.clear();

	// Notifica a mudança.
	fireTableDataChanged();
    }

}
