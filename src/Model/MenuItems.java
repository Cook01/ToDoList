package Model;

public enum MenuItems {

	CARTEPONCTUELLE("Carte ponctuelle"),
	CARTEAULONGCOURS("Carte AuLongCours"),
	CATEGORIE("Catégorie"),
	SAUVEGARDER("Sauvegarder"),
	QUITTER("Quitter");

	private final String text;

    private MenuItems(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

