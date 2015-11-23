/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.AddRemoveEvent;
import EventsListeners.ConnectEvent;
import EventsListeners.PingEvent;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.IIdentityProvider;
import model.ISerializer;
import model.IServer;
import model.IdentityException;
import model.IdentityProvider;
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
             
        data = new ServerObservableData(server.getState(), 
                server.getConnectedDevices(), server.getAllUsers());
        c = GUIController.getInstance(data);
        c.setResizable(false);
        
        c.setOnCloseRequest( (WindowEvent w) -> {
            try {
                server.stopServer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        
        c.addEventHandler(ConnectEvent.START_SERVER, e -> {
            try {
                server.startServer();
                data = new ServerObservableData(server.getState(), 
                        server.getConnectedDevices(), server.getAllUsers());
                c.updateObservableData(data);
            } catch (IOException ex) {
                c.displayMessage("Error! Save file not found.", MessageType.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                c.displayMessage("Error in deserializing.", MessageType.ERROR_MESSAGE);
            }
            c.displayMessage("Server has started.", MessageType.INFO_MESSAGE);
        });
        
        c.addEventHandler(ConnectEvent.STOP_SERVER, e -> {
            try {
                    server.stopServer();
                    c.displayMessage("Server has stopped.", MessageType.INFO_MESSAGE);
                } catch (IOException ex) {
                    c.displayMessage("Error! Could not save users!", MessageType.ERROR_MESSAGE);
                }
        });
        
        
        c.addEventFilter(ConnectEvent.USER_CONNECT, (ConnectEvent e ) -> {
            
            try {
                server.connectUser(e.getUsername(), e.getPassword(), e.getHostname());
                c.update();
                c.displayMessage("User " + e.getUsername() + " has logged in.", 
                        MessageType.SUCCESS_MESSAGE);
            } catch (IdentityException ex) {
                c.displayMessage(ex.getMessage(), MessageType.WARNING_MESSAGE);
            } catch (ServerException ex) {
                c.displayMessage("Error! " + ex.getMessage(), MessageType.ERROR_MESSAGE);
            }
        });
        
        
        c.addEventHandler(ConnectEvent.USER_DISCONNECT, (ConnectEvent e) -> {
            
            try {
                server.disconnectUser(e.getUsername(), e.getHostname());
                c.update();
                c.displayMessage("Disconnected user " + e.getUsername(),
                        MessageType.INFO_MESSAGE);
                
            } catch (RuntimeException ex) {
                c.displayMessage(ex.getMessage(), MessageType.ERROR_MESSAGE);
            }
            
        });
        
        
        c.addEventHandler(AddRemoveEvent.ADD_USER, (AddRemoveEvent e ) -> {
            
            try {
                server.addUser(e.getUsername(), e.getPassword());
                c.update();
                c.displayMessage("User " + e.getUsername() + " has been created.",
                        MessageType.SUCCESS_MESSAGE);
                
            } catch (IdentityException ex) {
                c.displayMessage("Error! " + ex.getMessage(),
                        MessageType.ERROR_MESSAGE);
            }
        });
        
        
        c.addEventHandler(AddRemoveEvent.REMOVE_USER, e -> {
            
            try {
                server.removeUser(e.getUsername());
                c.update();
                c.displayMessage("Removed user " + e.getUsername() + ".",
                        MessageType.SUCCESS_MESSAGE);
                
            } catch (IdentityException ex) {
                c.displayMessage("Error! No Users detected.", MessageType.ERROR_MESSAGE);
            }
        });
        
        
        c.addEventHandler(PingEvent.PING, e -> {
            
            if ( server.ping(e.getHostOrId(), e.getIdentifier()) ) {
                c.displayMessage("PING Successful! \n(performed by " + e.getUsername() + 
                        " on " + e.getHostOrId() + ").", MessageType.SUCCESS_MESSAGE);
            } else {
                c.displayMessage("PING Failed. \n(performed by " + e.getUsername() + 
                        " on " + e.getHostOrId() + ").", MessageType.WARNING_MESSAGE);
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
