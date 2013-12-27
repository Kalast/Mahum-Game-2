/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import mahum.game.Variables;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public abstract class Container extends AbstractComponent{
    protected Rectangle zone;
    protected int padding;
    protected boolean visible;
    protected ActionPerform action;
    
    public Container(GUIContext container, Rectangle zone) {
        super(container);
        this.zone = zone;
        this.padding = 0;
        this.visible = true;
        action = new ActionPerform() {
            @Override
            public void perform() {}
        };
        init();
    }
    
    public void setAction(ActionPerform action) {
        this.action = action;
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if(this.zone.contains(x, y)){
            this.setFocus(true);
            this.gainFocus();
        } else {
            this.setFocus(false);
            this.lostFocus();
        }
    }
    
    protected abstract void gainFocus();
    protected abstract void lostFocus();
    protected abstract void init();

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        if(this.visible){
            g.setClip(new Rectangle(this.zone.getX(), this.zone.getY(), this.zone.getWidth()+1, this.zone.getHeight()+1));
            g.setColor(new Color(0,0,255, 0.4f));
            g.fillRoundRect(this.zone.getX(), this.zone.getY(), this.zone.getWidth(), this.zone.getHeight(), 0);
            g.setColor(Color.white);
            g.drawRoundRect(this.zone.getX(), this.zone.getY(), this.zone.getWidth(), this.zone.getHeight(), 0);
            g.setClip(0,0,Variables.WIDTH_SCREEN, Variables.HEIGHT_SCREEN);
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.zone.setLocation(x, y);
    }

    @Override
    public int getX() {
        return (int) this.zone.getX();
    }

    @Override
    public int getY() {
        return (int)this.zone.getY();
    }

    @Override
    public int getWidth() {
        return (int)this.zone.getWidth();
    }

    @Override
    public int getHeight() {
        return (int)this.zone.getHeight();
    }
    
    public GUIContext getGUI(){
        return this.container;
    }
}
