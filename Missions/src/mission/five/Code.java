package mission.five;

import java.util.ArrayList;

/** 
 * Une classe representant les codes binaires associes aux caracteres du 
 * @author Loic Lacomblez
 *
 */
public class Code
{
	private ArrayList<Boolean> bits;
	
	/**
	 * Cree un nouvel objet code vide
	 */
	public Code()
	{
		bits = new ArrayList<Boolean>();
	}
	
	/**
	 * Configure le (i+1)-eme bit du code a la valeur 'bit'
	 * @pre _
	 * @post le code a une longueur >= index+1, dont le (i+1)-eme bit a la valeur
	 * 'bit' 
	 */
	public void setBit(int index, boolean bit)
	{
		if(index + 1 > bits.size())
			bits.ensureCapacity(index + 1);
		bits.add(index, bit);
	}
	
	/**
	 * Retourne le code binaire represente par l'objet
	 * @pre _
	 * @post _
	 */
	public ArrayList<Boolean> getCode()
	{
		return bits;
	}
}
