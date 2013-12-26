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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Kalast
 */
public class GameState extends BasicGameState{
    public static final int ID = 1; 
    private World world;
    private float av = 0;
    private Wall wall;
    private CopyOnWriteArrayList<Ball> balls;
    
    private JaugePuissance jauge;
    private ArrayList<Floor> floors;
    
    private PunchingBall punchingBall;
    private PunchingBall punchingBall2;
    
    private long time;
    
    private Box box = new Box(0,0,800,600);
    private Box box2 = new Box(500,200,200,200);
    Car car = new Car(50,50);
    
    HillPiece hill = new HillPiece(800,300,100,0,20);
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        time = 0;
        balls = new CopyOnWriteArrayList();
        floors = new ArrayList();
        jauge = new JaugePuissance(3);
        punchingBall = new PunchingBall(600, 300, 2, 30);
        punchingBall2 = new PunchingBall(670, 300, 2, 30);
        
        container.getInput().addMouseListener(new MouseListener() {

            @Override
            public void mouseWheelMoved(int change) {
                System.out.println(change);
                if(change > 0){
                    Variables.ZOOM ++;
                } else {
                    Variables.ZOOM --;
                }
                
            }

            @Override
            public void mouseClicked(int button, int x, int y, int clickCount) {
                /*if(button == org.newdawn.slick.Input.MOUSE_RIGHT_BUTTON){
                    floors.add(new Floor(x, y, 800, 20));
                    floors.get(floors.size()-1).create(world);
                }else{
                    floors.add(new Floor(x, y, 800, -20));
                    floors.get(floors.size()-1).create(world);
                }*/
            }

            @Override
            public void mousePressed(int button, int x, int y) {
                balls.add(new Ball(10));
                balls.get(balls.size()-1).create(world, x, y);
                balls.get(balls.size()-1).stopMovement();
                jauge.setStart(x, y);
                jauge.setEnd(x, y);
                jauge.setActive(true);
            }

            @Override
            public void mouseReleased(int button, int x, int y) {
                jauge.setActive(false);
                balls.get(balls.size()-1).resetMovement();
                balls.get(balls.size()-1).getBody().applyForceToCenter(jauge.getVector());
                //car.resetMovement();
            }

            @Override
            public void mouseMoved(int oldx, int oldy, int newx, int newy) {
                /*balls.add(new Ball(20));
                balls.get(balls.size()-1).create(world, newx, newy);*/
                      
            }

            @Override
            public void mouseDragged(int oldx, int oldy, int newx, int newy) {
                //balls.get(balls.size()-1).setLocation(newx, newy);
                jauge.setEnd(newx, newy);
                /*car.stopMovement();
                car.setLocation(newx, newy);*/
            }

            @Override
            public void setInput(Input input) {
               
            }

            @Override
            public boolean isAcceptingInput() {
                return true;
            }

            @Override
            public void inputEnded() {
                
            }

            @Override
            public void inputStarted() {
                
            }
        });
        

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
                
                System.out.println(contact.getManifold().localPoint);
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
        System.out.println("c=" + car.getPositionPhysics());
        hill.create(world);
        /*punchingBall2.getPunching().getBody().setAngularVelocity(50);
        punchingBall2.getPunching().getBody().setAngularDamping(0.2f);
        punchingBall2.getPunching().getBody().setLinearDamping(0.2f);*/
        //punchingBall.getPunching().getBody().setAngularVelocity(-10);
        //punchingBall.getPunching().getBody().setAngularDamping(10f);
        //punchingBall.getPunching().getBody().setLinearDamping(0.2f);
        
        //car.setLocation(50, 50);
        System.out.println(this.car.getPositionPhysics());
        //car.getBody().applyForceToCenter(new Vec2(5f,0));
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
