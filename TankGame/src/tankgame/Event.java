/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;
import java.awt.event.KeyEvent;
import java.util.Observable;
/**
 *
 * @author carolynchen
 */

public class Event extends Observable {
    Object event;
    int keyType;
    public Event(){

    }
    public void setValue(KeyEvent e){
        keyType = 1;
        event = e;
        setChanged();
        notifyObservers(event);
    }
    
    public Object getEvent(){
        return event;
    }
    public void setValue(String msg) {
          event = msg;
          setChanged();
         // trigger notification
         notifyObservers(event);  
        }
 }


