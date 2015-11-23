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
import javafx.scene.paint.Color;
import utility.PasswordChecker;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class DialogAddUser extends DialogStage implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
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
    
    public DialogAddUser(String fxmlFile, String title) {
        super(fxmlFile, title);
        errorLabel.setVisible(false);
        usernameField.requestFocus();
    }
    
    @Override
    public void okPressed() {
        if (usernameField.getText().length() > 1 
                && passwordField.getText().length() > 1) {
            
            DialogEvent event = new DialogEvent(this, usernameField.getText(), 
                    passwordField.getText());
            getListener().handle(event);
            close();
        }
        else {
            showError("Fields can not be less than one character.");
            errorLabel.setTextFill(Color.web("#c91414"));
        }
    }
    
    private void showError(String message) {
        errorLabel.setText("Error! " + message);
        errorLabel.setVisible(true);
    }
    
    public void checkPassword() {
        if (passwordField.getText().length() > 0) {
            String message = PasswordChecker.passwordStrength(passwordField.getText());
            errorLabel.setText(message);
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
        }
    }
}

