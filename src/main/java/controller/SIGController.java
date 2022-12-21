package controller;

import model.*;
import view.InvoiceDialog;
import view.InvoiceFrame;
import view.LineDialog;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SIGController implements ActionListener, ListSelectionListener {

    private InvoiceFrame invoiceFrame;
    private SIGHeader sigHeader ;
    private SIGItem sigItem;
    private String name ;
    private InvoiceDialog invoiceDialog ;
    private LineDialog lineDialog;

    public SIGController(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "New Invoice":
                newInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "New Line":
                newLine();
                break;
            case "Delete Line":
                deleteLine();
                break;

            case "createInvoice":
                addInvOk();
                break;

            case "cancelInvoice":
                cancelInvoice();
                break;
            case "createLine":
                createLine();
                break;
            case "cancelLine":
                cancelLine();
                break;

            case "Open File":
                //create an obk from fileoperations and return a list of onvoices then updates the to tables
                //the below line cause if the user wants to read the files after booting
                invoiceFrame.setInvoices(null);
                FileOperations fileOperations = new FileOperations(invoiceFrame);
                ArrayList<SIGHeader> inv= fileOperations.readFile();
                invoiceFrame.setInvoices(inv);
                InvoiceTable invoiceTable = new InvoiceTable(inv);
                invoiceFrame.setInvoiceTable(invoiceTable);
                invoiceFrame.getTableInvoiceHeader().setModel(invoiceTable);
                invoiceFrame.getInvoiceTable().fireTableDataChanged();

                break;

            case "Save File":
                //create an obk from fileoperations and write the data to the files
                FileOperations fileOperations1 = new FileOperations(invoiceFrame);


                fileOperations1.saveFile(invoiceFrame.getInvoices());


                break;
        }
    }

    public void valueChanged(ListSelectionEvent e) {

    }
    private void newInvoice() {
        invoiceDialog = new InvoiceDialog(invoiceFrame);
        invoiceDialog.setVisible(true);

    }
    //get the selected invoice from the tabel and delete it then updates the tabel
    private void deleteInvoice() {
        int row = invoiceFrame.getTableInvoiceHeader().getSelectedRow();
        if(row!= -1){
            invoiceFrame.getInvoices().remove(row);
            invoiceFrame.getInvoiceTable().fireTableDataChanged();

        }
    }
    //create obj dialoge of classaddLineDialoge and set it visible
    private void newLine() {
        lineDialog = new LineDialog(invoiceFrame);
        lineDialog.setVisible(true);

    }
    //get the selected invoice from the header tabel then get the selected line from the items tabel
    //then delete the selected item then updates the items and invoices header
    private void deleteLine() {
        int invoiceSelected= invoiceFrame.getTableInvoiceHeader().getSelectedRow();
        int row = invoiceFrame.getTableInvoiceLines().getSelectedRow();
        //only delete if the user select an invoice from the header tabel then a item from items tabel
        if((invoiceSelected!=-1) && (row!= -1)){
            SIGHeader invoice = invoiceFrame.getInvoices().get(invoiceSelected);
            invoice.getItems().remove(row);
            invoiceFrame.getInvoiceTable().fireTableDataChanged();
            LineTable line = new LineTable(invoice.getItems());
            invoiceFrame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();
        }
    }

//create two arrays from sigHeader and sigItem then save them in the desired file

    //get the invoice customer and date then create a new invoice from sigHeader class then update the tabel
    public void addInvOk() {
        String date= invoiceDialog.getInvoiceDate().getText();
        String customer = invoiceDialog.getCustomerName().getText();
        //get the total invoices number
        int num= invoiceFrame.getTotalInvNum();
        num++;
        SIGHeader newInvoice = new SIGHeader(num,customer,date);
        invoiceFrame.getInvoices().add(newInvoice);
        invoiceFrame.getInvoiceTable().fireTableDataChanged();
        //close the dialoge
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;

    }
    //get the invoice from by searching through its number
    public SIGHeader getInvoiceByNum(int num){
        for(SIGHeader inv: invoiceFrame.getInvoices()){
            if(num==inv.getNum()){
                return inv;
            }
        }
        return null;
    }


    //close the addInvoiceDialoge
    private void cancelInvoice() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;
    }
    /*add the item line data to the table and updates it  and add this line to its invoice
        and also updates the header table by get the selected invoice from the header table
        */
    private void createLine() {

        int invoiceSelected= invoiceFrame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceSelected!=-1){
            SIGHeader invoice = invoiceFrame.getInvoices().get(invoiceSelected);
            String item= lineDialog.getItemName().getText();
            String unitPrice = lineDialog.getUnitPrice().getText();
            String quantity = lineDialog.getQuantity().getText();
            double itemUnitPrice = Double.parseDouble(unitPrice);
            int itemQuantity = Integer.parseInt(quantity);
            SIGItem newLine = new SIGItem(item,itemQuantity,itemUnitPrice,invoice);
            invoice.getItems().add(newLine);
            LineTable line = new LineTable(invoice.getItems());
            invoiceFrame.getInvoiceTable().fireTableDataChanged();
            invoiceFrame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();

        }
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog=null;

    }
    //close the addLineDialoge
    private void cancelLine() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog=null;
    }
}
