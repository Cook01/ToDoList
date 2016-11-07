package Model;

import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Class AuLongCours</h1>
 *
 * @author  gkueny
 */
public class AuLongCours extends Tache {

    /**
     * Debut de la tache
     */
    private Calendar begin;

    /**
     *  Pourcentage d'avancement de la Tache
     */
    private int percentage;

    /**
     * Constructeur de la class
     *
     * @param title titre de la Tache
     * @param end   fin de la Tache
     */
    public AuLongCours(String title, Calendar end) {

        super(title,end);

        this.begin = Calendar.getInstance();


        this.begin.setTime(new Date());
        this.begin.set(Calendar.HOUR_OF_DAY, 0);
        this.begin.set(Calendar.MINUTE, 0);
        this.begin.set(Calendar.SECOND, 0);
        this.begin.set(Calendar.MILLISECOND, 0);

        this.percentage = 0;
    }

    /**
     * Constructeur de la class
     *
     * @param title titre de la Tache
     * @param begin debut de la Tache
     * @param end   fin de la Tache
     */
    public AuLongCours(String title,Calendar begin, Calendar end) {
        super(title,end);

        this.begin      = begin;
        this.percentage = 0;
    }

    /**
     * Getter de l'attribut begin
     *
     * @return {@link Calendar} begin
     */
    public Calendar getBegin() {
        return begin;
    }

    /**
     * Getter de l'attribut percentage
     *
     * @return int percentage
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Setter de l'attribut begin
     *
     * @param begin nouveau debut de la tache
     */
    public void setBegin(Calendar begin) {
        this.begin = begin;
    }

    /**
     * Setter de l'attribut percentage
     *
     * @param percentage nouveau avancement de la tache
     */
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Savoir si la tache est en retard ou pas
     *
     * @return true if Tache is late, false if tache is not late
     */
    public Boolean isLate() {

        Date today = new Date();

        Calendar currentCalendar = Calendar.getInstance();

        currentCalendar.setTime(today);
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);



        long dImpartie  = this.end.getTime().getTime() - this.begin.getTime().getTime() ;
        long d          = this.end.getTime().getTime() - currentCalendar.getTime().getTime();

        if ( (dImpartie - d) >= dImpartie ) {
            return this.percentage < 100;
        }

        if ( (dImpartie - d) >= ( 3 * dImpartie / 4) ) {
            return this.percentage < 75;
        }

        if ( (dImpartie - d) >=  ( dImpartie / 2) ) {
            return this.percentage < 50;
        }

        if ( (dImpartie - d) >=  ( dImpartie / 4) ) {
            return this.percentage < 25;
        }

        return this.percentage < 0;


    }


    /**
     * ONLT FOR TEST
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



        long dImpartie  = this.end.getTime().getTime() - this.begin.getTime().getTime() ;
        long d          = this.end.getTime().getTime() - currentCalendar.getTime().getTime();

        if ( (dImpartie - d) >= dImpartie ) {
            return this.percentage < 100;
        }

        if ( (dImpartie - d) >= ( 3 * dImpartie / 4) ) {
            return this.percentage < 75;
        }

        if ( (dImpartie - d) >=  ( dImpartie / 2) ) {
            return this.percentage < 50;
        }

        if ( (dImpartie - d) >=  ( dImpartie / 4) ) {
            return this.percentage < 25;
        }

        return this.percentage < 0;


    }
}
