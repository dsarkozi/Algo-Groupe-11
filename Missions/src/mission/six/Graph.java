package mission.six;

/** Une classe permettant de representer un graphe non-dirige
 * @author Loic Lacomblez
 *
 * @param <E> Type d'objet dans le noeud (Vertex)
 * @param <F> Type d'objet dans les arretes (Edge)
 */
public class Graph<E,F>
{
	private ArrayList<Vertex<E>> vertex;
	private ArrayList<Edge<F>> edge;
	
	/**
	 * Constructeur de la classe Graph
	 * @pre _
	 * @post Un nouveau graphe vide a ete cree
	 */
	public Graph()
	{
		vertex = new ArrayList<Vertex<E>>();
		edge = new ArrayList<Edge<F>>();
	}
	
	/**
	 * Retourne la liste des noeuds contenus dans le graphe
	 * @pre _
	 * @post un ArrayList contenant la liste des noeuds du graphe est retournee
	 * 		Celle-ci peut potentiellement etre vide !  
	 */
	public ArrayList<Vertex<E>> getVertex()
	{
		return vertex;
	}
	
	/**
	 * Retourne la liste des arretes contenues dans le graphe
	 * @pre _
	 * @post un ArrayList contenant la liste des noeuds du graphe est retournee
	 * 		Celle-ci peut potentiellement etre vide !
	 */
	public ArrayList<Edge<F>> getEdge()
	{
		return edge;
	}
	
	/**
	 * Ajoute au graphe un noeud
	 * @pre le noeud n'est pas encore contenu dans le graphe
	 * @post un nouveau noeud contenant l'element elem a ete ajoute au graphe. Ce noeud
	 * 		est retourne
	 */
	public Vertex<E> addVertex(E elem)
	{
		Vertex<E> newVertex = new Vertex<E>(elem);
		vertex.add(newVertex);
		return newVertex;
	}
	
	/**
	 * Ajoute au graphe une arrete liant les 2 noeuds specifies
	 * @pre l'arrete n'existe pas encore
	 * @post les noueds 'vertex1' et 'vertex2' sont maintenant lies par une arrete stockant
	 * 		l'element 'elem'.
	 */
	public void connextVertex(Vertex<E> vertex1, Vertex<E> vertex2, F weight)
	{
		edge.add(new Edge<F>(vertex1, vertex2, weight));
	}
}
