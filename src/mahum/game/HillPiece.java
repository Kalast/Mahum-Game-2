/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import java.util.ArrayList;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Kalast
 */
public class HillPiece {
    ArrayList<Ramp> rampes;
    
    private float width, height, y, x;
    private int nbRamp;

    public HillPiece(float width, float height, float y, float x, int nbRamp) {
        this.width = width;
        this.height = height;
        this.y = y;
        if(nbRamp % 2 != 0){
            nbRamp ++;
        }
        this.nbRamp = nbRamp;
    }
    
    public void render(Graphics g){
        for(Ramp r : rampes){
            r.render(g);
        }
    }
    
    public void create(World world){
        float rampWidth = width / nbRamp;
        this.rampes = new ArrayList();
    
        float c = 1;
        int phase = this.nbRamp / 2;
        for(int i = phase; i < nbRamp+phase; i ++){
            float y1 = (float)Math.round((height / 2 + height / 2*Math.cos(2*Math.PI/nbRamp*i)));
            float y2 = (float)Math.round((height / 2 + height / 2*Math.cos(2*Math.PI/nbRamp*(i+1))));
            this.rampes.add(new Ramp(rampWidth, 768, y + y1, y + y2, x+rampWidth*(i-phase)));
            this.rampes.get(i-phase).create(world);
        }
        
        /*for(int i = nbRamp / 2 - 1; i > 0; i --){
            this.rampes.add(new Ramp(rampWidth, 768,  y + height - height /(i+1), (y + height) - (height / (nbRamp/2 - i)), x+rampWidth*(i-1)));
            this.rampes.get(this.rampes.size()-1).create(world);
        }*/
    }
}
