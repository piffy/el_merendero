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
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Eddy, Manfredini, Bigliardi
 * @author Oukms, Brunelli, Tursi
 * @version 0.02
 */
public class Stampante implements Printable {

    private final int MARGINE = 150;
    private final int MARGINE_DESTRO = 500;
    private final int SPAZIATURA = 15;
//    private LinkedList<String> merende;
//    private LinkedList<Integer> numeri;
//    private LinkedList<Float> prezzi;
    private Image image1;
    private Image image2;
    private String classe;
    private String aula;
    private Calendar data;
    private OrdineDiClasse OrdineGen;

    /**
     * Costruttore di default che istanzia le liste e carica il logo
     */
    public Stampante() {
        image1 = new ImageIcon(this.getClass().getResource("Logo.jpg")).getImage();
        OrdineGen=new OrdineDiClasse();
        OrdineGen.add(new Ordine());
    }
    
    public void setStampaOrdine(OrdineDiClasse p){
        
        this.OrdineGen=p;
    }

    public OrdineDiClasse getOrdineGen() {
        return OrdineGen;
    }

    public void setOrdineGen(OrdineDiClasse OrdineGen) {
        this.OrdineGen = OrdineGen;
    }
    

    /**
     * Metodo che genera il layout della pagina da stampare
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
        appoggio = "Studente " + OrdineGen.getOrdine().getNomeAcquirente();
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 35);
        appoggio = "Classe " + classe;
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 50);
        //appoggio = data.get(Calendar.HOUR_OF_DAY) + ":" + data.get(Calendar.MINUTE) + " " + data.get(Calendar.DATE) + "/" + data.get(Calendar.MONTH) + "/" + data.get(Calendar.YEAR);
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 65);
        appoggio = "Aula " + aula;
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 80);

        Ordine comodo=OrdineGen.getOrdine();
        int i;
        for (i = 0; i < OrdineGen.getOrdine().getMerendeOrdinate().size(); i++) {
            appoggio = comodo.get(i).getPrezzo() + " €";
            g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 120 + SPAZIATURA * i);
            int lungi = fm.stringWidth(appoggio);
            appoggio =  OrdineGen.getOrdine().getMerendeOrdinate().get(i).getNumero() +" - "+comodo.get(i).getNome();
            while ( MARGINE_DESTRO - MARGINE - fm.stringWidth(appoggio) - lungi > 10) {
                appoggio+=".";
            }
            g.drawString(appoggio, MARGINE, 120 + SPAZIATURA * i);
        }
        
        g.setFont(new Font("suns", Font.BOLD, 12));
        appoggio = "Totale: " + OrdineGen.getOrdine().getTotale() + " €";
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 120 + SPAZIATURA * i);
        appoggio = "Soldi forniti: " + OrdineGen.getOrdine().getSoldiForniti() + " €";
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio) - 5, 140 + SPAZIATURA * i);
        appoggio = "Resto: " + OrdineGen.getOrdine().getResto() + " €";
        g.drawString(appoggio, MARGINE_DESTRO - fm.stringWidth(appoggio), 160 + SPAZIATURA * i);

        return PAGE_EXISTS;
    }

    /**
     * Metodo che aggiunge una merenda all'ordine
     * @param merenda Nome della merenda
     * @param prezzo Prezzo della merenda
     */
    public void add(String merenda, float prezzo) {
        this.OrdineGen.getOrdine().add(new Merenda(merenda,prezzo));
    }

    /**
     * Metodo obbligatorio che aggiunge il nome della classe all'ordine
     * @param classe Nome della classe
     */
    public void addClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Metodo obbligatorio che aggiunge il l'ora della consegna all'ordine
     * @param data Giorno e ora della consegna dell'ordine
     */
    public void addData(GregorianCalendar data) {
        this.data = data;
    }

    /**
     * Metodo obbligatorio che aggiunge l'aula della consegna all'ordine
     * @param aula Aula della consegna
     */
    public void addAula(String aula) {
        this.aula = aula;
    }

    /**
     * Metodo obbligatorio che aggiunge i soldi forniti all'ordine
     * @param soldiForniti valore float dei soldi forniti
     */

    void print() throws PrinterException {
        if (classe == null) {
            JOptionPane.showMessageDialog(null, "Specificare una classe prima della stampa", "Classe", 2);
            return;
        }
        if (data == null) {
            JOptionPane.showMessageDialog(null, "Specificare una data prima della stampa", "Data", 2);
            return;
        }
        if (aula == null) {
            JOptionPane.showMessageDialog(null, "Specificare un'aula prima della stampa", "Aula", 2);
            return;
        }
        if (OrdineGen.getOrdine().getSoldiForniti() == 0) {
            JOptionPane.showMessageDialog(null, "Specificare i soldi forniti prima della stampa", "Soldi Forniti", 2);
            return;
        }
        if (OrdineGen.getOrdine().getMerendeOrdinate().size() == 0) {
            JOptionPane.showMessageDialog(null, "Specificare una merenda prima della stampa", "Merenda", 2);
            return;
        }
        if(OrdineGen.getOrdine().getNomeAcquirente()==null){
            JOptionPane.showMessageDialog(null, "Specificare il nome acquirente", "Nome acquirente", 2);
            return;           
        }
        if(OrdineGen.getOrdine().getMerendeOrdinate()==null){
            JOptionPane.showMessageDialog(null, "Specificare le merende ordinate", "merende ordinate", 2);
            return;           
        }  

        // Creates and returns a PrinterJob which is initially associated with the default printer.
        PrinterJob pj = PrinterJob.getPrinterJob();
        // Sets the name of the document to be printed.
        pj.setJobName("TestStampa");
        // Presents a dialog to the user for changing the properties of the print job.
        pj.printDialog();
        // Calls painter to render the pages.
        pj.setPrintable(this);

        pj.print();

        classe = null;
        data = null;
        aula = null;
    }

        
    /**
     * Main di prova per la classe stampante
     * @param args the command line arguments
     * @throws PrinterException generata in caso di mancanza di stampante
     */
    public static void main(String[] args) throws PrinterException {
        Stampante st = new Stampante();
        Ordine o=new Ordine("Tursi");
        Listamerende l=new Listamerende();
        Merenda m=new Merenda(0.5f,"pizza");
        Merenda z=new Merenda(3f,"pizza");
        l.add(m);
        l.add(z);
        GregorianCalendar d = new GregorianCalendar(2013, 11, 22, 23, 12);
        st.addData(d);
        st.addClasse("4B Info");
        st.addAula("216");
        st.getOrdineGen().getOrdine().add(new Merenda("Cotoletta",5.80f));
        st.getOrdineGen().getOrdine().add(new Merenda("Hot-Dog",1.20f));
        st.getOrdineGen().getOrdine().add(new Merenda("Panino",3.60f));;
        st.getOrdineGen().getOrdine().add(new Merenda("Piadina",2.500f));
        st.print();
        // Crea im ordine personale
        //Chioai
    }
}
