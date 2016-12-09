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

    private JPanel center;

    private JTextField title;
	private JSpinner endDate;
    private JSpinner beginDate;
	private JComboBox<String> categorie;
    private JButton saveButton;
    private JButton cancelButton;


    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    private void updateView(Date endValue)
    {

        Date beginValue =  (Date)this.beginDate.getValue();

        if(beginValue.compareTo(endValue) > 0)
            beginValue = endValue;

        Calendar value = Calendar.getInstance();
        value.setTime(new Date(System.currentTimeMillis()));
        value.set(Calendar.HOUR_OF_DAY, 0);
        value.set(Calendar.MINUTE, 0);
        value.set(Calendar.SECOND, 0);
        value.set(Calendar.MILLISECOND, 0);

        SpinnerDateModel modelBegin 	= new SpinnerDateModel();
        modelBegin.setValue(beginValue);
        modelBegin.setStart(value.getTime());
        modelBegin.setEnd(endValue);


        this.beginDate = new JSpinner(modelBegin);

        JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
        DateFormatter formatterBegin 	= (DateFormatter)editorBegin.getTextField().getFormatter();
        formatterBegin.setAllowsInvalid(false);
        formatterBegin.setOverwriteMode(true);

        this.beginDate.setEditor(editorBegin);

        JComponent editorDefaukt2 = this.beginDate.getEditor();
        JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
        ftf2.setColumns(8);

        this.center.removeAll();

        this.center.add(this.beginDate);

        this.revalidate();
        this.validate();
        this.repaint();

    }

	public CreateTacheView(int id, String[] categories, ActionListener listener, Boolean type)
	{
		super();

        this.id = id;

        this.type = type;

		this.canvas = new JPanel();

		this.title 	= new JTextField();
		this.title.setColumns(20);

		SpinnerDateModel model 	= new SpinnerDateModel();

        Calendar value = Calendar.getInstance();
        value.setTime(new Date(System.currentTimeMillis()));
        value.set(Calendar.HOUR_OF_DAY, 0);
        value.set(Calendar.MINUTE, 0);
        value.set(Calendar.SECOND, 0);
        value.set(Calendar.MILLISECOND, 0);

        model.setValue(value.getTime());
        model.setStart(value.getTime());

        this.endDate 			= new JSpinner(model);

        SpinnerDateModel modelBegin 	= new SpinnerDateModel();
        modelBegin.setValue(value.getTime());
        modelBegin.setStart(value.getTime());
        modelBegin.setEnd(value.getTime());

        this.beginDate          = new JSpinner(modelBegin);


        this.endDate.addChangeListener(e -> {

            this.updateView((Date)this.endDate.getModel().getValue());

        });



		JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd / MM / yyyy");
        DateFormatter formatter 	= (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
        DateFormatter formatterBegin 	= (DateFormatter)editorBegin.getTextField().getFormatter();
        formatterBegin.setAllowsInvalid(false);
        formatterBegin.setOverwriteMode(true);

        this.endDate.setEditor(editor);

        this.beginDate.setEditor(editorBegin);

        JComponent editorDefaukt = this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaukt).getTextField();
        ftf.setColumns(8);

        JComponent editorDefaukt2 = this.beginDate.getEditor();
        JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
        ftf2.setColumns(8);


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

        center = new JPanel();

        center.add(this.beginDate);

        this.add(center, BorderLayout.SOUTH);

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