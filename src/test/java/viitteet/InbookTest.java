package viitteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import viitteet.Inbook;

/**
 *
 * @author samukaup
 */
public class InbookTest {
    
    private Inbook inbook;
    @Before
    public void setUp() {
        inbook = new Inbook();
    }
    
    @Test
    public void onkoPakollinenToimiiOikein() {
        String[] kentat = inbook.kentat();
        for (int i = 0; i < 5; i++) {
            assertTrue(inbook.onkoPakollinen(kentat[i]));
        }
        for (int i = 6; i < 12; i++) {
            assertFalse(inbook.onkoPakollinen(kentat[i]));
        }
        
    }
    @Test
    public void lisaaTietoEiTeeMitaanJosTietoLoytyyJo() {
        String[] kentat = inbook.kentat();
        String[] avain = inbook.getAvaimet();
        inbook.lisaaTieto("inbook", "0");
        inbook.lisaaTieto("inbook", "1");
        assertTrue(null == avain[1]);
    }
    
    @Test
    public void lueTietoToimiiOikein() {
        String[] kentat = inbook.kentat();
        String[] avain = inbook.getAvaimet();
        inbook.lisaaTieto("inbook", null);
        inbook.lueTieto("inbook");
        assertEquals(null, avain[0]);
    }
    
    @Test
    public void lueTietoTestPalauttaaNullJosTietoEiLoydy() {
        String[] kentat = inbook.kentat();
        String[] avain = inbook.getAvaimet();
        inbook.lisaaTieto("inbook", "0");
        assertEquals(null, inbook.lueTieto("koira"));
    }
    
    
    @Test
    public void annaViitteenTyypinNimiPalauttaaViitteenTyypin() {
        assertEquals(inbook.annaViitteenTyypinNimi(), "Inbook");
    }
}
