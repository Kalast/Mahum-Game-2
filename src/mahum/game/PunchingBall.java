/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.FrictionJointDef;
import org.jbox2d.dynamics.joints.MouseJointDef;
import org.jbox2d.dynamics.joints.RopeJointDef;
import org.jbox2d.dynamics.joints.WeldJointDef;
import org.jbox2d.dynamics.joints.WheelJointDef;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class PunchingBall {
    private Ball anchor;
    private Cube punching;
    private Ball punching2;
    private float x, y, radius;
    
    private ArrayList<Ball> perles;

    public PunchingBall(float x, float y, float length, float radius) {
        anchor = new Ball(5);
        punching = new Cube(radius*2, radius*2);
        punching2 = new Ball(radius);
        this.radius = radius;
        this.x = x;
        this.y = y;
        perles = new ArrayList();
    }
    
    public void create(World world){
        this.anchor.type = BodyType.STATIC;
        this.anchor.create(world, x, y);
        //this.punching.create(world, x, y+100);
        //punching2.create(world, x, y+50);
        
        Ball b = null;
        
        DistanceJointDef t = null;
        for(int i = 0; i < 30; i ++){
            perles.add(new Ball(3));
            this.perles.get(this.perles.size()-1).create(world, 0, 0);
            t = new DistanceJointDef();
            if(i == 0){
                t.bodyA = this.anchor.getBody();
            }else{
                t.bodyA = this.perles.get(i-1).getBody();
            }
            
            t.bodyB = this.perles.get(i).getBody();
            t.length = 10f / Constants.SCALE_PHYSICS;
            world.createJoint(t);
        }
        
        /*DistanceJointDef jd2 = new DistanceJointDef();
        jd2.bodyA = punching.getBody();
        jd2.bodyB = punching2.getBody();
        jd2.length = 1.5f;
        world.createJoint(jd2);*/
    }

    public Cube getPunching() {
        return punching;
    }
    
    
    
    public void render(Graphics g){
        this.anchor.render(g);
        g.setColor(Color.blue);
        //g.drawLine(this.anchor.getPositionGame().x + 5, this.anchor.getPositionGame().y + 5, this.punching.getPositionGame().x + radius, this.punching.getPositionGame().y + radius);
        g.setColor(Color.white);
        /*this.punching.render(g);
        this.punching2.render(g);*/
        
        for(Ball b : perles){
            b.render(g);
        }
        g.setColor(Color.white);
    }
}
