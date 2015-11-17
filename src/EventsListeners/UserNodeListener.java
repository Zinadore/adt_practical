/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsListeners;

import java.util.EventListener;

/**
 *
 * @author harry bournis
 */
public interface UserNodeListener extends EventListener {
    public void handle(UserNodeEvent e);
}
