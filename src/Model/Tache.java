package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Class Tache</h1>
 *
 * @author  gkueny
 */
public abstract class Tache implements Serializable{

    /**
     * Id de la tache
     */
    private int id;

    /**
     * Titre de la Tache
     */
    protected String title;

    /**
     * Boolean permettant de savoir si la tache est termine ou pas
     */
    private Boolean achieve;

    /**
     * Fin de la Tache
     */
    Calendar end;

    /**
    * Categorie de la tache
    */
    protected Categorie categorie;

    /**
    * Date de creation de la tache
    */
    private Calendar dateCreation;

    /**
     * Date de creation de la tache
     */
    private Calendar achieveDate;

    /**
     * Constructeur de la class
     *
     * @param id id de la tache
     * @param title titre de la Tache
     * @param end fin de la Tache
     * @param categorie categorie de la tache
     */
    public Tache(int id, String title, Calendar end, Categorie categorie)
    {

        this.id         = id;
        this.title      = title;
        this.achieve    = false;
        this.end        = end;
        this.categorie  = categorie;

        dateCreation    = Calendar.getInstance();
        dateCreation.setTime(new Date(System.currentTimeMillis()));

        dateCreation.set(Calendar.HOUR_OF_DAY, 0);
        dateCreation.set(Calendar.MINUTE, 0);
        dateCreation.set(Calendar.SECOND, 0);
        dateCreation.set(Calendar.MILLISECOND, 0);

        achieveDate = Calendar.getInstance();
        achieveDate.setTime(end.getTime());

        achieveDate.set(Calendar.HOUR_OF_DAY, 0);
        achieveDate.set(Calendar.MINUTE, 0);
        achieveDate.set(Calendar.SECOND, 0);
        achieveDate.set(Calendar.MILLISECOND, 0);

    }

    /**
     * Setter de l'attribut  categorie
     *
     * @param categorie nouvelle categorie de la Tache
     */
    public void setCategorie(Categorie categorie)
    {
        this.categorie = categorie;
    }

    /**
     * Setter de l'attribut  title
     *
     * @param title nouveau titre de la Tache
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Setter de l'attribut achieve
     *
     * @param achieve boolean indiquant si la Tache est termine ou pas
     */
    public void setAchieve(Boolean achieve)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date(System.currentTimeMillis()));

        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        if(achieve)
            this.setAchieveDate(now);

        this.achieve = achieve;
    }

    /**
     * Setter de l'attribut end
     *
     * @param end date de fin de la tache
     */
    public void setEnd(Calendar end)
    {
        this.end = end;
    }

    void setAchieveDate(Calendar achieveDate)
    {
        this.achieveDate = achieveDate;
    }

    /**
     * Getter de l'attribut id
     *
     * @return int id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Getter de l'attribut dateCreation
     *
     * @return Calendar dateCreation
     */
    public Calendar getDateCreation()
    {
        return dateCreation;
    }
    
    /**
     * Getter de l'attribut categorie
     *
     * @return Categorie categorie
     */
    public Categorie getCategorie()
    {
        return categorie;
    }

    /**
     * Getter de l'attribut title
     *
     * @return String title
     */
    public String getTitle()
    {

        return title;
    }

    /**
     * Getter de l'attribut achieve
     *
     * @return Boolean achieve
     */
    public Boolean getAchieve()
    {
        return achieve;
    }

    /**
     * Getter de l'attribut end
     *
     * @return {@link Calendar} end
     */
    public Calendar getEnd()
    {
        return end;
    }

    /**
     * Getter de l'attribut achieveDate
     *
     * @return {@link Calendar} achieveDate
     */
    public Calendar getAchieveDate()
    {
        return achieveDate;
    }

    /**
     * Savoir si la tache est en retard ou pas
     *
     * @return true if Tache is late, false if tache is not late
     */
    public abstract Boolean isLate();

    /**
     * Getter de l'échéance intermédiare
     *
     * @return {@link Calendar} échéance intermédiare de la tache
     */
    public abstract Calendar getDateEcheanceIntermediaire();

}
