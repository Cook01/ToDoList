package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * @author gkueny
 */
public class MainView extends JFrame
{

    private ArrayList<JPanel> tachesView;
    private JPanel pCenter;

    private String title;


    public MainView(String title, ArrayList<JPanel> tachesView, ArrayList<ArrayList<String>> menusTitle, ActionListener menuListener, ActionListener sortListener)
    { 

        this.tachesView = tachesView;
        this.title      = title;

        this.initMainView(menusTitle, menuListener, sortListener);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    } 

    private void initMainView(ArrayList<ArrayList<String>> menusTitle, ActionListener menuListener, ActionListener sortListener)
    {

        setTitle(this.title); 


        pCenter = new JPanel();
        JPanel pNorth = new JPanel();

        int space = 20;
        pCenter.setLayout(new GridLayout(0,3, space, space));

        for (JPanel aTachesView : this.tachesView) {

            pCenter.add(aTachesView);

        }

        JButton newestSort = new JButton("simple");
        JButton inteSort = new JButton("intermediaire");

        newestSort.addActionListener(sortListener);
        inteSort.addActionListener(sortListener);

        pNorth.add(new JLabel("Type de tri : "));
        pNorth.add(newestSort);
        pNorth.add(inteSort);

        add(pNorth, "North");
        add(pCenter, "Center");

        setJMenuBar(new MenuView(menusTitle, menuListener));

        pack(); 

    }

    public void updateView(String title, ArrayList<JPanel> tachesView)
    {
        this.setTitleView(title);
        this.setTachesView(tachesView);
        this.pack(); 
    }

    private void setTitleView(String title)
    {
        this.title = title;
        setTitle(this.title);
    }

     private void setTachesView(ArrayList<JPanel> tachesView)
    {
        this.tachesView = tachesView;
        
        pCenter.removeAll();

        for (JPanel aTachesView : this.tachesView) {

            pCenter.add(aTachesView);

        }

        pCenter.revalidate();
        pCenter.repaint();

    }


}
