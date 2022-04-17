package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;


public class CentralPanel extends JPanel {

    private JPanel panelInsert,panelTable,panelAction;

    private JLabel labelInsertNumber, labelInsertPositionX, labelInsertPositionY;

    private JTextField insertNumber;

    private JSlider sliderX,sliderY;

    private JTable table;

    private JButton clearButton, commitButton, saveButton;


    CentralPanel(){

        setLayout(new GridLayout(3,1));
        initGUI();

    }

    private void initGUI(){


        //Tworzenie labelów
        createLabels();

        //Tworzenie pól tekstowych
        createTextFiled();

        //Tworzenie sliderów
        createSlider();

        //Tworzenie tabeli
        createTable();

        //Tworzenie przycisków
         createButton();

        //Tworzenie paneli
        createPanel();


        //Dodawanie komponentów do paneli oraz ustawianie layoutu dla paneli
        addComponentsAndSetLayout();



    }



    private JLabel createJLabel(String label){

        JLabel jLabel = new JLabel(label);

        return jLabel;

    }

    private void createLabels(){

        this.labelInsertNumber = createJLabel("Wprowadź wartość: ");
        this.labelInsertPositionX = createJLabel("Wybierz kolumnę: ");
        this.labelInsertPositionY = createJLabel("Wybierz wiersz: ");

    }

    private JTextField createJTextField(int colNumber){

        JTextField jTextField = new JTextField(colNumber);

        return jTextField;

    }

    private void createTextFiled(){

        this.insertNumber = createJTextField(3);

    }


    private JSlider createJSlider(int min,int max){

        JSlider jSlider = new JSlider(min,max,1);

        jSlider.setPaintTrack(true);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);

        jSlider.setMajorTickSpacing(1);


        return jSlider;

    }

    private void createSlider(){


        this.sliderX = createJSlider(1,5);
        this.sliderY = createJSlider(1,5);

    }

    private JTable createJTable(int col, int row){


        JTable jTable = new JTable(col,row);


        return jTable;

    }

    private void createTable(){

        this.table = createJTable(5,5);

    }

    private JButton createJButton(String text){

        JButton jButton = new JButton(text);

        return jButton;

    }

    private void createButton(){

        this.clearButton = createJButton("Wyczyść");
        this.commitButton = createJButton("Wprowadź");
        this.saveButton = createJButton("Zapisz");

    }

    private JPanel createJPanel(){

        JPanel jPanel = new JPanel();

        return jPanel;

    }

    private void createPanel(){

        this.panelInsert = createJPanel();
        this.panelTable = createJPanel();
        this.panelAction = createJPanel();

    }



    //Tworzenie Layoutu dla panelu CentralPanel
    private LayoutManager createFormLayoutTop(int windowWidth, int windowHeight) {

        //Zmienna pomocnicza do obliczenia wielkości kolumn (sześć)
        long windowW = Math.round(windowWidth*0.19); //Wprowadź wartość Label
        long windowWTwo = Math.round(windowWidth*0.05); //TextField do wprowadzania wartości
        long windowWThree = Math.round(windowWidth*0.17); //Wprowadź X Label
        long windowWFour = Math.round(windowWidth*0.17); //JSlider X
        long windowWFive = Math.round(windowWidth*0.17); //Wprowadź X Label
        long windowWSix = Math.round(windowWidth*0.17); //JSlider X


        //Zmienne pomocnicze do obliczenia wielkości wierszy (dwóch)
        long windowH = Math.round(windowHeight*0.15);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px,"+windowWThree+"px,"+windowWFour+"px,"+windowWFive+"px,"+windowWSix+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;


    }

    private LayoutManager createFormLayoutMid(int windowWidth, int windowHeight){


        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.70);
        long windowWTwo = Math.round(windowWidth*0.20);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (dwóch)
        long windowH = Math.round(windowHeight*0.50);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;



    }

    private void addComponentsAndSetLayout(){

        //Obiekt służący do obsługi komórek
        CellConstraints cc = new CellConstraints();

        //Panel górny --------------------------------------------------------------------

            //Ustawianie layoutu dla JPanelu na podstawie wielkości okna aplikacji
            panelInsert.setLayout(createFormLayoutTop(Window.windowWidth,Window.windowHeight));
                panelInsert.add(labelInsertNumber,cc.xy(1,1,CellConstraints.CENTER,CellConstraints.CENTER));
                panelInsert.add(insertNumber,cc.xy(2,1,CellConstraints.LEFT,CellConstraints.CENTER));

                panelInsert.add(labelInsertPositionX,cc.xy(3,1,CellConstraints.CENTER,CellConstraints.CENTER));
                panelInsert.add(sliderX,cc.xy(4,1,CellConstraints.LEFT,CellConstraints.CENTER));

                panelInsert.add(labelInsertPositionY,cc.xy(5,1,CellConstraints.CENTER,CellConstraints.CENTER));
                panelInsert.add(sliderY,cc.xy(6,1,CellConstraints.LEFT,CellConstraints.CENTER));

            //Dodanie paneluInsert do głównego okna aplikacji
            add(panelInsert);



        //Panel środkowy ---------------------------------------------------------------------
        panelTable.setLayout(createFormLayoutMid(Window.windowWidth,Window.windowHeight));
            panelTable.add(table,cc.xy(1,1,CellConstraints.CENTER,CellConstraints.TOP));
            panelTable.add(panelAction,cc.xy(2,1,CellConstraints.CENTER,CellConstraints.TOP));

                //Wypełnianie panelu akcji przyciskami i nadanie mu stylu, żeby układały się w 1 kolumnie
                panelAction.setLayout(new GridLayout(3,1,0,5));
                    panelAction.add(clearButton);
                    panelAction.add(commitButton);
                    panelAction.add(saveButton);


            //Dodawanie paneluTable do głównego okna aplikacji
            add(panelTable);
    }


}
