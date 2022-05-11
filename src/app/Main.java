package app;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Klasa Main zawierajaca metode <code>main</code>, w której uruchamiane jest okno aplikacji i tworzone sa obiekty potrzebne do obslogi logow
 * @author Mateusz Zbiewski
 * @version 1.0
 */
public class Main {

    /**
     * Stala <code>logger</code> przechowujaca obiekt sluzacy do tworzenia logow za pomocą biblioteki LOG4J w konsoli
     */
    public final static Logger logger = Logger.getLogger("logger");
    /**
     * Stala <code>myLogger</code> przechowująca obiekt sluzacy do tworzenia logow za pomocą biblioteki LOG4J w pliku .log
     */
    public final static Logger myLogger = Logger.getLogger("myLogger");

    /**
     * Metoda sluzaca do uruchomienia aplikacji, w ktorej tworzony jest obiekt klasy <code>Window</code>
     * @param args Tablica parametrów klasy <code>String</code> potrzebnych przy uruchomieniu programu (niewykorzystywana)
     */
    public static void main(String args[]){

        PropertyConfigurator.configure("log/log.properties");
        logger.info("Start aplikacji");
        myLogger.info("Start aplikacji");

        Window window = new Window();
        window.setVisible(true);


    }

}
