package mission.five;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.generic.CHECKCAST;

/**
 * Class taking care of file decompression.
 * 
 * @author
 * @see Compress
 */
public class Decompress
{
	private String inputFile;
	private String outputFile;

	/**
	 * Constructor of the class.
	 * 
	 * @param inputFile
	 *            The input file to compress.
	 * @param outputFile
	 *            The output file that will contain the compressed input file.
	 */
	public Decompress(String inputFile, String outputFile)
	{
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	public String huff_decompress(Decompress unzip) throws IOException{
		
		
		
		// Lecture du fichier compresse
		
		InputBitStream in = new InputBitStream(unzip.inputFile);
		
		HashMap<Character,Integer> occurences = new HashMap<Character,Integer>();
		/*
		 * Le format du fichier compress� :
		 * 1) Nombre de caract�res contenus dans le fichier d'entr�e 
		 * 2) Nombre de caract�res cod�s ( nombre de couples caract�re-occurence)
		 * 3) Alphabet de d�cryptage : char - occurences
		 * 4) Texte cod�s
		 */
		
		int n_total_char = in.readInt(); // 1
		int n_coded_char = in.readInt(); // 2
		
		// remplissage des occurences.   // 3 
		
		for(int i; i < n_coded_char ; i++){
			char c = in.readChar();
			int x = in.readInt();
			occurences.put(c,x);
		}
		// build huffman tree pour recuperer les caract�re li�s aux codes
		
		HuffmanCoder hc = new HuffmanCoder(occurences);
		
		// Lecture du texte cod� (4)
		
		// On va utiliser un Code temporaire pour la lecture binaire. 
		ArrayList<Boolean> temp_code; // code temporaire, permet la recherche dans l'arbre de huffman
		
		String text_final = "";
		
		boolean new_code = true;
		while(/* condition a d�terminer ? in.hasSomethingtoRead*/){
		   
			if(new_code == true){
				temp_code  = new ArrayList<Boolean>();
				new_code = false;
			}
			
			boolean temp = in.readBoolean();
			temp_code.add(temp);
			// est ce que le code courant existe dans l'arbre de huffman ? Est-il un Code concluant ?
			boolean test = HuffmanCoder.checkCode(temp_code, hc.getTree());
		
			if(test == true ){
				// Le code courant correspond � un caract�re dans l'arbre de huffman. On ajoute le caractere au texte final.
				char cur_char = HuffmanCoder.getCharacter(temp_code, hc.getTree());
				text_final += cur_char;
				new_code = true;
			}
		
			else{
				// Si le code courant n'existe pas dans l'arbre de huffman, on ajoute un bit au code et on recommence
				temp_code.add(in.readBoolean());
			}
		     
		}
		
		if(text_final.length() != n_total_char){
			
			System.out.println("Le fichier d�compress� ne contient pas le m�me nombre de caract�res que le fichier d'entr�e : ERREUR \n");
			return text_final;
		}
		return text_final;
		
	
	}
	/**
	 * 
	 * @param args
	 *            Contains the arguments of the program (the input filename at
	 *            index 0, the output filename at index 1).
	 * @pre args contains valid data.
	 * @post Decompresses the input file and returns the result in the output
	 *       file.
	 */
	public static void main(String[] args)
	{
		if (args.length < 1 || args.length > 2)
		{
			System.err.println("Invalid number of arguments.");
			return;
		}
		Decompress unzip = new Decompress(args[0], args[1]);
	}
}
