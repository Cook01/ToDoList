package View;

import Controller.BilanListener;
import Controller.MainController;
import Model.Tache;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Vue correspondant à l'édition du bilan</h1>
 * Affiche la séléction de la période dans un premier temps
 * Affiche le bilan dans un second temps
 *
 * @author Gaëtan KUENY
 */
public class BilanView extends JFrame {

    private JPanel canvas;
    private JPanel title;

    private JSpinner beginDate;
    private JSpinner endDate;

    private JButton bilan;


    /**
     * BilanView constructor
     */
    public BilanView() {

        super();

        //initialisation des panel
        this.canvas = new JPanel();
        this.title  = new JPanel();

        // Initialisation des labels
        JLabel datechoice   = new JLabel("Choisissez la période");
        JLabel beginLabel   = new JLabel("Début");
        JLabel endLabel     = new JLabel("Fin");

        // Initialisation button
        this.bilan = new JButton("Générer bilan");


        // Current calendar :
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

        /*-----------------------------------------------------------------------------*/
        /*----------------------------- Spiner date begin -----------------------------*/
        /*-----------------------------------------------------------------------------*/
        // On définit le modèle du spinner
        SpinnerDateModel modelBegin = new SpinnerDateModel();
        modelBegin.setValue(currentCalendar.getTime());
        modelBegin.setStart(currentCalendar.getTime());

        // On crée notre spinner à partir du modèle
        this.beginDate = new JSpinner(modelBegin);

        // On crée un nouvelle éditeur pour notre spinner
        JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
        DateFormatter formatterBegin = (DateFormatter) editorBegin.getTextField().getFormatter();
        formatterBegin.setAllowsInvalid(false);formatterBegin.setOverwriteMode(true);

        // On update l'éditeur
        this.beginDate.setEditor(editorBegin);

        // On met à jour le nombre de colonne de notre spinner
        JComponent editorDefaukt2 = this.beginDate.getEditor();
        JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
        ftf2.setColumns(8);


        /*-----------------------------------------------------------------------------*/
        /*------------------------------ Spiner date end ------------------------------*/
        /*-----------------------------------------------------------------------------*/
        // Même chose que pour  beginDate
        SpinnerDateModel modelEnd = new SpinnerDateModel();
        modelEnd.setValue(MainController.currentCalendar.getTime());
        modelEnd.setStart(MainController.currentCalendar.getTime());

        this.endDate = new JSpinner(modelEnd);

        JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(this.endDate, "dd / MM / yyyy");
        DateFormatter formatterEnd = (DateFormatter) editorEnd.getTextField().getFormatter();
        formatterEnd.setAllowsInvalid(false);formatterEnd.setOverwriteMode(true);

        this.endDate.setEditor(editorEnd);

        JComponent editorDefaukt = this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaukt).getTextField();
        ftf.setColumns(8);



