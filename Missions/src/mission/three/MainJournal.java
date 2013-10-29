package mission.three;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainJournal
{
	HashMap<String, Journal> dictionary;
	static ArrayList<HashMap<String, Journal>> testDics;

	public MainJournal()
	{
		Journal.rankMap_init();
		dictionary = new HashMap<String, Journal>();
	}

	/**
	 * @author David Sarkozi, Clémentine Munyabarenzi
	 */
	public static void main(String[] args)
	{
		String inputFile = args[0];
		ArrayList<String> lines = null;
		try
		{
			lines = FileManager.readFile(inputFile);
		}
		catch (IOException e)
		{
			System.err
					.println("Une erreur est survenue à la lecture du fichier "
							+ inputFile);
			return;
		}
		MainJournal mr = new MainJournal();
		for (String line : lines)
		{
			mr.addJournal(line);
		}
		
	}// fin main

	/**
	 * Calcule le temps d'exécution moyen de la methode put sur un dictionnaire.
	 * 
	 * @pre d n'est pas null
	 * @post le temps d'exécution est renvoyé
	 * @exceptions -
	 * @param d
	 * @return le temps d'execution
	 */
	private static long putExecTime(HashMap<String, Journal> d, Journal dummy)
	{
		long startTime;
		long estimatedTime;
		startTime = System.nanoTime();
		// rajouter le dummy à la fin de chaque dico
		d.put(dummy.getTitle(), dummy);
		estimatedTime = System.nanoTime() - startTime;
		return estimatedTime;

	}

	/**
	 * Calcule le temps d'exécution moyen de la methode get sur un dictionnaire.
	 * 
	 * @pre : d n'est pas null
	 * @post : le temps d'exécution est renvoyé
	 * @exceptions : -
	 * @return le temps d'execution
	 */
	private static long getExecTime(HashMap<String, Journal> d)
	{
		// Récupérer le dummy inséré à la fin de chaque dictionnaire
		long startTime;
		long estimatedTime;
		startTime = System.nanoTime();
		d.get("SINF1121SINF1121SINF1121SINF1121SINF1121");
		estimatedTime = System.nanoTime() - startTime;
		return estimatedTime;
	}

	/**
	 * @pre -
	 * @param line
	 *            The line of the file representing a journal
	 */
	public void addJournal(String line)
	{
		String[] data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		Journal journal = new Journal(data);
		dictionary.put(journal.getTitle(), journal);
	}

}
