/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mahum.game.states.GameState;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Kalast
 */
public class GameClient extends Client{
    
    private long tickServer;
    
    public GameClient(){
        super();
        Kryo kryo = this.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        kryo.register(TickRequest.class);
        kryo.register(LancerBallRequest.class);
        kryo.register(Vec2.class);
        this.start();
    }
    
    public boolean connect(String address, int portTCP, int portUDP){
        try {
            this.connect(5000, address, portTCP);
            SomeRequest request = new SomeRequest();
            request.text = "Client : Je t'envoie une requête !";
            this.sendTCP(request);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(GameServeur.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        return false;
    }
    
    public void setTickServer(long value){
        this.tickServer = value;
    }
}
