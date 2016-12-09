package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishListener implements ActionListener
{

	private int id;

	public FinishListener(int id) 
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{

		MainController.updateProgressTache(id);


	}
}
