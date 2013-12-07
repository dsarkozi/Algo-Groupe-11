package mission.six;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Input file manager class.
 * 
 * @author David Sarkozi
 */

public class FileManager
{
	/**
	 * Input file reader.
	 */
	private BufferedReader reader;

	/**
	 * Input file path.
	 */
	private String filename;

	/**
	 * Constructor of the class.
	 * 
	 * @param filename
	 *            The path to the input file.
	 * @see #filename
	 */
	public FileManager(String filename)
	{
		this.filename = filename;
	}

	/**
	 * Reads a line of the input file. Equivalent to {@code readLine(false)}.
	 * 
	 * @pre {@link #reader} is open and ready to be read.
	 * @post Returns the line read.
	 * @return The line read.
	 * @throws IOException
	 *             If an unknown event occurs while reading.
	 * @see #readLine(boolean)
	 */
	public String readLine() throws IOException
	{
		return reader.readLine();
	}

	/**
	 * Reads a line of the input file and closes {@link #reader} if
	 * {@code closeFile} is set to {@code true}.
	 * 
	 * @param closeFile
	 *            If {@code true}, {@link #reader} is closed after reading.
	 * @pre {@link #reader} is opened and ready to be read.
	 * @post Returns the line read and closes {@link #reader} if
	 *       {@code closeFile} is set to {@code true}.
	 * @return The line read.
	 * @throws IOException
	 *             If an unknown event occurs while reading or closing the file.
	 * @see #closeFile()
	 */
	public String readLine(boolean closeFile) throws IOException
	{
		String currentLine = readLine();
		if (closeFile) closeFile();
		return currentLine;
	}

	/**
	 * Opens the input file whose path is {@link #filename}.
	 * 
	 * @return {@code this}
	 * @pre {@link #filename} exists and is readable.
	 * @post {@link #reader} contains the opened file and is ready to be read.
	 * @throws FileNotFoundException
	 *             If the input file isn't found or isn't readable.
	 * @see #reader
	 */
	public FileManager openFile() throws FileNotFoundException
	{
		reader = new BufferedReader(new FileReader(filename));
		return this;
	}

	/**
	 * Reopens the input file whose path is {@link #filename}.
	 * 
	 * @return {@code this}
	 * @pre -
	 * @post Reopens the input file after closing {@link #reader} first if it
	 *       isn't {@code null}.
	 * @see #openFile()
	 * @see #closeFile()
	 */
	public FileManager reopenFile() throws FileNotFoundException, IOException
	{
		if (reader != null) closeFile();
		return openFile();
	}

	/**
	 * Closes the input file.
	 * 
	 * @pre -
	 * @post Closes and sets {@link #reader} to {@code null}.
	 * @throws IOException
	 *             If it failed to close {@link #reader}.
	 */
	public void closeFile() throws IOException
	{
		reader.close();
		reader = null;
	}

	/**
	 * Sets the new file path to {@code this}.
	 * 
	 * @param filename
	 *            The new file path to set.
	 * @pre -
	 * @post Sets {@code filename} to {@link #filename} of {@code this}.
	 * @see #filename
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}
