package mission.two;

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
		patternSimple = Pattern.compile("(.+)([+\\-*/])(.+)");
		patternLeft = Pattern.compile("(.+\\))([+\\-*/])(.+)");
		patternRight = Pattern.compile("(.+)([+\\-*/])(\\(.+)");
		patternDouble = Pattern.compile("(.+\\))([+\\-*/])(\\(.+)");
		bTree = new LinkedRBinaryTree<>();
		operators = new ArrayList<String>();
		operators.add("sin");
		operators.add("cos");
	}

	/**
	 * 
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @author David Sarkozi
	 * @param expression
	 *            Une ligne de l'input formattee en String
	 * @pre -
	 * @post Stocke dans bTree l'arbre correspondant a expression
	 * 
	 */
	// ([^\(\)]+)[+\-*/]([^\(\)]+)
	public void loadExpression(String expression)
	{
		String[] expr = parenthesesRemover(expression);
		String[] exprSplit = null;
		if (expr == null) // 10
		{
			bTree.setElement(expression);
			return;
		}
		else if (expr.length == 1) // (10+x) or (x^3)
		{
			exprSplit = exprSplitter(expr[0]);
			if (exprSplit == null) // x^3
			{
				bTree.setElement(expr[0]);
			}
			else
			// 10+x
			{
				bTree.setElement(expr[1]); // +
				loadExpression(expr[0]); // 10
				loadExpression(expr[2]); // x
			}
		}
		else if (expr.length == 2) // sin or cos
		{
			bTree.setElement(expr[0]); // sin or cos
			loadExpression(expr[1]); // x or (x+2)
		}
	}

	/**
	 * @author David Sarkozi
	 * @pre -
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
	 * @pre -
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

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public FormalExpressionTree derive()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
