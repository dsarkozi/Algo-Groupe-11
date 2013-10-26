package mission.three;

import java.io.IOException;
import java.util.ArrayList;

public class MainRevue
{
	public MainRevue()
	{
		Revue.rankMap_init();
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
		MainRevue mr = new MainRevue();
		for (String line : lines)
		{
			mr.addRevue(line);
		}
	}

	/**
	 * @pre -
	 * @param line
	 *            The line of the file representing a journal
	 */
	public void addRevue(String line)
	{
		String[] data = line.split(",");
		Revue revue = new Revue(data);
		// TODO HashMap.put(revue.name, revue);
	}

}
