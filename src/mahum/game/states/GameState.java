/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.states;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.net.InetAddress;
import mahum.game.net.GameServeur;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import mahum.game.Ball;
import mahum.game.Box;
import mahum.game.Car;
import mahum.game.EventManager;
import mahum.game.Floor;
import mahum.game.HillPiece;
import mahum.game.JaugePuissance;
import mahum.game.PunchingBall;
import mahum.game.SomeResponse;
import mahum.game.TextPanel;
import mahum.game.Variables;
import mahum.game.Wall;
import mahum.game.net.GameClient;
import mahum.gui.ActionPerform;
import mahum.gui.TextField;
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
    public static final int ID = 3; 
    public static GameContainer container; 
    private World world;
    private float av = 0;
    private Wall wall;
    private CopyOnWriteArrayList<Ball> balls;
    public static TextPanel panel;
    private JaugePuissance jauge;
    private ArrayList<Floor> floors;
    
    private GameClient client;
    
    private PunchingBall punchingBall;
    private PunchingBall punchingBall2;
    
    private long time;
    
    private Box box = new Box(0,0,800,600);
    private Box box2 = new Box(500,200,200,200);
    Car car = new Car(50,50);
    private TextField field;
    HillPiece hill = new HillPiece(700,300,100,0,20);
    
    @Override
    public int getID() {
        return ID;
    }

    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("INIT GAMESTATE");
        GameState.container = container;
        time = System.currentTimeMillis();
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
        
        field = new TextField(container);
    }

    public void setClient(GameClient client) {
        this.client = client;
    }
    
    @Override
    public void enter(GameContainer container, final StateBasedGame game) throws SlickException {
        box.create(world);
        box2.create(world);
        //car.create(world, 0, 0);
        hill.create(world);
        this.field.setLocation(0, 500);
        this.field.setAction(new ActionPerform() {

            @Override
            public void perform() {
                field.clearText();
            }
        });
        panel = new TextPanel(container, new Rectangle(0, 300, 500, 300));
        client.addListener(new Listener(){

            @Override
            public void disconnected(Connection connection) {
                game.enterState(SearchServerState.ID);
            }

            @Override
            public void connected(Connection connection) {
                panel.addText("Vous êtes connecté !");
            }
            
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse) object;
                    System.out.println(response.text);
                    panel.addText(response.text);
                }
            }
            
        });
        EventManager.addEvents(container, world, balls, jauge, car, client);
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
        //car.render(g);
        this.hill.render(g);
        this.jauge.render(g);
        panel.render(g);
        field.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        world.step(delta / 1000f, 8, 3);
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
