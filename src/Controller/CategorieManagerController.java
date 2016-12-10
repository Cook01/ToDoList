package Controller;

import Model.Categorie;
import Model.Tache;
import View.CategorieManagerView;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * <h1>Contien les methodes appellé par les boutons du Categorie Manager</h1>
 * Implemente les fonctionnalitée "Ajout d'une Categorie", "Edition d'une Categorie" et "Suppression d'une categorie"
 *
 * @author  Vincent THOMAS
 */
class CategorieManagerController {

    /**
     * Ajoute une Categorie en se basant sur les champs de textes du Categorie Manager
     *
     * @param ecv Instance du Categorie Manager
     * @param catList Liste des Categories
     */
    static void addCategorie(CategorieManagerView ecv, ArrayList<Categorie> catList) {

        //Recuperation des Titres et Abreviation depuis les textesFields du Categorie Manager
        String title = ecv.getTitre();
        String abreviation = ecv.getLabel();


        //Interdit un Titre vide et remplace par un Titre par defaut
        if(title.equals("")){
            title = "Titre_Par_Defaut";
        }


        //Interdit une Abreviation vide et remplace par une Abreviation par defaut
        if(abreviation.equals("")){
            abreviation = "Def";
        }


        //Verifie que le Titre n'existe pas deja. Si oui, on rajoute un caracter "_" a la fin et on re-verifie
        boolean titleIsOk = false;
        while(!titleIsOk){
            titleIsOk = true;
            for(Categorie c : catList){
                if(c.getTitre().equals(title)){
                    title += "_";
                    titleIsOk = false;
                }
            }
        }


        //Creation de la nouvelle Categorie et ajout de celle-ci a la liste des Categories
        Categorie newCat = new Categorie(title, abreviation);
        catList.add(newCat);


        //On force la fermeture-reouverture du Categorie Manager pour mettre a jour la liste des Categories affichées
        ecv.dispatchEvent(new WindowEvent(ecv, WindowEvent.WINDOW_CLOSING));
        MainController.editCategorie();
    }


    /**
     * Edite une Categorie existante en se basant sur les champs de textes du Categorie Manager
     *
     * @param ecv Instance du Categorie Manager
     * @param catList Liste des Categories
     */
    static void editCategorie(CategorieManagerView ecv, ArrayList<Categorie> catList) {

        //Recuperation des Titres et Abreviation depuis les textesFields du Categorie Manager
        String title = ecv.getTitre();
        String abreviation = ecv.getLabel();


        //Interdit un Titre vide et remplace par un Titre par defaut
        if(title.equals("")){
            title = "Titre_Par_Defaut";
        }


        //Interdit une Abreviation vide et remplace par une Abreviation par defaut
        if(abreviation.equals("")){
            abreviation = "Def";
        }

        //Recherche de la Categorie qui a été selectionner pour la modification dans la Liste des Categories
        for(Categorie c : catList){
            if(c.getTitre().equals(ecv.getSelectedTitle())){


                //Verifie que le Titre n'existe pas deja. Si oui, on rajoute un caracter "_" a la fin et on re-verifie
                boolean titleIsOk = false;
                while(!titleIsOk){
                    titleIsOk = true;
                    for(Categorie c2 : catList){
                        if(c2.getTitre().equals(title) && !c2.equals(c)){
                            title += "_";
                            titleIsOk = false;
                        }
                    }
                }


                //Modification du Titre et de l'Abreviation de la Categorie existante
                c.setTitre(title);
                c.setAbreviation(abreviation);
            }
        }


        //On force la fermeture-reouverture du Categorie Manager pour mettre a jour la liste des Categories affichées
        ecv.dispatchEvent(new WindowEvent(ecv, WindowEvent.WINDOW_CLOSING));
        MainController.editCategorie();


        //Mise a jour de l'affichage de la fenetre principale
        MainController.updateAllTaches();
        MainController.updateView();
    }

    /**
     * Supprime une Categorie existante
     *
     * @param ecv Instance du Categorie Manager
     * @param catList Liste des Categories
     * @param tacheList Liste des Taches
     */
    static void removeCategorie(CategorieManagerView ecv, ArrayList<Categorie> catList, ArrayList<Tache> tacheList) {

        //Initialisation d'un pointeur sur la Categorie a supprimer
        Categorie catToRemove = null;


        //Recherche de la Categorie qui a été selectionner pour la modification dans la Liste des Categories
        for(Categorie c : catList) {
            if (c.getTitre().equals(ecv.getSelectedTitle())) {


                //Affectation du pointeur sur la Categorie a supprimer
                //(Impossible de supprimer de la liste ici car on boucle dessus pour la recherche, empechant toute opération sur celle-ci)
                catToRemove = c;
            }
        }


        //Recherche des Taches utilisant la Categorie a supprimer
        for(Tache t : tacheList){
            if(t.getCategorie().equals(catToRemove)){


                //Affection a ces Taches une Categoire vierge, symbolisant le faite qu'elles n'ont plus de Categories
                t.setCategorie(new Categorie("", ""));
            }
        }


        //Suppression de la categorie de la liste
        catList.remove(catToRemove);


        //On force la fermeture-reouverture du Categorie Manager pour mettre a jour la liste des Categories affichées
        ecv.dispatchEvent(new WindowEvent(ecv, WindowEvent.WINDOW_CLOSING));
        MainController.editCategorie();


        //Mise a jour de l'affichage de la fenetre principale
        MainController.updateAllTaches();
        MainController.updateView();
    }
}
