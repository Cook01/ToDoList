package Model;

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
    public Ponctuelle(String title, Date end) {
        super(title,end);
    }


    /**
     * Savoir si la tache est en retard ou pas
     *
     * @return true if Tache is late, false if tache is not late
     */
    public Boolean isLate() {

        return ( new Date(System.currentTimeMillis()).compareTo(this.getEnd()) > 0 );

    }
}
