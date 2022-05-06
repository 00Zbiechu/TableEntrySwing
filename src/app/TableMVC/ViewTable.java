package app.TableMVC;

import app.Window;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewTable extends JPanel {

    //Panele, w których będzie tablica i operacje
    private JPanel panelTable,panelAction;

    //Tablica
    private JScrollPane scrollTable; //Tablica będzie w scrollPane
    public static JTable tableView;

    //Przyciski znajdujące się obok tablicy
    public static JButton clearButton, commitButton, saveButton;
    private Icon iconClearButton, iconCommitButton, iconSaveButton;


    public ViewTable(){

        initGUI();

    }

    public void initGUI(){

        createPanel();
        createTable();
        createButton();
        createIcon();
        addComponentsAndSetLayout();

    }

    private JPanel createJPanel(){

        JPanel jPanel = new JPanel();

        return jPanel;

    }

    private void createPanel(){

        this.panelTable = createJPanel();
        this.panelAction = createJPanel();

    }


    private LayoutManager createFormLayoutMid(int windowWidth, int windowHeight){


        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.60);
        long windowWTwo = Math.round(windowWidth*0.20);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (jeden)
        long windowH = Math.round(windowHeight*0.28);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px";
        String rowConfiguration = windowH+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;

    }

    private void addComponentsAndSetLayout(){

        //Obiekt służący do obsługi komórek
        CellConstraints cc = new CellConstraints();

        //Wypełnianie panelu akcji przyciskami i nadanie mu stylu, żeby układały się w 1 kolumnie
        panelAction.setLayout(new GridLayout(3,1,0,5));
        panelAction.add(clearButton);
        panelAction.add(commitButton);
        panelAction.add(saveButton);

        //Panel środkowy ---------------------------------------------------------------------
        this.setLayout(createFormLayoutMid(Window.windowWidth,Window.windowHeight));
        this.add(scrollTable,cc.xy(1,1, CellConstraints.FILL,CellConstraints.TOP));
        this.add(panelAction,cc.xy(2,1,CellConstraints.CENTER,CellConstraints.TOP));



    }


    private void createTable(){

        this.tableView = new JTable(

        );

        scrollTable = new JScrollPane(tableView);

    }

    private JButton createJButton(String text,Icon icon){

        JButton jButton = new JButton(text,icon);
        jButton.setName(text);
        jButton.setBackground(Color.WHITE);

        return jButton;

    }

    private void createButton(){

        this.clearButton = createJButton("Wyczyść",iconClearButton);
        this.commitButton = createJButton("Wprowadź",iconCommitButton);
        this.saveButton = createJButton("Zapisz",iconSaveButton);


    }

    //Konstruktor ikon
    private Icon createJIcon(String file) {
        String name = "/grafika/"+file;
        Icon icon = new ImageIcon(getClass().getResource(name));

        return icon;
    }

    private void createIcon(){

        this.iconClearButton = createJIcon("min_clear.jpg");
        this.iconCommitButton = createJIcon("min_insert.png");
        this.iconSaveButton = createJIcon("min_print.jpg");

    }

    public void addTableListener(ActionListener listener){

        clearButton.addActionListener(listener);
        commitButton.addActionListener(listener);
        saveButton.addActionListener(listener);

    }

}

