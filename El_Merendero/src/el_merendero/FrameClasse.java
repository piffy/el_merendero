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
public class FrameClasse extends JFrame {

    private JComboBox boxClassi;
    private String nomiClassi[];
    private final String Aule[] = {"Aula 121","Aula 214","Aula 123","Aula 244","Aula 221","Aula 414","Aula 231","Aula 214","Aula 121","Aula 214","Aula 121","Aula 214"};
    private JButton cmdFatto;
    private JButton cmdIndietro;
    private ListaClassiHardwired list;
    private JPanel Classi = new JPanel();
    private String Classe;
    private String Aula;
    private JComboBox AulaN;

    public FrameClasse() {
        super("El_Merendero");

        setLayout(new BorderLayout());

        cmdFatto = new JButton("Fatto");
        cmdIndietro = new JButton("Indietro");

        list = new ListaClassiHardwired();

        nomiClassi = list.getNomiClassi();
        Classe = nomiClassi[0];
        Aula = Aule[0];
        AulaN = new JComboBox(Aule);
        boxClassi = new JComboBox(nomiClassi); // set up JComboBox
        boxClassi.setMaximumRowCount(10);
        Classi.add(boxClassi);
        Box vertical = Box.createVerticalBox();
        vertical.add(Classi);
        vertical.add(Box.createVerticalGlue());
        vertical.add(AulaN);
        add(vertical, BorderLayout.NORTH);
        boxClassi.addItemListener(new ItemListener() {
            // handle JComboBox event
            @Override
            public void itemStateChanged(ItemEvent e) {
                // determine whether checkbox selected
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Classe = e.getItem().toString(); 
                }
            } // end method itemStateChanged
        } // end anonymous inner class
                ); // end call to addItemListener
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
                        FrameClasse.this.setVisible(false);
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
                            FrameClasse.this.setVisible(false);
                            OrdineDiClasse odc = new OrdineDiClasse(Classe);
                            odc.setAula(Aula.substring(5));
                            FrameStudente fb = new FrameStudente(odc);
                            fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            fb.setSize( 275, 110 ); // set frame size
                            fb.setLocationRelativeTo(null); // display frame	
                            fb.setVisible(true); // display frame
                    }
                });
    }
}
