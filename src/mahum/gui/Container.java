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
    private boolean hovered;
    protected int borderRadius;
    
    protected boolean opaque;
    protected Color background;
    protected Color foreground;
    
    public Container(GUIContext container, Rectangle zone) {
        super(container);
        this.zone = zone;
        this.padding = 0;
        this.visible = true;
        this.hovered = false;
        this.opaque = true;
        this.borderRadius = 0;
        this.background = Color.lightGray;
        this.foreground = Color.black;
        action = new ActionPerform() {
            @Override
            public void perform() {}
        };
        init();
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    
    public void setAction(ActionPerform action) {
        this.action = action;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if(this.zone.contains(x, y)){
            if(!this.hasFocus()){
                this.setFocus(true);
                this.gainFocus();
            }
        } else {
            if(this.hasFocus()){
                System.out.println(zone);
                this.setFocus(false);
                this.lostFocus();
            }
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        super.mouseMoved(oldx, oldy, newx, newy); //To change body of generated methods, choose Tools | Templates.
        if(this.zone.contains(newx, newy)){
            if(!this.hovered){
                this.mouseEnter();
                this.hovered = true;
            }
        } else {
            if(this.hovered){
                this.mouseLeave();
                this.hovered = false;
            }
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        if(this.visible){
            g.setClip(new Rectangle(this.zone.getX(), this.zone.getY(), this.zone.getWidth()+1, this.zone.getHeight()+1));
            if(this.opaque){
            g.setColor(this.background);
            g.fillRoundRect(this.zone.getX(), this.zone.getY(), this.zone.getWidth(), this.zone.getHeight(), borderRadius);
            }
            g.setColor(Color.white);
            g.drawRoundRect(this.zone.getX(), this.zone.getY(), this.zone.getWidth(), this.zone.getHeight(), borderRadius);
            g.clearClip();
        }
    }
    
    protected void mouseEnter(){
        
    }
    
    protected void mouseLeave(){
        
    }

    @Override
    public void setLocation(int x, int y) {
        if(zone != null){
            this.zone.setLocation(x, y);
        }
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
