package View;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
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


    private final int rows = 1;
    private final int cols = 4;
    private JPanel[][] panelHolder = new JPanel[rows][cols];
    private Calendar value = Calendar.getInstance();


    public BilanView() {

        super();

        this.canvas = new JPanel();

        JPanel title = new JPanel();

        this.datechoice = new JLabel("Choisissez la période");

        this.beginLabel = new JLabel("Début");
        this.endLabel = new JLabel("Fin");


        value.setTime(new Date(System.currentTimeMillis()));
        value.set(Calendar.HOUR_OF_DAY, 0);value.set(Calendar.MINUTE, 0);value.set(Calendar.SECOND, 0);value.set(Calendar.MILLISECOND, 0);

        SpinnerDateModel modelBegin = new SpinnerDateModel();
        modelBegin.setValue(value.getTime());
        this.beginDate = new JSpinner(modelBegin);
        JSpinner.DateEditor editorBegin = new JSpinner.DateEditor(this.beginDate, "dd / MM / yyyy");
        DateFormatter formatterBegin = (DateFormatter) editorBegin.getTextField().getFormatter();
        formatterBegin.setAllowsInvalid(false);formatterBegin.setOverwriteMode(true);

        this.beginDate.setEditor(editorBegin);

        JComponent editorDefaukt2 = this.beginDate.getEditor();
        JFormattedTextField ftf2 = ((JSpinner.DefaultEditor) editorDefaukt2).getTextField();
        ftf2.setColumns(8);

        SpinnerDateModel modelEnd = new SpinnerDateModel();
        modelEnd.setValue(value.getTime());
        this.endDate = new JSpinner(modelEnd);
        JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(this.endDate, "dd / MM / yyyy");
        DateFormatter formatterEnd = (DateFormatter) editorEnd.getTextField().getFormatter();
        formatterEnd.setAllowsInvalid(false);formatterEnd.setOverwriteMode(true);

        this.endDate.setEditor(editorEnd);

        JComponent editorDefaukt = this.endDate.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editorDefaukt).getTextField();
        ftf.setColumns(8);


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

        this.pack();

    }


}
