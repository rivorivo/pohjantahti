package ohtumini.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ConsoleIOTest {

    Scanner s;
    IO io;

    public ConsoleIOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        io = new ConsoleIO();
        s = new Scanner(System.in);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of print method, of class ConsoleIO.
     */
    @Test
    public void testPrint() {
        try {
            io.print(null);
            io.print("");
            io.print("ääööö asdf");

        } catch (Exception ex) {
            fail("Printtaus ei onnistu");
        }
    }

    /**
     * Test of readInt method, of class ConsoleIO.
     */
    @Test
    public void testReadInt() {
        InputStream si = System.in;
        try {
            System.setIn(new ByteArrayInputStream("1".getBytes()));
            IO ioo = new ConsoleIO();
            int a = ioo.readInt("");
            assertEquals(1, a);
        } finally {
            System.setIn(si);
        }
    }

    /**
     * Test of readLine method, of class ConsoleIO.
     */
    @Test
    public void testReadLine() {
        InputStream si = System.in;
        try {
            System.setIn(new ByteArrayInputStream("testi lause tämä on.".getBytes()));
            IO ioo = new ConsoleIO();
             
            assertEquals("testi lause tämä on.", ioo.readLine(""));
        } finally {
            System.setIn(si);
        }

    }

}