        // initialisation panelHolder
        int rows = 1;
        int cols = 4;
        canvas.setLayout(new GridLayout(rows, cols));
        JPanel[][] panelHolder = new JPanel[rows][cols];
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {

                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);

            }
        }

        // Ajout des différents éléments dans notre panel
        panelHolder[0][0].add(beginLabel);
        panelHolder[0][1].add(this.beginDate);
        panelHolder[0][2].add(endLabel);
        panelHolder[0][3].add(this.endDate);

        // Ajout du texte dans un panel
        title.add(datechoice);

        // AJout des différents panel dans notre frame
        this.add(title, "North");
        this.add(canvas, "Center");
        this.add(bilan, "South");

        // Ajout des listener sur nos spinner
        this.addEventToDate();

        // Ajout du listener sur notre boutton
        bilan.addActionListener(new BilanListener(this.beginDate, this.endDate));

        this.pack();

    }

    /**
     * Update View to present Bilan
     *
     * @param allTachesFilter taches dans l'intervale
     * @param pourcentageRealiseInTime pourcentage des taches realisees dans les temps
     * @param pourcentageRealiseNotInTime pourcentage des taches non realisees dans les temps (mais finit)
     * @param pourcentageNotRealiser pourcentage des taches non finit
     */
    public void updateView(ArrayList<Tache> allTachesFilter, int pourcentageRealiseInTime, int pourcentageRealiseNotInTime, int pourcentageNotRealiser)
    {

        // Nouveau canvas dans lequel on va ajouter nos nouveau élements
        JPanel updateCanvas = new JPanel();

        // Création des JProgressBar
        JProgressBar pourcentageRealiseInTimeBar    = new JProgressBar(0, 100);
        pourcentageRealiseInTimeBar.setValue(pourcentageRealiseInTime);
        pourcentageRealiseInTimeBar.setStringPainted(true);

        JProgressBar pourcentageRealiseNotInTimeBar = new JProgressBar(0, 100);
        pourcentageRealiseNotInTimeBar.setValue(pourcentageRealiseNotInTime);
        pourcentageRealiseNotInTimeBar.setStringPainted(true);

        JProgressBar pourcentageNotRealiserBar      = new JProgressBar(0, 100);
        pourcentageNotRealiserBar.setValue(pourcentageNotRealiser);
        pourcentageNotRealiserBar.setStringPainted(true);

        // Création des Labels
        JLabel pourcentageRealiseInTimeLabel     = new JLabel("Réalisé dans les temps");
        JLabel pourcentageRealiseNotInTimeLabel  = new JLabel("Non réalisé dans les temps");
        JLabel pourcentageNotRealiserLabel       = new JLabel("Non finit");
        JLabel titleList = new JLabel("Liste des taches à terminer : ");

        // On récupère tout les titres des taches dans un tableau grâce au stream
        String[] listName = allTachesFilter.stream().map(Tache::getTitle).toArray(String[]::new);

        // Création de la JList
        JList<String> jlistName = new JList<String>(listName);

        JPanel center = new JPanel();
        center.add(titleList, "West");
        center.add(jlistName, "Center");

        // On initialise notre panelHolder
        int rows = 2;
        int cols = 3;
        JPanel[][] panelHolder = new JPanel[rows][cols];
        updateCanvas.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {

                panelHolder[row][col] = new JPanel();
                updateCanvas.add(panelHolder[row][col]);

            }
        }

        // On ajoute tout les éléments
        panelHolder[0][0].add(pourcentageRealiseInTimeLabel);
        panelHolder[0][1].add(pourcentageRealiseNotInTimeLabel);
        panelHolder[0][2].add(pourcentageNotRealiserLabel);

        panelHolder[1][0].add(pourcentageRealiseInTimeBar);
        panelHolder[1][1].add(pourcentageRealiseNotInTimeBar);
        panelHolder[1][2].add(pourcentageNotRealiserBar);

        // On clean notre JFrame
        this.remove(canvas);
        this.remove(title);
        this.remove(bilan);

        // On ajoute nos nouveaux JPanel
        this.add(updateCanvas, "North");
        this.add(center, "Center");

        // On invalide pour "repeindre" la JFrame
        this.invalidate();
        this.validate();
        this.repaint();


    }

    /**
     * Add event to Spinner Date
     *
     * if begin date > end date : update end Date to begin value
     * if end date < begin date : update begin Date to end value
     */
    private void addEventToDate()
    {
        // On ajoute un listener sur beginDate afin d'updater endDate si celui ci devient plus petit que beginDate
        this.beginDate.addChangeListener(e -> {

            Date end = (Date)this.endDate.getValue();
            Date begin = (Date)this.beginDate.getValue();

            if(begin.compareTo(end) > 0) {
                this.endDate.setValue(begin);
            }

        });

        // On ajoute un listener sur endDate afin d'updater beginDate si celle ci devient plus grande que endDate
        this.endDate.addChangeListener(e -> {

            Date end = (Date)this.endDate.getValue();
            Date begin = (Date)this.beginDate.getValue();
            if(end.compareTo(begin) < 0) {
                this.beginDate.setValue(end);
            }
        });
    }
}
