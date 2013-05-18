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
public class SalvatOrdClas {

    public static void Salvataggio(OrdineDiClasse p) {
        try {
            FileOutputStream file = new FileOutputStream("./config/ordini/OrdineClas.txt");
            PrintStream Output = new PrintStream(file);
            Output.println(p);


        } catch (IOException e) {
            System.out.println("Errore: " + e);
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        OrdineDiClasse prova1 = new OrdineDiClasse("4B INFO");
        Ordine p1 = new Ordine("Simone");
        Ordine p2 = new Ordine("Mattia");
        Ordine p3 = new Ordine("Edoardo");
        p1.add(new Merenda("panino", 2f, 1));
        p1.add(new Merenda("cotoletta", 5f, 4));
        p2.add(new Merenda("pizza", 1.5f, 3));
        p3.add(new Merenda("pizza", 1.5f, 1));
        prova1.add(p1);
        prova1.add(p2);
        prova1.add(p3);
        SalvatOrdClas.Salvataggio(prova1);
    }
}
