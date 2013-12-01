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
	
	public Tree getTree(){
		return codeTree;
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
		Set<Character> chars = occurences.keySet();

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
	
	/**
	 * Retourne les codes lies a chaque caractere de l'arbre codeTree
	 * @author Loic Lacomblez
	 */
	public HashMap<Character,Code> generateCodes()
	{
		HashMap<Character,Code> result = new HashMap<Character,Code>();
		codeTraversal(result, new ArrayList<Boolean>(), codeTree);
		return result;
	}
	
	/**
	 * Fonction recursive permettant de recuperer les codes lies a
	 * chaque caractere de l'arbre
	 * @author Loic Lacomblez
	 */
	
	private void codeTraversal(HashMap<Character,Code> result, ArrayList<Boolean> prevCode, Tree t)
	{
		// arrivee a un caractere final
		if(t.isLeaf())
		{
			/* Le clone evite de corrompre les codes des anciens caracteres a cause du passage
			 * par reference de java.
			 */
			Code finalCode = new Code((ArrayList<Boolean>) prevCode.clone());
			result.put(((Leaf) t).lettre, finalCode);
		}
		// appel recursif a gauche
		if(t.hasLeft())
		{
			prevCode.add(false);
			codeTraversal(result, prevCode, ((Node) t).gauche);
			prevCode.remove(prevCode.size() - 1);
		}
		// appel recursif a droite
		if(t.hasRight())
		{
			prevCode.add(true);
			codeTraversal(result, prevCode, ((Node) t).droit);
			prevCode.remove(prevCode.size() -1);
		}
	}
	
	/**
	 *
	 * Fonction permettant de savoir si CodeToCheck correspond à un caractère ( noued externe ), ou simplement un code intermediaire 
	 * Henri Crombé
	 */
	
	private boolean checkCode(ArrayList<Boolean> code, Tree t){
		
		int code_size = code.size();
		if(code_size==0 ){
			if(t.isLeaf()) return true;
			else return false;
		}
		
		if(t.isLeaf()){
				if(code_size == 0){
					return true;
				}
				else{
					return false;
				}
		}
		
		if(code.get(0) == true){ // aller a droite dans l'arbre
			if(t.hasRight()){
				code.remove(0);
				checkCode(code, ((Node)t).droit);	
			}
			else{
				return false;
			}
		}
		else{
			if(t.hasLeft()){
				code.remove(0);
				checkCode(code, ((Node)t).gauche);	
			}
		}
		return false;
		
	}
	
}
