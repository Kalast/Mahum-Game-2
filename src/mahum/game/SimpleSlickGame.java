/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import com.oracle.jrockit.jfr.Transition;
import mahum.game.states.GameState;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import mahum.game.states.SearchServerState;
import mahum.game.states.ServeurState;
import mahum.game.states.TitleState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Kalast
 */
public class SimpleSlickGame extends StateBasedGame {

    private AppGameContainer container; // le conteneur du jeu 

    public SimpleSlickGame() {
        super("Mon premier jeu de ouf");
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new TitleState());
        this.addState(new ServeurState());
        this.addState(new SearchServerState());
        this.addState(new GameState());
        this.enterState(TitleState.ID, new FadeOutTransition(Color.white), new FadeInTransition(Color.black));
    }
    
    

    public static void main(String[] args) {
        try
        { 
            AppGameContainer container = new AppGameContainer(new SimpleSlickGame()); 
            container.setDisplayMode(800, 600, false); // fenêtre de 1280*768 fullscreen =false !! 
            //container.setTargetFrameRate(60); // on règle le FrameRate 
            container.setVSync(true);
            //container.setFullscreen(true);
            container.setUpdateOnlyWhenVisible(false);
            container.setVerbose(true);
            container.setAlwaysRender(true);
            
            //container.getInput().enableKeyRepeat();
            container.start(); //on démarre le container 
            
        } 
        catch (SlickException e) {e.printStackTrace();
        } // l'exception de base de slick !!  
    }

    

}
