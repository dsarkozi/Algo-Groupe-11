package mission.one;

import java.util.EmptyStackException;

public class PostStack<E>
{
	private Node top;
	int size;

	/**
	 * Constructeur de la clase PostStack
	 * 
	 * @pre _
	 * @post Une nouvelle pile vide a ete cree
	 */
	public PostStack()
	{
		top = null;
		size = 0;
	}

	/**
	 * Evalue si la pile est vide ou pas
	 * 
	 * @pre _
	 * @post True a ete retourne si la pile etait vide, false sinon
	 */
	public boolean isEmpty()
	{
		return this.size == 0;
	}

	/**
	 * Retourne la taille de la pile
	 * 
	 * @pre _
	 * @post La taille de la pile a ete retournee. Si la pile est vide, retourne
	 *       0
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	* Sert a ajouter un nouvel element a la stack.
	* 
	* @pre _
	* @post Rajoute un element au sommet de la stack.
	* 	Increment le taille de la stack de 1 apres le push.
	*/
	public void push(E object)
	{
		Node newNode = new Node(top, object);
		top = newNode;
		size++;

	}

	/**
	 * Retourne le premier element de la pile, sans l'en enlever
	 * 
	 * @pre _
	 * @post L'element stocke au sommet de la pile a ete retourne, ou bien une
	 *       exception EmptyStackException a ete lancee si la pile etait vide.
	 */
	public E peek()
	{
		if (this.isEmpty())
		{
			throw new EmptyStackException();
		}
		return top.getObject();
	}
	
	/**
	* Cette methode sert a pop le top element et a le return
	* 
	* @pre _
	* @post pop le top element de le stack et le retourn.
	* 	Une execption EmptyStackException est lancee si la stack est vide.
	*/
	public E pop()
	{
		if (this.isEmpty())
		{
			throw new EmptyStackException();
		}
		E result = top.getObject();
		top = top.getNext();
		size--;
		return result;
	}
	
	/**
	* Sert a retourner l'element au sommet de la stack sans le retirer.
	* 
	* @pre _
	* @post Le top element est retourner.
	* 	Si la stack etait vide, une exception EmptyStackException est lancee.
	*/
	public E get(int index)
	{
		if (this.isEmpty()) throw new EmptyStackException();
		Node current = top;
		for (int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		return current.getObject();
	}
	
	/**
	* Sert a vider entièrement la stack.
	* 
	* * @pre _
 	* @post Tant que la stack n'est pas vide, on pop le top element.
	*/
	public void emptyStack()
	{
		while (!this.isEmpty())
		{
			this.pop();
		}
	}

	private class Node
	{
		private Node next;
		private E obj;

		public Node(Node next, E obj)
		{
			this.next = next;
			this.obj = obj;
		}

		public Node getNext()
		{
			return next;
		}

		public E getObject()
		{
			return obj;
		}
	}

}
