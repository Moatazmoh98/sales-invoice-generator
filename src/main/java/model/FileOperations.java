package model;

import view.InvoiceFrame;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileOperations {
    private InvoiceFrame invoiceFrame;
    //headerFile name


    public FileOperations(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }


    public ArrayList<SIGHeader> readFile() {
        String headerPath;
        //items file name
        String itemPath;
        File headerFile;
        File itemFile;
        List<String> headerLines = null;
        List<String> itemLines = null;
        int result;
        ArrayList<SIGHeader> invArray = new ArrayList<>();

        JOptionPane.showMessageDialog(invoiceFrame, "Please insert Headers File then Lines File");

        JFileChooser file = new JFileChooser();
        do {//do not close till the user chooses the right file
            result = file.showOpenDialog(invoiceFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                headerFile = file.getSelectedFile();
                if (headerFile.getName().contains(".csv")) {
                    headerPath = headerFile.getAbsolutePath();
                    break;
                } else {//display an error message
                    System.out.println("Wrong Headers File Format");
                    JOptionPane.showMessageDialog(invoiceFrame, "Wrong Headers File Format please insert the correct file again");
                }
            }
        } while (true);
        do {//do not close till the user chooses the right file
            result = file.showOpenDialog(invoiceFrame);

            if (result == JFileChooser.APPROVE_OPTION) {
                itemFile = file.getSelectedFile();
                if (itemFile.getName().contains(".csv")) {
                    itemPath = itemFile.getAbsolutePath();
                    break;
                } else {
                    //display an error message
                    System.out.println("Wrong Items File Format");
                    JOptionPane.showMessageDialog(invoiceFrame, "Wrong Items File Format");
                }

            }
        } while (true);

        System.out.println(headerPath);
        System.out.println(itemPath);

        //ready each line in each file as string then save them in array of strings

        try {
            headerLines = Files.lines(Paths.get(headerPath)).collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Wrong Headers File Path");
            JOptionPane.showMessageDialog(invoiceFrame, "Wrong Headers File Path");
        }


        try {
            itemLines = Files.lines(Paths.get(itemPath)).collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Wrong Items File Path");
            JOptionPane.showMessageDialog(invoiceFrame, "Wrong Items File Patth");
        }


        if (headerLines != null && itemLines != null) {
            for (String headerLine : headerLines) {
                    /*split each element in header arrays that has the invoices from header file
                    then dipose ',' and save each string in another array of strings
                    */
                String[] arr = headerLine.split(",");
                String numToString = arr[0];
                String date = arr[1];
                String customerName = arr[2];
                int num = Integer.parseInt(numToString);
                //create new invoice and add it in  invArray
                SIGHeader invoice = new SIGHeader(num, customerName, date);
                invArray.add(invoice);
                invoiceFrame.getInvoices().add(invoice);

            }
                /*split each element in itemLine arrays that has the invoices from items file
                    then dipose ',' and save each string in another array of strings
                    */
            for (String itemLine : itemLines) {
                String[] arr = itemLine.split(",");
                int num = Integer.parseInt(arr[0]);
                String name1 = arr[1];
                double unitPrice = Double.parseDouble(arr[2]);
                int quantity = Integer.parseInt(arr[3]);
                //create new line and add it inside its invoice
                SIGHeader invoice = getInvoiceByNum(num);
                SIGItem line = new SIGItem(name1, quantity, unitPrice, invoice);
                invoice.getItems().add(line);

            }
        }
        //update the two tables

        return invArray;
        //}


    }


    public SIGHeader getInvoiceByNum(int num) {
        for (SIGHeader inv : invoiceFrame.getInvoices()) {
            if (inv.getNum() == num) {
                return inv;
            }
        }
        return null;
    }

    public void saveFile(ArrayList<SIGHeader> headers) {
        /*create new array of sigHeader to get the current invoices
        create two strings to stroe in them the final data as string data
        */
        //Header file name

        String invoices = "";
        String items = "";
        File headerFile;
        File lineFile;
        int result;
        /*read each invoice from the header table and save it in a new line inside invoices string
        then read each item line inside the invoice and save it i a new line inside items string
        */
        for (SIGHeader header : headers) {
            String headerLines = header.getInvoicesFromTabel();
            invoices = invoices + headerLines;
            invoices = invoices + "\n";

            for (SIGItem item : header.getItems()) {
                String itemFile = item.getItemsFromTabel();
                items = items + itemFile;
                items = items + "\n";
            }
        }
        //open two file chooser to save these two strings inside the desired files if they are not found

        JOptionPane.showMessageDialog(invoiceFrame, "Kindly choose the Headers file then Lines file");
        JFileChooser file = new JFileChooser();
        do {//donot break till the user choose the right format
            result = file.showSaveDialog(invoiceFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                headerFile = file.getSelectedFile();
                if (headerFile.getName().contains(".csv")) {//write only in the correct file
                    FileWriter headFileWriter = null;
                    try {
                        headFileWriter = new FileWriter(headerFile);
                        headFileWriter.write(invoices);
                        headFileWriter.flush();
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            headFileWriter.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    System.out.println("Wrong Headers File Format");
                    JOptionPane.showMessageDialog(invoiceFrame, "Wrong Headers File Format");
                }
            }
        } while (true);


        do {//donot break till the user choose the right format
            result = file.showSaveDialog(invoiceFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                lineFile = file.getSelectedFile();
                if (lineFile.getName().contains(".csv")) {//write only in the correct file
                    FileWriter lineFileWriter = null;
                    try {
                        lineFileWriter = new FileWriter(lineFile);
                        lineFileWriter.write(items);
                        lineFileWriter.flush();
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            lineFileWriter.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                System.out.println("Wrong Lines File Format");
                JOptionPane.showMessageDialog(invoiceFrame, "Wrong Lines File Format");
            }
        } while (true);


    }
}
