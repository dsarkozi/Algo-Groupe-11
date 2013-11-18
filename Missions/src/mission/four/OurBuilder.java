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
 *
 */
public class OurBuilder<T> implements Builder<T> {
	
	/*default information*/
	private Object rawData;
	private List<Object> values;
	
	/**
	 * Construire une structure T à partir d'une liste
	 */
	public OurBuilder(List values) {
		
	}
	
	/**
	 * Construire une structure T sans informations à y stocker
	 */
	public OurBuilder() {
		// TODO Auto-generated constructor stub
		
		//par défaut mettra une seule référence dans chaque node
	}
	
	@Override
	public T build() {
		// TODO Auto-generated method stub
		
		//vérifier combien de champs il ya dans l'objet et construire les nodes
		//pour instancier les nodes, instancier d'abord les tabeaux de champs puis
		//instancier des OurNode de ArrayLists
		return null;
	}

}
