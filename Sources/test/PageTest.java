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
        page2.creerListeTache("Liste1");
        page2.creerListeTache("Liste2");
        page2.creerListeTache("Liste3");
        page2.creerListeTache("Liste4");

        page3 = new Page("Titre3");
        page3.creerListeTache("Liste1");
        page3.creerListeTache("Liste2");
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
    public void test_creerListeTache() throws IndiceInvalideException{
        page1.creerListeTache("Liste1");
        Assertions.assertEquals(1, page1.getListes().size());
        Assertions.assertEquals(0, page1.getArchives().size());
        ListeTache lt1 = page1.getListes().get(0);
        Assertions.assertEquals("Liste1", lt1.getTitre());

        page1.creerListeTache("Liste2");
        Assertions.assertEquals(2, page1.getListes().size());
        Assertions.assertEquals(0, page1.getArchives().size());
        ListeTache lt2 = page1.getListes().get(1);
        Assertions.assertEquals("Liste2", lt2.getTitre());

        try{
            page1.creerListeTache(null);
            fail("Une exeception aurait dû être levée");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void test_supprimerListeTache() throws IndiceInvalideException{
        Assertions.assertEquals(4, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.supprimerListeTache(-1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.supprimerListeTache(4);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        ListeTache lt1 = page2.supprimerListeTache(0);
        Assertions.assertEquals("Liste1", lt1.getTitre());
        Assertions.assertEquals(3, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTache lt2 = page2.supprimerListeTache(1);
        Assertions.assertEquals("Liste3", lt2.getTitre());
        Assertions.assertEquals(2, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTache lt3 = page2.supprimerListeTache(1);
        Assertions.assertEquals("Liste4", lt3.getTitre());
        Assertions.assertEquals(1, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTache lt4 = page2.supprimerListeTache(0);
        Assertions.assertEquals("Liste2", lt4.getTitre());
        Assertions.assertEquals(0, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.supprimerListeTache(0);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.supprimerListeTache(1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void test_archiverListeTache() throws IndiceInvalideException{
        Assertions.assertEquals(4, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.archiverListeTache(-1);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.archiverListeTache(4);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        ListeTache lt1 = page2.archiverListeTache(0);
        Assertions.assertEquals("Liste1", lt1.getTitre());
        Assertions.assertEquals(3, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTache lt2 = page2.archiverListeTache(1);
        Assertions.assertEquals("Liste3", lt2.getTitre());
        Assertions.assertEquals(2, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTache lt3 = page2.archiverListeTache(1);
        Assertions.assertEquals("Liste4", lt3.getTitre());
        Assertions.assertEquals(1, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        ListeTache lt4 = page2.archiverListeTache(0);
        Assertions.assertEquals("Liste2", lt4.getTitre());
        Assertions.assertEquals(0, page2.getListes().size());
        Assertions.assertEquals(0, page2.getArchives().size());

        try{
            page2.supprimerListeTache(0);
            fail("Une exeception aurait dû être levée");
        } catch (IndiceInvalideException e) {
            assertNotNull(e.getMessage());
        }

        try{
            page2.supprimerListeTache(1);
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
