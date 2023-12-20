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
    public void test_creerTache() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        int indice = liste.addTache("Tache1");
        assertEquals(0, indice);
        assertEquals("Tache1", liste.getTaches().get(indice).getTitre());
    }

    @Test
    public void test_ajouterTache() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        TachePrincipale tache = new TachePrincipale("Tache1", liste);
        liste.addTache(0, tache);
        assertEquals(tache, liste.getTaches().get(0));
    }

    @Test
    public void test_supprimerTache() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        TachePrincipale tache = new TachePrincipale("Tache1", liste);
        liste.addTache(0, tache);
        TachePrincipale removed = liste.removeTache(0);
        assertEquals(tache, removed);
        assertTrue(liste.getTaches().isEmpty());
    }

    @Test
    public void test_setTitre() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        liste.setTitre("New Title");
        assertEquals("New Title", liste.getTitre());
    }

    @Test
    public void test_constructeurTitreNull() {
        assertThrows(NullPointerException.class, () -> new ListeTaches(null, new Page("Parent")));
    }

    @Test
    public void test_creerTacheTitreNull() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        assertThrows(NullPointerException.class, () -> liste.addTache(null));
    }

    @Test
    public void test_ajouterTacheNull() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        assertThrows(NullPointerException.class, () -> liste.addTache(0, null));
    }

    @Test
    public void test_setTitreNull() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        assertThrows(NullPointerException.class, () -> liste.setTitre(null));
    }

    @Test
    public void test_supprimerTacheNull() {
        ListeTaches liste = new ListeTaches("Test", new Page("Parent"));
        assertThrows(IndexOutOfBoundsException.class, () -> liste.removeTache(0));
    }
}
