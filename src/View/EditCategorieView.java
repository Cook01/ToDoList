package View;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCategorieView extends JFrame implements ListSelectionListener{

	private ArrayList<String> catNameList;
	private ArrayList<String> catLabelList;

	private JPanel canvas;

	private JScrollPane listScroller;

    private DefaultListModel<String> model;
	private JList<String> list;

	private JPanel textFieldPanel;

	private JLabel titleLabel;
	private JLabel labelLabel;
	private JTextField titleField;
	private JTextField labelField;


	private JPanel buttonPanel;
	private JButton suppButton;
	private JButton addButton;
	private JButton editButton;

	private final int rows = 3;
    private final int cols = 1;
    private JPanel[][] panelHolder = new JPanel[rows][cols];

	public EditCategorieView(ArrayList<String> catNameList, ArrayList<String> catLabelList){
		super();

		this.canvas = new JPanel();

		this.catNameList = catNameList;
		this.catLabelList = catLabelList;

		this.model = new DefaultListModel<>();

		this.listScroller = new JScrollPane();
		this.list = new JList<>(model);

		this.textFieldPanel = new JPanel();

		this.titleLabel = new JLabel("Titre : ");
		this.titleField = new JTextField();

		this.labelLabel = new JLabel("Abrev. : ");
		this.labelField = new JTextField();

		this.buttonPanel = new JPanel();

		this.suppButton = new JButton("Supprimer");
		this.addButton = new JButton("Ajouter");
		this.editButton = new JButton("Modifier");

		initEditCategorieView();
	}

	public void initEditCategorieView(){
		this.canvas.setLayout(new GridLayout(rows, cols));

        for(String name : catNameList){
            model.addElement(name);
        }

		this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL);
		this.list.setVisibleRowCount(10);

		this.listScroller.setPreferredSize(new Dimension(250, 80));
		this.listScroller.setViewportView(list);

        this.textFieldPanel.setLayout(new FlowLayout());
		this.textFieldPanel.add(titleLabel);
		this.textFieldPanel.add(titleField);
		this.textFieldPanel.add(labelLabel);
		this.textFieldPanel.add(labelField);

        this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.add(addButton);
		this.buttonPanel.add(editButton);
		this.buttonPanel.add(suppButton);

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);
            }
        }

        panelHolder[0][0].add(listScroller);
        panelHolder[1][0].add(textFieldPanel);
        panelHolder[2][0].add(buttonPanel);

        updateView();

        this.add(canvas);
        this.pack();
	}

	public void updateView(){
		int index = list.getSelectedIndex();

		String title = "";
		String label = "";

		if(index != -1){
			title = catNameList.get(index);
			label = catLabelList.get(index);

			this.editButton.setEnabled(true);
			this.suppButton.setEnabled(true);
		} else {
			this.editButton.setEnabled(false);
			this.suppButton.setEnabled(false);
		}

		setTitle(title);
		setLabel(label);

	}

	public void setTitle(String title){
		this.titleField.setText(title);

		if(title.equals("")){
		    this.titleField.setPreferredSize(new Dimension(100, 20));
        }
	}

	public void setLabel(String label){
		this.labelField.setText(label);

        if(label.equals("")){
            this.labelField.setPreferredSize(new Dimension(100, 20));
        }
	}

	public String getSelectedTitle(){
		return catNameList.get(this.list.getSelectedIndex());
	}

	public void addListenerOnAddButton(ActionListener listener){
        try{
            this.addButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addListenerOnEditButton(ActionListener listener){
        try{
            this.editButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addListenerOnSuppButton(ActionListener listener){
        try{
            this.suppButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addListSelectionListener(ListSelectionListener listener){
    	this.list.addListSelectionListener(listener);
    }

    public void valueChanged(ListSelectionEvent e){
    	updateView();
    }
}