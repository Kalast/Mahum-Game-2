/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import mahum.gui.ConstraintAxisMovement;
import mahum.gui.ConstraintMovement;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author Kalast
 */
public class SimpleButton extends MouseOverArea{
    ConstraintAxisMovement constraint;

    public SimpleButton(GUIContext container, Image image, int x, int y) {
        super(container, image, x, y);
        
        constraint = new ConstraintAxisMovement(300,800,ConstraintMovement.AXIS_Y);
        constraint.attachTo(this);

        try {
            this.setMouseOverImage(new Image("images/scroll_h.png"));
            this.setMouseDownImage(new Image("images/scroll_d.png"));
        } catch (SlickException ex) {
            Logger.getLogger(SimpleButton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        if(this.isMouseOver()){
            if(!constraint.isConstraint()){
                this.setLocation(this.getX(), newy);
            } else {
                this.setLocation(this.getX(), oldy);
            }            
        }
    }
    
}
