package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditCategorieView extends JFrame implements ListSelectionListener{

	private String[] catNameList;
	private String[] catLabelList;

	private JPanel canvas;

	private JScrollPane listScroller;
	private JList list;

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

	public EditCategorieView(String[] catNameList, String[] catLabelList){
		super();

		this.canvas = new JPanel();

		this.catNameList = catNameList;
		this.catLabelList = catLabelList;

		this.listScroller = new JScrollPane();
		this.list = new JList(catNameList);

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

		this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL);
		this.list.setVisibleRowCount(-1);

		this.listScroller.setPreferredSize(new Dimension(250, 80));

		this.listScroller.add(list);

		this.textFieldPanel.add(titleLabel);
		this.textFieldPanel.add(titleField);
		this.textFieldPanel.add(labelLabel);
		this.textFieldPanel.add(labelField);

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
			title = catNameList[index];
			label = catLabelList[index];
		}		

		setTitle(title);
		setLabel(label);
	}

	public void setTitle(String title){
		this.titleField.setText(title);
	}

	public void setLabel(String label){
		this.titleLabel.setText(label);
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