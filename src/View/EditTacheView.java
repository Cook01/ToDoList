package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;


/**
 * <h1>Vue d'edition des Taches</h1>
 * Permet la modification des differents champs d'une Tache
 *
 * @author  Vincent THOMAS
 */
public class EditTacheView extends JPanel
{
    /**
     * ID de la vue, egale a l'ID de la Tache a modifier
     */
    private int id;

    /**
     * JPanel global qui vas aceuillir tous les composants
     */
    private JPanel canvas;

    /**
     * Champs de modificationd du Titre
     */
    private JTextField title;
    /**
     * Champs de selection de la date de fin
     */
    private JSpinner endDate;
    /**
     * Champs de selection de la categorie
     */
    private JComboBox<String> categorie;
    /**
     * Bouton de Sauvegarde
     */
    private JButton saveButton;
    /**
     * Bouton de Suppression
     */
    private JButton deleteButton;

    /**
     * Nombre de lignes dans le layout du JPanel canvas
     */
    private final int rows = 2;
    /**
     * Nombre de colones dans le layout du JPanel canvas
     */
    private final int cols = 3;
    /**
     * Tableau de JPanel correspondant aux emplacement dans le layout du JPanel canvas
     */
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    /**
     * Constructeur de la classe EditTacheView
     *
     * @param id ID de la vue, egale a l'ID de la Tache a modifier
     * @param title Texte a inserer dans le champ Titre
     * @param endDate Date a selectionner par defaut pour le selecteur de date de fin
     * @param categories Liste des Titres des Categories disponibles
     * @param idCategorie ID de la catégorie a afficher par defaut
     * @param isLate True si la Tache est en retard, False sinon
     * @param dateCreation Date de création de la Tache
     */
    public EditTacheView(int id, String title, Date endDate, String[] categories, int idCategorie, Boolean isLate, Calendar dateCreation){

        //Appel du constructeur de la classe parent (JPanel)
        super();


        //Setup du Model pour le selecteur de date
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(endDate);
        model.setStart(dateCreation.getTime());


        //Initialisation des attributs
        this.id = id;

        this.canvas = new JPanel();

        this.title = new JTextField();
        this.endDate = new JSpinner(model);
        this.categorie = new JComboBox<>();

        this.saveButton = new JButton("Save");
        this.deleteButton = new JButton("X");


        //Appel de la fonction de mise en place du layout de la vue
        initEditTacheView(title, categories, idCategorie, isLate);
    }


    /**
     * Met en place les elements graphiques pour construire la vue
     *
     * @param title Texte a inserer dans le champ Titre
     * @param categories Liste des Titres des Categories disponibles
     * @param idCategorie ID de la catégorie a afficher par defaut
     * @param isLate True si la Tache est en retard, False sinon
     */
    private void initEditTacheView(String title, String[] categories, int idCategorie, Boolean isLate){

        //Creation d'un contour autour de la vue
        this.setBorder(LineBorder.createGrayLineBorder());

        //Definition du layout
        this.setLayout(new BorderLayout());


        // On crée un nouvelle éditeur pour notre spinner
        JSpinner.DateEditor editor = new JSpinner.DateEditor(this.endDate, "dd/MM/yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        // On update l'éditeur
        this.endDate.setEditor(editor);

        // On met à jour le nombre de colonne de notre spinner
        JComponent editorDefaut = this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaut).getTextField();
        ftf.setColumns(8);


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
        panelHolder[0][0].add(this.saveButton);     //Ligne 1 - Colone 1
        panelHolder[0][1].add(this.title);          //Ligne 1 - Colone 2
        panelHolder[0][2].add(this.deleteButton);   //Ligne 1 - Colone 3

        panelHolder[1][0].add(this.endDate);        //Ligne 2 - Colone 1
                                                    //Ligne 2 - Colone 2
        panelHolder[1][2].add(this.categorie);      //Ligne 2 - Colone 3


        //Placement du canvas en haut de la vue (utile pour la bar de progression des taches Au Long Cours)
        this.add(canvas, BorderLayout.NORTH);


        //Mise a jour du contenue des composants
        updateView(title, categories, idCategorie, isLate);
    }


    /**
     * Mise a jour du contenue des composants de la vue
     *
     * @param title Texte a inserer dans le champ Titre
     * @param categories Liste des Titres des Categories disponibles
     * @param idCategorie ID de la catégorie a afficher par defaut
     * @param isLate True si la Tache est en retard, False sinon
     */
    private void updateView(String title, String[] categories, int idCategorie, Boolean isLate){

        //Ecriture du Titre dans le champ correspondant
        setTitle(title);

        //Bloque ou non la modification de la date en fonction du retard ou non de la Tache
        setEndDate(isLate);

        //Ecriture des Titres des Categories disponibles dans le selecteur de Categorie + selection par defaut de la Categorie actuel de la Tache
        setCategorie(categories, idCategorie);
    }


    /**
     * Ecrit dans le champ Titre le texte passé en parametre
     *
     * @param title Texte a ecrire dans le champ Titre
     */
    public void setTitle(String title){

        //Affectation du texte au champ
        this.title.setText(title);

        //Definition de la taille du champ
        this.title.setPreferredSize(new Dimension(100, 24));        
    }


    /**
     * Bloque ou non la modification de la date en fonction du retard ou non de la Tache
     *
     * @param isLate True si la Tache est en retard, False sinon
     */
    private void setEndDate(Boolean isLate){

        //"Verouille" ou non le selecteur en fonction de isLate
        this.endDate.setEnabled(!isLate);
    }


    /**
     * Ecriture des Titres des Categories disponibles dans le selecteur de Categorie et selection par defaut de la Categorie actuel de la Tache
     *
     * @param categories Liste des Titres des Categories disponibles
     * @param idCategorie ID de la catégorie a afficher par defaut
     */
    private void setCategorie(String[] categories, int idCategorie){

        //Ajout de tous les titres des Categories au selecteur
        for(String categorie : categories){
            this.categorie.addItem(categorie);
        }


        //Definition de l'index de la Categorie selectionné par defaut
        this.categorie.setSelectedIndex(idCategorie);
    }


    /**
     * Ajoute un ActionListener passé en parametre au bouton "Save"
     *
     * @param listener ActionListener a ajouter au bouton "Save"
     */
    public void addListenerOnSaveButton(ActionListener listener){
        try{
            this.saveButton.addActionListener(listener);
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
     *  Renvois l'ID de la vue d'edition, egale a l'ID de la Tache edité
     *
     * @return int : ID de la vue d'edition
     */
    public int getId(){
        return id;
    }


    /**
     * Renvois le texte inscrit dans le champ Titre
     *
     * @return String : Texte inscrit dans le champ Titre
     */
    public String getTitle() {
        return this.title.getText();
    }


    /**
     * Renvois la date de fin de la Tache
     *
     * @return Calendar : Date de fin de la Tache
     */
    public Calendar getEndDate() {

        //Instancie un nouveau Calendrier
        Calendar endDateCalendar = Calendar.getInstance();

        //Definie date de fin comme etant la date du Calendrier
        endDateCalendar.setTime((Date) endDate.getValue());

        //renvoi le Calendrier
        return endDateCalendar;
    }


    /**
     * Renvois le Titre de la Categorie selectionné
     *
     * @return String : Titre de la Categorie selectionné
     */
    public String getCategorie() {
        return categorie.getSelectedItem().toString();
    }
}