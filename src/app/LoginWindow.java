package app;

import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JDialog implements ActionListener {

    private int loginWindowWidth;
    private int loginWindowHeight;

    private JPanel loginPanel;

    private JLabel loginMessage,labelForLogin,labelForPassword;
    private JTextField loginUser;
    private JPasswordField passwordUser;
    private JButton confirm,exit;

    LoginWindow(){

        setTitle("Logowanie");
        setResizable(false);

        //Ustawienie wielkości okna LoginWindow
        setSizeLoginWindow(Window.windowWidth,Window.windowHeight);

        //Ustawienie lokalizacji okna
        setLocationLoginWindow(Window.windowWidth,Window.windowHeight,loginWindowWidth,loginWindowHeight);

        initGUI();

    }

    //Wywołanie metod tworzących GUI
    private void initGUI(){


        //Tworzenie pola tekstowego do wprowadzanie loginu dla usera
        createTextFiled();

        //Tworzenie pola do wprowadzania hasła usera
        createPasswordFiled();

        //Tworzenie etykiet tekstowych
        createLabels();

        //Tworzenie przycisków
        createButtons();

        //Tworzenie paneli i dodawanie do nich komponentów GUI
        createPanels();

    }


    //Ustawienie rozmiaru okna LoginWindow na podstawie wielkości głównego okna Window
    private void setSizeLoginWindow(int widthMainWindow,int heightMainWindow){

        this.loginWindowWidth = widthMainWindow/2;
        this.loginWindowHeight = heightMainWindow/2;

        setSize(loginWindowWidth, loginWindowHeight);


    }

    //Ustawianie lokalizacji okna LoginWindow na podstawie lokalizacji głównego Okna
    private void setLocationLoginWindow(int widthMain, int heightMain, int widthLogin, int heightLogin){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (screenSizeWidth-widthLogin)/2;
        int locationAboutY = (screenSIzeHeight-heightLogin)/2;

        setLocation(locationAboutX,locationAboutY);


    }


    //Konstruktor paneli
    private JPanel createJPanel(Color color){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);

        return jPanel;

    }

    private void createPanels(){

        //Stworzeni panelu loginPanel i dodanie do niego stworzonego form layoutu
        this.loginPanel = createJPanel(Color.WHITE);
        loginPanel.setLayout(createFormLayout(loginWindowWidth,loginWindowHeight));

        //Dodanie komponentów do loginPanel
        loginPanel.add(loginMessage);
        loginPanel.add(labelForLogin);
        loginPanel.add(labelForPassword);
        loginPanel.add(loginUser);
        loginPanel.add(passwordUser);
        loginPanel.add(confirm);
        loginPanel.add(exit);



        //Dodanie loginPanel do głównego okna
        this.add(loginPanel);



    }


    private JButton createJButton(String label){

        JButton jbutton = new JButton(label);
        jbutton.addActionListener((ActionListener) this);

        return jbutton;

    }

    private void createButtons(){

        this.confirm = createJButton("Potwierdź");
        this.exit = createJButton("Anuluj");

    }

    private JLabel createJLabels(String label){

        JLabel jLabel = new JLabel(label+"\n");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        return jLabel;
    }

    private void createLabels(){

        this.loginMessage = createJLabels("Proszę podać dane logowania");
        this.labelForLogin = createJLabels("Login: ");
        this.labelForPassword = createJLabels("Hasło: ");

    }


    private JTextField createJTextFiled(){

        JTextField jTextField = new JTextField();

        return jTextField;

    }

    private void createTextFiled(){

        this.loginUser = createJTextFiled();

    }


    private JPasswordField createJPasswordFiled(){

        JPasswordField jPasswordField = new JPasswordField();

        return jPasswordField;
    }

    private void createPasswordFiled(){

        this.passwordUser = createJPasswordFiled();

    }


    //Tworzenie Layoutu dla okna LoginWindow
    private LayoutManager createFormLayout(int windowWidth,int windowHeight) {

        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.30);
        long windowWTwo = Math.round(windowWidth*0.70);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (czterech)
        long windowH = Math.round(windowHeight*0.25);
        long windowHTwo = Math.round(windowHeight*0.25);
        long windowHThree = Math.round(windowHeight*0.25);
        long windowHFour = Math.round(windowHeight*0.25);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px";
        String rowConfiguration = windowH+"px, "+windowHTwo+"px"+windowHThree+"px, "+windowHFour+"px";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
