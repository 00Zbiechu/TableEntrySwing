package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelpWindow extends JDialog {


    private double helpWindowWidth;
    private double helpWindowHeight;


    HelpWindow(){

        setTitle("Kontekst pomocy");
        setResizable(false);

        setSizeHelpWindow(Window.windowWidth,Window.windowHeight);
        setLocationHelpWindow(Window.windowWidth,Window.windowHeight,helpWindowWidth,helpWindowHeight);

        //Zamknięcie okna
        this.addWindowListener	(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }
        });



    }


    //Ustawienie rozmiaru okna HelpWindow na podstawie głównego okna
    private void setSizeHelpWindow(int widthMainWindow,int heightMainWindow){

        this.helpWindowWidth = widthMainWindow*1.5;
        this.helpWindowHeight = heightMainWindow*1.5;

        setSize((int)helpWindowWidth, (int)helpWindowHeight);


    }

    //Ustawianie lokalizacji okna HelpWindow na podstawie głównego okna
    private void setLocationHelpWindow(int widthMain, int heightMain, double widthAbout, double heightAbout){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (int)(screenSizeWidth-widthAbout)/2;
        int locationAboutY = (int)(screenSIzeHeight-heightAbout)/2;

        setLocation(locationAboutX,locationAboutY);


    }


    //Konstruktor paneli dla HelpWindow
    private JPanel createJPanel(Color color){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);

        return jPanel;

    }

}
