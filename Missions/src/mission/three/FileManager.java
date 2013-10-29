package mission.three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager
{
	/**
	 * @author Henri Crombe
	 * @param filename
	 *            Le fichier a lire
	 * @pre Le fichier pointe par filename existe et est lisible
	 * @post Lit tout le contenu d'un fichier ligne par ligne et stocke chacune
	 *       de ces lignes dans une ArrayList
	 * @return Une ArrayList de String contenant les lignes du fichier passe en
	 *         parametre, null si le fichier n'existe pas et ecrit un message
	 *         d'erreur dans la console, ou lance une IOException si le fichier
	 *         existe, mais n'est pas lisible.
	 */

	public static ArrayList<String> readFile(String filename)
			throws IOException
	{

		File file = new File(filename);
		if (file.exists())
		{

			BufferedReader bufreader = new BufferedReader(new FileReader(file));
			ArrayList<String> content = new ArrayList<String>();
			String currentLine = null;
			while ((currentLine = bufreader.readLine()) != null)
			{
				content.add(currentLine);
			}
			bufreader.close();
			return content;
		}
		else System.err.println("Read failed : File doesn't exist");
		return null;
	}
	
	/**
	 * Lit un fichier et en extrait le nombre de lignes spécifiées en paramètre
	 * @pre : -
	 * @post :	une ArrayList de Strings contenant les n première lignes du fichier 
	 * 			a été renvoyée. 
	 * 			Si le fichier n'existait pas une erreur a été affichée
	 * 			dans la console.
	 * 			Si le fichier était vide une ArrayList vide a été renvoyée
	 * 			Si n > a la longieur du fichier, une ArrayList contenant toutes les
	 * 			lignes du fichier a été renvoyée.
	 * @exceptions : IOException
	 * @param filename : filename, le nom du fichier à lire
	 * 					 n, nombre de lignes à lire
	 * @return : une ArrayList des lignes lues
	 * @throws IOException
	 */
	public static ArrayList<String> readSmallerFile(String filename, int n)
			throws IOException
	{

		File file = new File(filename);
		if (file.exists())
		{
			int i = 0;
			BufferedReader bufreader = new BufferedReader(new FileReader(file));
			ArrayList<String> content = new ArrayList<String>();
			String currentLine = null;
			currentLine = bufreader.readLine();
			while ((i < n) && (currentLine != null))
			{			
				content.add(currentLine);
				i++;
				currentLine = bufreader.readLine();
			}
			bufreader.close();
			return content;
		}
		else System.err.println("Read failed : File doesn't exist");
		return null;
	}

}
