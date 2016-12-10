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

/**
 * <h1>Point central de l'application</h1>
 * Contient le main()
 *
 * @author Gaëtan KUENY
 * @author Vincent THOMAS
 */
public class MainController
{
    /**
     * Instance de la fenetre principale
     */
    private static MainView f;
    /**
     * Titre de la fenetre
     */
    private static String title = "ToDo List";

	private static SortTaches sortTache;

    /**
     * Liste des Vue des Taches
     */
    private static ArrayList<JPanel> tachesView;

    /**
     * Liste des Taches
     */
    private static ArrayList<Tache> allTaches;
    /**
     * Liste des Categories
     */
    private static ArrayList<Categorie> catList;

    private static CreateTacheView createTache;

    public static Calendar currentCalendar = Calendar.getInstance();

    private static BilanView bilan;

	private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");


    /**
     * Fonction lancer a l'execution du programe.
     *
     * @param args Eventuels arguments passer au lancement du programme. Inutiles ici.
     */
    public static void main(String args[])
    {

        currentCalendar.setTime(new Date());
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

    	sortTache = new SortTachesByNewest();


    	//Recuperation des Categories, puis des Taches
        catList = getCategorie();
	    allTaches = getTaches();


	    //Tris des Taches
	    allTaches = sortTache.sort(allTaches);

        ArrayList<Tache> allTachesFilter =  allTaches.stream()
                .filter(tache -> !tache.getAchieve()).collect(Collectors.toCollection(ArrayList::new));


        //Creation du menu
        ArrayList<ArrayList<String>> menu = getMenu();


        //Creation des vues des Taches
        tachesView	= getTachesView(allTachesFilter);


        //Creation du listener pour le menu à l'aide d'une Lambda
        ActionListener menuListener = (e -> {
            String id = e.getActionCommand();

            if(id.equals(MenuItems.CARTEPONCTUELLE.toString())){

                //Appel de la methode de creation de tache, située dans le MainController
                createTache(true);
            }
            if(id.equals(MenuItems.CARTEAULONGCOURS.toString())){

                //Appel de la methode de creation de tache, située dans le MainController
                createTache(false);
            }
            if(id.equals(MenuItems.CATEGORIE.toString())){

                //Appel de la methode de d'affichage du Categorie Manager, située dans le MainController
                getCategorieManager();
            }
            if(id.equals(MenuItems.SAUVEGARDER.toString())){

                //Appel de la methode de sauvegarde des Categories et des Taches, située dans le MainController
                saveAll();
            }
            if(id.equals(MenuItems.BILAN.toString())){

                //Appel de la methode de d'affichage de la fenetre d'edition de bilan, située dans le MainController
                bilan();
            }
        });


        //Creation du listener pour les boutons de tris à l'aide d'une Lambda
        ActionListener sortListener = (e -> {
            switch (e.getActionCommand()){
                case "simple" :

                    //Appel de la methode de tris des Taches, située dans le MainController
                    changeSort("simple");
                    break;

                case "intermediaire" :

                    //Appel de la methode de tris des Taches, située dans le MainController
                    changeSort("intermediaire");
                    break;
            }
        });


        //Instantiation de la fenetre principale
        f = new MainView(title, tachesView, menu, menuListener, sortListener);


        //Affichage de la fenetre principale
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
	    submenu3.add(MenuItems.BILAN.toString());

	    menu.add(submenu);
	    menu.add(submenu2);
	    menu.add(submenu3);

	    return menu;

    }

    private static void changeSort(String typeSort)
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



    /**
     * Ouvre le Categorie Manager
     */
    static void getCategorieManager(){

        //Declaration des listes des Titres et Abreviation de Categories
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> labelList = new ArrayList<>();


        //Affectation des listes des Titres et Abreviation de Categories
        for(Categorie c : catList){
            titleList.add(c.getTitre());
            labelList.add(c.getAbreviation());
        }


        //Instantition de la fenetre du Categorie Manager
        CategorieManagerView ecv = new CategorieManagerView(titleList, labelList);


        //Ajout d'un listener sur les boutons et la JList du CategorieManager
        ecv.addListSelectionListener(ecv);
        ecv.addListenerOnAddButton(e -> CategorieManagerController.addCategorie(ecv, catList));
        ecv.addListenerOnEditButton(e -> CategorieManagerController.editCategorie(ecv, catList));
        ecv.addListenerOnSuppButton(e -> CategorieManagerController.removeCategorie(ecv, catList, allTaches));


        //Affichage de la fenetre du Categorie Manager
        ecv.setVisible(true);
    }

