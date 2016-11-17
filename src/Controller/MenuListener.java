package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener
{

	public void actionPerformed(ActionEvent e) 
	{
	
		System.out.println("Selected: " + e.getActionCommand());

	}

}