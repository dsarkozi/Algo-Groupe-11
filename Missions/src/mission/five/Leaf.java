package mission.five;

/**
 * 
 * @author Benoit Sluysmans
 */
class Leaf extends Tree
{
	public char lettre;

	public Leaf(int freq, char l)
	{
		super(freq);
		lettre = l;
	}
}