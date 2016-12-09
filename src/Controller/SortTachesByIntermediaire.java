package Controller;

import Model.Tache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SortTachesByIntermediaire implements SortTaches
{

    private Comparator<Tache> comparator = (left, right) -> {
        System.out.println("left : " + left.getTitle() + " -> " + left.getDateEcheanceIntermediaire().getTime());
        System.out.println("right : " + right.getTitle() + " -> " + right.getDateEcheanceIntermediaire().getTime());

        return left.getDateEcheanceIntermediaire().getTime().compareTo(right.getDateEcheanceIntermediaire().getTime());
    };

    public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
    {
        // Sorting
        Collections.sort(allTaches, comparator);

        return allTaches;
    }
}