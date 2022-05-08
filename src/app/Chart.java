package app;

import app.TableMVC.ControllerTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Chart {

    private double chartWindowWidth;
    private double chartWindowHeight;

    //Dane do wykresu
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();


    Chart(){


        fillDataset();
        initGUI();

    }

    private void initGUI(){

        // Tworzy wykres typu Bar - słupkowy - poziomy
        JFreeChart chart = ChartFactory.createBarChart("Wykres danych w tabeli",
                "Wiersz", "Suma wartości w wierszu", dataset, PlotOrientation.HORIZONTAL,
                true, false, false);

        ChartFrame frame1=new ChartFrame("Wykres danych w tabeli",chart);
        frame1.setVisible(true);

        setSizeChartWindow(frame1,Window.windowWidth,Window.windowHeight);
        setLocationChartWindow(frame1,Window.windowWidth,Window.windowHeight,chartWindowWidth,chartWindowHeight);


    }

    //Ustawienie rozmiaru okna HelpWindow na podstawie głównego okna
    private void setSizeChartWindow(ChartFrame frame,int widthMainWindow,int heightMainWindow){

        chartWindowWidth = widthMainWindow*1.5;
        chartWindowHeight = heightMainWindow*1.5;

        frame.setSize((int) chartWindowWidth, (int) chartWindowHeight);


    }

    //Ustawianie lokalizacji okna HelpWindow na podstawie głównego okna
    private void setLocationChartWindow(ChartFrame frame, int widthMain, int heightMain, double widthHelp, double heightHelp){

        int screenSizeWidth = widthMain*2;
        int screenSIzeHeight = heightMain*2;

        int locationAboutX = (int)(screenSizeWidth-widthHelp)/2;
        int locationAboutY = (int)(screenSIzeHeight-heightHelp)/2;

        frame.setLocation(locationAboutX,locationAboutY);


    }

    public void fillDataset(){

        int sum;

        for(int i=0;i< ControllerTable.viewTable.table.getRowCount();i++){

            sum=0;

            for(int j=0;j<ControllerTable.viewTable.table.getColumnCount();j++){

                sum=sum+(int)ControllerTable.modelTable.getValueAt(i,j);

            }

            dataset.setValue(sum,"Wiersz "+(i+1), "Wiersz "+(i+1));

        }


    }

}
