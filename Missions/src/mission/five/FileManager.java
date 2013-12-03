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
	/**
	 * Mode set to read from an input file.
	 */
	public static final int READING = 1;

	/**
	 * Mode set to write to an output file.
	 */
	public static final int WRITING = 2;

	/**
	 * Mode set to read from an input bitstream.
	 */
	public static final int READ_BITSTREAM = 3;

	/**
	 * Mode set to write to an output bitstream.
	 */
	public static final int WRITE_BITSTREAM = 4;

	/**
	 * Input file reader.
	 */
	protected static BufferedReader reader;

	/**
	 * Output file writer.
	 */
	protected static PrintWriter writer;

	/**
	 * Input bitstream reader.
	 */
	protected static InputBitStream ibs;

	/**
	 * Output bitstream writer.
	 */
	protected static OutputBitStream obs;

	/**
	 * Input filename.
	 */
	private static String inputFile = null;

	/**
	 * Output filename.
	 */
	private static String outputFile = null;

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
			closeFile();
			System.exit(-1);
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
	 * Opens the input and/or output file(s) whose paths are {@link #inputFile}/
	 * {@link #outputFile}.
	 * 
	 * @pre -
	 * @post {@link #reader} et/ou {@link #writer} contient le fichier ouvert et
	 *       est pret a etre utilise.
	 */
	public static void openFile()
	{
		if (inputFile != null)
		{
			try
			{
				reader = new BufferedReader(new FileReader(inputFile));
			}
			catch (FileNotFoundException e)
			{
				System.err.println("Open failed : File doesn't exist");
				System.exit(-1);
			}
		}
		if (outputFile != null)
		{
			try
			{
				writer =
						new PrintWriter(new BufferedWriter(new FileWriter(
								outputFile)));
			}
			catch (IOException e)
			{
				System.err.println("Write failed : File doesn't exist");
				System.exit(-1);
			}
		}
	}

	/**
	 * Ouvre le fichier d'entree specifie par {@code filename}
	 * 
	 * @param filename
	 *            Le fichier a ouvrir
	 * @pre Le fichier pointe par {@code filename} existe et est lisible
	 * @post {@link #reader} contient le fichier ouvert et est pret a etre lu,
	 *       et {@link #inputFile} contient le nouveau fichier d'entree
	 * @see #openFile()
	 * @see #setInputFile(String)
	 */
	public static void openFile(String filename)
	{
		setInputFile(filename);
		openFile();
	}

	/**
	 * Opens the file under the specified mode. If the mode is set to
	 * {@link #READING} or {@link #WRITING}, the result is equivalent to the
	 * call to {@link #openFile()}.
	 * 
	 * @param mode
	 *            The mode the file has to be opened under.
	 * @pre -
	 * @post The stream specified by {@code mode} is opened.
	 * @see #openFile()
	 */
	public static void openFile(int mode)
	{
		switch (mode)
		{
			case READING:
			case WRITING:
				openFile();
				break;
			case READ_BITSTREAM:
				try
				{
					ibs = new InputBitStream(inputFile);
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
					obs = new OutputBitStream(outputFile);
				}
				catch (IOException e)
				{
					System.err.println("Writable bit stream open failed");
					System.exit(-1);
				}
				break;
			default:
				throw new IllegalArgumentException(
						"Unhandled operation on file");
		}
	}

	/**
	 * Opens the stream specified by {@code mode} and {@code filename}. As soon
	 * as the filename is set as an input file or an output file, it is
	 * equivalent to {@link #openFile(int)}.
	 * 
	 * @param mode
	 *            The mode the file has to be opened under.
	 * @param filename
	 *            the file to be opened.
	 * @see #openFile(int)
	 */
	public static void openFile(int mode, String filename)
	{
		if (mode == READING || mode == READ_BITSTREAM) setInputFile(filename);
		else if (mode == WRITING || mode == WRITE_BITSTREAM) setOutputFile(filename);
		openFile(mode);
	}

	/**
	 * Rouvre le fichier d'entree pointe par {@link #inputFile}
	 * 
	 * @pre -
	 * @post Rouvre le fichier d'entree en le fermant prealablement si
	 *       {@link #reader} n'est pas {@code null}
	 * @throws IOException
	 *             Si un evenement inattendu est survenu pendant la fermeture
	 *             prealable du fichier d'entree
	 * @see #openFile()
	 * @see #closeFile(int)
	 */
	public static void reopenFile(int mode)
	{
		closeFile(mode);
		openFile(mode);
	}

	/**
	 * Ferme tous les flux.
	 * 
	 * @pre -
	 * @post Ferme tous les flux et met leurs identificateurs respectifs a
	 *       {@code null}.
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
	 * Closes the filestream specified by {@code mode}.
	 * 
	 * @param mode
	 *            The mode whose identificator has to be closed.
	 * @pre -
	 * @post Closes the specified filestream and sets its identificator to
	 *       {@code null}.
	 */
	public static void closeFile(int mode)
	{
		switch (mode)
		{
			case READING:
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
				break;
			case READ_BITSTREAM:
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
				break;
			case WRITING:
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
				break;
			case WRITE_BITSTREAM:
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
				break;
			default:
				throw new IllegalArgumentException(
						"Unhandled operation on file");
		}
	}

	/**
	 * @param inputFile
	 *            Le chemin vers un fichier a lire
	 * @pre -
	 * @post Affecte {@code inputFile} a {@link #inputFile}
	 */
	public static void setInputFile(String inputFile)
	{
		FileManager.inputFile = inputFile;
	}

	/**
	 * @param outputFile
	 *            Le chemin vers un fichier dans lequel ecrire.
	 * @pre -
	 * @post Affecte {@code outputFile} a {@link #outputFile}.
	 */
	public static void setOutputFile(String outputFile)
	{
		FileManager.outputFile = outputFile;
	}
}
