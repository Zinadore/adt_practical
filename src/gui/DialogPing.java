/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.DialogEvent;
import EventsListeners.DialogListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.PingType;

/**
 * FXML Controller class
 *
 * @author arxidios
 */
public class DialogPing extends DialogStage implements Initializable {

    @FXML ToggleGroup toggleGroup;
    @FXML RadioButton HOSTNAME;
    @FXML RadioButton IP;
    @FXML TextField textField;
    @FXML Label userLabel;
    @FXML Label errorLabel;
    
    private DialogListener listener;
    private String username;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public DialogPing(String fxmlFile, String title, String username) {
        super(fxmlFile, title);
        this.username = username;
        userLabel.setText("User " + username + " will Ping: ");
        errorLabel.setVisible(false);
        
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(HOSTNAME);
        toggleGroup.getToggles().add(IP);
        textField.requestFocus();
    }
    
    @Override
    public void okPressed() {
        
        if (textField.getText().length() > 1) {
            
            if (toggleGroup.getSelectedToggle() != null) {
                RadioButton button = (RadioButton) toggleGroup.getSelectedToggle();
                DialogEvent newEvent = new DialogEvent(button, username, 
                        textField.getText(), PingType.valueOf(button.getId()));
                getListener().handle(newEvent);
                close();
            } 
            else {
                showError("Host Name or IP must be selected.");
            }
        } 
        else {
            showError("Entry should have more than 1 character.");
        }
    }
    
    
    private void showError(String message) {
        errorLabel.setText("Error! " + message);
        errorLabel.setVisible(true);
    }
}
