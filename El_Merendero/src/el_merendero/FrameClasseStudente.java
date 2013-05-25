/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author charlie
 */
public class FrameClasseStudente extends JFrame {

    private JComboBox boxClassi;
    private JComboBox boxStudenti;
    private String nomiClassi[];
    private String nomiStudenti[];
    private final String Aule[] = {"Aula 121","Aula 214","Aula 123","Aula 244","Aula 221","Aula 414","Aula 231","Aula 214","Aula 121","Aula 214","Aula 121","Aula 214"};
    private JButton cmdFatto;
    private JButton cmdIndietro;
    private ListaClassiHardwired list;
    private JPanel Classi = new JPanel();
    private JPanel Studenti = new JPanel();
    private String Classe;
    private String Studente;
    private String Aula;
    private JComboBox AulaN;
    private GregorianCalendar Data = new GregorianCalendar();
    private Date d;

    public FrameClasseStudente() {
        super("El_Merendero");

        setLayout(new BorderLayout());

        cmdFatto = new JButton("Fatto");
        cmdIndietro = new JButton("Indietro");

        list = new ListaClassiHardwired();

        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        d = new Date(Long.MIN_VALUE);
        d = cal.getTime();
        d.setHours(11);
        d.setMinutes(00);
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(d);
        JSpinner spinner = new JSpinner(model);
        spinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
               d = (Date) ((JSpinner)e.getSource()).getValue();
            }
        });
        JPanel BoxData = new JPanel();
        BoxData.setSize(15,15);
        Box vertical = Box.createVerticalBox();
        vertical.add(spinner);
        vertical.add(Box.createVerticalGlue());
        vertical.add(Box.createRigidArea(new Dimension(115, 8)));
        BoxData.add(vertical);
        add(BoxData, BorderLayout.NORTH);
        nomiClassi = list.getNomiClassi();
        nomiStudenti = list.ListaStudentiXClasseData(nomiClassi[0]);
        Classe = nomiClassi[0];
        Aula = Aule[0];
        Studente = nomiStudenti[0];
        AulaN = new JComboBox(Aule);
        boxStudenti = new JComboBox(nomiStudenti); //set up JComboBox
        boxClassi = new JComboBox(nomiClassi); // set up JComboBox
        boxClassi.setMaximumRowCount(10);
        boxStudenti.setMaximumRowCount(15);
        Classi.add(boxClassi);
        Studenti.add(boxStudenti);
        Box vertical2 = Box.createVerticalBox();
        vertical2.add(Classi);
        vertical2.add(Box.createVerticalGlue());
        vertical2.add(AulaN);
        vertical2.add(Studenti);
        add(vertical2, BorderLayout.CENTER);
        boxClassi.addItemListener(new ItemListener() {
            // handle JComboBox event
            @Override
            public void itemStateChanged(ItemEvent e) {
                // determine whether checkbox selected
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    nomiStudenti = list.ListaStudentiXClasseData(e.getItem().toString());
                    Classe = e.getItem().toString();
                    boxStudenti.removeAllItems();
                    for (int i = 0; i < nomiStudenti.length; i++) {
                        boxStudenti.addItem(nomiStudenti[i]);
                    }
                }
            } // end method itemStateChanged
        } // end anonymous inner class
                ); // end call to addItemListener
        boxStudenti.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Studente = e.getItem().toString();
                }
            }
        });
        AulaN.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Aula = e.getItem().toString();
                }
            }
        });
        Box horizontal = Box.createHorizontalBox();
        horizontal.add(cmdIndietro);
        horizontal.add(Box.createHorizontalGlue());
        horizontal.add(Box.createRigidArea(new Dimension(115, 8)));
        horizontal.add(cmdFatto);
        add(horizontal, BorderLayout.SOUTH);
        cmdIndietro.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FrameClasseStudente.this.setVisible(false);
                        Frame fb = new Frame();
                        fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        fb.setSize(800, 600); // set frame size
                        fb.setLocationRelativeTo(null); //center fr
                        fb.setVisible(true); // display frame	
                    }
                });
        cmdFatto.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Data.setTime(d);
                            FrameClasseStudente.this.setVisible(false);
                            OrdineDiClasse odc = new OrdineDiClasse(Classe);
                            odc.add(new Ordine(Studente));
                            odc.setData(Data);
                            odc.setAula(Aula.substring(5));
                            FrameOrdineSingolo fb = new FrameOrdineSingolo(odc);
                            fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            fb.setSize(800, 600); // set frame size
                            fb.setLocationRelativeTo(null); // display frame	
                            fb.setVisible(true); // display frame
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FrameClasseStudente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}
