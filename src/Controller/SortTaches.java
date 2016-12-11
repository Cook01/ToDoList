package Controller;

import Model.Tache;

import java.util.ArrayList;

/**
 * <h1> Interface pour les types de tries</h1>
 *
 * @author Gaëtan KUENY
 */
interface SortTaches
{
	/**
	 * Fonction de trie des taches
	 *
	 * @param allTaches ArrayList de toute les taches
	 * @return une ArrayList des taches triées
	 */
	ArrayList<Tache> sort(ArrayList<Tache> allTaches);
}