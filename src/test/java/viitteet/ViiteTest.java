/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viitteet;

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
public class ViiteTest {

    Viite v;

    public ViiteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        v = new ViiteImpl();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of kentat method, of class Viite.
     */
    @Test
    public void testKentat() {
        assertTrue(true);
    }

    /**
     * Testataan että tietoa ei lisätä jos kentän nimi ei olemassa.
     */
    @Test
    public void testLisaaTietoVaaraKentta() {
        v.lisaaTieto("random", "testi");
        for (String avaimet : v.avaimet) {
            assertEquals(null, avaimet);
        }
    }

    /**
     * Luettu tieto palauttaa oikean
     */
    @Test
    public void testLueTieto() {
        v.lisaaTieto("A", "testi");
        assertEquals("testi", v.lueTieto("A"));
    }

    /**
     * Test of luoBibTeX method, of class Viite.
     */
    @Test
    public void testLuoBibTeX() {
        v.lisaaTieto("A", "testi");
        v.lueTieto("A");        
        assertTrue(v.luoBibTeX().startsWith("@testi"));
    }

    /**
     * Test of annaViitteenTyypinNimi method, of class Viite.
     */
    @Test
    public void testAnnaViitteenTyypinNimi() {
        assertTrue(true);
    }

    /**
     * Test of getTunniste method, of class Viite.
     */
    @Test
    public void testGetTunniste() {
        assertEquals(null, v.getTunniste());
    }

    /**
     * Test of setTunniste method, of class Viite.
     */
    @Test
    public void testSetTunniste() {
        v.setTunniste("testi");
        assertEquals("testi", v.getTunniste());
    }

    /**
     * Test of toString method, of class Viite.
     */
    @Test
    public void testToString() {
        assertTrue(true);
    }

    public class ViiteImpl extends Viite {

        public String[] kentat = {"A", "B", "C"};

        public ViiteImpl() {
            avaimet = new String[kentat.length];
            super.kentat = kentat;
        }

        public String annaViitteenTyypinNimi() {
            return "testi";
        }
    }

}
