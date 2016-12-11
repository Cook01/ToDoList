package View;

import javax.swing.*;
import java.awt.*;


/**
 * <h1>Vue d'une Taches Au Long Cours</h1>
 * Permet la visualisation d'une Tache Au Long Cours
 *
 * @author  Vincent THOMAS
 */
public class TacheAuLongCourView extends TacheView{

    /**
     * Barre de progression de la Tache Au Long Cours
     */
    private JProgressBar progressBar;


    /**
     * Constructeur de la classe TacheAuLongCourView
     *
     * @param id ID de la vue, egale a l'ID de la Tache a afficher
     * @param title Titre a afficher
     * @param beginDate Date de debut de la Tache a afficher
     * @param endDate Date de fin de la Tache a afficher
     * @param interval Nb de jours restants avant la fin de la Tache
     * @param categorie Abreviation de la Categorie de la Tache a afficher
     * @param isLate True si la Tache est en retard, False sinon
     * @param pourcentage Progression de la Tache Au Long Cours
     */
    public TacheAuLongCourView(int id, String title, String beginDate, String endDate, int interval, String categorie, boolean isLate, int pourcentage){

        //Appel du constructeur de la classe parent (TacheView)
        super(id, title, beginDate, endDate, interval, categorie, isLate);


        //Initialisation des attributs
        this.progressBar = new JProgressBar(0, 100);


        //Appel de la fonction de mise en place du layout de la vue
        initTacheAuLongCourView(title, beginDate, endDate, interval, categorie, isLate, pourcentage);
    }


    /**
     * Met en place les elements graphiques pour construire la vue
     *
     * @param title Titre a afficher
     * @param beginDate Date de debut de la Tache a afficher
     * @param endDate Date de debut de la Tache a afficher
     * @param interval Nb de jours restants avant la fin de la Tache
     * @param categorie Abreviation de la Categorie de la Tache a afficher
     * @param isLate True si la Tache est en retard, False sinon
     * @param pourcentage Progression de la Tache Au Long Cours
     */
    private void initTacheAuLongCourView(String title, String beginDate,String endDate, int interval,String categorie, boolean isLate, int pourcentage){

        //Ajout de la barre de progression en bas de la vue de la Tache (super = TacheView = JPanel)
        super.add(this.progressBar, BorderLayout.SOUTH);

        //Changement du label sur le bouton Finish pour "Avancer"
        super.setFinisButtonTextToAvancer();


        //Mise a jour du contenue des composants
        updateView(title, beginDate, endDate, interval, categorie, isLate, pourcentage);
    }


    /**
     * Mise a jour du contenue des composants de la vue
     *
     * @param title Titre a afficher
     * @param beginDate Date de debut de la Tache a afficher
     * @param endDate Date de debut de la Tache a afficher
     * @param interval Nb de jours restants avant la fin de la Tache
     * @param categorie Abreviation de la Categorie de la Tache a afficher
     * @param isLate True si la Tache est en retard, False sinon
     * @param pourcentage Progression de la Tache Au Long Cours
     */
    private void updateView(String title,  String beginDate, String endDate, int interval, String categorie, boolean isLate, int pourcentage){

        //Appel de la methode de mise a jour du contenue des composants de la vue de la classe parent (TacheView)
        super.updateView(title, beginDate, endDate, interval, categorie, isLate);

        //Definition de l'avancement de la Barre de progression
        setProgressBar(pourcentage);
    }


    /**
     * Defini l'avancement de la barre de progression
     *
     * @param pourcentage Progression de la Tache Au Long Cours
     */
    private void setProgressBar(int pourcentage){
        this.progressBar.setValue(pourcentage);
        this.progressBar.setStringPainted(true);    
    }
}
