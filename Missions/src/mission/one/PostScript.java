package mission.one;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class PostScript {

	private ArrayList<UserValue> map;
	
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
	 * 
	 * @post Teste si les deux premiers elements de la stack ne sont pas egaux
	 */
	public void ne(Stack s){
		if(s.size()>=2){
			boolean temp =s.pop()!=s.pop();
			s.push(temp);
		}
	}
	
	/**
	 * 
	 * @post Teste si les deux premiers elements de la pile sont egaux
	 */
	public void eq(Stack s){
		if(s.size()>=2){
			boolean temp =s.pop()==s.pop();
			s.push(temp);
		}
	}
	
	/**
	 * @pre 
	 * @post Echange la place des deux elements top de la stack
	 */
	public void exch(Stack s) {
		if(s.size()>=2){
			String temp1=(String) s.pop();
			String temp2=(String) s.pop();
			s.push(temp1);
			s.push(temp2);
		}
	}
	
	/**
	 * 
	 * @post push une copie du top element
	 */
	public void dup(Stack s) {
		if(s.isEmpty()!=true){
			String temp= (String) s.peek();
			s.push(temp);
		}
	}
	
	/**
	 * 
	 * @post pop le top element
	 */
	public String pop(Stack s) {
		String temp="stackvide";
		if(s.isEmpty()!=true){
			temp=(String) s.pop();
		}
		return temp;
	}
	
	/**
	 * @pre The key to be defined and his value are in the stack s
	 * @post Defines a symbol from the key and his value
	 */
	public void def(Stack s) throws EmptyStackException
	{
		double value = (double) s.pop();
		String key = (String) s.pop();
		if (!key.startsWith("/")) return;
		map.add(new UserValue(key.substring(1), value));
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
				for (UserValue uv : map)
				{
					if (uv.getKey() == Elem[i])
					{
						Elem[i] = Double.toString(uv.getValue());
						break;
					}
				}
				DStack.push(Elem[i]);
				break;
			}
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
		 * @param key the key to set
		 */
		public void setKey(String key)
		{
			this.key = key;
		}

		/**
		 * @return the value
		 */
		public double getValue()
		{
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(int value)
		{
			this.value = value;
		}

	}
}
