package mission.five;

import java.util.HashMap;

/**
 * 
 * @author Benoit Sluysmans
 *
 */
public class Test {
	
	public static void main (String[] args)
	{
		HashMap<Character,Integer> occurences = new HashMap<Character,Integer>();
		occurences.put('a',400);
		occurences.put('b',300);
		occurences.put('c',200);
		occurences.put('d',100);
		
		HuffmanCoder HcodeTree = new HuffmanCoder(occurences);
		Tree codeTree = HcodeTree.getTree();

        System.out.println("Lettre\tfreq\tcode");
        printHuffman(codeTree, new StringBuffer());
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
