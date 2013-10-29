package mission.three;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainJournal
{
	HashMap<String, Journal> dictionary;
	public MainJournal()
	{
		Journal.rankMap_init();
	}

	/**
	 * @author David Sarkozi
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
