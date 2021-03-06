package mission.four;

import java.util.ArrayList;
import java.util.Arrays;
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
	private static Tree<Journal> dictionary;
	private ArrayList<Journal> journals;
	private static int errors = 0;
	private static final String USAGE = "Syntaxe : <méthode à appeler> "
			+ "<champ sur lequel opérer> " + "<valeur du champ>";
	private static final String[] METHODS = { "get", "put", "remove" };
	private static String[] fields;
	private static final String EXIT = "exit";

	/**
	 * Maximum amount of fields. Set at execution time.
	 */
	private static int numFields;

	public Dictionary(String inputFile)
	{
		FileManager.openFile(inputFile);
		String line = null;
		line = FileManager.readLine();
		fields = line.split(",");
		numFields = fields.length;
		/*
		 * Init ArrayList with number of attributes of a Journal as initial
		 * capacity
		 */
		Journal.rankMap_init();
		Journal.fieldMaps_init(fields);
		journals = new ArrayList<Journal>(numFields);

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
		dictionary = new TreeBuilder(journals).build();
	}

	public static Journal line2Journal(String line)
	{
		/*
		 * Line split sur les virgules, en ignorant celles entre guillemets, qui
		 * peuvent se trouver dans les titres des revues
		 */
		String[] data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		if (data.length > numFields)
		{
			errors++;
			return null;
		}
		return new Journal(data);
	}

	/**
	 * Scanne l'entree cote utilisateur.
	 * 
	 * @pre -
	 * @post Appelle la routine d'execution analysant l'entree scannee et ferme
	 *       le flux de l'entree si l'utilisateur desire quitter le programme
	 * @see #execute(String)
	 */
	private void scanInput()
	{
		System.out.println("********************************************");
		System.out.println("*** Bibliothèque de revues scientifiques ***");
		System.out.println("********************************************");
		System.out.println();
		System.out.println(USAGE);
		System.out.println("Où la méthode se trouve parmi : "
				+ Arrays.toString(METHODS));
		System.out.println("Et où le champ se trouve parmi : "
				+ Arrays.toString(fields));
		System.out.println("Sinon, tapez " + EXIT + " pour quitter");
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
	private boolean execute(String cmd)
	{
		// Removing leading and trailing whitespaces
		cmd = cmd.trim();
		String[] cmdSplit = cmd.split(" ", 3);
		if (cmdSplit.length > 3 || cmdSplit.length < 1 || cmdSplit.length == 2)
		{
			System.out.println(USAGE);
			return false;
		}
		String meth;
		String field;
		String value;
		if (cmdSplit.length > 1)
		{
			// Separating the method to call from the rest
			meth = cmdSplit[0];
			field = cmdSplit[1];
			value = cmdSplit[2];
		}
		// quit
		else return true;
		
		Journal res = null;
		for (Journal j : journals)
		{
			if (j.getData(field).equals(value))
			{
				res = j;
				break;
			}
		}
		switch (meth)
		{
			case "get":
				// Call dictionary.get();
				break;
			case "put":
				if (res != null) dictionary.put(value, res);
				break;
			case "remove":
				if (res != null) dictionary.remove(res);
				break;
			default:
				// Error, no such method
				System.err.println("No such method.");
				System.out.println(USAGE);
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
		Dictionary dico = new Dictionary(args[0]);
		if (errors > 0) System.err.println("There are " + errors
				+ " invalid lines in the input file.");
		dico.scanInput();
	}

}
