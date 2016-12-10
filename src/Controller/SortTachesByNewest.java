package Controller;

import java.util.*;
import java.util.ArrayList;

import Model.*;

/**
 * Implémentation du trie par date de fin
 */
class SortTachesByNewest implements SortTaches
{
    // Comparaisons des dates de fin de chaque taches
    private Comparator<Tache> comparator = Comparator.comparing(left -> left.getEnd().getTime());

    /**
     * Fonction de trie des taches
     *
     * @param allTaches ArrayList de toute les taches
     * @return ArrayList de taches triées
     */
	public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
	{
		// Sorting
		Collections.sort(allTaches, comparator);

		return allTaches;
	}
}