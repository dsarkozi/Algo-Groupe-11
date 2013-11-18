/**
 * 
 */
package mission.four;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Clémentine
 * @param <T>
 * Decrit une Classe qui construit un Tree à partir de Journals
 *
 */
public class OurBuilder {
	
	/*the information*/
	private ArrayList<Journal> values;
	private Tree<Journal> tree;
	private final int FUTURE_ROOT_IND = 0;
	
	/**
	 * Construire une structure T à partir d'une liste
	 */
	public OurBuilder(ArrayList<Journal> values) {

		this.values = values;
	}
	/**
	 * Cree un Tree destine a contenir des Journals
	 * @pre
	 * @post
	 * @exceptions
	 * @return
	 */
	public Tree<Journal> build() {

		tree = new Tree<Journal>(this.values.get(FUTURE_ROOT_IND));
		//pour chaque journal
				for(int i = 0; i <values.size(); i++){
					//l'introduire au bon endroit selon chaque champ
					
					
					
				}
		return tree;
	}

}
