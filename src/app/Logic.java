package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Logic {


        public static void insertDataIntoTable(String data, int x, int y, JTable table, Component parent){

            int value=0;
            try {
               value =  Integer.parseInt(data);
               Window.statusBar.setStatusAndValueOfApplication("Wprowadzono wartość",String.valueOf(value));

                //Model do obsługi tabeli
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                //Wprowadzenie zmian do tabeli
                model.setValueAt(value,--y,--x);
                table.selectAll();

            }catch (Exception e){
                Window.statusBar.setStatusAndValueOfApplication("Podaną złą wartość","Błąd");
                JOptionPane.showMessageDialog(parent,"Wprowadzono niewłaściwą wartość!","Zła wartość",JOptionPane.WARNING_MESSAGE);
            }




        }



        public static void calculate(String operation,JTable table,JTextArea textArea, Component parent){

            Window.statusBar.setStatusAndValueOfApplication("Wykonanie operacji",String.valueOf(operation));

            if(operation.equals("Średnia")){

                //Suma wszystkich elementów w tablicy
                int sum=0;

                    for(int i=0;i<5;i++){

                        for(int j=0;j<5;j++){

                            sum=sum+(int)(table.getValueAt(i,j));

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

                        sum=sum+(int)(table.getValueAt(i,j));

                    }

                }

                textArea.setText("Suma wszystkich elementów to: "+sum+"\n");

            }else if(operation.equals("MAX")){

                    //Dodanie elementów tablicy do ArrayListy, która ma metodę zwracającą MAX element
                    ArrayList<Integer> allElements = new ArrayList<Integer>();

                    try {

                        for(int i=0;i<5;i++){

                            for(int j=0;j<5;j++){


                                allElements.add((int)(table.getValueAt(i,j)));

                            }

                        }

                        textArea.setText("MAX element to: "+Collections.max(allElements)+"\n");
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

                            allElements.add((int)(table.getValueAt(i,j)));


                        }

                    }
                    textArea.setText("MIN element to: "+Collections.min(allElements)+"\n");
                }catch (Exception e){
                    Window.statusBar.setStatusAndValueOfApplication("Tablica","Pusta");
                    JOptionPane.showMessageDialog(parent,"Tablica jest pusta proszę ją wypełnić!","Brak danych",JOptionPane.WARNING_MESSAGE);
                }


            }

        }


        public static void clear(JTable table,JTextArea textArea) {

            //Model do obsługi tabeli
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            String data[][] = new String[5][5];

            //Wprowadzenie zmian do tabeli
            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    model.setValueAt(0, i, j);

                }

            }

            textArea.setText("");

        }

        public static void saveFile(Component parent,JTable table) throws IOException {

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

                        fileWriter.append("            "+table.getValueAt(i, j));

                }

            }

            fileWriter.close();

        }

        public static void printTable(JTable table){

            Window.statusBar.setStatusAndValueOfApplication("Drukowanie","True");

            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                try {pj.print();}
                catch (PrinterException exc) {

                }
            }


        }

}


