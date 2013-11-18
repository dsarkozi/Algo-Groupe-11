package mission.four;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mission.two.BTNode;
import mission.two.Position;

public class Tree<E> {
	
	/**
	 * Benoit Sluysmans
	 * Retourne toute les valeurs de l'arbre dans l'ordre du ieme crit�re
	 * 
	 */
	protected BTNode<E> root;
    protected int size;
    
    /* Constructeur de base*/
    public Tree(){
    	this.root = null;
    	this.size=0;
    }
    
    public Tree(BTNode<E> root){
    	this.root = root;
    	this.size = 1;
    }
    
    /* ADD  METHODES D AJOUT / SUPPRESSION HERE */
    
    
    
    /* METHODES DE RECHERCHE */
    
	public ArrayList<E> getAllValues(int i)
	{
		if(size==0)
			return null;
		else {
			LinkedList<E> all = new LinkedList<E>(); 
			return (ArrayList<E>) getHelper(root, all, i);
		}
	}
	public List<E> getHelper(BTNode<E> current, List all, int i)
	{
		if(current.getLeft()==null && current.getRight()==null)
			all.add(current.getList().getValue(i));
		else{
			if(current.getLeft()!=null)
				getHelper(current.getLeft(), all, i);

			all.add(current.getList().getValue(i),i);

			if(current.getRight()!=null)
				getHelper(current.getRight(), all,i);
		}
		return all;
	}
	
	/**
	 * Benoit Sluysmans
	 * Retourne toute les cl�s de l'arbre dans l'ordre du ieme crit�re
	 */
	public ArrayList<E> getAllKeys(int i)
	{
		if(size==0)
			return null;
		else {
			LinkedList<E> all = new LinkedList<E>(); 
			return (ArrayList<E>) getHelper2(root, all, i);
		}
	}
	
	public List<E> getHelper2(BTNode<E> current, List all, int i)
	{
		if(current.getLeft()==null && current.getRight()==null)
			all.add(current.getList().getKey(i));
		else{
			if(current.getLeft()!=null)
				getHelper(current.getLeft(), all,i);

			all.add(current.getList().getKey(i));

			if(current.getRight()!=null)
				getHelper(current.getRight(), all,i);
		}
		return all;
	}
	
	/**
	 * @author Clémentine
	 * Classe qui représentera un noeud de l'arbre
	 * 
	 */
	private class OurNode<E> extends BTNode<E>{
		
		/**
		 * Contructeur
		 */
		public OurNode(E element, Position<E> left, Position<E> right, Position<E> parent){
			super(element, left, right, parent);
		}
		
		/**
		 * Retourne la clé stockée à l'index i du tableau des membres du node
		 */
		public int getValue(int i){
			return ((ArrayList<Integer>)element()).get(i);
					
		}
		
		/**
		 * Remplace la clé stockée à l'index i du tableau des membres du node
		 * par la clé newKey et renvoie la clé précédente;
		 */
		public void setValue(int i, int newKey){
			((ArrayList<Integer>)element()).set(i, newKey);
					
		}
	}
	
	
}
