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
import mahum.game.SomeRequest;
import mahum.game.SomeResponse;

/**
 *
 * @author Kalast
 */
public class GameClient extends Client{
    
    public GameClient(){
        super();
        Kryo kryo = this.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        this.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse) object;
                    System.out.println(response.text);
                    GameState.panel.addText(response.text);
                }
            }
        });
        this.start();
        try {
            this.connect(5000, "localhost", 6900, 6901);
            SomeRequest request = new SomeRequest();
            request.text = "Client : Je t'envoie une requête !";
            this.sendTCP(request);
        } catch (IOException ex) {
            Logger.getLogger(GameServeur.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
