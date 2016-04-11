package viitteet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ArticleTest {
    private Article article;
    
    @Before
    public void setUp() {
        article = new Article();
    }
    
    @Test
    public void onkoPakollinenToimiiOikein() {
        String[] kentat = article.kentat();
        for (int i = 0; i < 5; i++) {
            assertTrue(article.onkoPakollinen(kentat[i]));
        }
        for (int i = 5; i < 10; i++) {
            assertFalse(article.onkoPakollinen(kentat[i]));
        }
        
    }
}
