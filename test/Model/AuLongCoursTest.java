package Model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;


/**
 * <h1>Classe de test JUnit pour la class {@link AuLongCoursTest}</h1>
 *
 * @author gkueny
 */
public class AuLongCoursTest extends TestCase {

    /**
     * totalAssertions
     */
    private static int totalAssertions = 0;

    /**
     * bilanAssertions
     */
    private static int bilanAssertions = 0;

    /**
     * Categorie
     */
    private static Categorie cat = new Categorie("test", "tes");

    /**
     * Test de la methode getBegin()
     *
     * @see AuLongCours#getBegin()
     */
    public void test_getBegin() {
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


        final AuLongCours testTache = new AuLongCours(1, "Tache de test",begin, end, cat);


        Calendar beginGet = testTache.getBegin();

        totalAssertions++ ;
        assertEquals(beginGet, begin);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getPercentage()
     *
     * @see AuLongCours#getPercentage()
     */
    public void test_getPercentage() {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        final AuLongCours testTache = new AuLongCours(1,"Tache de test", end, cat);


        int percentage = testTache.getPercentage();

        totalAssertions++ ;
        assertEquals(percentage, 0);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode setBegin()
     *
     * @see AuLongCours#setBegin(java.util.Calendar)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setBegin() throws Exception {
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


        final AuLongCours testTache = new AuLongCours(1,"Tache de test",begin, end, cat);


        testTache.setBegin(newEnd);

        final Field field = testTache.getClass().getDeclaredField("begin");
        field.setAccessible(true);


        totalAssertions++ ;
        assertEquals(field.get(testTache),newEnd);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode setPercentage()
     *
     * @see AuLongCours#setPercentage(int)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setPercentage() throws Exception {
        //given
        Calendar end        = Calendar.getInstance();
        int newPercentage   = 50;

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        final AuLongCours testTache = new AuLongCours(1,"Tache de test", end, cat);


        //when
        testTache.setPercentage(newPercentage);


        //then
        final Field field = testTache.getClass().getDeclaredField("percentage");
        field.setAccessible(true);

        totalAssertions++ ;
        assertEquals(field.get(testTache),newPercentage);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isNotLate25Percent() throws Exception {
        Calendar end    = Calendar.getInstance();
        Date endTest    = new Date(System.currentTimeMillis() + (  24 * 60 * 60 * 1000 ));// 25% = 1J

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);


        testTache.setPercentage(25);


        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertFalse( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isLate25Percent() throws Exception {

        Calendar end    = Calendar.getInstance();
        Date endTest    = new Date(System.currentTimeMillis() + (  24 * 60 * 60 * 1000 ));// 25% = 1J

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);


        testTache.setPercentage(20);


        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertTrue( islate );
        bilanAssertions++ ;
    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isNotLate50Percent() throws Exception {
        Calendar end    = Calendar.getInstance();
        Date endTest    = new Date(System.currentTimeMillis() + ( 2 * 24 * 60 * 60 * 1000 ));// 50% = 2J

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);


        testTache.setPercentage(55);


        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertFalse( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isLate50Percent() throws Exception {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);


        testTache.setPercentage(40);

        Date endTest = new Date(System.currentTimeMillis() + (  2 *  24 * 60 * 60 * 1000 ));// 50% = 2J

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertTrue( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isNotLate75Percent() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);

        testTache.setPercentage(99);

        Date endTest = new Date(System.currentTimeMillis() + ( 3 * 24 * 60 * 60 * 1000 ));// 75% = 3J

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertFalse( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isLate75Percent() throws Exception {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);


        testTache.setPercentage(74);

        Date endTest = new Date(System.currentTimeMillis() + (  3 *  24 * 60 * 60 * 1000 ));// 75% = 3J

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertTrue( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isNotLate100Percent() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);

        testTache.setPercentage(100);

        Date endTest = new Date(System.currentTimeMillis() + ( 4 * 24 * 60 * 60 * 1000 ));// 100% = 4J

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertFalse( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode isLate()
     *
     * @see AuLongCours#isLate()
     */
    public void test_isLate100Percent() throws Exception {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test" , end, cat);


        testTache.setPercentage(3);

        Date endTest = new Date(System.currentTimeMillis() + (  4 *  24 * 60 * 60 * 1000 ));// 100% = 4J

        Boolean islate = testTache.isLate(endTest);

        totalAssertions++ ;
        assertTrue( islate );
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getDateEcheanceIntermediaire()
     *
     * @see AuLongCours#getDateEcheanceIntermediaire()
     */
    public void test_getDateEcheanceIntermediaire() {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final AuLongCours testTache = new AuLongCours(1,"Tache de test", end, cat);


        Calendar dateEcheanceIntermediaire = testTache.getDateEcheanceIntermediaire();


        c.add(Calendar.DATE, 1);
        totalAssertions ++;
        assertEquals( c , dateEcheanceIntermediaire);
        bilanAssertions++ ;

        testTache.setPercentage(26);

        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 2);

        dateEcheanceIntermediaire = testTache.getDateEcheanceIntermediaire();

        totalAssertions ++;
        assertEquals( c , dateEcheanceIntermediaire);
        bilanAssertions++ ;

        testTache.setPercentage(56);

        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 3);

        dateEcheanceIntermediaire = testTache.getDateEcheanceIntermediaire();

        totalAssertions ++;
        assertEquals( c , dateEcheanceIntermediaire);
        bilanAssertions++ ;

        testTache.setPercentage(76);

        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 4);

        dateEcheanceIntermediaire = testTache.getDateEcheanceIntermediaire();

        totalAssertions ++;
        assertEquals( c , dateEcheanceIntermediaire);
        bilanAssertions++ ;

    }

    public static void main(String[] args) {

        junit.textui.TestRunner.run(new TestSuite(AuLongCoursTest.class));

        if (bilanAssertions == totalAssertions) {
            System.out.print("Bravo ! ");
        }
        else  {
            System.out.print("OUPS ! ");
        }

        System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");

    } // fin main


}