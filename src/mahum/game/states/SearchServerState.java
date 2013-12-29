/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.states;

import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import mahum.game.net.GameClient;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Kalast
 */
public class SearchServerState extends BasicGameState{
    public static final int ID = 4;
    private Animation loading;
    private String message;
    private float centerX;
    private long time = 0;
    private Thread t;
    private InetAddress address;
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, final StateBasedGame game) throws SlickException {
        loading = new Animation(new SpriteSheet("images/loading.png", 102, 102),50);
        loading.setLooping(true);
    }

    @Override
    public void enter(GameContainer container, final StateBasedGame game) throws SlickException {
        loading.start();
        t = new Thread(new Runnable() {
                private boolean discover = false;
            
                @Override
                public void run() {
                    while(!discover){
                        address = GameState.client.discoverHost(6901, 1000);
                        if(address != null){
                            discover = true;
                        }
                    }
                }
            });
        t.start();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        loading.draw(300, 100, 200, 200);
        g.drawString("Recherche de serveur en cours ...", 250, 350);
    }

    @Override
    public void update(GameContainer container, final StateBasedGame game, int delta) throws SlickException {
        if (address != null) {
            try {
               GameState.client.connect(5000, address, 6900, 6901);
                
            } catch (IOException ex) {
                System.out.println("Impossible de se connecter au serveur pour le moment !");
            }
            
            game.enterState(mahum.game.states.GameState.ID);
       }
    }
    
}
