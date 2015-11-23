/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ListInterface;
import static java.util.Collections.list;
import utility.BooleanHolder;
import model.INetworkDevice;
import model.User;

/**
 *
 * @author harry bournis
 */
public class ServerObservableData implements IObservableData{
    
    private BooleanHolder serverIsOn;
    private ListInterface<INetworkDevice> connectedList;
    private ListInterface<User> userList;
    
    public ServerObservableData(BooleanHolder b, 
            ListInterface<INetworkDevice> l, ListInterface<User> userList) {
        serverIsOn = b;
        connectedList = l;
        this.userList = userList;
    }

    public BooleanHolder getServerIsOn() {
        return serverIsOn;
    }

    public ListInterface<INetworkDevice> getConnectedList() {
        return connectedList;
    }
    
    public ListInterface<User> getUserList() {
        return userList;
    }
}
