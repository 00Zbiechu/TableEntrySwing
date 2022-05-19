package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Klasa <code>AboutWindow</code> pokazująca okno z informacjami o autorze i aplikacji, rozszerzająca klasę <code>JDialog</code> i implementująca interfejs <code>ActionListener</code>
 * @author Mateusz Zbiewski
 * @version 1.0
 */
public class AboutWindow extends JDialog implements ActionListener {

    /**
     * Zmienna przechowujace szerokosc okna <code>AboutWindow</code>
     */
    private int aboutWindowWidth;
    /**
     * Zmienna przechowujace wysokosc okna <code>AboutWindow</code>
     */
    private int aboutWindowHeight;

    /**
     * Pola klasy bedace obiektami JPanel przechowujace panele z komponentami GUI
     */
    private JPanel panelMain,
                        panelLogo,
                        panelInfo,
                        panelMail,
                        panelOk;

    /**
     * Informacje o autorze i aplikacji, obiekty JLabel
     */
    private JLabel authorIcon,
                   applicationName, applicationVersion,authorName,collageName,
                   authorMail;

    /**
     * Obiekt klasy JButton sluzyacy do obslugi zdarzenia wyjscia z okna <code>AboutWindow</code>
     */
    private JButton exit;


    /**
     * Konstruktor bezparametrowy klasy <code>AboutWindow</code>
     */
    public AboutWindow(){

        this.setTitle("Informacje o programie");
        this.setResizable(false);

        //ustawienie rozmiaru okna AboutWindow
        setSizeAboutWindow(Window.windowWidth,Window.windowHeight);

        //Ustawianie lokalizacji okna na połowę wielkości głównego okna
        setLocationAboutWindow(Window.windowWidth,Window.windowHeight,aboutWindowWidth,aboutWindowHeight);

        //Zamknięcie okna
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }
        });


        initGUI();

    }

    /**
     * Metoda tworzaca komponenty GUI
     */
    private void initGUI(){

        //Tworzenie komponentów GUI
        createButtons();
        createIcons();
        createLabels();

        //Tworzenie paneluMain oraz dodanie do nich paneli pomocniczych, dodanie komponentów do paneli
        //Ustawianie layoutu dla Paneli
        createPanelsAboutWindow();


    }


    /**
     * Ustawienie rozmiaru okna AboutWindow na podstawie wielkości głównego okna Window
     * @param widthMainWindow szerokosc glownego okna
     * @param heightMainWindow wysokosc glownego okna
     */
    private void setSizeAboutWindow(int widthMainWindow,int heightMainWindow){

        this.aboutWindowWidth = widthMainWindow/2;
        this.aboutWindowHeight = heightMainWindow/2;

        setSize(aboutWindowWidth,aboutWindowHeight);


    }


    /**
     * Ustawianie lokalizacji okna AboutWindow na podstawie lokalizacji głównego Okna
     * @param widthMain szerokosc glownego okna
     * @param heightMain wysokosc glownego okna
     * @param widthAbout szerokosc okna AboutWindow
     * @param heightAbout wysokosc okna AboutWindow
     */
    private void setLocationAboutWindow(int widthMain, int heightMain, int widthAbout, int heightAbout){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (screenSizeWidth-widthAbout)/2;
        int locationAboutY = (screenSIzeHeight-heightAbout)/2;

        setLocation(locationAboutX,locationAboutY);


    }


    /**
     * Konstruktor paneli
     * @param color kolor tła panelu
     * @return zwraca utworzony panel
     */
    private JPanel createJPanel(Color color){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);

        return jPanel;

    }


    /**
     * Tworzenie Layoutu dla okna AboutWindow
     * @param windowWidth szerokosc monitora, na ktorym wyswietlana jest aplikacja
     * @param windowHeight wysokosc monitora, na ktorym wyswietlana jest aplikacja
     * @return
     */
    private LayoutManager createFormLayout(int windowWidth,int windowHeight) {

        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.40);
        long windowWTwo = Math.round(windowWidth*0.60);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (dwóch)
        long windowH = Math.round(windowHeight*0.60);
        long windowHTwo = Math.round(windowHeight*0.40);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px";
        String rowConfiguration = windowH+"px, "+windowHTwo+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;


    }


    /**
     * Definicja paneli i dodawanie ich do paneli, a panele do okna
     */
    private void createPanelsAboutWindow(){


        //Tworzenie górnego panelu, który będzie przechowywał komponenty GUI
        panelMain = createJPanel(Color.BLUE);

            //panele pomocnicze, które będą dodane do panelu panelMain
            panelLogo = createJPanel(Color.WHITE);
            panelInfo = createJPanel(Color.WHITE);
            panelMail = createJPanel(Color.LIGHT_GRAY);
            panelOk = createJPanel(Color.LIGHT_GRAY);


            //Dodanie logo autora do panelu panelLogo
            panelLogo.add((Component) authorIcon);

            //Dodanie informacji do panelu panelInfo
            panelInfo.add(applicationName);
            panelInfo.add(applicationVersion);
            panelInfo.add(authorName);
            panelInfo.add(collageName);

            //Dodanie maila do panelu panelMail
            panelMail.add(authorMail);

            //Dodanie butona zniknięcia okna do panelu panelOK
            panelOk.add(exit);


            //FormLayout
            CellConstraints cc = new CellConstraints();

            //Ustawienie layout FormLayout dla panelu panelMain
            panelMain.setLayout(createFormLayout(aboutWindowWidth,aboutWindowHeight));

            //Dodanie paneli do panelu panelMain
            panelMain.add(panelLogo,cc.xy(1,1,CellConstraints.FILL,CellConstraints.FILL));
            panelMain.add(panelInfo,cc.xy(2,1,CellConstraints.FILL,CellConstraints.FILL));
            panelMain.add(panelMail,cc.xy(1,2,CellConstraints.FILL,CellConstraints.FILL));
            panelMain.add(panelOk,cc.xy(2,2,CellConstraints.FILL,CellConstraints.FILL));
            add(panelMain);

            //Ustawienie flowLayout dla 'paneli potomnych'
            panelLogo.setLayout(new FlowLayout());
            panelMail.setLayout(new FlowLayout());
            panelOk.setLayout(new FlowLayout());

            //Ustawienie GridLayout dla panelInfo, żeby wszystko było w osobnych wierszach
            panelInfo.setLayout(new GridLayout(4,1));


    }




    /**
     * Konstruktor obrazków, tylko że JLabel
     * @param file ciąg znakow odpowiadajacy nazwie pliku graficznego
     * @return zwraca obiekt klasy Icon
     */
    private JLabel createJIcon(String file) {

        String name = "/graphics/" +file;
        JLabel icon = new JLabel(new ImageIcon(getClass().getResource(name)));


        return icon;
    }

    /**
     * Definiuje ikony aplikacji
     */
    private void createIcons(){

        //Komponent do panelu panelLogo
        authorIcon = createJIcon("author_logo.png");

    }

    /**
     * Konstruktor Labeli
     * @param label Tekst wyswietlany w JLabel'u
     * @return zwraca obiekt klasy JLabel
     */
    private JLabel createJLabels(String label){

        JLabel jLabel = new JLabel(label+"\n");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        return jLabel;
    }


    /**
     * Definicja Labeli
     */
    private void createLabels(){

        applicationName = createJLabels("Aplikacja PK");
        applicationVersion = createJLabels("wersja 1.0.1");
        authorName = createJLabels("Mateusz Zbiewski");
        collageName = createJLabels("Politechnika Koszalińska");



        //Komponent do panelu panelMail
        authorMail = createJLabels("mail@tu.koszalin.pl");


    }

    /**
     * Konstruktor przyciskow
     * @param label Tekst wyswietlany na przycisku
     * @return zwraca obiekt klasy JButton
     */
    private JButton createJButton(String label){

        JButton jbutton = new JButton(label);
        jbutton.addActionListener(this);
        jbutton.setBackground(Color.WHITE);

        return jbutton;

    }


    /**
     * Definicja przyciskow
     */
    private void createButtons(){

        //Przycisk do panelu panelOK-zamknięcie okna (zniknięcia okna)
        exit = createJButton("OK");

    }


    /**
     * Metoda obslugujaca zdarzenie akcji
     * @param e obiekt klasy nasluchujacej <code>ActionListener</code>
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==exit){
            setVisible(false);
        }


    }
}
