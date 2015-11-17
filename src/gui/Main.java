/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ListException;
import EventsListeners.AddRemoveEvent;
import EventsListeners.ConnectEvent;
import EventsListeners.PingEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.IIdentityProvider;
import model.ISerializer;
import model.IServer;
import model.IdentityException;
import model.IdentityProvider;
import model.PingType;
import model.Serializer;
import model.Server;
import model.ServerException;
import model.User;



/**
 *
 * @author harry bournis
 */
public class Main extends Application {
    
    private IObservableData data;
    private GUIController c;
    
    @Override
    public void start(Stage primaryStage) {  
        
        ISerializer<User> serializer = new Serializer<User>();
        IIdentityProvider identityProvider = new IdentityProvider(serializer);
        IServer server = new Server(identityProvider);
             
        data = new ServerObservableData(server.getState(), server.getConnectedDevices());
        c = GUIController.getInstance(data);
        c.setResizable(false);
        
        c.setOnCloseRequest( (WindowEvent w) -> {
            try {
                server.stopServer();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        c.addEventHandler(ConnectEvent.SERVER_STATE, (ConnectEvent e ) -> {
            
            if (e.getServerState()) {
                try {
                    server.startServer();
                    data = new ServerObservableData(server.getState(), server.getConnectedDevices());
                    c.updateObservableData(data);
                } catch (IOException ex) {
                    c.displayMessage("Error! Save file not found.");
                } catch (ClassNotFoundException ex) {
                    c.displayMessage("Error in deserializing.");
                }
                c.displayMessage("Server has started.");
            } else {
                try {
                    server.stopServer();
                    c.displayMessage("Server has stopped.");
                } catch (IOException ex) {
                    c.displayMessage("Error! Could not save users!");
                }
            }
        });
        
        c.addEventHandler(ConnectEvent.USER_CONNECT, (ConnectEvent e ) -> {
            
            try {
                server.connectUser(e.getUsername(), e.getPassword(), e.getHostname());
                c.update();
                c.displayMessage("User " + e.getUsername() + " has logged in.");
            } catch (IdentityException ex) {
                c.displayMessage(ex.getMessage());
            } catch (ServerException ex) {
                c.displayMessage("Error! " + ex.getMessage());
            }
        });
        
        c.addEventHandler(ConnectEvent.USER_DISCONNECT, (ConnectEvent e) -> {
            
            try {
                server.disconnectUser(e.getUsername(), e.getHostname());
                c.update();
                c.displayMessage("Disconnected user " + e.getUsername());
                
            } catch (RuntimeException ex) {
                c.displayMessage(ex.getMessage());
            }
            
        });
        
        c.addEventHandler(AddRemoveEvent.ADD_USER, (AddRemoveEvent e ) -> {
            
            try {
                server.addUser(e.getUsername(), e.getPassword());
                c.update();
                c.displayMessage("User " + e.getUsername() + " has been created.");
                
            } catch (IdentityException ex) {
                c.displayMessage("Error! " + ex.getMessage());
            }
        });
        
        c.addEventHandler(AddRemoveEvent.REMOVE_USER, e -> {
            
            try {
                server.removeUser(e.getUsername());
                c.update();
                c.displayMessage("Removed user " + e.getUsername());
                
            } catch (IdentityException ex) {
                c.displayMessage("Error! No Users detected.");
            }
        });
        
        c.addEventHandler(PingEvent.PING, e -> {
            
            if ( server.ping(e.getHostOrId(), e.getIdentifier()) ) {
                c.displayMessage("PING Successful! \n(performed by " + e.getUsername() + 
                        " on " + e.getHostOrId() + ").");
            } else {
                c.displayMessage("PING Failed. \n(performed by " + e.getUsername() + 
                        " on " + e.getHostOrId() + ").");
            }
        });

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
