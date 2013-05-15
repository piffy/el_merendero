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
public class Ordine extends LinkedList<Merenda>{

    private String NomeAcquirente;
    private float SoldiForniti;
    private LinkedList<Merenda> MerendeOrdinate;
    private float resto;

   
    
    public float getResto() {
        return resto;
    }

    public void setResto() {
        if (this.ControlloGiustoImporto()) {
            resto = (float) SoldiForniti - getTotale();
        }
    }

    public boolean ControlloGiustoImporto() {
        if (SoldiForniti < getTotale()) {
            return false;
        } else {
            return true;
        }
    }

    public Ordine(String NomeAcquirente) {
        this.NomeAcquirente = NomeAcquirente;
        MerendeOrdinate = new LinkedList<Merenda>();
    }

    public Ordine() {
    }

    public LinkedList<Merenda> getMerendeOrdinate() {
        return MerendeOrdinate;
    }

    public void setMerendeOrdinate(LinkedList<Merenda> MerendeOrdinate) {
        this.MerendeOrdinate = MerendeOrdinate;
    }

    public String getNomeAcquirente() {
        return NomeAcquirente;
    }

    public void setNomeAcquirente(String NomeAcquirente) {
        this.NomeAcquirente = NomeAcquirente;
    }

    public float getSoldiForniti() {
        return SoldiForniti;
    }

    public void setSoldiForniti(float SoldiForniti) {
        this.SoldiForniti = SoldiForniti;
    }

    public float getTotale() {
        float tot = 0f;
        for (Merenda m : MerendeOrdinate) {
            tot += m.getPrezzo();
        }
        return tot;
    }

    @Override
    public String toString() {
        return NomeAcquirente + "\n" + MerendeOrdinate;
    }
}
