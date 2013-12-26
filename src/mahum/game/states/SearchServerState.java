/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.states;

import com.esotericsoftware.kryonet.Client;
import java.net.InetAddress;
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

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        loading = new Animation(new SpriteSheet("images/loading.png", 102, 102),50);
        loading.setLooping(true);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        loading.start();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        loading.draw(300, 100, 200, 200);
        g.drawString("Recherche de serveur en cours ...", 250, 350);
    }

    @Override
    public void update(GameContainer container, final StateBasedGame game, int delta) throws SlickException {
        if(System.currentTimeMillis() - time >= 1000){
            time = System.currentTimeMillis();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    GameClient c = new GameClient();
                    InetAddress address = c.discoverHost(6901, 5000);
                    if(address != null){
                        if(c.connect(address.getHostAddress(), 6900, 6901)){
                            ((mahum.game.states.GameState)game.getState(GameState.ID)).setClient(c);
                            game.enterState(GameState.ID);
                        }
                    }
                }
            }).start();
        } 
    }
    
}
