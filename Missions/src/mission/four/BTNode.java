package mission.four;

/*
 * @author : Benoit Sluysmans
 */


public class BTNode<E> implements Position<E>{
    private E element;
    private Position<E> left,right,parent;
    
    public BTNode(E element, Position<E> left, Position<E> right, Position<E> parent ){
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
            
    }
    /**
     * Determine s'il y a un enfant a gauche.
     */
    public boolean hasLeft(){
            if(left != null){
                    return true;
            }
            return false;
    }
    /**
     * Determine s'il y a un enfant a droite.
     */
    public boolean hasRight(){
            if(right != null){
                    return true;
            }
            return false;
    }
    /**
     * Retourne true si l'element a des enfants.
     */
    public boolean hasChildren(){
            if(left != null || right != null){
                    return true;
            }
            return false;
    }
    /**
     * Retourne true si l'element a un parent.
     */
    public boolean hasParent(){
            if(parent != null){
                    return true;
            }
            return false;
    }

    public void setElement(E element) {
            this.element = element;
    }

    public Position<E> getLeft() {
            return left;
    }

    public void setLeft(Position<E> left) {
            this.left = left;
    }

    public Position<E> getRight() {
            return right;
    }

    public void setRight(Position<E> right) {
            this.right = right;
    }

    public Position<E> getParent() {
            return parent;
    }

    public void setParent(Position<E> parent) {
            this.parent = parent;
    }

    public E element() {
            return element;
    }
    
}
