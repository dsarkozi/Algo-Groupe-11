package mission.four;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;




public class Tree<E>
{
	protected ArrayList<E> dataRefs;
	protected Node<ArrayList<WeakReference<E>>> tableRoot;
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

	public void remove(E elem)
	{
		dataRefs.remove(elem);
	}

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

	private LinkedList<E> getHelper(
			Node<ArrayList<WeakReference<E>>> tableRoot2, LinkedList<E> all,
			int i)
	{
		if (tableRoot2.getLeft() == null && tableRoot2.getRight() == null) all
				.add(tableRoot2.getValue(i));
		else
		{
			if (tableRoot2.getLeft() != null) getHelper(
					(Node<ArrayList<WeakReference<E>>>) tableRoot2.getLeft(),
					all, i);

			all.add(tableRoot2.getValue(i));

			if (tableRoot2.getRight() != null) getHelper(
					(Node<ArrayList<WeakReference<E>>>) tableRoot2.getRight(),
					all, i);
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

	private LinkedList<E> getHelper2(
			Node<ArrayList<WeakReference<E>>> tableRoot2, LinkedList<E> all,
			int i)
	{
		if (tableRoot2.getLeft() == null && tableRoot2.getRight() == null) all
				.add(tableRoot2.getValue(i));
		else
		{
			if (tableRoot2.getLeft() != null) getHelper(
					(Node<ArrayList<WeakReference<E>>>) tableRoot2.getLeft(),
					all, i);

			all.add(tableRoot2.getValue(i));

			if (tableRoot2.getRight() != null) getHelper(
					(Node<ArrayList<WeakReference<E>>>) tableRoot2.getRight(),
					all, i);
		}
		return all;
	}
	
	/**
	 * Henri Crombe
	 * getAllKeysWithRank/getHelper3 permet de r�cup�rer les noms des r�vues (dans l'ordre alphabetique) qui ont le rang �quivalent � String rank
	 * @PRE: String rank est un rang compris entre A* et C
	 * @POST: Retourne une LinkedList contenant les noms des revues qui ont le rang �quivalent � String rank
	 */
	public LinkedList<E> getAllKeysWithRank (String rank){
		
		
		if(size == 0 ) 
			return null;
		else{
			LinkedList<E> rankI =  new LinkedList<E>();
			return getHelper3(tableRoot,rankI,rank);
		}
	}
	public LinkedList<E> getHelper3(Node<ArrayList<WeakReference<E>>> tableRoot2,
			LinkedList<E> rankI, String rank) 
	{
		if (tableRoot2.getLeft() == null && tableRoot2.getRight() == null){
			if(tableRoot2.getValue(0) == rank) rankI.add(tableRoot2.getValue(1)); // Si le noeud courrant a le rang qui nous interresse, on place le nom de la revue dans la linkedlist
			
		}
		else
		{
			if (tableRoot2.getLeft() != null) getHelper3(
					(Node<ArrayList<WeakReference<E>>>) tableRoot2.getLeft(), rankI, rank);

			rankI.add(tableRoot2.getValue(1));

			if (tableRoot2.getRight() != null) getHelper3(
					(Node<ArrayList<WeakReference<E>>>) tableRoot2.getRight(), rankI, rank);
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
	 * Met à jour l'index de champ de l'objet E qui sera concerné
	 * par le prochain put()
	 * @return true si i était un index valide
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
	 * Met à jour l'index de champ de l'objet E qui sera concerné
	 * par le prochain get()
	 * @return true si i était un index valide
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
	 * Met à jour l'index de champ de l'objet E qui sera concerné
	 * par le prochain remove()
	 * @return true si i était un index valide
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
	public int size()
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
		public WeakReference<E> getValue(int i)
		{
			return ((ArrayList<WeakReference<E>>) element()).get(i);

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
		public WeakReference<E> setValue(int i, WeakReference<E> value)
		{
			WeakReference<E> old = ((ArrayList<WeakReference<E>>) element()).get(i);
			((ArrayList<WeakReference<E>>) element()).set(i, value);
			return old;
		}
	}
}
