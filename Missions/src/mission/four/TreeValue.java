/**
 * 
 */
package mission.four;

/**
 * @author Clémentine
 * Décrit les propriétés spécifiques qu'on veut donner à des objets 
 * qui sont des valeurs stockées dans un node.
 *
 */
public abstract class TreeValue {
	
	/**
	 * Le nombre de champs de l'objet
	 */
	protected int NUM_FIELDS;//TODO serait bien en final
	
	/**
	 * Constructeur
	 */
	public TreeValue(int numFields){
		NUM_FIELDS = numFields;
	}
	
	/**
	 * met à jour le nombre de champs
	 */
	public int numFields(){
		return NUM_FIELDS;
	}
	
	/**
	 * Rend les noms des champs delon leur "index"
	 * @author Clémentine
	 */
	public String getFieldName(int index){
		return null;
	}
	
	/**
	 * Renvoie le numéro qui correspond au champ en paramère
	 */
	public int getFieldIndex(String field){
		return 0;
	}
}
