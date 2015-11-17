/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsListeners;

import java.util.EventObject;
import model.PingType;

/**
 *
 * @author harry bournis
 */
public class DialogEvent extends EventObject {

    private String username;
    private String password;
    private String hostname;
    private String hostOrId;
    private PingType identifier;
    
    //Constructor for Connect User
    public DialogEvent(Object source, String username, String password, 
            String hostname) {
        super(source);
        this.username = username;
        this.password = password;
        this.hostname = hostname;
    }

    //COnstructor for Add New User
    public DialogEvent(Object source, String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
    }
    
    //Constructor for Delete User
    public DialogEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    //Constructor for Ping
    public DialogEvent(Object source, String username, String hostOrId, 
            PingType identifier) {
        super(source);
        this.username = username;
        this.hostOrId = hostOrId;
        this.identifier = identifier;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHostname() {
        return hostname;
    }
    
    public String getHostOrId() {
        return hostOrId;
    }
    
    public PingType getIdentifier() {
        return identifier;
    }
    
    
}