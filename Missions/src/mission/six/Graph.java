package mission.six;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Une classe permettant de representer un graphe non-dirige
 * @author Benoit Sluysmans
 * @author Loic Lacomblez
 * 
 * 
 * @param <E>
 *            Type d'objet dans le noeud (Vertex)
 * @param <F>
 *            Type d'objet dans les arretes (Edge). Doit implementer comparable
 *            !
 */
public class Graph<E, F extends Comparable<F>>
{
	private ArrayList<Vertex<E, F>> vertex;
	private ArrayList<Edge<E, F>> edge;

	/**
	 * Constructeur de la classe Graph
	 * 
	 * @pre _
	 * @post Un nouveau graphe vide a ete cree
	 */
	public Graph()
	{
		vertex = new ArrayList<Vertex<E, F>>();
		edge = new ArrayList<Edge<E, F>>();
	}
	
	public Graph(ArrayList<Vertex<E, F>> v, ArrayList<Edge<E, F>> e)
	{
		vertex = v;
		edge = e;
	}

	/**
	 * Retourne la liste des noeuds contenus dans le graphe
	 * 
	 * @pre _
	 * @post un ArrayList contenant la liste des noeuds du graphe est retournee
	 *       Celle-ci peut potentiellement etre vide !
	 */
	public ArrayList<Vertex<E, F>> getVertex()
	{
		return vertex;
	}

	/**
	 * Retourne la liste des arretes contenues dans le graphe
	 * 
	 * @pre _
	 * @post un ArrayList contenant la liste des noeuds du graphe est retournee
	 *       Celle-ci peut potentiellement etre vide !
	 */
	public ArrayList<Edge<E, F>> getEdge()
	{
		return edge;
	}

	/**
	 * Ajoute au graphe un noeud
	 * 
	 * @meth.author Refined by David Sarkozi
	 * @pre -
	 * @post Check la presence d'un noeud dans le graphe, s'il n'est pas present
	 *       l'ajoute, et s'il l'est, retourne l'instance existante.
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
	 * Ajoute au graphe une arrete liant les 2 noeuds specifies
	 * 
	 * @pre l'arrete n'existe pas encore
	 * @post les noueds 'vertex1' et 'vertex2' sont maintenant lies par une
	 *       arrete stockant l'element 'elem'.
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
	 * Applique l'algo de Kruskal sur g
	 * 
	 * @pre 
	 * @post l'arbre sous-tendant de g de poids minimum
	 */
	public static Graph kruskal(Graph g)
	{
	    ArrayList<Vertex> vertices = g.getVertex();
	    ArrayList<Edge> edges = g.getEdge();
		
	    //UnionFind pour runner l'algo
		UnionFind u = new UnionFind(vertices);
		
		//edges et vertices du graphe retourne
		ArrayList<Edge> outEdges = new ArrayList<Edge>();
		ArrayList<Vertex> outVertices = new ArrayList<Vertex>();
		
		//KRUSKAL
		Collections.sort(edges);
		
	    for (Edge e : edges) {
	    	//Noeuds lies a cette arete
	    	Vertex a = (Vertex) e.getEnds().get(0);
	        Vertex b = (Vertex) e.getEnds().get(1);
	        
	        //Si ils ne sont pas dans le meme set, on lie leur set
	        if (u.find(a.getNode()) != u.find(b.getNode())) {
	        	
	        	//On ajoute l'arete et les noeuds au graphe retourne
	        	outEdges.add(e);
	        	if (!outVertices.contains(a))
	        		outVertices.add(a);
	        	if (!outVertices.contains(b))
	        		outVertices.add(b);
	        	
	        	//On lie leur set
	        	u.union(a.getNode(), b.getNode());
	        }
	    }
	    
	    return new Graph(outVertices,outEdges);
	}
}
