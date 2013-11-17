package mission.four;

import java.util.HashMap;
import java.util.TreeMap;


/**
 * 
 * @author Nicolas Marchand
 *
 */
public class DicoTest {

	public static void main(String[] args) {
		

		HashMap<Integer,Integer> h= new HashMap<Integer,Integer>(100000000);
		TreeMap<Integer,Integer> t= new TreeMap<Integer,Integer>();
		long start; 
		long duree;
		long sumh1 = 0 ,sumh2 = 0,sumh3 = 0,sumh4 = 0,sumh5 = 0,sumh6 = 0, sumh7 = 0 ;
		long sumt1 = 0 ,sumt2 = 0,sumt3 = 0,sumt4 = 0,sumt5 = 0,sumt6 = 0,sumt7 = 0 ;
		
	/**
	 *  taille 10
	 */
		
		for(int i=1;i<=10;i++)
		{
			h.put(i,i);
			t.put(i,i);			
		}
		
		//mesure
		for(int j = 0 ; j <1000 ;j++)
		{
		//instruction de h
		start = System.nanoTime();

		h.get(3);
		h.get(7);
		h.get(9);
		
		duree = System.nanoTime() - start;
		sumh1 += (duree/3);
		
		
		//instruction de t
		start = System.nanoTime();

		t.get(3);
		t.get(7);
		t.get(9);
		
		duree = System.nanoTime() - start;
		sumt1 += (duree/3);
		
		}
		
		
		/**
		 *  taille 100
		 */
		for(int i=11;i<=100;i++)
		{
			
			h.put(i,i);
			t.put(i,i);
			
		}
		
		
		//mesure
		for(int j = 0 ; j <1000 ;j++)
		{
		//h
		start = System.nanoTime();

		h.get(12);
		h.get(59);
		h.get(96);
		
		duree = System.nanoTime() - start;
		sumh2 += (duree/3);
		
		//t
		start = System.nanoTime();

		
		t.get(12);
		t.get(59);
		t.get(96);
		
		duree = System.nanoTime() - start;
		sumt2 += (duree/3);
		}
		
		/**
		 *  taille 1000
		 */
		for(int i=101;i<=1000;i++)
		{
			
			h.put(i,i);
			t.put(i,i);
			
		}
		
		
		//mesure
		for(int j = 0 ; j <1000 ;j++)
		{
		start = System.nanoTime();

		h.get(110);
		h.get(762);
		h.get(987);
		
		duree = System.nanoTime() - start;
		sumh3 += (duree/3);
		
		//t
		start = System.nanoTime();

		t.get(110);
		t.get(762);
		t.get(987);
		
		duree = System.nanoTime() - start;
		sumt3 += (duree/3);
		}
		
		/**
		 *  taille 10 000
		 */
		for(int i=1001;i<=10000;i++)
		{
			
			h.put(i,i);
			t.put(i,i);
			
		}
		
		//mesure
		for(int j = 0 ; j <1000 ;j++)
		{
		start = System.nanoTime();

		h.get(1023-j);
		h.get(4623-j);
		h.get(9999-j);
		
		duree = System.nanoTime() - start;
		sumh4 += (duree/3);
		
		// t
		start = System.nanoTime();

		t.get(1023-j);
		t.get(4623-j);
		t.get(9999-j);
		
		duree = System.nanoTime() - start;
		sumt4 += (duree/3);
		}
		/**
		 *  taille 100 000
		 */
		for(int i=10001;i<100000;i++)
		{
			
			h.put(i,i);
			t.put(i,i);
			
		}
		
		//mesure
		for(int j = 0 ; j <1000 ;j++)
		{
		//h
		start = System.nanoTime();

		h.get(893);
		h.get(58123-j);
		h.get(97386-j);
		
		duree = System.nanoTime() - start;
		sumh5 += (duree/3);
		
		//t
		start = System.nanoTime();
		
		t.get(893);
		t.get(58123-j);
		t.get(97386-j);
		
		duree = System.nanoTime() - start;
		sumt5 += (duree/3);
		}
		/**
		 *  taille 1 000 000
		 */

		for(int i=100001;i<1000000;i++)
		{
			
			h.put(i,i);
			t.put(i,i);
			
		}
		//mesure
		for(int j = 0 ; j <1000 ;j++)
		{
		//h
		start = System.nanoTime();

		h.get(46);
		h.get(641863-j);
		h.get(987235-j);
		
		duree = System.nanoTime() - start;
		sumh6 += (duree/3);
		
		// t
		start = System.nanoTime();

		t.get(46);
		t.get(641863-j);
		t.get(987235-j);
		
		duree = System.nanoTime() - start;		
		sumt6 += (duree/3);
		}
		/**
		 * taille 1 400 000
		 */
		
		for(int i=1000001;i<1400000;i++)
		{
			
			h.put(i,i);
			t.put(i,i);
						
		}
		
		
		for(int j = 0; j<1000 ; j++)
		{
			//h
			start = System.nanoTime();

			h.get(1467-j);
			h.get(1399999-j);
			h.get(756894-j);
			
			duree = System.nanoTime() - start;
			sumh7 += (duree/3);
			
			// t
			start = System.nanoTime();

			t.get(1467-j);
			t.get(1399999-j);
			t.get(756894-j);
			
			duree = System.nanoTime() - start;		
			sumt7 += (duree/3);
		}
	
		
	
	
	System.out.println("mesure 1 a 10 h : "+ (sumt1)/1000);
	System.out.println("mesure 1 a 10 t : "+ (sumh1)/1000);
	System.out.println("mesure 1 a 100 h : "+ (sumt2)/1000);
	System.out.println("mesure 1 a 100 t : "+ (sumh2)/1000);
	System.out.println("mesure 1 a 1000 h : "+ (sumt3)/1000);
	System.out.println("mesure 1 a 1000 t : "+ (sumh3)/1000);
	System.out.println("mesure 1 a 10 000 h : "+ (sumt4)/1000);
	System.out.println("mesure 1 a 10 000 t : "+ (sumh4)/1000);
	System.out.println("mesure 1 a 100 000 h : "+ (sumt5)/1000);
	System.out.println("mesure 1 a 100 000 t :  "+ (sumh5)/1000);
	System.out.println("mesure 1 a 1 000 000 h :  "+ (sumt6)/1000);
	System.out.println("mesure 1 a 1 000 000 t :  "+ (sumh6)/1000);
	System.out.println("mesure 1 a 1 400 000 h :  "+ (sumt7)/1000);
	System.out.println("mesure 1 a 1 400 000 t :  "+ (sumh7)/1000);
	
   }

}


