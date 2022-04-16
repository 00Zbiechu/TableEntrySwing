package app;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JDialog implements ActionListener {

    private int loginWindowWidth;
    private int loginWindowHeight;

    private JPanel loginPanel,loginMessagePanel,loginBottomPanel;

    private JLabel loginMessage,labelForLogin,labelForPassword;
    private JTextField loginUser;
    private JPasswordField passwordUser;
    private JButton confirm,exit;

    //Dane logowania
    private String login = "root";
    private String password = "1234";

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


        //Tworzenie pola tekstowego do wprowadzanie loginu
        createTextFiled();

        //Tworzenie pola do wprowadzania hasła
        createPasswordFiled();

        //Tworzenie etykiet tekstowych
        createLabels();

        //Tworzenie przycisków
        createButtons();

        //Tworzenie paneli i dodawanie do nich komponentów GUI oraz Layoutów
        createPanelsLoginWindow();


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

    //Tworzenie Layoutu dla okna LoginWindow
    private LayoutManager createFormLayout(int windowWidth,int windowHeight) {

        //Zmienna pomocnicza do obliczenia wielkości kolumn (dwóch)
        long windowW = Math.round(windowWidth*0.20);
        long windowWTwo = Math.round(windowWidth*0.80);

        //Zmienne pomocnicze do obliczenia wielkości wierszy (trzech)
        long windowH = Math.round(windowHeight*0.25);
        long windowHTwo = Math.round(windowHeight*0.25);

        String columnConfiguration = windowW+"px, "+windowWTwo+"px";
        String rowConfiguration = windowH+"px, "+windowHTwo+"px,";


        FormLayout formLayout = new FormLayout(columnConfiguration,rowConfiguration);

        return formLayout;


    }


    //Konstruktor paneli
    private JPanel createJPanel(Color color){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);

        return jPanel;

    }

    private void createPanelsLoginWindow(){

        //Stworzenie panelu przechowującego wiadomość o konieczności zalogowania się
        loginMessagePanel = createJPanel(Color.WHITE);

        //Dodanie loginMessage do loginMessagePanel
        loginMessagePanel.add(loginMessage);

        //Stworzenie panelu przechowującego przyciski
        loginBottomPanel = createJPanel(Color.LIGHT_GRAY);
            loginBottomPanel.add(confirm);
            loginBottomPanel.add(exit);




        //Obiekt do obsługi komórek układu form Layout dla panelu loginPanel
        CellConstraints cc = new CellConstraints();

        //Stworzenie panelu loginPanel i dodanie do niego stworzonego form layoutu
        this.loginPanel = createJPanel(Color.WHITE);
        loginPanel.setLayout(createFormLayout(loginWindowWidth,loginWindowHeight));


        //Dodanie komponentów do loginPanel
            add(loginMessagePanel,BorderLayout.NORTH);
            loginPanel.add(labelForLogin,cc.xy(1,1,CellConstraints.RIGHT,CellConstraints.CENTER));
            loginPanel.add(loginUser,cc.xy(2,1,CellConstraints.LEFT,CellConstraints.CENTER));
            loginPanel.add(labelForPassword,cc.xy(1,2,CellConstraints.RIGHT,CellConstraints.CENTER));
            loginPanel.add(passwordUser,cc.xy(2,2,CellConstraints.LEFT,CellConstraints.CENTER));
            add(loginBottomPanel,BorderLayout.SOUTH);

        //Dodanie loginPanel do głównego okna
        add(loginPanel);

    }



    private JButton createJButton(String label){

        JButton jbutton = new JButton(label);
        jbutton.addActionListener(this);

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


    private JTextField createJTextFiled(int numberColumns){

        JTextField jTextField = new JTextField(numberColumns);

        return jTextField;

    }

    private void createTextFiled(){

        loginUser = createJTextFiled(18);

    }


    private JPasswordField createJPasswordFiled(int numberColumns){

        JPasswordField jPasswordField = new JPasswordField(numberColumns);

        return jPasswordField;
    }

    private void createPasswordFiled(){

        passwordUser = createJPasswordFiled(18);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==confirm){

            String userDataL = String.valueOf(loginUser.getText()).trim();
            String userDataP = String.valueOf(passwordUser.getPassword()).trim();

            if(userDataL.equals(login)&& userDataP.equals(password)){
                Window.statusBar.setStatusAndValueOfApplication("Logowanie","True");
                dispose();
            }else{
                loginMessage.setText("Błędne dane logowania");
                loginMessage.setForeground(Color.RED);
                Window.statusBar.setStatusAndValueOfApplication("Logowanie","False");
            }

        }else if(e.getSource()==exit){
            dispose();
        }

    }
}
