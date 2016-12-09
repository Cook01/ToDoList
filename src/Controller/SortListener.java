package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortListener implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand()) {
            case "simple" :
                MainController.changeSort("simple");
                break;

            case "intermediaire" :
                MainController.changeSort("intermediaire");
                break;
        }




    }
}