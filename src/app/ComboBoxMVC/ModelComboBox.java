package app.ComboBoxMVC;

import app.Window;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ModelComboBox implements ComboBoxModel {

    private String[] dateComboBox;

    public ModelComboBox(){

        dateComboBox = new String[] {"Średnia","Suma","MAX","MIN"};

    }

    @Override
    public int getSize() {
        return dateComboBox.length;
    }

    @Override
    public Object getElementAt(int i) {
        return dateComboBox[i];
    }

    public String[] getDateComboBox(){
        return dateComboBox;
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {

    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }


    @Override
    public void setSelectedItem(Object o) {

    }

    @Override
    public Object getSelectedItem() {
        return null;
    }


    public static void calculate(String operation,int[][] tableDataModel,JTextArea textArea, Component parent){

        Window.statusBar.setStatusAndValueOfApplication("Wykonanie operacji",String.valueOf(operation));

        if(operation.equals("Średnia")){

            //Suma wszystkich elementów w tablicy
            int sum=0;

            for(int i=0;i<5;i++){

                for(int j=0;j<5;j++){

                    sum=sum+tableDataModel[i][j];

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

                    sum=sum+tableDataModel[i][j];

                }

            }

            textArea.setText("Suma wszystkich elementów to: "+sum+"\n");

        }else if(operation.equals("MAX")){

            //Dodanie elementów tablicy do ArrayListy, która ma metodę zwracającą MAX element
            ArrayList<Integer> allElements = new ArrayList<Integer>();

            try {

                for(int i=0;i<5;i++){

                    for(int j=0;j<5;j++){


                        allElements.add(tableDataModel[i][j]);

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

                        allElements.add(tableDataModel[i][j]);


                    }

                }
                textArea.setText("MIN element to: "+Collections.min(allElements)+"\n");
            }catch (Exception e){
                Window.statusBar.setStatusAndValueOfApplication("Tablica","Pusta");
                JOptionPane.showMessageDialog(parent,"Tablica jest pusta proszę ją wypełnić!","Brak danych",JOptionPane.WARNING_MESSAGE);
            }


        }

    }


}
