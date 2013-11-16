package mission.four;

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
	 * @param filename
	 *            Le nom du fichier a lire
	 * @pre Le fichier pointe par filename existe et est lisible
	 * @post Cree un objet FileManager avec un reader ouvert et affecte au
	 *       fichier d'entree en cours de lecture
	 * @see #openFile(String)
	 */
	public FileManager(String filename)
	{
		this.openFile(filename);
		this.filename = filename;
	}

	/**
	 * Lit une ligne du fichier d'entree. Est equivalent a
	 * {@link #readLine(false)}
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
		return this.reader.readLine();
	}

	/**
	 * Lit une ligne du fichier d'entree et ferme le reader si specifie
	 * 
	 * @param closeFile
	 *            Si defini a true, le reader est ferme apres lecture
	 * @pre Le reader est ouvert et pret a etre lu
	 * @post Retourne la ligne lue et ferme le reader si closeFile est a true
	 * @return La ligne lue
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la lecture
	 */
	public String readLine(boolean closeFile) throws IOException
	{
		String currentLine = this.reader.readLine();
		if (closeFile)
		{
			reader.close();
			reader = null;
		}
		return currentLine;
	}

	/**
	 * Ouvre le fichier d'entree dont le chemin de this est {@link #filename}
	 * 
	 * @pre {@link #filename} existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu
	 */
	public void openFile()
	{
		try
		{
			this.reader = new BufferedReader(new FileReader(filename));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Read failed : File doesn't exist");
			System.exit(-1);
		}
	}

	/**
	 * Ouvre le fichier d'entree specifie par filename
	 * 
	 * @param filename
	 *            Le fichier a ouvrir
	 * @pre Le fichier pointe par filename existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu,
	 *       et {@link #filename} contient le nouveau fichier d'entree
	 * @see #openFile()
	 * @see #setFilename(String)
	 */
	public void openFile(String filename)
	{
		this.setFilename(filename);
		this.openFile();
	}

	/**
	 * Rouvre le fichier d'entree pointe par {@link #filename}
	 * 
	 * @pre -
	 * @post Rouvre le fichier d'entree en le fermant prealablement si
	 *       {@link #reader} n'est pas null
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la fermeture
	 *             prealable du fichier d'entree
	 * @see #openFile()
	 */
	public void reopenFile() throws IOException
	{
		if (reader != null)
		{
			reader.close();
			reader = null;
		}
		this.openFile();
	}

	/**
	 * @param filename
	 *            Le chemin vers un fichier a lire
	 * @pre -
	 * @post Affecte filename a l'attribut de this
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}
