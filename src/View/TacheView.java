package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class TacheView extends JPanel{
    private int id;

    private JPanel canvas;

    private JLabel interval;
    private JLabel title;
    private JLabel endDate;
    private JLabel beginDate;
    private JLabel categorie;
    private JButton editButton;
    private JButton deleteButton;
    private JButton finish;

    private final int rows = 3;
    private final int cols = 3;
    private JPanel[][] panelHolder = new JPanel[rows][cols];


    public TacheView(int id, String title, String beginDate,String endDate, int interval, String categorie, boolean isLate){
        super();

        this.id = id;

        this.canvas = new JPanel();

        this.title = new JLabel();
        this.interval = new JLabel();
        this.endDate = new JLabel();
        this.beginDate = new JLabel();
        this.categorie = new JLabel();
        this.editButton = new JButton("Edit");
        this.deleteButton = new JButton("X");
        this.finish = new JButton("Terminer");

        initTacheView(title, beginDate, endDate, interval,categorie, isLate);
    }

    private void initTacheView(String title, String beginDate, String endDate, int interval,String categorie, boolean isLate){
        this.setBorder(LineBorder.createGrayLineBorder());

        this.setLayout(new BorderLayout());

        canvas.setLayout(new GridLayout(rows, cols));
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                panelHolder[row][col] = new JPanel();
                canvas.add(panelHolder[row][col]);
            }
        }

        panelHolder[0][0].add(this.editButton);
        panelHolder[0][1].add(this.title);
        panelHolder[0][2].add(this.deleteButton);

        panelHolder[1][0].add(new JLabel("Début : "));
        panelHolder[1][0].add(this.beginDate);
        panelHolder[1][1].add(new JLabel("Fin : "));
        panelHolder[1][1].add(this.endDate);
        panelHolder[1][2].add(this.categorie);

        panelHolder[2][1].add(this.interval);

        this.add(canvas, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.add(this.finish);

        this.add(center, BorderLayout.CENTER);

        updateView(title, beginDate,endDate, interval, categorie, isLate);
    }

    void setFinisButtonText(String text)
    {
        this.finish.setText(text);
    }

    void updateView(String title, String beginDate, String endDate, int interval,String categorie, boolean isLate){
        setTitle(title, isLate);
        setEndDate(endDate);
        setBeginDate(beginDate);
        setCategorie(categorie);
        setInterval(interval);
    }

    public void setTitle(String title, boolean isLate){
        this.title.setText(title);
        if(isLate){
            this.title.setForeground(Color.RED);
        }
    }

    private void setEndDate(String endDate){
        this.endDate.setText(endDate);
    }

    private void setBeginDate(String beginDate)
    {
        this.beginDate.setText(beginDate);
    }

    private void setInterval(int interval)
    {
        this.interval.setText("<html>Échéance dans : <b>" + interval + "</b> jours</html>");
    }

    public void setCategorie(String categorie){
        this.categorie.setText(categorie);
    }

    public void addListenerOnEditButton(ActionListener listener){
        try{
            this.editButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public void addListenerOnSuppButton(ActionListener listener){
        try{
            this.deleteButton.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addListenerOnFinishButton(ActionListener listener){
        try{
            this.finish.addActionListener(listener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }
}
