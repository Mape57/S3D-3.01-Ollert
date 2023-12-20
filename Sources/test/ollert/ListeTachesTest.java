package ollert;

import ollert.tache.ListeTaches;
import ollert.tache.TachePrincipale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListeTachesTest {

	@Test
	public void test_constructeur() {
		Page parent = new Page("Parent");
		ListeTaches liste = new ListeTaches("Test", parent);
		assertEquals("Test", liste.getTitre());
		assertEquals(parent, liste.getParent());
		assertTrue(liste.getTaches().isEmpty());
	}

	@Test
	public void test_constructeurTitreNull() {
		assertThrows(NullPointerException.class, () -> new ListeTaches(null, new Page("Parent")));
	}

	@Test
	public void test_setTitre() {
		ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
		liste.setTitre("New Title");
		assertEquals("New Title", liste.getTitre());
	}

	@Test
	public void test_setTitreNull() {
		ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
		assertThrows(NullPointerException.class, () -> liste.setTitre(null));
	}

	@Test
	public void test_ajout_titre() {
		ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
		TachePrincipale tache = liste.addTache("Tache1");
		assertEquals(tache, liste.getTache(0));
	}

	@Test
	public void test_ajout_titre_null() {
		ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
		assertThrows(NullPointerException.class, () -> liste.addTache(null));
	}

	@Test
	public void test_ajout_tache() {
		ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
		liste.addTache("Tache1");
		liste.addTache("Tache2");
		liste.addTache("Tache3");
		TachePrincipale tache = new TachePrincipale("Tache4", liste);
		liste.addTache(1, tache);

		assertEquals(tache, liste.getTache(1));
	}
}
