/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.gui;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author Kalast
 */
public class TextCursor {
    public static final int TO_LEFT = 0;
    public static final int TO_RIGHT = 1;
    public static final int NO_DIRECTION = -1;

    private int position;
    private boolean atEnd;
    private boolean atStart;
    private TextComponent parent;
    private int linePixelposition;
    private long time;
    private boolean visible;
    private boolean returnAllowed;
    
    private int movedCharToRight;
    private int movedCharToLeft;
    private boolean active;

    public TextCursor() {
        this.active = false;
        this.returnAllowed = false;
        reset();
    }
    
    public void attachTo(TextComponent parent){
        this.parent = parent;
        this.position = parent.getText().length();
        linePixelposition = this.parent.getGUI().getDefaultFont().getWidth(parent.getText().toString());
    }  
    
    public void moveToEnd(){
        if(this.active){
            this.position = parent.getText().length();
        }
    }
    
    public void moveToStart(){
        if(this.active){
            this.position = 0;
        }
    }  
    
    public void reset(){
        this.position = 0;
        this.atStart = true;
        this.atEnd = false;
        this.linePixelposition = 0;
        this.visible = true;
        this.movedCharToRight = 0;
        this.movedCharToLeft = 0;
        if(parent != null){
            this.parent.setTextLocation(0, 0);
        }
    }
    
    public void moveTo(int position){
        if(this.active){
            this.position = position;
        }
    }
    
    public void moveToRight(){
        if(this.active){
            if(this.canMove(TextCursor.TO_RIGHT, 1)){
                this.position ++;
                linePixelposition += this.parent.getGUI().getDefaultFont().getWidth(this.parent.getText().charAt(position-1) + " ");
                if(linePixelposition > this.parent.getZoneText().getWidth()){
                    this.leaveZonetoRight();
                }
                this.resetDisplay();
            }
        }
    }
    
    public void moveToLeft(){
        if(this.active){
            if(this.canMove(TextCursor.TO_LEFT, 1)){
                this.position --;
                linePixelposition -= this.parent.getGUI().getDefaultFont().getWidth(this.parent.getText().charAt(position) + " ");
                if(linePixelposition < 0){
                    this.leaveZonetoLeft();
                }
                this.resetDisplay();
            }
        }
    }
    
    private boolean canMove(int direction, int value){
        if(direction == TextCursor.TO_LEFT){
            if(this.position - value >= 0){
                return true;
            }
        } else if(direction == TextCursor.TO_RIGHT){
            if(this.position + value <= this.parent.getText().length()){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean canWrite(){
        return true;
    }
    
    private boolean canDelete(){
        return true;
    }

    public int getPosition() {
        return position;
    }

    public boolean isAtEnd() {
        return atEnd;
    }

    public boolean isAtStart() {
        return atStart;
    }
    
    public void write(String s){
        if(this.active){
            if(s.length() > 0){
               this.parent.getText().append(s);
                this.position += s.length();

            }  
        }
    }
    
    public void write(char c){
        if(this.active){
            if(!((c+"").replaceAll("\\p{C}", "").equals(""))){
                this.parent.getText().insert(position, c);
                this.moveToRight();
            }
        }
    }
    
    private void leaveZonetoRight(){
        if(this.active){
            int wc = this.parent.getGUI().getDefaultFont().getWidth(this.parent.text.charAt(this.parent.text.length()-1)+" ");
            linePixelposition -= wc;
            moveTextToLeft(wc);
        }
    }
    
    private void leaveZonetoLeft(){
        if(this.active){
            int wc = this.parent.getGUI().getDefaultFont().getWidth(this.parent.text.charAt(position)+" ");
            linePixelposition = 0;
            if(this.movedCharToLeft > 0){
                moveTextToRight(wc);
            }  
        }
    }
    
    private void moveTextToLeft(int width){
        if(this.active){
            this.parent.setXPosText(this.parent.getXPosText()-width);
            this.movedCharToLeft ++;
            if(this.movedCharToRight > 0){
                this.movedCharToRight --;
            }
        }
    }
    
    private void moveTextToRight(int width){
        if(this.active){
            this.parent.setXPosText(this.parent.getXPosText()+width);

            this.movedCharToRight ++;
            if(this.movedCharToLeft > 0){
                this.movedCharToLeft --;
            }
        }
    }
    
    public void deleteChar(){
        if(this.active){
            if(this.position < this.parent.getText().length()){
                if(this.movedCharToLeft > 0){
                    position --;
                    this.moveTextToRight(this.parent.getGUI().getDefaultFont().getWidth(this.parent.text.charAt(position)+" "));
                }
                this.parent.getText().deleteCharAt(position);
            }
            resetDisplay();
        }
    }
    
    public void backChar(){
        if(this.active){
            if(this.position > 0){
                if(this.movedCharToLeft > 0){
                    position --;
                    this.moveTextToRight(this.parent.getGUI().getDefaultFont().getWidth(this.parent.text.charAt(position)+" "));
                } else {
                    this.moveToLeft();
                }
                this.parent.getText().deleteCharAt(position);
            }
        }
    }
    
    private void resetDisplay(){
        this.time = System.currentTimeMillis();
        this.visible = true;
    }
    
    public void render(Graphics g){
        if(this.active){
            if(System.currentTimeMillis() - time > 500){
                toggleVisible();
                time = System.currentTimeMillis();
            }
            if(this.visible){
                g.setColor(Color.black);
                g.fillRect(parent.getX()+this.parent.getPadding()+linePixelposition, parent.getY()+this.parent.getPadding(), 2, this.parent.getGUI().getDefaultFont().getLineHeight());
            }

            g.drawString("Cursor pos : " + this.position, 10, 30);
            g.drawString("MovedToLeft : " + this.movedCharToLeft, 10, 50);
            g.drawString("MovedToRight : " + this.movedCharToRight, 10, 70);
        }
    }

    private void toggleVisible() {
        this.visible = !this.visible;
    }

    public void setActive(boolean b) {
        this.active = b;
        this.resetDisplay();
    }
}
