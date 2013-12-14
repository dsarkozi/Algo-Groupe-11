package mission.six;

/**
 * @author: Benoit Sluysmans
 * 
 * Classe cree pour utiliser le concept UnionFind
 */
public class Node {
	public int rank;      // Number of nodes in the same set
	public int index;     // Each node has its own index
	public Node parent;   // Root of the node's set, null if the node is its own parent

	public Node(int r, int i, Node p) {
		this.rank = r;
		this.index = i;
		this.parent = p;
	}
}
