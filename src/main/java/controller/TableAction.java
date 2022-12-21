package controller;

import model.LineTable;
import model.SIGHeader;
import model.SIGItem;
import view.InvoiceFrame;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class TableAction implements ListSelectionListener {
    private InvoiceFrame invoiceFrame;

    public TableAction(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }

    /*when select an invoice from the header tabel this method finds out which invoice has been selected
        from the header tabel and gets its item lines and update the second table the item table
        */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int invoiceIndex = invoiceFrame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceIndex!= -1){
            SIGHeader selectedRow = invoiceFrame.getInvoices().get(invoiceIndex);
            ArrayList<SIGItem> items = selectedRow.getItems();
            invoiceFrame.getLabelCustomerName().setText(selectedRow.getName());
            invoiceFrame.getLabelInvoiceNum().setText(""+selectedRow.getNum());
            invoiceFrame.getLabelInvoiceDate().setText(selectedRow.getDate());
            invoiceFrame.getLabelTostalCost().setText(""+selectedRow.getTotalInvoice());
            LineTable line = new LineTable(items);
            invoiceFrame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();

        }
    }



}
