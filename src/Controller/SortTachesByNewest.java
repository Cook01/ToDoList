package Controller;

import java.util.*;
import java.util.ArrayList;

import Model.*;

class SortTachesByNewest implements SortTaches
{

    private Comparator<Tache> comparator = Comparator.comparing(left -> left.getEnd().getTime());

	public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
	{
		// Sorting
		Collections.sort(allTaches, comparator);

		return allTaches;
	}
}