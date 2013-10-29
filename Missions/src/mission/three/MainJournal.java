package mission.three;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainJournal
{
	/**
	 * @author Loic Lacomblez, Alaedine Chatri
	 */
	HashMap<String, Journal> dictionary;

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
	 * @author Nicolas Marchand
	 * @pre -
	 * @param line
	 *            The line of the file representing a journal
	 * @post Adds the Journal represented by line to the dictionary
	 */
	public void addJournal(String line)
	{
		String[] data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		Journal journal = new Journal(data);
		dictionary.put(journal.getTitle(), journal);
	}

}
