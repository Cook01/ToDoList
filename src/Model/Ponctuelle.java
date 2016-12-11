package Model;

import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Class Ponctuelle</h1>
 *
 * @author  gkueny
*/
public class Ponctuelle extends Tache {

    /**
     * Constructeur de la class
     *
     * @param id id de la tache
     * @param title titre de la Tache
     * @param end fin de la Tache
     * @param categorie categorie de la tache
     */
    public Ponctuelle(int id, String title, Calendar end, Categorie categorie)
    {
        super(id, title,end, categorie);
    }


    /**
     * Savoir si la tache est en retard ou pas
     *
     * @return true if Tache is late, false if tache is not late
     */
    public Boolean isLate()
    {


        Calendar currentCalendar    = Calendar.getInstance();
        Date today                  = new Date();

        currentCalendar.setTime(today);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

        // On retourne la comparaison entre la date du moment et la date de fin de la tache
        return ( currentCalendar.compareTo(this.end) > 0 );

    }

    /**
     * ONLY FOR TESTING
     *
     * @return true if Tache is late, false if tache is not late
     */
    Boolean isLate(Date todayForTheTest) {

        Calendar currentCalendar = Calendar.getInstance();

        currentCalendar.setTime(todayForTheTest);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);


        return ( currentCalendar.after(this.end) );

    }

    /**
     * Getter de l'échéance intermédiare
     *
     * @return {@link Calendar} échéance intermédiare de la tache
     */
    public Calendar getDateEcheanceIntermediaire()
    {
        return getEnd();
    }
}
