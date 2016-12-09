package Controller;

import Model.Categorie;
import Model.Tache;
import View.EditCategorieView;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by Vincent on 09/12/2016.
 */
public class CategorieController {

    public static void addCategorie(EditCategorieView ecv, ArrayList<Categorie> catList) {
        String title = ecv.getTitre();
        String abreviation = ecv.getLabel();

        if(title.equals("")){
            title = "Titre_Par_Defaut";
        }

        if(abreviation.equals("")){
            abreviation = "Def";
        }

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

        Categorie newCat = new Categorie(title, abreviation);
        catList.add(newCat);

        ecv.dispatchEvent(new WindowEvent(ecv, WindowEvent.WINDOW_CLOSING));
        MainController.editCategorie();
    }

    public static void editCategorie(EditCategorieView ecv, ArrayList<Categorie> catList) {
        String title = ecv.getTitre();
        String abreviation = ecv.getLabel();

        if(title.equals("")){
            title = "Titre_Par_Defaut";
        }

        if(abreviation.equals("")){
            abreviation = "Def";
        }

        for(Categorie c : catList){
            if(c.getTitre().equals(ecv.getSelectedTitle())){
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

                c.setTitre(title);
                c.setAbreviation(abreviation);
            }
        }

        ecv.dispatchEvent(new WindowEvent(ecv, WindowEvent.WINDOW_CLOSING));
        MainController.editCategorie();

        MainController.updateAllTaches();
        MainController.updateView();
    }

    public static void removeCategorie(EditCategorieView ecv, ArrayList<Categorie> catList, ArrayList<Tache> tacheList) {
        Categorie catToRemove = null;

        for(Categorie c : catList) {
            if (c.getTitre().equals(ecv.getSelectedTitle())) {
                catToRemove = c;
            }
        }

        for(Tache t : tacheList){
            if(t.getCategorie().equals(catToRemove)){
                t.setCategorie(new Categorie("", ""));
            }
        }

        catList.remove(catToRemove);

        ecv.dispatchEvent(new WindowEvent(ecv, WindowEvent.WINDOW_CLOSING));
        MainController.editCategorie();

        MainController.updateAllTaches();
        MainController.updateView();
    }
}
