package mission.one;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class PostScript
{

	private PostStack<String> stack;
	private ArrayList<String> lines;
	private String outputFile;
	private ArrayList<UserValue> userValues;

	public PostScript(String inputFile)
	{
		lines = new ArrayList<String>();
		userValues = new ArrayList<UserValue>();
		stack = new PostStack<String>();
		outputFile = inputFile.concat("--result.txt");
	}

	/**
	 * Provoque l'impression de toute la pile dans le fichier de sortie.
	 * 
	 * @pre s != null
	 * @post le contenu de la pile a ete imprime dans le fichier de sortie le
	 *       cas echeant, l'interpretation de la ligne courante est a mesure de
	 *       se poursuivre
	 */
	public void pstack() throws IOException
	{
		String line = "";
		if (!stack.isEmpty())
		{
			line = stack.get(0);
			for (int i = 1; i < stack.getSize(); i++)
			{
				line = (line.concat(" ")).concat(stack.get(i));
			}
		}
		FileManager.writeInFile(outputFile, line);
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la PostStack par leur somme
	 */
	public void add()
	{
		double result = Double.parseDouble(stack.pop())
				+ Double.parseDouble(stack.pop());
		stack.push(Double.toString(result));
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la PostStack par leur
	 *       différence
	 */
	public void sub()
	{
		double result = Double.parseDouble(stack.pop())
				- Double.parseDouble(stack.pop());
		stack.push(Double.toString(result));
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la PostStack par leur
	 *       multiplication
	 */
	public void mul()
	{
		double result = Double.parseDouble(stack.pop())
				* Double.parseDouble(stack.pop());
		stack.push(Double.toString(result));
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la PostStack par leur
	 *       division
	 */
	public void div()
	{
		double result = Double.parseDouble(stack.pop())
				/ Double.parseDouble(stack.pop());
		stack.push(Double.toString(result));
	}

	/**
	 * 
	 * @post Teste si les deux premiers elements de la PostStack ne sont pas
	 *       egaux
	 */
	public void ne()
	{
		boolean result = Double.parseDouble(stack.pop()) != Double
				.parseDouble(stack.pop());
		stack.push(Boolean.toString(result));
	}

	/**
	 * 
	 * @post Teste si les deux premiers elements de la pile sont egaux
	 */
	public void eq()
	{
		boolean result = Double.parseDouble(stack.pop()) == Double
				.parseDouble(stack.pop());
		stack.push(Boolean.toString(result));
	}

	/**
	 * @pre
	 * @post Echange la place des deux elements top de la PostStack
	 */
	public void exch()
	{
		String elem1 = stack.pop();
		String elem2 = stack.pop();
		stack.push(elem1);
		stack.push(elem2);
	}

	/**
	 * 
	 * @post push une copie du top element
	 */
	public void dup()
	{
		stack.push(stack.peek());
	}

	/**
	 * 
	 * @post pop le top element
	 */
	public String pop()
	{
		return stack.pop();
	}

	/**
	 * @pre The key to be defined and his value are in the stack
	 * @post Defines a symbol from the key and his value
	 */
	public void def()
	{
		double value = Double.parseDouble(stack.pop());
		String key = stack.pop();
		if (!key.startsWith("/")) return;
		else key = key.substring(1);
		for (UserValue uv : userValues)
		{
			if (uv.getKey().equals(key))
			{
				uv.setValue(value);
				return;
			}
		}
		userValues.add(new UserValue(key, value));
	}

	/**
	 * @pre: argument ligne non null
	 * @post: decode et execute les instructions presentes dans le String ligne.
	 */
	public void decode(String ligne)
	{
		assert ligne != null : "argument String ligne == null.";
		String elem[] = ligne.split(" ");
		for (int i = 0; i < elem.length; i++)
		{
			switch (elem[i])
			{
				case "pstack":
					try
					{
						pstack();
					}
					catch (IOException e)
					{
						System.err
								.println("Une erreur est survenue à l'écriture dans le fichier "
										+ outputFile);
						return;
					}
					break;
				case "add":
					add();
					break;
				case "sub":
					sub();
					break;
				case "mul":
					mul();
					break;
				case "div":
					div();
					break;
				case "dup":
					dup();
					break;
				case "exch":
					exch();
					break;
				case "eq":
					eq();
					break;
				case "ne":
					ne();
					break;
				case "def":
					def();
					break;
				case "pop":
					pop();
					break;
				default:
					for (UserValue uv : userValues)
					{
						if (uv.getKey().equals(elem[i]))
						{
							elem[i] = Double.toString(uv.getValue());
							break;
						}
					}
					stack.push(elem[i]);
					break;
			}
		}
	}

	public static void main(String[] args)
	{
		String inputFile = args[0];
		PostScript ps = new PostScript(args[0]);
		// transformer le fichier en lignes de strings (read)
		try
		{
			ps.lines = FileManager.readFile(inputFile);
		}
		catch (IOException e)
		{
			System.err
					.println("Une erreur est survenue à la lecture du fichier "
							+ inputFile);
			return;
		}
		// decoder et faire les calculs pour chaque ligne
		for (String line : ps.lines)
		{
			try
			{
				ps.decode(line);
			}
			catch (EmptyStackException e)
			{
				System.err
						.println("Not enough arguments in the stack for this operation. Check Result.txt file !");
				try
				{
					FileManager.writeInFile(ps.outputFile,
							"Format error in the line : " + line);
					FileManager
							.writeInFile(ps.outputFile, "-- line failed -- ");
				}
				catch (IOException ex)
				{
					System.err
							.println("Une erreur est survenue à l'écriture dans le fichier "
									+ ps.outputFile);
				}
				ps.stack.emptyStack();
				continue;
			}
			catch (NumberFormatException e)
			{
				System.err
						.println("Wrong type of value in the stack. Check Result.txt file !");
				try
				{
					FileManager.writeInFile(ps.outputFile,
							"Parse error in the line : " + line);
					FileManager
							.writeInFile(ps.outputFile, "-- line failed -- ");
				}
				catch (IOException ex)
				{
					System.err
							.println("Une erreur est survenue à l'écriture dans le fichier "
									+ ps.outputFile);
				}
				ps.stack.emptyStack();
				continue;
			}
			try
			{
				FileManager.writeInFile(ps.outputFile, "-- line passed --");
			}
			catch (IOException e)
			{
				System.err
						.println("Une erreur est survenue à l'écriture dans le fichier "
								+ ps.outputFile);
			}
		}
		try
		{
			FileManager.writeInFile(ps.outputFile, "** end of file **\n");
		}
		catch (IOException e)
		{
			System.err
					.println("Une erreur est survenue à l'écriture dans le fichier "
							+ ps.outputFile);
		}
	}

	private class UserValue
	{
		private String key;
		private double value;

		public UserValue(String key, double value)
		{
			this.key = key;
			this.value = value;
		}

		/**
		 * @return the key
		 */
		public String getKey()
		{
			return key;
		}

		/**
		 * @return the value
		 */
		public double getValue()
		{
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(double value)
		{
			this.value = value;
		}

	}
}
