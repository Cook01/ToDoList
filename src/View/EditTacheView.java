package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * Created by Vincent on 12/11/2016.
 */
public class EditTacheView extends JPanel
{
    private int id;

    protected JPanel canvas;

    private JTextField title;
    private JSpinner endDate;
    private JComboBox categorie;
    private JButton saveButton;
    private JButton deleteButton;

    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    public EditTacheView(int id, String title, String endString,Date endDate, String[] categories, int idCategorie, Boolean isLate, Calendar dateCreation){
        super();

        this.id = id;

        this.canvas = new JPanel();

        this.title = new JTextField();

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(endDate);
        model.setStart(dateCreation.getTime());

        this.endDate = new JSpinner(model);

        

        JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd/MM/yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();




        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        this.endDate.setEditor(editor);

        JComponent editorDefaukt = (JSpinner.DefaultEditor) this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaukt).getTextField();
        ftf.setColumns(8);

        this.categorie = new JComboBox();

        this.saveButton = new JButton("Save");
        this.deleteButton = new JButton("X");

        initEditTacheView(title, endString, categories, idCategorie, isLate);
    }

    public void initEditTacheView(String title, String endDate, String[] categories, int idCategorie, Boolean isLate){
        this.setBorder(LineBorder.createGrayLineBorder());
        this.setLayout(new BorderLayout());

        canvas.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);
            }
        }

        panelHolder[0][0].add(this.saveButton);
        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.deleteButton);

        panelHolder[1][0].add(this.endDate);
        panelHolder[1][2].add(this.categorie);

        this.add(canvas, BorderLayout.NORTH);

        updateView(title, endDate, categories, idCategorie, isLate);
    }

    public void updateView(String title, String endDate, String[] categories, int idCategorie, Boolean isLate){
        setTitle(title);
        setEndDate(endDate, isLate);
        setCategorie(categories, idCategorie);
    }

    public void setTitle(String title){
        this.title.setText(title);

        this.title.setPreferredSize(new Dimension(100, 24));        
    }

    public void setEndDate(String endDate, Boolean isLate){
        if(isLate){
            this.endDate.setEnabled(false);
        }

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        Date startDate;

        try {
            this.endDate.setValue(df.parse(endDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCategorie(String[] categories, int idCategorie){

        for(String categorie : categories){
            this.categorie.addItem(categorie);
        }

        this.categorie.setSelectedIndex(idCategorie);
    }

    public void addListenerOnSaveButton(ActionListener listener){
        try{
            this.saveButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void addListenerOnSuppButton(ActionListener listener){
        try{
            this.deleteButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return this.title.getText();
    }

    public Calendar getEndDate() {
        Calendar endDateCalendar = Calendar.getInstance();

        endDateCalendar.setTime((Date) endDate.getValue());

        return endDateCalendar;
    }

    public String getCategorie() {
        return categorie.getSelectedItem().toString();
    }
}