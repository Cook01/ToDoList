package View;

import Controller.BilanListener;
import Controller.MainController;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class BilanView extends JFrame {

    private JPanel canvas;
    private JPanel center;

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
        JPanel title = new JPanel();

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
            System.out.println("begin dat change = " + this.beginDate.getValue());
        });

        this.endDate.addChangeListener(e -> {

            Date end = (Date)this.endDate.getValue();
            Date begin = (Date)this.beginDate.getValue();
            if(end.compareTo(begin) < 0) {
                this.beginDate.setValue(end);
            }
            System.out.println("endDate dat change = " + this.endDate.getValue());
        });
    }


}
