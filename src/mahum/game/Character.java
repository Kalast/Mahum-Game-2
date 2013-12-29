/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kalast
 */
public class Character {
    private Body body;
    public BodyType type = BodyType.DYNAMIC;
    public Image img;
    private float x;
    private float y;

    public Character(String filename) {
        try {
            img = new Image(filename);
        } catch (SlickException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(World world, float x, float y){
        this.x = x / Constants.SCALE_PHYSICS;
        this.y = y / Constants.SCALE_PHYSICS;
        
        BodyDef def = new BodyDef();
        def.position = new Vec2(this.x, this.y);
        def.type = type;
        body = world.createBody(def);
        body.setFixedRotation(true);
        
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(this.img.getWidth() / Constants.SCALE_PHYSICS/2, this.img.getHeight() / Constants.SCALE_PHYSICS / 2);
    
        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.density = 1f;
        fd.friction = 1f;
        fd.restitution = 0.1f;
        body.createFixture(fd);
    }
    
    public void render(Graphics g){
        g.drawImage(img, this.getBody().getPosition().x * Constants.SCALE_PHYSICS - this.img.getWidth() / 2, this.getBody().getPosition().y * Constants.SCALE_PHYSICS - this.img.getHeight() / 2);
    }

    public Body getBody() {
        return body;
    }
    
    public void jump(){
        if(this.body.getContactList() != null){
            this.body.applyLinearImpulse(new Vec2(0,-5), new Vec2(0,0));
        }            
    }
    
    public void moveLeft(){
        if(this.body.getContactList() != null){
            this.body.applyLinearImpulse(new Vec2(-1,0), new Vec2(0,0));
        }            
    }
    
    public void moveRight(){
        if(this.body.getContactList() != null){
            this.body.applyLinearImpulse(new Vec2(1,0), new Vec2(0,0));
        }            
    }
}
