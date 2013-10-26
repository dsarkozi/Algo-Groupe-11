package mission.three;

import java.util.HashMap;

/**
 * @author Benoit Sluysmans
 */
public class Journal implements Comparable<Journal>
{
	private static HashMap<String, Integer> rankMap;

	private String rank;
	private String title;
	private int for1;
	private String for1name;
	private int for2;
	private String for2name;
	private int for3;
	private String for3name;

	public Journal(String[] data)
	{
		int i = 0;
		rank = data[i++];
		title = data[i++];
		if (data[i].equals(""))
		{
			for1 = 0;
			i++;
		}
		else for1 = Integer.parseInt(data[i++]);
		for1name = data[i++];
		if (data[i].equals(""))
		{
			for2 = 0;
			i++;
		}
		else for2 = Integer.parseInt(data[i++]);
		for2name = data[i++];
		if (data[i].equals(""))
		{
			for3 = 0;
			i++;
		}
		else for3 = Integer.parseInt(data[i++]);
		for3name = data[i++];
	}

	protected static void rankMap_init()
	{
		rankMap = new HashMap<>();
		rankMap.put("A*", 1);
		rankMap.put("B", 2);
		rankMap.put("C", 3);
		rankMap.put("Not ranked", Integer.MAX_VALUE);
	}

	/**
	 * @return the rank
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the for1
	 */
	public int getFor1()
	{
		return for1;
	}

	/**
	 * @param for1
	 *            the for1 to set
	 */
	public void setFor1(int for1)
	{
		this.for1 = for1;
	}

	/**
	 * @return the for1name
	 */
	public String getFor1name()
	{
		return for1name;
	}

	/**
	 * @param for1name
	 *            the for1name to set
	 */
	public void setFor1name(String for1name)
	{
		this.for1name = for1name;
	}

	/**
	 * @return the for2
	 */
	public int getFor2()
	{
		return for2;
	}

	/**
	 * @param for2
	 *            the for2 to set
	 */
	public void setFor2(int for2)
	{
		this.for2 = for2;
	}

	/**
	 * @return the for2name
	 */
	public String getFor2name()
	{
		return for2name;
	}

	/**
	 * @param for2name
	 *            the for2name to set
	 */
	public void setFor2name(String for2name)
	{
		this.for2name = for2name;
	}

	/**
	 * @return the for3
	 */
	public int getFor3()
	{
		return for3;
	}

	/**
	 * @param for3
	 *            the for3 to set
	 */
	public void setFor3(int for3)
	{
		this.for3 = for3;
	}

	/**
	 * @return the for3name
	 */
	public String getFor3name()
	{
		return for3name;
	}

	/**
	 * @param for3name
	 *            the for3name to set
	 */
	public void setFor3name(String for3name)
	{
		this.for3name = for3name;
	}

	/**
	 * @post Retoure un String representant le fichier
	 */
	public String toString(Journal r)
	{
		return rank + "," + title + "," + for1 + "," + for1name + "," + for2
				+ "," + for2name + "," + for3 + "," + for3name;
	}

	/**
	 * @post Compare deux revues par rapport a leur rang
	 */
	@Override
	public int compareTo(Journal other)
	{
		return rankMap.get(other.rank) - rankMap.get(this.rank);
	}

	/**
	 * @post Verifie si le rang de deux revues sont egaux
	 */
	public boolean equals(Journal other)
	{
		return this.rank.equals(other.rank);
	}
}
