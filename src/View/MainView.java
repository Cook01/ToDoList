package View;



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
 


    public MainView(String title, ArrayList<TacheView> tachesView)  
    { 

        this.tachesView = tachesView;
        this.title      = title;

        this.initMainView();

    } 

    private void initMainView()
    {

        setTitle(this.title);

        pCenter = new JPanel(); 
        pCenter.setLayout(new GridLayout(0,3,this.space, this.space));

        int size = this.tachesView.size();
        for (int i = 0; i < size; i++ ) {

            pCenter.add(this.tachesView.get(i));

        }

        add(pCenter, "Center");

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

        MainView f = new MainView("ToDo List", tachesView); 

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

    } 

}
