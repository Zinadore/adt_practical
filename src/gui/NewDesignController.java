/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ArrayBasedList;
import utility.BooleanHolder;
import EventsListeners.DialogEvent;
import EventsListeners.ConnectEvent;
import EventsListeners.AddRemoveEvent;
import EventsListeners.PingEvent;
import EventsListeners.UserNodeEvent;
import EventsListeners.UserNodeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import model.INetworkDevice;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class NewDesignController extends ControllerAbstract implements
        Initializable, UserNodeListener {

    @FXML Button serverBtn;
    @FXML Button showComputersBtn;
    @FXML Button connectUserBtn;
    @FXML Button createUserBtn;
    @FXML Button deleteUserBtn;
    private Button[] buttonArray;
    @FXML Label serverStatusLabel;
    @FXML TextArea log;
    @FXML private FlowPane userLayout;
    private CustomUserNode node;
    
    private ServerObservableData data;
    private ArrayBasedList<INetworkDevice> observableList;
    private BooleanHolder serverIsOn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonArray = new Button[4];
        buttonArray[0] = showComputersBtn;
        buttonArray[1] = connectUserBtn;
        buttonArray[2] = createUserBtn;
        buttonArray[3] = deleteUserBtn;
        disableButtons(true);
    }    

    @Override
    public void setData() throws UnsupportedOperationException {
        data = (ServerObservableData) observableData;
        observableList = data.getList();
        serverIsOn = data.getServerIsOn();
        update();
    }
    
    public void update() {
        userLayout.getChildren().clear();
        for (int i = 1; i < observableList.size(); i++) {
            node = new CustomUserNode(observableList.get(i));
            node.addUserNodeListener(this);
            userLayout.getChildren().add(node);
        }
    }

    @Override
    public void displayMessage(String message) {
        log.appendText(message + "\n" );
    }
    
    private void disableButtons(boolean b) {
        for (Button button : buttonArray) {
            button.setDisable(b);
        }
    }
    
    //Button Methods
    public void serverBtnPressed() {
        
        if (!serverIsOn.get()) {
            serverBtn.setText("Stop Server");
            serverStatusLabel.setText("Server On");
        } else {
            serverBtn.setText("Start Server");
            serverStatusLabel.setText("Server Off");
            userLayout.getChildren().clear();
        }
        
        disableButtons(serverIsOn.get());
        ConnectEvent newEvent = new ConnectEvent(ConnectEvent.SERVER_STATE,
                !serverIsOn.get());
        Event.fireEvent(userLayout, newEvent);
    }
    
    
    public void connectUser() {
        
        DialogConnectUser dialog = new DialogConnectUser();
        dialog.addDialogListener((DialogEvent e) -> {
            ConnectEvent newEvent = new ConnectEvent(ConnectEvent.USER_CONNECT, 
                e.getUsername(), e.getPassword(), 
                e.getHostname());
            Event.fireEvent(userLayout, newEvent);
        });
        dialog.showAndWait();
    }
    
    
    public void disconnectUser(UserNodeEvent e) {
        
        ConnectEvent newEvent = new ConnectEvent(ConnectEvent.USER_CONNECT, 
                    (String) e.getSource(), e.getHostname());
        Event.fireEvent(userLayout, newEvent);
    }
    
    
    public void addNewUser() {
        
        DialogAddUser dialog = new DialogAddUser();
        dialog.addDialogListener((DialogEvent e) -> {
            AddRemoveEvent newEvent = new AddRemoveEvent(AddRemoveEvent.ADD_USER,
                    e.getUsername(), e.getPassword());
            Event.fireEvent(userLayout, newEvent);
        });
        dialog.showAndWait();
    }
    
    
    public void removeUser() {
        
        DialogRemoveUser dialog = new DialogRemoveUser(observableList);
        dialog.addDialogListener((DialogEvent e) -> {
            AddRemoveEvent newEvent = new AddRemoveEvent(AddRemoveEvent.REMOVE_USER, 
                    e.getUsername());
            Event.fireEvent(userLayout, newEvent);
        });
        dialog.showAndWait();
    }
    
    
    public void ping(UserNodeEvent userNodeEvent) {
        
        DialogPing dialog = new DialogPing((String) userNodeEvent.getSource());
        dialog.addDialogListener( (DialogEvent dialogEvent) -> {
            PingEvent pingEvent = new PingEvent(PingEvent.PING, 
                    dialogEvent.getUsername(), dialogEvent.getHostOrId(), 
                    dialogEvent.getIdentifier());
            Event.fireEvent(userLayout, pingEvent);
        });
        dialog.showAndWait();
    }
    
    
    public void showConnectedComputers() {
        
        DialogShowComputers dialog = new DialogShowComputers(observableList);
        dialog.showAndWait();
    }
    
    
    @Override
    public void handle(UserNodeEvent e) {
        
        if (e.getAction().equals("Disconnect")) {
            disconnectUser(e);
        } 
        else if (e.getAction().equals("Ping")) {
            ping(e);
        }
    }
}
