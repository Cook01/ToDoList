package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Lister of Bilan view
 */
public class BilanListener implements ActionListener {


    private JSpinner beginDate;
    private JSpinner endDate;

    /**
     *
     * @param beginDate JSpinner de début du bilan
     * @param endDate JSpinner de fin du bilan
     */
    public BilanListener(JSpinner beginDate, JSpinner endDate) {
        super();
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     *
     * On écoute les action de la vue Bilan
     *
     * @param e ActionEvent action performed
     */
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Générer bilan" :
                //On appele le Main Controller afin de générer le bilan
                MainController.generateBilan((Date)this.beginDate.getValue(), (Date)this.endDate.getValue());
                break;
        }
    }
}
