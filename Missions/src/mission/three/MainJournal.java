package mission.three;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainJournal
{
	HashMap<String, Journal> dictionary;
	static ArrayList<HashMap<String, Journal>> testDics;
	
	public MainJournal()
	{
		Journal.rankMap_init();
	}

    
        public HashMap<String, Journal> fillMap(ArrayList<Journal> journal){
            int compteur=0;
            HashMap<String, Journal> map = new HashMap<String, Journal>();
            for(int i=0;i<=journal.size();i++){
                map.put(journal.get(i).getTitle(), journal.get(i));
            }
            
            return map;
        }

	/**
	 * @author David Sarkozi
	 */
	public static void main(String[] args)
	{
		String inputFile = args[0];
		ArrayList<String> lines = null;
		try
		{
			lines = FileManager.readFile(inputFile);
		}
		catch (IOException e)
		{
			System.err
					.println("Une erreur est survenue à la lecture du fichier "
							+ inputFile);
			return;
		}
		MainJournal mr = new MainJournal();
		for (String line : lines)
		{
			mr.addJournal(line);
		}
		/*
		 * Génération du matériel de tests de complexité
		 */
		
		//10 dictionnaires de tailles différentes
		for(int i= 0; i < 10; i++){
			testDics.add(new HashMap<String, Journal>());
		}
		
		//Garnir les dictionaires
		String[] data;
		Journal journal;
		//une "fausse" revue
		String dummyData = "Z,SINF1121SINF1121SINF1121SINF1121SINF1121,,,,";
		dummyData.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		Journal dummy = 
				new Journal(dummyData.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)")
);
		for(int i =0; i<testDics.size(); i++){
			//lire un certain nombre de lignes destinées à chaque dictionnaire
			try
			{	
			lines = FileManager.readSmallerFile(inputFile, 2071 * (i+1));//TODO hardcoded value!
			}
			catch (IOException e)
			{
				System.err
				.println("Une erreur est survenue à la lecture du fichier "
							+ inputFile);
				return;
			}
			//spliter les lignes les transformer en Journal et les ajouter dans chaque dico
			for (String line : lines)
			{
				data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
				journal = new Journal(data);
				testDics.get(i).put(journal.getTitle(), journal);

			}
			
		}			
			/*
			 * TESTS DE COMPLEXITE :
			 * PUT
			 * GET
			 * */
		
			/*
			 * Test de temps d'exécution sur la methode put(Object key, Journal j)
			 */
			System.out.println("*Test put(Object key, Journal j)*");
			for(int i = 0; i< 10; i++){				
			System.out.println("Put sur dictionnaire"+ i +":"+ putExecTime(testDics.get(i), dummy)+"nano");
			}
			
			/*
			 *Test de temps d'execution sur la methode get(Object key) 
			 */
			System.out.println("*Test get(Object key*)");
			for(int i = 0; i< 10; i++){				
				System.out.println("Get sur dictionnaire"+ i +":"+ getExecTime(testDics.get(i))+"nano");
			}
	}//fin main
	
	/**
	 * Calcule le temps d'exécution moyen de la methode put 
	 * sur un dictionnaire.
	 * @pre  d n'est pas null
	 * @post le temps d'exécution est renvoyé
	 * @exceptions -
	 * @param d
	 * @return le temps d'execution
	 */
	private static long putExecTime(HashMap<String, Journal> d, Journal dummy){
		long startTime;
		long estimatedTime;
		startTime = System.nanoTime() ;
		//rajouter le dummy à la fin de chaque dico
		d.put(dummy.getTitle(), dummy);
		estimatedTime = System.nanoTime() - startTime ;
		return estimatedTime;

	}
	
	/**
	 * Calcule le temps d'exécution moyen de la methode get 
	 * sur un dictionnaire. 
	 * @pre : d n'est pas null
	 * @post : le temps d'exécution est renvoyé
	 * @exceptions : -
	 * @return le temps d'execution
	 */
	private static long getExecTime(HashMap<String, Journal> d) {
		//Récupérer le dummy inséré à la fin de chaque dictionnaire
		long startTime;
		long estimatedTime;
			startTime = System.nanoTime() ;
			d.get("SINF1121SINF1121SINF1121SINF1121SINF1121");
			estimatedTime = System.nanoTime() - startTime ;
			return estimatedTime;
	}
	/**
	 * @pre -
	 * @param line
	 *            The line of the file representing a journal
	 */
	public void addJournal(String line)
	{
		String[] data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		Journal journal = new Journal(data);
		dictionary.put(journal.getTitle(), journal);
	}

}
