package mission.five;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class taking care of file compression.
 * 
 * @author Henri Crombe, revised by David Sarkozi
 * @see Decompress
 */
public class Compress
{
	private String inputFile;

	private String outputFile;

	private HashMap<Character, Code> char_map;

	private HashMap<Character, Integer> occurences;

	private int text_length = 0;

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

	/**
	 * Compresses the zip.inputfile into zip.outputfile
	 * 
	 */
	public void compressFile()
	{
		occurences = analyzeFile();

		// Cr�ation de l'arbre de huffman au moyen du hashmap des occurences
		HuffmanCoder hc = new HuffmanCoder(occurences);
		// On transforme le HashMap<char,int> en HashMap<char,code> au moyen
		// d'un parcours de l'arbre de Huffman
		char_map = hc.generateCodes();

		/*
		 * Le format du fichier compresse : 1) Nombre de caracteres contenus
		 * dans le fichier d'entree 2) Nombre de caracteres codes ( nombre de
		 * couples caractere-occurence) 3) Alphabet de decryptage : char -
		 * occurences 4) Texte codes
		 */
		try
		{
			writeResult();
		}
		catch (IOException e)
		{
			System.err.println("Writable bit stream write error");
			FileManager.closeFile();
			System.exit(-1);
		}
	}

	private HashMap<Character, Integer> analyzeFile()
	{
		String line = FileManager.readLine();
		char[] charLine;
		HashMap<Character, Integer> occurences =
				new HashMap<Character, Integer>();
		while (line != null)
		{
			charLine = line.toCharArray();
			for (Character c : charLine)
			{
				// On v�rifie que le caract�re courrant n'existe pas deja dans
				// le
				// HashMap
				if (occurences.containsKey(c))
				{
					// On incremente l'occurence du caractere c
					occurences.put(c, occurences.get(c) + 1);
				}
				else
				{
					// Nouvelle occurence du caractere c
					occurences.put(c, 1);

				}
				text_length++;
			}
			line = FileManager.readLine();
		}
		return occurences;
	}

	private void writeResult() throws IOException
	{
		/*
		 * Transformation du texte en suite de boolean ( 1 bit = 1 bool )
		 */

		ArrayList<Boolean> text_encoded = new ArrayList<Boolean>();
		FileManager.reopenFile(FileManager.READING);
		String line = FileManager.readLine();
		char[] charLine;
		FileManager.obs.write(text_length);
		// FileManager.obs.write('\n');
		FileManager.obs.write(occurences.size());
		// FileManager.obs.write('\n');
		for (Character c : occurences.keySet())
		{
			// On imprime l'alphabet de decryptage
			FileManager.obs.write((char) c);
			FileManager.obs.write((int) occurences.get(c));
		}
		while (line != null)
		{
			charLine = line.toCharArray();
			for (Character c : charLine)
			{
				// On place la suite d'array
				text_encoded.addAll(char_map.get(c).getCode());
				for (Boolean item : text_encoded)
				{
					FileManager.obs.write(item);
				}
			}
			line = FileManager.readLine();
		}
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
		FileManager.openFile(FileManager.READING, zip.inputFile);
		FileManager.openFile(FileManager.WRITE_BITSTREAM, zip.outputFile);
		zip.compressFile();
		FileManager.closeFile();
	}

}
