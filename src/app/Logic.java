package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Logic {

        public static void insertDataIntoTable(String data, int x, int y, JTable table){

            //Model do obsługi tabeli
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            //Wprowadzenie zmian do tabeli
            model.setValueAt(Integer.parseInt(data),--y,--x);
            table.selectAll();


        }


        public static void calculate(String operation,JTable table,JTextArea textArea){


            if(operation.equals("Średnia")){

                //Suma wszystkich elementów w tablicy
                int sum=0;

                    for(int i=0;i<5;i++){

                        for(int j=0;j<5;j++){

                            if(table.getValueAt(i,j)==null){
                                sum=sum+0;
                            }else{
                                sum=sum+(int)(table.getValueAt(i,j));
                            }

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

                        if(table.getValueAt(i,j)==null){
                            sum=sum+0;
                        }else{
                            sum=sum+(int)(table.getValueAt(i,j));
                        }

                    }

                }

                textArea.setText("Suma wszystkich elementów to: "+sum+"\n");

            }else if(operation.equals("MAX")){

                    //Dodanie elementów tablicy do ArrayListy, która ma metodę zwracającą MAX element
                    ArrayList<Integer> allElements = new ArrayList<Integer>();

                    for(int i=0;i<5;i++){

                        for(int j=0;j<5;j++){

                                if(table.getValueAt(i,j)==null){
                                    continue;
                                }else{
                                    allElements.add((int)(table.getValueAt(i,j)));
                                }

                        }

                    }

                textArea.setText("MAX element to: "+Collections.max(allElements)+"\n");


            }else if(operation.equals("MIN")){

                //Dodanie elementów tablicy do ArrayListy, która ma metodę zwracającą MIN element
                ArrayList<Integer> allElements = new ArrayList<Integer>();

                for(int i=0;i<5;i++){

                    for(int j=0;j<5;j++){

                        if(table.getValueAt(i,j)==null){
                            continue;
                        }else{
                            allElements.add((int)(table.getValueAt(i,j)));
                        }

                    }

                }

                textArea.setText("MIN element to: "+Collections.min(allElements)+"\n");

            }

        }


        public static void clear(JTable table,JTextArea textArea) {

            //Model do obsługi tabeli
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //Wprowadzenie zmian do tabeli
            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    model.setValueAt(null, i, j);

                }

            }

            textArea.setText("");

        }

        public static void saveFile(Component parent,JTable table) throws IOException {

            //Stworzenie obiektu do obsługi okna wyboru pliku
            JFileChooser fileChooser = new JFileChooser();

            //Wybranie katalogu domyślnego
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            //Otwarcie okna wyboru-zwracającego wartość //CANCEL(1), APPROVE(0), ERROR(-1)
            int result = fileChooser.showOpenDialog(parent);

            //Jeśli wybrano plik
            FileWriter fileWriter = null;
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

                        fileWriter.append("     "+table.getValueAt(i, j));

                }

            }

            fileWriter.close();


        }

}


