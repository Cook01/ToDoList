package View;

import Controller.MenuListener;
import Model.MenuItem;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

/**
 * @author gkueny
 */
public class MainView extends JFrame
{

    private ArrayList<TacheView> tachesView;
    private JPanel pCenter;

    private String title;

    private int space = 20;
 


    public MainView(String title, ArrayList<TacheView> tachesView, ArrayList<ArrayList<String>> menusTitle, Class listener)  
    { 

        this.tachesView = tachesView;
        this.title      = title;

        this.initMainView(menusTitle, listener);

    } 

    private void initMainView(ArrayList<ArrayList<String>> menusTitle, Class listener)
    {

        setTitle(this.title);

        pCenter = new JPanel(); 
        pCenter.setLayout(new GridLayout(0,3,this.space, this.space));

        int size = this.tachesView.size();
        for (int i = 0; i < size; i++ ) {

            pCenter.add(this.tachesView.get(i));

        }

        add(pCenter, "Center");

        setJMenuBar(new MenuView(menusTitle, listener));

        pack(); 

    }

    public void updateView(String title, ArrayList<TacheView> tachesView)
    {
        this.setTitleView(title);
        this.setTachesView(tachesView);
    }

    private void setTitleView(String title)
    {
        this.title = title;
        setTitle(this.title);
    }

     private void setTachesView(ArrayList<TacheView> tachesView)
    {
        this.tachesView = tachesView;
        
        pCenter.removeAll();

        int size = this.tachesView.size();
        for (int i = 0; i < size; i++ ) {

            pCenter.add(this.tachesView.get(i));

        }

        pCenter.revalidate();
        pCenter.repaint();

    }

    public static void main(String args[])  
    { 

        ArrayList<ArrayList<String>> menu   = new ArrayList<ArrayList<String>>();

        ArrayList<String> submenu           = new ArrayList<String>();
        ArrayList<String> submenu2          = new ArrayList<String>();
        ArrayList<String> submenu3          = new ArrayList<String>();

        submenu.add("Créer");
        submenu.add(MenuItem.CARTEPONCTUELLE);
        submenu.add(MenuItem.CARTEAULONGCOURS);

        submenu2.add("Manager");
        submenu2.add(MenuItem.CATEGORIE);

        submenu3.add("Autres");
        submenu3.add(MenuItem.SAUVEGARDER);
        submenu3.add(MenuItem.QUITTER);

        menu.add(submenu);
        menu.add(submenu2);
        menu.add(submenu3);

        ArrayList<TacheView> tachesView = new ArrayList<TacheView>();

        tachesView.add(new TacheView("Titre1", "25-10-1996", "Bite", false));
        tachesView.add(new TacheView("Titre2", "25-10-1997", "Bote", false));
        tachesView.add(new TacheView("Titre3", "25-10-1999", "", false));
        tachesView.add(new TacheView("Titre4", "25-12-1996", "Bite", true));
        tachesView.add(new TacheView("Titre5", "25-08-1996", "Bô", false));
        tachesView.add(new TacheView("Titre6", "25-01-1996", "Bô", false));
        tachesView.add(new TacheView("Titre7", "23-10-1996", "Bite", false));
        tachesView.add(new TacheView("Titre8", "01-10-1996", "Bote", false));
        tachesView.add(new TacheView("Titre9", "31-10-1996", "Bite", true));

        MainView f = new MainView("ToDo List", tachesView, menu, MenuListener.class); 

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

    } 

}
