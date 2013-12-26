/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class Wall {
    private Body body;
    private float x;
    private float y;
    private float height;
    public float rotate =0;

    public Wall(float x, float y, float height) {
        this.x = x / Constants.SCALE_PHYSICS;
        this.y = y / Constants.SCALE_PHYSICS;
        this.height = height / Constants.SCALE_PHYSICS;
    }
    
    public void render(Graphics g){
        g.drawLine(x * Constants.SCALE_PHYSICS, y * Constants.SCALE_PHYSICS, x * Constants.SCALE_PHYSICS, y * Constants.SCALE_PHYSICS + height * Constants.SCALE_PHYSICS);
    }
    
    public void create(World world){
        BodyDef def = new BodyDef();
        def.position = new Vec2(0, 0);
        def.type = BodyType.STATIC;
        
        EdgeShape es = new EdgeShape();
        es.set(new Vec2(x, y), new Vec2(x, y + height));
        
        FixtureDef fp = new FixtureDef();
        fp.shape = es;
        fp.restitution = 0.6f;
        fp.friction = 1f;
        fp.density = 1f;
        body = world.createBody(def);
        body.createFixture(fp);
        body.setTransform(body.getPosition(), (float) Math.toRadians(rotate));
    }
    
    public int getXOnGame(){
        return (int) (body.getPosition().x * Constants.SCALE_PHYSICS);
    }
    
    public int getYOnGame(){
        return (int) (body.getPosition().y * Constants.SCALE_PHYSICS);
    }
    
    public float getXOnWorld(){
        return body.getPosition().x;
    }
    
    public float getYOnWorld(){
        return body.getPosition().y;
    }
}
