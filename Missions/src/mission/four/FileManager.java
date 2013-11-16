package mission.four;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe de gestion des fichiers d'entree
 * 
 * @author Henri Crombe
 */

public class FileManager
{
	/**
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
			String currentLine = bufreader.readLine();
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
}
