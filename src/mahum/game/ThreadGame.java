/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kalast
 */

public class ThreadGame extends Thread{
    
    public static class GameFrame extends JFrame{
        GameContainer container;
        
        public GameFrame(final GameContainer container) {
            container.setVSync(true);
            container.setAlwaysRender(true);
            container.setUpdateOnlyWhenVisible(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.container = container;
            final JFrame frame = this;
            this.addWindowListener(new WindowListener() {

                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }
            });
        }
    }
    
    public static void main(String[] args) {
        try {
            CanvasGameContainer cgc = new CanvasGameContainer(new SimpleSlickGame());
            cgc.setSize(800, 600);
            cgc.dispose();

            GameFrame frame = new GameFrame(cgc.getContainer());
            frame.add(cgc);
            frame.pack();
            frame.setVisible(true);
            cgc.start(); //on démarre le container  
        } catch (SlickException ex) {
            Logger.getLogger(ThreadGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
