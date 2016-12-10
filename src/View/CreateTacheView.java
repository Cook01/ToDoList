package View;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Vue correspondant à la création du tache</h1>
 *
 * @author Gaëtan KUENY
 */
public class CreateTacheView extends JFrame 
{

	private JPanel canvas;

    private int id;

    private Boolean ponctuelle;

    private JPanel center;

    private JTextField title;
	private JSpinner endDate;
    private JSpinner beginDate;
	private JComboBox<String> categorie;
    private JButton saveButton;
    private JButton cancelButton;


    private final int rows = 2;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];

    /**
     * CreateTacheView constructor
     *
     * @param id id de la tache
     * @param categories liste des différentes carégories
     * @param listener listener des bouttons
     * @param ponctuelle Boolean indiquant si la tache à créer est une tache ponctuelle ou pas
     */
    public CreateTacheView(int id, String[] categories, ActionListener listener, Boolean ponctuelle)
	{
		super();

        this.id         = id;
        this.ponctuelle = ponctuelle;

        // Définition de notre calendar
        Calendar value = Calendar.getInstance();
        value.setTime(new Date(System.currentTimeMillis()));
        value.set(Calendar.HOUR_OF_DAY, 0);
        value.set(Calendar.MINUTE, 0);
        value.set(Calendar.SECOND, 0);
        value.set(Calendar.MILLISECOND, 0);

        // JPanel principal
        this.canvas = new JPanel();

        // JTextField du titre de la future tache
		this.title 	= new JTextField();
		this.title.setColumns(20);

         /*-----------------------------------------------------------------------------*/
        /*------------------------------ Spiner date end ------------------------------*/
        /*-----------------------------------------------------------------------------*/
        // On définit le modèle du spinner
		SpinnerDateModel model 	= new SpinnerDateModel();
        model.setValue(value.getTime());
        model.setStart(value.getTime());

        // On crée notre spinner à partir du modèle
        this.endDate = new JSpinner(model);

        // On crée un nouvelle éditeur pour notre spinner
        JSpinner.DateEditor editor  = new JSpinner.DateEditor(this.endDate, "dd / MM / yyyy");
        DateFormatter formatter     = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);formatter.setOverwriteMode(true);

        // On update l'éditeur
        this.endDate.setEditor(editor);

        // On met à jour le nombre de colonne de notre spinner
        JComponent editorDefaukt = this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaukt).getTextField();
        ftf.setColumns(8);

        // Si la nouvelle tache n'est pas ponctulle, on ajoute le spinner beginDate
        if( !this.ponctuelle ) {

             /*-----------------------------------------------------------------------------*/
            /*----------------------------- Spiner date begin -----------------------------*/
            /*-----------------------------------------------------------------------------*/
            // pareil que plus haut
            SpinnerDateModel modelBegin 	= new SpinnerDateModel();
            modelBegin.setValue(value.getTime());
            modelBegin.setStart(value.getTime());
            modelBegin.setEnd(value.getTime());

            this.beginDate          = new JSpinner(modelBegin);

            JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
            DateFormatter formatterBegin 	= (DateFormatter)editorBegin.getTextField().getFormatter();
            formatterBegin.setAllowsInvalid(false);
            formatterBegin.setOverwriteMode(true);

            this.beginDate.setEditor(editorBegin);

            // On met à jour le nombre de colonne de notre spinner
            JComponent editorDefaukt2 = this.beginDate.getEditor();
            JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
            ftf2.setColumns(8);

            // On ajoute un listener afin de mettre a jour le champ beginDate (on appelle updateView pour cela)
            this.endDate.addChangeListener(e -> this.updateView((Date)this.endDate.getModel().getValue()));
        }



        this.categorie = new JComboBox<>();

        // définission des bouttons
        this.saveButton = new JButton("Save");
        this.cancelButton = new JButton("Cancel");

        // On initialise notre vue
        initCreateTacheView(categories, listener);

	}

    /**
     * Dans le cas ou la tache n'est pas ponctuelle
     *
     * Mise à jour de la vue lorsque le SPiner endDate est modifié
     *
     * @param endValue Date endDate
     */
    private void updateView(Date endValue) {

        // au cas ou la tache est ponctuelle, on ne fais rien
        if (this.ponctuelle)
            return;

        Calendar value = Calendar.getInstance();
        value.setTime(new Date(System.currentTimeMillis()));
        value.set(Calendar.HOUR_OF_DAY, 0);
        value.set(Calendar.MINUTE, 0);
        value.set(Calendar.SECOND, 0);
        value.set(Calendar.MILLISECOND, 0);

        /*-----------On met à jour beginDate s'il le faut------------*/

        Date beginValue = (Date) this.beginDate.getValue();

        if (beginValue.compareTo(endValue) > 0)
            beginValue = endValue;


        SpinnerDateModel modelBegin = new SpinnerDateModel();
        modelBegin.setValue(beginValue);
        modelBegin.setStart(value.getTime());
        modelBegin.setEnd(endValue);

        this.beginDate = new JSpinner(modelBegin);

        JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
        DateFormatter formatterBegin = (DateFormatter) editorBegin.getTextField().getFormatter();
        formatterBegin.setAllowsInvalid(false);
        formatterBegin.setOverwriteMode(true);

        this.beginDate.setEditor(editorBegin);

        JComponent editorDefaukt2 = this.beginDate.getEditor();
        JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
        ftf2.setColumns(8);

        this.center.removeAll();

        center.add(new JLabel("Début : "));
        this.center.add(this.beginDate);

        this.revalidate();
        this.validate();
        this.repaint();


    }

	private void initCreateTacheView(String[] categories, ActionListener listener)
	{

		canvas.setLayout(new GridLayout(rows, cols));

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {

                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);

            }
        }

        panelHolder[0][0].add(this.saveButton);
        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.cancelButton);

        JPanel end = new JPanel();
        end.add(new JLabel("fin : "));
        end.add(this.endDate);
        panelHolder[1][0].add(end);
        panelHolder[1][2].add(this.categorie);

        this.add(canvas, BorderLayout.NORTH);

        if( !this.ponctuelle ) {
            center = new JPanel();

            center.add(new JLabel("Début : "));
            center.add(this.beginDate);

            this.add(center, BorderLayout.SOUTH);
        }


        this.addListenerOnSaveButton(listener);
        this.addListenerOnCancelButton(listener);

        this.setCategorie(categories);

        this.pack();

        
    }

    public void setCategorie(String[] categories){

        for(String categorie : categories){
            this.categorie.addItem(categorie);
        }

    }

    private void addListenerOnSaveButton(ActionListener listener){
        try{
            this.saveButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void addListenerOnCancelButton(ActionListener listener){
        try{
            this.cancelButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * getter de l'attribut id
     *
     * @return Int id de la nouvelle tache
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * getter de l'attribut title
     *
     * @return String titre de la nouvelle tache
     */
    public String getTitle()
    {
        return this.title.getText();
    }

    /**
     * getter de l'attribut endDate
     *
     * @return Calendar date de fin de la nouvelle tache
     */
    public Calendar getEndDate()
    {
        Calendar endDateCalendar = Calendar.getInstance();

        endDateCalendar.setTime((Date) endDate.getValue());

        return endDateCalendar;
    }

    /**
     * getter de l'attribut beginDate
     *
     * @return Calendar date de début de la nouvelle tache
     */
    public Calendar getBeginDate()
    {
        Calendar beginDateCalendar = Calendar.getInstance();

        beginDateCalendar.setTime((Date) beginDate.getValue());

        return beginDateCalendar;
    }

    /**
     * getter de l'attribut categorie
     *
     * @return Calendar categorie de la nouvelle tache
     */
    public String getCategorie()
    {
        return categorie.getSelectedItem().toString();
    }

    /**
     * getter de l'attribut ponctuelle
     *
     * @return Boolean true : si la nouvelle tache est ponctuelle, false sinon
     */
    public Boolean getIsPonctuelle()
    {
        return this.ponctuelle;
    }


}