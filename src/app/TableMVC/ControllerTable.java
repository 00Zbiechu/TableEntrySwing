package app.TableMVC;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTable{

    private ModelTable modelTable;
    private ViewTable viewTable;

    public ControllerTable(ModelTable modelTable, ViewTable viewTable){

        this.modelTable = modelTable;
        this.viewTable = viewTable;

        //Metoda dodająca mojego własnego nasłuchiwacza do buttonów w tabeli
        viewTable.addTableListener(new TableListener());

    }

    //Klasa wewnętrzna, żebym miał dostęp do utworzonych viewTable i modelTable :)
    class TableListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==viewTable.commitButton){

                System.out.println("Klik");

            }

        }
    }


}
