/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public class ListItem extends Component{
    private String name;
    private List parent;

    public ListItem(String name, GUIContext container) {
        super(container, null);
        this.name = name;
        this.zone = new Rectangle(0,0,this.container.getDefaultFont().getWidth(name)+this.padding*2, this.container.getDefaultFont().getLineHeight()+this.padding*2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.zone = new Rectangle(this.padding,this.padding,this.container.getDefaultFont().getWidth(name)+this.padding*2, this.container.getDefaultFont().getLineHeight()+this.padding*2);
    }
    
    public void setParentList(List list){
        this.parent = list;
    }

    @Override
    protected void mouseLeave() {
        this.background = Color.white;
        this.foreground = Color.black;
    }

    @Override
    protected void mouseEnter() {
        this.background = Color.black;
        this.foreground = Color.white;
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        System.out.println(name + " : " + this.hasFocus());
        if(this.hasFocus()){
            this.action.perform();
        }
    }
    
    @Override
    protected void gainFocus() {
        System.out.println(this.name + " gain focus");
    }

    @Override
    protected void lostFocus() {
        System.out.println(this.name + " lost focus");
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        super.render(container, g); //To change body of generated methods, choose Tools | Templates.
        g.setClip(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(foreground);
        g.drawString(name, this.getX() + padding, this.getY() + this.padding);
        g.clearClip();
    }

    @Override
    protected void init() {
        this.name = "";
        this.padding = 5;
        this.background = Color.white;
        this.foreground = Color.black;
        this.zone = new Rectangle(this.padding,this.padding,this.container.getDefaultFont().getWidth(name)+this.padding*2, this.container.getDefaultFont().getLineHeight()+this.padding*2);
    }
}
