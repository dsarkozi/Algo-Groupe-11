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
 * @author Benoit Sluysmans
 * 
 */
/*	Comments are for debugging purposes	*/
public class AirportNetwork
{
	private Graph<Integer, Integer> network;
	// private Graph<Integer, Integer> networkOptimal;

	public AirportNetwork()
	{
		network = new Graph<Integer, Integer>();
		// networkOptimal = new Graph<Integer, Integer>();
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
	 * Prints the airport {@code network}.
	 * 
	 * @param out
	 *            The {@link PrintStream} where it has to print.
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
		airnet.printNetwork(airnet.network, System.out);
		System.out.println("Do you want to save the output to a file ?");
		System.out.println("Not saving by default (y/n)");
		Scanner in = new Scanner(System.in);
		String output = null;
		if (in.hasNext("[yn]")) output = in.next();
		in.close();
		/*
		PrintStream ps = null;
		PrintStream psSort = null;
		PrintStream psKruskal = null;
		try
		{
			ps = new PrintStream("output.txt");
			psSort = new PrintStream("output-sorted.txt");
			psKruskal = new PrintStream("output-kruskal.txt");
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
		
		airnet.printNetwork(airnet.network, ps);
		Collections.sort(airnet.network.getEdge());
		airnet.printNetwork(airnet.network, psSort);
		airnet.networkOptimal = airnet.network.kruskal();
		airnet.printNetwork(airnet.networkOptimal, psKruskal);
		psSort.close();
		psKruskal.close();
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
			airnet.printNetwork(airnet.network.kruskal(), ps);
			System.out.println("Output saved to ./output.txt");
			ps.close();
		}
		else System.out.println("Output hasn't been saved.");
	}
}
