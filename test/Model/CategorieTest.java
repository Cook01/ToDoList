package Model;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * <h1>Classe de test JUnit pour la classe {@link Categorie}</h1>
 *
 * @author  Vincent THOMAS
 */
public class CategorieTest {

    /**
     * Test de la methode setTitre()
     *
     * @see Categorie#setTitre(String)
     * @throws Exception
     */
    @Test
    public void setTitre() throws Exception {
        //given
        final Categorie testCat = new Categorie("Categorie de test", "testCat");

        //when
        testCat.setTitre("Nouveau titre");

        //then
        final Field field = testCat.getClass().getDeclaredField("titre");
        field.setAccessible(true);
        assertEquals(field.get(testCat), "Nouveau titre");
    }

    /**
     * Test de la methode setAbreviation()
     *
     * @see Categorie#setAbreviation(String)
     * @throws Exception
     */
    @Test
    public void setAbreviation() throws Exception {
        //given
        final Categorie testCat = new Categorie("Categorie de test", "testCat");

        //when
        testCat.setAbreviation("newAbrv");

        //then
        final Field field = testCat.getClass().getDeclaredField("abreviation");
        field.setAccessible(true);
        assertEquals(field.get(testCat), "newAbrv");
    }
}