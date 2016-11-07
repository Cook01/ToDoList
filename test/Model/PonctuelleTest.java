package Model;

import org.junit.Test;

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

        Date end = new Date(System.currentTimeMillis() + (2 * 1000));

        final Ponctuelle testTache = new Ponctuelle("Tache de test" , end);

        //pause for 5,1s
        Thread.sleep(2100);

        Boolean islate = testTache.isLate();

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

        Date end = new Date(System.currentTimeMillis() + (10 * 60 * 1000));

        final Ponctuelle testTache = new Ponctuelle("Tache de test" , end);


        Boolean islate = testTache.isLate();

        assertFalse( islate );

    }

}
