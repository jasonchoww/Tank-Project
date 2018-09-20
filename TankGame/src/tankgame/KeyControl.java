/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
/**
 *
 * @author jasonchow & carolynchen
 */
public class KeyControl extends Observable implements KeyListener  {
    private final Tank t;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    private Event e;
    
    boolean[] keys;
    
    public KeyControl(Event e,Tank t, int up, int down, int left, int right,int shoot){
        
        keys = new boolean[99];
        
        
        this.t = t;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.e = e;
    }
    
    
    @Override
    public void keyPressed(KeyEvent k){

       // System.out.println("Pressed test");
        int keyPressed = k.getKeyCode();
        
        
        if (keyPressed == up) {
            this.t.up = true;
        }
        if (keyPressed == down) {
            this.t.down= true;
        }
        if (keyPressed == left) {
            this.t.left=true;
        }
        if (keyPressed == right) {
            this.t.right=true;
        }
         if (keyPressed == shoot) {
            this.t.shoot=true;
        }
 
        
        e.setValue(k);

    }
    @Override
    public void keyReleased(KeyEvent k){
       // System.out.println("Pressed test");
        int keyPressed = k.getKeyCode();
        if (keyPressed == up) {
            this.t.up = false;
        }
        if (keyPressed == down) {
            this.t.down= false;
        }
        if (keyPressed == left) {
            this.t.left=false;
        }
        if (keyPressed == right) {
            this.t.right=false;
        }
         if (keyPressed == shoot) {
            this.t.shoot=false;
        }
         e.setValue(k);
    
    }
    
    @Override
    public void keyTyped(KeyEvent e){

    }

    
    
}
