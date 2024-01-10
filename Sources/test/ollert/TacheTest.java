package ollert;

import ollert.tache.donneesTache.Priorite;
import ollert.tache.ListeTaches;
import ollert.tache.SousTache;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {

	Page page;
	ListeTaches liste;
	TachePrincipale tache;

	@BeforeEach
	public void setUp() {
		page = new Page("Page");
		liste = new ListeTaches("Liste", page);
		tache = new TachePrincipale("Tache", liste);
	}

	@Test
	public void constructeur_titre_null() {
		assertThrows(NullPointerException.class, () -> new TachePrincipale("nom", liste));
	}

	@Test
	public void sousTache_ok() {
		tache.addSousTache("SousTache Titre");
		assertEquals("SousTache Titre", tache.getSousTaches().get(0).getTitre());
	}

	@Test
	public void sousTache_titre_null() {
		assertThrows(NullPointerException.class, () -> tache.addSousTache(null));
	}

	@Test
	public void sousTache_inexistante() {
		assertThrows(IndexOutOfBoundsException.class, () -> tache.getSousTache(0));
	}

	@Test
	public void ajout_dependance_ok() {
		TachePrincipale tache2 = new TachePrincipale("Titre 2", liste);
		tache.ajouterDependance(tache2);
		assertTrue(tache.getDependances().contains(tache2));
		assertTrue(tache2.getAntecedents().contains(tache));
	}

	@Test
	public void suppression_dependance_ok() {
		TachePrincipale tache2 = new TachePrincipale("Titre 2", liste);
		tache.ajouterDependance(tache2);
		tache.supprimerDependance(tache2);
		assertFalse(tache.getDependances().contains(tache2));
		assertFalse(tache2.getAntecedents().contains(tache));
	}

	@Test
	public void init_priorite() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
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

	@Test
	public void ajout_utilisateur() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		tache.ajouterUtilisateur("Mathéo");
		assertEquals(1, tache.getMembres().size());
		// Etiquette dejà existante, l'ajout ne doit pas se faire
		tache.ajouterUtilisateur("Mathéo");
		assertEquals(1, tache.getMembres().size());
		// Ajout d'une deuxième étiquette
		tache.ajouterUtilisateur("Enzo");
		assertEquals(2, tache.getMembres().size());
		// Ajout d'une étiquette déjà utilisée ailleurs
		TachePrincipale tache1 = new TachePrincipale("Titre", liste);
		tache1.ajouterUtilisateur("Mathéo");
		assertEquals(1, tache1.getMembres().size());
		assertEquals(2, tache.getMembres().size());
		assertThrows(NullPointerException.class, () -> tache1.ajouterUtilisateur(""));
	}

	@Test
	public void suppression_utilisateur() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		tache.ajouterUtilisateur("Mathéo");
		tache.ajouterUtilisateur("Enzo");
		tache.ajouterUtilisateur("Grégoire");
		assertEquals(3, tache.getMembres().size());
		// Suppression d'une étiquette simple
		tache.supprimerUtilisateur("Mathéo");
		assertEquals(2, tache.getMembres().size());
		// Suppression une deuxième fois de l'étiquette
		tache.supprimerUtilisateur("Mathéo");
		assertEquals(2, tache.getMembres().size());
		// Suppression d'une étiquette qui n'existe pas
		tache.supprimerUtilisateur("Stéphane");
		assertEquals(2, tache.getMembres().size());
		// Suppression d'une étiquette qui n'est pas dans la tâche
		TachePrincipale tache1 = new TachePrincipale("1", liste);
		tache1.ajouterUtilisateur("Mathéo");
		tache.supprimerUtilisateur("Mathéo");
		assertEquals(2, tache.getMembres().size());
		assertEquals(1, tache1.getMembres().size());

	}

	@Test
	public void ajout_etiquette() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		tache.ajouterEtiquette("Tag1");
		assertEquals(1, tache.getTags().size());
		// Etiquette dejà existante, l'ajout ne doit pas se faire
		tache.ajouterEtiquette("Tag1");
		assertEquals(1, tache.getTags().size());
		// Ajout d'une deuxième étiquette
		tache.ajouterEtiquette("Tag2");
		assertEquals(2, tache.getTags().size());
		// Ajout d'une étiquette déjà utilisée ailleurs
		TachePrincipale tache1 = new TachePrincipale("Titre", liste);
		tache1.ajouterEtiquette("Tag1");
		assertEquals(1, tache1.getTags().size());
		assertThrows(NullPointerException.class, () -> tache1.ajouterEtiquette(""));
	}

	@Test
	public void suppression_etiquette() {
		TachePrincipale tache = new TachePrincipale("Titre", liste);
		tache.ajouterEtiquette("Tag1");
		tache.ajouterEtiquette("Tag2");
		tache.ajouterEtiquette("Tag3");
		assertEquals(3, tache.getTags().size());
		// Suppression d'une étiquette simple
		tache.supprimerEtiquette("Tag1");
		assertEquals(2, tache.getTags().size());
		// Suppression une deuxième fois de l'étiquette
		tache.supprimerEtiquette("Tag1");
		assertEquals(2, tache.getTags().size());
		// Suppression d'une étiquette qui n'existe pas
		tache.supprimerEtiquette("Tag6");
		assertEquals(2, tache.getTags().size());
		// Suppression d'une étiquette qui n'est pas dans la tâche
		TachePrincipale tache1 = new TachePrincipale("1", liste);
		tache1.ajouterEtiquette("Tag1");
		tache.supprimerEtiquette("Tag1");
		assertEquals(2, tache.getTags().size());
		assertEquals(1, tache1.getTags().size());

	}
}