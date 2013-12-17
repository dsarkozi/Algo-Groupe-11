package mission.six;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class representing a node of a {@link Graph}.
 * 
 * @author Benoit Sluysmans
 * @author Loic Lacomblez
 * 
 * @param <E>
 *            Type of element stored in each {@link Vertex}.
 * @param <F>
 *            Type of element stored in each {@link Edge} (weights), that has a
 *            natural ordering.
 */
public class Vertex<E, F extends Comparable<F>>
{
	/**
	 * The value stored by a node.
	 */
	private E element;

	/**
	 * List of all the links of a node.
	 */
	private ArrayList<Edge<E, F>> adjacent;

	/**
	 * The {@link Node} of a node, required by the {@link UnionFind} structure.
	 */
	private Node n;

	/**
	 * Constructor of the class.
	 * 
	 * @param elem
	 *            The value of {@code this} to set.
	 */
	public Vertex(E elem)
	{
		element = elem;
		adjacent = new ArrayList<Edge<E, F>>();
	}

	/**
	 * Sets a new {@link Node}.
	 * 
	 * @param n
	 *            The new {@link Node} to set.
	 * @pre -
	 * @post Sets {@link #n} to the new {@link Node}.
	 */
	public void setNode(Node n)
	{
		this.n = n;
	}

	/**
	 * Gets {@link #n}.
	 * 
	 * @pre -
	 * @post Returns {@link #n}.
	 * @return {@link #n}.
	 */
	public Node getNode()
	{
		return this.n;
	}

	/**
	 * Returns the value of {@code this}.
	 * 
	 * @return {@link #element}.
	 * @pre -
	 * @post Returns {@link #element}.
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * Sets {@link #element} to a new value {@code elem}.
	 * 
	 * @param elem
	 *            The new value to set.
	 * @pre -
	 * @post Sets {@link #element} to {@code elem}.
	 */
	public void setElement(E elem)
	{
		this.element = elem;
	}

	/**
	 * Returns the list of the links of {@code this}.
	 * 
	 * @return {@link #adjacent}.
	 * @pre -
	 * @post Returns {@link #adjacent}.
	 */
	public ArrayList<Edge<E, F>> getAdjacent()
	{
		return adjacent;
	}

	/**
	 * Adds a new link to {@link #adjacent}.
	 * 
	 * @param edge
	 *            The new link to add.
	 * @pre {@code this} doesn't have {@code edge} as a link yet.
	 * @post {@code edge} is added to {@link #adjacent}.
	 */
	public void addAdjacent(Edge<E, F> edge)
	{
		adjacent.add(edge);
	}

	/**
	 * Removes a link from the list {@link #adjacent} of {@code this}.
	 * 
	 * @param edge
	 *            The link to remove.
	 * @pre -
	 * @post Removes {@code edge} from {@link #adjacent}, or throws an exception
	 *       if an error occured.
	 * @throws NoSuchElementException
	 *             If {@code edge} isn't in {@link #adjacent}.
	 */
	public void removeAdjacent(Edge<E, F> edge) throws NoSuchElementException
	{
		if (!adjacent.remove(edge)) throw new NoSuchElementException();
	}

	/**
	 * Returns the textual representation of {@code this}.
	 * 
	 * @return The textual representation of {@link #element}.
	 * @pre -
	 * @post Returns the textual representation of {@code this} by returning the
	 *       textual representation of {@link #element}.
	 */
	@Override
	public String toString()
	{
		return element.toString();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @meth.author David Sarkozi
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Note that the comparison is done between {@link #element} of {@code this}
	 * .
	 * 
	 * @meth.author David Sarkozi
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vertex<?, ?> other = (Vertex<?, ?>) obj;
		if (element == null)
		{
			if (other.element != null) return false;
		}
		else if (!element.equals(other.element)) return false;
		return true;
	}
}
