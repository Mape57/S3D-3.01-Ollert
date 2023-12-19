package ollert;

import ollert.donneesTache.Priorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {

	Page page;
	ListeTaches liste;

	TachePrincipale tachePrincipale;

	@BeforeEach
	public void setUp() {
		page = new Page("Page");
		liste = new ListeTaches("Liste", page);
		tachePrincipale = new TachePrincipale("Tache", liste);
	}
	@Test
	public void constructeur_ok() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		assertEquals("Titre", tache.getTitre());
	}

	@Test
	public void constructeur_titre_null() {
		assertThrows(NullPointerException.class, () -> new TachePrincipale(null, liste));
	}

	@Test
	public void sousTache_ok() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		tache.creerSousTache("SousTache Titre");
		assertEquals("SousTache Titre", tache.obtenirSousTache(new ArrayList<>(List.of(0))).getTitre());
	}

	@Test
	public void sousTache_titre_null() {
		Tache tache = new SousTache("Titre", tachePrincipale);
		assertThrows(NullPointerException.class, () -> tache.creerSousTache(null));
	}

	@Test
	public void sousTache_multiple_ok() {
		SousTache tache = new SousTache("Titre", tachePrincipale);
		tache.creerSousTache("SousTache Titre 1");
		ArrayList<Integer> indices = new ArrayList<>(List.of(0));
		tache.obtenirSousTache(indices).creerSousTache("SousTache Titre 2");
		assertEquals("SousTache Titre 1", tache.obtenirSousTache(indices).getTitre());
		indices.add(0);
		assertEquals("SousTache Titre 2", tache.obtenirSousTache(indices).getTitre());
	}

	@Test
	public void sousTache_inexistante() {
		SousTache tache = new SousTache("Titre", tachePrincipale);
		assertThrows(IndexOutOfBoundsException.class, () -> tache.obtenirSousTache(new ArrayList<>(List.of(0))));
	}

	@Test
	public void ajout_dependance_ok() {
		TachePrincipale tache1 = new TachePrincipale("Titre 1", liste);
		TachePrincipale tache2 = new TachePrincipale("Titre 2", liste);
		tache1.ajouterDependance(tache2);
		assertTrue(tache1.getDependances().contains(tache2));
		assertTrue(tache2.getAntecedents().contains(tache1));
	}

	@Test
	public void suppression_dependance_ok() {
		TachePrincipale tache1 = new TachePrincipale("Titre 1", liste);
		TachePrincipale tache2 = new TachePrincipale("Titre 2", liste);
		tache1.ajouterDependance(tache2);
		tache1.supprimerDependance(tache2);
		assertFalse(tache1.getDependances().contains(tache2));
		assertFalse(tache2.getAntecedents().contains(tache1));
	}

	@Test
	public void supprimerSousTache_inexistante() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		assertThrows(IndexOutOfBoundsException.class, () -> tache.supprimerSousTache(new ArrayList<>(List.of(0))));
	}

	@Test
	public void obtenirSousTache_inexistante() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		assertThrows(IndexOutOfBoundsException.class, () -> tache.obtenirSousTache(new ArrayList<>(List.of(0))));
	}

	@Test
	public void init_priorite() {
		TachePrincipale tache = new TachePrincipale("Titre" , liste);
		assertEquals(0, tache.getPriorite().ordinal());
	}

	@Test
	public void ajout_priorite() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		tache.setPriorite(Priorite.FAIBLE);
		assertEquals(1, tache.getPriorite().ordinal());
		tache.setPriorite(Priorite.MOYENNE);
		assertEquals(2, tache.getPriorite().ordinal());
		tache.setPriorite(Priorite.ELEVEE);
		assertEquals(3, tache.getPriorite().ordinal());
	}
}