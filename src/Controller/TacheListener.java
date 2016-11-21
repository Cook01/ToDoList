package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MenuItems;

public class TacheListener implements ActionListener
{

	private int id;

	public TacheListener(int id) 
	{
		this.id = id;
	}

	public void actionPerformed(ActionEvent e) 
	{
	
		System.out.println(e.getActionCommand());
		System.out.println(id);


	}




}