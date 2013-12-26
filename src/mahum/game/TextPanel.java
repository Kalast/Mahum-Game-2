/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Kalast
 */
public class TextPanel {
    private Rectangle zone;
    private ArrayList<String> lines;
    private int padding;
    private int lineHeight;
    private Scroller scroller;

    public TextPanel(Rectangle zone) {
        this.zone = zone;
        this.lines = new ArrayList<>();
        this.padding = 5;
        this.lineHeight = 20;
        this.scroller = new Scroller(new Rectangle(
                zone.getX() + zone.getWidth() - 16,
                zone.getY(),
                16,
                this.zone.getHeight()
        ));
    }
    
    public void addText(String line){
        this.lines.add(line);
    }
    
    public void setLocation(int x, int y){
        this.zone.setLocation(x, y);
    }
    
    public void setSize(int width, int height){
        this.zone.setSize(width, height);
    }
    
    public void setPadding(int padding){
        this.padding = padding;
    }
    
    public void render(Graphics g){
        Color c = g.getColor();
        g.setColor(new Color(255,255,255));
        
        g.drawRect(this.zone.getX(),this.zone.getY(),this.zone.getWidth(),this.zone.getHeight());
        g.setClip(
                (int)(zone.getX() + padding),
                (int)(zone.getY() + padding), 
                (int)(zone.getWidth() - padding * 2),
                (int)(zone.getHeight() - padding * 2)
        );
        
        System.out.println(g.getClip());
        int i = 0;
        for(String line : lines){
            g.drawString(line, this.zone.getX() + this.padding, this.zone.getY() + this.padding + this.lineHeight * i);
            i ++;
        }
        
        g.setClip(0, 0, Variables.WIDTH_SCREEN, Variables.HEIGHT_SCREEN);
        try {
            this.scroller.render(GameState.container, g);
        } catch (SlickException ex) {
            Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setColor(c);
        
    }
}
