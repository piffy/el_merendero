/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package el_merendero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
*
* @author b11g8
*/
public class FrameOrdine extends JFrame{
    
    JRadioButton rdbPanini[] = new JRadioButton[3];
    JTextField txtPanini[]=new JTextField[3];
    JButton cmdPaniniDone[]=new JButton[3];
    JButton cmdPaniniPiu[]=new JButton[3];
    JButton cmdPaniniMeno[]=new JButton[3];
    JCheckBox ckbPaniniMaio[]=new JCheckBox[3];
    JCheckBox ckbPaniniKetchup[]=new JCheckBox[3];
    String Classe;
    String Studente;
    String names[]={"Forno","Cotoletta","Panino"};
    ButtonGroup group = new ButtonGroup();
    int x=20,y=0;
    
    public FrameOrdine(String Classe,String Studente){
        
        super("Ordina la tua merenda");
        setLayout(null);
        
        this.Classe=Classe;
        this.Studente=Studente;
        
        //Inizializzazione varie proprietà grafiche
        
        this.setFont(new Font("Segoe UI",Font.PLAIN,20));
        Gestore al=new Gestore();
        CambiaQuantita aq=new CambiaQuantita();
        OperazioneEseguita oe=new OperazioneEseguita();
        
        //Inserimento dei componenti per la gestione dell'ordine
        
        for(int i=0;i<rdbPanini.length;i++){
            rdbPanini[i]=new JRadioButton(names[i]);
            cmdPaniniPiu[i]=new JButton("+");
            cmdPaniniMeno[i]=new JButton("-");
            txtPanini[i]=new JTextField("0");
            cmdPaniniDone[i]=new JButton("Fatto");
            ckbPaniniMaio[i]=new JCheckBox("Maio");
            ckbPaniniKetchup[i]=new JCheckBox("Ketchup");
            
            rdbPanini[i].setFont(new Font("Segoe UI",Font.PLAIN,15));
            txtPanini[i].setFont(new Font("Segoe UI",Font.PLAIN,15));
            cmdPaniniDone[i].setFont(new Font("Segoe UI",Font.PLAIN,15));
            ckbPaniniMaio[i].setFont(new Font("Segoe UI",Font.PLAIN,15));
            ckbPaniniKetchup[i].setFont(new Font("Segoe UI",Font.PLAIN,15));
            
            group.add(rdbPanini[i]);
            rdbPanini[i].addActionListener(al);
            cmdPaniniPiu[i].addActionListener(aq);
            cmdPaniniMeno[i].addActionListener(aq);
            cmdPaniniDone[i].addActionListener(oe);
            
            rdbPanini[i].setBounds(new Rectangle(new Point(1, 1), rdbPanini[i].getPreferredSize()));
            cmdPaniniPiu[i].setBounds(new Rectangle(new Point(1, 1), cmdPaniniPiu[i].getPreferredSize()));
            cmdPaniniMeno[i].setBounds(new Rectangle(new Point(1, 1), cmdPaniniMeno[i].getPreferredSize()));
            txtPanini[i].setBounds(new Rectangle(new Point(1, 1), txtPanini[i].getPreferredSize()));
            cmdPaniniDone[i].setBounds(new Rectangle(new Point(1, 1), cmdPaniniDone[i].getPreferredSize()));
            ckbPaniniMaio[i].setBounds(new Rectangle(new Point(1, 1), ckbPaniniMaio[i].getPreferredSize()));
            ckbPaniniKetchup[i].setBounds(new Rectangle(new Point(1, 1), ckbPaniniKetchup[i].getPreferredSize()));
            
            rdbPanini[i].setLocation(x,y+=60);
            cmdPaniniPiu[i].setLocation(rdbPanini[i].getX()+170, rdbPanini[i].getY()+3);
            cmdPaniniMeno[i].setLocation(cmdPaniniPiu[i].getX()+45, rdbPanini[i].getY()+3);
            txtPanini[i].setLocation(cmdPaniniMeno[i].getX()+100,rdbPanini[i].getY()+3);
            cmdPaniniDone[i].setLocation(txtPanini[i].getX()+50, rdbPanini[i].getY());
            ckbPaniniMaio[i].setLocation(rdbPanini[i].getX()+20,rdbPanini[i].getY()+30);
            ckbPaniniKetchup[i].setLocation(ckbPaniniMaio[i].getX()+100,ckbPaniniMaio[i].getY());
            
            cmdPaniniPiu[i].setSize(45, 25);
            cmdPaniniMeno[i].setSize(45, 25);
            txtPanini[i].setSize(30, 25);
            
            cmdPaniniPiu[i].setVisible(false);
            cmdPaniniMeno[i].setVisible(false);
            txtPanini[i].setVisible(false);
            cmdPaniniDone[i].setVisible(false);
            ckbPaniniMaio[i].setVisible(false);
            ckbPaniniKetchup[i].setVisible(false);
            
            add(rdbPanini[i]);
            add(cmdPaniniPiu[i]);
            add(cmdPaniniMeno[i]);
            add(txtPanini[i]);
            add(cmdPaniniDone[i]);
            add(ckbPaniniMaio[i]);
            add(ckbPaniniKetchup[i]);
        }
        
    }
    
    private class Gestore implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int k=0;k<rdbPanini.length;k++){
                if(rdbPanini[k].isSelected()){
                    cmdPaniniPiu[k].setVisible(true);
                    cmdPaniniMeno[k].setVisible(true);
                    txtPanini[k].setVisible(true);
                    cmdPaniniDone[k].setVisible(true);
                    ckbPaniniMaio[k].setVisible(true);
                    ckbPaniniKetchup[k].setVisible(true);
                }
                else{
                    cmdPaniniPiu[k].setVisible(false);
                    cmdPaniniMeno[k].setVisible(false);
                    txtPanini[k].setVisible(false);
                    cmdPaniniDone[k].setVisible(false);
                    ckbPaniniMaio[k].setVisible(false);
                    ckbPaniniKetchup[k].setVisible(false);
                    ckbPaniniMaio[k].setSelected(false);
                    ckbPaniniKetchup[k].setSelected(false);
                    txtPanini[k].setText("0");
                }
            }
        }
        
    }
    
    private class CambiaQuantita implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int i=0;
            for(int k=0;k<rdbPanini.length;k++){
                if(rdbPanini[k].isSelected())
                    i=k;
            }
            if(e.getActionCommand()=="+"){
                int val=Integer.parseInt(txtPanini[i].getText());
                val++;
                txtPanini[i].setText(""+val);
            }
            else{
                if(Integer.parseInt(txtPanini[i].getText())>0){
                    int val=Integer.parseInt(txtPanini[i].getText());
                    val--;
                    txtPanini[i].setText(""+val);
                }
            }
        }
        
    }
    
    private class OperazioneEseguita implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<rdbPanini.length;i++){
                rdbPanini[i].setSelected(false);
                cmdPaniniPiu[i].setVisible(false);
                cmdPaniniMeno[i].setVisible(false);
                txtPanini[i].setVisible(false);
                cmdPaniniDone[i].setVisible(false);
                ckbPaniniMaio[i].setVisible(false);
                ckbPaniniKetchup[i].setVisible(false);
            }
        }
    }
}
        
