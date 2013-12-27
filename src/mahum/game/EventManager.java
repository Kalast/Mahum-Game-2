/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import mahum.game.net.SomeRequest;
import mahum.game.net.GameServeur;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import mahum.game.net.GameClient;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;

/**
 *
 * @author Kalast
 */
public class EventManager {
    public static Input input;
    
    public static void addEvents(GameContainer container, Object... a){
        input = container.getInput();
        final World world = (World) a[0];
        final CopyOnWriteArrayList<Ball> balls = (CopyOnWriteArrayList<Ball>) a[1];
        final JaugePuissance jauge = (JaugePuissance) a[2];
        final Cube cube = (Cube) a[3];
        final GameClient client = (GameClient) a[4];
        container.getInput().addMouseListener(new MouseListener() {

            @Override
            public void mouseWheelMoved(int change) {
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
        
        final float testSpeed = 0.3f;
        //cube.getBody().setLinearDamping(0.5f);
        container.getInput().addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(int key, char c) {
                if(key == Input.KEY_ENTER){
                    SomeRequest request = new SomeRequest();
                    request.text = "Client : J'appuie sur la touche ENTREE ! OUAAAAAAAAAAAAAAAAAAAAAAAAh !!";
                    client.sendTCP(request);
                }
                
                /*if(key == Input.KEY_D){
                    cube.getBody().applyForceToCenter(new Vec2(testSpeed,0));
                    if(cube.getBody().getLinearVelocity().x > testSpeed){
                        cube.getBody().setLinearVelocity(new Vec2(testSpeed,cube.getBody().getLinearVelocity().y));
                    }
                }
                
                if(key == Input.KEY_Z){
                    cube.getBody().applyForceToCenter(new Vec2(0,-testSpeed));
                    if(cube.getBody().getLinearVelocity().y < -testSpeed){
                        cube.getBody().setLinearVelocity(new Vec2(cube.getBody().getLinearVelocity().x,-testSpeed));
                    }
                }
                
                if(key == Input.KEY_Q){
                    cube.getBody().applyForceToCenter(new Vec2(-testSpeed,0));
                    if(cube.getBody().getLinearVelocity().x < -testSpeed){
                        cube.getBody().setLinearVelocity(new Vec2(-testSpeed,cube.getBody().getLinearVelocity().y));
                    }
                }
                
                if(key == Input.KEY_S){
                    cube.getBody().applyForceToCenter(new Vec2(0,testSpeed));
                    if(cube.getBody().getLinearVelocity().y > testSpeed){
                        cube.getBody().setLinearVelocity(new Vec2(cube.getBody().getLinearVelocity().x,testSpeed));
                    }
                }*/
            }

            @Override
            public void keyReleased(int key, char c) {
                
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
    }
}
