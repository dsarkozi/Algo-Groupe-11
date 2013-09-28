package mission.one;

import java.util.Stack;

public class PostScript {

	/**
	 * @pre
	 * @post imprime le contenu de toute la pile s;
	 */
	public void pstack(Stack s) {
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i));
		}
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la stack par leur somme
	 */
	public void add(Stack s) {
		double temp = (double) s.pop() + (double) s.pop();
		s.push(temp);
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la stack par leur différence
	 */
	public void sub(Stack s) {
		double temp = (double) s.pop() - (double) s.pop();
		s.push(temp);
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la stack par leur
	 *       multiplication
	 */
	public void mul(Stack s) {
		double temp = (double) s.pop() * (double) s.pop();
		s.push(temp);
	}

	/**
	 * @pre
	 * @post remplace les deux premiers elements de la stack par leur division
	 */
	public void div(Stack s) {
		double temp = (double) s.pop() / (double) s.pop();
		s.push(temp);
	}

	/**
	 * @pre: argument ligne non null
	 * @post: decode et execute les instructions presentes dans le String ligne.
	 */
	public void decode(String ligne) {
		assert ligne != null : "argument String ligne == null.";
		String Elem[] = ligne.split(" ");
		Stack DStack = new Stack(); // TODO , initialiser comme dans la def.
		for (int i = 0; i < Elem.length; i++) {
			switch (Elem[i]) {
			case "pstack":
				pstack(DStack);
				break;
			case "add":
				add(DStack);
				break;
			case "sub":
				sub(DStack);
				break;
			case "mul":
				mul(DStack);
				break;
			case "div":
				div(DStack);
				break;
			case "dup":
				dup(DStack);
				break;
			case "exch":
				exch(DStack);
				break;
			case "eq":
				eq(DStack);
				break;
			case "ne":
				ne(DStack);
				break;
			case "def":
				def(DStack);
				break;
			case "pop":
				pop(DStack);
				break;
			default:
				DStack.push(Elem[i]);
				break;
			}
		}
	}
}
