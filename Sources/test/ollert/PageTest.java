package ollert;

import org.junit.jupiter.api.*;
import ollert.*;
import exceptions.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class PageTest {

    public Page page1, page2, page3;

    @BeforeEach
    public void setUp(){
        page1 = new Page("Titre1");

        page2 = new Page("Titre2");
        page2.creerListeTaches("Liste1");
        page2.creerListeTaches("Liste2");
        page2.creerListeTaches("Liste3");
        page2.creerListeTaches("Liste4");

        page3 = new Page("Titre3");
        page3.creerListeTaches("Liste1");
        page3.creerListeTaches("Liste2");
        page3.getListes().get(0).creerTache("Tache01");
        page3.getListes().get(0).creerTache("Tache02");
        page3.getListes().get(1).creerTache("Tache11");
        page3.getListes().get(1).creerTache("Tache12");
        page3.getListes().get(1).creerTache("Tache13");
    }

    @Test
    public void test_Page(){
        page1 = new Page("Titre1");
        Assertions.assertEquals("Titre1", page1.getTitre());
        Assertions.assertEquals(0, page1.getListes().size());
        Assertions.assertEquals(0, page1.getArchives().size());

        try{
            new Page(null);
            fail("Une exeception aurait dû être levée");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void test_creerListeTaches() throws IndiceInvalideException{
        page1.creerListeTaches("Liste1");
        Assertions.assertEquals(1, page1.getListes().size());
        Assertions.assertEquals(0, page1.getArchives().size());
        ListeTaches lt1 = page1.getListes().get(0);
        Assertions.assertEquals("Liste1", lt1.getTitre());

        page1.creerListeTaches("Liste2");
        Assertions.assertEquals(2, page1.getListes().size());
        Assertions.assertEquals(0, page1.getArchives().size());
        ListeTaches lt2 = page1.getListes().get(1);
        Assertions.assertEquals("Liste2", lt2.getTitre());

        try{
            page1.creerListeTaches(null);
            fail("Une exeception aurait dû être levée");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void test_supprimerListeTaches() throws IndiceInvalideException{
        Assertions.assertEquals(4, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.supprimerListeTaches(-1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.supprimerListeTaches(4);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        ListeTaches lt1 = page2.supprimerListeTaches(0);
        Assertions.assertEquals("Liste1", lt1.getTitre());
        Assertions.assertEquals(3, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTaches lt2 = page2.supprimerListeTaches(1);
        Assertions.assertEquals("Liste3", lt2.getTitre());
        Assertions.assertEquals(2, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTaches lt3 = page2.supprimerListeTaches(1);
        Assertions.assertEquals("Liste4", lt3.getTitre());
        Assertions.assertEquals(1, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTaches lt4 = page2.supprimerListeTaches(0);
        Assertions.assertEquals("Liste2", lt4.getTitre());
        Assertions.assertEquals(0, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.supprimerListeTaches(0);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.supprimerListeTaches(1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void test_archiverListeTaches() throws IndiceInvalideException{
        Assertions.assertEquals(4, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.archiverListeTaches(-1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.archiverListeTaches(4);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        ListeTaches lt1 = page2.archiverListeTaches(0);
        Assertions.assertEquals("Liste1", lt1.getTitre());
        Assertions.assertEquals(3, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTaches lt2 = page2.archiverListeTaches(1);
        Assertions.assertEquals("Liste3", lt2.getTitre());
        Assertions.assertEquals(2, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTaches lt3 = page2.archiverListeTaches(1);
        Assertions.assertEquals("Liste4", lt3.getTitre());
        Assertions.assertEquals(1, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTaches lt4 = page2.archiverListeTaches(0);
        Assertions.assertEquals("Liste2", lt4.getTitre());
        Assertions.assertEquals(0, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.supprimerListeTaches(0);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.supprimerListeTaches(1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void test_archiverTache() throws IndiceInvalideException{
        page3.archiverTache(0, 1);
        Assertions.assertEquals(1, page3.getListes().get(0).getTaches().size());
        Assertions.assertEquals(3, page3.getListes().get(1).getTaches().size());
        Assertions.assertEquals(1, page3.getArchives().size());

        page3.archiverTache(0, 0);
        Assertions.assertEquals(0, page3.getListes().get(0).getTaches().size());
        Assertions.assertEquals(3, page3.getListes().get(1).getTaches().size());
        Assertions.assertEquals(2, page3.getArchives().size());

        page3.archiverTache(1, 1);
        Assertions.assertEquals(0, page3.getListes().get(0).getTaches().size());
    }
}
