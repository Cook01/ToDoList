package Model;

enum MenuItem {

	CARTEPONCTUELLE("Carte ponctuelle"),
	CARTEAULONGCOURS("Carte AuLongCours"),
	CATEGORIE("Catégorie"),
	SAUVEGARDER("Sauvegarder"),
	QUITTER("Quitter");

	private final String text;

    private Strings(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

