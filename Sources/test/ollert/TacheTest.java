package ollert;

import ollert.donneesTache.Priorite;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {
	@Test
	public void constructeur_ok() {
		Tache tache = new Tache("Titre");
		assertEquals("Titre", tache.getTitre());
	}

	@Test
	public void constructeur_titre_null() {
		assertThrows(NullPointerException.class, () -> new Tache(null));
	}

	@Test
	public void sousTache_ok() {
		Tache tache = new Tache("Titre");
		tache.creerSousTache("SousTache Titre");
		assertEquals("SousTache Titre", tache.obtenirSousTache(new ArrayList<>(List.of(0))).getTitre());
	}

	@Test
	public void sousTache_titre_null() {
		Tache tache = new Tache("Titre");
		assertThrows(NullPointerException.class, () -> tache.creerSousTache(null));
	}

	@Test
	public void sousTache_multiple_ok() {
		Tache tache = new Tache("Titre");
		tache.creerSousTache("SousTache Titre 1");
		ArrayList<Integer> indices = new ArrayList<>(List.of(0));
		tache.obtenirSousTache(indices).creerSousTache("SousTache Titre 2");
		assertEquals("SousTache Titre 1", tache.obtenirSousTache(indices).getTitre());
		indices.add(0);
		assertEquals("SousTache Titre 2", tache.obtenirSousTache(indices).getTitre());
	}

	@Test
	public void sousTache_inexistante() {
		Tache tache = new Tache("Titre");
		assertThrows(IndexOutOfBoundsException.class, () -> tache.obtenirSousTache(new ArrayList<>(List.of(0))));
	}

	@Test
	public void ajout_dependance_ok() {
		Tache tache1 = new Tache("Titre 1");
		Tache tache2 = new Tache("Titre 2");
		tache1.ajouterDependance(tache2);
		assertTrue(tache1.getDependances().contains(tache2));
		assertTrue(tache2.getAntecedents().contains(tache1));
	}

	@Test
	public void suppression_dependance_ok() {
		Tache tache1 = new Tache("Titre 1");
		Tache tache2 = new Tache("Titre 2");
		tache1.ajouterDependance(tache2);
		tache1.supprimerDependance(tache2);
		assertFalse(tache1.getDependances().contains(tache2));
		assertFalse(tache2.getAntecedents().contains(tache1));
	}

	@Test
	public void supprimerSousTache_inexistante() {
		Tache tache = new Tache("Titre");
		assertThrows(IndexOutOfBoundsException.class, () -> tache.supprimerSousTache(new ArrayList<>(List.of(0))));
	}

	@Test
	public void obtenirSousTache_inexistante() {
		Tache tache = new Tache("Titre");
		assertThrows(IndexOutOfBoundsException.class, () -> tache.obtenirSousTache(new ArrayList<>(List.of(0))));
	}

	@Test
	public void init_priorite() {
		Tache tache = new Tache("Titre");
		assertEquals(0, tache.getPriorite().ordinal());
	}

	@Test
	public void ajout_priorite() {
		Tache tache = new Tache("Titre");
		tache.setPriorite(Priorite.FAIBLE);
		assertEquals(1, tache.getPriorite().ordinal());
		tache.setPriorite(Priorite.MOYENNE);
		assertEquals(2, tache.getPriorite().ordinal());
		tache.setPriorite(Priorite.ELEVEE);
		assertEquals(3, tache.getPriorite().ordinal());
	}
}