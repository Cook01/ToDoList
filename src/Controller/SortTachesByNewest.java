package Controller;

import java.util.*;
import java.util.ArrayList;

import Model.*;

public class SortTachesByNewest implements SortTaches
{
	public ArrayList<Tache> sort(ArrayList<Tache> allTaches)
	{
		// Sorting
		Collections.sort(allTaches, new Comparator<Tache>() {
			@Override
			public int compare(Tache tache1, Tache tache2)
			{

				return  tache1.getEnd().compareTo(tache2.getEnd());
			}
		});

		return allTaches;
	}
}