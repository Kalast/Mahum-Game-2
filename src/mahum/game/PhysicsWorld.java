/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Settings;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class PhysicsWorld {
    private World world;

    public PhysicsWorld() {
        this.world = new World();
        Settings settings = new Settings();
        settings.setAutoSleepingEnabled(true);
        System.out.println(settings.getStepFrequency());
        this.world.setGravity(new Vector2(0,9.8));
        
        world.setSettings(settings);
        

                // create all your bodies/joints
        // create the floor
        Rectangle floorRect = new Rectangle(300, 10.0);
        GameObject floor = new GameObject();
        floor.addFixture(new BodyFixture(floorRect));
        floor.setMass(Mass.Type.FIXED_LINEAR_VELOCITY);
        floor.setAngularDamping(0.5);
        floor.getFixtures().get(0).setRestitution(0.5);
        // move the floor down a bit
        floor.translate(150, 500);

        this.world.addBody(floor);

        // try a rectangle
        Rectangle rectShape = new Rectangle(50.0, 50.0);
        GameObject rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.translate(100.0, 2.0);
        //rectangle.setBullet(true);
        rectangle.setMass();
        rectangle.setLinearVelocity(10.0, 100.0);
        this.world.addBody(rectangle);        
    }
    
    public void update(int delta) {
        this.world.update(delta/1000.f);
    }

    
    public void render(Graphics g){
        // draw all the objects in the world
        for (int i = 0; i < this.world.getBodyCount(); i++) {
            // get the object
            GameObject go = (GameObject) this.world.getBody(i);
            // draw the object
            go.render(g);
        }
    }
}
