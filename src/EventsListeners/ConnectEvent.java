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
public class ConnectEvent extends Event {

    public static final EventType<ConnectEvent> SERVER_STATE = new EventType<>(Event.ANY, "SERVER_STATE");
    public static final EventType<ConnectEvent> USER_CONNECT = new EventType<>(Event.ANY, "USER_CONNECT");
    public static final EventType<ConnectEvent> USER_DISCONNECT = new EventType<>(Event.ANY, "USER_DISCONNECT");
    
    private boolean serverIsOn;
    private String username;
    private String password;
    private String hostname;
    
    //Constructor for Server State EventType
    public ConnectEvent(EventType<? extends Event> eventType, boolean serverIsOn) {
        super(SERVER_STATE);
        this.serverIsOn = serverIsOn;
    }
    
    //Constructor for User Connect EventType
    public ConnectEvent(EventType<? extends Event> eventType, String username, 
            String password, String hostname) {
        super(USER_CONNECT);
        this.username = username;
        this.password = password;
        this.hostname = hostname;
    }
    
    //Constructor for User Disconnect
    public ConnectEvent(EventType<? extends Event> eventType, String username, 
            String hostname) {
        super(USER_DISCONNECT);
        this.username = username;
        this.hostname = hostname;
    }

    public boolean getServerState() {
        return serverIsOn;
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
