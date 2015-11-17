/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsListeners;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author harry bournis
 */
public class AddRemoveEvent extends Event {
    
    public static final EventType<AddRemoveEvent> ADD_USER = new EventType<>(Event.ANY, "ADD_USER");
    public static final EventType<AddRemoveEvent> REMOVE_USER = new EventType<>(Event.ANY, "REMOVE_USER");
    
    private String username;
    private String password;
    private String hostname;
    
    //Constructor for Add User
    public AddRemoveEvent(EventType<? extends Event> eventType, String username, 
        String password) {
        super(ADD_USER);
        this.username = username;
        this.password = password;
    }
    
    //Constructor for Remove User
    public AddRemoveEvent(EventType<? extends Event> eventType, String username) {
        super(REMOVE_USER);
        this.username = username;
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
}
