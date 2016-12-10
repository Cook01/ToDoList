package View;

import javax.swing.*;
import java.awt.*;

public class TacheAuLongCourView extends TacheView{
    private JProgressBar progressBar;

    public TacheAuLongCourView(int id, String title, String beginDate, String endDate, int interval, String categorie, boolean isLate, int pourcentage){
        super(id, title, beginDate, endDate, interval, categorie, isLate);

        this.progressBar = new JProgressBar(0, 100);


        initTacheAuLongCourView(title, beginDate, endDate, interval, categorie, isLate, pourcentage);
    }

    private void initTacheAuLongCourView(String title, String beginDate,String endDate, int interval,String categorie, boolean isLate, int pourcentage){

        super.add(this.progressBar, BorderLayout.SOUTH);
        super.setFinisButtonText("Avancer");

        updateView(title, beginDate, endDate, interval, categorie, isLate, pourcentage);
    }

    private void updateView(String title,  String beginDate, String endDate, int interval,String categorie, boolean isLate, int pourcentage){
        super.updateView(title, beginDate, endDate, interval, categorie, isLate);
        setProgressBar(pourcentage);
    }

    private void setProgressBar(int pourcentage){
        this.progressBar.setValue(pourcentage);
        this.progressBar.setStringPainted(true);    
    }
}
