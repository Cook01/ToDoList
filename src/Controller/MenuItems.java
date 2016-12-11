package Controller;

/**
 * Enumeration des Items de Menu disponibles
 *
 * @author Gaëtan KUENY
 */
public enum MenuItems {

	CARTEPONCTUELLE("Carte ponctuelle"),
	CARTEAULONGCOURS("Carte AuLongCours"),
	CATEGORIE("Catégorie"),
	SAUVEGARDER("Sauvegarder"),
    BILAN("Bilan");

    /**
     * Texte a afficher correspondant a l'Item de Menu
     */
    private final String text;


    /**
     * Constructeur de l'enumeration MenuItems
     *
     * @param text Texte a afficher correspondant a l'Item de Menu
     */
    MenuItems(final String text) {
        this.text = text;
    }


    /**
     * Renvoi le texte a afficher corespondant a l'Item de Menu
     *
     * @return String Texte a afficher correspondant a l'Item de Menu
     */
    @Override
    public String toString() {
        return text;
    }
}

