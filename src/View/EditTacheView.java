package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;

import java.awt.*;

/**
 * Created by Vincent on 12/11/2016.
 */
public class EditTacheView extends JPanel{
    private JTextField title;
    private JSpinner endDate;
    private JComboBox categorie;
    private JButton saveButton;

    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    public EditTacheView(String title, String endDate, String categorie, boolean isLate){
        super();

        initEditTacheView(title, endDate, categorie, isLate);
    }

    public void initEditTacheView(String title, String endDate, String categorie, boolean isLate){
        this.title = new JTextField();
        this.title.setPreferredSize(new Dimension(200, 24));

        SpinnerDateModel model = new SpinnerDateModel();
        this.endDate = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd.MM.yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();

        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        
        this.categorie = new JComboBox();
        this.saveButton = new JButton("Save");

        this.setBorder(LineBorder.createGrayLineBorder());

        this.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                add(panelHolder[row][col]);
            }
        }

        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.saveButton);

        panelHolder[1][0].add(this.endDate);
        panelHolder[1][2].add(this.categorie);

        updateView(title, endDate, categorie, isLate);
    }

    public void updateView(String title, String endDate, String categorie, boolean isLate){
        setTitle(title);
        setEndDate(endDate, isLate);
        setCategorie(categorie);
    }

    public void setTitle(String title){
        this.title.setText(title);

        
    }

    public void setEndDate(String endDate, boolean isLate){
        if(isLate){
            this.endDate.setEnabled(false);
        }
        
    }

    public void setCategorie(String categorie){
        //this.categorie.setText(categorie);
    }

    public static void main(String args[]){
        JFrame frame = new JFrame();
        EditTacheView test = new EditTacheView("Test", "25-10-2012", "cat", true);

        frame.add(test);
        //frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
