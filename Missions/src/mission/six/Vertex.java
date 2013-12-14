package mission.six;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Classe representant un noeud d'un graphe
 * @author Loic Lacomblez
 *
 * @param <E> type d'element stocke par le noeud
 * @param <F> type d'elemnt stocke par les arretes liant les noeuds
 */
public class Vertex<E,F>
{
	private E element;
	private ArrayList<Edge<E,F>> adjacent;
	
	/**
	 * Constructeur de la classe Vertex 
	 * @pre _
	 * @post Un nouveau noeud contenant l'element 'elem' a ete cree. Il
	 * 		possede une liste d'arretes adjacentes nulles
	 */
	public Vertex(E elem)
	{
		element = elem;
		adjacent = new ArrayList<Edge<E,F>>();
	}
	
	/**
	 * Retourne l'element contenu dans le noeud
	 * @pre _
	 * @post l'element contenu dans le noeud est retourne
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * Modifie l'element contenu dans le noeud
	 * @pre _
	 * @post this contient maintenant l'element 'elem'
	 */
	public void setElement(E elem)
	{
		this.element = elem;
	}

	/**
	 * Retourne la liste des arretes adjacentes au noeud
	 * @pre _
	 * @post une liste contenant les arretes adajacentes au noeud est retournee.
	 * 		Cette liste peut etre vide !
	 */
	public ArrayList<Edge<E,F>> getAdjacent()
	{
		return adjacent;
	}
	
	/**
	 * Ajoute l'arrete 'edge' a la liste des arretes adjacentes du noeud
	 * @pre edge n'est pas encore lie au noued 'this'
	 * @post 'edge' a ete rajoutee a la liste des arretes adjacentes au noeud
	 */
	public void addAdjacent(Edge<E,F> edge)
	{
		adjacent.add(edge);
	}
	
	/**
	 * Retire l'arrete 'edge' de la liste des arrets adjacentes du noeud
	 * @pre _
	 * @post edge a ete retire du noeud 'this'. Si edge n'etait pas liee
	 * 		au noued 'this', 'this' est inchange et une exception est lancee
	 */
	public void removeAdjacent(Edge<E,F> edge) throws NoSuchElementException
	{
		if(!adjacent.remove(edge))
			throw new NoSuchElementException();
	}
	
	/**
	 * Retourne la representation textuelle du noeud
	 * @pre_
	 * @post un String representant l'element stocke dans le noeud est retourne 
	 */
	public String toString()
	{
		return element.toString();
	}
	

}
