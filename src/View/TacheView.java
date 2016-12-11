package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <h1>Vue d'une Taches</h1>
 * Permet la visualisation d'une Tache
 *
 * @author  Vincent THOMAS
 */
public class TacheView extends JPanel{

    /**
     * ID de la vue, egale a l'ID de la Tache a afficher
     */
    private int id;

    /**
     * JPanel global qui vas aceuillir tous les composants
     */
    private JPanel canvas;


    /**
     * Affichage du nb de jours restants avant la fin de la Tache
     */
    private JLabel interval;
    /**
     * Affichage du Titre  de la Tache
     */
    private JLabel title;
    /**
     * Affichage de la date de fin de la Tache
     */
    private JLabel endDate;
    /**
     * Affichage de la date de debut de la Tache
     */
    private JLabel beginDate;
    /**
     * Affichage de l'Abreviation de la Categorie de la Tache
     */
    private JLabel categorie;
    /**
     * Bouton pour Modifier la Tache
     */
    private JButton editButton;
    /**
     * Bouton pour Supprimer la Tache
     */
    private JButton deleteButton;
    /**
     * Bouton pour valider/faire avancer la Tache
     */
    private JButton finish;


    /**
     * Nombre de lignes dans le layout du JPanel canvas
     */
    private final int rows = 3;
    /**
     * Nombre de colones dans le layout du JPanel canvas
     */
    private final int cols = 3;
    /**
     * Tableau de JPanel correspondant aux emplacement dans le layout du JPanel canvas
     */
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    /**
     * Constructeur de la classe TacheView
     *
     * @param id ID de la vue, egale a l'ID de la Tache a afficher
     * @param title Titre a afficher
     * @param beginDate Date de debut de la Tache a afficher
     * @param endDate Date de fin de la Tache a afficher
     * @param interval Nb de jours restants avant la fin de la Tache
     * @param categorie Abreviation de la Categorie de la Tache a afficher
     * @param isLate True si la Tache est en retard, False sinon
     */
    public TacheView(int id, String title, String beginDate,String endDate, int interval, String categorie, boolean isLate){

        //Appel du constructeur de la classe parent (JPanel)
        super();


        //Initialisation des attributs
        this.id = id;

        this.canvas = new JPanel();

        this.title = new JLabel();
        this.interval = new JLabel();
        this.endDate = new JLabel();
        this.beginDate = new JLabel();
        this.categorie = new JLabel();
        this.editButton = new JButton("Edit");
        this.deleteButton = new JButton("X");
        this.finish = new JButton("Terminer");


        //Appel de la fonction de mise en place du layout de la vue
        initTacheView(title, beginDate, endDate, interval,categorie, isLate);
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
     */
    private void initTacheView(String title, String beginDate, String endDate, int interval,String categorie, boolean isLate){

        //Creation d'un contour autour de la vue
        this.setBorder(LineBorder.createGrayLineBorder());

        //Definition du layout
        this.setLayout(new BorderLayout());


        //Definition du layout de canvas
        canvas.setLayout(new GridLayout(rows, cols));

        //Initialisationdes paneHolder, qui permetron le placement des 3 panels precedant dans canvas
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);
            }
        }


        //Placement des Panels dans canvas
        panelHolder[0][0].add(this.editButton);                 //Ligne 1 - Colone 1
        panelHolder[0][1].add(this.title);                      //Ligne 1 - Colone 2
        panelHolder[0][2].add(this.deleteButton);               //Ligne 1 - Colone 3

        panelHolder[1][0].add(new JLabel("Début : "));     //Ligne 2 - Colone 1
        panelHolder[1][0].add(this.beginDate);                  //Ligne 2 - Colone 1
        panelHolder[1][1].add(new JLabel("Fin : "));       //Ligne 2 - Colone 2
        panelHolder[1][1].add(this.endDate);                    //Ligne 2 - Colone 2
        panelHolder[1][2].add(this.categorie);                  //Ligne 2 - Colone 3

        panelHolder[2][1].add(this.interval);                   //Ligne 3 - Colone 2


        //Placement du canvas en haut de la vue (utile pour la bar de progression des taches Au Long Cours)
        this.add(canvas, BorderLayout.NORTH);


        //Creation d'une place pour le bouton "Finish" au centre de la vue
        JPanel center = new JPanel();

        //Ajout du bouton
        center.add(this.finish);

        //Ajout du panel au panel global
        this.add(center, BorderLayout.CENTER);


        //Mise a jour du contenue des composants
        updateView(title, beginDate,endDate, interval, categorie, isLate);
    }


    /**
     * Ecrit "Avancer" au lieu de "Terminer" sur le bouton "Finish"
     */
    void setFinisButtonTextToAvancer()
    {
        this.finish.setText("Avancer");
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
     */
    void updateView(String title, String beginDate, String endDate, int interval,String categorie, boolean isLate){

        //Ecriture du Titre et Surlignage en rouge si la Tache est en retard
        setTitle(title, isLate);

        //Ecriture de la date de fin
        setEndDate(endDate);

        //Ecriture de la date de debut
        setBeginDate(beginDate);

        //Ecriture de l'Abreviation de la categorie
        setCategorie(categorie);

        //Ecriture du nb de jours restants avant la fin de la Tache
        setInterval(interval);
    }


    /**
     * Ecrit le Titre passé en parametre et Surlignage en rouge si isLate = true
     *
     * @param title Titre a afficher
     * @param isLate True si la Tache est en retard, False sinon
     */
    private void setTitle(String title, boolean isLate){

        //Afficher le Titre
        this.title.setText(title);


        //Si isLate = True, on change la couleur du Titre pour Rouge
        if(isLate){
            this.title.setForeground(Color.RED);
        }
    }


    /**
     * Ecrit de la date de fin
     *
     * @param endDate Date de debut de la Tache a afficher
     */
    private void setEndDate(String endDate){
        this.endDate.setText(endDate);
    }


    /**
     * Ecrit de la date de debut
     *
     * @param beginDate Date de debut de la Tache a afficher
     */
    private void setBeginDate(String beginDate)
    {
        this.beginDate.setText(beginDate);
    }


    /**
     * Ecrit de le nb de jours restants avant la fin de la Tache
     *
     * @param interval Nb de jours restants avant la fin de la Tache
     */
    private void setInterval(int interval){
        this.interval.setText("<html>Échéance dans : <b>" + interval + "</b> jours</html>");
    }


    /**
     * Ecrit l'Abreviation de la categorie
     *
     * @param categorie Abreviation de la Categorie de la Tache a afficher
     */
    public void setCategorie(String categorie){
        this.categorie.setText(categorie);
    }


    /**
     * Ajoute un ActionListener passé en parametre au bouton "Edit"
     *
     * @param listener ActionListener a ajouter au bouton "Edit"
     */
    public void addListenerOnEditButton(ActionListener listener){
        try{
            this.editButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }   
    }


    /**
     * Ajoute un ActionListener passé en parametre au bouton "X"
     *
     * @param listener ActionListener a ajouter au bouton "X"
     */
    public void addListenerOnSuppButton(ActionListener listener){
        try{
            this.deleteButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Ajoute un ActionListener passé en parametre au bouton "Finish"
     *
     * @param listener ActionListener a ajouter au bouton "Finish"
     */
    public void addListenerOnFinishButton(ActionListener listener){
        try{
            this.finish.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Renvois l'ID de la vue, egale a l'ID de la Tache qu'elle affiche
     *
     * @return String : ID de la vue
     */
    public int getId(){
        return id;
    }
}
