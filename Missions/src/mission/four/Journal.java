package mission.four;

import java.util.HashMap;

/**
 * Classe representant une revue scientifique
 * 
 * @author Benoit Sluysmans, Julien De Maeyer
 */
public class Journal implements Comparable<Journal>
{
	/**
	 * Represente le HashMap permettant de stocker les rangs d'une revue
	 */
	private static HashMap<String, Integer> rankMap;

	private String rank = null;
	private String title = null;
	private String for1 = null;
	private String for1name = null;
	private String for2 = null;
	private String for2name = null;
	private String for3 = null;
	private String for3name = null;

	/**
	 * Constructeur affectant les donnees de data a un nouvel objet Journal
	 * 
	 * @param data
	 *            Les donnees de la revue a stocker
	 * @pre data correspond a des donnees d'une revue du fichier d'entree
	 * @post Stocke les donnees de data dans un nouvel objet Journal
	 */
	public Journal(String[] data)
	{
		int i = 0;
		if (data.length <= i) return;
		rank = data[i++];
		if (data.length <= i) return;
		title = data[i++];
		if (data.length <= i) return;
		for1 = data[i++];
		if (data.length <= i) return;
		for1name = data[i++];
		if (data.length <= i) return;
		for2 = data[i++];
		if (data.length <= i) return;
		for2name = data[i++];
		if (data.length <= i) return;
		for3 = data[i++];
		if (data.length <= i) return;
		for3name = data[i++];
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

	/**
	 * @pre -
	 * @post Retourne le rang de this
	 * @return Le rang de this
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * @param rank
	 *            Le rang a affecter
	 * @pre rank est un rang valide, present dans rankMap
	 * @post Stocke le rang dans l'attribut de this
	 */
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	/**
	 * @pre -
	 * @post Retourne le titre de this
	 * @return Le titre de this
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *            Le titre a affecter
	 * @pre title est un titre valide, qui est entre guillemets si celui-ci
	 *      contient des virgules
	 * @post Stocke le titre dans l'attribut de this
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @pre -
	 * @post Retourne le FoR1 de this
	 * @return Le FoR1 de this
	 */
	public String getFor1()
	{
		return for1;
	}

	/**
	 * @param for1
	 *            Le FoR1 a affecter
	 * @pre -
	 * @post Stocke for1 dans l'attribut de this
	 */
	public void setFor1(String for1)
	{
		this.for1 = for1;
	}

	/**
	 * @pre -
	 * @post Retourne le nom du FoR1 de this
	 * @return Le nom du FoR1 de this
	 */
	public String getFor1name()
	{
		return for1name;
	}

	/**
	 * @param for1name
	 *            Le nom du FoR1 a affecter
	 * @pre -
	 * @post Stocke for1name dans l'attribut de this
	 */
	public void setFor1name(String for1name)
	{
		this.for1name = for1name;
	}

	/**
	 * @pre -
	 * @post Retourne le FoR2 de this
	 * @return Le FoR2 de this
	 */
	public String getFor2()
	{
		return for2;
	}

	/**
	 * @param for2
	 *            Le FoR2 a affecter
	 * @pre -
	 * @post Stocke for2 dans l'attribut de this
	 */
	public void setFor2(String for2)
	{
		this.for2 = for2;
	}

	/**
	 * @pre -
	 * @post Retourne le nom du FoR2 de this
	 * @return Le nom du FoR2 de this
	 */
	public String getFor2name()
	{
		return for2name;
	}

	/**
	 * @param for2name
	 *            Le nom du FoR2 a affecter
	 * @pre -
	 * @post Stocke for2name dans l'attribut de this
	 */
	public void setFor2name(String for2name)
	{
		this.for2name = for2name;
	}

	/**
	 * @pre -
	 * @post Retourne le FoR3 de this
	 * @return Le FoR3 de this
	 */
	public String getFor3()
	{
		return for3;
	}

	/**
	 * @param for3
	 *            Le FoR3 a affecter
	 * @pre -
	 * @post Stocke for3 dans l'attribut de this
	 */
	public void setFor3(String for3)
	{
		this.for3 = for3;
	}

	/**
	 * @pre -
	 * @post Retourne le nom du FoR3 de this
	 * @return Le nom du FoR3 de this
	 */
	public String getFor3name()
	{
		return for3name;
	}

	/**
	 * @param for3name
	 *            Le nom du FoR3 a affecter
	 * @pre -
	 * @post Stocke for3name dans l'attribut de this
	 */
	public void setFor3name(String for3name)
	{
		this.for3name = for3name;
	}

	/**
	 * @pre -
	 * @post Retoure un String representant le fichier
	 * @return La representation en String de this
	 */
	public String toString()
	{
		return rank + "," + title + "," + for1 + "," + for1name + "," + for2
				+ "," + for2name + "," + for3 + "," + for3name;
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
		return rankMap.get(other.rank) - rankMap.get(this.rank);
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
		return this.rank.equals(other.rank);
	}
}
