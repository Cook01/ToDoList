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
    private JButton deleteButton;

    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    public EditTacheView(String title, String endDate, String[] categories, int idCategorie, boolean isLate){
        super();

        this.title = new JTextField();

        SpinnerDateModel model = new SpinnerDateModel();
        this.endDate = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd.MM.yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();

        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        this.categorie = new JComboBox();

        this.saveButton = new JButton("Save");
        this.deleteButton = new JButton("X");

        initEditTacheView(title, endDate, categories, idCategorie, isLate);
    }

    public void initEditTacheView(String title, String endDate, String[] categories, int idCategorie, boolean isLate){
        this.setBorder(LineBorder.createGrayLineBorder());

        this.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                add(panelHolder[row][col]);
            }
        }

        panelHolder[0][0].add(this.saveButton);
        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.deleteButton);

        panelHolder[1][0].add(this.endDate);
        panelHolder[1][2].add(this.categorie);

        updateView(title, endDate, categories, idCategorie, isLate);
    }

    public void updateView(String title, String endDate, String[] categories, int idCategorie, boolean isLate){
        setTitle(title);
        setEndDate(endDate, isLate);
        setCategorie(categories, idCategorie);
    }

    public void setTitle(String title){
        this.title.setText(title);

        this.title.setPreferredSize(new Dimension(100, 24));        
    }

    public void setEndDate(String endDate, boolean isLate){
        if(isLate){
            this.endDate.setEnabled(false);
        }
    }

    public void setCategorie(String[] categories, int idCategorie){

        for(String categorie : categories){
            this.categorie.addItem(categorie);
        }

        this.categorie.setSelectedIndex(idCategorie);
    }

    public static void main(String args[]){

        String[] categories = {"cat", "test", "lol", "ok"};

        JFrame frame = new JFrame();
        EditTacheView test = new EditTacheView("Test", "25-10-2012", categories, 2, true);

        frame.add(test);
        frame.pack();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
