package mission.six;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	
	private void printNetwork()
	{
		ArrayList<Edge<Integer, Integer>> edges = network.getEdge();
		ArrayList<Vertex<Integer, Integer>> vertex = null;
		for (Edge<Integer, Integer> edge : edges)
		{
			vertex = edge.getEnds();
			for (Vertex<Integer, Integer> v : vertex)
			{
				System.out.print(v.getElement());
				System.out.print("\t");
			}
			System.out.println(edge.getElement());
		}
	}

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
			System.exit(-1);
		}
		airnet.printNetwork();
	}

}
