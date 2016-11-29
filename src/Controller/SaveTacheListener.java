package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MenuItems;

public class SaveTacheListener implements ActionListener
{

	private int id;

	public SaveTacheListener(int id) 
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.saveTache((int)id);


	}
}