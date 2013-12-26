/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kalast
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.opengl.ImageData;
/**
 *
 * @author Kalast
 */
public class Scroller extends org.newdawn.slick.gui.AbstractComponent{
    private Rectangle zone;
    private MouseOverArea cursor;
    private Color cursorColor = Color.gray;

    public Scroller(Rectangle rect) {
        super(GameState.container);
        this.zone = rect;
        try {
            cursor = new MouseOverArea(this.container, new Image("images/scroll_uh.png"), 0, 0);
            this.cursor.setLocation(this.zone.getX(), this.zone.getY());
        } catch (SlickException ex) {
            Logger.getLogger(Scroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private boolean isHover() {
        return !(Mouse.getX() < this.cursor.getX() || Mouse.getX() >= this.cursor.getX() + this.cursor.getWidth()
                || Mouse.getY() < this.cursor.getY() || Mouse.getY() >= this.cursor.getY() + this.cursor.getHeight());
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        Color c = g.getColor();
        g.setColor(new Color(255,255,255, 0.5f));
        g.fillRect(this.zone.getX(),this.zone.getY(),this.zone.getWidth(),this.zone.getHeight());
        g.setColor(cursorColor);
        this.cursor.render(container, g);
        g.setColor(c);
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
        return (int) this.zone.getY();
    }

    @Override
    public int getWidth() {
        return (int) this.zone.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) this.zone.getHeight();
    }
}
