/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.util.LinkedList;

/**
 *
 * @author b11g12
 */
public class OrdineDiClasse extends LinkedList<Ordine> {

    private String classe;

    public OrdineDiClasse() throws Exception {
        throw new Exception("L'ordine deve contenere il nome della classe.");
    }

    public OrdineDiClasse(String nomeclasse) {
        classe = nomeclasse;
    }

    public float getTotale() {
        float tot = 0f;
        for (Ordine o : this) {
            tot += o.getMerendeOrdinate().getTotale();
        }
        return tot;
    }

    @Override
    public String toString() {
        String s = classe + "\n";
        for (Ordine o : this) {
            s += "Ordine: " + o + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
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
    }
}
