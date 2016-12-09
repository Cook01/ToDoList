package Controller;

import Model.Tache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SortTachesByIntermediaire implements SortTaches
{

    private Comparator<Tache> comparator = (left, right) -> left.getEnd().getTime().compareTo(right.getEnd().getTime());

    public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
    {
        // Sorting
        Collections.sort(allTaches, comparator);

        return allTaches;
    }
}