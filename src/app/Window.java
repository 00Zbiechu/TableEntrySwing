package app;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Window extends JFrame implements ActionListener {

    public static int windowWidth;
    public static int windowHeight;

    public static int windowX;
    public static int windowY;


    private String etykietyMenu[] = {"Pliki","Edytuj","Widok","Pomoc"};
    private String etykietyFileMenu[] = {"Logowanie","Wylogowanie","Drukuj","Zamknij"};
    private String etykietyEditMenu[] = {"Wyczyść","Wprowadź","Zapisz"};
    private String etykietyViewMenu[] = {"Ukryj pasek statusu","Ukryj pasek narzędziowy"};
    private String etykietyHelpMenu[] = {"Pomoc","Informacje o programie"};

    //zmienne przechowujące komponenty menu
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;

    //MenuItems
    private JMenuItem  fileLoginMenuItem, fileLogoutMenuItem, filePrintMenuItem, fileExitMenuItem,
            helpSettingsMenuItem, helpAboutMenuItem, editSaveItem, editCommitItem, editClearItem;

    //ViewMenuItemsCheckbox
    private JCheckBoxMenuItem viewStatusBarMenuItem, viewJToolBarMenuItem;

    //toolbar
    private JToolBar toolBar;

    //toolbar items
    private JButton saveButton, printButton, logoutButton, closeButton, helpButton, infoButton ;


    //Ikony do toolBara
    private Icon iconSave, iconPrint, iconLogout, iconClose, iconHelp, iconInfo;

    //Pasek statusu
    public static StatusBar statusBar;

    //Okno pomocy
    private HelpWindow helpWindow;

    //Okno o aplikacji
    private AboutWindow aboutWindow;

    //Okno Logowania
    public static LoginWindow loginWindow;

    //Panel blokady aplikacji-statyczne, żeby mieć dostęp z klasy LoginWindow
    public static LockWindow loginRequired;

    //Centralny panel aplikacji-statyczne, żeby mieć dostęp z klasy LoginWindow
    public static CentralPanel centralPanel;


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


        //Tworzenie GUI
        initGUI();

        //Ustawianie statusu aplikacji-start
        statusBar.setStatusAndValueOfApplication("Uruchomiono","True");


        //Sprawdzenie, czy można się zalogować
        isLaunched(statusBar.getStatus(),statusBar.getValue());


    }

    //Metoda służąca do wywoływania metod tworzących GUI
    private void initGUI(){

        //Metoda tworząca menuItems
        createMenuItem();

        //tworzenie menu aplikacji
        createMenu();

        //Tworzenie ikon dla przycisków toolbara
        createIcon();

        //Metoda tworząca przyciski do toolbara
        createToolbarButton();

        //tworzenie toolbaru dla aplikacji
        createToolBar();

        //Dodane stopki (pasek statusu aplikacji)
        createFooter();

        //Utworzenie wszystkich okien pomocniczych
        launchWindows();


        //Stworzenie panelu centralnego aplikacji
        createScrollCentralPanel();


    }

    //metoda z interfejsu DimensionWindow, pobierająca rozmiar okna;
    private Dimension getResolution() {

        //klasa toolkit zawiera metodę, umożliwiającą pobranie rozmiaru okna
        Toolkit kit = getToolkit();
        //klasa Dimension zawiera pola height i width, dzięki czemu możemy w niej przechować wymiary okna
        Dimension size = kit.getScreenSize();

       return size;

    }

    //metoda ustawiająca rozmiar okna aplikacji na połowę rozmiaru okna i przypisująca te wartości do zmiennych
    private void setDimensionWindow(Dimension resolution){

        windowWidth = resolution.width/2;
        windowHeight = resolution.height/2;
        setSize(windowWidth,windowHeight);
        setResizable(true);

        //Pokazuje ile wynosi rozmiar okna
        //System.out.println(windowWidth+" "+windowHeight);

    }

    //metoda ustawiająca miejsce ukazywania się okna programu na podstawie rozdzielczości ekranu
    private void setLocationWindow(Dimension resolution){

        int X = resolution.width/4;
        int Y = resolution.height/4;

        windowX = X;
        windowY = Y;

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
        editMenu.add(editClearItem);
        editMenu.add(editSaveItem);

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
                KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.ALT_MASK));

        fileExitMenuItem = createJMenuItem(etykietyFileMenu[3],
                KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));


        //viewMenu
        viewJToolBarMenuItem = createJMenuCheckBoxItem(etykietyViewMenu[1],false,
                KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.ALT_MASK));

        viewStatusBarMenuItem = createJMenuCheckBoxItem(etykietyViewMenu[0],false,
                KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.ALT_MASK));


        //editMenu
        editClearItem = createJMenuItem(etykietyEditMenu[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.SHIFT_MASK));


        editSaveItem = createJMenuItem(etykietyEditMenu[2],
                KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.SHIFT_MASK));

        //helpMenu
        helpSettingsMenuItem = createJMenuItem(etykietyHelpMenu[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));

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
        toolBar = createJToolBar(false,true,1,6);


        //Dodanie elementów do Toolbara
        toolBar.add(saveButton);
        toolBar.add(printButton);
        toolBar.add(logoutButton);
        toolBar.add(closeButton);
        toolBar.add(helpButton);
        toolBar.add(infoButton);




        //Ustawienie toolbar'a w oknie
        this.add(toolBar,BorderLayout.NORTH);


    }

    //Konstruktor przycisków do toolbar'a
    private JButton createJToolBarButton(String tooltip,Icon icon){

        JButton jButton = new JButton("",icon);
        jButton.setToolTipText(tooltip);
        jButton.addActionListener(this);
        jButton.setEnabled(true);
        jButton.setBackground(Color.WHITE);

        return jButton;
    }

    //Tworzenie elementów Toolbar Button
    private void createToolbarButton(){

        saveButton = createJToolBarButton("Zapisz",iconSave);
        printButton = createJToolBarButton("Drukuj",iconPrint);
        logoutButton = createJToolBarButton("Wyloguj",iconLogout);
        closeButton = createJToolBarButton("Zamknij",iconClose);
        helpButton = createJToolBarButton("Pomoc", iconHelp);
        infoButton = createJToolBarButton("Informacje o programie",iconInfo);

    }


    //Konstruktor ikon
    private Icon createJIcon(String file) {
        String name = "/grafika/"+file;
        Icon icon = new ImageIcon(getClass().getResource(name));

        return icon;
    }

    private void createIcon(){

            iconPrint = createJIcon("print.jpg");
            iconSave = createJIcon("save.jpg");
            iconClose = createJIcon("close.jpg");
            iconLogout = createJIcon("logout.jpg");
            iconInfo = createJIcon("about.jpg");
            iconHelp = createJIcon("settings.jpg");

    }

    private StatusBar createStatusBar(){

        StatusBar statusBar = new StatusBar();

        return statusBar;

    }

    private void createFooter(){

        statusBar = createStatusBar();
            add(statusBar,BorderLayout.SOUTH);

    }

    private JPanel createJPanel(Color color){

        JPanel jpanel = new JPanel();
        jpanel.setBackground(color);

        return jpanel;

    }





    private void createScrollCentralPanel(){


            centralPanel = new CentralPanel(); //Tworzenie obiektu klasy CentralPanel
                centralPanel.setVisible(false); //Początkowa widoczność wyłączona
            loginRequired = new LockWindow(); //Tworzenie obiektu klasy LockWindow

        JPanel panelMain = new JPanel();
                panelMain.add(centralPanel);
                panelMain.add(loginRequired);

            JScrollPane scrollableArea = new JScrollPane(panelMain);
            scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollableArea);

    }



    //Utworzenie obiektów Okien, żeby stworzyć je tylko raz
    private void launchWindows(){

        helpWindow = new HelpWindow();
        aboutWindow = new AboutWindow();
        loginWindow = new LoginWindow();

    }

    private void isLaunched(String status, String value){

        if(status.equals("Uruchomiono") && value.equals("True")) {

            loginWindow.setVisible(true);
            loginWindow.setAlwaysOnTop(true);


        }

    }

    //Metoda odpowiedzialna za ukrywanie/pokazywanie toolbara
    private void setVisibilityToolBar(boolean valueOFVisibility){

        if(valueOFVisibility==false){

            toolBar.setVisible(true);
            statusBar.setStatusAndValueOfApplication("Pasek narzędziowy","Widoczny");


        }else if(valueOFVisibility==true){

            toolBar.setVisible(false);
            statusBar.setStatusAndValueOfApplication("Pasek narzędziowy","Ukryty");

        }

    }

    //Metoda odpowiedzialna za ukrywanie/pokazywanie statusbara
    private void setVisibilityStatusBar(boolean valueOFVisibility){

        if(valueOFVisibility==false){

            statusBar.setVisible(true);
            statusBar.setStatusAndValueOfApplication("Pasek statusu","Widoczny");

        }else if(valueOFVisibility==true){

            statusBar.setVisible(false);
            statusBar.setStatusAndValueOfApplication("Pasek statusu","Ukryty");

        }

    }




    //metoda sprawdzająca źródło wywołujące zdarzenie okna
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==fileExitMenuItem || e.getSource()==closeButton){

            //Zamknięcie okna głównego
            closeWindow();

        }else if(e.getSource()==fileLoginMenuItem){

            //Pokazanie okna logowania
            loginWindow.setVisible(true);
            statusBar.setStatusAndValueOfApplication("Logowanie","W trakcie");

        }else if(e.getSource()==logoutButton || e.getSource()==fileLogoutMenuItem){

            //Wylogowanie-ustawienie statusu na Logowanie false i ukrycie panelu Aplikacji, pokazanie panelu blokady
            Window.loginRequired.setVisible(true);
            Window.centralPanel.setVisible(false);
            Window.statusBar.setStatusAndValueOfApplication("Logowanie","False");


        }else if(e.getSource()==printButton || e.getSource()==filePrintMenuItem){

            //Drukowanie
            //Logic.printTable(CentralPanel.table);

        }
        //----------------------------------------------------FileMenu
        else if(e.getSource()==saveButton || e.getSource()==editSaveItem){

            //Zapis do pliku
//            try {
//                Logic.saveFile(this,CentralPanel.table);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }

        }else if(e.getSource()==editClearItem){

            //Czyszczenie tabeli
            //Logic.clear(CentralPanel.table,CentralPanel.resultArea);

        }
        //--------------------------------------------------EditMenu
        else if(e.getSource()==viewJToolBarMenuItem){

            // Ukrycie/Pokazanie toolbar'a
            setVisibilityToolBar(viewJToolBarMenuItem.getState());


        }else if(e.getSource()==viewStatusBarMenuItem){

            //Ukrycie/Pokazanie statusBara
            setVisibilityStatusBar(viewStatusBarMenuItem.getState());

        }
        //--------------------------------------------------HelpMenu
        else if(e.getSource()==helpAboutMenuItem || e.getSource()==infoButton){

            //Pokazanie okna AboutWindow
            aboutWindow.setVisible(true);
            statusBar.setStatusAndValueOfApplication("Uruchomienie okna","O programie");

        }else if(e.getSource()==helpSettingsMenuItem || e.getSource()== helpButton){

            //Pokazane okna HelpWindow
            helpWindow.setVisible(true);
            statusBar.setStatusAndValueOfApplication("Uruchomienie okna","Pomoc");

        }


    }
}
