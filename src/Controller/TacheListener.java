package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>Contient les methodes appellé par les boutons des TacheView</h1>
 * Appel les methodes du MainController correspondantes a l'action voulu
 *
 * @author Vincent THOMAS
 * @author Gaëtan KUENY
 */
public class TacheListener implements ActionListener {

    /**
     * ID de la Tache a modifier
     */
    private int id;

    /**
     * Action a effectuer sur la Tache
     */
    private String action;


    /**
     * Constructeur de la classe TacheListener
     *
     * @param id ID de la Tache a modifier
     * @param action Action a effectuer sur la Tache. Peut etre : "Edition", "Suppression", "Sauvegarde" ou "Finish".
     */
    public TacheListener(int id, String action){
        this.id = id;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(this.action){
            case "Edition" :

                //Appel de la methode d'edition de tache, située dans le MainController
                MainController.editTache(id);
                break;

            case "Suppression" :

                //Appel de la methode de suppression de tache, située dans le MainController
                MainController.removeTache(id);
                break;

            case "Sauvegarde" :

                //Appel de la methode de sauvegarde de tache, située dans le MainController
                MainController.saveTache(id);
                break;

            case "Finish" :

                //Appel de la methode de mise a jour de la progression de tache, située dans le MainController
                MainController.updateProgressTache(id);
                break;

            default :

                //Indique dans la sortie d'erreur que l'action specifiée n'est pas reconnue
                System.err.println("Erreur : action non reconnue");
                break;
        }
    }
}
