package app.ComboBoxMVC;

import app.Main;
import app.Window;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Klasa <code>ModelComboBox</code> implementujaca interfejs<code>ComboBoxModel</code>, klasa jest modelem danych i baza regul dla struktury MVC comboBoxa
 */
public class ModelComboBox implements ComboBoxModel {

    private String[] dateComboBox;

    /**
     * Konstruktor bezparametrowy klasy <code>ModelComboBox</code> wypelniajacy tablice danych dla ComboBoxa
     */
    public ModelComboBox(){

        dateComboBox = new String[] {"Średnia","Suma","MAX","MIN"};

    }

    /**
     * Pobieranie rozmiaru tablicy <code>dateComboBox</code>
     * @return dateComboBox
     */
    @Override
    public int getSize() {
        return dateComboBox.length;
    }

    /**
     * Pobieranie obiektu tablicy na pozycji opisywanej przez parametr
     * @param i pozycja w tablicy
     * @return zwraca obiekt bedacy na danej pozycji
     */
    @Override
    public Object getElementAt(int i) {
        return dateComboBox[i];
    }

    /**
     * Pobieranie tablicy z danymi
     * @return zwraca dateComboBox
     */
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

    /**
     * Metoda sluzaca do obliczania wartosci
     * @param operation rodzaj operacji
     * @param tableDataModel tabela, z ktorej maja zostac pobrane dane
     * @param textArea textArea, w ktorej ma znalezc sie wynik
     * @param parent rodzic w ktorym ma zostac pokazany JOptionPane z komunikatem w raze przechwycenia wyjatk
     */
    public static void calculate(String operation,int[][] tableDataModel,JTextArea textArea, Component parent){

        Window.statusBar.setStatusAndValueOfApplication("Wykonanie operacji",String.valueOf(operation));
        Main.logger.info("Wykonanie operacji "+String.valueOf(operation));
        Main.myLogger.info("Wykonanie operacji "+String.valueOf(operation));

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
                Main.logger.warn("Tablica jest pusta");
                Main.myLogger.warn("Tablica jest pusta");
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
                Main.logger.warn("Tablica jest pusta");
                Main.myLogger.warn("Tablica jest pusta");
            }


        }

    }


}
