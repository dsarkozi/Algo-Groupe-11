package mission.six;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Main class, where the input file is processed and where the output is
 * printed.
 * 
 * @author David Sarkozi
 * 
 */
public class AirportNetwork
{
	private Graph<Integer, Integer> network;

	public AirportNetwork()
	{
		network = new Graph<Integer, Integer>();
	}

	/**
	 * Hydrates {@link #network} with the values of {@code inputFile}.
	 * 
	 * @param inputFile
	 *            The input file manager.
	 * @throws IOException
	 *             If the reading operation failed.
	 * @throws NumberFormatException
	 *             If the input file isn't made of Integers.
	 */
	private void hydrateNetwork(FileManager inputFile) throws IOException
	{
		String[] lineParts = null;
		String line = null;
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
	 * Prints the airport {@link #network}.
	 * 
	 * @param out
	 *            The {@link PrintStream} where it has to print.
	 * @pre -
	 * @post Prints {@link #network} to the {@link PrintStream} specified by
	 *       {@code out}. If {@code out == null}, then printing is directed to
	 *       {@link System#out} by default.
	 */
	private void printNetwork(PrintStream out)
	{
		if (out == null) out = System.out;
		ArrayList<Edge<Integer, Integer>> edges = network.getEdge();
		ArrayList<Vertex<Integer, Integer>> vertex = null;
		for (Edge<Integer, Integer> edge : edges)
		{
			vertex = edge.getEnds();
			for (Vertex<Integer, Integer> v : vertex)
			{
				out.print(v.getElement());
				out.print("\t");
			}
			out.println(edge.getElement());
		}
	}

	/**
	 * @param args
	 *            The input of the program.
	 * @pre -
	 * @post Processes the input and prints the output to {@link System#out} or
	 *       any other {@link PrintStream} specified, or an error message if
	 *       something went bad.
	 */
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println("Invalid number of args.");
			System.exit(-1);
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
		AirportNetwork airnet = new AirportNetwork();
		try
		{
			airnet.hydrateNetwork(inputFile);
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
		airnet.printNetwork(System.out);
		PrintStream ps = null;
		PrintStream psSort = null;
		try
		{
			ps = new PrintStream("output.txt");
			psSort = new PrintStream("output-sorted.txt");
		}
		catch (FileNotFoundException e1)
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
		airnet.printNetwork(ps);
		Collections.sort(airnet.network.getEdge());
		airnet.printNetwork(psSort);
	}
}
