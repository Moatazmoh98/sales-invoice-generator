package view;

import controller.SIGController;
import controller.TableAction;
import model.FileOperations;
import model.InvoiceTable;
import model.SIGHeader;
import model.SIGItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class InvoiceFrame extends JFrame {
    private JTable tableInvoiceHeader ;
    private JMenuBar JMenuBar;
    private JMenu MenuBar;
    private JMenuItem OpenMenuItem;
    private JMenuItem SaveMenuItem;
    private JLabel customerName;
    private JLabel invoiceDate;
    private JLabel invoiceNum;
    private JLabel invoiceTotalCost;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JLabel labelCustomerName;
    private JLabel labelInvoiceDate;
    private JLabel labelInvoiceNum;
    private JLabel labelTostalCost;
    private JTable tableInvoiceLines;
    private JButton btnDeleteInvoice;
    private JButton btnDeleteLine;
    private JButton btnNewInvoice;
    private JButton btnNewLine;

    private SIGController controller = new SIGController(this);
    private TableAction tableAction = new TableAction(this);
    public SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    private ArrayList<SIGHeader> invoices;
    private ArrayList<SIGItem> lines;

    private InvoiceTable invoiceTable ;


    public InvoiceFrame(){
        initComponents();
    }


    public static void main( String[] args ){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InvoiceFrame invoiceFrame= new InvoiceFrame();

                invoiceFrame.setVisible(true);
                FileOperations fileOperations = new FileOperations(invoiceFrame);
                ArrayList<SIGHeader> inv= fileOperations.readFile();
                invoiceFrame.setInvoices(inv);
                InvoiceTable invoiceTable = new InvoiceTable(inv);
                invoiceFrame.setInvoiceTable(invoiceTable);
                invoiceFrame.getTableInvoiceHeader().setModel(invoiceTable);
                invoiceFrame.getInvoiceTable().fireTableDataChanged();

            }
        });
    }
    public int getTotalInvNum(){
        int num=0;
        for(SIGHeader invoice: invoices){
            if(invoice.getNum()>num){
                num= invoice.getNum();
            }
        }

        return num;
    }



    private void OpenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenMenuItemActionPerformed
        // TODO add your handling code here:
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        tableInvoiceHeader = new JTable();
        tableInvoiceHeader.getSelectionModel().addListSelectionListener(tableAction);
        jScrollPane2 = new  JScrollPane();
        tableInvoiceLines = new  JTable();
        invoiceNum = new  JLabel();
        invoiceDate = new JLabel();
        customerName = new JLabel();
        invoiceTotalCost = new JLabel();
        labelCustomerName = new JLabel();
        labelTostalCost = new JLabel();
        labelInvoiceDate = new JLabel();
        labelInvoiceNum = new JLabel();
        btnNewInvoice = new JButton();
        btnNewInvoice.addActionListener(controller);
        btnDeleteInvoice = new JButton();
        btnDeleteInvoice.addActionListener(controller);
        btnNewLine = new JButton();
        btnNewLine.addActionListener(controller);
        btnDeleteLine = new JButton();
        btnDeleteLine.addActionListener(controller);
        JMenuBar = new JMenuBar();
        MenuBar = new JMenu();
        OpenMenuItem = new JMenuItem();
        OpenMenuItem.addActionListener(controller);
        SaveMenuItem = new JMenuItem();
        SaveMenuItem.addActionListener(controller);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tableInvoiceHeader.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
        ));
        tableInvoiceHeader.setShowGrid(true);
        jScrollPane1.setViewportView(tableInvoiceHeader);

        tableInvoiceLines.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
        ));
        tableInvoiceLines.setShowGrid(true);
        jScrollPane2.setViewportView(tableInvoiceLines);
        tableInvoiceLines.getAccessibleContext().setAccessibleName("");

        invoiceNum.setText("Invoice Num:");

        invoiceDate.setText("Invoice Date:");

        customerName.setText("Customer Name:");

        invoiceTotalCost.setText("Invoice Total Cost:");

        labelCustomerName.setText("-");

        labelTostalCost.setText("-");

        labelInvoiceDate.setText("-");

        labelInvoiceNum.setText("-");

        btnNewInvoice.setText("New Invoice");

        btnDeleteInvoice.setText("Delete Invoice");

        btnNewLine.setText("New Line");

        btnDeleteLine.setText("Delete Line");

        MenuBar.setText("File");

        OpenMenuItem.setText("Open File");
        OpenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenMenuItemActionPerformed(evt);
            }
        });
        MenuBar.add(OpenMenuItem);

        SaveMenuItem.setText("Save File");
        MenuBar.add(SaveMenuItem);

        JMenuBar.add(MenuBar);

        setJMenuBar(JMenuBar);
        JMenuBar.getAccessibleContext().setAccessibleName("");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addComponent(invoiceNum))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(invoiceTotalCost)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(labelTostalCost))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(customerName)
                                                                                        .addComponent(invoiceDate))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(labelInvoiceDate)
                                                                                        .addComponent(labelCustomerName)
                                                                                        .addComponent(labelInvoiceNum)))))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(btnNewInvoice)
                                                .addGap(37, 37, 37)
                                                .addComponent(btnDeleteInvoice)
                                                .addGap(68, 68, 68)
                                                .addComponent(btnNewLine)
                                                .addGap(118, 118, 118)
                                                .addComponent(btnDeleteLine)))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(invoiceNum)
                                                        .addComponent(labelInvoiceNum))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(invoiceDate)
                                                        .addComponent(labelInvoiceDate))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(customerName)
                                                        .addComponent(labelCustomerName))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(invoiceTotalCost)
                                                        .addComponent(labelTostalCost))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNewInvoice)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnDeleteInvoice)
                                                .addComponent(btnNewLine)
                                                .addComponent(btnDeleteLine)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        invoiceDate.getAccessibleContext().setAccessibleName("jLabel2");

        pack();
    }


    public JTable getTableInvoiceHeader() {
        return tableInvoiceHeader;
    }

    public void setTableInvoiceHeader(JTable tableInvoiceHeader) {
        this.tableInvoiceHeader = tableInvoiceHeader;
    }

    public JLabel getCustomerName() {
        return customerName;
    }

    public JLabel getInvoiceDate() {
        return invoiceDate;
    }

    public JLabel getLabelCustomerName() {
        return labelCustomerName;
    }

    public JLabel getLabelInvoiceDate() {
        return labelInvoiceDate;
    }

    public JLabel getLabelInvoiceNum() {
        return labelInvoiceNum;
    }

    public JLabel getLabelTostalCost() {
        return labelTostalCost;
    }

    public JTable getTableInvoiceLines() {
        return tableInvoiceLines;
    }

    public SIGController getController() {
        return controller;
    }

    public ArrayList<SIGHeader> getInvoices() {
        if(invoices==null){
            invoices= new ArrayList<>();
        }
        return invoices;
    }

    public void setInvoices(ArrayList<SIGHeader> invoices) {
        this.invoices = invoices;
    }

    @Override
    public javax.swing.JMenuBar getJMenuBar() {
        return JMenuBar;
    }

    @Override
    public void setJMenuBar(javax.swing.JMenuBar JMenuBar) {
        this.JMenuBar = JMenuBar;
    }

    public void setMenuBar(JMenu menuBar) {
        MenuBar = menuBar;
    }

    public JMenuItem getOpenMenuItem() {
        return OpenMenuItem;
    }

    public void setOpenMenuItem(JMenuItem openMenuItem) {
        OpenMenuItem = openMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return SaveMenuItem;
    }

    public void setSaveMenuItem(JMenuItem saveMenuItem) {
        SaveMenuItem = saveMenuItem;
    }

    public void setCustomerName(JLabel customerName) {
        this.customerName = customerName;
    }

    public void setInvoiceDate(JLabel invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public JLabel getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(JLabel invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public JLabel getInvoiceTotalCost() {
        return invoiceTotalCost;
    }

    public void setInvoiceTotalCost(JLabel invoiceTotalCost) {
        this.invoiceTotalCost = invoiceTotalCost;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public void setLabelCustomerName(JLabel labelCustomerName) {
        this.labelCustomerName = labelCustomerName;
    }

    public void setLabelInvoiceDate(JLabel labelInvoiceDate) {
        this.labelInvoiceDate = labelInvoiceDate;
    }

    public void setLabelInvoiceNum(JLabel labelInvoiceNum) {
        this.labelInvoiceNum = labelInvoiceNum;
    }

    public void setLabelTostalCost(JLabel labelTostalCost) {
        this.labelTostalCost = labelTostalCost;
    }

    public void setTableInvoiceLines(JTable tableInvoiceLines) {
        this.tableInvoiceLines = tableInvoiceLines;
    }

    public JButton getBtnDeleteInvoice() {
        return btnDeleteInvoice;
    }

    public void setBtnDeleteInvoice(JButton btnDeleteInvoice) {
        this.btnDeleteInvoice = btnDeleteInvoice;
    }

    public JButton getBtnDeleteLine() {
        return btnDeleteLine;
    }

    public void setBtnDeleteLine(JButton btnDeleteLine) {
        this.btnDeleteLine = btnDeleteLine;
    }

    public JButton getBtnNewInvoice() {
        return btnNewInvoice;
    }

    public void setBtnNewInvoice(JButton btnNewInvoice) {
        this.btnNewInvoice = btnNewInvoice;
    }

    public JButton getBtnNewLine() {
        return btnNewLine;
    }

    public void setBtnNewLine(JButton btnNewLine) {
        this.btnNewLine = btnNewLine;
    }

    public void setController(SIGController controller) {
        this.controller = controller;
    }

    public TableAction getTableAction() {
        return tableAction;
    }

    public void setTableAction(TableAction tableAction) {
        this.tableAction = tableAction;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public ArrayList<SIGItem> getLines() {
        return lines;
    }

    public void setLines(ArrayList<SIGItem> lines) {
        this.lines = lines;
    }

    public InvoiceTable getInvoiceTable() {
        return invoiceTable;
    }

    public void setInvoiceTable(InvoiceTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }
}
