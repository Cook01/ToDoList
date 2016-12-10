package Controller;

import Model.Tache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SortTachesByIntermediaire implements SortTaches
{

    private Comparator<Tache> comparator = (left, right) -> left.getDateEcheanceIntermediaire().getTime().compareTo(right.getDateEcheanceIntermediaire().getTime());

    public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
    {
        // Sorting
        Collections.sort(allTaches, comparator);

        return allTaches;
    }
}