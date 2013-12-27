/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public class Dialog extends Container{
    private String text;

    public Dialog(GUIContext container) {
        super(container, new Rectangle(0,0,0,0));
    }

    @Override
    protected void gainFocus() {
    
    }

    @Override
    protected void lostFocus() {
    
    }

    @Override
    protected void init() {
    
    }
    
    
}
