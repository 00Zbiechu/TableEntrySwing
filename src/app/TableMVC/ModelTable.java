package app.TableMVC;

import app.Main;
import app.Window;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class ModelTable extends AbstractTableModel {

    private byte rowNumber = 5;
    private byte colNumber = 5;
    private int tableData[][] = new int[rowNumber][colNumber];
    private String[] labelForTableColumns = {"1","2","3","4","5"};

    public ModelTable(){


    }


    @Override
    public int getRowCount() {
        return rowNumber;
    }

    @Override
    public int getColumnCount() {
        return colNumber;
    }

    @Override
    public Object getValueAt(int x, int y) {
        return tableData[x][y];
    }

    public String getColumnName(int col) {
        return labelForTableColumns[col];
    }

    public String[] getColumnNames() {
        return labelForTableColumns;
    }

    public int[][] getDataTable(){return tableData;}


    //Wypełnianie tabeli wartościami
    public void insertDataIntoTableData(String data, int x, int y, Component parent){

        int value=0;
        try {
            value =  Integer.parseInt(data);
            Window.statusBar.setStatusAndValueOfApplication("Wprowadzono wartość",String.valueOf(value));
            Main.logger.info("Wprowadzono wartość");
            Main.myLogger.info("Wprowadzono wartość");

            //Wprowadzenie zmian do tabeliData
            tableData[--x][--y]=value;

        }catch (Exception e){
            Window.statusBar.setStatusAndValueOfApplication("Podaną złą wartość","Błąd");
            JOptionPane.showMessageDialog(parent,"Wprowadzono niewłaściwą wartość!","Zła wartość",JOptionPane.WARNING_MESSAGE);
            Main.logger.warn("Wprowadzono niewłaściwą wartość!");
            Main.myLogger.warn("Wprowadzono niewłaściwą wartość!");
        }

        fireTableDataChanged();

    }


    public void fillTableZeros() {


        for (int i = 0; i < rowNumber; i++) {

            for (int j = 0; j < colNumber; j++) {

                tableData[i][j] = 0;

            }
        }
        fireTableDataChanged();
        Window.statusBar.setStatusAndValueOfApplication("Zerowanie","True");
        Main.logger.info("Zerowanie tablicy");
        Main.myLogger.info("Zerowanie tablicy");
    }


    public static void printTable(JTable table) {

        Window.statusBar.setStatusAndValueOfApplication("Drukowanie", "True");
        Main.logger.info("Drukowanie tablicy");
        Main.myLogger.info("Drukowanie tablicy");

        PrinterJob pj = PrinterJob.getPrinterJob();

        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (PrinterException exc) {

            }
        }
    }



    public void saveFile(Component parent) throws IOException {

        Window.statusBar.setStatusAndValueOfApplication("Zapis do pliku","Tablicy");
        Main.logger.info("Zapis do pliku tablicy");
        Main.myLogger.info("Zapis do pliku tablicy");

        //Stworzenie obiektu do obsługi okna wyboru pliku
        JFileChooser fileChooser = new JFileChooser();

        //Wybranie katalogu domyślnego
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        //Otwarcie okna wyboru-zwracającego wartość //CANCEL(1), APPROVE(0), ERROR(-1)
        int result = fileChooser.showOpenDialog(parent);

        //Jeśli wybrano plik
        FileWriter fileWriter = null; //obiekt służący do pisania w pliku
        if (result == JFileChooser.APPROVE_OPTION) {

            //Plik wybrany
            File selectedFile = fileChooser.getSelectedFile();

            //Plik, w którym będziemy pisać
            fileWriter = new FileWriter(selectedFile);

            //Zrób jej kopie w pliku :)
            //Nagłówek
            fileWriter.append("Tablica\n");

            //Tabela
            for (int i = 0; i < 5; i++) {

                fileWriter.append("\n");

                for (int j = 0; j < 5; j++)

                    fileWriter.append("            "+tableData[i][j]);

            }

        }

        fileWriter.close();

    }


}
