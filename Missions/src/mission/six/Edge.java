package mission.six;

import java.util.ArrayList;

/**
 * Une classe representant les arrets d'un graphe
 * @author Loic Lacomblez
 *
 * @param <E> type d'elment stocke dans les noueds connectes aux arretes
 * @param <F> type d'element stocke dans les arretes ('poids') 
 */
public class Edge<E,F extends Comparable<F>> implements Comparable<Edge<E,F>>
{
	private ArrayList<Vertex<E,F>> ends;
	private F element;
	
	/**
	 * Constructeur de la classe Edge
	 * @pre _
	 * @post une nouvelle arrete a ete cree, liant les noueds 'vertex1' et 'vertex2'.
	 * 		Cette arrete contient l'element elem.
	 */
	public Edge(Vertex<E,F> vertex1, Vertex<E,F> vertex2, F elem)
	{
		ends = new ArrayList<Vertex<E,F>>(2);
		ends.add(vertex1);
		ends.add(vertex2);
		element = elem;
	}
	
	/**
	 * retourne les noeuds aux extremites de l'arrete
	 * @pre _ 
	 * @post une liste des 2 noeuds contenus aux extremites de l'arrete est retournee
	 */
	public ArrayList<Vertex<E,F>> getEnds()
	{
		return ends;
	}

	/**
	 * retourne l'element stocke dans l'arrete
	 * @pre _
	 * @post l'element stocke par l'arrete est retourne
	 */
	public F getElement()
	{
		return element;
	}

	/**
	 * modifie l'element stocke a l'arret
	 * @pre _
	 * @post l'element stocke a l'arrete est modifie, et son ancien element
	 * 		est retourne
	 */
	public F setElement(F elem)
	{
		F prevElem = element;
		element = elem;
		return prevElem;
	}

	/**
	 * Retourne une representation textuelle de l'arrete
	 * @pre _
	 * @post un String est retourne : reprenant les deux noeuds connectes ainsi que l'element
	 * 		stocke dans cette arrete (format : "vertex1 \t vertex2 \t element")
	 */
	@Override
	public String toString()
	{
		return ends.get(0).toString() + '\t' + ends.get(1).toString() + '\t' + element.toString();
	}
	
	/**
	 * Comparateur de la classe Edge
	 * @pre _
	 * @post une valeur est retournee : <0 si 'this.elem' < 'edge.elem'
	 * 									=0 si 'this.elem' == 'edge.elem'
	 * 									>0 si 'this.elem' > 'edge.elem'
	 */
	@Override
	public int compareTo(Edge<E,F> edge)
	{
		return this.getElement().compareTo(edge.getElement());
	}
}
