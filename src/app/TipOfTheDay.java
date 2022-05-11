package app;

import app.TableMVC.ViewTable;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

/**
 * Klasa TipOfTheDay sluzaca do wyswietlania okna z proadami dnia
 */
public class TipOfTheDay {

    private String[] title = {"Tip1","Tip2","Tip3","Tip4","Tip5"};
    private String[] tip = {"Porada nr 1","Porada nr 2","Porada nr 3","Porada nr 4","Porada nr 5"};

    DefaultTipModel spis_porad = new DefaultTipModel();

    /**
     * Konstruktor bezparametrowy klasy <code>TipOfTheDay</code> sluzacy do wypelnania tablicy porad i pokazywania okna
     */
    public TipOfTheDay(){


        //Dodaje tablice tip i title do spis_porad
        fillTips();
        //Umieszczenie spisu porad w obiekcie klasy JTipOfTheDay
        JTipOfTheDay porady = new JTipOfTheDay(spis_porad);
        //Pokazywanie utworzonego obiektu
        porady.showDialog(ViewTable.table);
        Main.logger.info("Pokazanie okna z poradami dnia");
        Main.myLogger.info("Pokazanie okna z poradami dnia");

    }

    private void fillTips(){

        for(int i=0;i<title.length;i++){

            spis_porad.add(new DefaultTip(title[i],tip[i]));

        }

    }

}
