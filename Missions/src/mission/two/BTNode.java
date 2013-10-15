package mission.two;

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
            
            /**if(left.getClass().equals(this.getClass())){
                    ((BTNode<E>) left).setParent(this);
            }
            if(right.getClass().equals(this.getClass())){
                    ((BTNode<E>) right).setParent(this);
            }**/
    }
    /**
     * Methode permettant de determiner s'il y a un enfant a gauche.
     * @return true s'il y en a un et false dans le cas contraire.
     */
    public boolean hasLeft(){
            if(left != null){
                    return true;
            }
            return false;
    }
    /**
     * Methode permettant de determiner s'il y a un enfant a droite.
     * @return true s'il y en a un et false dans le cas contraire.
     */
    public boolean hasRight(){
            if(right != null){
                    return true;
            }
            return false;
    }
    /**
     * Methode qui renvoie true si l'element courant a des enfants.
     */
    public boolean hasChildren(){
            if(left != null || right != null){
                    return true;
            }
            return false;
    }
    /**
     * Methode qui renvoie true si l'element courant a un parent.
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
    /**
     * Return l'élément present dans le Node.
     */
    public E element() {
            return element;
    }
    
}
