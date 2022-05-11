package app;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

    public final static Logger logger = Logger.getLogger("logger");
    public final static Logger myLogger = Logger.getLogger("myLogger");

    public static void main(String args[]){

        PropertyConfigurator.configure("log/log.properties");
        logger.info("Start aplikacji");
        myLogger.info("Start aplikacji");

        Window window = new Window();
        window.setVisible(true);


    }

}
