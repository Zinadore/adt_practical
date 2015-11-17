/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ArrayBasedList;
import utility.BooleanHolder;
import model.INetworkDevice;

/**
 *
 * @author harry bournis
 */
public class ServerObservableData implements IObservableData{
    
    private BooleanHolder serverIsOn;
    private ArrayBasedList<INetworkDevice> list;
    
    public ServerObservableData(BooleanHolder b, ArrayBasedList<INetworkDevice> l) {
        serverIsOn = b;
        list = l;
    }

    public BooleanHolder getServerIsOn() {
        return serverIsOn;
    }

    public ArrayBasedList<INetworkDevice> getList() {
        return list;
    }
    
}
