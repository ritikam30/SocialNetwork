////////////////////////////////////////////////////////////////
// Title: ATeam Project                                       // 
// Author: TODO                                               //
// Email: TODO@wisc.edu                                       //
// Lecture: TODO                                              //
// Description: creates simple GUI program using JavaFX       //                                                 
// Files: //TODO                                              //   
////////////////////////////////////////////////////////////////
package application;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * creates GUI interface that can be interacted with, but produces no results
 * 
 * @author TODO
 */
public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 600; // width of pop up FIXME
    private static final int WINDOW_HEIGHT = 500; // height of pop up FIXME
    private static final String APP_TITLE = "ATeam Project"; // title to be displayed on window bar //FIXME

    /**
     * sets up elements to be displayed in GUI
     * 
     * @param primaryStage - stage that GUI elements are set on
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // save args example
        args = this.getParameters().getRaw();

        // Main layout is Border Pane example (top,left,center,right,bottom)
        BorderPane root = new BorderPane();

        // creates new scene
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // TODO
        
     // Making an "Undo" Button in the left panel
        Button button1 = new Button("Undo");
        root.setLeft(button1);

     // Making a "Redo" Button in the Top panel
        Button button2 = new Button("Redo");
        root.setTop(button2);
        
       //Making a "Help" Button in the right panel
        Button button3 = new Button ("Help");
        root.setRight(button3);
        
        //Making an "Exit" Button in the center panel
        Button button4 = new Button ("Exit");
       
        
        //Making an "Search" button 
        Button button5 = new Button ("Search");
            
        //Creating a text field for the search bar
        TextField t1 = new TextField();
        
        //Creating a horizontal box for the search bar
        HBox h1 = new HBox();
        h1.getChildren().addAll(t1, button5);
        root.setBottom(h1);
        
        //Creating another hbox for undo and redo
        HBox h2 = new HBox();
        h2.getChildren().addAll(button1, button2);
        root.setTop(h2);
        
        //Creating a Vbox
        VBox v1 = new VBox();
        v1.getChildren().addAll(h2, h1);
        root.setTop(v1);
        
        //creating another hbox for exit and help
        HBox h3 = new HBox();
        h3.getChildren().addAll(button3, button4);
        root.setRight(h3);
        
        
        
        // Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true); // makes window fullscreen, remove if needed FIXME
        primaryStage.show();

    }

    /**
     * main method to launch GUI
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
