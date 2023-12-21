package ollert;

import ollert.tache.ListeTaches;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PageTest {

	Page page1, page2;

	@BeforeEach
	public void setUp() {
		page1 = new Page("Page 1");
		page2 = new Page("Page 2");
	}

	@Test
	public void test_Page() {
		// test constructeur valide
		assertEquals("Page 1", page1.getTitre());
		assertEquals(0, page1.sizeListe());

		// test titre==null ==> NullPointerException
		try {
			new Page(null);
			fail("Le constructeur aurait du lever une exception");
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void test_addListesTaches(){
		// ajout 1 listeTaches
		page1.addListeTaches("Liste 1");
		assertEquals(1, page1.sizeListe());
		assertEquals("Liste 1", page1.getListeTaches(0).getTitre());

		page2.addListeTaches("Liste 1");
		page2.addListeTaches("Liste 2");
		page2.addListeTaches("Liste 3");
		assertEquals(3, page2.sizeListe());
		assertEquals("Liste 2", page2.getListeTaches(1).getTitre());
	}

	@Test
	public void test_removeListeTaches(){
		page1.addListeTaches("Liste 1");
		ListeTaches lt = page1.addListeTaches("Liste 2");
		page1.addListeTaches("Liste 3");


		page1.removeListeTaches(lt);
		assertEquals(2, page1.sizeListe());
		assertEquals("Liste 3", page1.getListeTaches(1).getTitre());
	}

}
