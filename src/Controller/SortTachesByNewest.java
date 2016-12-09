package Controller;

import java.util.*;
import java.util.ArrayList;

import Model.*;

class SortTachesByNewest implements SortTaches
{
	public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
	{
		// Sorting
		Collections.sort(allTaches, Comparator.comparing(Tache::getEnd));

		return allTaches;
	}
}