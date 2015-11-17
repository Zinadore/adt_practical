/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.ButtonPressedListener;
import EventsListeners.ButtonPressedEvent;

/**
 *
 * @author harry bournis
 */
public abstract class ControllerAbstract {
    
    private ButtonPressedListener buttonPressedListener;
    protected IObservableData observableData;
    
    public abstract void setData() throws UnsupportedOperationException;
    
    public abstract void update();
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void addButtonPressedListener(ButtonPressedListener listener) {
        buttonPressedListener = listener;
    }
    
    public void setObservableData(IObservableData observableData) {
        this.observableData = observableData;
    }
    
    public void loadScreen(String screenName) {
        ButtonPressedEvent buttonPressedEvent = new ButtonPressedEvent(this, screenName);
        
        if (buttonPressedListener != null) {
            buttonPressedListener.loadScreen(buttonPressedEvent);
        } else {
            System.out.println("listener is null");
        }
    }

}
