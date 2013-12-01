package mission.five;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Class taking care of file compression.
 * 
 * @author
 * @see Decompress
 */
public class Compress
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
	public Compress(String inputFile, String outputFile)
	{
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	
	public void huff_compress(Compress zip){
		
		File in = new File(zip.inputFile);
		File out = new File(zip.outputFile);
		// On place tout le contenu du fichier dans un String, histoire de pouvori travailler char par char
		String text_tocompress = FileToString(in);
		char[] textc_tocompress = text_tocompress.toCharArray();
		
		HashMap<Character,Integer> occurences = new HashMap<Character,Integer>();
		
		for(Character c : textc_tocompress){
				// On v�rifie que le caract�re courrant n'existe pas deja dans le HashMap
				boolean alreadyInHash = occurences.containsKey(c);
				if(alreadyInHash){
						// On incremente l'occurence du caractere c
						occurences.put(c, occurences.get(c) + 1);
				}
				else{	
						// Nouvelle occurence du caractere c
						occurences.put(c, 1);	
				}
			
		}
		
		HuffmanCoder hc = new HuffmanCoder(occurences);
		
		
	}
	
	public String FileToString(File in){
		
		if (in.exists())
		{

			BufferedReader bufreader = new BufferedReader(new FileReader(file));
			String text = "";
			String currentLine = null;
			while ((currentLine = bufreader.readLine()) != null)
			{
				text += currentLine;
			}
			bufreader.close();
			return text;
		}
		else System.out.println("Read failed : File doesn't exist");
		return null;
	}
		
		

	/**
	 * 
	 * @param args
	 *            Contains the arguments of the program (the input filename at
	 *            index 0, the output filename at index 1).
	 * @pre args contains valid data.
	 * @post Compresses the input file and returns the result in the output
	 *       file.
	 */
	public static void main(String[] args)
	{
		if (args.length < 1 || args.length > 2)
		{
			System.err.println("Invalid number of arguments.");
			return;
		}
		Compress zip = new Compress(args[0], args[1]);
		

		
	}
	
	
}
