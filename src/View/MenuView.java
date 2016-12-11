package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <h1Menu</h1>
 * Permet d'afficher le menu
 *
 * @author  Gaëtan KUENY
 */
class MenuView extends JMenuBar
{

    /**
     * List des JMenu
     */
    private ArrayList<JMenu> menus;

    /**
     * List des titres du menu
     */
    private ArrayList<ArrayList<String>> menusTitle;

    /**
     * MenuVIew constructor
     *
     * @param menusTitle titres du menu
     * @param listener listener du menu
     */
    MenuView(ArrayList<ArrayList<String>> menusTitle, ActionListener listener)
    {
        this.menus      = new ArrayList<>();
        this.menusTitle = menusTitle;

        // initialisation de la menuView
        this.initMenuView(listener);
    }

    /**
     * Initalisation de la menuView
     *
     * @param listener listener du menu
     */
    private void initMenuView(ActionListener listener)
    {
        int size = this.menusTitle.size();

        // pour tout les titres de menu
        for (int i = 0 ; i < size ; i++) {

            // On ajoute un nouveau JMenu à partir du premier titre de la liste courante
            this.menus.add( new JMenu( this.menusTitle.get(i).get(0) ) );

            JMenu menu = this.menus.get(i);

            int subMenuSize = this.menusTitle.get(i).size();

            // Pour chaque titre du sous-menu
            for (int j = 1 ; j < subMenuSize ; j++) {

                // On créee un nouveau JMenuItem
                JMenuItem item = new JMenuItem( this.menusTitle.get(i).get(j) );

                // que l'on ajoute à notre menu
                menu.add( item );

                try {
                    // on ajoute le listener
                     item.addActionListener( listener );

                } catch(Exception e) {
                   e.printStackTrace();
                }

            }

            this.add(menu);
        }
    }
}
