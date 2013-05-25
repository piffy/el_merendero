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
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Eddy, Manfredini, Bigliardi
 * @version 0.01
 */
public class Stampante implements Printable {

    private final int MARGINE = 150;
    private final int MARGINE_DESTRO = 500;
    private final int SPAZIATURA = 15;
    private OrdineDiClasse ordine;
    private Image image1;
    private Image image2;

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
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        g.drawImage(image1, 50, 20, null);
        image2 = new ImageIcon(this.getClass().getResource("Logo2.jpg")).getImage();
        g.drawImage(image2, 215, 20, null);

        FontMetrics fm = g.getFontMetrics();
        String appoggio;
        appoggio = "Classe " + ordine.getClasse();
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 50);
        appoggio = ordine.getData().get(Calendar.HOUR_OF_DAY) + ":" + ordine.getData().get(Calendar.MINUTE) + " " + ordine.getData().get(Calendar.DATE) + "/" + ((ordine.getData().get(Calendar.MONTH))+1) + "/" + ordine.getData().get(Calendar.YEAR);
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 65);
        appoggio = "Aula " + ordine.getAula();
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 80);
        if(ordine.size() == 1){
            appoggio = "Studente " + ordine.getFirst().getNomeAcquirente();
            g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 95);
        }

        int i;
        for (i = 0; i < ordine.getFirst().size(); i++) {
            appoggio = ordine.getFirst().get(i).getPrezzo() + " €";
            g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 120 + SPAZIATURA * i);
            int lungi = fm.stringWidth(appoggio);
            appoggio = ordine.getFirst().get(i).stampaSenzaPrezzo();
            while (MARGINE_DESTRO - MARGINE - fm.stringWidth(appoggio) - lungi > 10) {
                appoggio += ".";
            }
            g.drawString(appoggio, MARGINE, 120 + SPAZIATURA * i);
        }
        g.setFont(new Font("suns", Font.BOLD, 12));
        appoggio = "Totale: " + ordine.getTotale() + " €";
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 120 + SPAZIATURA * i);
        appoggio = "Soldi forniti: " + ordine.getFirst().getSoldiForniti() + " €";
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio) - 5, 140 + SPAZIATURA * i);
        appoggio = "Resto: " + (ordine.getFirst().getSoldiForniti() - ordine.getTotale()) + " €";
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 160 + SPAZIATURA * i);

        return PAGE_EXISTS;
    }

    void print() throws PrinterException {
        if (ordine == null) {
            JOptionPane.showMessageDialog(null, "Speficiare un ordine prima della stampa", "Ordine", 2);
            return;
        }
        if (ordine.getClasse() == null) {
            JOptionPane.showMessageDialog(null, "Speficiare una classe prima della stampa", "Classe", 2);
            return;
        }
        if (ordine.getData() == null) {
            JOptionPane.showMessageDialog(null, "Speficiare una data prima della stampa", "Data", 2);
            return;
        }
        if (ordine.getAula() == null) {
            JOptionPane.showMessageDialog(null, "Speficiare un'aula prima della stampa", "Aula", 2);
            return;
        }
        if (ordine.getFirst().getSoldiForniti() == 0) {
            JOptionPane.showMessageDialog(null, "Speficiare i soldi forniti prima della stampa", "Soldi Forniti", 2);
            return;
        }
        if (ordine.getFirst().size() == 0) {
            JOptionPane.showMessageDialog(null, "Speficiare una merenda prima della stampa", "Merenda", 2);
            return;
        }
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("TestStampa");
        pj.setPrintable(this);
        if (pj.printDialog()) {
            pj.print();
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
        odc.add(new Ordine("Manfredini"));
        odc.setData(d);
        odc.setClasse("4B Info");
        odc.setAula("216");
        odc.getFirst().setSoldiForniti(17.5f);
        boolean[] cond = new boolean[2];
        cond[1] = true;
        odc.getFirst().add(new Merenda("Cotoletta", 5.80f, 2));
        odc.getFirst().add(new Merenda("Hot-Dog", 1.20f, 1, cond));
        odc.getFirst().add(new Merenda("Forno", 3.00f, 3));
        odc.getFirst().add(new Merenda("Piadina", 2.50f, 4));
        st.setOrdine(odc);
        st.print();
    }
}