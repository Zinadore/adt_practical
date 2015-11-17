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
public class UserNodeEvent extends EventObject{

    private String action;
    private String hostname;

    public UserNodeEvent(String username, String hostname, String action) {
        super(username);
        this.hostname = hostname;
        this.action = action;
    }
    
    public UserNodeEvent(String username, String action) {
        super(username);
        this.action = action;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public String getAction() {
        return action;
    }
}
