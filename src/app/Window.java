package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    private int windowWidth;
    private int windowHeight;

    //konstruktor klasy Window
    Window(){

        //Rozmiar i położenie okna
        setDimensionWindow(getResolution());
        setLocationWindow(getResolution());

        //Operacja zamknięcia okna
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Nie zamykaj okna
        //Metoda czeka na zdarzenie okna
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //metoda zamknięcia okna
                closeWindow();
            }
        });



    }

    //metoda pobierająca rozmiar okna;
    private Dimension getResolution() {

        //klasa toolkit zawiera metodę, umożliwiającą pobranie rozmiaru okna
        Toolkit kit = getToolkit();
        //klasa Dimension zawiera pola height i width, dzięki czemu możemy w niej przechować wymiary okna
        Dimension size = kit.getScreenSize();

       return size;

    }

    //metoda ustawiająca rozmiar okna aplikacji na połowę rozmiaru okna i przypisująca te wartości do zmiennych
    private void setDimensionWindow(Dimension resolution){

        this.windowWidth = resolution.width/2;
        this.windowHeight = resolution.height/2;
        setSize(windowWidth,windowHeight);
        setVisible(true);
        setResizable(false);

        //Pokazuje ile wynosi rozmiar okna
        //System.out.println(windowWidth+" "+windowHeight);


    }

    //metoda ustawiająca miejsce ukazywania się okna programu na podstawie rozdzielczości ekranu
    private void setLocationWindow(Dimension resolution){

        int X = resolution.width/4;
        int Y = resolution.height/4;

        setLocation(X,Y);


    }


    //Metoda zamykająca okno
    private void closeWindow(){
        //Wywołanie metody showOptionDialog, która zwraca wartość wyboru użytkownika
        int value = JOptionPane.showOptionDialog(
                //argumenty metody showOptionDialog
                this, //rodzic, obszar w którym, pojawi się ramka w tym przypadku JFrame
                "Czy chcesz zamknąć aplikacje?",
                "Zakończ",
                JOptionPane.YES_NO_CANCEL_OPTION, //liczba całkowita oznaczająca opcje dostępne w oknie dialogowym == 1;
                JOptionPane.QUESTION_MESSAGE, //rodzaj komunikatu
                null, //ikona
                new String[] { "Tak", "Nie"}, //opcje
                "Tak" //wartość domyślna
        );

        if (value == JOptionPane.YES_NO_OPTION) {
            dispose();
            System.exit(0);
        }
    };



}
