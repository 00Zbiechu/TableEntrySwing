package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutWindow extends JDialog implements ActionListener {

    private int aboutWindowWidth;
    private int aboutWindowHeight;

    //Panele dzielące okno
    private JPanel panelMain,
                        panelLogo,
                        panelInfo,
                        panelMail,
                        panelOk;

    //Informacje o autorze i aplikacji
    private JLabel authorIcon,
                   applicationName, applicationVersion,authorName,collageName,
                   authorMail;

    //Przycisk wyjścia
    private JButton exit;




    AboutWindow(){

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

    //Tworzenie GUI
    private void initGUI(){

        //Tworzenie komponentów GUI
        createButtons();
        createIcons();
        createLabels();

        //Tworzenie paneluMain oraz dodanie do nich paneli pomocniczych, dodanie komponentów do paneli
        //Ustawianie layoutu dla Paneli
        createPanelsAboutWindow();


    }

    //Ustawienie rozmiaru okna AboutWindow na podstawie wielkości głównego okna Window
    private void setSizeAboutWindow(int widthMainWindow,int heightMainWindow){

        this.aboutWindowWidth = widthMainWindow/2;
        this.aboutWindowHeight = heightMainWindow/2;

        setSize(aboutWindowWidth,aboutWindowHeight);


    }

    //Ustawianie lokalizacji okna AboutWindow na podstawie lokalizacji głównego Okna
    private void setLocationAboutWindow(int widthMain, int heightMain, int widthAbout, int heightAbout){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (screenSizeWidth-widthAbout)/2;
        int locationAboutY = (screenSIzeHeight-heightAbout)/2;

        setLocation(locationAboutX,locationAboutY);


    }

    //Konstruktor paneli
    private JPanel createJPanel(Color color){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);

        return jPanel;

    }

    //Tworzenie Layoutu dla okna AboutWindow
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




    //Konstruktor obrazków, tylko że JLabel
    private JLabel createJIcon(String file) {

        String name = "/grafika/"+file;
        JLabel icon = new JLabel(new ImageIcon(getClass().getResource(name)));


        return icon;
    }

    private void createIcons(){

        //Komponent do panelu panelLogo
        authorIcon = createJIcon("author_logo.jpg");

    }


    private JLabel createJLabels(String label){

        JLabel jLabel = new JLabel(label+"\n");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        return jLabel;
    }





    private void createLabels(){

        applicationName = createJLabels("Aplikacja PK");
        applicationVersion = createJLabels("wersja 1.0.1");
        authorName = createJLabels("Mateusz Zbiewski");
        collageName = createJLabels("Politechnika Koszalińska");



        //Komponent do panelu panelMail
        authorMail = createJLabels("mail@tu.koszalin.pl");


    }

    private JButton createJButton(String label){

        JButton jbutton = new JButton(label);
        jbutton.addActionListener(this);

        return jbutton;

    }


    private void createButtons(){

        //Przycisk do panelu panelOK-zamknięcie okna (zniknięcia okna)
        exit = createJButton("OK");

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==exit){
            setVisible(false);
        }


    }
}
