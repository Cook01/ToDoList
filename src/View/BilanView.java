package View;

import Controller.BilanListener;
import Controller.MainController;
import Model.Tache;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class BilanView extends JFrame {

    private JPanel canvas;
    private JPanel title;

    private JLabel datechoice;
    private JLabel beginLabel;
    private JLabel endLabel;

    private JSpinner beginDate;
    private JSpinner endDate;

    private JButton bilan;


    private final int rows = 1;
    private final int cols = 4;
    private JPanel[][] panelHolder = new JPanel[rows][cols];

    /**
     * BilanView constructor
     */
    public BilanView() {

        super();

        //initialisation des panel
        this.canvas = new JPanel();
        title = new JPanel();

        // Initialisation des labels
        this.datechoice = new JLabel("Choisissez la période");
        this.beginLabel = new JLabel("Début");
        this.endLabel   = new JLabel("Fin");

        // Initialisation button
        bilan = new JButton("Générer bilan");

        /*-----------------------------------------------------------------------------*/
        /*----------------------------- Spiner date begin -----------------------------*/
        /*-----------------------------------------------------------------------------*/
        SpinnerDateModel modelBegin = new SpinnerDateModel();
        modelBegin.setValue(MainController.currentCalendar.getTime());
        modelBegin.setStart(MainController.currentCalendar.getTime());

        this.beginDate = new JSpinner(modelBegin);

        JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
        DateFormatter formatterBegin = (DateFormatter) editorBegin.getTextField().getFormatter();
        formatterBegin.setAllowsInvalid(false);formatterBegin.setOverwriteMode(true);

        this.beginDate.setEditor(editorBegin);

        JComponent editorDefaukt2 = this.beginDate.getEditor();
        JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
        ftf2.setColumns(8);


        /*-----------------------------------------------------------------------------*/
        /*------------------------------ Spiner date end ------------------------------*/
        /*-----------------------------------------------------------------------------*/
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
        canvas.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {

                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);

            }
        }

        panelHolder[0][0].add( this.beginLabel);
        panelHolder[0][1].add(this.beginDate);
        panelHolder[0][2].add(this.endLabel);
        panelHolder[0][3].add(this.endDate);

        title.add(this.datechoice);
        this.add(title, "North");
        this.add(canvas, "Center");
        this.add(bilan, "South");

        this.addEventToDate();
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
        //this.removeAll();

        JPanel updateCanvas = new JPanel();


        JProgressBar pourcentageRealiseInTimeBar    = new JProgressBar(0, 100);
        pourcentageRealiseInTimeBar.setValue(pourcentageRealiseInTime);
        pourcentageRealiseInTimeBar.setStringPainted(true);

        JProgressBar pourcentageRealiseNotInTimeBar = new JProgressBar(0, 100);
        pourcentageRealiseNotInTimeBar.setValue(pourcentageRealiseNotInTime);
        pourcentageRealiseNotInTimeBar.setStringPainted(true);

        JProgressBar pourcentageNotRealiserBar      = new JProgressBar(0, 100);
        pourcentageNotRealiserBar.setValue(pourcentageNotRealiser);
        pourcentageNotRealiserBar.setStringPainted(true);


        JLabel pourcentageRealiseInTimeLabel     = new JLabel("Réalisé dans les temps");
        JLabel pourcentageRealiseNotInTimeLabel  = new JLabel("Non réalisé dans les temps");
        JLabel pourcentageNotRealiserLabel       = new JLabel("Non finit");

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

        panelHolder[0][0].add(pourcentageRealiseInTimeLabel);
        panelHolder[0][1].add(pourcentageRealiseNotInTimeLabel);
        panelHolder[0][2].add(pourcentageNotRealiserLabel);

        panelHolder[1][0].add(pourcentageRealiseInTimeBar);
        panelHolder[1][1].add(pourcentageRealiseNotInTimeBar);
        panelHolder[1][2].add(pourcentageNotRealiserBar);

        this.remove(canvas);
        this.remove(title);
        this.remove(bilan);
        this.add(updateCanvas, "Center");

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
        this.beginDate.addChangeListener(e -> {
            Date end = (Date)this.endDate.getValue();
            Date begin = (Date)this.beginDate.getValue();
            if(begin.compareTo(end) > 0) {
                this.endDate.setValue(begin);
            }
        });

        this.endDate.addChangeListener(e -> {

            Date end = (Date)this.endDate.getValue();
            Date begin = (Date)this.beginDate.getValue();
            if(end.compareTo(begin) < 0) {
                this.beginDate.setValue(end);
            }
        });
    }
}
