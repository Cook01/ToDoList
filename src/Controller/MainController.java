package Controller;

import View.*;
import Model.*;

import java.util.ArrayList;

public class MainController
{

	private  MainView f;

	ArrayList<ArrayList<String>> menu;
	ArrayList<TacheView> tachesView;

	public static void main(String args[])  
    { 

        menu 		= getMenu();
        tachesView	= getTacheView();

        f 			= new MainView("ToDo List", tachesView, menu, MenuListener.class); 

        f.setVisible(true);

 
    } 

    private static ArrayList<ArrayList<String>> getMenu()
    {

		ArrayList<ArrayList<String>> menu   = new ArrayList<ArrayList<String>>();

	    ArrayList<String> submenu           = new ArrayList<String>();
	    ArrayList<String> submenu2          = new ArrayList<String>();
	    ArrayList<String> submenu3          = new ArrayList<String>();

	    submenu.add("Créer");
	    submenu.add(MenuItems.CARTEPONCTUELLE.toString());
	    submenu.add(MenuItems.CARTEAULONGCOURS.toString());

	    submenu2.add("Manager");
	    submenu2.add(MenuItems.CATEGORIE.toString());

	    submenu3.add("Autres");
	    submenu3.add(MenuItems.SAUVEGARDER.toString());
	    submenu3.add(MenuItems.QUITTER.toString());

	    menu.add(submenu);
	    menu.add(submenu2);
	    menu.add(submenu3);

	    return menu;

    }

    public static

    private static ArrayList<TacheView> getTacheView()
    {
    	ArrayList<TacheView> tachesView = new ArrayList<TacheView>();

        tachesView.add(new TacheView("Titre1", "25-10-1996", "Bite", false, new TacheListener(1)));
        tachesView.add(new TacheView("Titre2", "25-10-1997", "Bote", false, new TacheListener(2)));
        tachesView.add(new TacheView("Titre3", "25-10-1999", "", false, new TacheListener(3)));
        tachesView.add(new TacheView("Titre4", "25-12-1996", "Bite", true, new TacheListener(9)));
        tachesView.add(new TacheAuLongCourView("Titre5", "25-08-1996", "Bô", false, 20, new TacheListener(4)));
        tachesView.add(new TacheView("Titre6", "25-01-1996", "Bô", false, new TacheListener(5)));
        tachesView.add(new TacheView("Normal", "23-10-1996", "Bite", false, new TacheListener(6)));
        tachesView.add(new TacheView("Titre8", "01-10-1996", "Bote", false, new TacheListener(7)));
        tachesView.add(new TacheView("Titre9", "31-10-1996", "Bite", true, new TacheListener(8)));

        return tachesView;
    }
}