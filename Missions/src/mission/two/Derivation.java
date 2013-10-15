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
	private Pattern pattern;
	private Pattern patternSimple;
	private Pattern patternLeft;
	private Pattern patternRight;
	private Pattern patternDouble;
	private ArrayList<String> operators;

	public Derivation()
	{
		pattern = Pattern.compile("\\((.+)\\)");
		patternSimple = Pattern.compile("(.+)([+\\-*/\\^])(.+)");
		patternLeft = Pattern.compile("(.+\\))([+\\-*/\\^])(.+)");
		patternRight = Pattern.compile("(.+)([+\\-*/\\^])(\\(.+)");
		patternDouble = Pattern.compile("(.+\\))([+\\-*/\\^])(\\(.+)");
		bTree = new LinkedRBinaryTree<String>();
		operators = new ArrayList<String>();
		operators.add("sin");
		operators.add("cos");
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
				drv.loadExpression(line, drv.bTree.root);
		}
		try
		{
			FileManager.writeInFile(outputFile,""); //TODO Appel a derive
		}
		catch (IOException e)
		{
			System.err
					.println("Une erreur est survenue à l'écriture dans le fichier "
							+ outputFile);
		}
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
			iterator.setElement(expression);
			return;
		}
		else if (expr.length == 1) // 10+x or x^3
		{
			exprSplit = exprSplitter(expr[0]);
			if (exprSplit == null)
			{
				throw new InputFormatException();
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
		matcher = patternLeft.matcher(expression);
		if (matcher.matches())
		{
			return new String[] { matcher.group(1), matcher.group(2),
					matcher.group(3) };
		}
		matcher = patternRight.matcher(expression);
		if (matcher.matches())
		{
			return new String[] { matcher.group(1), matcher.group(2),
					matcher.group(3) };
		}
		matcher = patternSimple.matcher(expression);
		if (matcher.matches())
		{
			return new String[] { matcher.group(1), matcher.group(2),
					matcher.group(3) };
		}
		return null;
	}
	
	/** Fonction retournant un String representant l'arbre bTree
	 * @pre bTree.isEmpty() == null
	 * @post Un string representant l'arbre est retourne, ou bien 
	 */
	@Override
	public String toString()
	{
		return toStringR(bTree);
	}
	
	/** Fonction recursive pour parcourir l'arbre et le convertir en String
	 * @param T <l'arbre a convertir en String>
	 */
	private String toStringR(RBinaryTree<String> T)
	{
		// element isole
		if(T.isLeaf())
			return T.root().element();
		// operateur 'original'
		if(operators.contains(T.root().element()))
		{
			if(T.leftTree() != null)
				return T.root().element() + "(" + toStringR(T.leftTree()) + ")";
			else
				return T.root().element() + "(" + toStringR(T.rightTree()) + ")";
		}
		// cas general
		return toStringR(T.leftTree()) + T.root().element() + toStringR(T.rightTree());
	}

	@Override
	public FormalExpressionTree derive()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
