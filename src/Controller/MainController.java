package Controller;

import Model.AuLongCours;
import Model.MenuItems;
import Model.Ponctuelle;
import Model.Categorie;
import Model.Tache;
import View.*;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class MainController
{
	public static MainView f;
	private static String title = "ToDo List";

	private static SortTaches sortTache;

    private static ArrayList<JPanel> tachesView;

    private static ArrayList<Tache> allTaches;
    private static ArrayList<Categorie> catList;

    private static CreateTacheView createTache;

    private static Calendar currentCalendar = Calendar.getInstance();

    private static int width;


	private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");


	public static void main(String args[])
    {

        currentCalendar.setTime(new Date());
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

    	sortTache = new SortTachesByNewest();

        catList = getCategorie();
	    allTaches = getTaches();

	    allTaches = sortTache.sort(allTaches);

        ArrayList<Tache> allTachesFilter =  allTaches.stream()
                .filter(tache -> !tache.getAchieve()).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ArrayList<String>> menu = getMenu();
        tachesView	= getTachesView(allTachesFilter);


        ActionListener menuListener = (e -> {
            String id = e.getActionCommand();

            if(id.equals(MenuItems.CARTEPONCTUELLE.toString())){
                createTache(true);
            }
            if(id.equals(MenuItems.CARTEAULONGCOURS.toString())){
                createTache(false);
            }
            if(id.equals(MenuItems.CATEGORIE.toString())){
                editCategorie();
            }
            if(id.equals(MenuItems.SAUVEGARDER.toString())){
                saveAll();
            }
            if(id.equals(MenuItems.BILAN.toString())){
                bilan();
            }
        });

        ActionListener sortListener = (e -> {
            switch (e.getActionCommand()){
                case "simple" :
                    changeSort("simple");
                    break;

                case "intermediaire" :
                    changeSort("intermediaire");
                    break;
            }
        });


        f = new MainView(title, tachesView, menu, menuListener, sortListener);

        f.setVisible(true);

        width = f.getWidth();

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
	    submenu3.add(MenuItems.BILAN.toString());

	    menu.add(submenu);
	    menu.add(submenu2);
	    menu.add(submenu3);

	    return menu;

    }

    static void changeSort(String typeSort)
    {

        switch (typeSort) {
            case "simple" :
                sortTache = new SortTachesByNewest();
                break;

            case "intermediaire" :
                sortTache = new SortTachesByIntermediaire();
                break;
        }

        update();
    }

    private static void update()
    {

        allTaches = sortTache.sort(allTaches);

        ArrayList<Tache> allTachesFilter =  allTaches.stream()
                .filter(tache -> !tache.getAchieve()).collect(Collectors.toCollection(ArrayList::new));

	    tachesView	= reOrderTacheView(allTachesFilter, tachesView);

    	updateView();
    }

    static void updateView()
    {
        f.updateView(title, tachesView);
    }

    static void editCategorie()
    {
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> labelList = new ArrayList<>();

        for(Categorie c : catList){
            titleList.add(c.getTitre());
            labelList.add(c.getAbreviation());
        }

        CategorieManagerView ecv = new CategorieManagerView(titleList, labelList);

        ecv.addListSelectionListener(ecv);
        ecv.addListenerOnAddButton(e -> CategorieManagerController.addCategorie(ecv, catList));
        ecv.addListenerOnEditButton(e -> CategorieManagerController.editCategorie(ecv, catList));
        ecv.addListenerOnSuppButton(e -> CategorieManagerController.removeCategorie(ecv, catList, allTaches));

        ecv.setVisible(true);
    }

    static void removeTache(int id)
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

    static void cancelCreateTache()
    {
        if(createTache != null) {

            createTache.dispose();
            createTache = null;
        }
    }

    static void updateProgressTache(int id)
    {

        int i = 0;
        int size = allTaches.size();
        boolean find = false;

        while(!find && i < size) {

            if (allTaches.get(i).getId() == id) {

                if(allTaches.get(i) instanceof Ponctuelle) {

                    allTaches.get(i).setAchieve(true);

                } else if(allTaches.get(i) instanceof AuLongCours) {

                    ((AuLongCours)allTaches.get(i)).setPercentage(((AuLongCours)allTaches.get(i)).getPercentage() + 10);

                }

                if( allTaches.get(i).getAchieve())
                    JOptionPane.showMessageDialog(f, "La tâche " +  allTaches.get(i).getTitle() + " est terminée");

                find = true;

                updateOneTache(allTaches.get(i));
                update();

            }
            i++;
        }



    }

    static void createTache(Boolean ponctuelle)
    {
        ArrayList<String> stringList = catList.stream().map(Categorie::getTitre).collect(Collectors.toCollection(ArrayList::new));

        ActionListener listener;

        if(createTache != null) {
            createTache.dispose();
            createTache = null;
        }

        listener = (e ->
        {
            switch (e.getActionCommand()) {
                case "Save":
                    MainController.addTache();
                    break;
                case "Cancel":
                    MainController.cancelCreateTache();
                    break;
            }
        });

        int id = (int) (new Date().getTime()/1000);
        createTache = new CreateTacheView(id, stringList.toArray(new String[stringList.size()]), listener, ponctuelle);
        createTache.setVisible(true);

    }

    static void editTache(int id)
    {
    	for(Tache t : allTaches){
            if(t.getId() == id){


                ArrayList<String> stringList = new ArrayList<>();
                int indexCat = 0;

                if(catList.size() != 0){
                    for(Categorie cat : catList){
                        stringList.add(cat.getTitre());

                        if(t.getCategorie().getTitre().equals(cat.getTitre())){
                            indexCat = catList.indexOf(cat);
                        }
                    }
                } else {
                    stringList.add("");
                    indexCat = 0;
                }

                EditTacheView edit = new EditTacheView(id, t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getEnd().getTime(), stringList.toArray(new String[stringList.size()]), indexCat, t.isLate(), t.getDateCreation());

                edit.addListenerOnSuppButton(new TacheListener(id, "Suppression"));
                edit.addListenerOnSaveButton(new TacheListener(id, "Sauvegarde"));

                tachesView.stream().filter(jp -> jp instanceof TacheView).forEach(jp -> {
                    TacheView tv = (TacheView) jp;

                    if (tv.getId() == id) {
                        int index = tachesView.indexOf(jp);
                        tachesView.set(index, edit);
                    }
                });
            }
        }

       updateView();
    }

    static void addTache()
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



                long diff = createTache.getEndDate().getTime().getTime() - currentCalendar.getTime().getTime();
                int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                String dateBeginFormated = formatDate.format(currentCalendar.getTime().getTime());
                String dateEndFormated = formatDate.format(createTache.getEndDate().getTime());
                TacheView tacheView = new TacheView(createTache.getId(), tache.getTitle(), dateBeginFormated, dateEndFormated, interval, catTache.getAbreviation(), tache.isLate());
                tacheView.addListenerOnEditButton(new TacheListener(createTache.getId(), "Edition"));
                tacheView.addListenerOnSuppButton(new TacheListener(createTache.getId(), "Suppression"));
                tacheView.addListenerOnFinishButton(new TacheListener(createTache.getId(), "Finish"));


                tachesView.add(tacheView);

            } else {
                AuLongCours tache = new AuLongCours(createTache.getId(), createTache.getTitle(), createTache.getBeginDate(), createTache.getEndDate(), catTache);
                allTaches.add(tache);

                long diff = createTache.getEndDate().getTime().getTime() - currentCalendar.getTime().getTime();
                int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                String dateFormatedEnd      = formatDate.format(createTache.getEndDate().getTime());
                String dateFormatedBegin    = formatDate.format(createTache.getBeginDate().getTime());
                TacheAuLongCourView tacheView = new TacheAuLongCourView(createTache.getId(), tache.getTitle(), dateFormatedBegin, dateFormatedEnd, interval,catTache.getAbreviation(), tache.isLate(), tache.getPercentage());
                tacheView.addListenerOnEditButton(new TacheListener(createTache.getId(), "Edition"));
                tacheView.addListenerOnSuppButton(new TacheListener(createTache.getId(), "Suppression"));
                tacheView.addListenerOnFinishButton(new TacheListener(createTache.getId(), "Finish"));


                tachesView.add(tacheView);
            }



            createTache.dispose();
            createTache = null;

        }




        update();

    }

    private static void updateOneTache(Tache t)
    {
        tachesView.stream().filter(jp -> jp instanceof TacheView).filter(jp -> ((TacheView) jp).getId() == t.getId()).forEach(jp -> {
            if (t instanceof Ponctuelle) {

                long diff = t.getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
                int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                TacheView tw = new TacheView(t.getId(), t.getTitle(),formatDate.format(t.getDateCreation().getTime()) , formatDate.format(t.getEnd().getTime()), interval, t.getCategorie().getAbreviation(), t.isLate());
                tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                int index = tachesView.indexOf(jp);
                tachesView.set(index, tw);
            } else if (t instanceof AuLongCours) {

                long diff = t.getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
                int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                TacheAuLongCourView tw = new TacheAuLongCourView(t.getId(), t.getTitle(),formatDate.format(((AuLongCours)t).getBegin().getTime()), formatDate.format(t.getEnd().getTime()), interval, t.getCategorie().getAbreviation(), t.isLate(), ((AuLongCours) t).getPercentage());
                tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                int index = tachesView.indexOf(jp);
                tachesView.set(index, tw);
            }
        });


        ArrayList<String> stringList = new ArrayList<>();
        int indexCat = 0;

        if(catList.size() != 0){
            for(Categorie cat : catList){
                stringList.add(cat.getTitre());

                if(t.getCategorie().getTitre().equals(cat.getTitre())){
                    indexCat = catList.indexOf(cat);
                }
            }
        } else {
            stringList.add(" ");
            indexCat = 0;
        }

        final int id = indexCat;

        tachesView.stream().filter(jp -> jp instanceof EditTacheView).filter(jp -> ((EditTacheView) jp).getId() == t.getId()).forEach(jp -> {

            EditTacheView edit = new EditTacheView(t.getId(), t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getEnd().getTime(),stringList.toArray(new String[stringList.size()]) , id, t.isLate(), t.getDateCreation());
            edit.addListenerOnSuppButton(new TacheListener(id, "Suppression"));
            edit.addListenerOnSaveButton(new TacheListener(id, "Sauvegarde"));

            int index = tachesView.indexOf(jp);
            tachesView.set(index, edit);

        });
    }

    static void updateAllTaches() {
        allTaches.forEach(MainController::updateOneTache);
        update();
    }

    static void saveTache(int id)
    {
        allTaches.stream().filter(t -> t.getId() == id).forEach(t -> tachesView.stream().filter(jp -> jp instanceof EditTacheView).forEach(jp -> {
            EditTacheView edit = (EditTacheView) jp;

            t.setTitle(edit.getTitle());

            Calendar endDate = edit.getEndDate();
            endDate.set(Calendar.HOUR_OF_DAY, 0);
            endDate.set(Calendar.MINUTE, 0);
            endDate.set(Calendar.SECOND, 0);
            endDate.set(Calendar.MILLISECOND, 0);
            t.setEnd(endDate);

            String cat = edit.getCategorie();
            catList.stream().filter(c -> c.getTitre().equals(cat)).forEach(t::setCategorie);

            if (edit.getId() == id) {
                if (t instanceof Ponctuelle) {

                    long diff =  t.getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
                    int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                    TacheView tw = new TacheView(t.getId(), t.getTitle(), formatDate.format(t.getDateCreation().getTime()) ,formatDate.format(t.getEnd().getTime()), interval, t.getCategorie().getAbreviation(), t.isLate());
                    tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                    tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                    tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                    int index = tachesView.indexOf(jp);
                    tachesView.set(index, tw);
                } else if (t instanceof AuLongCours) {

                    long diff = t.getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
                    int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                    TacheAuLongCourView tw = new TacheAuLongCourView(t.getId(), t.getTitle(), formatDate.format(((AuLongCours)t).getBegin().getTime()), formatDate.format(t.getEnd().getTime()), interval,  t.getCategorie().getAbreviation(), t.isLate(), ((AuLongCours) t).getPercentage());
                    tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                    tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                    tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                    int index = tachesView.indexOf(jp);
                    tachesView.set(index, tw);
                }
            }
        }));

       update();
    }


    private static ArrayList<Categorie> getCategorie(){
	    ArrayList<Categorie> ret = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(".CatSave.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ret = (ArrayList<Categorie>) ois.readObject();
            ois.close();
            fis.close();

            JOptionPane.showMessageDialog(null, "Sauvegardes trouvés. Chargement des catégories sauvegardées ...", "Chargement des categories", JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException ioe){
            ret.add(new Categorie("Personnel", "Perso"));
            ret.add(new Categorie("Travail", "Trav."));

            JOptionPane.showMessageDialog(null, "Aucunes sauvegardes trouvés. Chargement des catégories par defaut ...", "Chargement des categories", JOptionPane.WARNING_MESSAGE);

        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
        }

        return ret;
    }
    

    private static ArrayList<Tache> getTaches()
    {
        ArrayList<Tache> allTaches = new ArrayList<>();

        try{
            FileInputStream fis = new FileInputStream(".TacheSave.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            allTaches = (ArrayList<Tache>) ois.readObject();
            ois.close();
            fis.close();

            JOptionPane.showMessageDialog(null, "Sauvegardes trouvés. Chargement des taches sauvegardées ...", "Chargement des taches", JOptionPane.INFORMATION_MESSAGE);
        }catch(IOException ioe){

            JOptionPane.showMessageDialog(null, "Aucunes sauvegardes trouvés. Chargement d'un environement vierge ...", "Chargement des taches", JOptionPane.WARNING_MESSAGE);
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
        }

        return allTaches;
    }


    private static ArrayList<JPanel> getTachesView(ArrayList<Tache> allTaches)
    {
    	tachesView = new ArrayList<>();

    	int size = allTaches.size();

    	for (int i = 0; i < size ; i++ ) {


    		String dateFormatedEnd = formatDate.format(allTaches.get(i).getEnd().getTime());
            String dateFormatedBegin = formatDate.format(allTaches.get(i).getDateCreation().getTime());


    		if(allTaches.get(i) instanceof Ponctuelle) {

                long diff =  allTaches.get(i).getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
                int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                tachesView.add(new TacheView(allTaches.get(i).getId(), allTaches.get(i).getTitle(), dateFormatedBegin, dateFormatedEnd, interval, allTaches.get(i).getCategorie().getAbreviation(), allTaches.get(i).isLate()));

            } else if (allTaches.get(i) instanceof AuLongCours) {

                long diff = allTaches.get(i).getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
                int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

                dateFormatedBegin = formatDate.format((((AuLongCours) allTaches.get(i)).getBegin().getTime()));
                tachesView.add(new TacheAuLongCourView(allTaches.get(i).getId(), allTaches.get(i).getTitle(), dateFormatedBegin, dateFormatedEnd, interval, allTaches.get(i).getCategorie().getAbreviation(), allTaches.get(i).isLate(), ((AuLongCours)allTaches.get(i)).getPercentage()));

            }

    		if(tachesView.get(i) instanceof TacheView){
    			((TacheView)tachesView.get(i)).addListenerOnEditButton(new TacheListener(allTaches.get(i).getId(), "Edition"));
    			((TacheView)tachesView.get(i)).addListenerOnSuppButton(new TacheListener(allTaches.get(i).getId(), "Suppression"));
                ((TacheView)tachesView.get(i)).addListenerOnFinishButton(new TacheListener(allTaches.get(i).getId(), "Finish"));
    		      
            } else if(tachesView.get(i) instanceof EditTacheView){
    			((EditTacheView)tachesView.get(i)).addListenerOnSaveButton(new TacheListener(allTaches.get(i).getId(), "Sauvegarde"));
    			((EditTacheView)tachesView.get(i)).addListenerOnSuppButton(new TacheListener(allTaches.get(i).getId(), "Suppression"));
            }
    	}

        return tachesView;
    }

    private static ArrayList<JPanel> reOrderTacheView(ArrayList<Tache> allTaches, ArrayList<JPanel> tacheView)
    {
        ArrayList<JPanel> newTacheView = new ArrayList<>();

        for (Tache allTache : allTaches) {

            int tacheId = allTache.getId();

            int tacheViewSize = tacheView.size();
            for (int j = 0; j < tacheViewSize; j++) {

                int tacheViewId = -1;

                if (tachesView.get(j) instanceof TacheView) {
                    TacheView tache = (TacheView) tachesView.get(j);
                    tacheViewId = tache.getId();
                }
                if (tachesView.get(j) instanceof TacheAuLongCourView) {
                    TacheAuLongCourView tache = (TacheAuLongCourView) tachesView.get(j);
                    tacheViewId = tache.getId();
                }
                if (tachesView.get(j) instanceof EditTacheView) {
                    EditTacheView tache = (EditTacheView) tachesView.get(j);
                    tacheViewId = tache.getId();
                }

                if (tacheViewId == tacheId)
                    newTacheView.add(tachesView.get(j));

            }
        }

        return newTacheView;
    }

    static void saveAll() {
        saveCategorieInFile(".CatSave.sav");
        saveTachesInFile(".TacheSave.sav");
    }

    private static void saveTachesInFile(String file) {
        saveInFIle(file, allTaches);
    }

    private static void saveCategorieInFile(String file) {
        saveInFIle(file, catList);
    }

    private static void saveInFIle(String file, Object o)
    {
        try{
            FileOutputStream fileOut= new FileOutputStream(file);
            ObjectOutputStream objectOut= new ObjectOutputStream(fileOut);
            objectOut.writeObject(o);
            objectOut.close();
            fileOut.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    static void bilan()
    {
        BilanView bilan = new BilanView();
        bilan.setVisible(true);
    }
}