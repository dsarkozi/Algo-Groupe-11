package mission.five;

/**
 * Class taking care of file decompression.
 * 
 * @author
 * @see Compress
 */
public class Decompress
{
	private String inputFile;
	private String outputFile;

	/**
	 * Constructor of the class.
	 * 
	 * @param inputFile
	 *            The input file to compress.
	 * @param outputFile
	 *            The output file that will contain the compressed input file.
	 */
	public Decompress(String inputFile, String outputFile)
	{
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	/**
	 * 
	 * @param args
	 *            Contains the arguments of the program (the input filename at
	 *            index 0, the output filename at index 1).
	 * @pre args contains valid data.
	 * @post Decompresses the input file and returns the result in the output
	 *       file.
	 */
	public static void main(String[] args)
	{
		if (args.length < 1 || args.length > 2)
		{
			System.err.println("Invalid number of arguments.");
			return;
		}
		Decompress unzip = new Decompress(args[0], args[1]);
	}
}
