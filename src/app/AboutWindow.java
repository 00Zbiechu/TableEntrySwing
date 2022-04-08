package app;

import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JDialog {

    private int aboutWindowWidth;
    private int aboutWindowHeight;

    //Panele testowe do sprawdzenia rozkładu okna
    private JPanel conPane,panel1,panel2,panel3,panel4,panel5;

    AboutWindow(){

        this.setTitle("Informacje o programie");
        this.setResizable(false);

        //ustawienie rozmiaru okna AboutWindow
        setSizeAboutWindow(Window.windowWidth,Window.windowHeight);

        //Ustawianie lokalizacji okna na połowę wielkości głównego okna
        setLocationAboutWindow(Window.windowWidth,Window.windowHeight,aboutWindowWidth,aboutWindowHeight);

    }

    //Tworznie GUI
    public void initGUI(){

        //Tworzenie struktury paneli
        createPanel();

    }

    //Ustawienie rozmiaru okna AboutWindow
    private void setSizeAboutWindow(int widthMainWindow,int heightMainWindow){

        this.aboutWindowWidth = widthMainWindow/2;
        this.aboutWindowHeight = heightMainWindow/2;

        setSize(aboutWindowWidth,aboutWindowHeight);


    }

    //Ustawianie lokalizacji okna AboutWindow
    private void setLocationAboutWindow(int widthMain, int heightMain, int widthAbout, int heightAbout){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (screenSizeWidth-widthAbout)/2;
        int locationAboutY = (screenSIzeHeight-heightAbout)/2;

        setLocation(locationAboutX,locationAboutY);


    }

    private JPanel createJPanel(Color color,int width, int height){

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);
        jPanel.setSize(width,height);


        return jPanel;

    }

    private void createPanel(){

        //Panel przechowujący całe GUI okna AboutWindow
        conPane = (JPanel) this.getContentPane();

        panel1 = createJPanel(Color.black,aboutWindowWidth/3,aboutWindowHeight/3);
        panel2 = createJPanel(Color.blue, aboutWindowWidth/3, aboutWindowHeight/3);
        panel3 = createJPanel(Color.red, aboutWindowWidth/3, aboutWindowHeight/3);
        panel4 = createJPanel(Color.yellow, aboutWindowWidth/3, aboutWindowHeight/3);
        panel5 = createJPanel(Color.green, aboutWindowWidth/3, aboutWindowHeight/3);

        //Dodanie pomniejszych paneli do conPane
        conPane.add(panel1,BorderLayout.NORTH);
        conPane.add(panel2,BorderLayout.EAST);
        conPane.add(panel3,BorderLayout.SOUTH);
        conPane.add(panel4,BorderLayout.WEST);
        conPane.add(panel5,BorderLayout.CENTER);

        //Dodane conPane do okna



    }





}
