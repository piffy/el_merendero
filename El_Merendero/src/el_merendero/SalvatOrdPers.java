/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author piffy
 */
public class SalvatOrdPers {

    public static void Salvataggio(Ordine p) {
        try {
            FileOutputStream file = new FileOutputStream("./config/ordini/OrdinePers.txt");
            PrintStream Output = new PrintStream(file);
            Output.println(p);


        } catch (IOException e) {
            System.out.println("Errore: " + e);
            System.exit(1);
        }
    }
    public static void main (String args[]) {
        Ordine prova1=new Ordine("Simone");
        prova1.add(new Merenda("panino",2f,1));
        prova1.add(new Merenda("pizza",1.5f,3));
        prova1.add(new Merenda("pizza",1.5f,1));
        SalvatOrdPers.Salvataggio(prova1);
    }
}
