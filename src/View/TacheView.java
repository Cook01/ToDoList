package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import Controller.TacheListener;

/**
 * Created by Vincent on 12/11/2016.
 */
public class TacheView extends JPanel{
    private JLabel title;
    private JLabel endDate;
    private JLabel categorie;
    private JButton editButton;
    private JButton deleteButton;

    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    public TacheView(String title, String endDate, String categorie, boolean isLate, Class tacheListener){
        super();

        this.title = new JLabel();
        this.endDate = new JLabel();
        this.categorie = new JLabel();
        this.editButton = new JButton("Edit");
        this.deleteButton = new JButton("X");

        initTacheView(title, endDate, categorie, isLate, tacheListener);
    }

    public void initTacheView(String title, String endDate, String categorie, boolean isLate, Class tacheListener){

        this.setBorder(LineBorder.createGrayLineBorder());

        this.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                add(panelHolder[row][col]);
            }
        }

         try {

            this.editButton.addActionListener( (TacheListener)tacheListener.newInstance() );
            this.deleteButton.addActionListener( (TacheListener)tacheListener.newInstance() );

        } catch(Exception e) {

            System.out.println(e);

        }

        

        panelHolder[0][0].add(this.editButton);
        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.deleteButton);

        panelHolder[1][0].add(this.endDate);
        panelHolder[1][2].add(this.categorie);

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
