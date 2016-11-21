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
     * @param title titre de la Tache
     * @param end   fin de la Tache
     */
    public Ponctuelle(int id, String title, Calendar end) {
        super(id, title,end);
    }


    /**
     * Savoir si la tache est en retard ou pas
     *
     * @return true if Tache is late, false if tache is not late
     */
    public Boolean isLate() {


        Calendar currentCalendar    = Calendar.getInstance();
        Date today                  = new Date();

        currentCalendar.setTime(today);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);


        return ( currentCalendar.compareTo(this.end) > 0 );

    }

    /**
     * ONLY FOR TESTING
     *
     * @return true if Tache is late, false if tache is not late
     */
    public Boolean isLate(Date todayForTheTest) {

        Calendar currentCalendar = Calendar.getInstance();

        currentCalendar.setTime(todayForTheTest);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);


        return ( currentCalendar.after(this.end) );

    }
}
