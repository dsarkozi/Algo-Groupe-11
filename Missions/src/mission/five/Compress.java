package mission.five;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mission.five.HuffmanCoder;

import com.sun.org.apache.bcel.internal.classfile.Code;


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
	
	/**
	 * Huff_compress compresses the zip.inputfile into zip.outputfile
	 * @param zip
	 * 			zip contains references to the input/output file
	 * 
	 * Henri Crombé
	 * 
	 */
	public void Huff_compress(Compress zip){
		
		File in = new File(zip.inputFile);
		File out = new File(zip.outputFile);
		// On place tout le contenu du fichier dans un String, histoire de pouvori travailler char par char
		String text_tocompress = FileToString(in);
		char[] textc_tocompress = text_tocompress.toCharArray();
		
		HashMap<Character,Integer> occurences = new HashMap<Character,Integer>();
		
		for(Character c : textc_tocompress){
				// On vérifie que le caractère courrant n'existe pas deja dans le HashMap
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
		// Création de l'arbre de huffman au moyen du hashmap des occurences
		HuffmanCoder hc = new HuffmanCoder(occurences);
		// On transforme le HashMap<char,int> en HashMap<char,code> au moyen d'un parcours de l'arbre de Huffman
		HashMap<Character, mission.five.Code> char_map = hc.generateCodes();
		
		/* 
		 * Transformation du texte en suite de boolean ( 1 bit = 1 bool )
		 */
		
		ArrayList<Boolean> text_encoded = new ArrayList<Boolean>();
		
		for(Character c : textc_tocompress){
			// On place la suite d'array
			text_encoded.addAll(char_map.get(c).getCode());
		}
		
		/*
		 * Le format du fichier compressé :
		 * 1) Nombre de caractères contenus dans le fichier d'entrée
		 * 2) Nombre de caractères codés ( nombre de couples caractère-occurence)
		 * 3) Alphabet de décryptage : char - occurences
		 * 4) Texte codés
		 */
		
		
		
		OutputBitStream out_compressed = new OutputBitStream(zip.outputFile);
		out_compressed.write(textc_tocompress.length);
		out_compressed.write(occurences.size());
		/* pas sur de la syntaxe java pour cette partie */
		for(Character c  : occurences.keySet() ){
				// On imprime l'alphabet de décryptage
				out_compressed.write((char) c); 
				out_compressed.write((int) occurences.get(c));	
		}

		
		for(int i=0;i<textc_tocompress.length;i++){
			// Ajout de la suite de boolean ( texte codé ) dans le fichier compressé
			out_compressed.write((boolean) text_encoded.get(i));
		}
		
			
	}
	
	public String FileToString(File in){
		
		if (in.exists())
			try {
				
					
					BufferedReader bufreader = new BufferedReader(new FileReader(in));
					String text = "";
					String currentLine = null;
					while ((currentLine = bufreader.readLine()) != null)
					{
						text += currentLine;
					}
					bufreader.close();
					return text;
			}
			 catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
