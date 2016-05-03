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
public class BookletTest {
    
    private Booklet booklet;
    @Before
    public void setUp() {
        booklet = new Booklet();
    }
    
    @Test
    public void onkoPakollinenToimiiOikein() {
        String[] kentat = booklet.kentat();
            assertTrue(booklet.onkoPakollinen(kentat[1]));
        
        for (int i = 2; i < 6; i++) {
            assertFalse(booklet.onkoPakollinen(kentat[i]));
        }
        assertFalse(booklet.onkoPakollinen(kentat[0]));
        
    }
    @Test
    public void lisaaTietoEiTeeMitaanJosTietoLoytyyJo() {
        String[] kentat = booklet.kentat();
        String[] avain = booklet.getAvaimet();
        booklet.lisaaTieto("booklet", "0");
        booklet.lisaaTieto("booklet", "1");
        assertTrue(null == avain[1]);
    }
    
    @Test
    public void lueTietoToimiiOikein() {
        String[] kentat = booklet.kentat();
        String[] avain = booklet.getAvaimet();
        booklet.lisaaTieto("booklet", null);
        booklet.lueTieto("booklet");
        assertEquals(null, avain[0]);
    }
    
    @Test
    public void lueTietoTestPalauttaaNullJosTietoEiLoydy() {
        String[] kentat = booklet.kentat();
        String[] avain = booklet.getAvaimet();
        booklet.lisaaTieto("booklet", "0");
        assertEquals(null, booklet.lueTieto("koira"));
    }
    
    
    @Test
    public void annaViitteenTyypinNimiPalauttaaViitteenTyypin() {
        assertEquals(booklet.annaViitteenTyypinNimi(), "Booklet");
    }
}
