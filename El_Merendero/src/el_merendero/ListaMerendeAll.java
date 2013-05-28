/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b11g12
 */
public class ListaMerendeAll {

    private String prodotti[] /*= {"Bombolone", "Panino al salame"}*/;
    private float prezzi[] /*= {0.90f, 1.30f}*/;

    public ListaMerendeAll() throws FileNotFoundException {
        int i = 0;
        int j=0;
        int k=0;
        String s;
        prodotti = new String[30];
        prezzi = new float[30];
        // NOTA: Non mettete i path in stile windows. Java è in grado di utilizzare lo stile Unix/web
        BufferedReader reader = new BufferedReader(new FileReader(("."+File.separator+"config"+File.separator+"merende"+File.separator).concat("merende.txt")));
        try {
            while ((s = reader.readLine()) != null) {
                if (i % 2 != 0) {
                    prezzi[j] = Float.parseFloat(s);
                    j++;
                } else {
                    prodotti[k] = s;
                    k++;
                }
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ListaMerendeAll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float[] getPrezzi() {
        return prezzi;
    }

    public void setPrezzi(float[] prezzi) {
        this.prezzi = prezzi;
    }

    public String[] getProdotti() {
        return prodotti;
    }

    public void setProdotti(String[] prodotti) {
        this.prodotti = prodotti;
    }
}
