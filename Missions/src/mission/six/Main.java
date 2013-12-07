package mission.six;

import java.io.FileNotFoundException;

/**
 * Main class, where the input file is processed and where the output is
 * printed.
 * 
 * @author David Sarkozi
 * 
 */
public class Main
{
	
	/**
	 * @param args
	 *            The input of the program.
	 * @pre -
	 * @post Processes the input and prints the output to {@code System.out}, or
	 *       an error message if something went bad.
	 */
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println("Invalid number of args.");
		}
		FileManager inputFile = new FileManager(args[0]);
		try
		{
			inputFile.openFile();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error loading the file.");
			System.exit(-1);
		}
	}

}
