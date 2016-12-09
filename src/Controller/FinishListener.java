package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.MainController.updateProgressTache;

class FinishListener implements ActionListener
{

	private int id;

	FinishListener(int id)
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		updateProgressTache(id);


	}
}
