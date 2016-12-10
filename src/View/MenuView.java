package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author gkueny
 */
class MenuView extends JMenuBar
{

    private ArrayList<JMenu> menus;
    private ArrayList<ArrayList<String>> menusTitle;

 


    MenuView(ArrayList<ArrayList<String>> menusTitle, ActionListener listener)
    { 

        this.menus      = new ArrayList<>();
        this.menusTitle = menusTitle;

        this.initMenuView(listener);


    } 

    private void initMenuView(ActionListener listener)
    {

        int size = this.menusTitle.size();
        for (int i = 0 ; i < size ; i++) {

            this.menus.add( new JMenu( this.menusTitle.get(i).get(0) ) );

            JMenu menu = this.menus.get(i);

            int subMenuSize = this.menusTitle.get(i).size();
            for (int j = 1 ; j < subMenuSize ; j++) {
            
                JMenuItem item = new JMenuItem( this.menusTitle.get(i).get(j) );

                menu.add( item );

                try {

                     item.addActionListener( listener );

                } catch(Exception e) {
                   e.printStackTrace();
                }

            }

            this.add(menu);

        }

    }

}
