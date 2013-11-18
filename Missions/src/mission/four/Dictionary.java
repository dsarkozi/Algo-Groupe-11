package mission.four;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe mere articulant les differents elements du programme : arbre, manager
 * de fichier, etc.
 * 
 * @author David Sarkozi
 * 
 */
public class Dictionary
{
	private Tree<Journal> dictionary;
	private static int errors = 0;

	/**
	 * Required amount of fields. Set at compilation time, can be edited by
	 * developer.
	 */
	private static final int REQUIRED = 2;

	/**
	 * Maximum amount of fields. Set at execution time.
	 */
	private static int max;

	public Dictionary(String inputFile)
	{
		FileManager.openFile(inputFile);
		String line = null;
		line = FileManager.readLine();
		max = line.split(",").length;
		/*
		 * Init ArrayList with number of attributes of a Journal as initial
		 * capacity
		 */
		ArrayList<Journal> journals = new ArrayList<Journal>(max);

		line = FileManager.readLine();
		Journal j = null;
		while ((line != null))
		{
			j = line2Journal(line);
			if (j != null) journals.add(line2Journal(line));
			line = FileManager.readLine();
		}
		FileManager.closeFile();
		// TODO Tree building
	}

	public static Journal line2Journal(String line)
	{
		/*
		 * Line split sur les virgules, en ignorant celles entre guillemets, qui
		 * peuvent se trouver dans les titres des revues
		 */
		String[] data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		if (data.length < REQUIRED || data.length > max)
		{
			errors++;
			return null;
		}
		Journal.JournalBuilder jbuilder = new Journal.JournalBuilder(data[0],
				data[1]);
		for (int i = REQUIRED; i < max; i++)
		{
			jbuilder.optData(data[i], i);
		}
		return jbuilder.build();
	}

	/**
	 * Scanne l'entree cote utilisateur.
	 * 
	 * @pre -
	 * @post Appelle la routine d'execution analysant l'entree scannee et ferme
	 *       le flux de l'entree si l'utilisateur desire quitter le programme
	 * @see #execute(String)
	 */
	private static void scanInput()
	{
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while (!exit)
		{
			exit = execute(in.nextLine());
		}
		in.close();
	}

	/**
	 * Routine d'execution selectionnant la methode a appeler a partir de la
	 * demande de l'utilisateur.
	 * 
	 * @pre {@code cmd} est une ligne tapee de l'utilisateur
	 * @post Appelle la methode adequate en fonction de la demande de
	 *       l'utilisateur. Retourne {@code true} s'il desire quitter le
	 *       programme, {@code false} sinon, et ecrit un message d'erreur si la
	 *       methode demandee n'existe pas
	 * @param cmd
	 *            La ligne lue par le scanner
	 * @return {@code true} si l'utilisateur desire quitter le programme,
	 *         {@code false} sinon
	 * @see #scanInput()
	 */
	private static boolean execute(String cmd)
	{
		// Removing leading and trailing whitespaces
		cmd = cmd.trim();
		// Separating the method to call from the rest
		String meth = (cmd.split(" ", 2))[0];
		switch (meth)
		{
			case "get":
				// Call Tree.get();
				break;
			case "remove":
				// Call Tree.remove();
				break;
			case "quit":
				// Quit the program
				return true;
			default:
				// Error, no such method
				System.err.println("No such method.");
				break;
		}
		return false;
	}

	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println("Invalid main arguments.");
			System.exit(-1);
		}
		new Dictionary(args[0]);
		if (errors > 0) System.err.println("There are " + errors
				+ " invalid lines in the input file.");
		scanInput();
	}

}
