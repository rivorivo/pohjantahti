package viitteet;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViitelistaTest {
    
    private Viitelista lista;
    private String nimi = "lide";
    public ViitelistaTest() {
        lista = new Viitelista(nimi);        
    }
    
    public void alustaViite(){
        
    }
    
    
    @Test
    public void lisaaminenToimii(){
        Article a = new Article(); 
        a.tunniste="t";      
        lista.add(a);          
        assertFalse(lista.getViitteet().isEmpty());        
    }
    
    @Test
    public void listastaPoistaminenToimii(){
         Article a = new Article(); 
        a.tunniste="t";      
        lista.add(a); 
        assertFalse(lista.getViitteet().isEmpty()); 
        lista.remove("t");
        assertTrue(lista.getViitteet().isEmpty());
    }
    
    @Test
    public void getViiteToimii(){
        Article a = new Article(); 
        a.tunniste="t"; 
        lista.add(a);
        assertEquals(lista.get("t"),a);
    }
    
    @Test
    public void getViitteetToimii(){
        Article a = new Article(); 
        a.tunniste="t"; 
        Book b = new Book();
        b.tunniste="t2";
        ArrayList<Viite> viitteet = new ArrayList<>();
        viitteet.add(a);
        viitteet.add(b);
        lista.add(a);
        lista.add(b);
                assertEquals(lista.getViitteet(),viitteet);    
    }
    
    @Test
    public void getNimiToimii(){
        assertEquals(lista.getNimi(),"lide");
    }
    
     @Test
    public void sizeToimii(){
         Article a = new Article(); 
        a.tunniste="t"; 
        Book b = new Book();
        b.tunniste="t2";
        Inproceedings i = new Inproceedings();
        i.tunniste="t3";
        lista.add(i);
        lista.add(a);
        lista.add(b);
        assertEquals(lista.size(),3);
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
