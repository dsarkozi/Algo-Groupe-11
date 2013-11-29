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
 
    //Reimplementation de compareTo pour que la priorityQueue tienne compte de cet ordre
    public int compareTo(Tree a) 
    {
        return this.frequence - a.frequence;
    }
}
