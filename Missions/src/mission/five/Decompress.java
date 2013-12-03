package mission.five;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class taking care of file decompression.
 * 
 * @author Henri Crombe, revised by David Sarkozi
 * @see Compress
 */
public class Decompress
{
	private String inputFile;
	private String outputFile;

	HashMap<Character, Integer> occurences;

	private int char_amount;

	private int char_coded_amount;

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
		occurences = new HashMap<Character, Integer>();
	}

	/**
	 * 
	 * @param unzip
	 * @return Texte final decompress�
	 * @throws IOException
	 * 
	 *             Henri Cromb�
	 */
	public void decompressFile()
	{
		try
		{
			processHeader();
			writeResult(new HuffmanCoder(occurences));
		}
		catch (IOException e)
		{
			System.err.println("Readable bit stream read error");
			System.exit(-1);
		}
		finally
		{
			FileManager.closeFile();
		}
	}

	private void processHeader() throws IOException
	{
		char_amount = FileManager.ibs.readInt();
		char_coded_amount = FileManager.ibs.readInt();
		for (int i = 0; i < char_coded_amount; i++)
		{
			char c = FileManager.ibs.readChar();
			int x = FileManager.ibs.readInt();
			occurences.put(c, x);
		}
	}

	private void writeResult(HuffmanCoder hc) throws IOException
	{
		// On va utiliser un Code temporaire pour la lecture binaire.
		ArrayList<Boolean> temp_code = new ArrayList<Boolean>(); // code
																	// temporaire,
																	// permet la
																	// recherche
		// dans l'arbre de huffman

		int text_final = 0;

		boolean new_code = true;

		boolean temp;

		for (int i = 0; i < char_amount; i++)
		{
			if (new_code)
			{
				temp_code.clear();
				new_code = false;
			}
			temp = FileManager.ibs.readBoolean();
			temp_code.add(temp);
			ArrayList<Boolean> temp_temp_code =
					new ArrayList<Boolean>(temp_code);

			// est ce que le code courant existe dans l'arbre de huffman ?
			// Est-il un Code concluant ?
			boolean test = HuffmanCoder.checkCode(temp_temp_code, hc.getTree());
			if (test)
			{
				// Le code courant correspond � un caract�re dans l'arbre de
				// huffman. On ajoute le caractere au texte final.
				FileManager.writer.print(HuffmanCoder.getCharacter(temp_code,
						hc.getTree()));
				text_final++;
				new_code = true;
			}
			// Si le code courant n'existe pas dans l'arbre de huffman, on
			// ajoute un bit au code et on recommence
		}
		if (text_final != char_amount)
		{
			System.out
					.println("Le fichier decompresse ne contient pas le meme nombre de caracteres que le fichier d'entree : ERREUR");
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
		FileManager.openFile(FileManager.READ_BITSTREAM, unzip.inputFile);
		FileManager.openFile(FileManager.WRITING, unzip.outputFile);
		unzip.decompressFile();
	}
}
