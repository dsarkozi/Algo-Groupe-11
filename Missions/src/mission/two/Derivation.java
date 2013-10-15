package mission.two;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * @author
 * 
 */
public class Derivation implements FormalExpressionTree
{
	private LinkedRBinaryTree<String> bTree;
	private static final int PAR = 1;
	private static final int NO_PAR = 2;
	private static final int NO_OP = 3;
	private Pattern pattern;
	private Pattern patternSimple;
	private Pattern patternLeft;
	private Pattern patternRight;
	private Pattern patternDouble;
	private Pattern patternNegative;
	private ArrayList<String> operators;
	private ArrayList<Pattern> patternOps;

	public Derivation()
	{
		pattern = Pattern.compile("\\((.+)\\)");
		patternSimple = Pattern.compile("(.+)([+\\-*/\\^])(.+)");
		patternLeft = Pattern.compile("(.+\\))([+\\-*/\\^])(.+)");
		patternRight = Pattern.compile("(.+)([+\\-*/\\^])(\\(.+)");
		patternDouble = Pattern.compile("(.+\\))([+\\-*/\\^])(\\(.+)");
		patternNegative = Pattern.compile("\\-.+");
		bTree = new LinkedRBinaryTree<String>(new BTNode<String>(null, null,
				null, null));
		operators = new ArrayList<String>();
		operators.add("sin");
		operators.add("cos");

		patternOps = new ArrayList<Pattern>();
		for (String operator : operators)
		{
			patternOps.add(Pattern.compile("(.+)([+\\-*/\\^])(" + operator
					+ ".+)"));
		}
	}

	/**
	 * @author David Sarkozi
	 */
	public static void main(String[] args)
	{
		String inputFile = args[0];
		String outputFile = inputFile.concat("--result.txt");
		Derivation drv = new Derivation();
		ArrayList<String> lines = null;
		String output = null;
		int err = 0;
		// transformer le fichier en lignes de strings (read)
		try
		{
			lines = FileManager.readFile(inputFile);
		}
		catch (IOException e)
		{
			System.err
					.println("Une erreur est survenue à la lecture du fichier "
							+ inputFile);
			return;
		}
		// decoder et faire les calculs pour chaque ligne
		for (String line : lines)
		{
			if (!drv.checkSyntax(line, PAR))
			{
				err++;
				output = "Line syntaxically incorrect";
			}
			else
			{
				try
				{
					drv.loadExpression(line, drv.bTree.root);
					output = drv.toString();
				}
				catch (InputFormatException e)
				{
					err++;
					output = e.getMessage();
				}
			}
			try
			{
				FileManager.writeInFile(outputFile, output);
				// TODO Appel a derive
			}
			catch (IOException e)
			{
				System.err
						.println("Une erreur est survenue à l'écriture dans le fichier "
								+ outputFile);
			}
			drv.bTree = new LinkedRBinaryTree<String>(new BTNode<String>(null,
					null, null, null));
		}
		if (err > 0) System.err.println("Input file syntaxically incorrect in "
				+ err + " lines");
	}

	/**
	 * @author David Sarkozi
	 * @param expression
	 *            Une ligne de l'input formattee en String
	 * @param iterator
	 *            Un noeud de bTree, point de depart ou stocker l'equivalent en
	 *            arbre de expression
	 * @pre iterator doit etre la racine de bTree lors du premier appel et le
	 *      noeud de depart pour les autres appels successifs, expression doit
	 *      etre correctement formattee (bien parenthesee et parsable)
	 * @post Stocke dans bTree l'arbre correspondant a expression a l'aide de
	 *       iterator, parcourant bTree
	 * 
	 */
	// ([^\(\)]+)[+\-*/]([^\(\)]+)
	public void loadExpression(String expression, BTNode<String> iterator)
	{
		String[] expr = parenthesesRemover(expression);
		String[] exprSplit = null;
		if (expr == null) // 10
		{

			if (!checkSyntax(expression, NO_PAR)
					|| !checkSyntax(expression, NO_OP))
			{
				throw new InputFormatException("Line syntaxically incorrect");
			}

			iterator.setElement(expression);
			return;
		}
		else if (expr.length == 1) // 10+x or x^(-3)
		{
			exprSplit = exprSplitter(expr[0]);
			if (exprSplit == null) // -3
			{
				// throw new InputFormatException();
				if (patternNegative.matcher(expr[0]).matches())
				{
					iterator.setElement(expr[0]);
					return;
				}
				else
				{
					System.err.println("Input file syntaxically incorrect");
				}
			}
			else
			// 10+x
			{
				iterator.setElement(exprSplit[1]); // +
				BTNode<String> iterLeft = new BTNode<String>(null, null, null,
						iterator);
				iterator.setLeft(iterLeft);
				BTNode<String> iterRight = new BTNode<String>(null, null, null,
						iterator);
				iterator.setRight(iterRight);
				loadExpression(exprSplit[0], iterLeft); // 10
				loadExpression(exprSplit[2], iterRight); // x
			}
		}
		else if (expr.length == 2) // sin or cos
		{
			iterator.setElement(expr[0]); // sin or cos
			BTNode<String> iterChild = new BTNode<String>(null, null, null,
					iterator);
			iterator.setRight(iterChild);
			loadExpression(expr[1], iterChild); // x or (x+2)
		}
	}

