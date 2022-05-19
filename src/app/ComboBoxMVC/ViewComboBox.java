package app.ComboBoxMVC;

import app.Window;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Klasa ViewComboBox dziedzicaca po <code>JPanel</code> tworzaca GUI dla ComboBoxa
 */
public class ViewComboBox extends JPanel {

    //Model potrzeby do utworzenie comboBoxa
    private  ModelComboBox modelComboBox = new ModelComboBox();

    private JPanel panelResult, panelOperation;

    private JLabel labelSelectOperation;

    private Icon iconCalculate;

    public static JButton calculate;

    public static JComboBox selectOperation;

    public static JTextArea resultArea;


    /**
     * Konstruktor bezparametrowy klasy <code>ViewComboBox</code> sluzy do tworzenia GUI ComboBoxa na podstawie modelu danych
     */
    public ViewComboBox(){

        initGUI();

    }

    private void initGUI(){

        //Tworzenie paneli
        createPanel();

        //Tworzenie label
        createLabels();

        //Tworzenie ikon do przycisków
        createIcon();

        //Tworzenie przycisków
        createButton();

        //Tworzenie comboBox'a
        createComboBox();


        //Ustawianie layoutu
        addComponentsAndSetLayout();

    }

    private JLabel createJLabel(String label){

        JLabel jLabel = new JLabel(label);

        return jLabel;

    }

    private void createLabels(){

        this.labelSelectOperation = createJLabel(" Wybierz operację : ");

    }


    //Konstruktor ikon
    private Icon createJIcon(String file) {
        String name = "/graphics/" +file;
        Icon icon = new ImageIcon(getClass().getResource(name));

        return icon;
    }

    private void createIcon(){

        this.iconCalculate = createJIcon("min_operation.jpg");

    }


    private JButton createJButton(String text,Icon icon){

        JButton jButton = new JButton(text,icon);
        jButton.setBackground(Color.WHITE);

        return jButton;

    }


    private void createButton(){


        this.calculate = createJButton("Oblicz",iconCalculate);


    }



    private void createComboBox(){

        this.selectOperation = new JComboBox(modelComboBox.getDateComboBox());
            selectOperation.setBackground(Color.WHITE);

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

        this.panelOperation = createJPanel();
        this.panelResult = createResultPanel("Rezultat operacji",Color.BLACK);

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
        this.setLayout(new GridLayout(2,1,0,0));

        //Obiekt służący do obsługi komórek
        CellConstraints cc = new CellConstraints();



        //Panel dolny-wybór operacji do wykonania-------------------------------------------------
        panelOperation.setLayout(createFormLayoutBottom(app.Window.windowWidth, Window.windowHeight));
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

    /**
     * Dodanie nasluchiwacza dla komponentow
     * @param actionListener obiekt nasluchiwacza
     */
    public void addComboBoxListener(ActionListener actionListener){

        calculate.addActionListener(actionListener);
        selectOperation.addActionListener(actionListener);

    }

}
