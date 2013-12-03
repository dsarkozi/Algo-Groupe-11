package mission.five;

/**
 * 
 * @author Benoit Sluysmans
 */
public class Tree implements Comparable<Tree>
{
	public int frequence;

	public Tree(int freq)
	{
		frequence = freq;
	}

	// Reimplementation de compareTo pour que la priorityQueue tienne compte de
	// cet ordre
	public int compareTo(Tree a)
	{
		return this.frequence - a.frequence;
	}

	/**
	 * Determine si this est un objet Leaf
	 * 
	 * @author Loic Lacomblez
	 */
	public boolean isLeaf()
	{
		return (this instanceof Leaf);
	}

	/**
	 * Determine si this possede un attribut 'gauche' != null
	 */
	public boolean hasLeft()
	{
		return (this instanceof Node) && ((Node) this).gauche != null;
	}

	/**
	 * Determine si this possede un attribut 'droite' != null
	 */
	public boolean hasRight()
	{
		return (this instanceof Node) && ((Node) this).droit != null;
	}
}
