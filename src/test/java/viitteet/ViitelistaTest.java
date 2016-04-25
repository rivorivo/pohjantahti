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
    
     @Test
    public void getViiteToimii(){
        Article a = new Article(); 
        a.tunniste="t"; 
        lista.add(a);
        assertEquals(a,lista.get("t"));
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
        assertEquals(viitteet,lista.getViitteet());    
    }
    
    @Test
    public void getNimiToimii(){
        assertEquals(lista.getNimi(),nimi);
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
    
    @Test
    public void setNimiToimii(){
        assertEquals("lide",lista.getNimi());
        lista.setNimi("listis");
        assertEquals("listis",lista.getNimi());
    }
    
    //viiteiteraattoritestit
    public ViiteIteraattori itis;
    
    @Test
    public void hasNextToimii(){
        Article a = new Article(); 
        a.tunniste="t"; 
        Book b = new Book();
        b.tunniste="t2";
        Inproceedings i = new Inproceedings();
        i.tunniste="t3";
        lista.add(i);
        lista.add(a);
        lista.add(b);
        itis= new ViiteIteraattori(lista);
        assertEquals(true,itis.hasNext());        
    }
    
    @Test
    public void removeToimii(){
        Article a = new Article(); 
        a.tunniste="t"; 
        lista.add(a);
        itis= new ViiteIteraattori(lista);
        itis.remove();
        assertEquals(false,itis.hasNext());        
    }
    @Test
    public void nextToimii(){
        Article a = new Article(); 
        a.tunniste="t"; 
        Book b = new Book();
        b.tunniste="t2";
        Inproceedings i = new Inproceedings();
        i.tunniste="t3";
        lista.add(i);
        lista.add(a);
        lista.add(b);
        itis= new ViiteIteraattori(lista);
        assertEquals(itis.next(),i);
    }
           
}
