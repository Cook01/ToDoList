package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.*;
import Model.MenuItems;

public class MenuListener implements ActionListener
{

	public void actionPerformed(ActionEvent e) 
	{
	
		String id = e.getActionCommand();
		
		if( id.equals( MenuItems.CARTEPONCTUELLE.toString() ) ) {

				createCarte(true);

		}

		if( id.equals( MenuItems.CARTEAULONGCOURS.toString() ) ) {

				createCarte(false);

		}

		if( id.equals( MenuItems.CATEGORIE.toString() ) ) {

				categorie();

		}

		if( id.equals( MenuItems.SAUVEGARDER.toString() ) ) {

				sauvegarder();

		}

		if( id.equals( MenuItems.QUITTER.toString() ) ) {

				quitter();

		}


	}

	private void createCarte(Boolean ponctuelle)
	{
	
		MainController.createTache(ponctuelle);

	}

	private void categorie()
	{
		System.out.println("manage Categorie");
	}

	private void sauvegarder()
	{
		System.out.println("sauvegarder");
	}

	private void quitter()
	{
		System.out.println("quitter");
	}



}