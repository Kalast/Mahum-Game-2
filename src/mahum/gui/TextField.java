/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;


import java.awt.FontMetrics;
import java.util.logging.Level;
import java.util.logging.Logger;
import mahum.game.Variables;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public class TextField extends TextComponent{
    
    public TextField(GUIContext container) {
        super(container, new Rectangle(0,0,200,29));
    }

    @Override
    protected void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.setPadding(5);
    }

    @Override
    public void keyPressed(int key, char c) {
        if(this.hasFocus()){
            if(key == Input.KEY_BACK){
                this.cursor.backChar();
            } else if(key == Input.KEY_DELETE){
                this.cursor.deleteChar();
            } else if(key == Input.KEY_RIGHT){
                this.cursor.moveToRight();
            } else if(key == Input.KEY_LEFT){
                this.cursor.moveToLeft();
            } else if(key == Input.KEY_ENTER){
                this.action.perform();
            } else {
                this.cursor.write(c);
            }
        }
    }

    @Override
    protected void mouseLeave() {
        this.container.setDefaultMouseCursor();
        if(!this.hasFocus()){
            this.background = Color.lightGray;
        }
    }

    @Override
    protected void mouseEnter() {
        try {
            this.container.setMouseCursor("images/cursors/text.png", 7, 12);
            if(!this.hasFocus()){
                this.background = new Color(255,255,255,0.4f);
            }
        } catch (SlickException ex) {
            Logger.getLogger(TextField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocation(int x, int y) {
        if(this.zone != null){
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

    @Override
    protected void gainFocus() {
        this.cursor.setActive(true);
        this.background = new Color(0,255,0,0.4f);
    }

    @Override
    protected void lostFocus() {
        this.cursor.setActive(false);
        this.background = Color.lightGray;
    }

}
