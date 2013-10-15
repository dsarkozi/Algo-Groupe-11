package mission.two;

/*
 * @author : Benoit Sluysmans
 */

public class LinkedRBinaryTree<E> implements RBinaryTree<E> {
    
	protected BTNode<E> root;
    protected int size;

    public LinkedRBinaryTree() {
            root = null;
            size = 0;
    }
    
    public LinkedRBinaryTree(BTNode<E> e) {
            root = e;
            size = 1;
    }

    @Override
    public boolean isEmpty() {
            return (root == null);
    }

    @Override
    public int size() {
            return size;    
    }

    @Override
    public Position<E> root() {
            return root;
    }

    @Override
    public boolean isLeaf() {
            return (root.getLeft() == null && root.getRight() == null);
    }

    @Override
    public RBinaryTree<E> leftTree() {
            return new LinkedRBinaryTree<E>((BTNode<E>) root.getLeft());
    }

    @Override
    public RBinaryTree<E> rightTree() {
            return new LinkedRBinaryTree<E>((BTNode<E>) root.getRight());
    }

    @Override
    public void setElement(E o) {
            root.setElement(o);
    }

    @Override
    public void setLeft(RBinaryTree<E> tree) {
            root.setLeft((BTNode<E>) tree.root());
            size++;
    }

    @Override
    public void setRight(RBinaryTree<E> tree) {
            root.setRight((BTNode<E>) tree.root());
            size++;
    }

    @Override
    public Iterable<Position<E>> positions() {
    	/*PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		  	if(!isEmpty()) inorderPositions(this,positions);
		  	return positions;*/
    	return null;
    }
    
    /*public void inorderPositions(LinkedRBinaryTree<E> tree, PositionList<Position<E>> pos){
		  	if(tree.leftTree() != null) inorderPositions(tree.leftTree(), pos);
		  	pos.addLast(tree.root());
		  	if(tree.rightTree() != null) inorderPositions(tree.rightTree(), pos);
	  	}*/

}
