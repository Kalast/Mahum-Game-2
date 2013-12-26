/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class JaugePuissance {
    
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private float radius;
    private boolean active;

    public JaugePuissance(float radius) {
        this.radius = radius;
    }
    
    public void render(Graphics g){
        if(this.active){
            g.setColor(Color.blue);
            g.drawOval(startX - radius, startY - radius, radius*2, radius*2);
            g.setColor(Color.white);
            g.drawLine(startX, startY, endX, endY);
            g.setColor(Color.blue);
            g.drawOval(endX - radius, endY - radius, radius*2, radius*2);
            g.setColor(Color.white);
        }
    }
    
    public void setStart(float x, float y){
        this.startX = x;
        this.startY = y;
    }
    
    public void setEnd(float x, float y){
        this.endX = x;
        this.endY = y;
    }

    public boolean isActive() {
        return active;
    }
    
    public Vec2 getVector(){
        return new Vec2(this.startX - this.endX, this.startY - this.endY);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
