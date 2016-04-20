package ohtumini.io;

import java.util.ArrayList;
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
public class StubIOTest {

    StubIO i;

    public StubIOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        i = new StubIO("testi", "1");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of print method, of class StubIO.
     */
    @Test
    public void testPrint() {
        i.print("testi");
        assertEquals("testi", i.getPrints().get(0));
    }

    /**
     * Test of readInt method, of class StubIO.
     */
    @Test
    public void testReadInt() {
        i.readLine(">");
        assertEquals(1, i.readInt(""));
    }

    /**
     * Test of getPrints method, of class StubIO.
     */
    @Test
    public void testGetPrints() {
        i.print("testi");
        i.print("testi");
        i.print("testi");
        assertEquals(3, i.getPrints().size());
    }

    /**
     * Test of readLine method, of class StubIO.
     */
    @Test
    public void testReadLine() {
        assertEquals("testi", i.readLine(""));
    }

    /**
     * Test of readLine method, of class StubIO.
     */
    @Test
    public void testReadLineEiPrintteja() {
        i.readLine("");
        i.readInt("");
        assertEquals("tallentamatta-lopeta", i.readLine(""));
    }

}
