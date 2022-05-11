package app.TableMVC;


import app.CentralPanel;
import app.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa <code>ControllerTable</code> pokazujaca okno widoczne dla zalogowanych uzytkownikow obslugajaca zdarzenia wywolane elementami <code>ViewTable</code> i obsluzone metodami <code>ModelTable</code>
 */
public class ControllerTable{

    /**
     * Pole zawierajace modelTabeli
     */
    public static ModelTable modelTable;
    /**
     * Pole zawierajace widokTabeli
     */
    public static ViewTable viewTable;

    /**
     * Konstruktor klasy <code>ControllerTable</code> laczacej Model i View
     * @param modelTable model tabeli zawierajacy dane i metody
     * @param viewTable widok tabeli zawierajacy GUI
     */
    public ControllerTable(ModelTable modelTable, ViewTable viewTable){

        this.modelTable = modelTable;
        this.viewTable = viewTable;

        //Metoda dodająca mojego własnego nasłuchiwacza do buttonów w tabeli
        viewTable.addTableListener(new TableListener());

    }

    /**
     * Klasa wewnetrza sluzaca do oslugi zdarzen wywolanych przez widok
     */
    public class TableListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==viewTable.commitButton && CentralPanel.insertNumber.getText()!=null){

                //Funkcja modyfikująca model tablicy-zmienia wartości tablicy (modelTable)
                modelTable.insertDataIntoTableData(CentralPanel.insertNumber.getText(),CentralPanel.sliderY.getValue(),CentralPanel.sliderX.getValue(),viewTable);
                viewTable.table.setModel(modelTable);
                viewTable.table.repaint();

                //Testowanie pobierania wartości
                //System.out.println(CentralPanel.insertNumber.getText()+" "+CentralPanel.sliderX.getValue()+" "+CentralPanel.sliderY.getValue());

            }else if(e.getSource()==viewTable.clearButton){

                modelTable.fillTableZeros();
                viewTable.table.setModel(modelTable);
                viewTable.table.repaint();


            }else if(e.getSource()==viewTable.saveButton){

                try {
                    modelTable.saveFile(viewTable.table);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(viewTable,"Niepowodzenie zapisywania pliku!");
                }

            }else if(e.getSource()==Window.printButton){

                modelTable.printTable(viewTable.table);

            }

        }
    }


}
