package app.TableMVC;

import app.Window;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class ModelTable extends AbstractTableModel {

    private byte rowNumber = 5;
    private byte colNumber = 5;
    private int tableData[][] = new int[rowNumber][colNumber];
    private String[] labelForTableColumns = {"1","2","3","4","5"};

    public ModelTable(){

        fillTableZeros();

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

    


    public void setValueAt(int x, int y, int value){

        tableData[x][y] = value;
        //Poinformowanie słuchacza o zmianie w modelu-wprowadzenie wartości
        fireTableDataChanged();

    }

    public void insertDataIntoTableData(String data, int x, int y, Component parent){

        int value=0;
        try {
            value =  Integer.parseInt(data);
            Window.statusBar.setStatusAndValueOfApplication("Wprowadzono wartość",String.valueOf(value));

            //Wprowadzenie zmian do tabeliData
            tableData[x][y]=value;
            //table.selectAll();

        }catch (Exception e){
            Window.statusBar.setStatusAndValueOfApplication("Podaną złą wartość","Błąd");
            JOptionPane.showMessageDialog(parent,"Wprowadzono niewłaściwą wartość!","Zła wartość",JOptionPane.WARNING_MESSAGE);
        }

    }


    public void fillTableZeros() {

        for (int i = 0; i < rowNumber; i++) {

            for (int j = 0; j < colNumber; j++) {

                tableData[i][j] = 0;

            }
        }
        //Poinformowanie słuchacza o zmianie w modelu-wyzerowanie tablicy
    }


    public void printTable(JTable table) {

        Window.statusBar.setStatusAndValueOfApplication("Drukowanie", "True");

        PrinterJob pj = PrinterJob.getPrinterJob();

        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (PrinterException exc) {

            }
        }
    }



    public void saveFile(Component parent,JTable table) throws IOException {

        Window.statusBar.setStatusAndValueOfApplication("Zapis do pliku","Tablicy");

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


    public void calculate(String operation,JTextArea textArea, Component parent){

        Window.statusBar.setStatusAndValueOfApplication("Wykonanie operacji",String.valueOf(operation));

        if(operation.equals("Średnia")){

            //Suma wszystkich elementów w tablicy
            int sum=0;

            for(int i=0;i<5;i++){

                for(int j=0;j<5;j++){

                    sum=sum+(int)getValueAt(i,j);

                }

            }

            //Obliczenie średniej arytmetycznej wszystkich komórek tabeli
            double average = sum/25.0;

            //Dodanie obliczonej wartości do textArea przechowującego rezultaty wykonanych działań
            String averageToAdd = String.valueOf(average);
            textArea.setText("Średnia arytmetyczna: "+averageToAdd+"\n");


        }else if(operation.equals("Suma")){


            //Suma wszystkich elementów w tablicy
            int sum=0;

            for(int i=0;i<5;i++){

                for(int j=0;j<5;j++){

                    sum=sum+(int)getValueAt(i,j);

                }

            }

            textArea.setText("Suma wszystkich elementów to: "+sum+"\n");

        }else if(operation.equals("MAX")){

            //Dodanie elementów tablicy do ArrayListy, która ma metodę zwracającą MAX element
            ArrayList<Integer> allElements = new ArrayList<Integer>();

            try {

                for(int i=0;i<5;i++){

                    for(int j=0;j<5;j++){


                        allElements.add((int) getValueAt(i,j));

                    }

                }

                textArea.setText("MAX element to: "+ Collections.max(allElements)+"\n");
            }catch (Exception e){
                Window.statusBar.setStatusAndValueOfApplication("Tablica","Pusta");
                JOptionPane.showMessageDialog(parent,"Tablica jest pusta proszę ją wypełnić.");
            }



        }else if(operation.equals("MIN")){

            //Dodanie elementów tablicy do ArrayListy, która ma metodę zwracającą MIN element
            ArrayList<Integer> allElements = new ArrayList<Integer>();

            try{
                for(int i=0;i<5;i++){

                    for(int j=0;j<5;j++){

                        allElements.add((int) getValueAt(i,j));


                    }

                }
                textArea.setText("MIN element to: "+Collections.min(allElements)+"\n");
            }catch (Exception e){
                Window.statusBar.setStatusAndValueOfApplication("Tablica","Pusta");
                JOptionPane.showMessageDialog(parent,"Tablica jest pusta proszę ją wypełnić!","Brak danych",JOptionPane.WARNING_MESSAGE);
            }


        }

    }


    public void refreshTable(JTable table){

        for(int i=0;i<table.getRowCount();i++){

            for(int j=0;j<table.getColumnCount();j++){

                table.setValueAt(getValueAt(i,j),i,j);

            }

        }


    }




}
