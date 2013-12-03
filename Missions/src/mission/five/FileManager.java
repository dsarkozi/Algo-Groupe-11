package mission.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class managing input and output files.
 * 
 * @author David Sarkozi
 */

public class FileManager
{
	public static final int READING = 1;

	public static final int WRITING = 2;

	public static final int READ_BITSTREAM = 3;

	public static final int WRITE_BITSTREAM = 4;

	/**
	 * Represente le reader du fichier d'entree
	 */
	private static BufferedReader reader;

	protected static PrintWriter writer;

	protected static InputBitStream ibs;

	protected static OutputBitStream obs;

	/**
	 * Represente le chemin vers le fichier d'entree
	 */
	private static String filename;

	/**
	 * Lit une ligne du fichier d'entree. Est equivalent a
	 * {@code readLine(false)}
	 * 
	 * @pre Le reader est ouvert et pret a etre lu
	 * @post Retourne la ligne lue
	 * @return La ligne lue
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la lecture
	 * @see #readLine(boolean)
	 */
	public static String readLine()
	{
		try
		{
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.err.println("Error in reading the file.");
			System.exit(-1);
		}
		finally
		{
			closeFile();
		}
		return null;
	}

	/**
	 * Lit une ligne du fichier d'entree et ferme {@link #reader} si specifie
	 * 
	 * @param closeFile
	 *            Si defini a {@code true}, {@link #reader} est ferme apres
	 *            lecture
	 * @pre {@link #reader} est ouvert et pret a etre lu
	 * @post Retourne la ligne lue et ferme {@link #reader} si closeFile est a
	 *       {@code true}
	 * @return La ligne lue
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la lecture
	 * @see #closeFile()
	 */
	public static String readLine(boolean closeFile)
	{
		String currentLine = readLine();
		if (closeFile) closeFile();
		return currentLine;
	}

	public static void writeLine(String line)
	{
		writer.println(line);
		if (writer.checkError())
		{
			System.err.println("Error in writing in the file.");
			closeFile();
			System.exit(-1);
		}
	}

	/**
	 * 
	 * @return
	 */
	/*
	 * public static String readFile() { StringBuilder sb = new StringBuilder();
	 * String line = readLine(); while (line != null) {
	 * sb.append(line).append("\n"); line = readLine(); } return sb.toString();
	 * }
	 */

	/**
	 * Ouvre le fichier d'entree dont le chemin est {@link #filename}
	 * 
	 * @pre {@link #filename} existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu
	 */
	public static void openFile()
	{
		try
		{
			reader = new BufferedReader(new FileReader(filename));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Open failed : File doesn't exist");
			System.exit(-1);
		}
	}

	/**
	 * Ouvre le fichier d'entree specifie par {@code filename}
	 * 
	 * @param filename
	 *            Le fichier a ouvrir
	 * @pre Le fichier pointe par {@code filename} existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu,
	 *       et {@link #filename} contient le nouveau fichier d'entree
	 * @see #openFile()
	 * @see #setFilename(String)
	 */
	public static void openFile(String filename)
	{
		setFilename(filename);
		openFile();
	}

	/**
	 * Opens the file under the specified mode ({@link #READING} or
	 * {@link #WRITING}). If the mode is set to {@link #READING}, the result is
	 * equivalent to the call to {@link #openFile()}.
	 * 
	 * @param mode
	 *            The mode the file has to be opened under.
	 * @see #openFile()
	 */
	public static void openFile(int mode)
	{
		switch (mode)
		{
			case READING:
				openFile();
				break;
			case WRITING:
				try
				{
					writer =
							new PrintWriter(new BufferedWriter(new FileWriter(
									filename)));
				}
				catch (IOException e)
				{
					System.err.println("Read failed : File doesn't exist");
					System.exit(-1);
				}
				break;
			case READ_BITSTREAM:
				try
				{
					ibs = new InputBitStream(filename);
				}
				catch (IOException e)
				{
					System.err.println("Readable bit stream open failed");
					System.exit(-1);
				}
				break;
			case WRITE_BITSTREAM:
				try
				{
					obs = new OutputBitStream(filename);
				}
				catch (IOException e)
				{
					System.err.println("Writable bit stream open failed");
					System.exit(-1);
				}
			default:
				throw new IllegalArgumentException(
						"Unhandled operation on file");
		}
	}

	public static void openFile(int mode, String filename)
	{
		setFilename(filename);
		openFile(mode);
	}

	/**
	 * Rouvre le fichier d'entree pointe par {@link #filename}
	 * 
	 * @pre -
	 * @post Rouvre le fichier d'entree en le fermant prealablement si
	 *       {@link #reader} n'est pas {@code null}
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la fermeture
	 *             prealable du fichier d'entree
	 * @see #openFile()
	 * @see #closeFile()
	 */
	public static void reopenFile()
	{
		if (reader != null) closeFile();
		openFile();
	}

	/**
	 * Ferme le flux du fichier d'entree.
	 * 
	 * @pre -
	 * @post Ferme le flux du fichier d'entree et met {@link #reader} et/ou
	 *       {@link #writer} a {@code null}
	 */
	public static void closeFile()
	{
		if (reader != null)
		{
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				System.err.println("Error in closing the file.");
				System.exit(-1);
			}
			reader = null;
		}
		if (writer != null)
		{
			writer.close();
			if (writer.checkError())
			{
				System.err.println("Error in closing the file.");
				System.exit(-1);
			}
			writer = null;
		}
		if (ibs != null)
		{
			try
			{
				ibs.close();
			}
			catch (IOException e)
			{
				System.err.println("Readable bit stream close failed");
				System.exit(-1);
			}
			ibs = null;
		}
		if (obs != null)
		{
			try
			{
				obs.close();
			}
			catch (IOException e)
			{
				System.err.println("Writable bit stream close failed");
				System.exit(-1);
			}
			obs = null;
		}
	}

	/**
	 * @param filename
	 *            Le chemin vers un fichier a lire
	 * @pre -
	 * @post Affecte {@code filename} a {@link #filename}
	 */
	public static void setFilename(String filename)
	{
		FileManager.filename = filename;
	}
}
