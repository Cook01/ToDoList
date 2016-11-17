package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vincent on 12/11/2016.
 */
public class TacheView extends JPanel{
    private JLabel title;
    private JLabel endDate;
    private JLabel categorie;

    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    public TacheView(String title, String endDate, String categorie, boolean isLate){
        super();
        tacheModel = model;

        initTacheView(titre, endDate, categorie, isLate);
    }

    public void initTacheView(String title, String endDate, String categorie, boolean isLate){
        this.title = new JLabel();
        this.endDate = new JLabel();
        this.categorie = new JLabel();

        this.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                add(panelHolder[row][col]);
            }
        }

        panelHolder[0][1].add(title);

        panelHolder[1][0].add(new JLabel("Date de fin : "));
        panelHolder[1][1].add(endDate);
        panelHolder[1][2].add(categorie);

        updateView(title, endDate, categorie, isLate);
    }

    public void updateView(String title, String endDate, String categorie, boolean isLate){
        setTitle(title, isLate);
        setEndDate(endDate);
        setCategorie(categorie);
    }

    public void setTitle(String title, boolean isLate){
        this.title.setText(title);
        if(isLate){
            this.title.setForeground(Color.RED);
        }
    }

    public void setEndDate(String endDate){
        this.endDate.setText(endDate);
    }

    public void setCategorie(String categorie){
        this.categorie.setText(categorie);
    }
}