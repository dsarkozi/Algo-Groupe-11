package mission.three;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import biblio.model.BookRepository;
import biblio.model.ClientRepository;


/**
 * @author Clémentine
 *
 */
public class MainJournalTest {
	
	JournalMap jm1;
	JournalMap jm2;
	JournalMap jm3;
	JournalMap jm4;
	JournalMap jm5;
	JournalMap jm6;
	JournalMap jm7;
	JournalMap jm8;
	JournalMap jm9;
	JournalMap jm10;


	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void get() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * load the Journals.
	 * In this way they will be accessible for new tests
	 */
	private static void loadJournals() {
		if(journalMap == null) {
			journalMap = new journalMap();
		}
		assertTrue("No jounals in the map for testing", journalMap.getJournals().size()>0);		
	}

	private static void generateTestFiles(){
		if (file.exists())
			{
	
				BufferedReader bufreader = new BufferedReader(new FileReader(file));
				ArrayList<String> content = new ArrayList<String>();
				String currentLine = null;
				while ((currentLine = bufreader.readLine()) != null)
				{
					content.add(currentLine);
				}
				bufreader.close();
				return content;
			}
			else System.err.println("Read failed : File doesn't exist");
			return null;
		}
}
