package app;

import app.TableMVC.ControllerTable;
import app.TableMVC.ModelTable;
import app.TableMVC.ViewTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CentralPanel extends JPanel implements ActionListener {

    private JPanel  panelInsert, panelMid, panelOperation, panelResult;

    private JLabel labelInsertNumber, labelInsertPositionX, labelInsertPositionY, labelSelectOperation;

    public static JTextField insertNumber;

    private JSlider sliderX,sliderY;

    private Icon iconCalculate;

    private JButton calculate;

    private JComboBox selectOperation;

    private String[] dateComboBox = {"Średnia","Suma","MAX","MIN"};

    public static JTextArea resultArea;



    CentralPanel(){

        initGUI();

    }

    private void initGUI(){


        //Tworzenie labelów
        createLabels();

        //Tworzenie pól tekstowych
        createTextFiled();

        //Tworzenie sliderów
        createSlider();

        //Tworzenie ikon do przycisków
        createIcon();

        //Tworzenie przycisków
        createButton();

        //Tworzenie comboBox'a
        createComboBox();

        //Wypełnianie comboBox'a danym
        fillDataComboBox();

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
        this.labelSelectOperation = createJLabel(" Wybierz operację : ");

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


    private JButton createJButton(String text,Icon icon){

        JButton jButton = new JButton(text,icon);
        jButton.addActionListener(this);
        jButton.setBackground(Color.WHITE);

        return jButton;

    }


    private void createButton(){


        this.calculate = createJButton("Oblicz",iconCalculate);


    }

    private JPanel createJPanel(){

        JPanel jPanel = new JPanel();

        return jPanel;

    }

    private JPanel createResultPanel(String title,Color color) {

        JPanel jp = new JPanel();

        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color), title);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        jp.setBorder(titledBorder);
        jp.setLayout(new BorderLayout(0,0));

        //Pole JTextArea wewnątrz paneluResult
        this.resultArea = new JTextArea();
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);

        jp.add(resultArea,BorderLayout.CENTER);
        return jp;
    }

    private void createPanel(){

        //Panele pomocnicze, które będą dodane do panelMain
        this.panelInsert = createJPanel();
        this.panelMid = createJPanel();
        this.panelOperation = createJPanel();
        this.panelResult = createResultPanel("Rezultat operacji",Color.BLACK);


    }

    //Konstruktor ikon
    private Icon createJIcon(String file) {
        String name = "/grafika/"+file;
        Icon icon = new ImageIcon(getClass().getResource(name));

        return icon;
    }

    private void createIcon(){

        this.iconCalculate = createJIcon("min_operation.jpg");

    }

    private JComboBox createJComboBox(){

        JComboBox jComboBox = new JComboBox();
        jComboBox.setBackground(Color.WHITE);
        jComboBox.addActionListener(this);

        return jComboBox;

    }

    private void createComboBox(){

        this.selectOperation = createJComboBox();

    }

    private void fillDataComboBox(){

        for(int i=0;i<dateComboBox.length;i++){

            selectOperation.addItem(dateComboBox[i]);

        }

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
        long windowWTwo = Math.round(windowWidth*0.20);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (jeden)
        long windowH = Math.round(windowHeight*0.28);

        String columnConfiguration = windowW+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;

    }

    private LayoutManager createFormLayoutBottom(int windowWidth, int windowHeight){


        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.20);
        long windowWTwo = Math.round(windowWidth*0.15);
        long windowWThree = Math.round(windowWidth*0.20);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (jeden)
        long windowH = Math.round(windowHeight*0.15);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px,"+windowWThree+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;



    }


    private void addComponentsAndSetLayout(){

        //Ustawianie Layoutu dla głównego okna aplikacji
        this.setLayout(new GridLayout(4,1,0,0));

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

            panelMid.add(,cc.xy(1,1,CellConstraints.CENTER,CellConstraints.CENTER));

        //Dodawanie paneluTable do głównego okna aplikacji
        add(panelMid);


        //Panel dolny-wybór operacji do wykonania-------------------------------------------------
        panelOperation.setLayout(createFormLayoutBottom(Window.windowWidth,Window.windowHeight));

        //Dodanie komponentów GUI do panelu Operation
        panelOperation.add(labelSelectOperation,cc.xy(1,1,CellConstraints.RIGHT,CellConstraints.CENTER));
        panelOperation.add(selectOperation,cc.xy(2,1,CellConstraints.LEFT,CellConstraints.CENTER));
        panelOperation.add(calculate,cc.xy(3,1,CellConstraints.LEFT,CellConstraints.CENTER));

        //Dodanie panelu panelOperation do okna głównego
        add(panelOperation);


        //Panel dolny-rezultat wykonanej operacji ------------------------------------------------

        //Dodanie panelu panelResult do głównego okna
        add(panelResult);





    }

    public static String getInsertValue(JTextField textField){

        return textField.getText();

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==selectOperation){

            Window.statusBar.setStatusAndValueOfApplication("Pasek operacji","Wybrano");

        }
    }
}