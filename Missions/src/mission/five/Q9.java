/**
 * 
 */
package mission.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe qui implemente les reponses a la question 9 au sujet du taux de
 * compression de nos programmes.
 * 
 * @author clementine
 * 
 */
@SuppressWarnings("all")
public class Q9
{

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{

		/**
		 * Test influence nombre de chars différents dans le texte: on crée 9
		 * fichiers de meme taille (approximativement 2000) ou on fait augmenter
		 * de façon constante le nombre de charactères différents
		 */

		// On fera varier le nombre de chars dans chaque fichier
		ArrayList<String> chars = new ArrayList<String>();
		chars.add("abcdefghij");
		chars.add("abcdefghijklmno");
		chars.add("abcdefghijklmnopqrst");
		chars.add("abcdefghijklmnopqrstuvwxy");
		chars.add("abcdefghijklmnopqrstuvwxyzABCD");
		chars.add("abcdefghijklmnopqrstuvwxyzABCDEFGHI");
		chars.add("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN");
		chars.add("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRS");
		chars.add("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWX");

		int x = 10;
		final int T = 2000;
		int o;
		float tc = 0;
		ArrayList taux = new ArrayList();
		BufferedReader br;
		BufferedWriter bw;
		System.out.println("nbr lettres différentes -  Taux de compression");

		// création des fichiers compression et calcul du taux
		for (int i = 0; i < 8; i++)
		{
			// remplissage du fichier courant
			bw = new BufferedWriter(new FileWriter("file" + i, true));
			o = T / x;
			for (int j = 0; j < o; j++)
			{
				bw.write(chars.get(i));
			}
			bw.close();
			// compression du fichier courant
			// TODO new Compress("file"+i, "cfile"+i);

			// calcul du taux de compression pour le fichier courant
			br = new BufferedReader(new FileReader("file" + i)); // TODO put
																	// cfile
			while (br.read() != -1)
			{
				tc++;
			}
			br.close();
			taux.add(tc / (o * x));

			// afficher le résultat
			System.out.println(x + "\t\t\t" + taux.get(i));
			tc = 0;
			x = x + 5;
		}

		/**
		 * Test influence taille du fichier: on crée et compresse 10 fichiers de
		 * taille constamment croissantes (+10000) et on calcule le taux de
		 * comression pour chaque fichier ainsi que le temps d'execution de
		 * chaque compression. On s'assure de garder le nombre de caractère
		 * différents et la variance des nombres d'occurences des caracteres
		 * constants
		 */
		final int X = 10;
		o = 10000;
		tc = 0;
		long startTime;
		long stopTime;
		ArrayList taux2 = new ArrayList();
		ArrayList temps = new ArrayList();
		ArrayList dctemps = new ArrayList();
		System.out.println("Taille du fichier - Taux de compression "
				+ "- Temps compression(ns) - Temps decompression(ns)");
		for (int i = 0; i < X; i++)
		{
			// remplissage du fichier courant
			bw = new BufferedWriter(new FileWriter("file2" + i, true));
			for (int j = 0; j < o; j++)
			{
				bw.write(chars.get(0));
			}
			bw.close();
			// compression du fichier courant
			startTime = System.nanoTime();
			// TODO new Compress("file2"+i, "cfile2"+i);
			stopTime = System.nanoTime();

			// calcul du taux de compression
			tc = 0;
			br = new BufferedReader(new FileReader("file2" + i));// TODO put
																	// cfile2
			while (br.read() != -1)
			{
				tc++;
			}
			br.close();

			taux2.add(tc / (o * X));
			temps.add(stopTime - startTime);

			// calcul du temps de décompression
			startTime = System.nanoTime();
			// TODO new Decompress("cfile2"+i, "dcfile2"+i);
			stopTime = System.nanoTime();
			dctemps.add(stopTime - startTime);

			// afficher les résultats
			System.out.println((o * X) + "chars\t\t\t" + taux2.get(i)
					+ "\t\t\t" + temps.get(i) + "\t\t\t" + dctemps.get(i));

			o = o + 10000;
		}

	}

}