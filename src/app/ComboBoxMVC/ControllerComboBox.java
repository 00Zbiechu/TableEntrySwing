package app.ComboBoxMVC;


import app.CentralPanel;
import app.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static app.ComboBoxMVC.ViewComboBox.selectOperation;

/**
 * Klasa <code>ControllerComboBox</code> obslugajaca zdarzenia wywolane elementami <code>ViewComboBox</code> i obsluzone metodami <code>ModelComboBox</code>
 */
public class ControllerComboBox {

    /**
     * model ComboBoxa zawierajacy dane i metody
     */
    public static ModelComboBox modelComboBox;
    /**
     * widok comboBoxa zawierajacy GUI
     */
    public static ViewComboBox viewComboBox;

    /**
     * Konstruktor klasy <code>ControllerComboBox</code> laczacej Model i View
     *
     * @param modelComboBox model ComboBoxa zawierajacy dane i metody
     * @param viewComboBox  widok comboBoxa zawierajacy GUI
     */
    public ControllerComboBox(ModelComboBox modelComboBox, ViewComboBox viewComboBox) {

        ControllerComboBox.modelComboBox = modelComboBox;
        ControllerComboBox.viewComboBox = viewComboBox;

        //Dodanie słuchacza do widoku
        ControllerComboBox.viewComboBox.addComboBoxListener(new ComboBoxListener());

    }

    /**
     * Klasa wewnetrza sluzaca do oslugi zdarzen wywolanych przez sluchacza
     */
    public class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == ViewComboBox.calculate) {

                ModelComboBox.calculate((String) selectOperation.getSelectedItem(), CentralPanel.modelTable.getDataTable(), ViewComboBox.resultArea, ViewComboBox.resultArea);

            } else if (e.getSource() == selectOperation) {

                Window.statusBar.setStatusAndValueOfApplication("Wybieranie operacji", String.valueOf(selectOperation.getSelectedItem()));

            }


        }

    }

}
