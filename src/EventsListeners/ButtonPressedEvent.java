/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsListeners;

import java.util.EventObject;

/**
 *
 * @author harry bournis
 */
public class ButtonPressedEvent extends EventObject {

    private String screenName;
    
    public ButtonPressedEvent(Object source, String screenName) {
        super(source);
        this.screenName = screenName;
    }
    
    public String getScreenName() {
        return screenName;
    }
    
}
