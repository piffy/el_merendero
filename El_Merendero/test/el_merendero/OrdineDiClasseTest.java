/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Eddy
 */
public class OrdineDiClasseTest {
    
    /**
     * Test of main method, of class OrdineDiClasse.
     */
    @Test
    public void testMain() {
        OrdineDiClasse odc = new OrdineDiClasse("4B Info");
        Merenda m = new Merenda(4.3f, "Panino 1") ;
        Listamerende lm = new Listamerende(m);
        Ordine o = new Ordine("Manfredini");
        o.setMerendeOrdinate(lm);
        odc.add(o);
        lm = new Listamerende(new Merenda(5.6f, "Panino 2"));
        o = new Ordine("Eddy");
        o.setMerendeOrdinate(lm);
        odc.add(o);
        System.out.println(odc);
        Float expected = 9.9f;
        assertEquals("Test per l'ordine di classe",expected,odc.getTotale(),0.001);
    }
}
