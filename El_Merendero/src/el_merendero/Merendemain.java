/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author b11g12
 */
public class Merendemain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Merenda[] ar = new Merenda[10];
        ar[0] = new Merenda("pan",5.0f);
        ar[1] = new Merenda("pasaaan",3.0f);
        ar[2] = new Merenda("painon",4.5f);
        Listamerende li = new Listamerende();
        li.add(ar[0]);
        li.add(ar[1]);
        li.add(ar[2]);
        li.remove("poninan");
        System.out.println(li);
        System.out.println(li.getTotale());
//-------------------------------------------------------------------------------------------------
        Ordine io = new Ordine("Simone");
        Merenda m1 = new Merenda( "cane",1.2f);
        io.getMerendeOrdinate().add(m1);
        io.getMerendeOrdinate().add(ar[1]);
        ListaMerendeAll p = new ListaMerendeAll();
        String[] f = p.getProdotti();
        System.out.println(f[0]);
        System.out.println(f[1]);
        float price[]=p.getPrezzi();
        System.out.println(price[0]);
    }
    //--------------------------------------------------------------------------------------
}
