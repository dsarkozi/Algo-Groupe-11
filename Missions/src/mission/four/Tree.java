package mission.four;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mission.two.BTNode;

public class Tree<E>
{

	/**
	 * Benoit Sluysmans Retourne toute les valeurs de l'arbre dans l'ordre du
	 * ieme critere
	 * 
	 */
	protected BTNode<E> root;
	protected int size;

	/* Constructeur de base */
	public Tree()
	{
		this.root = null;
		this.size = 0;
	}

	public Tree(BTNode<E> root)
	{
		this.root = root;
		this.size = 1;
	}

	/**
	 * Ajoute la revue a partir de l'attribut reference par {@code str}.
	 * 
	 * @pre -
	 * @post La revue referencee par {@code journal} a ete ajoutee a la
	 *       structure de donnees
	 * @meth.author Loic Lacomblez
	 * @param str
	 *            ?
	 * @param journal
	 *            La revue a ajouter a la structure de donnees
	 */
	public void put(String str, Journal journal)
	{
		// TODO put(String,Journal)
	}

	/**
	 * Enleve la revue referencee par son titre et la retourne.
	 * 
	 * @meth.author David Sarkozi
	 * @param title
	 *            Le titre de la revue a enlever de la structure de donnees
	 * @return La revue enlevee
	 */
	public Journal remove(String title)
	{
		// TODO remove(String)
		return null;
	}

	/* METHODES DE RECHERCHE */

	public ArrayList<E> getAllValues(int i)
	{
		if (size == 0) return null;
		else
		{
			LinkedList<E> all = new LinkedList<E>();
			return (ArrayList<E>) getHelper(root, all, i);
		}
	}

	public List<E> getHelper(BTNode<E> current, List all, int i)
	{
		if (current.getLeft() == null && current.getRight() == null) all
				.add(current.getList().getValue(i));
		else
		{
			if (current.getLeft() != null) getHelper(current.getLeft(), all, i);

			all.add(current.getList().getValue(i), i);

			if (current.getRight() != null) getHelper(current.getRight(), all,
					i);
		}
		return all;
	}

	/**
	 * Benoit Sluysmans Retourne toute les cles de l'arbre dans l'ordre du ieme
	 * critere
	 */
	public ArrayList<E> getAllKeys(int i)
	{
		if (size == 0) return null;
		else
		{
			LinkedList<E> all = new LinkedList<E>();
			return (ArrayList<E>) getHelper2(root, all, i);
		}
	}

	public List<E> getHelper2(BTNode<E> current, List all, int i)
	{
		if (current.getLeft() == null && current.getRight() == null) all
				.add(current.getList().getKey(i));
		else
		{
			if (current.getLeft() != null) getHelper(current.getLeft(), all, i);

			all.add(current.getList().getKey(i));

			if (current.getRight() != null) getHelper(current.getRight(), all,
					i);
		}
		return all;
	}
}
