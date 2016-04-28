/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtumini.bibtex;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import viitteet.Article;
import viitteet.Viite;
import viitteet.Viitelista;

/**
 *
 * @author Simo
 */
public class DataTiedostoTest {

    private Viitelista v;
    private DataTiedosto t;
    private String testiNimi;

    public DataTiedostoTest() {
        testiNimi = "testi666";
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        v = new Viitelista("testi");
        Viite a = new Article();
        a.lisaaTieto("TITLE", "testi");
        a.setTunniste("testi");
        v.add(a);
        try {
            t = new DataTiedosto(testiNimi);
            t.luoTiedosto();
        } catch (IOException ex) {

        }
    }

    @After
    public void tearDown() {
        File f = new File( testiNimi + ".rflist");
        f.delete();

    }

    /**
     * Test of tallennaTiedostoon method, of class DataTiedosto.
     */
    @Test
    public void testTallennaTiedostoon() {
        t.tallennaTiedostoon(v);
        assertTrue(new File(testiNimi+ ".rflist").exists());
    }

    /**
     * Test of haeTiedostosta method, of class DataTiedosto.
     */
    @Test
    public void testHaeTiedostosta() {
        testTallennaTiedostoon();
        assertEquals(v.size(), t.haeTiedostosta().size());
    }

    /**
     * Test of luoTiedosto method, of class DataTiedosto.
     */
    @Test
    public void testLuoTiedosto() {
        try {
            assertTrue(!t.luoTiedosto());
        } catch (IOException ex) {
            fail("tiedoston luonti ei onnistu");
        }

    }

    /**
     * Test of asetaNimi method, of class DataTiedosto.
     */
    @Test
    public void testAsetaNimi() {
        try {
            t.asetaNimi("uusinimi");
            t.luoTiedosto();
            File f = new File("uusinimi.rflist");
            assertTrue(f.exists());
            f.delete();
        } catch (Exception ex) {
            fail("tiedoston nimen vaihto");
        }
    }
    
        /**
     * Test of asetaNimi method, of class DataTiedosto.
     */
    @Test
    public void testAsetaRflistNimi() {
        try {
            t.asetaNimi("uusinimi.rflist");
            t.luoTiedosto();
            File f = new File("uusinimi.rflist");
            assertTrue(f.exists());
            f.delete();
        } catch (Exception ex) {
            fail("tiedoston nimen vaihto");
        }
    }

}
