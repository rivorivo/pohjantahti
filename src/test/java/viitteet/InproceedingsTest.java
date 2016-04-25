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
 * @author rivorivo
 */
public class InproceedingsTest {

    private Inproceedings inpro;
    private String[] kentat;
    String[] avain;

    @Before
    public void setUp() {
        inpro = new Inproceedings();
    }

    @Test
    public void pakollisetToimii() {
        kentat = inpro.kentat();
        avain = inpro.getAvaimet();
        for (int i = 0; i < 4; i++) {
            assertTrue(inpro.onkoPakollinen(kentat[i]));
        }
        for (int i = 4; i < 7; i++) {
            assertFalse(inpro.onkoPakollinen(kentat[i]));
        }
    }

    @Test
    public void lisaaTietoToimiiOikein() {
        kentat = inpro.kentat();
        avain = inpro.getAvaimet();
        inpro.lisaaTieto("author", "0");
        assertTrue("0" == avain[0]);
    }

    @Test
    public void lisaaTietoEiTeeMitaanJosTietoLoytyyJo() {
        kentat = inpro.kentat();
        avain = inpro.getAvaimet();
        inpro.lisaaTieto("author", "0");
        inpro.lisaaTieto("author", "1");
        assertTrue(null == avain[1]);
    }

    @Test
    public void lueTietoToimiiOikein() {
        kentat = inpro.kentat();
        avain = inpro.getAvaimet();
        inpro.lisaaTieto("author", "0");
        inpro.lueTieto("author");
        assertEquals("0", avain[0]);
    }

    @Test
    public void lueTietoTestPalauttaaNullJosTietoEiLoydy() {
        kentat = inpro.kentat();
        avain = inpro.getAvaimet();
        inpro.lisaaTieto("author", "0");
        assertEquals(null, inpro.lueTieto("koira"));
    }

    @Test
    public void annaViitteenTyypinNimiPalauttaaViitteenTyypin() {
        assertEquals(inpro.annaViitteenTyypinNimi(), "Inproceedings");
    }

}
