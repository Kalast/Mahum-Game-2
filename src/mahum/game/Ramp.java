/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Kalast
 */
public class Ramp {
    private float width;
    private float y1;
    private float y2;
    private float x;
    private PolygonShape shape;
    private Body body;
    private float yGround;
    Polygon p = new Polygon();

    public Ramp(float width, float yGround, float y1, float y2, float x) {
        this.width = width;
        this.y1 = y1;
        this.y2 = y2;
        this.x = x;
        this.yGround = yGround;
    }
    
    public void create(World world){
        float x_p = x / Constants.SCALE_PHYSICS;
        float width_p = width / Constants.SCALE_PHYSICS;

        shape = new PolygonShape();
        shape.m_count = 4;
        p.setAllowDuplicatePoints(true);
        p.addPoint(0, 0); // 1
        p.addPoint(0, -y1);
        p.addPoint(width, -y2); // 3
        p.addPoint(width, 0); // 4
        
        
         // 2
        
        
        
        Vec2[] edges = new Vec2[4];
        for(int i = 0; i < p.getPointCount(); i++){
            edges[i] = new Vec2(p.getPoint(i)[0] / Constants.SCALE_PHYSICS, p.getPoint(i)[1] / Constants.SCALE_PHYSICS);
        }
        
        shape.set(edges, 4);
        
        System.out.println("-------");
        System.out.println("w = " + width_p);
        System.out.println("x_p + width_p = " + (x_p + width_p));
        System.out.println("-------");
        Utils.display_array(shape.getVertices());
        System.out.println("--------");
        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set(x_p, this.yGround / Constants.SCALE_PHYSICS);
        
        body = world.createBody(def);
        FixtureDef f = new FixtureDef();
        f.shape = shape;
        f.density = 1;
        f.restitution = 0.3f;
        
        body.createFixture(f);
    }
    
    public Vec2 getPositionGame(){
        return new Vec2((int)(this.body.getPosition().x * Constants.SCALE_PHYSICS), (int)(this.body.getPosition().y * Constants.SCALE_PHYSICS));
    }
    
    public void render(Graphics g){
        p.setLocation(x, this.yGround);
        g.drawLine(this.body.getPosition().x * Constants.SCALE_PHYSICS, this.body.getPosition().y * Constants.SCALE_PHYSICS, this.body.getPosition().x * Constants.SCALE_PHYSICS, this.body.getPosition().y * Constants.SCALE_PHYSICS);
        //g.drawLine(this.body.getPosition().x * Constants.SCALE_PHYSICS - width / 2, this.body.getPosition().y * Constants.SCALE_PHYSICS, this.body.getPosition().x * Constants.SCALE_PHYSICS + width / 2, this.body.getPosition().y * Constants.SCALE_PHYSICS / 2);
        g.setColor(Color.blue);
        g.fill(p);
        g.setColor(Color.red);
        g.draw(p);
        g.setColor(Color.blue);
        /*g.draw(p, new ShapeFill() {

            @Override
            public Color colorAt(Shape shape, float x, float y) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Vector2f getOffsetAt(Shape shape, float x, float y) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        g.draw(p);*/
        
    }
}
