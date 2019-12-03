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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * creates GUI interface that can be interacted with, but produces no results
 * 
 * @author TODO
 */
public class Main_J extends Application {
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

     // Create a vertical box with Hello labels for each args
     		VBox vbox = new VBox();
     		
     		// Creates a canvas that can draw shapes and text
     		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
     		GraphicsContext gc = canvas.getGraphicsContext2D();
     		
     		//TODO: Note: the process of drawing nodes and lines between them will be easy to make into a method, just 
     		//not done here
     		
     		//define position constants
     		double radius = 50;
     		double node1x = 300;
     		double node1y = 250;
     		double node2x = 350;
     		double node2y = 100;
     		double node3x = 100;
     		double node3y = 450;
     		double node4x = 500;
     		double node4y = 400;
     		
     	// Draw a line
     		// Lines use the stroke color
     		//draw lines from center to center
     		gc.setStroke(Color.BLUE);
     		gc.setLineWidth(2);
     		gc.strokeLine(node1x, node1y, node2x, node2y);
     		gc.strokeLine(node1x, node1y, node3x, node3y);
     		gc.strokeLine(node1x, node1y, node4x, node4y);
     		gc.strokeLine(node2x, node2y, node4x, node4y);
     		
     		
     		
     		// The circles draw from the top left, so to center them, subtract the radius from each coordinate
     	    // Draw a few nodes
     		gc.setFill(Color.BLACK);
     		gc.fillOval(node1x-radius, node1y-radius, 2*radius, 2*radius);
     		gc.setFill(Color.RED);
     		gc.fillOval(node2x-radius, node2y-radius, 2*radius, 2*radius);
     		gc.setFill(Color.YELLOW);
     		gc.fillOval(node3x-radius, node3y-radius, 2*radius, 2*radius);
     		gc.setFill(Color.HOTPINK);
     		gc.fillOval(node4x-radius, node4y-radius, 2*radius, 2*radius);
     		
     		// Write some text
     		// Text is filled with the fill color
     		gc.setFill(Color.GREEN);
     		gc.setFont(new Font(18));
     		gc.fillText("Friend1", node1x-30, node1y+10);
     		gc.fillText("Friend2", node2x-30, node2y+10);
     		gc.fillText("Friend3", node3x-30, node3y+10);
     		gc.fillText("Friend4", node4x-30, node4y+10);
     		
     	

     		vbox.getChildren().add(canvas);

     		

     		// Add the vertical box to the center of the root pane
     		root.setCenter(vbox);
     		

     		// Add the stuff and set the primary stage
     		primaryStage.setTitle(APP_TITLE);
     		primaryStage.setScene(mainScene);
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
