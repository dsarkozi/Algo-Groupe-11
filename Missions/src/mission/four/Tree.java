package mission.four;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mission.two.BTNode;
import mission.two.Position;

public class Tree<E>
{
	protected OurNode<ArrayList<Integer>> tableRoot;
    protected int size;
    /*sélection du champ de l'Objet stoque qui est maintenant valide pour put, get et remove*/
    protected int currentPutKey ;
    protected int currentGetKey;
    protected int currentRemoveKey;
    
    /* Constructeur de base*/
    public Tree(){

    	currentPutKey = -1;
    	currentGetKey = -1;
    	currentRemoveKey = -1;
    	this.tableRoot = null;
    	this.size=0;
    }
    
    /**
     * Constructeur2
     * cree le tableau qui sera membre du node tableRoot
     * pour cela demande au journal combien de champs il a.
     * on presume que le tableau est intitialisé à 0=référence de la racine
     * quelquesoit le critère de recherhe, le 1er élément est la racine.
     * @param tableRoot
     */
    public Tree(E root ){//Clementine a change le constructeur de Tree
    	
		ArrayList<Integer> t = new ArrayList<Integer>(((TreeValue)root).numFields());
    	this.tableRoot = new OurNode<ArrayList<Integer>>(t, null, null, null);
    	this.size = 1;
    }
    	
    /* ADD  METHODES D AJOUT / SUPPRESSION HERE */
    
    
    
    /* METHODES DE RECHERCHE */
    
    /**
	 * Benoit Sluysmans Retourne toute les valeurs de l'arbre dans l'ordre du
	 * ieme critere
	 * 
	 */
	public LinkedList<E> getAllValues(int i)
	{
		if(size==0)
			return null;
		else {
			LinkedList<E> all = new LinkedList<E>(); 
			return (LinkedList<E>) getHelper(tableRoot, (LinkedList<Integer>) all, i);

		}
	}

	private LinkedList<Integer> getHelper(OurNode<ArrayList<Integer>> current, LinkedList<Integer> all, int i)
	{
		if (current.getLeft() == null && current.getRight() == null) all.add(current.getValue(i));
		else
		{
			if (current.getLeft() != null) getHelper((OurNode<ArrayList<Integer>>) current.getLeft(), all, i);

			all.add(current.getValue(i));

			if (current.getRight() != null) getHelper((OurNode<ArrayList<Integer>>) current.getRight(), all,
					i);
		}
		return all;
	}

	/**
	 * Benoit Sluysmans
	 * Retourne toute les cles de l'arbre dans l'ordre du ieme critere
	 */
	public LinkedList<E> getAllKeys(int i)
	{
		if(size==0)
			return null;
		else {
			LinkedList<E> all = new LinkedList<E>(); 
			return (LinkedList<E>) getHelper2(tableRoot, (LinkedList<Integer>) all, i);

		}
	}

	private LinkedList<Integer> getHelper2(OurNode<ArrayList<Integer>> current, LinkedList<Integer> all, int i)
	{
		if (current.getLeft() == null && current.getRight() == null) all.add(current.getKey(i));
		else
		{
			if (current.getLeft() != null) getHelper((OurNode<ArrayList<Integer>>) current.getLeft(), all, i);

			all.add(current.getKey(i));

			if (current.getRight() != null) getHelper((OurNode<ArrayList<Integer>>) current.getRight(), all,
					i);
		}
		return all;
	}
	

	/**
	 * @author Clémentine
	 * @return the currentPutKey
	 */
	public int getCurrentPutKey() {
		return currentPutKey;
	}

	/**
	 * @author Clémentine
	 * @param currentPutKey the currentPutKey to set
	 */
	public boolean setCurrentPutKey(int i) {
		if(i < tableRoot.numFields( )){
		this.currentPutKey = i;
		return true;
		}
		return false;
	}

	/**
	 * @author Clémentine
	 * @return the currentGetKey
	 */
	public int getCurrentGetKey() {
		return currentGetKey;
	}

	/**
	 * @author Clémentine
	 * @param currentGetKey the currentGetKey to set
	 */
	public boolean setCurrentGetKey(int i) {
		if(i < tableRoot.numFields( )){
			this.currentGetKey = i;
			return true;
		}
		return false;
		
	}

	/**
	 * @author Clémentine
	 * @return the currentRemoveKey
	 */
	public int getCurrentRemoveKey() {
		return currentRemoveKey;
	}

	/**
	 * @author Clémentine
	 * @param currentRemoveKey the currentRemoveKey to set
	 */
	public boolean setCurrentRemoveKey(int i) {
		if(i < tableRoot.numFields( )){
			this.currentRemoveKey = i;
			return true;
		}
		return false;
		
	}

 	/**
	 * Retourne la taille de l'arbre.
	 * 
	 * @meth.author Alaaedine Chatri
	 *            
	 * @return La taille de l'arbre.
	 */
    	public int sizeOfTree(){
        	return size;
        }
        
        /**
     * Méthode qui vérifie si l'arbre est vide.
     * 
     * @meth.author Alaaedine Chatri
     * 
     * @return Un booléen true si l'arbre est vide, false sinon.
     */
    public boolean isEmpty(){
            return size==0;
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
		/**
		 * Retourne le nombre de cases du tableau à l'intérieur du OurNode
		 * @author Clémentine
		 * @pre
		 * @post
		 * @exceptions
		 * @return
		 */
		public int numFields(){
			return ((ArrayList<Integer>)element()).size();
		}
		
		
	}
	
  
}
