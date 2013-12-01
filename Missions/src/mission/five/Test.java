package mission.five;

import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author Benoit Sluysmans
 *
 */
public class Test {
	
	public static void main (String[] args)
	{
		HashMap<Character,Integer> occurences = new HashMap<Character,Integer>();
		char[] tab = {'a','b','c','d','e','f','g','h','i','j'};
		int[] numb = {1000, 450, 300, 200, 150, 100, 85, 76, 56, 11}; 
		for(int i=0; i < tab.length; i++)
		{
			occurences.put(tab[i], numb[i]);
		}
		
		HuffmanCoder HcodeTree = new HuffmanCoder(occurences);
		Tree codeTree = HcodeTree.getTree();
		
		// Inspection sur l'arbre
        System.out.println("Lettre\tfreq\tcode");
        printHuffman(codeTree, new StringBuffer());
        
        // Inspection sur Generate_Codes
        HashMap<Character,Code> res = HcodeTree.generateCodes();
		Set<Character> set = res.keySet();
		for(char letter : set)
		{
			System.out.println("char : " + letter + " - code : " + res.get(letter).toString());
		}
    }
	
	
	public static void printHuffman(Tree tree, StringBuffer prefix) {
        if (tree instanceof Leaf) {
            Leaf leaf = (Leaf)tree;
            System.out.println(leaf.lettre + "\t" + leaf.frequence + "\t" + prefix);
 
        } 
        else if (tree instanceof Node) {
            Node node = (Node)tree;
 
            // a gauche
            prefix.append('0');
            printHuffman(node.gauche, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // a droite
            prefix.append('1');
            printHuffman(node.droit, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
}
