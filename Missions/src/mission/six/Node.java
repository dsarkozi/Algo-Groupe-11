package mission.six;

/**
 * Class representing the nodes of the {@link UnionFind} structure.
 * 
 * @author Benoit Sluysmans
 * @see UnionFind
 */
public class Node
{
	/**
	 * Number of nodes in the same set.
	 */
	public int rank;

	/**
	 * Each node has its own index.
	 */
	public int index;

	/**
	 * Root of the node's set, {@code null} if the node is its own parent.
	 */
	public Node parent;

	/**
	 * Constructor of the class.
	 * 
	 * @param r
	 *            The rank.
	 * @param i
	 *            The index.
	 * @param p
	 *            The parent.
	 */
	public Node(int r, int i, Node p)
	{
		this.rank = r;
		this.index = i;
		this.parent = p;
	}
}
