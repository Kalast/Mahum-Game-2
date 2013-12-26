/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.states;

import mahum.gui.ActionButton;
import mahum.gui.ActionPerform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Kalast
 */
public class TitleState extends BasicGameState{
    public static final int ID = 1;
    
    private ActionButton serveurBtn;
    private ActionButton clientBtn;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, final StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void enter(GameContainer container, final StateBasedGame game) throws SlickException {
        this.serveurBtn = new ActionButton(container, "serveur_btn", 300, 200);
        this.clientBtn = new ActionButton(container, "client_btn", 300, 400);
        
        this.serveurBtn.setActionPerform(new ActionPerform() {
            @Override
            public void perform() {
                game.enterState(ServeurState.ID);
            }
        });
        
        this.clientBtn.setActionPerform(new ActionPerform() {
            @Override
            public void perform() {
                game.enterState(SearchServerState.ID);
            }
        });
        
        super.enter(container, game);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        this.serveurBtn.setAcceptingInput(false);
        this.clientBtn.setAcceptingInput(false);
        super.leave(container, game); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.serveurBtn.render(container, g);
        this.clientBtn.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
}
