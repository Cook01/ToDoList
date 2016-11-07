package Model;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;


/**
 * <h1>Classe de test JUnit pour la class {@link AuLongCoursTest}</h1>
 *
 * @author gkueny
 */
public class AuLongCoursTest {

    /**
     * Test de la methode getBegin()
     *
     * @see AuLongCours#getBegin()
     */
    @Test
    public void getBegin() {
        Calendar end    = Calendar.getInstance();
        Calendar begin  = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        begin.setTime(new Date(System.currentTimeMillis()));

        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);


        final AuLongCours testTache = new AuLongCours("Tache de test",begin, end);


        Calendar beginGet = testTache.getBegin();

        assertEquals(beginGet, begin);
    }

    /**
     * Test de la methode getPercentage()
     *
     * @see AuLongCours#getPercentage()
     */
    @Test
    public void getPercentage() {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        final AuLongCours testTache = new AuLongCours("Tache de test", end);


        int percentage = testTache.getPercentage();

        assertEquals(percentage, 0);
    }

    /**
     * Test de la methode setBegin()
     *
     * @see AuLongCours#setBegin(java.util.Calendar)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    @Test
    public void setBegin() throws Exception {
        //given
        Calendar end    = Calendar.getInstance();
        Calendar begin  = Calendar.getInstance();
        Calendar newEnd = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        begin.setTime(new Date(System.currentTimeMillis() ));
        newEnd.setTime(new Date(System.currentTimeMillis() + (6 * 24 * 60 * 60 * 1000)));

        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        newEnd.set(Calendar.HOUR_OF_DAY, 0);
        newEnd.set(Calendar.MINUTE, 0);
        newEnd.set(Calendar.SECOND, 0);
        newEnd.set(Calendar.MILLISECOND, 0);


        final AuLongCours testTache = new AuLongCours("Tache de test",begin, end);


        testTache.setBegin(newEnd);

        final Field field = testTache.getClass().getDeclaredField("begin");
        field.setAccessible(true);


        assertEquals(field.get(testTache),newEnd);
    }

    /**
     * Test de la methode setPercentage()
     *
     * @see AuLongCours#setPercentage(int)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    @Test
    public void setPercentage() throws Exception {
        //given
        Calendar end        = Calendar.getInstance();
        int newPercentage   = 50;

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        final AuLongCours testTache = new AuLongCours("Tache de test", end);


        //when
        testTache.setPercentage(newPercentage);


        //then
        final Field field = testTache.getClass().getDeclaredField("percentage");
        field.setAccessible(true);
        assertEquals(field.get(testTache),newPercentage);
    }

    @Test
    public void isNotLate25Percent() throws Exception {
        Calendar end    = Calendar.getInstance();
        Date endTest    = new Date(System.currentTimeMillis() + (  24 * 60 * 60 * 1000 ));// 25% = 1J

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);


        testTache.setPercentage(25);


        Boolean islate = testTache.isLate(endTest);

        assertFalse( islate );
    }

    @Test
    public void isLate25Percent() throws Exception {

        Calendar end    = Calendar.getInstance();
        Date endTest    = new Date(System.currentTimeMillis() + (  24 * 60 * 60 * 1000 ));// 25% = 1J

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);


        testTache.setPercentage(20);


        Boolean islate = testTache.isLate(endTest);

        assertTrue( islate );
    }

    @Test
    public void isNotLate50Percent() throws Exception {
        Calendar end    = Calendar.getInstance();
        Date endTest    = new Date(System.currentTimeMillis() + ( 2 * 24 * 60 * 60 * 1000 ));// 50% = 2J

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);


        testTache.setPercentage(55);


        Boolean islate = testTache.isLate(endTest);

        assertFalse( islate );
    }

    @Test
    public void isLate50Percent() throws Exception {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);


        testTache.setPercentage(40);

        Date endTest = new Date(System.currentTimeMillis() + (  2 *  24 * 60 * 60 * 1000 ));// 50% = 2J

        Boolean islate = testTache.isLate(endTest);

        assertTrue( islate );
    }

    @Test
    public void isNotLate75Percent() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);

        testTache.setPercentage(99);

        Date endTest = new Date(System.currentTimeMillis() + ( 3 * 24 * 60 * 60 * 1000 ));// 75% = 3J

        Boolean islate = testTache.isLate(endTest);

        assertFalse( islate );
    }

    @Test
    public void isLate75Percent() throws Exception {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);


        testTache.setPercentage(74);

        Date endTest = new Date(System.currentTimeMillis() + (  3 *  24 * 60 * 60 * 1000 ));// 75% = 3J

        Boolean islate = testTache.isLate(endTest);

        assertTrue( islate );
    }

    @Test
    public void isNotLate100Percent() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);

        testTache.setPercentage(100);

        Date endTest = new Date(System.currentTimeMillis() + ( 4 * 24 * 60 * 60 * 1000 ));// 100% = 4J

        Boolean islate = testTache.isLate(endTest);

        assertFalse( islate );
    }

    @Test
    public void isLate100Percent() throws Exception {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours("Tache de test" , end);


        testTache.setPercentage(3);

        Date endTest = new Date(System.currentTimeMillis() + (  4 *  24 * 60 * 60 * 1000 ));// 100% = 4J

        Boolean islate = testTache.isLate(endTest);

        assertTrue( islate );
    }



}