package app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class HelpWindow extends JDialog {


    private double helpWindowWidth;
    private double helpWindowHeight;

    private JEditorPane editor;
    private URL url;



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

        //Stworzenie editorPane
        createEditorPane();

        //Ustawianie url
        setURL("/stronaPomocy/index.html");

        //Ładowanie index.html do editorPane
        loadHTML(editor,url);

        //dodawanie załadowanego url'em pane do okna
        this.add(new JScrollPane(editor));


    }


    //Ustawienie rozmiaru okna HelpWindow na podstawie głównego okna
    private void setSizeHelpWindow(int widthMainWindow,int heightMainWindow){

        this.helpWindowWidth = widthMainWindow*1.5;
        this.helpWindowHeight = heightMainWindow*1.5;

        setSize((int)helpWindowWidth, (int)helpWindowHeight);


    }

    //Ustawianie lokalizacji okna HelpWindow na podstawie głównego okna
    private void setLocationHelpWindow(int widthMain, int heightMain, double widthHelp, double heightHelp){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (int)(screenSizeWidth-widthHelp)/2;
        int locationAboutY = (int)(screenSIzeHeight-heightHelp)/2;

        setLocation(locationAboutX,locationAboutY);


    }


    private JEditorPane createJEditorPane(){

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        return editorPane;
    }

    private void createEditorPane(){

        this.editor = createJEditorPane();

    }


    private void setURL(String url){

        this.url = app.HelpWindow.class.getResource(url);

    }

    private void loadHTML(JEditorPane editorPane, URL url) {

        try {
            editorPane.setPage(url);
        }
        catch(Exception e) {
            editorPane.setText("Nie można znaleźć takiego pliku URL " + url);
        }

    }

}
