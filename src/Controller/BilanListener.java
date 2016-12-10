package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BilanListener implements ActionListener {

    private JSpinner beginDate;
    private JSpinner endDate;

    public BilanListener(JSpinner beginDate, JSpinner endDate) {
        super();
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Générer bilan" :
                MainController.generateBilan((Date)this.beginDate.getValue(), (Date)this.endDate.getValue());
                break;
        }
    }
}
