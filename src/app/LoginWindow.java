package app;

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

        this.loginPanel = createJPanel(Color.WHITE);

        //Dodanie przycisków do loginPanel
        loginPanel.add(labelForLogin);
        loginPanel.add(labelForPassword);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
