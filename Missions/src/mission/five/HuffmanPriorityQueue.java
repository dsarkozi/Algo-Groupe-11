package mission.five;

import java.util.*;

/**
 * 
 * @author Benoit Sluysmans
 */
public class HuffmanPriorityQueue {

	/**
	 * 
	 * @pre frequences.length > 0 && frequences.length == lettres.length
	 * @post un arbre binaire representant l'arbre utilise dans l'algorithme de Huffman
	 */
	 public Tree buildTree(int[] frequences, char[] lettres) 
	 {
		    //Creation de la priorityQueue de Tree
	        PriorityQueue<Tree> tas = new PriorityQueue<Tree>();
	        for (int i = 0; i < frequences.length; i++)
	        {
	            if (frequences[i] > 0)
	            	tas.add(new Leaf(frequences[i], (char)i));
	        }
	        
	        //boucle jusqu'a ce qu'il ne reste qu'un seul Tree dans la queue
	        while (tas.size() > 1) 
	        {
	            Tree gauche = tas.poll();
	            Tree droit = tas.poll();
	            tas.add(new Node(gauche, droit));
	        }
	        
	        return tas.poll();
	    }
}
