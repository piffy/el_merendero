package el_merendero;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bravo, palma
 * @version 1
 */
public class OrdineTest {

    @Test
    public void testSetResto() {
        Ordine Test = new Ordine("Marco");
        Test.getMerendeOrdinate().add(new Merenda(3.5f, "panino"));
        Test.getMerendeOrdinate().add(new Merenda(1.5f, "focaccia"));
        Test.setSoldiForniti(10f);
        Test.setResto();
        assertEquals(5f, 0.1, Test.getResto());
    } 

    @Test
    public void ControlloGiustoImporto() {
        Ordine controll = new Ordine("Giulia");
        controll.getMerendeOrdinate().add(new Merenda(3.5f, "panino"));
        controll.getMerendeOrdinate().add(new Merenda(1.5f, "focaccia"));
        controll.setSoldiForniti(10f);
        assertEquals(true,controll.ControlloGiustoImporto());
    }
    
}
