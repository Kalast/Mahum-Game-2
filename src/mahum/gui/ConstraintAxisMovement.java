/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import org.newdawn.slick.gui.AbstractComponent;

/**
 *
 * @author Kalast
 */
public class ConstraintAxisMovement extends ConstraintMovement{

    private int minValue;
    private int maxValue;
    private int axis;

    public ConstraintAxisMovement(int minValue, int maxValue, int axis) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.axis = axis;
    }

    @Override
    public boolean isConstraint() {
        switch(this.axis){
            case ConstraintMovement.AXIS_X:
            return this.component.getX() < this.minValue || this.component.getX() + this.component.getWidth() > this.maxValue;

                
            case ConstraintMovement.AXIS_Y:
            return this.component.getY() < this.minValue || this.component.getY() + this.component.getHeight() > this.maxValue;
        }
        
        return false;
    }
    
}
