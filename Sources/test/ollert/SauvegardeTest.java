package ollert;

import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*    ;


public class SauvegardeTest {

    Page page1, page2, page3;

    @BeforeEach
    public void setUp(){
        page1 = new Page("Ollert1");

        page2 = new Page("Ollert2");
        page2.creerListeTaches("Liste 1");

        page3 = new Page("Ollert3");
        page3.creerListeTaches("Liste 1");
        page3.creerListeTaches("Liste 2");
        page3.obtenirListe(0).creerTache("Tache 01");
        page3.obtenirListe(0).creerTache("Tache 02");
        page3.obtenirListe(1).creerTache("Tache 11");


        File dossier = new File("ressource");
        if (dossier.exists() && dossier.isDirectory()) {
            // Liste des fichiers dans le dossier
            File[] fichiers = dossier.listFiles();
            if (fichiers != null) {
                for (File fichier : fichiers) {
                    fichier.delete();
                }
            }
        }
    }

    @AfterAll
    public static void close(){
        File dossier = new File("ressource");
        if (dossier.exists() && dossier.isDirectory()) {
            // Liste des fichiers dans le dossier
            File[] fichiers = dossier.listFiles();
            if (fichiers != null) {
                for (File fichier : fichiers) {
                    fichier.delete();
                }
            }
        }
    }

    @Test
    public void test_sauvegarderPage(){
        // sauvegarde valide
        assertTrue(Sauvegarde.sauvegarderPage(page1));
        File fichier1 = new File("ressource/Ollert1.ol");
        assertTrue(fichier1.exists());

        // verifications fichier non présent (normal)
        File fichier2 = new File("ressource/Ollert2.ol");
        assertFalse(fichier2.exists());

        // verification non présence fichier mauvaise place
        File fichier3 = new File("Ollert1.ol");
        assertFalse(fichier3.exists());
    }

    @Test
    public void test_chargerPage(){
        Sauvegarde.sauvegarderPage(page1);
        Sauvegarde.sauvegarderPage(page2);
        Sauvegarde.sauvegarderPage(page3);

        // fichier valide

        Page page1Chargee = Sauvegarde.chargerPage("ressource/Ollert1.ol");
        assertEquals(page1.toString(), page1Chargee.toString());

        Page page2Chargee = Sauvegarde.chargerPage("ressource/Ollert2.ol");
        assertEquals(page2.toString(), page2Chargee.toString());

        Page page3Chargee = Sauvegarde.chargerPage("ressource/Ollert3.ol");
        assertEquals(page3.toString(), page3Chargee.toString());


        // fichier non valide => null
        Page page4Chargee = Sauvegarde.chargerPage("ressource/Ollert4.ol");
        assertNull(page4Chargee);
    }
}
