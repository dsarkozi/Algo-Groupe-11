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

	/**
	 * Ajoute un nouveau journal dans l'arbre, en respectant l'ordre selon le
	 * critere specifie par currentPutKey.
	 * 
	 * @meth.author Loic Lacomblez
	 * @pre une valeur valide est assignee a currentPutKey
	 * @post L'objet Journal elem a ete ajoute a l'arbre, trie selon l'atribut
	 *       designe par currentPutKey
	 */
	public void put(String value, E elem)
	{
		if (tableRoot == null)
		{
			ArrayList<WeakReference<E>> data =
					new ArrayList<WeakReference<E>>(numFields);
			data.add(currentPutKey, new WeakReference<E>((E) elem));
			tableRoot =
					new Node<ArrayList<WeakReference<E>>>(data, null, null,
							null);
		}
		else
		{
			putHelper(tableRoot, value, elem);
		}
	}

	/* Methode secondaire utilisee par put */
	private void putHelper(Node<ArrayList<WeakReference<E>>> currentNode,
			String value, E jr)
	{
		String currentString = value;

		/* S'il n'y a pas de journal dans currentNode a cet indice */
		if (currentNode.getValue(currentPutKey) == null
				|| currentNode.getValue(currentPutKey).get() == null)
		{
			/* Si le noeud n'a pas de fils, on insere l'element la */
			if (!currentNode.hasChildren())
			{
				currentNode.setValue(currentPutKey,
						new WeakReference<E>((E) jr));
			}
			/* Si le noeud n'a pas de fils gauche, on inspecte le fils droit */
			else if (!currentNode.hasLeft())
			{
				WeakReference<E> rightRef =
						((Node<ArrayList<WeakReference<E>>>) currentNode
								.getRight()).getValue(currentPutKey);
				if (rightRef != null
						&& rightRef.get() != null
						&& ((Journal) rightRef.get()).getData(currentPutKey)
								.compareTo(currentString) > 0)
				{
					currentNode.setValue(currentPutKey, new WeakReference<E>(
							(E) jr));
				}
				else
				{
					putHelper((Node<ArrayList<WeakReference<E>>>) currentNode
							.getRight(), value, jr);
				}
			}
			/* Si le noeud n'a pas de fils droit, on inspecte le noeud gauche */
			else if (!currentNode.hasRight())
			{
				WeakReference<E> leftRef =
						((Node<ArrayList<WeakReference<E>>>) currentNode
								.getLeft()).getValue(currentPutKey);
				if (leftRef != null
						&& leftRef.get() != null
						&& ((Journal) leftRef.get()).getData(currentPutKey)
								.compareTo(currentString) < 0)
				{
					currentNode.setValue(currentPutKey,
							new WeakReference<E>(jr));
				}
				else
				{
					putHelper((Node<ArrayList<WeakReference<E>>>) currentNode
							.getLeft(), value, jr);
				}
			}
			else
			{
				WeakReference<E> leftRef =
						((Node<ArrayList<WeakReference<E>>>) currentNode
								.getLeft()).getValue(currentPutKey);
				WeakReference<E> rightRef =
						((Node<ArrayList<WeakReference<E>>>) currentNode
								.getRight()).getValue(currentPutKey);

				/* Si jr < leftChild.getElem, on se propage dans leftChild */
				if (leftRef != null
						&& leftRef.get() != null
						&& ((Journal) leftRef.get()).getData(currentPutKey)
								.compareTo(currentString) > 0)
				{
					putHelper((Node<ArrayList<WeakReference<E>>>) currentNode
							.getLeft(), value, jr);
				}
				/* Si jr > rightChild.getElem, on se propage dans rightChild */
				else if (rightRef != null
						&& rightRef.get() != null
						&& ((Journal) rightRef.get()).getData(currentPutKey)
								.compareTo(currentString) < 0)
				{
					putHelper((Node<ArrayList<WeakReference<E>>>) currentNode
							.getRight(), value, jr);
				}
				/* Sinon, on peut placer le journal dans currentNode */
				else
				{
					currentNode.setValue(currentPutKey, new WeakReference<E>(
							(E) jr));
				}
			}
		}
		/*
		 * Sinon, il y a un journal dans currentNode.getValue(currentPutKey) et
		 * on effectue l'appel recursif
		 */
		else
		{
			/* appel recursif vers le fils gauche */
			if (((Journal) currentNode.getValue(currentPutKey).get()).getData(
					currentPutKey).compareTo(currentString) >= 0)
			{
				if (currentNode.hasLeft()) putHelper(
						(Node<ArrayList<WeakReference<E>>>) currentNode
								.getLeft(), value, jr);
				else
				{
					ArrayList<WeakReference<E>> data =
							new ArrayList<WeakReference<E>>(numFields);
					data.add(currentPutKey, new WeakReference<E>(jr));
					Node<ArrayList<WeakReference<E>>> newNode =
							new Node<ArrayList<WeakReference<E>>>(data, null,
									null, currentNode);
					currentNode.setLeft(newNode);
				}
			}
			/* appel recursif vers le fils droit */
			else
			{
				if (currentNode.hasRight()) putHelper(
						(Node<ArrayList<WeakReference<E>>>) currentNode
								.getRight(), value, jr);
				else
				{
					ArrayList<WeakReference<E>> data =
							new ArrayList<WeakReference<E>>(numFields);
					data.add(currentPutKey, new WeakReference<E>((E) jr));
					Node<ArrayList<WeakReference<E>>> newNode =
							new Node<ArrayList<WeakReference<E>>>(data, null,
									null, currentNode);
					currentNode.setRight(newNode);
				}
			}
		}
	}

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
	public LinkedList<WeakReference<E>> getAllValues(int i)
	{
		if (size == 0) return null;
		else
		{
			LinkedList<WeakReference<E>> all = new LinkedList<WeakReference<E>>();
			return getHelper(tableRoot, (LinkedList<WeakReference<E>>) all, i);

		}
	}

	private LinkedList<WeakReference<E>> getHelper(
			Node<ArrayList<WeakReference<E>>> tableRoot2,
			LinkedList<WeakReference<E>> all, int i)
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
	public LinkedList<WeakReference<E>> getAllKeys(int i)
	{
		if (size == 0) return null;
		else
		{
			LinkedList<WeakReference<E>> all = new LinkedList<WeakReference<E>>();
			return getHelper2(tableRoot, (LinkedList<WeakReference<E>>) all, i);

		}
	}

	private LinkedList<WeakReference<E>> getHelper2(
			Node<ArrayList<WeakReference<E>>> tableRoot2,
			LinkedList<WeakReference<E>> all, int i)
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
	 * @meth.author Clémentine
	 * @return the currentPutKey
	 */
	public int getCurrentPutKey()
	{
		return currentPutKey;
	}

	/**
	 * Met à jour l'index de champ de l'objet E qui sera concerné par le
	 * prochain put()
	 * 
	 * @return true si i était un index valide
	 * @meth.author Clémentine
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
	 * @meth.author Clémentine
	 * @return the currentGetKey
	 */
	public int getCurrentGetKey()
	{
		return currentGetKey;
	}

	/**
	 * Met à jour l'index de champ de l'objet E qui sera concerné par le
	 * prochain get()
	 * 
	 * @return true si i était un index valide
	 * @meth.author Clémentine
	 * @param i
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
	 * @meth.author Clémentine
	 * @return the currentRemoveKey
	 */
	public int getCurrentRemoveKey()
	{
		return currentRemoveKey;
	}

	/**
	 * Met à jour l'index de champ de l'objet E qui sera concerné par le
	 * prochain remove()
	 * 
	 * @return true si i était un index valide
	 * @meth.author Clémentine
	 * @param i
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
	 *         classe sait que le paramètre M est une
	 *         ArrayList<WeakReference<E>>
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
		 * @meth.author Clem
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
		 * @meth.author clem
		 */
		public WeakReference<E> setValue(int i, WeakReference<E> value)
		{
			WeakReference<E> old =
					((ArrayList<WeakReference<E>>) element()).get(i);
			((ArrayList<WeakReference<E>>) element()).set(i, value);
			return old;
		}
	}
}
