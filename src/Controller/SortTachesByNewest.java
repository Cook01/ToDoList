package Controller;

import java.util.*;
import java.util.ArrayList;

import Model.*;

class SortTachesByNewest implements SortTaches
{

    private Comparator<Tache> comparator = (left, right) -> left.getEnd().getTime().compareTo(right.getEnd().getTime());

	public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
	{
		// Sorting
		Collections.sort(allTaches, comparator);

		return allTaches;
	}
}