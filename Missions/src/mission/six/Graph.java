package mission.six;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a non-directed graph.
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
public class Graph<E, F extends Comparable<F>>
{
	/**
	 * List of the nodes of a graph.
	 */
	private ArrayList<Vertex<E, F>> vertex;

	/**
	 * List of the connections of a graph.
	 */
	private ArrayList<Edge<E, F>> edge;

	/**
	 * Constructor of the class.
	 */
	public Graph()
	{
		vertex = new ArrayList<Vertex<E, F>>();
		edge = new ArrayList<Edge<E, F>>();
	}

	/**
	 * Constructor of the class.
	 * 
	 * @param v
	 *            A list of nodes.
	 * @param e
	 *            A list of connections.
	 */
	public Graph(ArrayList<Vertex<E, F>> v, ArrayList<Edge<E, F>> e)
	{
		vertex = v;
		edge = e;
	}

	/**
	 * Returns the list of the nodes of {@code this}.
	 * 
	 * @return {@link #vertex}.
	 * @pre -
	 * @post Returns {@link #vertex}.
	 */
	public ArrayList<Vertex<E, F>> getVertex()
	{
		return vertex;
	}

	/**
	 * Returns the list of the connections of {@code this}.
	 * 
	 * @return {@link #edge}.
	 * @pre -
	 * @post Returns {@link #edge}.
	 */
	public ArrayList<Edge<E, F>> getEdge()
	{
		return edge;
	}

	/**
	 * Adds a new {@link Vertex} to {@code this} with value {@code elem}.
	 * 
	 * @param elem
	 *            The value of the new {@link Vertex}.
	 * @return The new {@link Vertex} added or the existing one.
	 * @meth.author Refined by David Sarkozi.
	 * @pre -
	 * @post Checks if the new {@link Vertex} referenced by {@code elem} already
	 *       exists. If yes, then returns the existing one. Otherwise, adds it
	 *       to the list.
	 */
	public Vertex<E, F> addVertex(E elem)
	{
		Vertex<E, F> newVertex = new Vertex<E, F>(elem);
		ArrayList<Vertex<E, F>> vertexList = getVertex();
		int index = vertexList.indexOf(newVertex);
		if (index > -1) return vertexList.get(index);
		vertex.add(newVertex);
		return newVertex;
	}

	/**
	 * Adds a new weighted connection between two {@link Vertex}.
	 * 
	 * @param vertex1
	 *            One end of the new connection.
	 * @param vertex2
	 *            The other end of the new connection.
	 * @param weight
	 *            The weight of the connection.
	 * @pre The new connection doesn't exist yet.
	 * @post Adds a new connection to {@code this} with {@code weight} between
	 *       {@code vertex1} and {@code vertex2}.
	 */
	public void connectVertex(Vertex<E, F> vertex1, Vertex<E, F> vertex2,
			F weight)
	{
		/* creation de l'arrete */
		Edge<E, F> newEdge = new Edge<E, F>(vertex1, vertex2, weight);

		/* update des vertex */
		vertex1.addAdjacent(newEdge);
		vertex2.addAdjacent(newEdge);

		edge.add(newEdge);
	}

	/**
	 * Applies Kruskal's algorithm on {@code this}.
	 * 
	 * @return The resulting {@link Graph} from Kruskal's algorithm.
	 * @pre -
	 * @post Returns the underlying {@link Graph} of minimum weight.
	 */
	public Graph<E, F> kruskal()
	{
		ArrayList<Vertex<E, F>> vertices = this.getVertex();
		ArrayList<Edge<E, F>> edges = this.getEdge();

		// UnionFind pour runner l'algo
		UnionFind<E, F> u = new UnionFind<E, F>(vertices);

		// edges et vertices du graphe retourne
		ArrayList<Edge<E, F>> outEdges = new ArrayList<Edge<E, F>>();
		ArrayList<Vertex<E, F>> outVertices = new ArrayList<Vertex<E, F>>();

		// KRUSKAL
		Collections.sort(edges);

		for (Edge<E, F> e : edges)
		{
			// Noeuds lies a cette arete
			Vertex<E, F> a = e.getEnds().get(0);
			Vertex<E, F> b = e.getEnds().get(1);

			// Si ils ne sont pas dans le meme set, on lie leur set
			if (u.find(a.getNode()) != u.find(b.getNode()))
			{

				// On ajoute l'arete et les noeuds au graphe retourne
				outEdges.add(e);
				if (!outVertices.contains(a)) outVertices.add(a);
				if (!outVertices.contains(b)) outVertices.add(b);

				// On lie leur set
				u.union(a.getNode(), b.getNode());
			}
		}

		return new Graph<E, F>(outVertices, outEdges);
	}
}
