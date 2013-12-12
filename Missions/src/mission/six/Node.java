package mission.six;

/*
 * Author: Benoit Sluysmans
 */
public class Node {
	int rank;      // Number of nodes in the same set
	int index;     // Each node has its own index
	Node parent;   // Root of the node's set, null if the node is its own parent

	public Node(int r, int i, Node p) {
		this.rank = r;
		this.index = i;
		this.parent = p;
	}
}
