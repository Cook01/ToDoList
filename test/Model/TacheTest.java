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

    private static int totalAssertions = 0;
    private static int bilanAssertions = 0;
    private static Categorie cat = new Categorie("test", "tes");


    /**
     * Test de la methode setCategorie()
     *
     * @see Tache#setCategorie(Categorie)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setCategorie() throws Exception {
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

        Categorie newCat = new Categorie("test2", "tes2");
        //when
        testTache.setCategorie(newCat);

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("categorie");
        field.setAccessible(true);

        totalAssertions ++;
        assertEquals(field.get(testTache), newCat);
        bilanAssertions++ ;
    }

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

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

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

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

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

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

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
     * Test de la methode setAchieveDate()
     *
     * @see Tache#setAchieveDate(java.util.Calendar)
     * @throws Exception NoSuchFieldException, IllegalAccessException
     */
    public void test_setAchieveDate() throws Exception {
        //given
        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() ));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

        Calendar nouvelleDate    = Calendar.getInstance();

        nouvelleDate.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        nouvelleDate.set(Calendar.HOUR_OF_DAY, 0);
        nouvelleDate.set(Calendar.MINUTE, 0);
        nouvelleDate.set(Calendar.SECOND, 0);
        nouvelleDate.set(Calendar.MILLISECOND, 0);
        //when
        testTache.setAchieveDate(nouvelleDate);

        //then
        final Field field = testTache.getClass().getSuperclass().getDeclaredField("achieveDate");
        field.setAccessible(true);

        totalAssertions ++;
        assertEquals(field.get(testTache),nouvelleDate);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getId()
     *
     * @see Tache#getId()
     */
    public void test_getId() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

        int id = testTache.getId();

        totalAssertions ++;
        assertEquals(id, 1);
        bilanAssertions++ ;

    }


    /**
     * Test de la methode getDateCreation()
     *
     * @see Tache#getDateCreation()
     */
    public void test_getDateCreation() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        Calendar dateCreationTest = Calendar.getInstance();
        dateCreationTest.setTime(new Date(System.currentTimeMillis()));
        dateCreationTest.set(Calendar.HOUR_OF_DAY, 0);
        dateCreationTest.set(Calendar.MINUTE, 0);
        dateCreationTest.set(Calendar.SECOND, 0);
        dateCreationTest.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

        Calendar dateCreation = testTache.getDateCreation();

        totalAssertions ++;
        assertEquals(dateCreation, dateCreationTest);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getCategorie()
     *
     * @see Tache#getCategorie()
     */
    public void test_getCategorie() {


        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

        Categorie categorie = testTache.getCategorie();

        totalAssertions ++;
        assertEquals(categorie, cat);
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

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);

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

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);


        Boolean achieve = testTache.getAchieve();

        totalAssertions ++;
        assertFalse(achieve);
        bilanAssertions++ ;

        testTache.setAchieve(true);

        achieve = testTache.getAchieve();

        totalAssertions ++;
        assertTrue(achieve);
        bilanAssertions++ ;

    }

    /**
     * Test de la methode getAchieveDate()
     *
     * @see Tache#getAchieveDate()
     */
    public void test_getAchieveDate() {

        Calendar end    = Calendar.getInstance();

        end.setTime(new Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000)));
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);


        Calendar now    = Calendar.getInstance();

        now.setTime(new Date(System.currentTimeMillis()));
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);


        testTache.setAchieve(true);

        Calendar achieve = testTache.getAchieveDate();

        totalAssertions ++;
        assertEquals(achieve, now);
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

        final Ponctuelle testTache = new Ponctuelle(1,"Tache de test", end, cat);


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
