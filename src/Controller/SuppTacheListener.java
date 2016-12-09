package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuppTacheListener implements ActionListener
{

	private int id;

	SuppTacheListener(int id)
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.removeTache(id);


	}
}