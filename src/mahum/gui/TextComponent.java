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
public abstract class TextComponent extends Container{
    protected TextCursor cursor;
    protected StringBuffer text;
    protected int XPosText;
    protected int YPosText;

    public TextComponent(GUIContext container, Rectangle zone) {
        super(container, zone);
    }

    @Override
    protected void init() {
        XPosText = 0;
        YPosText = 0;
        cursor = new TextCursor();
        text = new StringBuffer();
        cursor.attachTo(this);
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        super.render(container, g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.white);
        g.setClip(new Rectangle(this.zone.getX()+this.padding, this.zone.getY()+this.padding, this.zone.getWidth()-this.padding*2+1, this.zone.getHeight()-this.padding*2+1));
        g.drawString(this.text.toString(), this.zone.getX() + this.getPadding() + this.XPosText, this.zone.getY() + this.getPadding() + this.YPosText);
        this.cursor.render(g);
        g.setClip(0,0,Variables.WIDTH_SCREEN, Variables.HEIGHT_SCREEN);
    }

    public StringBuffer getText() {
        return text;
    }
    
    public void clearText(){
        text.setLength(0);
        this.cursor.reset();
    }
    
    public void setTextLocation(int x, int y){
        this.XPosText = x;
        this.YPosText = y;
    }

    public int getXPosText() {
        return XPosText;
    }

    public void setXPosText(int XPosText) {
        this.XPosText = XPosText;
    }

    public int getYPosText() {
        return YPosText;
    }

    public void setYPosText(int YPosText) {
        this.YPosText = YPosText;
    }
    
    public Rectangle getZoneText(){
        return new Rectangle(this.getX() + padding, this.getY() + padding, this.getWidth() - padding*2, this.getHeight() - padding*2);
    }
}
