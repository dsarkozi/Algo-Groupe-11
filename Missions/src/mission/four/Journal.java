package mission.four;

import java.util.HashMap;

/**
 * Classe representant une revue scientifique.
 * 
 * @author Benoit Sluysmans, Julien De Maeyer
 */
public class Journal implements Comparable<Journal>
{
	/**
	 * Represente le HashMap permettant de stocker les rangs d'une revue
	 */
	private static HashMap<String, Integer> rankMap;
	private static HashMap<Integer, String> fieldMap;
	private static HashMap<String, Integer> indexMap;

	private String[] data;

	public Journal(String[] data)
	{
		this.data = data;
	}

	/**
	 * @meth.author David Sarkozi
	 * @pre -
	 * @post Stocke dans un HashMap les rangs existants dans le fichier a
	 *       analyser, lies a un entier, ce qui permet d'ordonner les objets
	 *       Journal et d'ajouter aisement de nouveaux rangs si necessaire
	 * @see #rankMap
	 */
	protected static void rankMap_init()
	{
		rankMap = new HashMap<String, Integer>();
		rankMap.put("A*", 1);
		rankMap.put("B", 2);
		rankMap.put("C", 3);
		rankMap.put("Not ranked", Integer.MAX_VALUE);
	}

	protected static void fieldMaps_init(String[] fields)
	{
		fieldMap = new HashMap<Integer, String>();
		indexMap = new HashMap<String, Integer>();
		for (int i = 0; i < fields.length; i++)
		{
			fieldMap.put(i, fields[i]);
			indexMap.put(fields[i], i);
		}
	}

	public String getData(String field)
	{
		return this.data[indexMap.get(field)];
	}

	public void setData(String field, String value)
	{
		this.data[indexMap.get(field)] = value;
	}

	/**
	 * @pre -
	 * @post Retoure un String representant le fichier
	 * @return La representation en String de this
	 */
	public String toString()
	{
		String result = "";
		for (int i = 0; i < data.length; i++)
		{
			result += (i == data.length - 1) ? data[i] : data[i] + ",";
		}
		return result;
	}

	/**
	 * @param other
	 *            Un Journal a comparer avec this
	 * @pre -
	 * @post Compare deux revues par rapport a leur rang, retourne un nombre
	 *       negatif si this a un rang inferieur a other, 0 si les deux rangs
	 *       sont identiques, et un nombre positif si le rang de this est plus
	 *       eleve que celui de other
	 * @return Un nombre negatif si this a un rang inferieur a other, 0 si les
	 *         deux rangs sont identiques, et un nombre positif si le rang de
	 *         this est plus eleve que celui de other
	 */
	@Override
	public int compareTo(Journal other)
	{
		return rankMap.get(other.getData("rank"))
				- rankMap.get(this.getData("rank"));
	}

	/**
	 * @param other
	 *            Un Journal a comparer avec this
	 * @pre -
	 * @post Verifie si le rang de deux revues sont egaux
	 * @return true si les deux revues sont egales vis-a-vis de leur rang, false
	 *         sinon
	 */
	public boolean equals(Journal other)
	{
		return this.getData("rank").equals(other.getData("rank"));
	}

}
