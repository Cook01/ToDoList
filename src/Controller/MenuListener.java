package Controller;

import Model.MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		if( id.equals( MenuItems.BILAN.toString() ) ) {

				bilan();

		}


	}

	private void createCarte(Boolean ponctuelle)
	{
	
		MainController.createTache(ponctuelle);

	}

	private void categorie()
	{
		MainController.editCategorie();
	}

	private void sauvegarder()
	{
		System.out.println("sauvegarder");

		MainController.saveAll();
	}

	private void bilan()
	{

		MainController.bilan();
	}



}