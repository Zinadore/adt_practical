/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.DialogEvent;
import EventsListeners.DialogListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.PingType;

/**
 * FXML Controller class
 *
 * @author arxidios
 */
public class DialogPing extends Stage implements Initializable {

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
    
    public DialogPing(String username) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogPing.fxml"));
        fxmlLoader.setController(this);

        try
        {
            setScene(new Scene((Parent) fxmlLoader.load()));
            setTitle("Ping Host Name or IP");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        this.username = username;
        userLabel.setText("User " + username + " will Ping: ");
        errorLabel.setVisible(false);
        
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(HOSTNAME);
        toggleGroup.getToggles().add(IP);
    }
    
    public void okPressed() {
        
        if (textField.getText().length() > 1) {
            
            if (toggleGroup.getSelectedToggle() != null) {
                RadioButton button = (RadioButton) toggleGroup.getSelectedToggle();
                DialogEvent newEvent = new DialogEvent(button, username, 
                        textField.getText(), PingType.valueOf(button.getId()));
                listener.handle(newEvent);
            } 
            else {
                showError("Host Name or IP must be selected.");
            }
        } 
        else {
            showError("Entry should have more than 1 character.");
        }
    }
    
    public void cancelPressed() {
        close();
    }
    
    public void addDialogListener(DialogListener dialogListener) {
        listener = dialogListener;
    }
    
    private void showError(String message) {
        errorLabel.setText("Error! " + message);
        errorLabel.setVisible(true);
    }
}
