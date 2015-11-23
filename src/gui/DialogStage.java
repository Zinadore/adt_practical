/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.DialogListener;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author harry bournis
 */
public class DialogStage extends Stage {
    
    protected DialogListener listener;
    
    public DialogStage(String fxmlFile, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        fxmlLoader.setController(this);

        try
        {
            setScene(new Scene((Parent) fxmlLoader.load()));
            setTitle(title);
            initModality(Modality.APPLICATION_MODAL);
            setResizable(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                okPressed();
            } else if(e.getCode().equals(KeyCode.ESCAPE)) {
                cancelPressed();
            }
        });
    }
    
    public void okPressed() {
        System.out.println("OK Button Pressed");
    }
    
    public void cancelPressed() {
        close();
    }
    
    public void addDialogListener(DialogListener dialogListener) {
        listener = dialogListener;
    }
    
    protected DialogListener getListener() {
        return listener;
    }
}
