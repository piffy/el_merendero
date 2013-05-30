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
public class FrameClasse extends JFrame {

    private JComboBox boxClassi;
    private String nomiClassi[];
    private JButton cmdFatto;
    private JButton cmdIndietro;
    private String Aule[] = {"Aula 131", "Aula 123", "Aula 313", "Aula 404", "Aula 112"};
    private ListaClassiHardwired list;
    private JComboBox boxAula;
    private JPanel Classi = new JPanel();
    private JPanel AuleN = new JPanel();
    private String Classe;
    private String Aula;
    private GregorianCalendar Data = new GregorianCalendar();
    private final SpinnerDateModel model;
    private final JSpinner spinner;
    private Date d = new Date(Long.MIN_VALUE);

    public FrameClasse() {
        super("El_Merendero");

        setLayout(new BorderLayout());


        cmdFatto = new JButton("Fatto");
        cmdIndietro = new JButton("Indietro");

        list = new ListaClassiHardwired();
        Calendar cal = Calendar.getInstance();
        d = cal.getTime();
        d.setHours(11);
        d.setMinutes(00);
        model = new SpinnerDateModel();
        model.setValue(d);
        spinner = new JSpinner(model);
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                d = (Date) ((JSpinner) e.getSource()).getValue();
            }
        });
        JPanel BoxData = new JPanel();
        BoxData.setSize(15, 15);
        Box vertical = Box.createVerticalBox();
        vertical.add(spinner);
        vertical.add(Box.createVerticalGlue());
        vertical.add(Box.createRigidArea(new Dimension(115, 8)));
        BoxData.add(vertical);
        add(BoxData, BorderLayout.NORTH);

        nomiClassi = list.getNomiClassi();

        Classe = nomiClassi[0];
        Aula = Aule[0];

        boxAula = new JComboBox(Aule);
        boxClassi = new JComboBox(nomiClassi); // set up JComboBox
        boxClassi.setMaximumRowCount(10);
        Classi.add(boxClassi);
        AuleN.add(boxAula);
        Box vertical2 = Box.createVerticalBox();
        vertical2.add(Classi);
        vertical2.add(Box.createRigidArea(new Dimension(20, 8)));
        vertical2.add(AuleN);
        vertical2.add(Box.createRigidArea(new Dimension(20, 8)));
        add(vertical2, BorderLayout.CENTER);

        boxAula.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // determine whether checkbox selected
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Aula = e.getItem().toString();
                }
            }
        });

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
                        Data.setGregorianChange(d);
                        Data.setTime(d);
                        OrdineDiClasse odc = new OrdineDiClasse(Classe);
                        odc.setAula(Aula.substring(5));
                        odc.setData(Data);
                        FrameStudente fb = new FrameStudente(odc);
                        fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        fb.setSize(275, 110); // set frame size
                        fb.setLocationRelativeTo(null); // display frame	
                        fb.setVisible(true); // display frame
                        FrameClasse.this.dispose();
                    }
                });
    }
}
