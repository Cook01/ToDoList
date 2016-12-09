package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTacheListener implements ActionListener
{

	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand()) {
			case "Save" :
				MainController.addTache();
				break;

			case "Cancel" :
				MainController.cancelCreateTache();
				break;
		}

	}
}