package app;

import app.TableMVC.ViewTable;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

public class TipOfTheDay {

    String[] title = {"Tip1","Tip2","Tip3","Tip4","Tip5"};
    String[] tip = {"Porada nr 1","Porada nr 2","Porada nr 3","Porada nr 4","Porada nr 5"};

    DefaultTipModel spis_porad = new DefaultTipModel();

    TipOfTheDay(){

        fillTips();
        JTipOfTheDay porady = new JTipOfTheDay(spis_porad);
        porady.showDialog(ViewTable.table);

    }

    private void fillTips(){

        for(int i=0;i<title.length;i++){

            spis_porad.add(new DefaultTip(title[i],tip[i]));

        }

    }

}
