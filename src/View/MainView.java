package View;

import Controller.MenuListener;
import Controller.TacheListener;

import Model.MenuItems;

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
 


    public MainView(String title, ArrayList<TacheView> tachesView, ArrayList<ArrayList<String>> menusTitle, Class menuListener)  
    { 

        this.tachesView = tachesView;
        this.title      = title;

        this.initMainView(menusTitle, menuListener);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } 

    private void initMainView(ArrayList<ArrayList<String>> menusTitle, Class menuListener)
    {

        setTitle(this.title); 


        pCenter = new JPanel(); 
        pCenter.setLayout(new GridLayout(0,3,this.space, this.space));

        int size = this.tachesView.size();
        for (int i = 0; i < size; i++ ) {

            pCenter.add(this.tachesView.get(i));

        }

        add(pCenter, "Center");

        setJMenuBar(new MenuView(menusTitle, menuListener));

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

   

}
