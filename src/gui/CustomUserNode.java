/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.UserNodeEvent;
import EventsListeners.UserNodeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.INetworkDevice;

/**
 *
 * @author harry bournis
 */
public class CustomUserNode extends StackPane {

    private Label usernameLabel;
    private Button disconnectBtn;
    private Button pingBtn;
    private VBox vbox;
    private HBox hbox;
    private UserNodeListener userNodeListener;
    private String username;
    private String hostname;
    
    public CustomUserNode(INetworkDevice networkDevice) {
        username = networkDevice.getUserName();
        hostname = networkDevice.getHostname();
        
        vbox = new VBox();
        hbox = new HBox();
        hbox.setHgrow(this, Priority.ALWAYS);
        usernameLabel = new Label(networkDevice.getUserName());
        disconnectBtn = new Button("Disconnect");
        pingBtn = new Button("Ping...");
        
        hbox.getStyleClass().add("hboxx");
        getStyleClass().add("customUserNode");
        vbox.getStyleClass().add("vboxx");
        
        Image image = new Image("css/User-blue-icon.png", 150, 200, true, false);
        ImageView view = new ImageView(image);
        view.setEffect(new GaussianBlur(5.0));
        
        hbox.getChildren().add(usernameLabel);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(pingBtn);
        vbox.getChildren().add(disconnectBtn);
        
        getChildren().add(view);
        getChildren().add(vbox);
        
        setListeners();
    }
    
    private void setListeners() {
        
        disconnectBtn.setOnAction((ActionEvent event) -> {
            UserNodeEvent newEvent = new UserNodeEvent(username,
                    hostname ,"Disconnect");
            userNodeListener.handle(newEvent);
        });
        
        pingBtn.setOnAction((ActionEvent event) -> {
            UserNodeEvent newEvent = new UserNodeEvent(username, "Ping"); 
            userNodeListener.handle(newEvent);
       });
    }
    
    public String getUsername() {
        return usernameLabel.getText();
    }

    void addUserNodeListener(UserNodeListener l) {
        userNodeListener = l;
    }
}