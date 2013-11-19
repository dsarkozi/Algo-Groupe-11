/**
 * 
 */
package mission.four;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Clémentine
 * @param <T>
 *            Decrit une Classe qui construit un Tree à partir de Journals
 * 
 */
public class TreeBuilder
{

	/* the information */
	private ArrayList<Journal> journals;
	private Tree<Journal> tree;
	/*
	 * les données des champs du premier journal du tableau seront décisives
	 * pour l'allure de l'arbre
	 */
	private int numFields;

	/**
	 * Construire une structure T à partir d'une liste
	 */
	public TreeBuilder(ArrayList<Journal> journals)
	{
		this.journals = journals;
	}

	/**
	 * Cree un Tree destine a contenir des Journals
	 * 
	 * @pre journals ne contient pas de valeur null
	 * @post
	 * @exceptions
	 * @return
	 */
	public Tree<Journal> build()
	{
		Journal jr;
		tree = new Tree<Journal>(numFields);
		// pour chaque critère
		for (int i = 0; i < numFields; i++)
		{
			// ajout selon le critère i
			tree.setCurrentPutKey(i);
			// pour chaque journal
			for (int j = 0; j < journals.size(); j++)
			{
				jr = journals.get(j);
				tree.put(jr.getData(i), jr);
			}
		}
		return tree;
	}
}