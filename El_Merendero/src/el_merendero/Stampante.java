/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Eddy, Manfredini, Bigliardi
 * @version 0.01
 */
public class Stampante implements Printable {

    private final int MARGINE = 150;
    private final int MARGINE_DESTRO = 500;
    private final int RIGHE_PER_PAGINA=20;
    private int OFFSET_ORDINE = 0;
    private final int SPAZIATURA = 20;
    private OrdineDiClasse ordine;
    private Image image1;
    private Image image2;
    private int pagine;
    private int Iordine;

    /**
     * Costruttore di default che istanzia le liste e carica il logo
     */
    public Stampante() {
        image1 = new ImageIcon(this.getClass().getResource("Logo.jpg")).getImage();
    }

    /**
     * Metodo che genera il layout della pagina da stampare
     *
     * @param g Layout della pagina
     * @param pf Formato della pagina
     * @param page Numero di pagine
     * @return una costante che indica alla stampante quando deve finire
     */
    @Override
    public int print(Graphics g, PageFormat pf, int page) {
        if (page > chePaginaSono(ordine.size()-1)) {
            return NO_SUCH_PAGE;
        }
        int righe = 25;
        g.drawImage(image1, 50, 20, null);
        image2 = new ImageIcon(this.getClass().getResource("Logo2.jpg")).getImage();
        g.drawImage(image2, 215, 20, null);

        FontMetrics fm = g.getFontMetrics();
        String appoggio;
        appoggio = "Classe " + ordine.getClasse();
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 50);
        appoggio = ordine.getData().get(Calendar.HOUR_OF_DAY) + ":" + ordine.getData().get(Calendar.MINUTE) + " " + ordine.getData().get(Calendar.DATE) + "/" + ((ordine.getData().get(Calendar.MONTH)) + 1) + "/" + ordine.getData().get(Calendar.YEAR);
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 65);
        appoggio = "Aula " + ordine.getAula();
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 80);
        int i = 0, o = 0;
        OFFSET_ORDINE = 0;
        for (o = 0; o < ordine.size(); o++) {
            if (chePaginaSono(o) == page) {
                System.out.println(chePaginaSono(o)+" - "+o);
                appoggio = "Studente " + ordine.get(o).getNomeAcquirente();
                g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 110 + OFFSET_ORDINE);
                for (i = 0; i < ordine.get(o).size(); i++) {
                    appoggio = ordine.get(o).get(i).getPrezzo() + " €";
                    g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 135 + OFFSET_ORDINE);
                    int lungi = fm.stringWidth(appoggio);
                    appoggio = ordine.get(o).get(i).stampaSenzaPrezzo();
                    while (MARGINE_DESTRO - MARGINE - fm.stringWidth(appoggio) - lungi > 10) {
                        appoggio += ".";
                    }
                    g.drawString(appoggio, MARGINE, 135 + OFFSET_ORDINE);
                    OFFSET_ORDINE += SPAZIATURA;
                }
                OFFSET_ORDINE += SPAZIATURA * 2;
            }
        }
        //System.err.println(ordine.size() + " - " + Iordine);
        if (chePaginaSono(ordine.size()-1) == page) {
            g.setFont(new Font("suns", Font.BOLD, 12));
            appoggio = "Totale: " + ordine.getTotale() + " €";
            g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 120 + OFFSET_ORDINE);
            appoggio = "Soldi forniti: " + ordine.getSoldiForniti() + " €";
            g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio) - 5, 140 + OFFSET_ORDINE);
            appoggio = "Resto: " + (ordine.getFirst().getSoldiForniti() - ordine.getTotale()) + " €";
            g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 160 + OFFSET_ORDINE);
        }
        return PAGE_EXISTS;
    }

    int chePaginaSono(int no) {
        int np = 0;
        int righe = RIGHE_PER_PAGINA;
        for (Ordine o : ordine) {
            righe -= o.getNumeroMerende();
            if(o == ordine.get(ordine.size()-1) && righe < 5){
                np++;
                break;
            } 
            if (righe < 0) {
                righe = RIGHE_PER_PAGINA;
                np++;
            }
            if (ordine.get(no) == o) {
                break;
            } 
        }
        return np;
    }

    void print() throws PrinterException {
        pagine = chePaginaSono(ordine.size()-1);
        Iordine = 0;
        if (ordine == null) {
            JOptionPane.showMessageDialog(null, "Speficiare un ordine prima della stampa", "Ordine", 2);
            throw new PrinterException();
        }
        if (ordine.getClasse() == null) {
            JOptionPane.showMessageDialog(null, "Speficiare una classe prima della stampa", "Classe", 2);
            throw new PrinterException();
        }
        if (ordine.getData() == null) {
            JOptionPane.showMessageDialog(null, "Speficiare una data prima della stampa", "Data", 2);
            throw new PrinterException();
        }
        if (ordine.getAula() == null) {
            JOptionPane.showMessageDialog(null, "Speficiare un'aula prima della stampa", "Aula", 2);
            throw new PrinterException();
        }
        if (ordine.getFirst().getSoldiForniti() == 0) {
            JOptionPane.showMessageDialog(null, "Speficiare i soldi forniti prima della stampa", "Soldi Forniti", 2);
            throw new PrinterException();
        }
        if (ordine.getFirst().size() == 0) {
            JOptionPane.showMessageDialog(null, "Speficiare una merenda prima della stampa", "Merenda", 2);
            throw new PrinterException();
        }
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("TestStampa");
        pj.setPrintable(this);
        if (pj.printDialog()) {
            pj.print();
        } else {
            throw new PrinterException();
        }
    }

    public void setOrdine(OrdineDiClasse ordine) {
        this.ordine = ordine;
    }

    /**
     * Main di prova per la classe stampante
     *
     * @param args the command line arguments
     * @throws PrinterException generata in caso di mancanza di stampante
     */
    public static void main(String[] args) throws PrinterException {
        Stampante st = new Stampante();
        GregorianCalendar d = new GregorianCalendar(2013, 11, 22, 23, 12);
        OrdineDiClasse odc = new OrdineDiClasse("4B Info");
        odc.setData(d);
        odc.setClasse("4B Info");
        odc.setAula("216");
        Ordine o = new Ordine("Manfredini");
        boolean[] cond = new boolean[2];
        cond[1] = true;
        o.setSoldiForniti(17.5f);
        o.add(new Merenda("Cotoletta", 5.80f, 2));
        o.add(new Merenda("Hot-Dog", 1.20f, 1, cond));
        o.add(new Merenda("Forno", 3.00f, 3));
        o.add(new Merenda("Piadina", 2.50f, 4));
        odc.add(o);
        for (int i = 0; i < 26; i++) {
            o = new Ordine("Eddy" + i);
            cond = new boolean[2];
            cond[1] = true;
            o.setSoldiForniti(17.5f);
            o.add(new Merenda("Cotoletta", 5.80f, 2));
            o.add(new Merenda("Hot-Dog", 1.20f, 1, cond));
            o.add(new Merenda("Forno", 3.00f, 3));
            //o.add(new Merenda("Piadina", 2.50f, 4));
            odc.add(o);
        }
        st.setOrdine(odc);
        st.print();
    }
}