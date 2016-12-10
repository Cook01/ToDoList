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

    /**
     * Instance de la fenetre "CreateTache"
     */
    private static CreateTacheView createTache;

    /**
     * date actuel
     */
    public static Calendar currentCalendar = Calendar.getInstance();

    /**
     * Instance de la vue Bilan
     */
    private static BilanView bilan;

    /**
     * Format des dates
     */
	private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * étape en pourcentage d'avancement d'une tache
     */
    private static int avancement = 5;


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


    /**
     * Récupère les items du menu
     *
     * @return ArrayList<ArrayList<String>> items du menu ( 1 er argument de ArrayList<String> = titre du menu déroulant )
     */
    private static ArrayList<ArrayList<String>> getMenu()
    {

		ArrayList<ArrayList<String>> menu   = new ArrayList<>();
	    ArrayList<String> submenu           = new ArrayList<>();
	    ArrayList<String> submenu2          = new ArrayList<>();
	    ArrayList<String> submenu3          = new ArrayList<>();

        // On ajoute le titre du menu déroulant
	    submenu.add("Créer");
        // On ajoute les items de notre menu
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

    /**
     * Change le type de tri
     *
     * @param typeSort String du nouveau type de tri
     */
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

        // On update afin de voir les changements
        update();
    }

    /**
     * Mise a jours des des TachesView et mise à jour de la fenetre
     */
    private static void update()
    {

        // On tri nos taches
        allTaches = sortTache.sort(allTaches);

        // On ne garde que celle qui ne sont pas fini
        ArrayList<Tache> allTachesFilter =  allTaches.stream()
                .filter(tache -> !tache.getAchieve()).collect(Collectors.toCollection(ArrayList::new));

        // On ré-ordonne nos TachesView
	    tachesView	= reOrderTacheView(allTachesFilter, tachesView);

        // On met à jour la vue
    	updateView();
    }

    /**
     * Mise à jour de la vue principal
     */
    static void updateView()
    {
        // On appel la fonctio de mise a jour de notre fenetre principal
        f.updateView(title, tachesView);
    }



    /**
     * Ouvre le Categorie Manager
     */
    static void getCategorieManager()
    {

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

    /**
     * Suppression d'une tache par son id
     *
     * @param id int id de la tache
     */
    static void removeTache(int id)
    {
        int i = 0;
    	int size = allTaches.size();
    	boolean find = false;

        // On cherche notre tache dans la list des taches
    	while(!find && i < size) {

    		if (allTaches.get(i).getId() == id) {

                // On la supprime de l'ArrayList stockant toutes les taches.
    			allTaches.remove(i);
    			find = true;

    		}
    		i++;
    	}

        size = tachesView.size();
        find = false;
        i = 0;

        // On cherche notre tache dans la list des TacheView
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

                // On la supprime de l'ArrayList stockant toutes les TacheViex.
                tachesView.remove(i);
                find = true;

            }

            i++;

        }

        // On met le tout à jour
        update();
    }

    /**
     * Annulation de la création d'une tache
     */
    private static void cancelCreateTache()
    {
        // SI la fenetre createTache existe
        if(createTache != null) {
            // On ne l'affiche plus
            createTache.dispose();

            // on indique qu'elle n'existe plus
            createTache = null;
        }
    }

    /**
     * Mise à jour de l'avancement d'une tache
     *
     * @param id id de la tache
     */
    static void updateProgressTache(int id)
    {

        int i = 0;
        int size = allTaches.size();
        boolean find = false;

        // On cherche notre tache
        while(!find && i < size) {

            if (allTaches.get(i).getId() == id) {

                if(allTaches.get(i) instanceof Ponctuelle) {

                    // Si c'est une tache ponctuelle on indique qu'elle est terminé
                    allTaches.get(i).setAchieve(true);

                } else if(allTaches.get(i) instanceof AuLongCours) {

                    // Si c'est une tache AuLongCours, on met à jour son pourcentage
                    ((AuLongCours)allTaches.get(i)).setPercentage(((AuLongCours)allTaches.get(i)).getPercentage() + avancement);

                }

                // SI la tache est fini après l'avoir modifié
                if( allTaches.get(i).getAchieve()) // On affiche une fenetre d'information
                    JOptionPane.showMessageDialog(f, "La tâche " +  allTaches.get(i).getTitle() + " est terminée");

                find = true;

                // mise a jour de la tache concerné
                updateOneTache(allTaches.get(i));

                // Mise a jour global
                update();

            }
            i++;
        }



    }

    /**
     * Ouvre la fenetre de création de tache
     *
     * @param ponctuelle Boolean true : si la tache est ponctuelle, false sinon
     */
    private static void createTache(Boolean ponctuelle)
    {

        // On récupère le liste des titres de chaque tache
        ArrayList<String> stringList = catList.stream().map(Categorie::getTitre).collect(Collectors.toCollection(ArrayList::new));

        ActionListener listener;

        if(createTache != null) {
            // Si une fenetre createTache est déjà ouvert, on la ferme
            createTache.dispose();
            createTache = null;
        }

        // On crée le listener de nos boutons
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

        // On crée un id unique (utilisation d'un timestamp 👌🏻🤘🏻) [on divise pas 1000, car sinon cela est trop grand pour un int]
        int id = (int) (new Date().getTime()/1000);

        // On récupere l'instance de notre fenetre CreateTacheView
        createTache = new CreateTacheView(id, stringList.toArray(new String[stringList.size()]), listener, ponctuelle);

        // On l'affiche
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


                //On verifie que la liste des Categories n'est pas vide
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

    /**
     * Ajout d'une nouvelle tache à partir de la fenetre createTache
     */
    private static void addTache()
    {

        // Si l'instance en n'existe pas , on stop tout
        if(createTache == null)
            return;

        // On récupère le texte de la catégorie
        String cat = createTache.getCategorie();

        Categorie catTache = new Categorie("", "");

        // On recherche notre catégorie dans la liste existante
        for(Categorie c : catList){
            if(c.getTitre().equals(cat)){
                catTache = c;
            }
        }

        long diff = createTache.getEndDate().getTime().getTime() - currentCalendar.getTime().getTime();
        int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

        if(createTache.getIsPonctuelle()) {

            // On crée notre tache à partir de ce que nous fourni la fenetre createTache
            Ponctuelle tache = new Ponctuelle(createTache.getId(), createTache.getTitle(), createTache.getEndDate(), catTache);

            allTaches.add(tache);

            // On crée sa vue
            String dateBeginFormated = formatDate.format(currentCalendar.getTime().getTime());
            String dateEndFormated = formatDate.format(createTache.getEndDate().getTime());
            TacheView tacheView = new TacheView(createTache.getId(), tache.getTitle(), dateBeginFormated, dateEndFormated, interval, catTache.getAbreviation(), tache.isLate());
            tacheView.addListenerOnEditButton(new TacheListener(createTache.getId(), "Edition"));
            tacheView.addListenerOnSuppButton(new TacheListener(createTache.getId(), "Suppression"));
            tacheView.addListenerOnFinishButton(new TacheListener(createTache.getId(), "Finish"));

            tachesView.add(tacheView);

        } else {

            // On crée notre tache à partir de ce que nous fourni la fenetre createTache
            AuLongCours tache = new AuLongCours(createTache.getId(), createTache.getTitle(), createTache.getBeginDate(), createTache.getEndDate(), catTache);
            allTaches.add(tache);

            // On crée sa vue
            String dateFormatedEnd      = formatDate.format(createTache.getEndDate().getTime());
            String dateFormatedBegin    = formatDate.format(createTache.getBeginDate().getTime());
            TacheAuLongCourView tacheView = new TacheAuLongCourView(createTache.getId(), tache.getTitle(), dateFormatedBegin, dateFormatedEnd, interval,catTache.getAbreviation(), tache.isLate(), tache.getPercentage());
            tacheView.addListenerOnEditButton(new TacheListener(createTache.getId(), "Edition"));
            tacheView.addListenerOnSuppButton(new TacheListener(createTache.getId(), "Suppression"));
            tacheView.addListenerOnFinishButton(new TacheListener(createTache.getId(), "Finish"));


            tachesView.add(tacheView);
        }


        // On ferme notre fenetre
        createTache.dispose();
        createTache = null;

        // On met à jour le tout
        update();

    }

    /**
     * Mise à jour d'une seul tache
     *
     * @param t Tache, la tache à metre à jour
     */
    private static void updateOneTache(Tache t)
    {
        // On filtre afin de ne récuper que la TacheView d'id t.getId()
        tachesView.stream().filter(jp -> jp instanceof TacheView).filter(jp -> ((TacheView) jp).getId() == t.getId()).forEach(jp -> {

            long diff = t.getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
            int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

            int index = tachesView.indexOf(jp);

            if (t instanceof Ponctuelle) {

                // On crée un nouvelle tacheView à partir de la Tache t
                TacheView tw = new TacheView(t.getId(), t.getTitle(),formatDate.format(t.getDateCreation().getTime()) , formatDate.format(t.getEnd().getTime()), interval, t.getCategorie().getAbreviation(), t.isLate());
                tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                // On met à jour la TacheView concerné
                tachesView.set(index, tw);


            } else if (t instanceof AuLongCours) {

                // On crée un nouvelle tacheView à partir de la Tache t
                TacheAuLongCourView tw = new TacheAuLongCourView(t.getId(), t.getTitle(),formatDate.format(((AuLongCours)t).getBegin().getTime()), formatDate.format(t.getEnd().getTime()), interval, t.getCategorie().getAbreviation(), t.isLate(), ((AuLongCours) t).getPercentage());
                tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                // On met à jour la TacheView concerné
                tachesView.set(index, tw);

            }

        });

        ArrayList<String> stringList = new ArrayList<>();
        int indexCat = 0;

        // On recherche notre catégororie à partir du titre de celle-ci
        // Et on génère la liste des titres des catégories
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

        // Pourche chaque TacheView d'id t.getId() et d'instance EditTacheView
        tachesView.stream().filter(jp -> jp instanceof EditTacheView).filter(jp -> ((EditTacheView) jp).getId() == t.getId()).forEach(jp -> {

            // On crée l'EditTacheView
            EditTacheView edit = new EditTacheView(t.getId(), t.getTitle(), formatDate.format(t.getEnd().getTime()), t.getEnd().getTime(),stringList.toArray(new String[stringList.size()]) , id, t.isLate(), t.getDateCreation());
            edit.addListenerOnSuppButton(new TacheListener(id, "Suppression"));
            edit.addListenerOnSaveButton(new TacheListener(id, "Sauvegarde"));

            int index = tachesView.indexOf(jp);

            // On met à jour la TacheView concerné
            tachesView.set(index, edit);

        });
    }

    /**
     * Mise à jour de toute les taches
     */
    static void updateAllTaches()
    {
        allTaches.forEach(MainController::updateOneTache);
        update();
    }


    /**
     * Sauvegarde les changement sur la Tache selectionner et remet sa vue par defaut
     *
     * @param id ID de la Tache a sauvegarder
     */
    static void saveTache(int id){

        // Pour toute les tache d'id id et les taches view d'instance EditTacheView et d'id id
        allTaches.stream()
                .filter(t -> t.getId() == id)
                .forEach(t -> tachesView.stream()
                        .filter(jp -> jp instanceof EditTacheView)
                        .filter(jp -> ((EditTacheView)jp).getId() == id)
                        .forEach(jp -> {

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

            long diff =  t.getEnd().getTime().getTime() - currentCalendar.getTime().getTime();
            int interval = (int) ((diff) / (1000 * 60 * 60 * 24));

            if (t instanceof Ponctuelle) {

                TacheView tw = new TacheView(t.getId(), t.getTitle(), formatDate.format(t.getDateCreation().getTime()) ,formatDate.format(t.getEnd().getTime()), interval, t.getCategorie().getAbreviation(), t.isLate());
                tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                int index = tachesView.indexOf(jp);
                tachesView.set(index, tw);
            } else if (t instanceof AuLongCours) {

                TacheAuLongCourView tw = new TacheAuLongCourView(t.getId(), t.getTitle(), formatDate.format(((AuLongCours)t).getBegin().getTime()), formatDate.format(t.getEnd().getTime()), interval,  t.getCategorie().getAbreviation(), t.isLate(), ((AuLongCours) t).getPercentage());
                tw.addListenerOnEditButton(new TacheListener(t.getId(), "Edition"));
                tw.addListenerOnSuppButton(new TacheListener(t.getId(), "Suppression"));
                tw.addListenerOnFinishButton(new TacheListener(t.getId(), "Finish"));

                int index = tachesView.indexOf(jp);
                tachesView.set(index, tw);
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