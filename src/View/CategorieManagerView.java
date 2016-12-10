package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <h1>Fenetre de Management des Categories</h1>
 * Permet l'ajout, la modification et la suppression des categories
 *
 * @author  Vincent THOMAS
 */
public class CategorieManagerView extends JFrame implements ListSelectionListener{

	/**
	 *	Liste des Titres des Categories
	 */
	private ArrayList<String> catNameList;
	/**
	 * Liste des Abreviations des Categories
	 */
	private ArrayList<String> catLabelList;

	/**
	 * JPanel contenant toute la fenetre
	 */
	private JPanel canvas;

	/**
	 * JPanel contenant la liste, avec un scroller
	 */
	private JScrollPane listScroller;

	/**
	 * Model pour la liste, contenant les Titres des Categories
	 */
	private DefaultListModel<String> model;
	/**
	 * Affichage de la liste des Titres des Categories
	 */
	private JList<String> list;

	/**
	 * JPanel contenant les champs pour modifier les Titres et Abreviations
	 */
	private JPanel textFieldPanel;

	/**
	 * Label indiquant quel est le champ pour le Titre
	 */
	private JLabel titleLabel;
	/**
	 * Label indiquant quel est le champ pour l'Abreviation
	 */
	private JLabel labelLabel;
	/**
	 * Champ pour le Titre
	 */
	private JTextField titleField;
	/**
	 * Champ pour l'Abreviation
	 */
	private JTextField labelField;


	/**
	 * JPanel contenant les boutons
	 */
	private JPanel buttonPanel;
	/**
	 * Bouton de Suppression
	 */
	private JButton suppButton;
    /**
     * Bouton d'ajout
     */
    private JButton addButton;
    /**
     * Bouton de Modification
     */
    private JButton editButton;

    /**
     * Nombre de lignes dans le layout du JPanel canvas
     */
    private final int rows = 3;
    /**
     * Nombre de colones dans le layout du JPanel canvas
     */
    private final int cols = 1;
    /**
     * Tableau de JPanel correspondant aux emplacement dans le layout du JPanel canvas
     */
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    /**
     * Constructeur de la classe CategorieManagerView
     *
     * @param catNameList Liste des Titres des Categories
     * @param catLabelList Liste des Abreviations des Categories
     */
    public CategorieManagerView(ArrayList<String> catNameList, ArrayList<String> catLabelList){

        //Appel du constructeur de la classe parent (JFrame)
        super();


        //Initialisation des attributs
		this.canvas = new JPanel();

		this.catNameList = catNameList;
		this.catLabelList = catLabelList;

		this.model = new DefaultListModel<>();

		this.listScroller = new JScrollPane();
		this.list = new JList<>(model);

		this.textFieldPanel = new JPanel();

		this.titleLabel = new JLabel("Titre : ");
		this.titleField = new JTextField();

		this.labelLabel = new JLabel("Abrev. : ");
		this.labelField = new JTextField();

		this.buttonPanel = new JPanel();

		this.suppButton = new JButton("Supprimer");
		this.addButton = new JButton("Ajouter");
		this.editButton = new JButton("Modifier");


		//Appel de la fonction de mise en place du layout de la fenetre
		initEditCategorieView();
	}


