/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public class List extends Container{
    private ArrayList<ListItem> items;

    public List(GUIContext container, Rectangle zone) {
        super(container, zone);
    }
    
    public void addItem(ListItem item, int position){
        item.setParentList(this);
        item.setLocation(this.getX() + padding, this.getY() + padding);
        item.setLocation(item.getX(), item.getY() + (36 * items.size()));
        items.add(position, item);
    }

    @Override
    protected void gainFocus() {
    }

    @Override
    protected void lostFocus() {
        
    }

    @Override
    protected void init() {
        items = new ArrayList();
        this.padding = 10;
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        super.render(container, g); //To change body of generated methods, choose Tools | Templates.
        g.setWorldClip(this.zone);

        for(ListItem item : items){
            item.render(container, g);
        }
        g.clearWorldClip();
    }
    
    
}
