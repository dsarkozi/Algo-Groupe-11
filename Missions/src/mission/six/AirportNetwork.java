package mission.six;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main class, where the input file is processed and where the output is
 * printed.
 * 
 * @author David Sarkozi
 * 
 */
/* Comments are for debugging purposes */
public class AirportNetwork
{
	/**
	 * Represents the airport network graph, containing all the potential flight
	 * paths between all the destinations.
	 */
	private Graph<Integer, Integer> network;

	// private Graph<Integer, Integer> networkOptimal;

	/**
	 * Constructor of the class.
	 */
	public AirportNetwork()
	{
		network = new Graph<Integer, Integer>();
		// networkOptimal = new Graph<Integer, Integer>();
	}

	/**
	 * Constructor of the class. Opens {@code inputFile} and hydrates
	 * {@link #network} with data read from it.
	 * 
	 * @param inputFile
	 *            The input file manager to be opened and read.
	 * @see #hydrateNetwork(FileManager)
	 */
	public AirportNetwork(FileManager inputFile)
	{
		this();
		try
		{
			inputFile.openFile();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error loading the file.");
			System.exit(-1);
		}
		try
		{
			hydrateNetwork(inputFile);
		}
		catch (IOException e)
		{
			System.err.println("Error while reading input file.");
			try
			{
				inputFile.closeFile();
			}
			catch (IOException e1)
			{
			}
			System.exit(-1);
		}
	}

	/**
	 * Hydrates {@link #network} with data from {@code inputFile}.
	 * 
	 * @pre {@code inputFile} has been opened before the call.
	 * @post Hydrates {@link #network} with data from {@code inputFile}, throws
	 *       an exception on error.
	 * 
	 * @param inputFile
	 *            The input file manager.
	 * @throws IOException
	 *             If the reading operation failed.
	 * @throws NumberFormatException
	 *             If the input file isn't made of {@code Integers}.
	 */
	private void hydrateNetwork(FileManager inputFile) throws IOException
	{
		String[] lineParts = null;
		String line = null;
		/* For tab delimiters parsing */
		Pattern pattern = Pattern.compile("[\\t ]");
		while ((line = inputFile.readLine()) != null)
		{
			lineParts = pattern.split(line);
			Vertex<Integer, Integer> v1 =
					network.addVertex(Integer.parseInt(lineParts[0]));
			Vertex<Integer, Integer> v2 =
					network.addVertex(Integer.parseInt(lineParts[1]));
			network.connectVertex(v1, v2, Integer.parseInt(lineParts[2]));
		}
	}

	/**
	 * Prints an airport {@link #network}.
	 * 
	 * @param out
	 *            The {@link PrintStream} where it has to print.
	 *            {@link System#out} by default if {@code null}.
	 * @param network
	 *            The {@link #network} to print.
	 * @pre -
	 * @post Prints {@code network} to the {@link PrintStream} specified by
	 *       {@code out}. If {@code out == null}, then printing is directed to
	 *       {@link System#out} by default.
	 */
	public void printNetwork(Graph<Integer, Integer> network, PrintStream out)
	{
		if (out == null) out = System.out;
		ArrayList<Edge<Integer, Integer>> edges = network.getEdge();
		for (Edge<Integer, Integer> edge : edges)
		{
			out.println(edge.toString());
		}
	}

	/**
	 * @param args
	 *            The input of the program. {@code args[0] == inputFile}.
	 * @pre -
	 * @post Processes the input and prints the output to {@link System#out}
	 *       plus any other {@link PrintStream} specified, or an error message
	 *       if something went bad.
	 */
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println("Invalid number of args.");
			System.exit(-1);
		}
		FileManager inputFile = new FileManager(args[0]);
		AirportNetwork airnet = new AirportNetwork(inputFile);

		Graph<Integer, Integer> kruskal = airnet.network.kruskal();
		airnet.printNetwork(kruskal, System.out);
		System.out.println("Do you want to save the output to a file ?");
		System.out.println("Not saving by default (y/n)");
		Scanner in = new Scanner(System.in);
		String output = null;
		if (in.hasNext("[yn]")) output = in.next();
		in.close();
		/*
		 * PrintStream ps = null; PrintStream psSort = null; PrintStream
		 * psKruskal = null; try { ps = new PrintStream("output.txt"); psSort =
		 * new PrintStream("output-sorted.txt"); psKruskal = new
		 * PrintStream("output-kruskal.txt"); } catch (FileNotFoundException e1)
		 * { System.err.println("Error while writing to output file."); try {
		 * inputFile.closeFile(); } catch (IOException e2) { } System.exit(-1);
		 * } airnet.printNetwork(airnet.network, ps);
		 * Collections.sort(airnet.network.getEdge());
		 * airnet.printNetwork(airnet.network, psSort); airnet.networkOptimal =
		 * airnet.network.kruskal(); airnet.printNetwork(airnet.networkOptimal,
		 * psKruskal); psSort.close(); psKruskal.close();
		 */
		if (output != null && output.equals("y"))
		{
			PrintStream ps = null;
			try
			{
				ps = new PrintStream("output.txt");
			}
			catch (FileNotFoundException e)
			{
				System.err.println("Error while writing to output file.");
				try
				{
					inputFile.closeFile();
				}
				catch (IOException e2)
				{
				}
				System.exit(-1);
			}
			airnet.printNetwork(kruskal, ps);
			System.out.println("Output saved to ./output.txt");
			ps.close();
		}
		else System.out.println("Output hasn't been saved.");
	}
}
