package view;

import javax.swing.*;
import java.awt.*;

public class InvoiceDialog extends JDialog {
    private JTextField customerName;
    private JTextField invoiceDate;
    private JLabel custNameLabel;
    private JLabel dateLabel;
    private JButton ok;
    private JButton cancel;

    public InvoiceDialog(InvoiceFrame invoiceFrame) {
        custNameLabel= new JLabel("Customer Name:");
        customerName = new JTextField(30);
        dateLabel = new JLabel("Date:");
        invoiceDate = new JTextField(30);
        ok= new JButton("OK");
        cancel = new JButton("Cancel");
        ok.setActionCommand("createInvoice");
        cancel.setActionCommand("cancelInvoice");
        ok.addActionListener(invoiceFrame.getController());
        cancel.addActionListener(invoiceFrame.getController());
        setLayout(new GridLayout(4, 4));
        add(custNameLabel);
        add(customerName);
        add(dateLabel);
        add(invoiceDate);
        add(ok);
        add(cancel);


        pack();


    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JTextField getInvoiceDate() {
        return invoiceDate;
    }
}
