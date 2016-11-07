package Model;

import org.junit.Test;

import java.lang.reflect.Field;
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
        Date end = new GregorianCalendar(2050,0,31).getTime();

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
        Date end = new GregorianCalendar(2050,0,31).getTime();

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
     * @see Tache#setEnd(java.util.Date)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    @Test
    public void setEnd() throws Exception {
        //given
        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);

        Date nouvelleDate = new Date();
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

        Date end = new GregorianCalendar(2050,0,31).getTime();

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

        Date end = new GregorianCalendar(2050,0,31).getTime();

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

        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Ponctuelle testTache = new Ponctuelle("Tache de test", end);


        Date date = testTache.getEnd();

        assertTrue( new Date().compareTo(date) < 0 );

    }

}
