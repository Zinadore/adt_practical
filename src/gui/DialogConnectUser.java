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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class DialogConnectUser extends DialogStage implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField hostnameField;
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;
    @FXML private Label errorLabel;
    
    private FXMLLoader fxmlLoader;
    private DialogListener listener;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public DialogConnectUser(String fxmlFile, String title) {
        super(fxmlFile, title);
        errorLabel.setVisible(false);
        usernameField.requestFocus();
    }
    
    public void okPressed() {
        if (usernameField.getText().length() > 0 
                && passwordField.getText().length() > 0
                && hostnameField.getText().length() > 0) {
            
            DialogEvent event = new DialogEvent(this, usernameField.getText(), 
                    passwordField.getText(), hostnameField.getText());
            getListener().handle(event);
            close();
        }
        else {
            showError("Fields should not be empty.");
        }
    }
    
    private void showError(String message) {
        errorLabel.setText("Error! " + message);
        errorLabel.setVisible(true);
    }
}

