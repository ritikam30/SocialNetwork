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
import javafx.geometry.Pos;
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
		
		HBox hboxStatus = new HBox(5); // creates HBox w/ spacing of 5px
		Label labelStatus = new Label("Status:");
		// TODO: in working implementation, this label should change
		Label labelStatusUpdate = new Label("Sample error status.");
		labelStatusUpdate.setStyle("-fx-text-fill: red"); // make label red
		hboxStatus.getChildren().addAll(labelStatus, labelStatusUpdate);
		hboxStatus.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding to 10px
		
		// (1) Label/TextField/Button for new user
		HBox hboxNewUser = new HBox(5);
		Label labelNewUser = new Label("New User:");
		TextField fieldNewUser = new TextField();
		fieldNewUser.setPromptText("Name"); // hint text
		Button buttonNewUser = new Button("add");
		hboxNewUser.getChildren().addAll(labelNewUser, fieldNewUser, buttonNewUser);
		
		// (2) Label/TextField/Button for loading file
		HBox hboxLoadFile = new HBox(5);
		Label labelLoadFile = new Label("Load File:");
		TextField fieldLoadFile = new TextField();
		fieldLoadFile.setPromptText("filename.txt"); // hint text
		Button buttonLoadFile = new Button("load");
		hboxLoadFile.getChildren().addAll(labelLoadFile, fieldLoadFile, buttonLoadFile);
		
		// (3) Label/TextField/Button for exporting file
		HBox hboxExport = new HBox(5);
		Label labelExport = new Label("Export:");
		TextField fieldExport = new TextField();
		fieldExport.setPromptText("filename.txt"); // hint text
		Button buttonExport = new Button("export");
		hboxExport.getChildren().addAll(labelExport, fieldExport, buttonExport);
		
		// places HBoxes 1, 2, 3, in VBox
		VBox vbox = new VBox(5); // creates VBox w/ spacing of 5px
		vbox.getChildren().addAll(hboxStatus, hboxNewUser, hboxLoadFile, hboxExport);
		
		root.setRight(vbox); // sets vbox to horizontal right
		// vbox.setAlignment(Pos.CENTER); // sets vbox to vertical center FIXME
		
		root.requestFocus(); // takes focus away from text fields so hints display 

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		// primaryStage.setMaximized(true); // makes window fullscreen FIXME
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
