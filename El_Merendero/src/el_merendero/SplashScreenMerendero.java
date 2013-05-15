/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package el_merendero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
    
    public SplashScreenMerendero(final Frame f){
        pg.setBorderPainted(true);
        pg.setForeground(Color.green);
        
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                times++;
                pg.setValue(times);
                if ( times* timer.getDelay() == 5000) {
                    dispose();
                    timer.stop();
                    
                    f.setVisible(true);  
                }
            }
        });
        timer.start();
        
        setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon("images/splash.jpg"); 
        
        JLabel label = new JLabel(img);
        //TODO: Setbackground
        add(label,BorderLayout.CENTER);
        add(pg,BorderLayout.SOUTH);
    }
}
