package Model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * <h1>Classe de test JUnit pour la class {@link Ponctuelle}</h1>
 *
 * @author gkueny
 */
public class PonctuelleTest {

    /**
     * Test de la methode isLate()
     * Dans le cas ou la Tache est en retard
     *
     * @see     Tache#isLate()
     * @throws  InterruptedException (sleep method)
     */
    @Test
    public void isLate() throws InterruptedException {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        final Ponctuelle testTache = new Ponctuelle("Tache de test" , end);

        Date endTest = new Date(System.currentTimeMillis() + (6 * 24 * 60 * 60 * 1000));//6 jours

        Boolean islate = testTache.isLate(endTest);

        assertTrue( islate );

    }

    /**
     * Test de la methode isLate()
     * Dans le cas ou la Tache n'est pas en retard
     *
     * @see     Tache#isLate()
     * @throws  InterruptedException (sleep method)
     */
    @Test
    public void isNotLate() throws InterruptedException {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test" , end);

        Date endTest = new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000));//6 jours

        Boolean islate = testTache.isLate(endTest);

        assertFalse( islate );

    }

}
