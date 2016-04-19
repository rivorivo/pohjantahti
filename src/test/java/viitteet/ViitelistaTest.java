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
    public void testAdd() {
        Viite v = new Article();
        v.setTunniste("TestiViite");
        Viite v2 = new Book();
        v2.setTunniste("TestiViite2");
        Viitelista lista = new Viitelista("lista");
        
        lista.add(v);
        lista.add(v2);
        
        assertNotNull("Lisätty viite ei löytynyt", lista.get("TestiViite"));
        assertNotNull("Toka viite ei löytynyt", lista.get("TestiViite2"));
        assertNull("Olematon Viite löytynyt", lista.get("TestiViite3"));
    }

    /**
     * Test of remove method, of class Viitelista.
     */
    @Test
    public void testRemove() {
        Viite v = new Article();
        v.setTunniste("TestiViite");
        Viitelista lista = new Viitelista("lista");
        lista.add(v);
        lista.remove("TestiViite");
        assertNull("Lisätty viite ei poistunut", lista.get("TestiViite"));
        lista.remove("TestiViite2");
        lista.remove("TestiViite");
        
    }
    
}
