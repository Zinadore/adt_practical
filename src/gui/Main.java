/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utility.BooleanHolder;
import EventsListeners.ConnectEvent;
import EventsListeners.AddRemoveEvent;
import EventsListeners.PingEvent;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author harry bournis
 */
public class Main extends Application {
    
    private IObservableData data;
    private GUIController c;
    private BooleanHolder serverIsOn = new BooleanHolder(false);
    
    @Override
    public void start(Stage primaryStage) {  
        
//        1) create a new ServerObservableData(BooleanHolder b, INetworkDeviceList l) object
//        2) pass it as a parameter in the GUIController.getInstance() method
//        3) Set resizable to false to avoid user stupidity
//        4) handle each of the different events bellow
        
        //data = new ServerObservableData(serverIsOn, );
        c = GUIController.getInstance(data);
        c.setResizable(false);
        
        //Save user data here before exiting
        c.setOnCloseRequest( (WindowEvent w) -> {
            System.out.println("Save Data and exit");
        });
        
        
        c.addEventHandler(ConnectEvent.SERVER_STATE, (ConnectEvent e ) -> {
            //use e.getServerState to update the server variable
        });
        
        c.addEventHandler(ConnectEvent.USER_CONNECT, (ConnectEvent e ) -> {
            //Use e.getUsername, e.getPassword, e.getHostname to connect a user
            //update gui at the end
            c.update(); 
        });
        
        c.addEventHandler(ConnectEvent.USER_DISCONNECT, (ConnectEvent e) -> {
            //use e.getUsername to disconnect user.
            //update gui at the end
            c.update();
        });
        
        c.addEventHandler(AddRemoveEvent.ADD_USER, (AddRemoveEvent e ) -> {
            //use e.getUsername, e.getPassword to add a new user
            //update gui at the end
            c.update();
        });
        
        c.addEventHandler(AddRemoveEvent.REMOVE_USER, (AddRemoveEvent e ) -> {
            //use e.getUsername to delete a user.
            //update gui at the end
            c.update();
        });
        
        c.addEventHandler(PingEvent.PING, (PingEvent e) -> {
            //user e.getUsername to print the user that issued the ping,
            // and e.getHostOrId and e.getIdentifier for the ping() method
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
