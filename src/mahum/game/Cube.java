/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author Kalast
 */
public class Cube {
    protected Body body;
    protected float width;
    protected float height;
    protected Color color = Color.yellow;
    public BodyType type = BodyType.DYNAMIC;
    Polygon p = new Polygon();
    private boolean first = true;
    private Image img;

    public Cube(float width, float height) {
        this.width = width;
        this.height = height;
        try {
            img = new Image("images/test.png");
        } catch (SlickException ex) {
            Logger.getLogger(Cube.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private long time;
    public void render(Graphics g){
        
        g.setColor(Color.blue);
        //g.draw(new Rectangle(0, 0, 50, 50));
        
        g.setColor(Color.white);
        g.drawString(this.getPositionGame()+"", getPositionPhysics().x * Constants.SCALE_PHYSICS+width+3, getPositionPhysics().y * Constants.SCALE_PHYSICS);
        g.setColor(Color.red);
        g.drawString(this.getPositionPhysics()+"", getPositionPhysics().x * Constants.SCALE_PHYSICS+height+3, getPositionPhysics().y * Constants.SCALE_PHYSICS+15);   

        g.rotate(getPositionPhysics().x * Constants.SCALE_PHYSICS, getPositionPhysics().y * Constants.SCALE_PHYSICS, (float) Math.toDegrees(this.body.getAngle()));
        g.drawLine(getPositionPhysics().x * Constants.SCALE_PHYSICS - width / 2, getPositionPhysics().y * Constants.SCALE_PHYSICS - height / 2, getPositionPhysics().x * Constants.SCALE_PHYSICS + width / 2, getPositionPhysics().y * Constants.SCALE_PHYSICS + height / 2);
        g.drawLine(getPositionPhysics().x * Constants.SCALE_PHYSICS - width / 2, getPositionPhysics().y * Constants.SCALE_PHYSICS + height / 2, getPositionPhysics().x * Constants.SCALE_PHYSICS + width / 2, getPositionPhysics().y * Constants.SCALE_PHYSICS - height / 2);

        g.setColor(color);
        //p.setLocation(25,25);
        /*p = (Polygon) p.transform(Transform.createRotateTransform(body.getAngle(), p.getCenterX(), p.getCenterY())); 
        g.draw(p);
        p = (Polygon) p.transform(Transform.createRotateTransform(-body.getAngle(), p.getCenterX(), p.getCenterY()));*/
        p.setLocation(getPositionPhysics().x * Constants.SCALE_PHYSICS, getPositionPhysics().y * Constants.SCALE_PHYSICS);
        //p.setLocation(1230 + p.getWidth() / 2,719 + p.getHeight() / 2);
        g.drawImage(img, p.getX()-p.getWidth()/2, p.getY()-p.getHeight()/2);
        //g.draw(p);
        
        g.rotate(getPositionPhysics().x * Constants.SCALE_PHYSICS, getPositionPhysics().y * Constants.SCALE_PHYSICS, (float) -Math.toDegrees(this.body.getAngle()));
        g.setColor(Color.blue);
        
        //g.drawRect(0, 0, 50, 50);
    }
    
    public void setColor(Color c){
        color = c;
    }
    
    public Vec2 getPositionPhysics(){
        return this.body.getPosition();
    }
    
    public Vec2 getPositionGame(){
        return new Vec2((int)(this.body.getPosition().x * Constants.SCALE_PHYSICS-width/2), (int)(this.body.getPosition().y* Constants.SCALE_PHYSICS-width/2));
    }
    
    public float getAngle(){
        return this.body.getAngle();
    }
    
    public void create(World world, float x, float y){
        x = x / Constants.SCALE_PHYSICS + width / Constants.SCALE_PHYSICS / 2;
        y = y / Constants.SCALE_PHYSICS + height / Constants.SCALE_PHYSICS / 2;
        
        BodyDef def = new BodyDef();
        def.position = new Vec2(x,y);
        def.type = type;
        body = world.createBody(def);

        PolygonShape ps = new PolygonShape();

        p.addPoint(-width / 2, -height / 2); // 1
        p.addPoint(-width / 2, height / 2); // 2
        p.addPoint(width / 2, height / 2); // 3
        p.addPoint(width / 2, -height / 2); // 4
        //ps.setAsBox(width / Constants.SCALE_PHYSICS/2, height / Constants.SCALE_PHYSICS / 2);
        
        Vec2[] edges = new Vec2[4];
        for(int i = 0; i < p.getPointCount(); i++){
            edges[i] = new Vec2(p.getPoint(i)[0] / Constants.SCALE_PHYSICS, p.getPoint(i)[1] / Constants.SCALE_PHYSICS);
        }
        ps.set(edges, 4);
        //Utils.display_array(ps.getVertices());
        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.density = 0.8f;
        fd.friction = 0.5f;   
        fd.restitution = 0.3f;
        body.createFixture(fd);
        //body.setGravityScale(0.5f);
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
