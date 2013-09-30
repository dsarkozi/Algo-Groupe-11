package mission.one;

import java.util.EmptyStackException;

public class PostStack <E>
{
	private Node top;
	int size;
	
	/**
	 * Constructeur de la clase PostStack
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
	 * @pre _
	 * @post True a ete retourne si la pile etait vide, false sinon
	 */
	public boolean isEmpty()
	{
		return this.size==0;
	}
	
	/**
	 * Retourne la taille de la pile
	 * @pre _
	 * @post La taille de la pile a ete retournee. Si la pile est vide, retourne 0
	 */
	public int getSize()
	{
		return this.size;	
	}
	
	public void push(E object)
	{
		Node newNode = new Node(top, object);
		top = newNode;
		size++;
		
	}
	
	/**
	 * Retourne le premier element de la pile, sans l'en enlever
	 * @pre _
	 * @post L'element stocke au sommet de la pile a ete retourne, ou bien une
	 * exception EmptyStackException a ete lancee si la pile etait vide.
	 */
	public E peek() throws EmptyStackException
	{
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		return top.getObject();
	}
	
	public E pop() throws EmptyStackException
	{
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		E result = top.getObject();
		top = top.getNext();
		size--;
		return result;
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
