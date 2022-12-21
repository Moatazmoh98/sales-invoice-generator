package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoiceTable extends AbstractTableModel {

    private String[] colums = {"Num", "Date", "Customer", "Total"};
    private ArrayList<SIGHeader> invoices;

    public InvoiceTable(ArrayList<SIGHeader> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SIGHeader invoice = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoice.getNum();

            case 1:
                return invoice.getDate();

            case 2:
                return invoice.getName();

            case 3:
                return invoice.getTotalInvoice();
        }
        return null;
    }
}
