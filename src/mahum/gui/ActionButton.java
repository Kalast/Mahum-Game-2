/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author Kalast
 */
public class ActionButton extends MouseOverArea{
    private ActionPerform action;

    public ActionButton(GUIContext container, String name, int x, int y) throws SlickException {
        super(container, new Image("images/" + name + ".png"), x, y);
        this.setMouseOverImage(new Image("images/" + name + "_h.png"));
        this.setMouseDownImage(new Image("images/" + name + "_d.png"));
    }
    
    public void setActionPerform(ActionPerform action){
        this.action = action;
    }
 
    @Override
    public void mouseReleased(int button, int mx, int my) {
        if(action != null){
            if(this.isMouseOver()){
                action.perform();
            }
        }
    }
    
        
}
