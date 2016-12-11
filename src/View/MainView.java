package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <h1>Fenetre Principal</h1>
 * Permet d'afficher les TachesView et le menu
 *
 * @author  Vincent THOMAS
 * @author  Gaëtan KUENY
 */
public class MainView extends JFrame
{

    /**
     * List des tachesView
     */
    private ArrayList<JPanel> tachesView;

    /**
     * JPanel du centre
     */
    private JPanel pCenter;

    /**
     * Titre de la fenetre
     */
    private String title;


    /**
     * Constructeur de la Main View
     *
     * @param title titre de la fenetre
     * @param tachesView listes des tachesView
     * @param menusTitle liste des titres du menu
     * @param menuListener listener du menu
     * @param sortListener listener du tri
     */
    public MainView(String title, ArrayList<JPanel> tachesView, ArrayList<ArrayList<String>> menusTitle, ActionListener menuListener, ActionListener sortListener)
    { 

        this.tachesView = tachesView;
        this.title      = title;

        // Initialisation de la fenetre
        this.initMainView(menusTitle, menuListener, sortListener);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Initalisation de la fenetre
     *
     * @param menusTitle liste des titres du menu
     * @param menuListener listener du menu
     * @param sortListener listener du tri
     */
    private void initMainView(ArrayList<ArrayList<String>> menusTitle, ActionListener menuListener, ActionListener sortListener)
    {

        setTitle(this.title); 

        // Initalisation des JPanel
        pCenter         = new JPanel();
        JPanel pNorth   = new JPanel();

        int space = 20;
        pCenter.setLayout(new GridLayout(0,3, space, space));

        // Pour toutes les tachesView, on les ajoutent dans le JPanel center
        for (JPanel aTachesView : this.tachesView) {

            pCenter.add(aTachesView);

        }

        // Création des boutons de tri
        JButton newestSort = new JButton("simple");
        JButton inteSort = new JButton("intermediaire");

        // AJout du listener de tri
        newestSort.addActionListener(sortListener);
        inteSort.addActionListener(sortListener);

        // Ajout des boutons de tri dans le JPanel pNorth
        pNorth.add(new JLabel("Type de tri : "));
        pNorth.add(newestSort);
        pNorth.add(inteSort);

        // AJout des JPanel à la fenetre
        add(pNorth, "North");
        add(pCenter, "Center");

        // Mise a jour du menu
        setJMenuBar(new MenuView(menusTitle, menuListener));

        pack(); 

    }

    /**
     * Mise à jour de la vue
     *
     * @param title titre de la fenetre
     * @param tachesView liste des tachesView
     */
    public void updateView(String title, ArrayList<JPanel> tachesView)
    {
        this.setTitleView(title);
        this.setTachesView(tachesView);
        this.pack(); 
    }

    /**
     * Mise à jour du titre de la fenetre
     *
     * @param title titre de la fenetre
     */
    private void setTitleView(String title)
    {
        this.title = title;
        setTitle(this.title);
    }

    /**
     * Mise à jour de la liste des tachesView
     *
     * @param tachesView liste des tachesView
     */
    private void setTachesView(ArrayList<JPanel> tachesView)
    {
        this.tachesView = tachesView;

        // On remove tout du JPanel pCenter
        pCenter.removeAll();

        // Pour toutes les tachesView, on les ajoutent dans le JPanel center
        for (JPanel aTachesView : this.tachesView) {

            pCenter.add(aTachesView);

        }

        // On force la revalidation pour updater la vue
        pCenter.revalidate();
        pCenter.repaint();

    }

}
