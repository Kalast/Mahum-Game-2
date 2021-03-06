/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.states;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import mahum.game.net.SomeRequest;
import mahum.game.net.SomeResponse;
import mahum.game.TextPanel;
import mahum.game.net.GameServeur;
import mahum.game.net.LancerBallRequest;
import mahum.gui.ActionButton;
import mahum.gui.ActionPerform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Kalast
 */
public class ServeurState extends BasicGameState{
    public static final int ID = 2; 
    
    private TextPanel panel;
    private GameServeur serveur;
    private ActionButton startServBtn;
    private ActionButton clearLogs;
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        startServBtn = new ActionButton(container, "start_btn", 180, 25);
        clearLogs = new ActionButton(container, "clear_btn", 420, 25);
        clearLogs.setActionPerform(new ActionPerform() {

            @Override
            public void perform() {
                panel.clear();
            }
        });
        panel = new TextPanel(container, new Rectangle(100,100,600,400));
        startServBtn.setActionPerform(new ActionPerform() {

            @Override
            public void perform() {
                if(serveur == null){
                    serveur = new GameServeur();
                    if(serveur.isStarted()){
                        panel.addText("Le serveur à démarré !");
                        serveur.addListener(new Listener() {
                            public void received (Connection connection, Object object) {
                               if (object instanceof SomeRequest) {
                                  SomeRequest request = (SomeRequest)object;
                                  panel.addText(request.text);
                                  System.out.println(request.text);

                                  SomeResponse response = new SomeResponse();
                                  response.text = "Server : J'ai bien reçu ta requête !";
                                  connection.sendTCP(response);
                               } else if (object instanceof LancerBallRequest) {
                                  LancerBallRequest request = (LancerBallRequest)object;
                                  serveur.sendToAllExceptTCP(connection.getID(), request);
                               }
                            }

                            @Override
                            public void idle(Connection connection) {
                                //panel.addText(connection.getID() + ":Idle:" + System.currentTimeMillis() / 1000);
                            }

                            @Override
                            public void disconnected(Connection connection) {
                                panel.addText(connection.getID() + " s'est déconnecté ..");
                            }

                            @Override
                            public void connected(Connection connection) {
                                panel.addText("Envoi du Tick : " + serveur.getTickRequest().value);
                                connection.sendTCP(serveur.getTickRequest());
                                panel.addText(connection + " s'est connecté ..");
                            }
                         });
                    }
                }
            }
        });
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        startServBtn.render(container, g);
        this.clearLogs.render(container, g);
        panel.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(this.serveur != null){
            this.serveur.majTick(delta);
        }
    }
    
}
