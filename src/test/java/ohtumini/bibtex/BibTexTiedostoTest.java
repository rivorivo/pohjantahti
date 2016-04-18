package ohtumini.bibtex;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simo
 */
public class BibTexTiedostoTest {

    private BibTexTiedosto t;

    public BibTexTiedostoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            t = new BibTexTiedosto("testi");
        } catch (IOException ex) {

        }
    }

    @After
    public void tearDown() {
        t.poistaTiedosto();
    }

    /**
     * Testataan tiedoston luonti
     */
    @Test
    public void testLuoTiedosto() {
        File file = new File("testi.bib");
        assertTrue("Tiedosto ei luotu oikealla nimellä", file.exists());
    }

    /**
     * Testataan tiedoston luonti, jos ei olemassa
     */
    @Test
    public void testLuoTiedostoVarmistus() {
        try {
            BibTexTiedosto t2 = new BibTexTiedosto("testi2");
            File file = new File("testi2.bib");
            assertTrue("Tiedosto ei luotu kun ei valmiiksi olemassa", file.exists());
            t2.poistaTiedosto();
        } catch (IOException ex) {
            fail("Tiedoston luonti ei onnistu");
        }

    }

    /**
     * Testataan tiedoston poisto
     */
    @Test
    public void testPoistaTiedosto() {
        assertTrue("Tiedosto ei luotu oikealla nimellä", t.poistaTiedosto());

    }

    /**
     * Testataan viite tulee lisättyä tiedostoon.
     */
    @Test
    public void testLisaaViiteTiedostoon() {
        t.lisaaViiteTiedostoon(esim);
        Scanner s = new Scanner("testi.bib");
        assertTrue(s.hasNextLine());
    }

    private final String esim = "@Book{hicks2001,\n"
            + " author    = \"von Hicks, III, Michael\",\n"
            + " title     = \"Design of a Carbon Fiber Composite Grid Structure for the GLAST\n"
            + "              Spacecraft Using a Novel Manufacturing Technique\",\n"
            + " publisher = \"Stanford Press\",\n"
            + " year      =  2001,\n"
            + " address   = \"Palo Alto\",\n"
            + " edition   = \"1st\",\n"
            + "}";

}
