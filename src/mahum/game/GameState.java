/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.Collision;
import org.jbox2d.collision.Distance;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.TimeOfImpact;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.Mat33;
import org.jbox2d.common.Rot;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import static org.jbox2d.dynamics.World.WORLD_POOL_CONTAINER_SIZE;
import static org.jbox2d.dynamics.World.WORLD_POOL_SIZE;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.JointType;
import org.jbox2d.pooling.IDynamicStack;
import org.jbox2d.pooling.IWorldPool;
import org.jbox2d.pooling.normal.DefaultWorldPool;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Kalast
 */
public class GameState extends BasicGameState{
    public static final int ID = 1; 
    public static GameContainer container; 
    private World world;
    private float av = 0;
    private Wall wall;
    private CopyOnWriteArrayList<Ball> balls;
    public static TextPanel panel;
    private JaugePuissance jauge;
    private ArrayList<Floor> floors;
    
    private PunchingBall punchingBall;
    private PunchingBall punchingBall2;
    
    private long time;
    
    private Box box = new Box(0,0,800,600);
    private Box box2 = new Box(500,200,200,200);
    Car car = new Car(50,50);
    
    HillPiece hill = new HillPiece(700,300,100,0,20);
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        GameState.container = container;
        time = 0;
        balls = new CopyOnWriteArrayList();
        floors = new ArrayList();
        jauge = new JaugePuissance(3);
        punchingBall = new PunchingBall(600, 300, 2, 30);
        punchingBall2 = new PunchingBall(670, 300, 2, 30);
        
        Vec2 gravity = new Vec2(0, 9.8f);
        world = new World(gravity);
        world.setAllowSleep(true);
        world.getContactManager().m_contactListener = new ContactListener() {
            
            @Override
            public void beginContact(Contact contact) {
                
                if(contact.m_fixtureA.getBody().getUserData() instanceof Ball){
                    ((Ball)contact.m_fixtureA.getBody().getUserData()).setColor(Color.green);
                     ((Ball)contact.m_fixtureB.getBody().getUserData()).setColor(Color.green);
                    
                }
            }

            @Override
            public void endContact(Contact contact) {
                if(contact.m_fixtureA.getBody().getUserData() instanceof Ball){
                    ((Ball)contact.m_fixtureA.getBody().getUserData()).setColor(Color.red);
                     ((Ball)contact.m_fixtureB.getBody().getUserData()).setColor(Color.red);
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                
            }
        };

        box.create(world);
        box2.create(world);
        /*this.punchingBall.create(world);
        this.punchingBall2.create(world);*/
        car.create(world, 0, 0);
        hill.create(world);
        /*punchingBall2.getPunching().getBody().setAngularVelocity(50);
        punchingBall2.getPunching().getBody().setAngularDamping(0.2f);
        punchingBall2.getPunching().getBody().setLinearDamping(0.2f);*/
        //punchingBall.getPunching().getBody().setAngularVelocity(-10);
        //punchingBall.getPunching().getBody().setAngularDamping(10f);
        //punchingBall.getPunching().getBody().setLinearDamping(0.2f);
        
        //car.setLocation(50, 50);
        //car.getBody().applyForceToCenter(new Vec2(5f,0));
        
        EventManager.addEvents(container, world, balls, jauge, car);
        panel = new TextPanel(new Rectangle(0, 300, 500, 300));
        GameServeur serveur = new GameServeur();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.scale(Variables.ZOOM, Variables.ZOOM);
        g.setColor(Color.red);
        for(Ball ball : balls){
            ball.render(g);
        }
        box.render(g);
        box2.render(g);
        for(Floor floor : floors){
            floor.render(g);
        }
        
        /*punchingBall.render(g);
        punchingBall2.render(g);*/
        car.render(g);
        this.hill.render(g);
        this.jauge.render(g);
        panel.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(System.currentTimeMillis() - time >= 100){

            time = System.currentTimeMillis();
            
        } 
        world.step(delta / 1000f, 20, 3);
        autoClean();  
    }
    
        
     public void autoClean(){
        for(Ball ball : balls){
            if(ball.getPositionGame().y > 768 || ball.getPositionGame().y < -10){
                world.destroyBody(ball.getBody());
                balls.remove(ball);
            }
        }
    }
}
