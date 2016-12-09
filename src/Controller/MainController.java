package Controller;

import Model.AuLongCours;
import Model.MenuItems;
import Model.Ponctuelle;
import Model.Categorie;
import Model.Tache;
import View.*;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    public static ArrayList<Categorie> catList;

    private static CreateTacheView createTache;


	private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	

	public static void main(String args[])  
    { 

    	sortTache = new SortTachesByNewest();

        catList = getCategorie();
	    allTaches = getTaches();

	    allTaches = sortTache.sort(allTaches);
	    
        menu 		= getMenu();
        tachesView	= getTachesView(allTaches);

        f 			= new MainView(title, tachesView, menu, MenuListener.class); 

        f.setVisible(true);
    }

    private static ArrayList<ArrayList<String>> getMenu()
    {

		ArrayList<ArrayList<String>> menu   = new ArrayList<>();
	    ArrayList<String> submenu           = new ArrayList<>();
	    ArrayList<String> submenu2          = new ArrayList<>();
	    ArrayList<String> submenu3          = new ArrayList<>();

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

        System.out.println("update");

	    allTaches = sortTache.sort(allTaches);

	    tachesView	= reOrderTacheView(allTaches, tachesView);

    	updateView();
    }

    public static void updateView()
    {
        f.updateView(title, tachesView);
    }

    public static void editCategorie(){
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> labelList = new ArrayList<>();

        for(Categorie c : catList){
            titleList.add(c.getTitre());
            labelList.add(c.getAbreviation());
        }

        EditCategorieView ecv = new EditCategorieView(titleList, labelList);
        ecv.addListSelectionListener(ecv);

        ecv.setVisible(true);
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

        size = tachesView.size();
        find = false;
        i = 0;
        while(!find && i < size) {

            int myId = -1;

            if(tachesView.get(i) instanceof TacheView){
                TacheView tache = (TacheView)tachesView.get(i);
                myId = tache.getId();
            }
            if(tachesView.get(i) instanceof TacheAuLongCourView){
                 TacheAuLongCourView tache = (TacheAuLongCourView)tachesView.get(i);
                myId = tache.getId();
            }
            if(tachesView.get(i) instanceof EditTacheView){
                EditTacheView tache = (EditTacheView)tachesView.get(i);
                myId = tache.getId();
            } 
            

            if ( myId == id) {

                tachesView.remove(i);
                find = true;

            }

            i++;

        }

        update();
    }

    public static void cancelCreateTache()
    {
        if(createTache != null) {

            createTache.dispose();
            createTache = null;
        }
    }

    public static void updateProgressTache(int id)
    {

        int i = 0;
        int size = allTaches.size();
        boolean find = false;

        while(!find && i < size) {

            if (allTaches.get(i).getId() == id) {

                if(allTaches.get(i) instanceof Ponctuelle) {
                    System.out.println("updateProgressTache Ponctuelle");
                }else if(allTaches.get(i) instanceof AuLongCours) {
                    System.out.println("updateProgressTache AuLongCours");
                    ((AuLongCours)allTaches.get(i)).setPercentage(((AuLongCours)allTaches.get(i)).getPercentage() + 10);
                }

                find = true;

                updateOneTache(allTaches.get(i));

            }
            i++;
        }

      

    }

    public static void createTache(Boolean ponctuelle)
    {
        ArrayList<String> stringList = new ArrayList<>();
        
        for(Categorie cat : catList){
            stringList.add(cat.getTitre());
        }

        ActionListener listener;

        if(createTache != null) {
            createTache.dispose();
            createTache = null;
        }

        if(ponctuelle) {

            listener = new CreateTacheListener();
            
        } else {

            listener = new CreateTacheAuLongCoursListener();

        }

        int id = (int) (new Date().getTime()/1000);
        createTache = new CreateTacheView(id, stringList.toArray(new String[stringList.size()]), listener, ponctuelle);
        createTache.setVisible(true);
        
    }

    public static void editTache(int id)
    {
    	for(Tache t : allTaches){
            if(t.getId() == id){


                ArrayList<String> stringList = new ArrayList<>();
                int indexCat = 0;

                for(Categorie cat : catList){
                    stringList.add(cat.getTitre());

                    if(t.getCategorie().getTitre().equals(cat.getTitre())){
                        indexCat = catList.indexOf(cat);
                    }
                }

                EditTacheView edit = new EditTacheView(id, t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getEnd().getTime(), stringList.toArray(new String[stringList.size()]), indexCat, t.isLate(), t.getDateCreation());

                edit.addListenerOnSuppButton(new SuppTacheListener(id));
                edit.addListenerOnSaveButton(new SaveTacheListener(id));

                for(JPanel jp : tachesView){
                    if(jp instanceof TacheView){
                        TacheView tv = (TacheView)jp;

                        if(tv.getId() == id){
                            int index = tachesView.indexOf(jp);
                            tachesView.set(index, edit);
                        }
                    }
                }
            }
        }

       updateView();
    }

    public static void addTache()
    {

        if(createTache != null) {

            String cat = createTache.getCategorie();
            Categorie catTache = new Categorie("", "");

            for(Categorie c : catList){
                if(c.getTitre().equals(cat)){
                    catTache = c;
                }
            }

            if(createTache.getIsPonctuelle()) {
                Ponctuelle tache = new Ponctuelle(createTache.getId(), createTache.getTitle(), createTache.getEndDate(), catTache);
                allTaches.add(tache);

                String dateFormated = formatDate.format(createTache.getEndDate().getTime());
                TacheView tacheView = new TacheView(createTache.getId(), tache.getTitle(), dateFormated,catTache.getAbreviation(), tache.isLate());
                tacheView.addListenerOnEditButton(new EditTacheListener(createTache.getId()));
                tacheView.addListenerOnSuppButton(new SuppTacheListener(createTache.getId()));
                tacheView.addListenerOnFinishButton(new FinishListener(createTache.getId()));


                tachesView.add(tacheView);

            } else {
                AuLongCours tache = new AuLongCours(createTache.getId(), createTache.getTitle(), createTache.getEndDate(), catTache);
                allTaches.add(tache);

                String dateFormated = formatDate.format(createTache.getEndDate().getTime());
                TacheAuLongCourView tacheView = new TacheAuLongCourView(createTache.getId(), tache.getTitle(), dateFormated,catTache.getAbreviation(), tache.isLate(), tache.getPercentage());
                tacheView.addListenerOnEditButton(new EditTacheListener(createTache.getId()));
                tacheView.addListenerOnSuppButton(new SuppTacheListener(createTache.getId()));
                tacheView.addListenerOnFinishButton(new FinishListener(createTache.getId()));


                tachesView.add(tacheView);
            }

            

            createTache.dispose();
            createTache = null;

        }

        


        update();
        
    }

    public static void updateOneTache(Tache t)
    {
        for(JPanel jp : tachesView){
            if(jp instanceof TacheView){
                if( ((TacheView)jp).getId() == t.getId()) {
                    if(t instanceof Ponctuelle){
                        TacheView tw = new TacheView(t.getId(), t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getCategorie().getAbreviation(), t.isLate());
                        tw.addListenerOnSuppButton(new SuppTacheListener(t.getId()));
                        tw.addListenerOnEditButton(new EditTacheListener(t.getId()));
                        tw.addListenerOnFinishButton(new FinishListener(t.getId()));

                        int index = tachesView.indexOf(jp);
                        tachesView.set(index, tw);
                    } else if(t instanceof  AuLongCours){
                        TacheAuLongCourView tw = new TacheAuLongCourView(t.getId(), t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getCategorie().getAbreviation(), t.isLate(), ((AuLongCours) t).getPercentage());
                        tw.addListenerOnSuppButton(new SuppTacheListener(t.getId()));
                        tw.addListenerOnEditButton(new EditTacheListener(t.getId()));
                        tw.addListenerOnFinishButton(new FinishListener(t.getId()));

                        int index = tachesView.indexOf(jp);
                        tachesView.set(index, tw);
                    }
                }
            }
        }

        update();
    }
    public static void saveTache(int id)
    {
    	for(Tache t : allTaches){
            if(t.getId() == id){
                for(JPanel jp : tachesView){
                    if(jp instanceof EditTacheView){
                        EditTacheView edit = (EditTacheView)jp;

                        t.setTitle(edit.getTitle());

                        Calendar endDate = edit.getEndDate();
                        endDate.set(Calendar.HOUR_OF_DAY, 0);
                        endDate.set(Calendar.MINUTE, 0);
                        endDate.set(Calendar.SECOND, 0);
                        endDate.set(Calendar.MILLISECOND, 0);
                        t.setEnd(endDate);

                        String cat = edit.getCategorie();
                        for(Categorie c : catList){
                            if(c.getTitre().equals(cat)){
                                t.setCategorie(c);
                            }
                        }

                        if(edit.getId() == id){
                            if(t instanceof Ponctuelle){
                                TacheView tw = new TacheView(t.getId(), t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getCategorie().getAbreviation(), t.isLate());
                                tw.addListenerOnSuppButton(new SuppTacheListener(id));
                                tw.addListenerOnEditButton(new EditTacheListener(id));
                                tw.addListenerOnFinishButton(new FinishListener(id));

                                int index = tachesView.indexOf(jp);
                                tachesView.set(index, tw);
                            } else if(t instanceof  AuLongCours){
                                TacheAuLongCourView tw = new TacheAuLongCourView(t.getId(), t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getCategorie().getAbreviation(), t.isLate(), ((AuLongCours) t).getPercentage());
                                tw.addListenerOnSuppButton(new SuppTacheListener(id));
                                tw.addListenerOnEditButton(new EditTacheListener(id));
                                tw.addListenerOnFinishButton(new FinishListener(id));

                                int index = tachesView.indexOf(jp);
                                tachesView.set(index, tw);
                            }
                        } 
                    }
                }
            }
        }

       update();
    }


    private static ArrayList<Categorie> getCategorie(){
        Categorie travail = new Categorie("Travail", "work");
        Categorie personnel = new Categorie("Personnel", "perso");
        Categorie vide = new Categorie("", "");

        ArrayList<Categorie> ret = new ArrayList<>();
        ret.add(travail);
        ret.add(personnel);

        ret.add(vide);

        return ret;
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

    	ArrayList<Tache> allTaches = new ArrayList<>();

        allTaches.add(new Ponctuelle(1, "TachePonctuelle1", end, catList.get(0)));
		allTaches.add(new Ponctuelle(2, "TachePonctuelle2", end2, catList.get(1)));
		allTaches.add(new Ponctuelle(3, "TachePonctuelle3", end, catList.get(1)));
		allTaches.add(new Ponctuelle(4, "TachePonctuelle4", end3, catList.get(0)));

		allTaches.add(new AuLongCours(5, "TacheAuLongCours5", end2, catList.get(1)));
		
        AuLongCours aloLongCour = new AuLongCours(6, "TacheAuLongCours6", end, catList.get(0));
        aloLongCour.setPercentage(40);
        allTaches.add(aloLongCour);

        return allTaches;
    }


    private static ArrayList<JPanel> getTachesView(ArrayList<Tache> allTaches)
    {
    	tachesView = new ArrayList<>();

    	int size = allTaches.size();

    	for (int i = 0; i < size ; i++ ) {


    		String dateFormated = formatDate.format(allTaches.get(i).getEnd().getTime());

    		if(allTaches.get(i) instanceof Ponctuelle)
				tachesView.add(new TacheView(allTaches.get(i).getId(), allTaches.get(i).getTitle(), dateFormated, allTaches.get(i).getCategorie().getAbreviation(), allTaches.get(i).isLate()));
    		else if (allTaches.get(i) instanceof AuLongCours)
                tachesView.add(new TacheAuLongCourView(allTaches.get(i).getId(), allTaches.get(i).getTitle(), dateFormated, allTaches.get(i).getCategorie().getAbreviation(), allTaches.get(i).isLate(), ((AuLongCours)allTaches.get(i)).getPercentage()));

    		if(tachesView.get(i) instanceof TacheView){
    			((TacheView)tachesView.get(i)).addListenerOnEditButton(new EditTacheListener(allTaches.get(i).getId()));
    			((TacheView)tachesView.get(i)).addListenerOnSuppButton(new SuppTacheListener(allTaches.get(i).getId()));
                ((TacheView)tachesView.get(i)).addListenerOnFinishButton(new FinishListener(allTaches.get(i).getId()));
    		      
            } else if(tachesView.get(i) instanceof EditTacheView){
    			((EditTacheView)tachesView.get(i)).addListenerOnSaveButton(new SaveTacheListener(allTaches.get(i).getId()));
    			((EditTacheView)tachesView.get(i)).addListenerOnSuppButton(new SuppTacheListener(allTaches.get(i).getId()));  
            }
    	}

        return tachesView;
    }

    public static ArrayList<JPanel> reOrderTacheView(ArrayList<Tache> allTaches, ArrayList<JPanel> tacheView)
    {
        ArrayList<JPanel> newTacheView = new ArrayList<JPanel>();

        int tacheSize = allTaches.size();
        for (int i = 0; i <  tacheSize; i++) {

            int tacheId = allTaches.get(i).getId();

            int tacheViewSize = tacheView.size();
            for (int j = 0; j < tacheViewSize; j++ ) {
                
                int tacheViewId = -1;

                if(tachesView.get(j) instanceof TacheView){
                    TacheView tache = (TacheView)tachesView.get(j);
                    tacheViewId = tache.getId();
                }
                if(tachesView.get(j) instanceof TacheAuLongCourView){
                     TacheAuLongCourView tache = (TacheAuLongCourView)tachesView.get(j);
                    tacheViewId = tache.getId();
                }
                if(tachesView.get(j) instanceof EditTacheView){
                    EditTacheView tache = (EditTacheView)tachesView.get(j);
                    tacheViewId = tache.getId();
                } 

                if(tacheViewId == tacheId)
                    newTacheView.add(tachesView.get(j));

            }
        }

        return newTacheView;
    }
}