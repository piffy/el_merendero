/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author b11g7
 */
public class SplashScreenMerendero extends JWindow {

    JProgressBar pg = new JProgressBar();
    Timer timer;
    int times = 0;
    public SplashScreenMerendero(){
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times++;
                pg.setValue(times);
                if ( times* timer.getDelay() == 5000) {
                    dispose();
                    timer.stop();
                    
                    Frame fr=new Frame();
                    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fr.setSize(800, 600);
                    fr.setVisible(true);
                    fr.setLocationRelativeTo(null);
                    
                }
            }
        });
        timer.start();
        
        setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon(getClass().getResource("splash.jpg")); // BUG NULL POINTER EXCEPTION
        if (img == null) {
            System.out.print("Errore");
            System.exit(-1);
        }
        JLabel label = new JLabel(img);
        //TODO: Setbackground
        add(label,BorderLayout.CENTER);
        add(pg,BorderLayout.SOUTH);
    }

    public static void main(String args[]) {
        final SplashScreenMerendero window = new SplashScreenMerendero();
        JPanel content = new JPanel();
        content.setOpaque(false);

        window.setSize(450, 350);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
       
        ImageIcon image = new ImageIcon("splash.jpg");
        //Graphics2D graphics = new Graphics2D();
        //graphics.drawString("Ciao mondo", 10, 10);

        
        
    }
}
