package app.TableMVC;


import app.CentralPanel;
import app.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTable{

    public static ModelTable modelTable;
    public static ViewTable viewTable;

    public ControllerTable(ModelTable modelTable, ViewTable viewTable){

        this.modelTable = modelTable;
        this.viewTable = viewTable;

        //Metoda dodająca mojego własnego nasłuchiwacza do buttonów w tabeli
        viewTable.addTableListener(new TableListener());

    }

    //Klasa wewnętrzna, żebym miał dostęp do utworzonych viewTable i modelTable :)
    class TableListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==viewTable.commitButton && CentralPanel.insertNumber.getText()!=null){

                //Funkcja modyfikująca model tablicy-zmienia wartości tablicy (modelTable)
                modelTable.insertDataIntoTableData(CentralPanel.insertNumber.getText(),CentralPanel.sliderX.getValue(),CentralPanel.sliderY.getValue(),viewTable);
                viewTable.table.setModel(modelTable);
                viewTable.table.selectAll();

                //Testowanie pobierania wartości
                //System.out.println(CentralPanel.insertNumber.getText()+" "+CentralPanel.sliderX.getValue()+" "+CentralPanel.sliderY.getValue());

            }else if(e.getSource()==viewTable.clearButton){

                modelTable.fillTableZeros();
                viewTable.table.setModel(modelTable);
                viewTable.table.selectAll();


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
