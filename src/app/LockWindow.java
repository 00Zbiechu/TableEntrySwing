package app;

import javax.swing.*;
import java.awt.*;

public class LockWindow extends JPanel {


    private JPanel loginRequired;
    private JLabel loginRequiredMessage = new JLabel("Musisz się zalogować, aby otrzymać dostęp do aplikacji");

    LockWindow(){

        initGUI();

    }

    private void initGUI(){

        //Panel zawierający informację o konieczności logowania
        this.loginRequired = createJPanel(Color.LIGHT_GRAY);
        loginRequired.setLayout(new GridLayout(1,1));


            //Dodanie komponentów GUI do panelu loginRequired
            loginRequired.add(loginRequiredMessage);

            //Dodanie loginRequired do głównego okna
            add(loginRequired);


    }

    private JPanel createJPanel(Color color){

        JPanel jpanel = new JPanel();
        jpanel.setBackground(color);

        return jpanel;

    }

}
