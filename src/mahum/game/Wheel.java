/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kalast
 */
public class Wheel {
    private Image wheelImg;
    private Ball wheelSkeleton;

    public Wheel(String imgPath) {
        try {
            wheelImg = new Image(imgPath);
        } catch (SlickException ex) {
            Logger.getLogger(Wheel.class.getName()).log(Level.SEVERE, null, ex);
        }
        wheelSkeleton = new Ball(wheelImg.getWidth() / 2);
    }
    
    public void create(World world, float x, float y){
        this.wheelSkeleton.create(world, x, y);
    }

    public void render(Graphics g) {
        g.rotate(wheelSkeleton.getPositionGame().x + wheelImg.getWidth() / 2, wheelSkeleton.getPositionGame().y + wheelImg.getHeight() / 2, (float) Math.toDegrees(wheelSkeleton.getBody().getAngle()));
        g.drawImage(wheelImg, wheelSkeleton.getPositionGame().x, wheelSkeleton.getPositionGame().y);
        g.rotate(wheelSkeleton.getPositionGame().x + wheelImg.getWidth() / 2, wheelSkeleton.getPositionGame().y + wheelImg.getHeight() / 2, -(float) Math.toDegrees(wheelSkeleton.getBody().getAngle()));
        this.wheelSkeleton.render(g);
    }
    
    public Body getBody(){
        return this.wheelSkeleton.getBody();
    }
}
