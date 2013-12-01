package mission.five;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		boolean[] text_coded = new boolean[n_total_char]; // brace yourself, array outofbound excecption coming !
		/*
		for(int i; i < n_total_char ; i++){
				
			
		}
		*/
		
		// On va utiliser un Code temporaire pour la lecture binaire. 
		
		ArrayList<Boolean> temp_code;
		boolean new_code = true;
		while(/* condition a d�terminer ? in.hasSomethingtoRead*/){
		   
			if(new_code == true){
				temp_code  = new ArrayList<Boolean>();
				new_code = false;
			}
			
			boolean temp = in.readBoolean();
			temp_code.add(temp);
			
			
			
		   
		  
		   
		   
		   
		   
		   
	   }
		
		
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
