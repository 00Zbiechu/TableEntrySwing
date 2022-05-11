package app;

import app.TableMVC.ModelTable;
import com.l2fprod.common.swing.JButtonBar;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import static app.TableMVC.ControllerTable.modelTable;
import static app.TableMVC.ControllerTable.viewTable;

/**
 * Klasa <code>Window</code> zawierajaca GUI aplikacji dziedziczaca po klasie <code>JFrame</code> oraz badaca implementacja interfejsu <code>ActionListener</code>
 */
public class Window extends JFrame implements ActionListener {

    /**
     * Zmienna statyczna <code>windowWidth</code> przechowujaca szerokość wyświetlacza, na którym uruchamiana jest aplikacja
     */
    public static int windowWidth;
    /**
     * Zmienna statyczna <code>windowHeight</code> przechowujaca szerokość wyświetlacza, na którym uruchamiana jest aplikacja
     */
    public static int windowHeight;

    /**
     * Zmienna statyczna <code>windowX</code> przechowujaca pozycję aplikacji na monitorze w płaszczyźnie poziomej.
     */
    public static int windowX;
    /**
     * Zmienna statyczna <code>windowY</code> przechowujaca pozycję aplikacji na monitorze w płaszczyźnie pionowej.
     */
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
            helpSettingsMenuItem, helpAboutMenuItem, editSaveItem, editClearItem;

    //ViewMenuItemsCheckbox
    private JCheckBoxMenuItem viewStatusBarMenuItem, viewJToolBarMenuItem;

    //toolbar
    private JToolBar toolBar;

    /**
     * Zmienna statyczna przechowujaca pole typu JButton <code>saveButton</code>
     */
    public static JButton saveButton;
    /**
     * Zmienna statyczna przechowujaca pole typu JButton <code>printButton</code>
     */
    public static JButton printButton;

    private JButton logoutButton, closeButton, helpButton, infoButton ;

    //Ikony do toolBara
    private Icon iconSave, iconPrint, iconLogout, iconClose, iconHelp, iconInfo;

    //nawigacja
    private JButtonBar nawigacja;
    private Icon iconAdd, iconWhere, iconAccept, iconTrash, iconMath, iconEqual;
    private JButton addButton,whereButton,acceptButton,trashButton,mathButton,resultButton;


    /**
     * Zmienna statyczna przechowujaca pole typu StatusBar <code>statusBar</code>
     */
    public static StatusBar statusBar;

    //Okno pomocy
    private HelpWindow helpWindow;

    //Okno o aplikacji
    private AboutWindow aboutWindow;

    /**
     * Zmienna statyczna przechowujaca pole typu LoginWindow <code>loginWindow</code>
     */
    public static LoginWindow loginWindow;

    //Główne okno będzie w scrollPanelu
    private JScrollPane scrollableArea;

    /**
     * Zmienna statyczna przechowujaca obiekt <code>loginRequired</code> klasy <code>LockWindow</code>.
     */
    public static LockWindow loginRequired;

    //Centralny panel aplikacji-statyczne, żeby mieć dostęp z klasy LoginWindow
    /**
     * Zmienna statyczna przechowujaca obiekt <code>centralPanel</code> klasy <code>CentralPanel</code>.
     */
    public static CentralPanel centralPanel;



    /**
     * Konstruktor bezparametrowy klasy <code>Window</code>
     */
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

        //Metoda tworząca przyciski do buttonBara
        createButtonBarButton();

        //tworzenie nawigacji jButtonBar
        createButtonBar();

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
        setResizable(false);

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
            Main.logger.info("Koniec działania aplikacji");
            Main.myLogger.info("Koniec działania aplikacji");
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

        jButton.addActionListener(this);

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


    private JButtonBar createJButtonBar(){

        JButtonBar jButtonBar = new JButtonBar();
            jButtonBar.setPreferredSize(new Dimension((int) (windowWidth*0.05), (int) (windowHeight*0.80)));

        return jButtonBar;

    }

    //Konstruktor przycisków do toolbar'a
    private JButton createJButtonBarButton(String tooltip,Icon icon){

        JButton jButton = new JButton("",icon);
        jButton.setToolTipText(tooltip);
        jButton.addActionListener(this);
        jButton.setEnabled(true);
        jButton.setPreferredSize(new Dimension((int) (windowWidth*0.03), (int) (windowHeight*0.10)));

        return jButton;
    }


