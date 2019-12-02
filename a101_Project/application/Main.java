////////////////////////////////////////////////////////////////
// Title: ATeam Project                                       // 
// Author: TODO                                  			  //
// Email: TODO@wisc.edu                                       //
// Lecture: TODO                                              //
// Description: creates simple GUI program using JavaFX		  //                                                 
// Files: //TODO   				          			          //   
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * creates GUI interface that can be interacted with, but produces no results
 * 
 * @author Sakuni Egodawatte
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
