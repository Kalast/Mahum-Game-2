/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import mahum.game.states.GameState;
import mahum.game.SomeRequest;
import mahum.game.SomeResponse;

/**
 *
 * @author Kalast
 */

public class GameServeur extends Server{
    private boolean started;
    
    public GameServeur() {
        super();
        started = false;
        Kryo kryo = this.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        this.start();
        try {
            this.bind(6900, 6901);
            started = true;
        } catch (IOException ex) {
            Logger.getLogger(GameServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isStarted() {
        return started;
    }
    
    
    
}
