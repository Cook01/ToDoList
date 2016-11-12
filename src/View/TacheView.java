package View;

import Model.Ponctuelle;
import Model.Tache;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vincent on 12/11/2016.
 */
public class TacheView extends JPanel{
    private Tache tacheModel;
    private JLabel titre;
    private JLabel endDate;

    public TacheView(Tache model){
        super();
        tacheModel = model;

        titre = new JLabel(tacheModel.getTitle());

        SimpleDateFormat format1 = new SimpleDateFormat("EE dd MMM y");
        endDate = new JLabel(format1.format(tacheModel.getEnd().getTime()));

        this.setLayout(new BorderLayout());

        this.add(titre, BorderLayout.NORTH);

        JPanel south = new JPanel();
        south.add(new JLabel("Date de fin : "));
        south.add(endDate);

        this.add(south, BorderLayout.SOUTH);
    }
}
