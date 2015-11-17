/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ArrayBasedList;
import utility.BooleanHolder;
import EventsListeners.ConnectEvent;
import EventsListeners.AddRemoveEvent;
import EventsListeners.PingEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.INetworkDevice;
import model.Laptop;
import model.User;

/**
 *
 * @author harry bournis
 */
public class MainTest extends Application {
    
    private ArrayBasedList<INetworkDevice> list = new ArrayBasedList<INetworkDevice>();
    private BooleanHolder serverOn = new BooleanHolder(false);
    
    @Override
    public void start(Stage primaryStage) {  
        //Sample data
        User user1 = new User("ilithios", "1234");
        INetworkDevice device1 = new Laptop("skata", "389298298");
        device1.changeUser(user1);
        User user2 = new User("tyrenios", "1234");
        INetworkDevice device2 = new Laptop("emetos", "23334455");
        device2.changeUser(user2);
        list.append(device1);
        list.append(device2);
        //
        
        IObservableData data = new ServerObservableData(serverOn, list);
        GUIController c = GUIController.getInstance(data);
        c.setResizable(false);
        
        c.setOnCloseRequest( (WindowEvent w) -> {
            System.out.println("Save Data and exit");
        });
        
        
        c.addEventHandler(ConnectEvent.SERVER_STATE, (ConnectEvent e ) -> {
            
            serverOn.set(e.getServerState());
            System.out.println("server is " + serverOn.get());
        });
        
        c.addEventHandler(ConnectEvent.USER_CONNECT, (ConnectEvent e ) -> {
            
            System.out.println("user connect");
            User user3 = new User(e.getUsername(), e.getPassword());
            INetworkDevice device3 = new Laptop(e.getHostname(), "55555");
            device3.changeUser(user3);
            list.append(device3);

            c.update(); 
        });
        
        c.addEventHandler(ConnectEvent.USER_DISCONNECT, (ConnectEvent e) -> {
            
//            System.out.println("user disconnect");
//            INetworkDevice remove = null;
//            for (INetworkDevice d : list) { 
//                if (d.getUserName().equals(e.getUsername())) {
//                   remove = d;
//                }
//            }
//            if (remove != null) {
//               list.(remove); 
//               
//               c.update();
//            }
        });
        
        c.addEventHandler(AddRemoveEvent.ADD_USER, (AddRemoveEvent e ) -> {
            
            System.out.println("add new user");
            User newUser = new User(e.getUsername(), e.getPassword());
            INetworkDevice devicee = new Laptop(e.getHostname(), "88888");
            devicee.changeUser(newUser);
            list.append(devicee);
            
            c.update();
        });
        
        c.addEventHandler(AddRemoveEvent.REMOVE_USER, (AddRemoveEvent e ) -> {
            
//            System.out.println("delete user");
//            INetworkDevice remove = null;
//            for (INetworkDevice d : list) { 
//                if (d.getUserName().equals(e.getUsername())) {
//                   remove = d;
//                }
//            }
//            if (remove != null) {
//               list.remove(remove); 
//               
//               c.update();
//            }
        });
        
        c.addEventHandler(PingEvent.PING, (PingEvent e) -> {
            //System.out.println("ping received. identifier :" +e.getIdentifier());
            
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
