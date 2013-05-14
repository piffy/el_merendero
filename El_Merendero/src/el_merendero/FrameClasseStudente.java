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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author charlie
 */
public class FrameClasseStudente extends JFrame {
    
    private JComboBox boxClassi;
    private JComboBox boxStudenti;
    private String nomiClassi[];
    private String nomiStudenti[];
    private JButton cmdFatto;
    private JButton cmdAnnulla;
    private ListaClassiHardwired list;
    private JPanel Classi = new JPanel();
    private JPanel Studenti = new JPanel();
    
    public FrameClasseStudente(){
        super("El_Merendero");
        
        setLayout(new BorderLayout());
        
        cmdFatto=new JButton("Fatto");
        cmdAnnulla=new JButton("Indietro");
        
        list = new ListaClassiHardwired();
        
        nomiClassi=list.getNomiClassi();
        
        nomiStudenti = list.ListaStudentiXClasseData(nomiClassi[0]);
        boxStudenti = new JComboBox(nomiStudenti); //set up JComboBox
        boxClassi = new JComboBox( nomiClassi ); // set up JComboBox
        boxClassi.setMaximumRowCount( 10 );
        boxStudenti.setMaximumRowCount( 15 );
        Classi.add(boxClassi);
        add(Classi,BorderLayout.NORTH);
        Studenti.add(boxStudenti);
        add(Studenti,BorderLayout.CENTER);
        boxClassi.addItemListener(new ItemListener() {
            // handle JComboBox event
            @Override
            public void itemStateChanged( ItemEvent e )
            {
               // determine whether checkbox selected
               if ( e.getStateChange() == ItemEvent.SELECTED ){
                   nomiStudenti = list.ListaStudentiXClasseData(e.getItem().toString());
                   boxStudenti.removeAllItems();
                   for(int i=0;i<nomiStudenti.length;i++) {
                       boxStudenti.addItem(nomiStudenti[i]);
                   }
                   Studenti.add(boxStudenti);
                   add(Studenti,BorderLayout.CENTER);
               }
            } // end method itemStateChanged
         } // end anonymous inner class
      ); // end call to addItemListener
        Box horizontal = Box.createHorizontalBox();
        horizontal.add(cmdAnnulla);
        horizontal.add(Box.createHorizontalGlue());
        horizontal.add(Box.createRigidArea(new Dimension(115, 8)));
        horizontal.add(cmdFatto);
        add(horizontal,BorderLayout.SOUTH);
        cmdAnnulla.addActionListener(
                new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    FrameClasseStudente.this.setVisible(false);
                    Frame fb = new Frame();
                    fb.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                    fb.setSize( 275, 180 ); // set frame size
                    fb.setLocationRelativeTo(null); //center fr
                    fb.setVisible( true ); // display frame	
                }
            });
    }
}
