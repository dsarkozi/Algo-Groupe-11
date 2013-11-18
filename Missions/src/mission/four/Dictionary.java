package mission.four;

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

	public Dictionary()
	{
	}

	/**
	 * Scanne l'entree cote utilisateur.
	 * 
	 * @pre -
	 * @post Appelle la methode appropriee en fonction de la demande de
	 *       l'utilisateur, quitte le programme si {@code exit} est entre ou
	 *       ecrit un message d'erreur si la methode demandee n'existe pas
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
	 * Routine selectionnant la methode a appeler a partir de la demande de
	 * l'utilisateur.
	 * 
	 * @pre {@code cmd} est une ligne tapee de l'utilisateur
	 * @post Appelle la methode adequate en fonction de la demande de
	 *       l'utilisateur. Retourne {@code true} s'il desire quitter le
	 *       programme, {@code false} sinon
	 * @param cmd
	 *            La ligne lue par le scanner
	 * @return {@code true} si l'utilisateur desire quitter le programme,
	 *         {@code false} sinon
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
		new Dictionary();
		scanInput();
	}

}
