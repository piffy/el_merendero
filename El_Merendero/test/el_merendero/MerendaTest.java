/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author piffy
 */
public class MerendaTest {
    
  @Test
    public void testAdd() {
        Ordine prova = new Ordine();
        prova.add(new Merenda("panino",2.5f));
        assertEquals("panino", prova.getFirst().getNome());
        assertEquals(2.5f, 0.1, prova.getFirst().getPrezzo());
    }

    @Test
    public void testRemove() {
        Listamerende prova = new Listamerende();
        prova.add(new Merenda("panino",2.5f));
        prova.add(new Merenda("focaccia",1.5f));
        prova.remove("panino");
        assertEquals("focaccia", prova.getNext().getProdotto().getNome());
        assertEquals(1.5f, 0.1, prova.getNext().getProdotto().getPrezzo());
    }

    @Test
    public void testGetTotale() {
        Listamerende prova = new Listamerende();
        prova.add(new Merenda("panino",2.5f));
        prova.add(new Merenda("focaccia",1.5f));
        assertEquals(4f, 0.1, prova.getTotale());
    }

    @Test
    public void testSetResto() {
        Ordine prova = new Ordine("Paolo");
        prova.getMerendeOrdinate().add(new Merenda("panino",2.5f));
        prova.getMerendeOrdinate().add(new Merenda("focaccia",1.5f));
        prova.setSoldiForniti(10f);
        prova.setResto();
        assertEquals(6f, 0.1, prova.getResto());
    }

    @Test
    public void ControlloGiustoImporto() {
        Ordine prova = new Ordine("Simone");
        prova.getMerendeOrdinate().add(new Merenda("panino",2.5f));
        prova.getMerendeOrdinate().add(new Merenda("focaccia",1.5f));
        prova.setSoldiForniti(10f);
        assertEquals(true,prova.ControlloGiustoImporto());
    }
}
