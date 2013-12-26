/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

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

/**
 *
 * @author Kalast
 */

public class GameServeur {

    public static Server server;
    public static Client client;
    
    public GameServeur() {
        server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        server.start();
        try {
            server.bind(6900, 6901);
            
            server.addListener(new Listener() {
                public void received (Connection connection, Object object) {
                   if (object instanceof SomeRequest) {
                      SomeRequest request = (SomeRequest)object;
                      GameState.panel.addText(request.text);
                      System.out.println(request.text);

                      SomeResponse response = new SomeResponse();
                      response.text = "Server : J'ai bien reçu ta requête !";
                      connection.sendTCP(response);
                   }
                }
             });
            
            testClient();
        } catch (IOException ex) {
            Logger.getLogger(GameServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testClient(){
        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse) object;
                    System.out.println(response.text);
                    GameState.panel.addText(response.text);
                }
            }
        });
        client.start();
        try {
            client.connect(5000, "localhost", 6900, 6901);
            SomeRequest request = new SomeRequest();
            request.text = "Client : Je t'envoie une requête !";
            client.sendTCP(request);
        } catch (IOException ex) {
            Logger.getLogger(GameServeur.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
