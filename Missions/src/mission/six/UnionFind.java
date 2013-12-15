package mission.six;

import java.util.*;

/**
 * @author: Benoit Sluysmans
 * 
 * Union-find structure based on nodes
 */

public class UnionFind<E,F extends Comparable<F>> {
	public ArrayList<Node> rootNodes;
	public int nodeCount;
	public int setCount;
	
	/**
	 * Create n sets, each one with one node
	 * @param list = node's list of the graph
	 */
	public UnionFind(ArrayList<Vertex<E,F>> list) {
		nodeCount = 0;
		setCount = 0;
		this.rootNodes = new ArrayList<Node>(list.size());
		for (Vertex<E,F> v : list)
		      makeSet(v);
	}

	/**
	 * Returns the index of the set that n is currently in
	 * @pre n is in the graph
	 */
	public int find(Node n) {
		Node current = n;
		// Root's research
		while (current.parent != null)
			current = current.parent;
		
		Node root = current;

		// Set each node of the set's parent to the root
		// Increase the speed of next researches
		current = n;
		while (current != root) {
			Node temp = current.parent;
			current.parent = root;
			current = temp;
		}

		return root.index;
	}


	/**
	 * Unify the sets containing nodes i and j.
	 * @pre i and j are in the graph
	 */
	public void union(Node i, Node j) {
		int indexI = find(i);
		int indexJ = find(j);

		// Check if they are in the same set
		if (indexI == indexJ) return;

		// Root nodes of each set
		Node a = this.rootNodes.get(indexI);
		Node b = this.rootNodes.get(indexJ);

		// Attach the smaller set to the larger set
		if (a.rank < b.rank) {
			a.parent = b;
			b.rank++;
		} else {
			b.parent = a;
			a.rank++;
		}
		this.setCount--;
	}

	/**
	 * Creates a singleton set containing one node
	 * @pre n is in the graph
	 */
	public void makeSet(Vertex<E,F> v) {
		Node n = new Node(0, rootNodes.size(), null);
	    v.setNode(n);
		this.rootNodes.add(n);
		this.setCount++;
		this.nodeCount++;
	}
}
