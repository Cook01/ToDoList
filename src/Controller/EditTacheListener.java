package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MenuItems;

public class EditTacheListener implements ActionListener
{

	private int id;

	public EditTacheListener(int id) 
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.editTache((int)id);


	}
}