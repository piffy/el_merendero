/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Eddy
 */
public class OrdineDiClasseTest {

    /**
     * Test of constructor, of class OrdineDiClasse.
     */
    @Test
    public void testCostructor() {
        try{
        OrdineDiClasse odc = new OrdineDiClasse();
        }catch(Exception e){
            assertEquals("Test per il costruttore.","L'ordine deve contenere il nome della classe.",e.getMessage());
        }
    }
    
    /**
     * Test of getTotale method, of class OrdineDiClasse.
     */
    @Test
    public void testGetTotale() {
        OrdineDiClasse odc = new OrdineDiClasse("4B Info");
        Merenda m = new Merenda(4.3f, "Panino 1");
        Listamerende lm = new Listamerende(m);
        Ordine o = new Ordine("Manfredini");
        o.setMerendeOrdinate(lm);
        odc.add(o);
        lm = new Listamerende(new Merenda(5.6f, "Panino 2"));
        o = new Ordine("Eddy");
        o.setMerendeOrdinate(lm);
        odc.add(o);
        Float expected = 9.9f;
        assertEquals("Test per il totale dell'ordine", expected, odc.getTotale(), 0.001);
    }

    /**
     * Test of toString method, of class OrdineDiClasse.
     */
    @Test
    public void testToString() {
        OrdineDiClasse odc = new OrdineDiClasse("4B Info");
        Merenda m = new Merenda(4.3f, "Panino 1");
        Listamerende lm = new Listamerende(m);
        Ordine o = new Ordine("Manfredini");
        o.setMerendeOrdinate(lm);
        odc.add(o);
        lm = new Listamerende(new Merenda(5.6f, "Panino 2"));
        o = new Ordine("Eddy");
        o.setMerendeOrdinate(lm);
        odc.add(o);
        String expected = "4B Info\nOrdine: Manfredini\nNome = Panino 1  Prezzo = 4.3\n\nOrdine: Eddy\nNome = Panino 2  Prezzo = 5.6\n\n";
        assertEquals("Test per l'ordine di classe", expected, odc.toString());
    }
}
