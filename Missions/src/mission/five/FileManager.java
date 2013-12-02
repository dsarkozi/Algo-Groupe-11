package mission.five;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class managing input and output files.
 * 
 * @author David Sarkozi
 */

public class FileManager
{
	/**
	 * Represente le reader du fichier d'entree
	 */
	private static BufferedReader reader;

	/**
	 * Represente le chemin vers le fichier d'entree
	 */
	private static String filename;

	/**
	 * Lit une ligne du fichier d'entree. Est equivalent a
	 * {@code readLine(false)}
	 * 
	 * @pre Le reader est ouvert et pret a etre lu
	 * @post Retourne la ligne lue
	 * @return La ligne lue
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la lecture
	 * @see #readLine(boolean)
	 */
	public static String readLine()
	{
		try
		{
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.err.println("Error in reading the file.");
			System.exit(-1);
		}
		return null;
	}

	/**
	 * Lit une ligne du fichier d'entree et ferme {@link #reader} si specifie
	 * 
	 * @param closeFile
	 *            Si defini a {@code true}, {@link #reader} est ferme apres
	 *            lecture
	 * @pre {@link #reader} est ouvert et pret a etre lu
	 * @post Retourne la ligne lue et ferme {@link #reader} si closeFile est a
	 *       {@code true}
	 * @return La ligne lue
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la lecture
	 * @see #closeFile()
	 */
	public static String readLine(boolean closeFile)
	{
		String currentLine = readLine();
		if (closeFile) closeFile();
		return currentLine;
	}

	/**
	 * Ouvre le fichier d'entree dont le chemin est {@link #filename}
	 * 
	 * @pre {@link #filename} existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu
	 */
	public static void openFile()
	{
		try
		{
			reader = new BufferedReader(new FileReader(filename));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Read failed : File doesn't exist");
			System.exit(-1);
		}
	}

	/**
	 * Ouvre le fichier d'entree specifie par {@code filename}
	 * 
	 * @param filename
	 *            Le fichier a ouvrir
	 * @pre Le fichier pointe par {@code filename} existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu,
	 *       et {@link #filename} contient le nouveau fichier d'entree
	 * @see #openFile()
	 * @see #setFilename(String)
	 */
	public static void openFile(String filename)
	{
		setFilename(filename);
		openFile();
	}

	/**
	 * Rouvre le fichier d'entree pointe par {@link #filename}
	 * 
	 * @pre -
	 * @post Rouvre le fichier d'entree en le fermant prealablement si
	 *       {@link #reader} n'est pas {@code null}
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la fermeture
	 *             prealable du fichier d'entree
	 * @see #openFile()
	 * @see #closeFile()
	 */
	public static void reopenFile() throws IOException
	{
		if (reader != null) closeFile();
		openFile();
	}

	/**
	 * Ferme le flux du fichier d'entree.
	 * 
	 * @pre -
	 * @post Ferme le flux du fichier d'entree et met {@link #reader} a
	 *       {@code null}
	 */
	public static void closeFile()
	{
		try
		{
			reader.close();
		}
		catch (IOException e)
		{
			System.err.println("Error in reading the file.");
			System.exit(-1);
		}
		reader = null;
	}

	/**
	 * @param filename
	 *            Le chemin vers un fichier a lire
	 * @pre -
	 * @post Affecte {@code filename} a {@link #filename}
	 */
	public static void setFilename(String filename)
	{
		FileManager.filename = filename;
	}
}
