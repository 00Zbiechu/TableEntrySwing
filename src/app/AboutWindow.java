package app;

import javax.swing.*;

public class AboutWindow extends JDialog {

    private int aboutWindowWidth;
    private int aboutWindowHeight;

    AboutWindow(){

        this.setTitle("Informacje o programie");
        this.setResizable(false);

        //ustawienie rozmiaru okna AboutWindow
        setSizeAboutWindow(Window.windowWidth,Window.windowHeight);

    }

    private void setSizeAboutWindow(int widthMainWindow,int heightMainWindow){

        this.aboutWindowWidth = widthMainWindow/2;
        this.aboutWindowHeight = heightMainWindow/2;

        setSize(aboutWindowWidth,aboutWindowHeight);


    }





}
