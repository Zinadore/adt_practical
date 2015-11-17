/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EventsListeners.ButtonPressedEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author harry bournis
 */
public class GUIController extends Stage {
    
    private final String START_SCREEN = "NewDesign.fxml";
    
    private static GUIController instance = null;
    private Group group = new Group();
    private BorderPane pane = new BorderPane();
    private Scene scene = new Scene(group);
    private FXMLLoader loader = new FXMLLoader(getClass().getResource(START_SCREEN));
    private ControllerAbstract currentController;
    private DataDisplayManager dataDisplayManager = new DataDisplayManager();
    private String previousScreen;
    
    public static GUIController getInstance(IObservableData data) {
        if (instance == null) {
            instance = new GUIController(data);
        }
        return instance;
    }
    
    private GUIController(IObservableData data) {
        dataDisplayManager.add(START_SCREEN, data);
        loadScreen(START_SCREEN);
        setScene(scene);
        setTitle("Server");
        show();
    }
    
    public final void loadScreen(String screenName){
        try {
            URL newLocation = getClass().getResource(screenName);
            previousScreen = new File(loader.getLocation().toString()).getName();
            group = null;
            loader.setRoot(null);
            loader.setController(null);
            
            loader.setLocation(newLocation);
            BorderPane pane = (BorderPane) loader.load();
            currentController = loader.getController();
            currentController.setObservableData(
                    dataDisplayManager.getObservableData(screenName));
            
            try {
                currentController.setData();
            } catch (UnsupportedOperationException e) {
                System.out.println("no data to be set for: " + screenName);
            }
            
            scene.setRoot(pane);
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Add the change screen listener to the newly assigned controller
        currentController.addButtonPressedListener( (ButtonPressedEvent event) -> {
            
            if (!"Back".equals(event.getScreenName())) {
                loadScreen(event.getScreenName());
            } else {
                back();
            }
        } );
    }
    
    private void back(){
        loadScreen(previousScreen);
    }
    
    public void update() {
        currentController.update();
    }
    
    public ControllerAbstract getController() {
        return currentController;
    }

    public void addObservableData(String screenName, 
            IObservableData observableData){
        dataDisplayManager.add(screenName, observableData);
    }
    
    public void displayMessage(String message) {
        currentController.displayMessage(message);
    }
}

class DataDisplayManager {
    
    private HashMap<String, IObservableData> map;
    
    public DataDisplayManager() {
        map = new HashMap<>();
    }
    
    public IObservableData getObservableData(String key) {
        return map.get(key);
    }
    
    public void add(String screen, IObservableData observableDataObject) {
        map.put(screen, observableDataObject);
    }
}