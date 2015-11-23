/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ListInterface;
import EventsListeners.DialogListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.INetworkDevice;

/**
 * FXML Controller class
 *
 * @author arxidios
 */
public class DialogShowComputers extends DialogStage implements Initializable {

    @FXML Button closeBtn;
    @FXML TableView<INetworkDevice> tableView;
    @FXML TableColumn<INetworkDevice, String> ipColumn;
    @FXML TableColumn<INetworkDevice, String> hostNameColumn;
    @FXML TableColumn<INetworkDevice, String> userNameColumn;
    
    private DialogListener listener;
    ObservableList<INetworkDevice> observableList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public DialogShowComputers(String fxmlFile, String title, ListInterface<INetworkDevice> list) {
        super(fxmlFile, title);
        for (int i = 1; i <= list.size(); i++) {
            observableList.add(list.get(i));
        }
        
        ipColumn.setCellValueFactory( new 
                PropertyValueFactory<INetworkDevice, String>("ip") );
        hostNameColumn.setCellValueFactory( new
                PropertyValueFactory<INetworkDevice, String>("hostname") );
        userNameColumn.setCellValueFactory( new 
                PropertyValueFactory<INetworkDevice, String>("userName") );
        
        tableView.setItems(observableList);
    }
}
