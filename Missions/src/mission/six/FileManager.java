package mission.six;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe de gestion des fichiers d'entree
 * 
 * @author David Sarkozi
 */

public class FileManager
{
	/**
	 * Represente le reader du fichier d'entree
	 */
	private BufferedReader reader;

	/**
	 * Represente le chemin vers le fichier d'entree
	 */
	private String filename;

	/**
	 * Constructor of the class.
	 * 
	 * @param filename
	 *            The path to the input file.
	 * @see #filename
	 */
	public FileManager(String filename)
	{
		this.filename = filename;
	}

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
	public String readLine() throws IOException
	{
		return reader.readLine();
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
	 *             Si un evenement inattendu est survenu pendant la lecture ou
	 *             la fermeture du fichier le cas echeant.
	 * @see #closeFile()
	 */
	public String readLine(boolean closeFile) throws IOException
	{
		String currentLine = readLine();
		if (closeFile) closeFile();
		return currentLine;
	}

	/**
	 * Opens the input file whose path is {@link #filename}.
	 * 
	 * @return this
	 * @pre {@link #filename} exists and is readable.
	 * @post {@link #reader} contains the opened file and is ready to be read.
	 * @throws FileNotFoundException
	 *             If the input file isn't found or isn't readable.
	 * @see #reader
	 */
	public FileManager openFile() throws FileNotFoundException
	{
		reader = new BufferedReader(new FileReader(filename));
		return this;
	}

	/**
	 * Rouvre le fichier d'entree pointe par {@link #filename}
	 * 
	 * @return this
	 * @pre -
	 * @post Rouvre le fichier d'entree en le fermant prealablement si
	 *       {@link #reader} n'est pas {@code null}
	 * @see #openFile()
	 * @see #closeFile()
	 */
	public FileManager reopenFile() throws FileNotFoundException, IOException
	{
		if (reader != null) closeFile();
		return openFile();
	}

	/**
	 * Ferme le flux du fichier d'entree.
	 * 
	 * @pre -
	 * @post Ferme le flux du fichier d'entree et met {@link #reader} a
	 *       {@code null}
	 * @throws IOException
	 *             If it failed to close the file.
	 */
	public void closeFile() throws IOException
	{
		reader.close();
		reader = null;
	}

	/**
	 * @param filename
	 *            Le chemin vers un fichier a lire
	 * @pre -
	 * @post Affecte {@code filename} a {@link #filename}
	 * @see #filename
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}
