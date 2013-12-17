package mission.six;

import java.util.ArrayList;

/**
 * Class representing the connections between {@link Vertex} of a {@link Graph}.
 * These connections have a natural ordering according to their weight.
 * 
 * @author Loic Lacomblez
 * 
 * @param <E>
 *            Type of element stored in each {@link Vertex}.
 * @param <F>
 *            Type of element stored in each {@link Edge} (weights), that has a
 *            natural ordering.
 */
public class Edge<E, F extends Comparable<F>> implements Comparable<Edge<E, F>>
{
	/**
	 * Represents the two {@link Vertex} of a connection.
	 */
	private ArrayList<Vertex<E, F>> ends;

	/**
	 * Represents the weight of the connection, that can be naturally ordered.
	 */
	private F element;

	/**
	 * Constructor of the class.
	 * 
	 * @param vertex1
	 *            One end of the new connection.
	 * @param vertex2
	 *            The other end of the new connection.
	 * @param elem
	 *            The weight of the connection.
	 */
	public Edge(Vertex<E, F> vertex1, Vertex<E, F> vertex2, F elem)
	{
		ends = new ArrayList<Vertex<E, F>>(2);
		ends.add(vertex1);
		ends.add(vertex2);
		element = elem;
	}

	/**
	 * Returns the two {@link Vertex} of {@code this}, represented by
	 * {@link #ends}.
	 * 
	 * @return {@link #ends}.
	 * @pre -
	 * @post Returns {@link #ends}.
	 */
	public ArrayList<Vertex<E, F>> getEnds()
	{
		return ends;
	}

	/**
	 * Returns the weight of {@code this}.
	 * 
	 * @return {@link #element}.
	 * @pre -
	 * @post Returns {@link #element}.
	 */
	public F getElement()
	{
		return element;
	}

	/**
	 * Edits the weight of {@code this}.
	 * 
	 * @param element
	 *            The new weight to set.
	 * @pre -
	 * @post Sets {@link #element} to a new value.
	 */
	public void setElement(F element)
	{
		this.element = element;
	}

	/**
	 * Returns a textual representation of {@code this}.
	 * 
	 * @return A textual representation of {@code this}.
	 * @pre -
	 * @post Returns a textual representation of {@code this} as follows :
	 *       {@code vertex1.toString() + '\t' + vertex2.toString() + '\t' +
	 *       {@link #element}.toString()} .
	 */
	@Override
	public String toString()
	{
		return ends.get(0).toString() + '\t' + ends.get(1).toString() + '\t'
				+ element.toString();
	}

	/**
	 * Comparator of the class.
	 * 
	 * @param edge
	 *            A connection to compare with {@code this}.
	 * @pre -
	 * @post Compares two connections according to the natural ordering of their
	 *       {@link #element}.
	 */
	@Override
	public int compareTo(Edge<E, F> edge)
	{
		return this.getElement().compareTo(edge.getElement());
	}
}
