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
	protected BTNode<ArrayList<Integer>> root;
    protected int size;
    
    /* Constructeur de base*/
    public Tree(){
    	this.root = null;
    	this.size=0;
    }
    
    /**
     * Constructeur1
     * cree le tableau qui sera membre du node root
     * pour cela demander au journal combien de champs il a
     * on presume que le tableau est intitialisé à 0=référence de la racine
     * @param root
     */
    public Tree(E root ){//Clementine a change le constructeur de Tree
    	
		ArrayList<Integer> t = new ArrayList<Integer>(((TreeValue)root).numFields());
    	this.root = new OurNode<ArrayList<Integer>>(t, null, null, null);
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
	 * Classe qui représentera un noeud de l'arbre.
	 * Cette classe sait que le paramètre M est un ArrayList<Interger>
	 * 
	 */
	private class OurNode<M> extends BTNode<M>{
		
		/**
		 * Contructeur
		 */
		public OurNode(M element, Position<M> left, Position<M> right, Position<M> parent){
		
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
		public int setValue(int i, int newKey){
			int oldKey = ((ArrayList<Integer>)element()).get(i);
			((ArrayList<Integer>)element()).set(i, newKey);
			return oldKey;
					
		}
	}
	
	
}
