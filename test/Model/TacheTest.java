package Model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;


/**
 * <h1>Classe de test JUnit pour la class {@link TacheTest} via la class {@link Ponctuelle}</h1>
 *
 * @author gkueny
 */
public class TacheTest extends TestCase {

    public static int totalAssertions = 0;
    public static int bilanAssertions = 0;

    /**
     * Test de la methode setTitle()
     *
     * @see Tache#setTitle(String)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setTitle() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end);

        //when
        testTache.setTitle("Nouveau titre");

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("title");
        field.setAccessible(true);

        totalAssertions ++;
        assertEquals(field.get(testTache), "Nouveau titre");
        bilanAssertions++ ;
    }

    /**
     * Test de la methode setAchieve()
     *
     * @see Tache#setAchieve(Boolean)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setAchieve() throws Exception {
        //given
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end);

        //when
        testTache.setAchieve(true);

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("achieve");
        field.setAccessible(true);

        totalAssertions ++;
        assertEquals(field.get(testTache), true);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode setEnd()
     *
     * @see Tache#setEnd(java.util.Calendar)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setEnd() throws Exception {
        //given
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end);

        Calendar nouvelleDate    = Calendar.getInstance();

        nouvelleDate.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        nouvelleDate.set(Calendar.HOUR_OF_DAY, 0);
        nouvelleDate.set(Calendar.MINUTE, 0);
        nouvelleDate.set(Calendar.SECOND, 0);
        nouvelleDate.set(Calendar.MILLISECOND, 0);
        //when
        testTache.setEnd(nouvelleDate);

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("end");
        field.setAccessible(true);

        totalAssertions ++;
        assertEquals(field.get(testTache),nouvelleDate);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getTitle()
     *
     * @see Tache#getTitle()
     */
    public void test_getTitle() {


        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end);

        String title = testTache.getTitle();

        totalAssertions ++;
        assertEquals(title, "Tache de test");
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getAchieve()
     *
     * @see Tache#getAchieve()
     */
    public void test_getAchieve() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end);


        Boolean achieve = testTache.getAchieve();

        totalAssertions ++;
        assertFalse(achieve);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getEnd()
     *
     * @see Tache#getEnd()
     */
    public void test_getEnd() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end);


        Calendar date = testTache.getEnd();

        Calendar testDate    = Calendar.getInstance();

        testDate.setTime(new Date(System.currentTimeMillis()));

        totalAssertions ++;
        assertTrue( testDate.compareTo(date) < 0 );
        bilanAssertions++ ;

    }


    public static void main(String[] args) {

        junit.textui.TestRunner.run(new TestSuite(TacheTest.class));

        if (bilanAssertions == totalAssertions) {
            System.out.print("Bravo ! ");
        }
        else  {
            System.out.print("OUPS ! ");
        }

        System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");

    } // fin main

}
