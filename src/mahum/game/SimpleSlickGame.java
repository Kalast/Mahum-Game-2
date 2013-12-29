/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import mahum.game.states.GameState;
import mahum.editor.characters.CharacterEditor;
import mahum.game.states.SearchServerState;
import mahum.game.states.ServeurState;
import mahum.game.states.TitleState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Kalast
 */
public class SimpleSlickGame extends StateBasedGame {
    
    public SimpleSlickGame() {
        super("Mon premier jeu de ouf");
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new TitleState());
        this.addState(new ServeurState());
        this.addState(new SearchServerState());
        this.addState(new GameState());
        this.addState(new CharacterEditor());
        this.enterState(TitleState.ID, new FadeOutTransition(Color.white), new FadeInTransition(Color.black));
    }
}
