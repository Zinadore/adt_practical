/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsListeners;

import javafx.event.Event;
import javafx.event.EventType;
import model.PingType;

/**
 *
 * @author harry bournis
 */
public class PingEvent extends Event {

    public static final EventType<PingEvent> PING = new EventType<>(Event.ANY, "PING");
    
    private String username;
    private String hostOrId;
    private PingType identifier;
    
    public PingEvent(EventType<? extends Event> eventType, String username, 
            String hostOrId, PingType identifier) {
        super(PING);
        this.username = username;
        this.hostOrId = hostOrId;
        this.identifier = identifier;
    }

    public String getUsername() {
        return username;
    }

    public String getHostOrId() {
        return hostOrId;
    }

    public PingType getIdentifier() {
        return identifier;
    }
}
