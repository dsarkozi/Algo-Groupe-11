package mission.four;

public class Tree {
	
	/**
	 * Benoit Sluysmans
	 * Retourne toute les valeurs de l'arbre dans l'ordre du ieme critère
	 */
	public ArrayList<E> getAllValues(int i)
	{
		if(size==0)
			return null;
		else {
			List all = new LinkedList<E>(); 
			return getHelper(root, all, i);
		}
	}
	public List<E> getHelper(BSTNode<E> current, List all, int i)
	{
		if(current.getLeft()==null && current.getRight()==null)
			all.add(current.getList().getValue(i));
		else{
			if(current.getLeft()!=null)
				getHelper(current.getLeft(), all);

			all.add(current.getList().getValue(i));

			if(current.getRight()!=null)
				getHelper(current.getRight(), all);
		}
		return all;
	}
	
	/**
	 * Benoit Sluysmans
	 * Retourne toute les clés de l'arbre dans l'ordre du ieme critère
	 */
	public ArrayList<E> getAllKeys(int i)
	{
		if(size==0)
			return null;
		else {
			List all = new LinkedList<E>(); 
			return getHelper2(root, all, i);
		}
	}
	
	public List<E> getHelper2(BSTNode<E> current, List all, int i)
	{
		if(current.getLeft()==null && current.getRight()==null)
			all.add(current.getList().getKey(i));
		else{
			if(current.getLeft()!=null)
				getHelper(current.getLeft(), all);

			all.add(current.getList().getKey(i));

			if(current.getRight()!=null)
				getHelper(current.getRight(), all);
		}
		return all;
	}
}
