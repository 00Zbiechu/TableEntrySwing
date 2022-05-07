package app.ComboBoxMVC;


import app.CentralPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static app.ComboBoxMVC.ViewComboBox.selectOperation;

public class ControllerComboBox {


    public static ModelComboBox modelComboBox;
    public static ViewComboBox viewComboBox;

    public ControllerComboBox(ModelComboBox modelComboBox, ViewComboBox viewComboBox){

        this.modelComboBox = modelComboBox;
        this.viewComboBox = viewComboBox;

        //Dodanie słuchacza do widoku
        this.viewComboBox.addComboBoxListener(new ComboBoxListener());

    }

    class ComboBoxListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==viewComboBox.calculate){

                modelComboBox.calculate((String) selectOperation.getSelectedItem(),CentralPanel.modelTable.getDataTable(), ViewComboBox.resultArea,ViewComboBox.resultArea);

            }


        }

    }

}
