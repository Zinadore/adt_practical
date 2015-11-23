/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ListInterface;
import EventsListeners.DialogEvent;
import EventsListeners.DialogListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import model.User;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class DialogRemoveUser extends DialogStage implements Initializable {

    @FXML private TextField usernameField;
    @FXML private ListView<String> listview;
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;
    
    private FXMLLoader fxmlLoader;
    private ObservableList<String> observable = FXCollections.observableArrayList();
    private DialogListener listener;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public DialogRemoveUser(String fxmlFile, String title, ListInterface<User> data) {
        super(fxmlFile, title);
        for (int i = 1; i <= data.size(); i++) {
            observable.add(data.get(i).getUsername());
        }
        listview.getItems().setAll(observable);
        listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    @Override
    public void okPressed() {
        if (listview.getSelectionModel().getSelectedItem() != null) {
            
            DialogEvent event = new DialogEvent(this, 
                    listview.getSelectionModel().getSelectedItem());
            getListener().handle(event);
            close();
        }
    }
}

