package Model;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * <h1>Classe de test JUnit pour la classe {@link Tache}</h1>
 *
 * @author  gkueny
 */
public class TacheTest {

    /**
     * Test de la methode setTitle()
     *
     * @see Tache#setTitle(String)
     * @throws Exception
     */
    @Test
    public void setTitle() throws Exception {
        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);

        //when
        testTache.setTitle("Nouveau titre");

        //then
        final Field field = testTache.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals(field.get(testTache), "Nouveau titre");
    }

    /**
     * Test de la methode setAchieve()
     *
     * @see Tache#setAchieve(Boolean)
     * @throws Exception
     */
    @Test
    public void setAchieve() throws Exception {
        //given
        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);

        //when
        testTache.setAchieve(true);

        //then
        final Field field = testTache.getClass().getDeclaredField("achieve");
        field.setAccessible(true);
        assertEquals(field.get(testTache), true);
    }

    /**
     * Test de la methode setPercentage()
     *
     * @see Tache#setPercentage(int)
     * @throws Exception
     */
    @Test
    public void setPercentage() throws Exception {
        //given
        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);

        //when
        testTache.setPercentage(90);

        //then
        final Field field = testTache.getClass().getDeclaredField("percentage");
        field.setAccessible(true);
        assertEquals(field.get(testTache),90);
    }

    /**
     * Test de la methode setBegin()
     *
     * @see Tache#setBegin(java.util.Date)
     * @throws Exception
     */
    @Test
    public void setBegin() throws Exception {
        //given
        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);

        Date nouvelleDate = new Date();
        //when
        testTache.setBegin(nouvelleDate);

        //then
        final Field field = testTache.getClass().getDeclaredField("begin");
        field.setAccessible(true);
        assertEquals(field.get(testTache),nouvelleDate);
    }

    /**
     * Test de la methode setEnd()
     *
     * @see Tache#setEnd(java.util.Date)
     * @throws Exception
     */
    @Test
    public void setEnd() throws Exception {
        //given
        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);

        Date nouvelleDate = new Date();
        //when
        testTache.setEnd(nouvelleDate);

        //then
        final Field field = testTache.getClass().getDeclaredField("end");
        field.setAccessible(true);
        assertEquals(field.get(testTache),nouvelleDate);
    }

    /**
     * Test de la methode getTitle()
     *
     * @see Tache#getTitle()
     * @throws Exception
     */
    @Test
    public void getTitle() throws Exception {

        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);


        String title = testTache.getTitle();

        assertEquals(title, "Tache de test");

    }

    /**
     * Test de la methode getAchieve()
     *
     * @see Tache#getAchieve()
     * @throws Exception
     */
    @Test
    public void getAchieve() throws Exception {

        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);


        Boolean achieve = testTache.getAchieve();

        assertEquals(achieve, false);

    }

    /**
     * Test de la methode getPercentage()
     *
     * @see Tache#getPercentage()
     * @throws Exception
     */
    @Test
    public void getPercentage() throws Exception {

        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);


        int percentage = testTache.getPercentage();

        assertEquals(percentage, 0);

    }

    /**
     * Test de la methode getBegin()
     *
     * @see Tache#getBegin()
     * @throws Exception
     */
    @Test
    public void getBegin() throws Exception {

        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);


        Thread.sleep(100);

        Date date = testTache.getBegin();

        assertTrue( new Date(System.currentTimeMillis()).compareTo(date) > 0 );

    }

    /**
     * Test de la methode getEnd()
     *
     * @see Tache#getEnd()
     * @throws Exception
     */
    @Test
    public void getEnd() throws Exception {

        Date end = new GregorianCalendar(2050,0,31).getTime();

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);


        Date date = testTache.getEnd();

        assertTrue( new Date().compareTo(date) < 0 );

    }

    /**
     * Test de la methode isLate()
     *
     * @see Tache#isLate()
     * @throws Exception
     */
    @Test
    public void isLate() throws Exception {

        Date end = new Date(System.currentTimeMillis() + (2 * 1000));

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);

        //pause for 5,1s
        Thread.sleep(2100);

        Boolean islate = testTache.isLate();

        assertTrue( islate );

    }

    /**
     * Test de la methode isLate()
     *
     * @see Tache#isLate()
     * @throws Exception
     */
    @Test
    public void isNotLate() throws Exception {

        Date end = new Date(System.currentTimeMillis() + (10 * 60 * 1000));

        final Tache testTache = new Tache("Tache de test", Type.AuLongCours, end);


        Boolean islate = testTache.isLate();

        assertFalse( islate );

    }



}