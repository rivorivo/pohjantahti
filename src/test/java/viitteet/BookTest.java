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
 * @author samukaup
 */
public class BookTest {
    
    private Book book;
    @Before
    public void setUp() {
        book = new Book();
    }
    
    @Test
    public void onkoPakollinenToimiiOikein() {
        String[] kentat = book.kentat();
        for (int i = 0; i < 4; i++) {
            assertTrue(book.onkoPakollinen(kentat[i]));
        }
        for (int i = 4; i < 10; i++) {
            assertFalse(book.onkoPakollinen(kentat[i]));
        }
        
    }
    
    @Test
    public void lisaaTietoEiTeeMitaanJosTietoLoytyyJo() {
        String[] kentat = book.kentat();
        String[] avain = book.getAvaimet();
        book.lisaaTieto("book", "0");
        book.lisaaTieto("book", "1");
        assertTrue(null == avain[1]);
    }
    
    @Test
    public void lueTietoToimiiOikein() {
        String[] kentat = book.kentat();
        String[] avain = book.getAvaimet();
        book.lisaaTieto("book", null);
        book.lueTieto("book");
        assertEquals(null, avain[0]);
    }
    
    @Test
    public void lueTietoTestPalauttaaNullJosTietoEiLoydy() {
        String[] kentat = book.kentat();
        String[] avain = book.getAvaimet();
        book.lisaaTieto("book", "0");
        assertEquals(null, book.lueTieto("koira"));
    }
    
    
    @Test
    public void annaViitteenTyypinNimiPalauttaaViitteenTyypin() {
        assertEquals(book.annaViitteenTyypinNimi(), "Book");
    }
}
