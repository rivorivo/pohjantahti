/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtumini.UI;

import java.util.Locale;
import javax.rmi.CORBA.Stub;
import ohtumini.io.ConsoleIO;
import ohtumini.io.IO;
import ohtumini.io.StubIO;
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
public class KyselyTest {
    
    private Kysely kysely;
    private StubIO stub;
    
    @Before
    public void setUp() {
        stub = new StubIO("", "y");
        kysely = new Kysely(stub);
    }
    
    
}
