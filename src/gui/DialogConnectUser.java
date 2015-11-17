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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class DialogConnectUser extends Stage implements Initializable {

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
    
    public DialogConnectUser() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogConnectUser.fxml"));
        fxmlLoader.setController(this);

        try
        {
            setScene(new Scene((Parent) fxmlLoader.load()));
            setTitle("Connect User");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        errorLabel.setVisible(false);
        usernameField.requestFocus();
    }
    
    public void okPressed() {
        if (usernameField.getText().length() > 0 
                && passwordField.getText().length() > 0
                && hostnameField.getText().length() > 0) {
            
            DialogEvent event = new DialogEvent(this, usernameField.getText(), 
                    passwordField.getText(), hostnameField.getText());
            listener.handle(event);
            close();
        }
        else {
            showError("Fields should not be empty.");
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

