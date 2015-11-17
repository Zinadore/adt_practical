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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utility.PasswordChecker;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class DialogAddUser extends Stage implements Initializable {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
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
    
    public DialogAddUser() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogAddUser.fxml"));
        fxmlLoader.setController(this);

        try
        {
            setScene(new Scene((Parent) fxmlLoader.load()));
            setTitle("Create New User");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        errorLabel.setVisible(false);
    }
    
    public void okPressed() {
        if (usernameField.getText().length() > 0 
                && passwordField.getText().length() > 0) {
            
            DialogEvent event = new DialogEvent(this, usernameField.getText(), 
                    passwordField.getText());
            listener.handle(event);
            close();
        }
        else {
            showError("Fields can not be empty.");
            errorLabel.setTextFill(Color.RED);
        }
    }
    
    public void cancelPressed() {
        close();
    }

    void addDialogListener(DialogListener dialogListener) {
        listener = dialogListener;
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

