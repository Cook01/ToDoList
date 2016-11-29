package View;

import Controller.*;

import java.lang.Exception;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;

import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CreateTacheView extends JPanel
{

	protected JPanel canvas;

	private JTextField title;
	private JSpinner endDate;
	private JComboBox categorie;
    private JButton saveButton;
    private JButton cancelButton;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	

    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];

	public CreateTacheView(ActionListener listener)
	{
		super();

		this.canvas = new JPanel();

		this.title 	= new JTextField();

		SpinnerDateModel model 	= new SpinnerDateModel();
		this.endDate 			= new JSpinner(model);

		JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd.MM.yyyy");
        DateFormatter formatter 	= (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        this.categorie = new JComboBox();

        this.saveButton = new JButton("Save");
        this.cancelButton = new JButton("Cancel");

        initCreateTacheView(listener);

	}

	public void initCreateTacheView(ActionListener listener)
	{
		this.setBorder(LineBorder.createGrayLineBorder());
		this.setLayout(new BorderLayout());

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

        
    }

    public void addListenerOnSaveButton(ActionListener listener){
        try{
            this.saveButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void addListenerOnCancelButton(ActionListener listener){
        try{
            this.cancelButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}