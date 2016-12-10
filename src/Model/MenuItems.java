package Model;

public enum MenuItems {

	CARTEPONCTUELLE("Carte ponctuelle"),
	CARTEAULONGCOURS("Carte AuLongCours"),
	CATEGORIE("Catégorie"),
	SAUVEGARDER("Sauvegarder"),
    BILAN("Bilan");

	private final String text;

    MenuItems(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

