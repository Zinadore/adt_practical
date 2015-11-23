/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.paint.Color;

/**
 *
 * @author arxidios
 */
public enum MessageType {
    WARNING_MESSAGE(Color.DARKORANGE),
    ERROR_MESSAGE(Color.RED),
    SUCCESS_MESSAGE(Color.GREEN),
    INFO_MESSAGE(Color.BLUE)
    ;
    
    private Color color;
    
    private MessageType(Color c) {
        color = c;
    }

    public Color value() {
        return color;
    }

    
}
