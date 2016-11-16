package Model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Calendar;
import java.util.Date;

/**
 * <h1>Classe de test JUnit pour la class {@link Ponctuelle}</h1>
 *
 * @author gkueny
 */
public class PonctuelleTest extends TestCase {

    public static int totalAssertions = 0;
    public static int bilanAssertions = 0;

    /**
     * Test de la methode isLate()
     * Dans le cas ou la Tache est en retard
     *
     * @see     Tache#isLate()
     * @throws  InterruptedException (sleep method)
     */
    public void test_isLate() throws InterruptedException {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        final Ponctuelle testTache = new Ponctuelle("Tache de test" , end);

        Date endTest = new Date(System.currentTimeMillis() + (6 * 24 * 60 * 60 * 1000));//6 jours

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertTrue( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     * Dans le cas ou la Tache n'est pas en retard
     *
     * @see     Tache#isLate()
     * @throws  InterruptedException (sleep method)
     */
    public void test_isNotLate() throws InterruptedException {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test" , end);

        Date endTest = new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000));//6 jours

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertFalse( islate );
        bilanAssertions++ ;

    }

    /**
     * main de la classe Test
     *
     * @param args arguments
     *
     */
    public static void main(String[] args) {

        junit.textui.TestRunner.run(new TestSuite(PonctuelleTest.class));

        if (bilanAssertions == totalAssertions) {
            System.out.print("Bravo ! ");
        }
        else  {
            System.out.print("OUPS ! ");
        }

        System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");

    } // fin main

}
