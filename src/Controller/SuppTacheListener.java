package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MenuItems;

public class SuppTacheListener implements ActionListener
{

	private int id;

	public SuppTacheListener(int id) 
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.removeTache((int)id);


	}
}