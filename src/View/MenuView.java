package View;



import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

/**
 * @author gkueny
 */
public class MenuView extends JMenuBar
{

    private ArrayList<JMenu> menus;
    private ArrayList<ArrayList<String>> menusTitle;

 


    public MenuView(ArrayList<ArrayList<String>> menusTitle)  
    { 

        this.menus      = new ArrayList<JMenu>();
        this.menusTitle = menusTitle;

        this.initMenuView();


    } 

    private void initMenuView()
    {

        int size = this.menusTitle.size();
        for (int i = 0 ; i < size ; i++) {

            this.menus.add( new JMenu( this.menusTitle.get(i).get(0) ) );

            JMenu menu = this.menus.get(i);

            int subMenuSize = this.menusTitle.get(i).size();
            for (int j = 1 ; j < subMenuSize ; j++) {
            
               

                menu.add( new JMenuItem( this.menusTitle.get(i).get(j) ) );

            }

            this.add(menu);

        }

    }

}
