package Model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Field;

/**
 * <h1>Classe de test JUnit pour la classe {@link Categorie}</h1>
 *
 * @author  Vincent THOMAS
 */
public class CategorieTest extends TestCase {

    /**
     * totalAssertions
     */
    private static int totalAssertions = 0;

    /**
     * bilanAssertions
     */
    private static int bilanAssertions = 0;


    /**
     * Test de la methode setTitre()
     *
     * @see Categorie#setTitre(String)
     * @throws Exception
     */
    public void test_setTitre() throws Exception {
        //given
        final Categorie testCat = new Categorie("Categorie de test", "testCat");

        //when
        testCat.setTitre("Nouveau titre");

        //then
        final Field field = testCat.getClass().getDeclaredField("titre");
        field.setAccessible(true);

        totalAssertions++ ;
        assertEquals(field.get(testCat), "Nouveau titre");
        bilanAssertions++ ;

    }

    /**
     * Test de la methode setAbreviation()
     *
     * @see Categorie#setAbreviation(String)
     * @throws Exception
     */
    public void test_setAbreviation() throws Exception {
        //given
        final Categorie testCat = new Categorie("Categorie de test", "testCat");

        //when
        testCat.setAbreviation("newAbrv");

        //then
        final Field field = testCat.getClass().getDeclaredField("abreviation");
        field.setAccessible(true);

        totalAssertions++ ;
        assertEquals(field.get(testCat), "newAbrv");
        bilanAssertions++ ;

    }

    /**
     * main de la classe Test
     *
     * @param args arguments
     *
     */
    public static void main(String[] args) {

        junit.textui.TestRunner.run(new TestSuite(CategorieTest.class));

        if (bilanAssertions == totalAssertions) {
            System.out.print("Bravo ! ");
        }
        else  {
            System.out.print("OUPS ! ");
        }

        System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");

    } // fin main
}