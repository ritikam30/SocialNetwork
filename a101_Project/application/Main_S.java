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
		hboxStatus.setStyle("-fx-padding: 0 0 7 0"); // sets bottom padding to 7px
		
		HBox hboxGroups = new HBox(5); // creates HBox w/ spacing of 5px
		Label labelGroups = new Label("Groups:");
		// TODO: in working implementation, this label should change
		Label labelGroupNum = new Label("#");
		labelGroupNum.setStyle("-fx-font-weight:bold"); // makes label bold
		hboxGroups.getChildren().addAll(labelGroups, labelGroupNum);
		hboxGroups.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding to 10px
		
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
		VBox vbox1 = new VBox(7); // creates VBox w/ spacing of 5px
		vbox1.getChildren().addAll(hboxStatus, hboxGroups, hboxNewUser, hboxLoadFile, hboxExport);
		
		VBox vbox2 = new VBox();
		
		Label label0 = new Label("Compare:");
		label0.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding of label to 10px
		
		Label label1 = new Label("User1: ");
		TextField textField1 = new TextField ();
		textField1.setPromptText("Name"); // hint text
		Label label2 = new Label("User2: ");
		TextField textField2 = new TextField ();
		textField2.setPromptText("Name"); // hint text
		
		Button btn1 = new Button("Mutual Friends");
		Button btn2 = new Button("Shortest Path");
		Button btn3 = new Button("Remove Friendship");
		
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField1);
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(label2, textField2);
		hb.setSpacing(5);
		hb2.setSpacing(5);
		
		vbox2.getChildren().addAll(label0,hb,hb2,btn1,btn2,btn3);
		vbox2.setSpacing(7);
		
		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(vbox1, vbox2);
		
		// button to clear display
		Button clear = new Button("clear");
		clear.setStyle("-fx-background-color:red; -fx-text-fill:white");
		
		root.setRight(vbox); // sets vbox to horizontal right
		root.setBottom(clear); // sets clear button to left
		
		root.requestFocus(); // takes focus away from text fields so hints display 

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
