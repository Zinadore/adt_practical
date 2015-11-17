/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Collections.ArrayBasedList;
import EventsListeners.DialogListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.INetworkDevice;

/**
 * FXML Controller class
 *
 * @author arxidios
 */
public class DialogShowComputers extends Stage implements Initializable {

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
    
    public DialogShowComputers(ArrayBasedList<INetworkDevice> list) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("DialogShowComputers.fxml"));
        fxmlLoader.setController(this);

        try
        {
            setScene(new Scene((Parent) fxmlLoader.load()));
            setTitle("Connected Computers");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        for (int i = 1; i < list.size(); i++) {
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
    
    public void closeBtnPressed() {
        close();
    }

    public void addDialogListener(DialogListener dialogListener) {
        listener = dialogListener;
    }
}
