/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mahum.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Vector2;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class GameObject extends Body {

    /**
     * The color of the object
     */
    protected float[] color = new float[4];

    /**
     * Default constructor.
     */
    public GameObject() {
        // randomly generate the color
        this.color[0] = (float) Math.random() * 0.5f + 0.5f;
        this.color[1] = (float) Math.random() * 0.5f + 0.5f;
        this.color[2] = (float) Math.random() * 0.5f + 0.5f;
        this.color[3] = 1.0f;
    }

    /**
     * Draws the body.
     * <p>
     * Only coded for polygons.
     *
     * @param gl the OpenGL graphics context
     */
    public void render(Graphics g) {
        g.pushTransform();
        g.translate((float)this.transform.getTranslationX(), (float)this.transform.getTranslationY());
        g.rotate(0, 0, (float) Math.toDegrees(this.transform.getRotation()));
        
        // loop over all the body fixtures for this body
                        for (BodyFixture fixture : this.fixtures) {
                                // get the shape on the fixture
                                Convex convex = fixture.getShape();
                                // check the shape type
                                if (convex instanceof Polygon) {
                                        // since Triangle, Rectangle, and Polygon are all of
                                        // type Polygon in addition to their main type
                                        Polygon p = (Polygon) convex;
                                        org.newdawn.slick.geom.Polygon pslick = new org.newdawn.slick.geom.Polygon();
                                        for (Vector2 v : p.getVertices()) {
                                                pslick.addPoint((float)v.x, (float)v.y);
                                        }
                                        
                                        g.fill(pslick);
                                        // set the color
                                        g.setColor(new Color(this.color[0],this.color[1],this.color[2]));
                                        
                                        // set the color
                                        g.setColor(new Color(this.color[0]*0.8f,this.color[1]*0.8f,this.color[2]*0.8f));
                                        
                                        // draw the shape
                                        g.draw(pslick);
                                }
                                // circles and other curved shapes require a little more work, so to keep
                                // this example short we only include polygon shapes; see the RenderUtilities
                                // in the Sandbox application
                        }
        
        g.popTransform();
    }
}
