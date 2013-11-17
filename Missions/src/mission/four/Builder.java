package mission.four;

/**
 * Interface implementant une variante du <i>Builder pattern</i>
 * 
 * @author David Sarkozi
 * 
 */
public interface Builder<T>
{
	/**
	 * @pre -
	 * @post Retourne une instance de {@code T} hydratee par le {@code builder}
	 * @return Une instance de {@code T} hydratee 
	 */
	T build();
}
