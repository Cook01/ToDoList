package Model;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * <h1>Classe de test JUnit pour la class {@link TacheTest} via la class {@link Ponctuelle}</h1>
 *
 * @author gkueny
 */
public class TacheTest {

    /**
     * Test de la methode setTitle()
     *
     * @see Tache#setTitle(String)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    @Test
    public void setTitle() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);

        //when
        testTache.setTitle("Nouveau titre");

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals(field.get(testTache), "Nouveau titre");
    }

    /**
     * Test de la methode setAchieve()
     *
     * @see Tache#setAchieve(Boolean)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    @Test
    public void setAchieve() throws Exception {
        //given
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);

        //when
        testTache.setAchieve(true);

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("achieve");
        field.setAccessible(true);
        assertEquals(field.get(testTache), true);
    }

    /**
     * Test de la methode setEnd()
     *
     * @see Tache#setEnd(java.util.Calendar)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    @Test
    public void setEnd() throws Exception {
        //given
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);

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
        assertEquals(field.get(testTache),nouvelleDate);
    }

    /**
     * Test de la methode getTitle()
     *
     * @see Tache#getTitle()
     */
    @Test
    public void getTitle() {


        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);

        String title = testTache.getTitle();

        assertEquals(title, "Tache de test");

    }

    /**
     * Test de la methode getAchieve()
     *
     * @see Tache#getAchieve()
     */
    @Test
    public void getAchieve() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);


        Boolean achieve = testTache.getAchieve();

        assertEquals(achieve, false);

    }

    /**
     * Test de la methode getEnd()
     *
     * @see Tache#getEnd()
     */
    @Test
    public void getEnd() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);


        Calendar date = testTache.getEnd();

        Calendar testDate    = Calendar.getInstance();

        testDate.setTime(new Date(System.currentTimeMillis()));

        assertTrue( testDate.compareTo(date) < 0 );

    }

}
