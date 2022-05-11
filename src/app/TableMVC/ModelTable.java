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


/**
 * Klasa <code>ModelTable</code> dziedziczy po klasie <code>AbstractTableModel</code> zawiera dane i metody do obslugi tabeli
 */
public class ModelTable extends AbstractTableModel {

    private byte rowNumber = 5;
    private byte colNumber = 5;
    private int tableData[][] = new int[rowNumber][colNumber];
    private String[] labelForTableColumns = {"1","2","3","4","5"};

    /**
     * Konstruktor bezparametrowy <code>ModelTable</code> wypelnia tablice danych zerami
     */
    public ModelTable(){

        fillTableZeros();

    }

    /**
     * Pobieranie ilosci wierszy
     * @return ilosc wierszy
     */
    @Override
    public int getRowCount() {
        return rowNumber;
    }

    /**
     * Pobieranie ilosci kolumn
     * @return ilosc kolumn
     */
    @Override
    public int getColumnCount() {
        return colNumber;
    }

    /**
     * Zwraca wartosc w punkcie x,y
     * @param x wartosc wiersza
     * @param y wartosc kolumny
     * @return zwraca wartosc z tablicy o indeksie x,y
     */
    @Override
    public Object getValueAt(int x, int y) {
        return tableData[x][y];
    }

    /**
     * Pobieranie nazwy kolumny
     * @param col nr kolumny
     * @return zwraca tekst z nazwa kolumny
     */
    public String getColumnName(int col) {
        return labelForTableColumns[col];
    }

    /**
     * Pobieranie tablicy z danymi
     * @return tablica z danymi
     */
    public int[][] getDataTable(){return tableData;}


    /**
     * Wypełnianie tabeli wartościami
     * @param data wartosc ktora zostanie wypelniona tablica
     * @param x nr wiersza
     * @param y nr kolumny
     * @param parent rodzic dla ktorego zostanie wyswietlone okno dialogowe w przypadku bledu
     */
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


    /**
     * Wypelnianie tablicy danych zerami
     */
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


    /**
     * Metoda sluzaca do drukowania wartosci tabeli
     * @param table tabela z ktorej dane zostana wydrukowane
     */
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


    /**
     * zapisywanie wartosci z tabeli do pliku
     * @param parent rodzic dla ktorego zostanie wywolane okno dialogowe
     * @throws IOException wyjatek rzycony w przypadku niepowodzenia
     */
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
