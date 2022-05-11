package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Klasa <code>StatusBar</code> rozszerzajaca klase <code>JPanel</code>
 */
public class StatusBar extends JPanel {

    private JLabel descriptionStatusBar,status,value;
    private JTextField statusField,valueField;

    /**
     * Konstruktor klasy <code>StatusBar</code> inicjalizujacej GUI StatusBara
     */
    public StatusBar(){

        initGUI();


    }

    private void initGUI(){

        this.setBorder(new LineBorder(Color.LIGHT_GRAY));

        //utworzenie etykiet
        createLabels();

        //utworzenie pól tekstowych, które będą przetrzymywać stan aplikacji
        createTextField();

        //Dodanie layoutu do klasy i dodane komponentów GUI
        addComponentsAndSetLayout();


    }

    //Tworzenie Layoutu dla okna stopki zawierającej status programu
    private LayoutManager createFormLayout(int windowWidth,int windowHeight) {

        //Zmienna pomocnicza do obliczenia wielkości kolumn (sześć)
        long windowW = Math.round(windowWidth*0.16); //descriptionStatusBar

        long windowWTwo = Math.round(windowWidth*0.30); //odstęp

        long windowWThree = Math.round(windowWidth*0.10); //statusLabel
        long windowWFour = Math.round(windowWidth*0.22); //statusField

        long windowWFive = Math.round(windowWidth*0.10); //statusLabel
        long windowWSix = Math.round(windowWidth*0.16); //statusField



        //Zmienne pomocnicze do obliczenia wielkości wierszy (jeden)
        long windowH = Math.round(windowHeight*0.06);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px,"+windowWThree+"px,"+windowWFour+"px,"
                +windowWFive+"px,"+windowWSix+"px";

        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;


    }

    private JLabel createJLabel(String text){

        JLabel jlabel = new JLabel(text);

        return jlabel;

    }

    private void createLabels(){

        descriptionStatusBar = createJLabel(" Pasek statusu");
        status = createJLabel("Status: ");
        value = createJLabel("Wartość: ");

    }

    private JTextField createJTextFiled(int numberColumns){

        JTextField jTextField = new JTextField(numberColumns);
        jTextField.setEditable(false);
        return jTextField;
    }

    private void createTextField(){

        statusField = createJTextFiled(25);
        valueField = createJTextFiled(15);

    }

    //Metoda służąca do dodawania komponentów do okna
    private void addComponentsAndSetLayout(){

        this.setLayout(createFormLayout(Window.windowWidth,Window.windowHeight));
        this.setBackground(new Color(219,219,219));

        //Utworzenie obiektu do obsługi komórek formLayout
        CellConstraints cc = new CellConstraints();

        add(descriptionStatusBar,cc.xy(1,1,CellConstraints.LEFT,CellConstraints.CENTER));
        add(status,cc.xy(3,1,CellConstraints.RIGHT,CellConstraints.CENTER));
        add(statusField,cc.xy(4,1,CellConstraints.LEFT,CellConstraints.CENTER));
        add(value,cc.xy(5,1,CellConstraints.RIGHT,CellConstraints.CENTER));
        add(valueField,cc.xy(6,1,CellConstraints.LEFT,CellConstraints.CENTER));


    }

    /**
     * Metoda sluzaca do ustawiania statusu i wartosci statusu w StatusBarze
     * @param status String zawierajacy status paska statusu
     * @param value String zawierajacy wartosc paska statusu
     */
    public void setStatusAndValueOfApplication(String status,String value){

        this.statusField.setText(status);
        this.valueField.setText(value);

    }

    /**
     * Pobieranie statusu z paska statusu
     * @return zwraca String z tekstem zwierajacym obecny status
     */
    public String getStatus(){

        return this.statusField.getText();

    }

    /**
     * Pobieranie statusu z paska statusu
     * @return zwraca String z tekstem zwierajacym obecna wartosc
     */
    public String getValue(){

        return this.valueField.getText();

    }



}
