package mission.five;

/**
 * 
 * @author Benoit Sluysmans
 */
class Node extends Tree
{
	public Tree gauche, droit;

	public Node(Tree g, Tree d)
	{
		super(g.frequence + d.frequence);
		gauche = g;
		droit = d;
	}
}