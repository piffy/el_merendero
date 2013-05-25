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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author charlie
 */
public class FrameStudente extends JFrame {


    private JComboBox boxStudenti;
    private String nomiStudenti[];
    private JButton cmdFatto;
    private JButton cmdIndietro;
    private ListaClassiHardwired list;
    private JPanel Studenti = new JPanel();
    private String Studente;

    public FrameStudente(OrdineDiClasse ordine) {
        super("El_Merendero");

        setLayout(new BorderLayout());
        final OrdineDiClasse odc = ordine;

        cmdFatto = new JButton("Fatto");
        cmdIndietro = new JButton("Indietro");

        list = new ListaClassiHardwired();
        nomiStudenti = list.ListaStudentiXClasseData(ordine.getClasse());

        Studente = nomiStudenti[0];
        boxStudenti = new JComboBox(nomiStudenti); //set up JComboBox
        boxStudenti.setMaximumRowCount(15);
        Box vertical = Box.createVerticalBox();
        vertical.add(Box.createVerticalGlue());
        add(vertical, BorderLayout.NORTH);
        Studenti.add(boxStudenti);
        add(Studenti, BorderLayout.CENTER);
        boxStudenti.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Studente = e.getItem().toString();
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
                        FrameStudente.this.setVisible(false);
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
                            FrameStudente.this.setVisible(false);
                            odc.add(new Ordine(Studente));
                            FrameOrdineClasse fb = new FrameOrdineClasse(odc);
                            fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            fb.setSize(800, 600); // set frame size
                            fb.setLocationRelativeTo(null); // display frame	
                            fb.setVisible(true); // display frame
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FrameStudente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}
