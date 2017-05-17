/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahooapi;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JorgeContreras
 */
public class YahooAPITest {
    
    public YahooAPITest() {
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
     * Test of uploadHistory method, of class YahooAPI.
     */
    @Test
    public void testUploadHistory() {
        System.out.println("uploadHistory");
        String d = "";
        String o = "";
        String h = "";
        String l = "";
        String c = "";
        String v = "";
        String a = "";
        YahooAPI.uploadHistory(d, o, h, l, c, v, a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
