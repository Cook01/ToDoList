package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;

import java.awt.*;

/**
 * Created by Vincent on 21/11/2016.
 */
public class TacheAuLongCourView extends TacheView{
    JProgressBar progressBar;

    public TacheAuLongCourView(String title, String endDate, String categorie, boolean isLate, int pourcentage){
        super(title, endDate, categorie, isLate);

        this.progressBar = new JProgressBar(0, 100);

        initTacheAuLongCourView(title, endDate, categorie, isLate, pourcentage);
    }

    public void initTacheAuLongCourView(String title, String endDate, String categorie, boolean isLate, int pourcentage){

        super.add(this.progressBar, BorderLayout.SOUTH);

        updateView(title, endDate, categorie, isLate, pourcentage);
    }

    public void updateView(String title, String endDate, String categorie, boolean isLate, int pourcentage){
        super.updateView(title, endDate, categorie, isLate);
        setProgressBar(pourcentage);
    }

    public void setProgressBar(int pourcentage){
        this.progressBar.setValue(pourcentage);
        this.progressBar.setStringPainted(true);    
    }
}
