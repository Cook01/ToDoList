package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTacheListener implements ActionListener
{

	private int id;

	SaveTacheListener(int id)
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.saveTache(id);


	}
}