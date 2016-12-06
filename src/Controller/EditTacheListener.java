package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTacheListener implements ActionListener
{

	private int id;

	public EditTacheListener(int id) 
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.editTache(id);


	}
}