    /**
     * Met en place les elements graphiques pour construire la fenetre
     */
    private void initEditCategorieView(){

        //Definition du layout de canvas
		this.canvas.setLayout(new GridLayout(rows, cols));


		//Ajout des Titres des Categories dans le model de la JList
        for(String name : catNameList){
            model.addElement(name);
        }


        //On autorise la selection d'un seul element a la fois
		this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        //La disposition de la JListe est verticale (les autres layouts disponibles forment des tableaux et non des listes comme on voudrais ici)
		this.list.setLayoutOrientation(JList.VERTICAL);

		//Le nombre d'elements visibles a l'ouveture de la fenetre.
		this.list.setVisibleRowCount(4);


		//On affecte la JList au JScrollPane (1ere ligne)
		this.listScroller.setViewportView(list);


		//Ajout des Labels et des TextFields au textFieldPanel (2eme ligne)
        this.textFieldPanel.setLayout(new FlowLayout());
		this.textFieldPanel.add(titleLabel);
		this.textFieldPanel.add(titleField);
		this.textFieldPanel.add(labelLabel);
		this.textFieldPanel.add(labelField);


		//Ajout des boutons au buttonPanel (3eme ligne)
        this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.add(addButton);
		this.buttonPanel.add(editButton);
		this.buttonPanel.add(suppButton);


		//Initialisationdes paneHolder, qui permetron le placement des 3 panels precedant dans canvas
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                panelHolder[row][col].setLayout(new BorderLayout());

                canvas.add(panelHolder[row][col]);
            }
        }


        //Placement des 3 Panel dans canvas
        panelHolder[0][0].add(listScroller); // 1ere ligne
        panelHolder[1][0].add(textFieldPanel); // 2eme ligne
        panelHolder[2][0].add(buttonPanel); // 3eme ligne


        //Mise a jour du contenue des composants
        updateView();


        //ajout de canvas a la fenetre
        this.add(canvas);

        //Redimensionnemznt automatique de la fenetre
        this.pack();
	}


    /**
     * Mise a jour du contenue des composants de la fenetre
     */
    private void updateView(){

        //index de l'element selectionner dans la liste
		int index = list.getSelectedIndex();


		//Declaration et initialisation a "vide" de Titre et Abreviaition
		String title = "";
		String label = "";


		//On verifi qu'un element est bien selectionner dans la liste
		if(index != -1){

		    //Recuperation du Titre et de l'Abreviation de la Categorie selectionné
			title = catNameList.get(index);
			label = catLabelList.get(index);


            //"Activation" des boutons "Modifier" et "Supprimer"
			this.editButton.setEnabled(true);
			this.suppButton.setEnabled(true);

		} else { //Si aucun elements n'est selectionner dans la liste

            //On laisse Titre et Abreviation vide

            //"Desactivation" des boutons "Modifier" et "Supprimer"
			this.editButton.setEnabled(false);
			this.suppButton.setEnabled(false);
		}


		//Ecriture du Titre et de l'Abreviation de la Categorie selectionner dans les champs correspondants
		setTitre(title);
		setLabel(label);
	}


    /**
     * Ecrit dans le champ Titre le texte passé en parametre
     *
     * @param title Texte a ecrire dans le champ Titre
     */
    public void setTitre(String title){

        //Affectation du texte au champ
		this.titleField.setText(title);

		//Si le texte est vide, on augmente la taille pour eviter au champs de disparaitre lors du redimentionnement
		if(title.equals("")){
		    this.titleField.setPreferredSize(new Dimension(100, 20));
        }
	}


    /**
     * Renvois le texte inscrit dans le champ Titre
     *
     * @return String : Texte inscrit dans le champ Titre
     */
    public String getTitre(){
		return this.titleField.getText();
	}


    /**
     * Ecrit dans le champ Abreviation le texte passé en parametre
     *
     * @param label Texte a ecrire dans le champ Abreviation
     */
    private void setLabel(String label){

        //Affectation du texte au champ
		this.labelField.setText(label);


        //Si le texte est vide, on augmente la taille pour eviter au champs de disparaitre lors du redimentionnement
        if(label.equals("")){
            this.labelField.setPreferredSize(new Dimension(100, 20));
        }
	}


    /**
     * Renvois le texte inscrit dans le champ Abreviation
     *
     * @return String : Texte inscrit dans le champ Abreviation
     */
    public String getLabel() {
        return this.labelField.getText();
    }


    /**
     * @return String : renvois le Titre de la methode selectionné
     */
    public String getSelectedTitle(){
		return catNameList.get(this.list.getSelectedIndex());
	}


    /**
     * Ajoute un ActionListener passé en parametre au bouton "Ajouter"
     *
     * @param listener ActionListener a ajouter au bouton "Ajouter"
     */
    public void addListenerOnAddButton(ActionListener listener){
        try{
            this.addButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un ActionListener passé en parametre au bouton "Modifier"
     *
     * @param listener ActionListener a ajouter au bouton "Modifier"
     */
    public void addListenerOnEditButton(ActionListener listener){
        try{
            this.editButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un ActionListener passé en parametre au bouton "Supprimer"
     *
     * @param listener ActionListener a ajouter au bouton "Supprimer"
     */
    public void addListenerOnRemoveButton(ActionListener listener){
        try{
            this.suppButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Ajoute un ListSelectionListener passé en parametre au a la JList, afin "d'ecouter" les changement de selection
     *
     * @param listener ListSelectionListener a ajouter a la JList
     */
    public void addListSelectionListener(ListSelectionListener listener){
    	this.list.addListSelectionListener(listener);
    }



    @Override
    public void valueChanged(ListSelectionEvent e){

        //Mise a jour du contenue des composants a chaques changements de selection
    	updateView();
    }


}