package app;

import app.ComboBoxMVC.ControllerComboBox;
import app.ComboBoxMVC.ModelComboBox;
import app.ComboBoxMVC.ViewComboBox;
import app.TableMVC.ControllerTable;
import app.TableMVC.ModelTable;
import app.TableMVC.ViewTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;




public class CentralPanel extends JPanel{

    private JPanel  panelInsert, panelMid, panelBottom;

    private JLabel labelInsertNumber, labelInsertPositionX, labelInsertPositionY;

    public static JTextField insertNumber;

    public static JSlider sliderX,sliderY;

    //MVC Table
    public static ModelTable modelTable = new ModelTable();//Muszę się dostać do tego pola z poziomu Kontrolera Combo
    ViewTable viewTable = new ViewTable();
    ControllerTable controllerTable;

    //MVC ComboBox
    ModelComboBox modelComboBox = new ModelComboBox();
    ViewComboBox viewComboBox = new ViewComboBox();
    ControllerComboBox controllerComboBox;



    CentralPanel(){

        //Utworzenie kontrolera dla MVC tablicy
        controllerTable = new ControllerTable(modelTable,viewTable);
        controllerComboBox = new ControllerComboBox(modelComboBox,viewComboBox);

        initGUI();

    }

    private void initGUI(){


        //Tworzenie labelów
        createLabels();

        //Tworzenie pól tekstowych
        createTextFiled();

        //Tworzenie sliderów
        createSlider();



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

        this.labelInsertNumber = createJLabel(" Wprowadź wartość : ");
        this.labelInsertPositionX = createJLabel(" X : ");
        this.labelInsertPositionY = createJLabel(" Y : ");
    }

    private JTextField createJTextField(int colNumber){

        JTextField jTextField = new JTextField(colNumber);

        return jTextField;

    }

    private void createTextFiled(){

        this.insertNumber = createJTextField(10);

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



    private JPanel createJPanel(){

        JPanel jPanel = new JPanel();

        return jPanel;

    }


    private void createPanel(){

        //Panele pomocnicze, które będą dodane do panelMain
        this.panelInsert = createJPanel();
        this.panelMid = createJPanel();
        this.panelBottom = createJPanel();

    }


    //Tworzenie Layoutu dla panelu CentralPanel
    private LayoutManager createFormLayoutTop(int windowWidth, int windowHeight) {

        //Zmienna pomocnicza do obliczenia wielkości kolumn (sześć)
        long windowW = Math.round(windowWidth*0.24); //Wprowadź wartość Label
        long windowWTwo = Math.round(windowWidth*0.06); //TextField do wprowadzania wartości
        long windowWThree = Math.round(windowWidth*0.05); //Wprowadź X Label
        long windowWFour = Math.round(windowWidth*0.20); //JSlider X
        long windowWFive = Math.round(windowWidth*0.05); //Wprowadź X Label
        long windowWSix = Math.round(windowWidth*0.20); //JSlider X


        //Zmienne pomocnicze do obliczenia wielkości wierszy (dwóch)
        long windowH = Math.round(windowHeight*0.15);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px,"+windowWThree+"px,"+windowWFour+"px,"+windowWFive+"px,"+windowWSix+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;


    }

    private LayoutManager createFormLayoutMid(int windowWidth, int windowHeight){


        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.80);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (jeden)
        long windowH = Math.round(windowHeight*0.28);

        String columnConfiguration = windowW+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;

    }

    private LayoutManager createFormLayoutBottom(int windowWidth, int windowHeight){


        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.80);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (jeden)
        long windowH = Math.round(windowHeight*0.30);

        String columnConfiguration = windowW+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;



    }


    private void addComponentsAndSetLayout(){

        //Ustawianie Layoutu dla głównego okna aplikacji
        this.setLayout(new GridLayout(3,1,0,0));

        //Obiekt służący do obsługi komórek
        CellConstraints cc = new CellConstraints();


        //Panel górny --------------------------------------------------------------------

        //Ustawianie layoutu dla JPanelu na podstawie wielkości okna aplikacji
        panelInsert.setLayout(createFormLayoutTop(Window.windowWidth,Window.windowHeight));
        panelInsert.add(labelInsertNumber,cc.xy(1,1,CellConstraints.RIGHT,CellConstraints.BOTTOM));
        panelInsert.add(insertNumber,cc.xy(2,1,CellConstraints.LEFT,CellConstraints.BOTTOM));

        panelInsert.add(labelInsertPositionX,cc.xy(3,1,CellConstraints.RIGHT,CellConstraints.BOTTOM));
        panelInsert.add(sliderX,cc.xy(4,1,CellConstraints.LEFT,CellConstraints.BOTTOM));

        panelInsert.add(labelInsertPositionY,cc.xy(5,1,CellConstraints.RIGHT,CellConstraints.BOTTOM));
        panelInsert.add(sliderY,cc.xy(6,1,CellConstraints.LEFT,CellConstraints.BOTTOM));

        //Dodanie paneluInsert do głównego okna aplikacji
        add(panelInsert);



        //Panel środkowy ---------------------------------------------------------------------
        panelMid.setLayout(createFormLayoutMid(Window.windowWidth,Window.windowHeight));
            //Dodanie do panelu MID przechowującego tablice modelu widoku MVC
            panelMid.add(ControllerTable.viewTable,cc.xy(1,1,CellConstraints.CENTER,CellConstraints.TOP));

        //Dodawanie paneluTable do głównego okna aplikacji
        add(panelMid);


        //Panel dolny-wybór operacji do wykonania-------------------------------------------------
        panelBottom.setLayout(createFormLayoutBottom(Window.windowWidth,Window.windowHeight));
            //Dodane do panelu Operation przechowującego wybór możliwej operacji do wykonania modelu widoku MVC
            panelBottom.add(ControllerComboBox.viewComboBox,cc.xy(1,1,CellConstraints.LEFT,CellConstraints.TOP));

        //Dodanie panelu panelOperation do okna głównego
        add(panelBottom);

    }


}