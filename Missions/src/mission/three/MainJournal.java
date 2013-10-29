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
		
		//Garnir les dicos
		String[] data;
		Journal journal;
		//une "fausse" revue
		String dummyData = "Z,SINF1121SINF1121SINF1121SINF1121SINF1121,,,,";
		dummyData.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
		Journal dummy = 
				new Journal(dummyData.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)")
);
		for(int i =0; i<testDics.size(); i++){
			//lire un certain nombre de lignes destinées à chaque dico
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
			//rajouter le dummy à la fin de chaque dico
			testDics.get(i).put(dummy.getTitle(), dummy);
			
			/*
			 * TESTS
			 * 
			 */
			
			/*
			 *Test de temps d'execution sur la methode get(Object key) 
			 */
			System.out.println(getExecTime());
			
		}

		
	}//fin main
	
	/**
	 * Calcule le temps d'exécution moyen de la methode get 
	 * sur chaque dictionnaire d'une liste de dicos 
	 * @pre :
	 * @post : un rapport est affiché dans la console
	 * @exceptions :
	 * @return void
	 */
	private static char[] getExecTime() {
		// TODO Auto-generated method stub
		return null;
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
