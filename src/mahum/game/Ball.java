/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class Ball {
    private Body body;
    private float radius;
    private Color color;
    public BodyType type = BodyType.DYNAMIC;

    public Ball(float radius) {
        this.radius = radius;
        color = Color.red;
    }
    
    public void render(Graphics g){
        g.setColor(Color.white);
        //g.drawString(this.getPositionGame()+"", body.getPosition().x * Constants.SCALE_PHYSICS+radius+3, body.getPosition().y * Constants.SCALE_PHYSICS);
        g.setColor(Color.lightGray);
        //g.drawString(this.getPositionPhysics()+"", body.getPosition().x * Constants.SCALE_PHYSICS+radius+3, body.getPosition().y * Constants.SCALE_PHYSICS+15);
        g.setColor(Color.yellow);
        g.drawOval(body.getPosition().x * Constants.SCALE_PHYSICS + body.getLocalCenter().x * Constants.SCALE_PHYSICS, body.getPosition().y * Constants.SCALE_PHYSICS + body.getLocalCenter().y * Constants.SCALE_PHYSICS, 1, 1);
        g.setColor(color);
        g.drawOval(body.getPosition().x * Constants.SCALE_PHYSICS - this.radius, body.getPosition().y * Constants.SCALE_PHYSICS - this.radius, radius*2, radius*2);
        g.setColor(Color.white);
    }
    
    public void setColor(Color c){
        color = c;
    }
    
    public Vec2 getPositionPhysics(){
        return this.body.getPosition();
    }
    
    public Vec2 getPositionGame(){
        return new Vec2((int)(this.body.getPosition().x * Constants.SCALE_PHYSICS - this.radius), (int)(this.body.getPosition().y* Constants.SCALE_PHYSICS - this.radius));
    }
    
    public float getAngle(){
        return this.body.getAngle();
    }
    
    public void create(World world, float x, float y){
        x = x / Constants.SCALE_PHYSICS;
        y = y / Constants.SCALE_PHYSICS;
        
        BodyDef def = new BodyDef();
        def.position = new Vec2(x, y);
        def.type = type;
        while(world.isLocked());
        body = world.createBody(def);
        CircleShape cs = new CircleShape();
        cs.m_radius = this.radius / Constants.SCALE_PHYSICS;
        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 1;
        fd.friction = 0;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        //this.body.setGravityScale(0);
        body.setUserData(this);
    }
    
    public void setLocation(float x, float y){
        this.body.setTransform(new Vec2(x / Constants.SCALE_PHYSICS, y / Constants.SCALE_PHYSICS), 0);
    }
    
    public void resetMovement(){
        this.body.setAngularVelocity(0);
        this.body.setLinearVelocity(new Vec2(0,0));
        this.body.setActive(true);
    }
    
    public void stopMovement(){
        this.body.setActive(false);
    }

    public Body getBody() {
        return body;
        
    }
}