    private void createButtonBarButton(){

        addButton = createJButtonBarButton("Idź do: Dodaj",iconAdd);
        whereButton = createJButtonBarButton("Idź do: Pozycja",iconWhere);
        acceptButton = createJButtonBarButton("Idź do: Wprowadź",iconAccept);
        trashButton = createJButtonBarButton("Idź do: Usuń",iconTrash);
        mathButton = createJButtonBarButton("Idź do: Oblicz",iconMath);
        resultButton = createJButtonBarButton("Idź do: Wynik",iconEqual);

    }

    private void createButtonBar(){

        this.nawigacja = createJButtonBar();
            nawigacja.setOrientation(SwingConstants.VERTICAL);

            //Dodawanie do JButtonBara
            nawigacja.add(addButton);
            nawigacja.add(whereButton);
            nawigacja.add(acceptButton);
            nawigacja.add(trashButton);
            nawigacja.add(mathButton);
            nawigacja.add(resultButton);


        add(nawigacja,BorderLayout.WEST);

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

            //DO nawigacji
            iconAdd = createJIcon("min_add.jpg");
            iconWhere = createJIcon("ruler.png");
            iconAccept = createJIcon("accept.jpg");
            iconTrash = createJIcon("trash.png");
            iconMath = createJIcon("math.png");
            iconEqual = createJIcon("equal.png");

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

            this.scrollableArea = new JScrollPane(panelMain);
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
            Main.logger.info("Logowanie False");
            Main.myLogger.info("Logowanie False");


        }else if(e.getSource()==filePrintMenuItem || e.getSource()==printButton){

            ModelTable.printTable(viewTable.table);

        }
        //--------------------------------------------------EditMenu
        else if(e.getSource()==editClearItem){

            modelTable.fillTableZeros();
            viewTable.table.setModel(modelTable);
            viewTable.table.repaint();


        }else if(e.getSource()==editSaveItem || e.getSource()==this.saveButton){

            try {
                modelTable.saveFile(this);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewTable,"Niepowodzenie zapisywania pliku!");
                Main.logger.warn("Niepowodzenie zapisywania pliku!");
                Main.myLogger.warn("Niepowodzenie zapisywania pliku!");
            }

        }
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
            Main.logger.info("Uruchomienie okna O programie");
            Main.myLogger.info("Uruchomienie okna O programie");

        }else if(e.getSource()==helpSettingsMenuItem || e.getSource()== helpButton){

            //Pokazane okna HelpWindow
            helpWindow.setVisible(true);
            statusBar.setStatusAndValueOfApplication("Uruchomienie okna","Pomoc");
            Main.logger.info("Uruchomienie okna pomoc");
            Main.myLogger.info("Uruchomienie okna pomoc");

        //Nawigacja -------------------------------------------Nawigacja
        }else if(e.getSource()==addButton){

            scrollableArea.getVerticalScrollBar().setValue(0);
            Window.statusBar.setStatusAndValueOfApplication("Skok","Wprowadzanie");
            Main.logger.info("Skok do wprowadzanie");
            Main.myLogger.info("Skok do wprowadzanie");

        }else if(e.getSource()==whereButton){

            scrollableArea.getVerticalScrollBar().setValue(0);
            Window.statusBar.setStatusAndValueOfApplication("Skok","Pozycja");
            Main.logger.info("Skok do pozycja");
            Main.myLogger.info("Skok do pozycja");


        }else if(e.getSource()==acceptButton){

            scrollableArea.getVerticalScrollBar().setValue(50);
            Window.statusBar.setStatusAndValueOfApplication("Skok","Dodawanie");
            Main.logger.info("Skok do dodawanie");
            Main.myLogger.info("Skok do dodawanie");


        }else if(e.getSource()==trashButton){

            scrollableArea.getVerticalScrollBar().setValue(50);
            Window.statusBar.setStatusAndValueOfApplication("Skok","Usuwanie");
            Main.logger.info("Skok do usuwanie");
            Main.myLogger.info("Skok do usuwanie");

        }else if(e.getSource()==mathButton){

            scrollableArea.getVerticalScrollBar().setValue(80);
            Window.statusBar.setStatusAndValueOfApplication("Skok","Obliczanie");
            Main.logger.info("Skok do obliczanie");
            Main.myLogger.info("Skok do obliczanie");

        }else if(e.getSource()==resultButton){

            scrollableArea.getVerticalScrollBar().setValue(80);
            Window.statusBar.setStatusAndValueOfApplication("Skok","Wynik");
            Main.logger.info("Skok do wynik");
            Main.myLogger.info("Skok do wynik");

        }



    }
}
