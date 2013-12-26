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
public class Car extends Cube{
    
    private Wheel w1;
    private Wheel w2;

    public Car(float width, float height) {
        super(width, height);
        w1 = new Wheel("images/wheel.png");
        w2 = new Wheel("images/wheel.png");
    }

    @Override
    public void render(Graphics g) {
        super.render(g); //To change body of generated methods, choose Tools | Templates.
        /*w1.render(g);
        w2.render(g);*/
    }

    @Override
    public void create(World world, float x, float y) {
         //To change body of generated methods, choose Tools | Templates.
       // this.type = BodyType.STATIC;
       /* w1.create(world, 400, 400);
        w2.create(world, 600, 650);*/
        
        super.create(world, x, y);
        this.body.setGravityScale(0f);
       /* WheelJointDef wjd1 = new WheelJointDef();

        this.body.getFixtureList().setDensity(1);
        wjd1.initialize(this.w1.getBody(),this.getBody(), new Vec2(0,1), new Vec2(0, 1f));
        wjd1.enableMotor = true;
        wjd1.frequencyHz =1;
        wjd1.maxMotorTorque = 4;
        wjd1.motorSpeed = -5;
        wjd1.dampingRatio = 0.8f;
        
        this.w1.getBody().getFixtureList().setDensity(0.1f);
        
        WheelJointDef wjd2 = new WheelJointDef();
        wjd2.initialize(this.getBody(),this.w2.getBody(), new Vec2(0, 1), new Vec2(0, 1));
        wjd2.frequencyHz =1;
        wjd2.maxMotorTorque = 4;
        wjd2.motorSpeed = -5;
        wjd2.dampingRatio = 0.8f;
        world.createJoint(wjd1);
        this.w2.getBody().getFixtureList().setDensity(0.1f);

        world.createJoint(wjd2);*/
        
        
    }
}
