/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;

/**
 *
 * @author Kalast
 */
public abstract class ConstraintMovement {
    public static final int AXIS_X = 1;
    public static final int AXIS_Y = 2;
    
    protected AbstractComponent component;
    
    public void attachTo(AbstractComponent component){
        this.component = component;
    }

    public abstract boolean isConstraint();
}
