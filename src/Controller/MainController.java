package Controller;

import View.*;
import Model.*;

import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.*;

public class MainController
{

	private static MainView f;
	private static String title = "ToDo List";

	private static SortTaches sortTache;

	private static ArrayList<ArrayList<String>> menu;
	private static ArrayList<JPanel> tachesView;

	private static Calendar end;
	private static Calendar end2;
	private static Calendar end3;
	
	private static ArrayList<Tache> allTaches;

	private static SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
	

	public static void main(String args[])  
    { 

    	sortTache = new SortTachesByNewest();

	    allTaches = getTaches();

	    allTaches = sortTache.sort(allTaches);
	    
        menu 		= getMenu();
        tachesView	= getTachesView(allTaches);

        f 			= new MainView(title, tachesView, menu, MenuListener.class); 

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

    public static void update()
    {

	    allTaches = sortTache.sort(allTaches);

	    tachesView	= getTachesView(allTaches);

    	f.updateView(title, tachesView);
    }

    public static void removeTache(int id)
    {

    	int i = 0;
    	int size = allTaches.size();
    	boolean find = false;

    	while(!find && i < size) {

    		if (allTaches.get(i).getId() == id) {

    			allTaches.remove(i);
    			find = true;

    		}

    		i++;

    	}
    }

    public static void editTache(int id)
    {
    	System.out.println("edit tache " + id);

        for(Tache t : allTaches){
            if(t.getId() == id){

                String[] catList = {"Lol", "Il faudrait", "Penser a", "Implementer les", "Categories"};

                EditTacheView edit = new EditTacheView(id, t.getTitle(), formatDate.format(t.getEnd().getTime()), catList, 1, t.isLate());

                for(JPanel jp : tachesView){
                    if(jp instanceof TacheView){
                        TacheView tv = (TacheView)jp;

                        if(tv.getId() == id){

                            int index = tachesView.indexOf(jp);

                            tachesView.set(index, jp);
                        }
                    }
                }
            }
        }

        f.updateView(f.getTitle(), tachesView);
    }

    public static void saveTache(int id)
    {
    	System.out.println("save tache " + id);
    }

    

    private static ArrayList<Tache> getTaches()
    {

    	end = Calendar.getInstance();
    	end.setTime(new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000)));
	    end.set(Calendar.HOUR_OF_DAY, 0);
	    end.set(Calendar.MINUTE, 0);
	    end.set(Calendar.SECOND, 0);
	    end.set(Calendar.MILLISECOND, 0);

	    end2 = Calendar.getInstance();
    	end2.setTime(new Date(System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000)));
	    end2.set(Calendar.HOUR_OF_DAY, 0);
	    end2.set(Calendar.MINUTE, 0);
	    end2.set(Calendar.SECOND, 0);
	    end2.set(Calendar.MILLISECOND, 0);

	    end3 = Calendar.getInstance();
    	end3.setTime(new Date(System.currentTimeMillis() + (1 * 24 * 60 * 60 * 1000)));
	    end3.set(Calendar.HOUR_OF_DAY, 0);
	    end3.set(Calendar.MINUTE, 0);
	    end3.set(Calendar.SECOND, 0);
	    end3.set(Calendar.MILLISECOND, 0);

    	ArrayList<Tache> allTaches = new ArrayList<Tache>();

    	AuLongCours aloLongCour = new AuLongCours(6, "TacheAuLongCours2", end);
    	aloLongCour.setPercentage(40);

        allTaches.add(new Ponctuelle(1, "TachePonctuelle1", end));
		allTaches.add(new Ponctuelle(2, "TachePonctuelle2", end2));
		allTaches.add(new Ponctuelle(3, "TachePonctuelle3", end));
		allTaches.add(new Ponctuelle(4, "TachePonctuelle4", end3));

		allTaches.add(new AuLongCours(4, "TacheAuLongCours1", end2));
		allTaches.add(aloLongCour);

        return allTaches;
    }


    private static ArrayList<JPanel> getTachesView(ArrayList<Tache> allTaches)
    {
    	tachesView = new ArrayList<JPanel>();

    	int size = allTaches.size();

    	for (int i = 0; i < size ; i++ ) {


    		String dateFormated = formatDate.format(allTaches.get(i).getEnd().getTime());

    		if(allTaches.get(i) instanceof Ponctuelle)
				tachesView.add(new TacheView(allTaches.get(i).getId(), allTaches.get(i).getTitle(), dateFormated, "Unknow", allTaches.get(i).isLate()));
    		else if (allTaches.get(i) instanceof AuLongCours)
    			tachesView.add(new TacheAuLongCourView(allTaches.get(i).getId(), allTaches.get(i).getTitle(), dateFormated, "Unknow", allTaches.get(i).isLate(), ((AuLongCours)allTaches.get(i)).getPercentage()));

    		if(tachesView.get(i) instanceof TacheView){
    			((TacheView)tachesView.get(i)).addListenerOnEditButton(new EditTacheListener(allTaches.get(i).getId()));
    			((TacheView)tachesView.get(i)).addListenerOnSuppButton(new SuppTacheListener(allTaches.get(i).getId()));
    		} else if(tachesView.get(i) instanceof EditTacheView){
    			((EditTacheView)tachesView.get(i)).addListenerOnSaveButton(new SaveTacheListener(allTaches.get(i).getId()));
    			((EditTacheView)tachesView.get(i)).addListenerOnSuppButton(new SuppTacheListener(allTaches.get(i).getId()));
    		}
    	}

        return tachesView;
    }
}