	/**
	 * @author David Sarkozi
	 * @pre expression doit etre bien parenthesee
	 * @post Retourne un tableau contenant soit l'expression sans parenthese,
	 *       soit l'operateur trigonometrique et son expression, soit null s'il
	 *       n'y a pas de parenthese
	 * @param expression
	 *            Une expression dont il faut enlever les parentheses
	 * @return Un tableau contenant la ligne sans parentheses, ou l'operateur
	 *         sin ou cos suivi de son element, ou null s'il n'y a pas de
	 *         parenthese a enlever
	 */
	private String[] parenthesesRemover(String expression)
	{
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches())
		{
			return new String[] { matcher.group(1) };
		}
		else
		{
			String operator = matcher.replaceFirst("");
			if (operators.contains(operator))
			{
				return new String[] { operator, matcher.group(1) };
			}
			else
			{
				return null;
			}
		}
	}

	/**
	 * @author David Sarkozi
	 * @pre expression doit etre bien formee
	 * @post Retourne un tableau contenant soit l'expression splittee, soit null
	 *       s'il n'y a pas d'operateur dans expression
	 * @param expression
	 *            Une expression dont il faut extraire l'operateur et ses termes
	 * @return Un tableau contenant l'expression splittee sous la forme :
	 *         [terme, operateur, terme] ou null s'il n'y a pas d'operateur dans
	 *         expression
	 */
	private String[] exprSplitter(String expression)
	{
		Matcher matcher = patternDouble.matcher(expression);
		if (matcher.matches())
		{
			return new String[] { matcher.group(1), matcher.group(2),
					matcher.group(3) };
		}
		matcher = patternRight.matcher(expression);
		if (matcher.matches())
		{
			if (checkSyntax(matcher.group(1), PAR)
					&& checkSyntax(matcher.group(3), PAR))
			{
				return new String[] { matcher.group(1), matcher.group(2),
						matcher.group(3) };
			}
		}
		matcher = patternLeft.matcher(expression);
		if (matcher.matches())
		{
			if (checkSyntax(matcher.group(1), PAR)
					&& checkSyntax(matcher.group(3), PAR))
			{
				return new String[] { matcher.group(1), matcher.group(2),
						matcher.group(3) };
			}
		}
		matcher = patternSimple.matcher(expression);
		if (matcher.matches())
		{
			Matcher matcherOps = null;
			for (Pattern pat : patternOps)
			{
				matcherOps = pat.matcher(expression);
				if (matcherOps.matches())
				{
					return new String[] { matcherOps.group(1),
							matcherOps.group(2), matcherOps.group(3) };
				}
			}
			return new String[] { matcher.group(1), matcher.group(2),
					matcher.group(3) };
		}
		return null;
	}

	/**
	 * @author David Sarkozi
	 * @pre check doit etre un algorithme implemente
	 * @post Verifie la syntaxe d'expression selon l'algorithme choisi avec
	 *       check
	 * @param expression
	 *            Une expression dont il faut verifier sa syntaxe
	 * @param check
	 *            L'algorithme de verification utilise, entre verifier le nombre
	 *            de parentheses, verifier leur presence et verifier la presence
	 *            d'un operateur
	 * @return true si l'expression est syntaxiquement correcte selon le check
	 *         choisi, false sinon
	 */
	private boolean checkSyntax(String expression, int check)
	{
		char[] tab = expression.toCharArray();
		switch (check)
		{
			case PAR:
				int l = 0,
				r = 0;
				for (int k = 0; k < tab.length; k++)
				{
					if (tab[k] == '(') l++;
					else if (tab[k] == ')') r++;
				}
				return l == r;
			case NO_PAR:
				for (int k = 0; k < tab.length; k++)
				{
					if (tab[k] == '(' || tab[k] == ')') return false;
				}
				return true;
			case NO_OP:
				for (int k = 0; k < tab.length; k++)
				{
					if (tab[k] == '+' || tab[k] == '*' || tab[k] == '\\'
							|| tab[k] == '^' || (tab[k] == '-' && k > 0))
					{
						return false;
					}
				}
				return true;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return toStringR(bTree);
	}

	private String toStringR(RBinaryTree<String> T)
	{
		// element isole
		if (T.isLeaf())
		{
			BTNode<String> root = (BTNode<String>) T.root();
			if (root.hasParent())
			{
				String elem = root.element();
				return (patternNegative.matcher(elem).matches()) ? "(" + elem
						+ ")" : elem;
			}
			else return root.element();
		}
		// operateur 'original'
		if (operators.contains(T.root().element()))
		{
			if (!T.leftTree().isEmpty()) return T.root().element() + "("
					+ toStringR(T.leftTree()) + ")";
			else return T.root().element() + "(" + toStringR(T.rightTree())
					+ ")";
		}
		// cas general
		return "(" + toStringR(T.leftTree()) + T.root().element()
				+ toStringR(T.rightTree()) + ")";
	}

	@Override
	public FormalExpressionTree derive()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
