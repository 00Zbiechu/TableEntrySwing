package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {

    private int windowWidth;
    private int windowHeight;

    private String etykietyMenu[] = {"Pliki","Edytuj","Widok","Pomoc"};
    private String etykietyFileMenu[] = {"Logowanie","Wylogowanie","Drukuj","Zamknij"};
    private String etykietyEditMenu[] = {"Kopiuj","Wytnij","Cofnij"};
    private String etykietyViewMenu[] = {"Ukryj pasek statusu","Ukryj pasek narzędziowy"};
    private String etykietyHelpMenu[] = {"Ustawienia","Informacje o programie"};

    //zmienne przechowujące komponenty menu
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;

    //MenuItems
    private JMenuItem  fileLoginMenuItem, fileLogoutMenuItem, filePrintMenuItem, fileExitMenuItem,
            helpSettingsMenuItem, helpAboutMenuItem, editUndoMenuItem, editCutMenuItem, editCopyMenuItem;

    //ViewMenuItemsCheckbox
    private JCheckBoxMenuItem viewStatusBarMenuItem, viewJToolBarMenuItem;

    //toolbar
    private JToolBar toolBar;

    //toolbar items
    private JButton saveButton, printButton, logoutButton, closeButton, settingsButton, infoButton ;


    //Ikony do toolBara
    private Icon iconSave, iconPrint, iconLogout, iconClose, iconSettings, iconInfo;



    //konstruktor klasy Window
    Window(){

        //tytuł aplikacji
        setTitle("Aplikacja");


        //Rozmiar i położenie okna
        setDimensionWindow(getResolution());
        setLocationWindow(getResolution());


        //Operacja zamknięcia okna
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Nie zamykaj okna
        //Metoda czeka na zdarzenie okna
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //metoda zamknięcia okna
                closeWindow();
            }
        });

        //Metoda tworząca menuItems
        createMenuItem();

        //tworzenie menu aplikacji
        createMenu();

        //Tworzenie ikon dla przyciskow toolbara
        createIcon();

        //Metoda tworząca przyciski do toolbara
        createToolbarButton();

        //tworzenie toolbaru dla aplikacji
        createToolBar();

    }

    //metoda pobierająca rozmiar okna;
    private Dimension getResolution() {

        //klasa toolkit zawiera metodę, umożliwiającą pobranie rozmiaru okna
        Toolkit kit = getToolkit();
        //klasa Dimension zawiera pola height i width, dzięki czemu możemy w niej przechować wymiary okna
        Dimension size = kit.getScreenSize();

       return size;

    }

    //metoda ustawiająca rozmiar okna aplikacji na połowę rozmiaru okna i przypisująca te wartości do zmiennych
    private void setDimensionWindow(Dimension resolution){

        this.windowWidth = resolution.width/2;
        this.windowHeight = resolution.height/2;
        setSize(windowWidth,windowHeight);
        setResizable(false);

        //Pokazuje ile wynosi rozmiar okna
        //System.out.println(windowWidth+" "+windowHeight);


    }

    //metoda ustawiająca miejsce ukazywania się okna programu na podstawie rozdzielczości ekranu
    private void setLocationWindow(Dimension resolution){

        int X = resolution.width/4;
        int Y = resolution.height/4;

        setLocation(X,Y);


    }


    //Metoda zamykająca okno
    private void closeWindow(){
        //Wywołanie metody showOptionDialog, która zwraca wartość wyboru użytkownika
        int value = JOptionPane.showOptionDialog(
                //argumenty metody showOptionDialog
                this, //rodzic, obszar w którym, pojawi się ramka w tym przypadku JFrame
                "Czy chcesz zamknąć aplikacje?",
                "Zakończ",
                JOptionPane.YES_NO_CANCEL_OPTION, //liczba całkowita oznaczająca opcje dostępne w oknie dialogowym == 1;
                JOptionPane.QUESTION_MESSAGE, //rodzaj komunikatu
                null, //ikona
                new String[] { "Tak", "Nie"}, //opcje
                "Tak" //wartość domyślna
        );

        //System.out.println(JOptionPane.YES_NO_OPTION); == 0
        if (value == JOptionPane.YES_NO_OPTION) {
            dispose();
            System.exit(0);
        }
    }


    //metoda będąca konstruktorem pól JMenu
    private JMenu createJMenu(String name,int keyEvent){

        JMenu jMenu = new JMenu(name);
        jMenu.setMnemonic(keyEvent);

        return jMenu;
    }

    //Tworzenie pól menu i dodanie ich do menuBar'a
    private void createMenu(){
        menuBar = new JMenuBar();

        fileMenu = createJMenu(etykietyMenu[0], KeyEvent.VK_P);
        editMenu = createJMenu(etykietyMenu[1], KeyEvent.VK_E);
        viewMenu = createJMenu(etykietyMenu[2], KeyEvent.VK_W);
        helpMenu = createJMenu(etykietyMenu[3], KeyEvent.VK_P);



        //Dodanie do paska menu
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);



        //Dodanie elementów do fileMenu
        fileMenu.add(fileLoginMenuItem);
        fileMenu.add(fileLogoutMenuItem);
        fileMenu.add(filePrintMenuItem);
        fileMenu.add(fileExitMenuItem);

        //Dodanie elementów do editMenu
        editMenu.add(editCopyMenuItem);
        editMenu.add(editCutMenuItem);
        editMenu.add(editUndoMenuItem);

        //Dodanie elementów do helpMenu
        helpMenu.add(helpSettingsMenuItem);
        helpMenu.add(helpAboutMenuItem);


        //Dodanie elementów do viewMenu
        viewMenu.add(viewStatusBarMenuItem);
        viewMenu.add(viewJToolBarMenuItem);


        this.setJMenuBar(menuBar);
    }

    //Konstruktor pojedynczego elementu menu
    private JMenuItem createJMenuItem(String name,KeyStroke keyStroke){

        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(keyStroke);

        return menuItem;

    }

    private JCheckBoxMenuItem createJMenuCheckBoxItem(String name, boolean activated,KeyStroke keyStroke){

        JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(name);

        jCheckBoxMenuItem.setState(activated);
        jCheckBoxMenuItem.addActionListener(this);
        jCheckBoxMenuItem.setAccelerator(keyStroke);

        return jCheckBoxMenuItem;

    }

    //Tworzenie item'ów menu
    private void createMenuItem(){

        //fileMenu
        fileLoginMenuItem = createJMenuItem(etykietyFileMenu[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));

        fileLogoutMenuItem = createJMenuItem(etykietyFileMenu[1],
                KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));

        filePrintMenuItem = createJMenuItem(etykietyFileMenu[2],
                KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));

        fileExitMenuItem = createJMenuItem(etykietyFileMenu[3],
                KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));


        //viewMenu
        viewJToolBarMenuItem = createJMenuCheckBoxItem(etykietyViewMenu[0],false,
                KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.ALT_MASK));

        viewStatusBarMenuItem = createJMenuCheckBoxItem(etykietyViewMenu[1],false,
                KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.ALT_MASK));


        //editMenu
        editCopyMenuItem = createJMenuItem(etykietyEditMenu[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.SHIFT_MASK));

        editCutMenuItem = createJMenuItem(etykietyEditMenu[1],
                KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.SHIFT_MASK));

        editUndoMenuItem = createJMenuItem(etykietyEditMenu[2],
                KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.SHIFT_MASK));

        //helpMenu
        helpSettingsMenuItem = createJMenuItem(etykietyHelpMenu[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));

        helpAboutMenuItem = createJMenuItem(etykietyHelpMenu[1],
                KeyStroke.getKeyStroke(KeyEvent.VK_0,ActionEvent.ALT_MASK));


    }


    private JToolBar createJToolBar(boolean floatable,boolean borderPainted, int howManyRows, int howManyColumns){

        JToolBar jToolBar = new JToolBar();

        jToolBar.setFloatable(floatable);
        jToolBar.setBorderPainted(borderPainted);
        jToolBar.setLayout(new GridLayout(howManyRows,howManyColumns));


        return jToolBar;
    }

    private void createToolBar(){

        //3 wiersze są puste na przyszłość
        toolBar = createJToolBar(false,false,8,1);


        //Dodanie elementów do Toolbara
        toolBar.add(saveButton);
        toolBar.add(printButton);
        toolBar.add(logoutButton);
        toolBar.add(closeButton);
        toolBar.add(settingsButton);
        toolBar.add(infoButton);




        //Ustawienie toolbar'a w oknie
        this.add(toolBar,BorderLayout.WEST);


    }

    //Konstruktor przycisków do toolbar'a
    private JButton createJToolBarButton(String tooltip,Icon icon){

        JButton jButton = new JButton("",icon);
        jButton.setToolTipText(tooltip);
        jButton.addActionListener(this);
        jButton.setEnabled(true);

        return jButton;
    }

    //Tworzenie elementów Toolbar Button
    private void createToolbarButton(){

        saveButton = createJToolBarButton("Zapisz",iconSave);
        printButton = createJToolBarButton("Drukuj",iconPrint);
        logoutButton = createJToolBarButton("Wyloguj",iconLogout);
        closeButton = createJToolBarButton("Zamknij",iconClose);
        settingsButton = createJToolBarButton("Ustawienia",iconSettings);
        infoButton = createJToolBarButton("Informacje o programie",iconInfo);

    }


    //Konstruktor ikon
    private Icon createJIcon(String file) {
        String name = "/grafika/"+file;
        Icon icon = new ImageIcon(getClass().getResource(name));

        return icon;
    }

    private void createIcon(){


        //Ikony do Toolbara
        iconPrint = createJIcon("print.jpg");
        iconSave = createJIcon("save.jpg");
        iconClose = createJIcon("close.jpg");
        iconLogout = createJIcon("logout.jpg");
        iconInfo = createJIcon("about.jpg");
        iconSettings = createJIcon("settings.jpg");

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
