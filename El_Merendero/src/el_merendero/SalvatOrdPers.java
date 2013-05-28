/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.io.File;
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
            FileOutputStream file = new FileOutputStream("."+File.separator+"config"+File.separator+"ordini"+File.separator+"OrdinePers.txt");
            PrintStream Output = new PrintStream(file);
            Output.println(p);


        } catch (IOException e) {
            System.out.println("Errore: " + e);
            System.exit(1);
        }
    }

}
