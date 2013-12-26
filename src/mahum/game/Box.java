/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class Box {
    private Floor top;
    private Floor bottom;
    private Wall left;
    private Wall right;
    
    private float x,y,width,height;

    public Box(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.bottom = new Floor(x, y + height, width);
        this.top = new Floor(x, y, width);
        this.left = new Wall(x, y+1, height-2);
        this.right = new Wall(x + width-1, y+1, height-2);
    }
    
    public void create(World world){
        this.top.create(world);
        this.bottom.create(world);
        this.right.create(world);
        this.left.create(world);
    }
    
    public void render(Graphics g){
        this.bottom.render(g);
        this.top.render(g);
        this.right.render(g);
        this.left.render(g);
    }
    
}
