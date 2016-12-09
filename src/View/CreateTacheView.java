package View;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class CreateTacheView extends JFrame 
{

	private JPanel canvas;

    private int id;

    private Boolean type;

	private JTextField title;
	private JSpinner endDate;
	private JComboBox<String> categorie;
    private JButton saveButton;
    private JButton cancelButton;


    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];

	public CreateTacheView(int id, String[] categories, ActionListener listener, Boolean type)
	{
		super();

        this.id = id;

        this.type = type;

		this.canvas = new JPanel();

		this.title 	= new JTextField();
		this.title.setColumns(20);

		SpinnerDateModel model 	= new SpinnerDateModel();
        model.setValue(new Date(System.currentTimeMillis() + (1 * 24 * 60 * 60 * 1000)));
        model.setStart(new Date(System.currentTimeMillis() - (1 * 24 * 60 * 60 * 1000)));

		this.endDate 			= new JSpinner(model);

		JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd / MM / yyyy");
        DateFormatter formatter 	= (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        this.endDate.setEditor(editor);

        JComponent editorDefaukt = this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaukt).getTextField();
        ftf.setColumns(8);


        this.categorie = new JComboBox<>();

        this.saveButton = new JButton("Save");
        this.cancelButton = new JButton("Cancel");

        initCreateTacheView(categories, listener);

	}

	private void initCreateTacheView(String[] categories, ActionListener listener)
	{

		canvas.setLayout(new GridLayout(rows, cols));

		 canvas.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {

                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);

            }
        }

        panelHolder[0][0].add(this.saveButton);
        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.cancelButton);

        panelHolder[1][0].add(this.endDate);
        panelHolder[1][2].add(this.categorie);

        this.add(canvas, BorderLayout.NORTH);

        this.addListenerOnSaveButton(listener);
        this.addListenerOnCancelButton(listener);

        this.setCategorie(categories);

        this.pack();

        
    }

    public void setCategorie(String[] categories){

        for(String categorie : categories){
            this.categorie.addItem(categorie);
        }

    }

    private void addListenerOnSaveButton(ActionListener listener){
        try{
            this.saveButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void addListenerOnCancelButton(ActionListener listener){
        try{
            this.cancelButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getId()
    {
        return this.id;
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

    public Boolean getIsPonctuelle()
    {
        return this.type;
    }


}