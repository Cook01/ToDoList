package Controller;

import Model.Tache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <h1> Implémentation du trie par échéance intermédiaire</h1>
 *
 * @author Gaëtan KUENY
 */
class SortTachesByIntermediaire implements SortTaches
{

    // Comparaisons des echances intermediaire de chaque tache
    private Comparator<Tache> comparator = Comparator.comparing(left -> left.getDateEcheanceIntermediaire().getTime());

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