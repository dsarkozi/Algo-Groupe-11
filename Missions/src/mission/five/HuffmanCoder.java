package mission.five;

import java.util.*;

/**
 * Classe englobant les structures et l'algorithme utilises par le codage de Huffman
 * @author Benoit Sluysmans & Loic Lacomblez
 */
public class HuffmanCoder
{
	private Tree codeTree;

	/**
	 * Constructeur de la classe HuffmanCoder
	 * @author Loic Lacomblez
	 * @pre occurences contient une collection valide de couples caracteres--entier>0
	 * @post _
	 */
	public HuffmanCoder(HashMap<Character,Integer> occurences)
	{
		codeTree = buildTree(occurences);
	}
	
	/**
	 * Retourne les codes lies a chaque caractere de l'arbre codeTree
	 * @author Loic Lacomblez
	 */
	public HashMap<Character, Code> generateCodes()
	{
		// TODO
	}

	/**
	 * 
	 * @pre _
	 * @post un arbre binaire representant l'arbre utilise dans l'algorithme de Huffman
	 */
	private Tree buildTree(HashMap<Character,Integer> occurences) 
	{
		//Creation de la priorityQueue de Tree
		PriorityQueue<Tree> tas = new PriorityQueue<Tree>();
		//Recuperation des occurences des caracteres
		Set<Character>chars = occurences.keySet();

		for(char letter:chars)
		{
			tas.add(new Leaf(occurences.get(letter), letter));
		}

		// boucle jusqu'a ce qu'il ne reste qu'un seul Tree dans la queue
		while (tas.size() > 1) 
		{
			Tree gauche = tas.poll();
			Tree droit = tas.poll();
			tas.add(new Node(gauche, droit));
		}

		return tas.poll();
	}
}
