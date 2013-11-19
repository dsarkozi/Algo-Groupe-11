package mission.four;

import java.util.ArrayList;
import java.util.LinkedList;

import mission.two.BTNode;
import mission.two.Position;

public class Tree<E>
{

	protected Node<ArrayList<E>> tableRoot;
	protected int size;
	protected int numFields;
	/*
	 * sélection du champ de l'Objet stoque qui est maintenant valide pour put,
	 * get et remove
	 */
	protected int currentPutKey;
	protected int currentGetKey;
	protected int currentRemoveKey;

	/* Constructeur de base */
	public Tree(int numFields)
	{

		currentPutKey = -1;
		currentGetKey = -1;
		currentRemoveKey = -1;
		this.tableRoot = null;
		this.size = 0;
		this.numFields = numFields;
	}

	/* ADD METHODES D AJOUT / SUPPRESSION HERE */

	/* METHODES DE RECHERCHE */

	/**
	 * Benoit Sluysmans Retourne toute les valeurs de l'arbre dans l'ordre du
	 * ieme critere
	 * 
	 */
	public LinkedList<E> getAllValues(int i)
	{
		if (size == 0) return null;
		else
		{
			LinkedList<E> all = new LinkedList<E>();
			return getHelper(tableRoot, (LinkedList<E>) all, i);

		}
	}

	private LinkedList<E> getHelper(Node<ArrayList<E>> current,
			LinkedList<E> all, int i)
	{
		if (current.getLeft() == null && current.getRight() == null) all
				.add(current.getValue(i));
		else
		{
			if (current.getLeft() != null) getHelper(
					(Node<ArrayList<E>>) current.getLeft(), all, i);

			all.add(current.getValue(i));

			if (current.getRight() != null) getHelper(
					(Node<ArrayList<E>>) current.getRight(), all, i);
		}
		return all;
	}

	/**
	 * Benoit Sluysmans Retourne toute les cles de l'arbre dans l'ordre du ieme
	 * critere
	 */
	public LinkedList<E> getAllKeys(int i)
	{
		if (size == 0) return null;
		else
		{
			LinkedList<E> all = new LinkedList<E>();
			return (LinkedList<E>) getHelper2(tableRoot, (LinkedList<E>) all, i);

		}
	}

	private LinkedList<E> getHelper2(Node<ArrayList<E>> current,
			LinkedList<E> all, int i)
	{
		if (current.getLeft() == null && current.getRight() == null) all
				.add(current.getValue(i));
		else
		{
			if (current.getLeft() != null) getHelper(
					(Node<ArrayList<E>>) current.getLeft(), all, i);

			all.add(current.getValue(i));

			if (current.getRight() != null) getHelper(
					(Node<ArrayList<E>>) current.getRight(), all, i);
		}
		return all;
	}
	
	/**
	 * Henri Crombe
	 * getAllKeysWithRank permet de r�cup�rer les noms des r�vues (dans l'ordre alphabetique) qui ont le rang �quivalent � String rank
	 */
	public LinkedList<E> getAllKeysWithRank (String rank){
		
		
		if(size == 0 ) 
			return null;
		else{
			LinkedList<E> rankI =  new LinkedList<E>();
			return getHelper3(tableRoot,rankI,rank);
		}
	}
	public LinkedList<E> getHelper3(Node<ArrayList<E>> current,
			LinkedList<E> rankI, String rank) 
	{
		if (current.getLeft() == null && current.getRight() == null){
			if(current.getValue(0) == rank) rankI.add(current.getValue(1)); // Si le noeud courrant a le rang qui nous interresse, on place le nom de la revue dans la linkedlist
			
		}
		else
		{
			if (current.getLeft() != null) getHelper3(
					(Node<ArrayList<E>>) current.getLeft(), rankI, rank);

			rankI.add(current.getValue(1));

			if (current.getRight() != null) getHelper3(
					(Node<ArrayList<E>>) current.getRight(), rankI, rank);
		}
		return null;
	}

	/**
	 * @author Clémentine
	 * @return the currentPutKey
	 */
	public int getCurrentPutKey()
	{
		return currentPutKey;
	}

	/**
	 * @author Clémentine
	 * @param currentPutKey
	 *            the currentPutKey to set
	 */
	public boolean setCurrentPutKey(int i)
	{
		if (i < numFields)
		{
			this.currentPutKey = i;
			return true;
		}
		return false;
	}

	/**
	 * @author Clémentine
	 * @return the currentGetKey
	 */
	public int getCurrentGetKey()
	{
		return currentGetKey;
	}

	/**
	 * @author Clémentine
	 * @param currentGetKey
	 *            the currentGetKey to set
	 */
	public boolean setCurrentGetKey(int i)
	{
		if (i < numFields)
		{
			this.currentGetKey = i;
			return true;
		}
		return false;

	}

	/**
	 * @author Clémentine
	 * @return the currentRemoveKey
	 */
	public int getCurrentRemoveKey()
	{
		return currentRemoveKey;
	}

	/**
	 * @author Clémentine
	 * @param currentRemoveKey
	 *            the currentRemoveKey to set
	 */
	public boolean setCurrentRemoveKey(int i)
	{
		if (i < numFields)
		{
			this.currentRemoveKey = i;
			return true;
		}
		return false;

	}

	/**
	 * Retourne la taille de l'arbre.
	 * 
	 * @meth.author Alaaedine Chatri
	 * 
	 * @return La taille de l'arbre.
	 */
	public int sizeOfTree()
	{
		return size;
	}

	/**
	 * Méthode qui vérifie si l'arbre est vide.
	 * 
	 * @meth.author Alaaedine Chatri
	 * 
	 * @return Un booléen true si l'arbre est vide, false sinon.
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * @author Clémentine Classe qui représentera un noeud de l'arbre. Cette
	 *         classe sait que le paramètre M est un ArrayList<Interger>
	 * 
	 */
	private class Node<M> extends BTNode<M>
	{

		/**
		 * Contructeur
		 */
		public Node(M element, Position<M> left, Position<M> right,
				Position<M> parent)
		{

			super(element, left, right, parent);
		}

		/**
		 * Retourne l'objet E stocké à l'index i du tableau des membres du node
		 * 
		 * @pre i est un index valide du tableau stocké dans le noeud
		 * @post
		 * @author Clem
		 */
		public E getValue(int i)
		{
			return ((ArrayList<E>) element()).get(i);

		}

		/**
		 * Remplace l'objet E stocké à l'index i du tableau des membres du node
		 * par l'objet new et renvoie l'objet précédent;
		 * 
		 * @pre i est un index valide du tableau stocké dans le noeud
		 * @post la référence de l'objet E à l'index i du noeud a été mis à
		 *       jour.
		 * @author clem
		 */
		public E setValue(int i, E value)
		{
			E old = ((ArrayList<E>) element()).get(i);
			((ArrayList<E>) element()).set(i, value);
			return old;
		}
	}
}
