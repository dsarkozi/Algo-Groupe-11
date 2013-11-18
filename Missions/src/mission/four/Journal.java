package mission.four;

import java.util.HashMap;

/**
 * Classe representant une revue scientifique.
 * 
 * @author Benoit Sluysmans, Julien De Maeyer
 */
public class Journal extends TreeValue implements Comparable<Journal>//Clem add TODO
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
			NUM_FIELDS = 8;
			this.rank = rank;
			this.title = title;
		}

		/**
		 * Methode d'ajout d'attributs optionnels a un objet {@link Journal} en
		 * train d'etre construit.
		 * 
		 * @pre {@code index} est le bon indice du tableau de donnees des
		 *      attributs d'une revue
		 * @post Retourne un {@code builder} contenant un attribut dont la
		 *       valeur a ete correctement affectee a {@code opt}
		 * @param opt
		 *            L'attribut optionnel a ajouter a l'objet
		 * @param index
		 *            L'indice du tableau de donnees des attributs
		 * @return Un {@code builder} contenant l'attribut affecte
		 */
		public JournalBuilder optData(String opt, int index)
		{
			switch (index)
			{
				case 2:
					for1 = opt;
					break;
				case 3:
					for1name = opt;
					break;
				case 4:
					for2 = opt;
					break;
				case 5:
					for2name = opt;
					break;
				case 6:
					for3 = opt;
					break;
				case 7:
					for3name = opt;
					break;
			}
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

	/** (non-Javadoc)
	 * @see mission.four.TreeValue#numFields()
	 * @author : Clem
	 */
	@Override
	public int numFields() {
	
		return super.numFields();
	}

	/**
	 * Renvoie le nom de chma 
	 * (non-Javadoc)
	 * @see mission.four.TreeValue#getFieldName(int)
	 * @author Clémentine
	 */
	@Override
	public String getFieldName(int index) {
		switch(index){
		case 0 : return "rank";
		case 1 : return "title";
		case 2 : return "for1";
		case 3 : return "for1name";
		case 4 : return "for2";
		case 5 : return "for2name";
		case 6 : return "for3";
		case 7 : return "for3name";
		default : return null;//TODO gestion des erreurs
		}
	}

	/**
	 * Renvoi le numéro du champ correspondant au nom de champ
	 *  (non-Javadoc)
	 * @see mission.four.TreeValue#getFieldIndex()
	 * @author Clémentine
	 */
	@Override
	public int getFieldIndex(String field) {
		switch(field){
		case "rank"	: 	return 0;
		case "title" : 	return 1;
		case "for1" : 	return 2;
		case "for1name" : return 3;
		case "for2" : 	return 4;
		case "for2name" : return 5;
		case "for3" : 	return 6;
		case "for3name": return 7;
		default : return -1;//TODO gestion des erreurs
		}
	}

	/**
	 * Accès générique aux champs
	 * @pre
	 * @post
	 * @exceptions
	 * @param i
	 * @return
	 */
	public String getField(int index) {
		switch(index){
		case 0 : return getRank();
		case 1 : return getTitle();
		case 2 : return getFor1();
		case 3 : return getFor1name();
		case 4 : return getFor2();
		case 5 : return getFor2name();
		case 6 : return getFor3();
		case 7 : return getFor3name();
		default : return null;//TODO gestion des erreurs
		}
	}
	
	/**
	 * Mise à jour générique des champs
	 * @pre
	 * @post
	 * @exceptions
	 * @param i
	 * @return
	 */
	public boolean setField(int index, String val) {
		switch(index){
		case 0 :   setRank(val); return true; 
		case 1 :   setTitle(val); return true; 
		case 2 :   setFor1(val); return true; 
		case 3 :   setFor1name(val); return true; 
		case 4 :   setFor2(val); return true; 
		case 5 :   setFor2name(val); return true; 
		case 6 :   setFor3(val); return true; 
		case 7 :   setFor3name(val); return true; 
		default : return false;//TODO gestion des erreurs
		}
	}
	
	
	
	
}
