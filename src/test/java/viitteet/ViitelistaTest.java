package viitteet;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViitelistaTest {
    
    public ViitelistaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lisaaViiteListaan method, of class Viitelista.
     */
    @Test
    public void testLisaaViiteListaan() {
        Viite v = new Article();
        v.setTunniste("TestiViite");
        Viite v2 = new Book();
        v2.setTunniste("TestiViite2");
        Viitelista lista = new Viitelista("lista");
        
        lista.lisaaViiteListaan(v);
        lista.lisaaViiteListaan(v2);
        
        assertNotNull("Lisätty viite ei löytynyt", lista.haeViite("TestiViite"));
        assertNotNull("Toka viite ei löytynyt", lista.haeViite("TestiViite2"));
        assertNull("Olematon Viite löytynyt", lista.haeViite("TestiViite3"));
    }

    /**
     * Test of poistaViiteListasta method, of class Viitelista.
     */
    @Test
    public void testPoistaViiteListasta() {
        Viite v = new Article();
        v.setTunniste("TestiViite");
        Viitelista lista = new Viitelista("lista");
        lista.lisaaViiteListaan(v);
        lista.poistaViiteListasta("TestiViite");
        assertNull("Lisätty viite ei poistunut", lista.haeViite("TestiViite"));
        lista.poistaViiteListasta("TestiViite2");
        lista.poistaViiteListasta("TestiViite");
        
    }
    
}
