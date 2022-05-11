package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa <code>LockWindow</code> do tworzenia panelu blokady dla niezalogowanych uzytkownikow, rozszerzajaca klase <code>JPanel</code> oraz implementujaca interfejs <code>ActionListener</code>
 */
public class LockWindow extends JPanel implements ActionListener {


    private JPanel loginRequired;
    private JLabel loginRequiredMessage = new JLabel("Musisz się zalogować, aby otrzymać dostęp do aplikacji");
    private Icon iconLogin;
    private JButton loginButton;

    /**
     * Konstruktor bezparametrowy klasy LockWindow tworzącej GUI okna
     */
    public LockWindow(){

        initGUI();

    }

    private void initGUI(){

        setLayout(new GridLayout(2,1,10,10));

        createIcon();
        createButton();

        //Panel zawierający informację o konieczności logowania
        this.loginRequired = createJPanel(Color.LIGHT_GRAY);
        loginRequired.setLayout(new GridLayout(1,1));


            //Dodanie komponentów GUI do panelu loginRequired
            loginRequired.add(loginRequiredMessage);



            //Dodanie loginRequired do głównego okna
            add(loginRequired);
            add(loginButton);

    }

    private JPanel createJPanel(Color color){

        JPanel jpanel = new JPanel();
        jpanel.setBackground(color);

        return jpanel;

    }

    //Konstruktor ikon
    private Icon createJIcon(String file) {
        String name = "/grafika/"+file;
        Icon icon = new ImageIcon(getClass().getResource(name));

        return icon;
    }

    private void createIcon(){

        iconLogin = createJIcon("min_about.jpg");

    }

    private JButton createJButton(String text,Icon icon){

        JButton jButton = new JButton(text,icon);
        jButton.addActionListener(this);
        jButton.setBackground(Color.WHITE);

        return jButton;

    }

    private void createButton(){


        this.loginButton = createJButton("Logowanie",iconLogin);

    }

    /**
     * Metoda obslugujaca zdarzenie akcji
     * @param e obiekt klasy nasluchujacej <code>ActionListener</code>
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==loginButton){

            Window.statusBar.setStatusAndValueOfApplication("Logowanie","W trakcie");
            Main.logger.info("Logowanie W trakcie");
            Main.myLogger.info("Logowanie W trakcie");
            Window.loginWindow.setVisible(true);

        }

    }
}