    static void removeTache(int id){
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

    private static void cancelCreateTache()
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

    private static void createTache(Boolean ponctuelle)
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


    /**
     * Change la vue de la Tache selectionner pour une vue permetant la modification
     *
     * @param id ID de la Tache a modifier
     */
    static void editTache(int id){

        //Recherche de la Tache a modifier dans la liste des Taches
    	for(Tache t : allTaches){
            if(t.getId() == id){


                //Declaration de la liste des Titres des Categories et de l'index de la Categorie actuel de la Tache dans cette liste
                ArrayList<String> stringList = new ArrayList<>();
                int indexCat = 0;


                //On verifi que la liste des Categories n'est pas vide
                if(catList.size() != 0){


                    //On ajoutes tous les Titres des Categories a la liste des Titres
                    for(Categorie cat : catList){
                        stringList.add(cat.getTitre());


                        //Si le Titre correspond a celui de la Tache a modifier, on enregistre son index
                        if(t.getCategorie().getTitre().equals(cat.getTitre())){
                            indexCat = catList.indexOf(cat);
                        }
                    }


                } else { //Si la liste des categories est vide, on ajoute un champ vide pour empecher une erreur du a la creation d'une JCombobox vide
                    stringList.add("");
                    indexCat = 0;
                }


                //Instanciation de la vue d'edition de la Tache
                EditTacheView edit = new EditTacheView(id, t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getEnd().getTime(), stringList.toArray(new String[stringList.size()]), indexCat, t.isLate(), t.getDateCreation());


                //Ajout des Listeners sur les boutons de cette nouvelle vue
                edit.addListenerOnSuppButton(new TacheListener(id, "Suppression"));
                edit.addListenerOnSaveButton(new TacheListener(id, "Sauvegarde"));


                //On remplace l'ancienne vue par la nouvelle dans la liste des vues
                tachesView.stream().filter(jp -> jp instanceof TacheView).forEach(jp -> {
                    TacheView tv = (TacheView) jp;

                    if (tv.getId() == id) {
                        int index = tachesView.indexOf(jp);
                        tachesView.set(index, edit);
                    }
                });
            }
        }


        //On met a jours l'affichage
        updateView();
    }


    private static void addTache()
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


    /**
     * Sauvegarde les changement sur la Tache selectionner et remet sa vue par defaut
     *
     * @param id ID de la Tache a sauvegarder
     */
    static void saveTache(int id){

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


    /**
     * Charge les Categories enregistrées sur le fichier ".CatSave.sav". Si le fichier n'existe pas, charge les categories par defaut : "Travail" et "Personnel"
     *
     * @return ArrayList&lt;Categorie&gt; Liste des Categories chargées
     */
    private static ArrayList<Categorie> getCategorie(){

        //Declaration de la liste des Categories a retourner
	    ArrayList<Categorie> ret = new ArrayList<>();


	    //Essai d'ouvrir le fichier et en recuperer les données sauvegardé dessus
        try{

            //Ouverture du fichier
            FileInputStream fis = new FileInputStream(".CatSave.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Recuperation des données
            ret = (ArrayList<Categorie>) ois.readObject();

            //Fermeture du fichier
            ois.close();
            fis.close();


            //Affichage d'un message indiquant que le chargement a reussi
            JOptionPane.showMessageDialog(null, "Sauvegardes trouvés. Chargement des catégories sauvegardées ...", "Chargement des categories", JOptionPane.INFORMATION_MESSAGE);

        }catch(Exception e){ //Si une erreur se produit

            //Chargement des Categories par defaut
            ret.add(new Categorie("Personnel", "Perso"));
            ret.add(new Categorie("Travail", "Trav."));


            //Affichage d'un message indiquant que le chargement a échoué
            JOptionPane.showMessageDialog(null, "Aucunes sauvegardes trouvées, ou fichier coromput. Chargement des catégories par defaut ...", "Chargement des categories", JOptionPane.WARNING_MESSAGE);


            //Affichage de l'erreur en console pour un eventuel débugage
            e.printStackTrace();
        }

        //On renvoi la liste chargée
        return ret;
    }


    /**
     * Charge les Taches enregistrées sur le fichier ".TacheSave.sav". Si le fichier n'existe pas, charge une liste vide
     *
     * @return ArrayList&lt;Tache&gt; Liste des Taches chargées
     */
    private static ArrayList<Tache> getTaches()
    {
        //Declaration de la liste des Taches a retourner
        ArrayList<Tache> allTaches = new ArrayList<>();


        //Essai d'ouvrir le fichier et en recuperer les données sauvegardé dessus
        try{

            //Ouverture du fichier
            FileInputStream fis = new FileInputStream(".TacheSave.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Recuperation des données
            allTaches = (ArrayList<Tache>) ois.readObject();

            //Fermeture du fichier
            ois.close();
            fis.close();


            //Affichage d'un message indiquant que le chargement a reussi
            JOptionPane.showMessageDialog(null, "Sauvegardes trouvés. Chargement des taches sauvegardées ...", "Chargement des taches", JOptionPane.INFORMATION_MESSAGE);

        }catch(Exception e){ //Si une erreur se produit

            //On laisse la liste vide


            //Affichage d'un message indiquant que le chargement a échoué
            JOptionPane.showMessageDialog(null, "Aucunes sauvegardes trouvées, ou fichier coromput. Chargement d'un environement vierge ...", "Chargement des taches", JOptionPane.WARNING_MESSAGE);


            //Affichage de l'erreur en console pour un eventuel débugage
            e.printStackTrace();
        }


        //On renvoi la liste chargée
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


    /**
     * Sauvegarde sucessivement la liste des Categories et la liste des Taches dans, respectivement, les fichiers ".CatSave.sav" et ".TacheSave.sav"
     */
    private static void saveAll() {

        //Sauvegarde des Categories
        saveCategorieInFile(".CatSave.sav");


        //Sauvegarde des Taches
        saveTachesInFile(".TacheSave.sav");
    }


    /**
     * Sauvegarde la liste des Taches dans le fichier specifié
     *
     * @param file Chemin vers le fichier dans lequel sauvegarder les Taches
     */
    private static void saveTachesInFile(String file) {

        //Sauvegarde les Taches dans le fichier
        saveInFIle(file, allTaches);
    }


    /**
     * Sauvegarde la liste des Categories dans le fichier specifié
     *
     * @param file Chemin vers le fichier dans lequel sauvegarder les Categories
     */
    private static void saveCategorieInFile(String file) {

        //Sauvegarde les Taches dans le fichier
        saveInFIle(file, catList);
    }

    /**
     * Serialize et enregistre dans le fichier specifier l'Object passer en parametre
     *
     * @param file chemin vers le fichier dans lequel sauvegarder l'Object
     * @param o Object a sauvegarder
     */
    private static void saveInFIle(String file, Object o){

        //Essai d'ouvrir le fichier et d'y inscrire les donné de o
        try{

            //Ouverture du fichier
            FileOutputStream fileOut= new FileOutputStream(file);
            ObjectOutputStream objectOut= new ObjectOutputStream(fileOut);

            //Ecriture des données de o
            objectOut.writeObject(o);

            //Fermeture du fichier
            objectOut.close();
            fileOut.close();

            //Affichage d'un message indiquant que la sauvegarde a reussi
            JOptionPane.showMessageDialog(null, "Sauvegardes Réussi", "Sauvegardes Réussi", JOptionPane.INFORMATION_MESSAGE);

        }catch(Exception e){ //Si une erreur se produit

            //Affichage d'un message indiquant que la sauvegarde a echoue
            JOptionPane.showMessageDialog(null, "Sauvegardes Réussi", "Sauvegardes Réussi", JOptionPane.ERROR_MESSAGE);


            //Affichage de l'erreur en console pour un eventuel débugage
            e.printStackTrace();
        }
    }

    private static void bilan()
    {
        bilan = new BilanView();
        bilan.setVisible(true);
    }

    static void generateBilan(Date begin, Date end)
    {
        ArrayList<Tache> allTachesFilter = allTaches.stream()
                .filter( tache ->  tache.getEnd().getTime().compareTo(begin) > 0 && tache.getEnd().getTime().compareTo(end) <= 0 && !tache.getAchieve())
                .collect(Collectors.toCollection(ArrayList::new));

        int pourcentageRealiseInTime = getPourcentageRealiserInTime(allTachesFilter);
        int pourcentageRealiseNotInTime = getPourcentageRealiserNotInTime(allTachesFilter);
        int pourcentageNotRealiser = getPourcentageNotRealiser(allTachesFilter);

        bilan.updateView(allTachesFilter, pourcentageRealiseInTime, pourcentageRealiseNotInTime, pourcentageNotRealiser);
    }

    private static int getPourcentageRealiserInTime(ArrayList<Tache> taches)
    {
        int realise =  0;
        int notRealiser = 0;

        for (Tache t : taches) {
            if(t.getAchieve() && t.getAchieveDate().compareTo(t.getEnd()) <= 0)
                realise++;
            else
                notRealiser++;
        }

        if(realise + notRealiser == 0)
            return 0;

        return ((realise * 100) / (realise + notRealiser));
    }

    private static int getPourcentageRealiserNotInTime(ArrayList<Tache> taches)
    {
        int realise =  0;
        int notRealiser = 0;

        for (Tache t : taches) {
            if(t.getAchieve() && t.getAchieveDate().compareTo(t.getEnd()) > 0)
                realise++;
            else
                notRealiser++;
        }

        if(realise + notRealiser == 0)
            return 0;

        return ((realise * 100) / (realise + notRealiser));
    }

    private static int getPourcentageNotRealiser(ArrayList<Tache> taches)
    {
        int realise =  0;
        int notRealiser = 0;

        for (Tache t : taches) {
            if(t.getAchieve())
                realise++;
            else
                notRealiser++;
        }

        if(realise + notRealiser == 0)
            return 0;

        return ((notRealiser * 100) / (realise + notRealiser));
    }
}