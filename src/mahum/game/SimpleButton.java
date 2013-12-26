/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author Kalast
 */
public class SimpleButton extends MouseOverArea{

    public SimpleButton(GUIContext container, Image image, int x, int y) {
        super(container, image, x, y);
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if(mouseOver())
    }
    
    
}
