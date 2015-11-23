/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ListInterface;
import utility.BooleanHolder;
import EventsListeners.DialogEvent;
import EventsListeners.ConnectEvent;
import EventsListeners.AddRemoveEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.INetworkDevice;
import model.User;

/**
 * FXML Controller class
 *
 * @author harry bournis
 */
public class NewDesignController extends ControllerAbstract implements
        Initializable {

    @FXML Button serverBtn;
    @FXML ImageView ledPosition;
    @FXML Button showComputersBtn;
    @FXML Button connectUserBtn;
    @FXML Button createUserBtn;
    @FXML Button deleteUserBtn;
    @FXML Label serverStatusLabel;
    @FXML ScrollPane scrollPane;
    @FXML TextFlow log;
    @FXML private FlowPane userLayout;
    private CustomUserNode node;
    private Button[] buttonArray;
    
    private ServerObservableData data;
    private ListInterface<INetworkDevice> deviceList;
    private ListInterface<User> userList;
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
        deviceList = data.getConnectedList();
        userList = data.getUserList();
        serverIsOn = data.getServerIsOn();
    }
    
    public void update() {
        userLayout.getChildren().clear();
        for (int i = 1; i <= deviceList.size(); i++) {
            node = new CustomUserNode(deviceList.get(i));
            userLayout.getChildren().add(node);
        }
    }

    
    //Button Methods
    public void serverBtnPressed() {
        
        if (!serverIsOn.get()) {
            serverBtn.setText("Stop Server");
            serverStatusLabel.setText("Server On");
            Image icon = new Image("css/green_light.png", 20, 20, true, true);
            ledPosition.setImage(icon);
            disableButtons(false);
            
            ConnectEvent startEvent = new ConnectEvent(ConnectEvent.START_SERVER);
            Event.fireEvent(serverBtn, startEvent);
            
        } else {
            serverBtn.setText("Start Server");
            serverStatusLabel.setText("Server Off");
            Image icon = new Image("css/red_light.png", 20, 20, true, true);
            ledPosition.setImage(icon);
            userLayout.getChildren().clear();
            disableButtons(true);
            
            ConnectEvent stopEvent = new ConnectEvent(ConnectEvent.STOP_SERVER);
            Event.fireEvent(serverBtn, stopEvent);
        }
    }
    
    
    public void connectUser() {
        
        DialogStage dialog = new DialogConnectUser("DialogConnectUser.fxml", "Connect User");
        dialog.addDialogListener((DialogEvent e) -> {
            ConnectEvent newEvent = new ConnectEvent(ConnectEvent.USER_CONNECT, 
                e.getUsername(), e.getPassword(), 
                e.getHostname());
            Event.fireEvent(userLayout, newEvent);
        });
        dialog.showAndWait();
    }
    
    
    public void addUser() {
        
        DialogStage dialog = new DialogAddUser("DialogAddUser.fxml", "Create New User");
        dialog.addDialogListener((DialogEvent e) -> {
            AddRemoveEvent newEvent = new AddRemoveEvent(AddRemoveEvent.ADD_USER,
                    e.getUsername(), e.getPassword());
            Event.fireEvent(userLayout, newEvent);
        });
        dialog.showAndWait();
    }
    
    
    public void removeUser() {
        
        DialogStage dialog = new DialogRemoveUser("DialogRemoveUser.fxml", 
                "Delete User", userList);
        dialog.addDialogListener((DialogEvent e) -> {
            AddRemoveEvent newEvent = new AddRemoveEvent(AddRemoveEvent.REMOVE_USER, 
                    e.getUsername());
            Event.fireEvent(userLayout, newEvent);
        });
        dialog.showAndWait();
    }
    
    public void showConnectedComputers() {
        
        DialogStage dialog = new DialogShowComputers("DialogShowComputers.fxml", 
                "Connected Computers", deviceList);
        dialog.showAndWait();
    }
    
    
    @Override
    public void displayMessage(String message, MessageType type) {
        Text text = new Text(message + "\n\n");
        text.setFill(type.value());
        log.getChildren().add(text);
        scrollPane.setVvalue(1.0);
    }
    
    private void disableButtons(boolean b) {
        for (Button button : buttonArray) {
            button.setDisable(b);
        }
    }
}
