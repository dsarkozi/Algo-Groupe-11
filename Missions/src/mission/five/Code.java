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
	 * Cree un nouvel objet code contenant kes bits de 'bits'
	 */
	public Code(ArrayList<Boolean> bits)
	{
		this.bits = bits;
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
	
	/**
	 * Retourne la taille du code binaire
	 * @pre _
	 * @post une taile >=0 est retournee
	 */
	public int getSize()
	{
		return bits.size();
	}
	
	/**
	 * Retourne la representation textuelle du code binaire
	 * @pre _
	 * @post un String contenant des '0' et des '1' est retourne
	 */
	public String toString()
	{
		String result = new String();
		for(boolean bit : bits)
		{
			if(bit)
				result += '1';
			else
				result += '0';
		}
		return result;
	}
}
