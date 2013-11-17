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

	private String rank;
	private String title;
	private String for1;
	private String for1name;
	private String for2;
	private String for2name;
	private String for3;
	private String for3name;

	/**
	 * Classe statique implementant l'interface {@link Builder}. Elle permet
	 * d'hydrater les attributs de {@link Journal} independamment de l'ordre et
	 * en distinguant les attributs obligatoires des optionnels.
	 * 
	 * @author David Sarkozi
	 * @see Builder
	 */

	public static class JournalBuilder implements Builder<Journal>
	{
		private String rank;
		private String title;
		private String for1 = null;
		private String for1name = null;
		private String for2 = null;
		private String for2name = null;
		private String for3 = null;
		private String for3name = null;

		/**
		 * Constructeur des attributs obligatoires.
		 * 
		 * @param rank
		 *            Le rang de la revue
		 * @param title
		 *            Le titre de la revue
		 * @see Journal#setRank(String)
		 * @see Journal#setTitle(String)
		 */
		public JournalBuilder(String rank, String title)
		{
			this.rank = rank;
			this.title = title;
		}

		/**
		 * 
		 * @param for1
		 * @return this
		 * @see Journal#setFor1(String)
		 */
		public JournalBuilder for1(String for1)
		{
			this.for1 = for1;
			return this;
		}

		/**
		 * 
		 * @param for1name
		 * @return this
		 * @see Journal#setFor1name(String)
		 */
		public JournalBuilder for1name(String for1name)
		{
			this.for1name = for1name;
			return this;
		}

		/**
		 * 
		 * @param for2
		 * @return this
		 * @see Journal#setFor2(String)
		 */
		public JournalBuilder for2(String for2)
		{
			this.for2 = for2;
			return this;
		}

		/**
		 * 
		 * @param for2name
		 * @return this
		 * @see Journal#setFor2name(String)
		 */
		public JournalBuilder for2name(String for2name)
		{
			this.for2name = for2name;
			return this;
		}

		/**
		 * 
		 * @param for3
		 * @return this
		 * @see Journal#setFor3(String)
		 */
		public JournalBuilder for3(String for3)
		{
			this.for3 = for3;
			return this;
		}

		/**
		 * 
		 * @param for3name
		 * @return this
		 * @see Journal#setFor3name(String)
		 */
		public JournalBuilder for3name(String for3name)
		{
			this.for3name = for3name;
			return this;
		}

		/**
		 * Construit une nouvelle instance de {@link Journal}.
		 * 
		 * @return Une instance de {@link Journal} hydratee par les attributs du
		 *         {@code builder}
		 */
		@Override
		public Journal build()
		{
			return new Journal(this);
		}

	}

	/**
	 * Constructeur prive obligeant le developpeur a passer par
	 * {@link JournalBuilder} pour construire une instance de {@link Journal}.
	 * 
	 * @meth.author David Sarkozi
	 * @param builder
	 *            Instance de {@link JournalBuilder} contenant les attributs du
	 *            nouvel objet {@link Journal}
	 * @pre -
	 * @post Cree un nouvel objet {@link Journal} avec les attributs du
	 *       {@code builder}
	 */
	private Journal(JournalBuilder builder)
	{
		this.rank = builder.rank;
		this.title = builder.title;
		this.for1 = builder.for1;
		this.for1name = builder.for1name;
		this.for2 = builder.for2;
		this.for2name = builder.for2name;
		this.for3 = builder.for3;
		this.for3name = builder.for3name;
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
