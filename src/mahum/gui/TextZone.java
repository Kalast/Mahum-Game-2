/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public class TextZone extends Component{

    private ArrayList<StringBuffer> lines;

    public TextZone(GUIContext container, Rectangle zone) {
        super(container, zone);
    }
    
    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        super.render(container, g); //To change body of generated methods, choose Tools | Templates.
        for(StringBuffer line : lines){
            line.toString().trim().split(" ");
            g.drawString(line.toString(), padding, padding);
        }
    }  
    
    public void append(String s){
        lines.get(lines.size()-1).append(s);
    }
    
    public void newLigne(){
        lines.add(new StringBuffer());
    }
    
    @Override
    protected void gainFocus() {}

    @Override
    protected void lostFocus() {}

    @Override
    protected void init() {
        lines = new ArrayList();
    }
    
}
