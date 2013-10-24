/*
 * Author: Benoit Sluysmans
 */

package mission.three;

import java.util.Comparator;

public class Revue implements Comparator<Revue> {

	public String rank;
	public String title;
	public int for1;
	public int for2;
	public String for2name;
	public int for3;
	public String for3name;
	public String for1name;
	
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the for1
	 */
	public int getFor1() {
		return for1;
	}
	/**
	 * @param for1 the for1 to set
	 */
	public void setFor1(int for1) {
		this.for1 = for1;
	}
	/**
	 * @return the for1name
	 */
	public String getFor1name() {
		return for1name;
	}
	/**
	 * @param for1name the for1name to set
	 */
	public void setFor1name(String for1name) {
		this.for1name = for1name;
	}
	/**
	 * @return the for2
	 */
	public int getFor2() {
		return for2;
	}
	/**
	 * @param for2 the for2 to set
	 */
	public void setFor2(int for2) {
		this.for2 = for2;
	}
	/**
	 * @return the for2name
	 */
	public String getFor2name() {
		return for2name;
	}
	/**
	 * @param for2name the for2name to set
	 */
	public void setFor2name(String for2name) {
		this.for2name = for2name;
	}
	/**
	 * @return the for3
	 */
	public int getFor3() {
		return for3;
	}
	/**
	 * @param for3 the for3 to set
	 */
	public void setFor3(int for3) {
		this.for3 = for3;
	}
	/**
	 * @return the for3name
	 */
	public String getFor3name() {
		return for3name;
	}
	/**
	 * @param for3name the for3name to set
	 */
	public void setFor3name(String for3name) {
		this.for3name = for3name;
	}
	
	/**
	 * @post Retoure un String representant le fichier
	 */
	public String toString(Revue r) {
		return rank+","+title+","+for1+","+for1name+","+for2+","+for2name+","+for3+","+for3name;
	}
	
	/**
	 * @override
	 * Compare deux revues par rapport a leur rang
	 */
	public int compare(Revue a, Revue b) {
		int ra=0, rb=0;
		if(a.rank == "A*") ra = 3;
		else if(a.rank == "A") ra = 2;
		else if(a.rank == "B") ra = 1;
		
		if(b.rank == "A*") rb = 3;
		else if(b.rank == "A") rb = 2;
		else if(b.rank == "B") rb = 1;
		
		return ra-rb;
	}
	
	/**
	 * @override
	 * Verifie si le rang de deux revues sont egaux
	 */
	public boolean equals(Revue a) {
		return this.rank.equals(a.rank);
	}
}
