////////////////////////////////////////////////////////////////
// Title: a3 Social Network //
// Authors: Ritika Mittal, Jared Horwitz, Keerthy Sudharsan, //
// Sakuni Egodawatte, Erik Tiedt //
// Emails: rmittal6@wisc.edu, sudharsan@wisc.edu, //
// jhorwitz3@wisc.edu, egodawatte@wisc.edu //
// etiedt@wisc.edu //
// Lectures: 001, 002 //
// Description: creates social network visualizer with GUI //
// interface //
// Files: Main.java, Graph.java, GraphADT.java, Person.java //
// SocialNetwork.java, SocialNetworkADT.java //
////////////////////////////////////////////////////////////////
package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * creates GUI interface that can be interacted with, but produces no results
 * 
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 * @author Erik Tiedt
 */
public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  public SocialNetwork socialNetwork = new SocialNetwork();
  public VBox leftPane = new VBox(5);
  public ListView<String> userList = new ListView<>();
  private VBox statsBox = new VBox(5);
  public VBox twoInputBox;
  public VBox centerBox;
  public VBox bottomBox;

  private static final int WINDOW_WIDTH = 950; // width of pop up
  private static final int WINDOW_HEIGHT = 400; // height of pop up
  private static final String APP_TITLE = "Social Network"; // title to be displayed on window bar

  /**
   * sets up elements to be displayed in GUI
   * 
   * TODO: We need to refactor this according to the provided design, & set it up for generic use
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
    Button clear = new Button("CLEAR");
    Button clear1 = new Button("CLEAR");
    Button clear2 = new Button("CLEAR SOCIAL NETWORK");
    clear2.setStyle("-fx-background-color:red; -fx-text-fill:white");
    // HBox hboxStatus = new HBox(5); // creates HBox w/ spacing of 5px
    // Label labelStatus = new Label("STATUS:");
    // // in working implementation, this label will change
    // Label labelStatusUpdate = new Label("Sample error status message.");
    // labelStatusUpdate.setStyle("-fx-text-fill: red"); // make label red
    // hboxStatus.getChildren().addAll(labelStatus, labelStatusUpdate);
    // hboxStatus.setStyle("-fx-padding: 0 0 7 0"); // sets bottom padding to 7px
    //
    // HBox hboxGroups = new HBox(5); // creates HBox w/ spacing of 5px
    // Label labelGroups = new Label("GROUPS:");
    // // in working implementation, this label will change
    // Label labelGroupNum = new Label("#");
    // labelGroupNum.setStyle("-fx-font-weight:bold"); // makes label bold
    // hboxGroups.getChildren().addAll(labelGroups, labelGroupNum);
    // hboxGroups.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding to 10px

    // (1) Label/TextField/Button for new user
    HBox hboxNewUser = new HBox(5);
    Label labelNewUser = new Label("NEW USER:");
    TextField fieldNewUser = new TextField();
    fieldNewUser.setPromptText("Name"); // hint text
    Button buttonNewUser = new Button("ADD");
    hboxNewUser.getChildren().addAll(labelNewUser, fieldNewUser, buttonNewUser);

    // (2) Label/TextField/Button for loading file
    HBox hboxLoadFile = new HBox(5);
    Label labelLoadFile = new Label("LOAD FILE:");
    TextField fieldLoadFile = new TextField();
    fieldLoadFile.setPromptText("filename.txt"); // hint text
    Button buttonLoadFile = new Button("LOAD");
    hboxLoadFile.getChildren().addAll(labelLoadFile, fieldLoadFile, buttonLoadFile);

    // (3) Label/TextField/Button for exporting file
    HBox hboxExport = new HBox(5);
    Label labelExport = new Label("EXPORT FILE:");
    TextField fieldExport = new TextField();
    fieldExport.setPromptText("filename.txt"); // hint text
    Button buttonExport = new Button("EXPORT");
    hboxExport.getChildren().addAll(labelExport, fieldExport, buttonExport);

    // places HBoxes 1, 2, 3, in VBox
    VBox vbox1 = new VBox(7); // creates VBox w/ spacing of 5px
    // vbox1.getChildren().addAll(hboxStatus, hboxGroups, hboxNewUser, hboxLoadFile, hboxExport,
    // clear1);

    VBox vbox2 = new VBox();

    Label label0 = new Label("EVALUATE FRIENDSHIP:");
    label0.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding of label to 10px

    // Labels/TextFields to enter two users to evaluate
    Label label1 = new Label("USER 1:");
    TextField textField1 = new TextField();
    textField1.setPromptText("Name"); // hint text
    Label label2 = new Label("USER 2:");
    TextField textField2 = new TextField();
    textField2.setPromptText("Name"); // hint text

    Button btn1 = new Button("MUTUAL FRIENDS"); // display in pop up window
    // show shortest path in graph from user 1 -> user 2
    Button btn2 = new Button("SHORTEST PATH");
    Button btn3 = new Button("REMOVE FRIENDSHIP");

    // HBoxes for Label/TextField for each user
    HBox hb = new HBox();
    hb.getChildren().addAll(label1, textField1);
    HBox hb2 = new HBox();
    hb2.getChildren().addAll(label2, textField2);
    hb.setSpacing(5);
    hb2.setSpacing(5);

    vbox2.getChildren().addAll(label0, hb, hb2, btn1, btn2, btn3, clear);
    vbox2.setSpacing(8);

    root.setCenter(vbox1);
    root.setRight(vbox2);
    root.requestFocus(); // takes focus away from text fields so hints display

    // Making a "Undo" Button in the top panel
    Button button1 = new Button("UNDO");

    // Making a "Redo" Button in the Top panel
    Button button2 = new Button("REDO");

    // Making a "Help" Button in the right panel
    Button button3 = new Button("HELP");

    // Making an "Exit" Button in the center panel
    Button button4 = new Button("EXIT");

    // Making an "Search" button
    Button button5 = new Button("SEARCH USER");

    // Creating a text field for the search bar
    TextField t1 = new TextField();

    // Creating a horizontal box for the search bar
    VBox v = new VBox();
    HBox h1 = new HBox();
    h1.getChildren().addAll(t1, button5, button3, button4);
    h1.setSpacing(5);
    HBox h2 = new HBox();
    h2.getChildren().addAll(button1, button2);
    h2.setSpacing(5);
    v.getChildren().addAll(h1, h2);
    v.setSpacing(8);
    VBox Vfinal = new VBox();

    VBox V = new VBox();
    // Graph :
    // Creates a canvas that can draw shapes and text
    Canvas canvas = new Canvas(400, 300);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // define position constants
    double radius = 20;
    double node1x = 150;
    double node1y = 60;
    double node2x = 70;
    double node2y = 160;
    double node3x = 100;
    double node3y = 205;
    double node4x = 280;
    double node4y = 69;

    // Draw a line
    // Lines use the stroke color
    // draw lines from center to center
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(2);
    gc.strokeLine(node1x, node1y, node2x, node2y);
    gc.strokeLine(node1x, node1y, node3x, node3y);
    gc.strokeLine(node1x, node1y, node4x, node4y);
    gc.strokeLine(node2x, node2y, node4x, node4y);

    // The circles draw from the top left, so to center them, subtract the radius
    // from each coordinate
    // Draw a few nodes
    gc.setFill(Color.BLACK);
    gc.fillOval(node1x - radius, node1y - radius, 2 * radius, 2 * radius);
    gc.setFill(Color.RED);
    gc.fillOval(node2x - radius, node2y - radius, 2 * radius, 2 * radius);
    gc.setFill(Color.YELLOW);
    gc.fillOval(node3x - radius, node3y - radius, 2 * radius, 2 * radius);
    gc.setFill(Color.HOTPINK);
    gc.fillOval(node4x - radius, node4y - radius, 2 * radius, 2 * radius);

    // Write some text
    // Text is filled with the fill color
    gc.setFill(Color.GREEN);
    gc.setFont(new Font(18));
    gc.fillText("Friend1", node1x - 30, node1y + 10);
    gc.fillText("Friend2", node2x - 30, node2y + 10);
    gc.fillText("Friend3", node3x - 30, node3y + 10);
    gc.fillText("Friend4", node4x - 30, node4y + 10);

    V.getChildren().add(canvas);
    Vfinal.getChildren().addAll(v, V, clear2);
    Vfinal.setSpacing(10);
    // root.setLeft(Vfinal);

    this.setUpStatsBox();

    leftPane.getChildren().add(this.setUpSignUpBox());
    leftPane.getChildren().add(this.setUpCurrentUsersList());
    leftPane.getChildren().add(this.statsBox);
    leftPane.setPadding(new Insets(5, 5, 5, 5));

    root.setTop(this.setUpMenuBox());
    root.setLeft(leftPane);

    // Add the elements and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();

  }

  private VBox setUpSignUpBox() {
    VBox signUpBox = new VBox(5);
    HBox newUserComponent = new HBox(5);

    Label labelNewUser = new Label("New User:");
    TextField text = new TextField();
    text.setPrefWidth(100);
    text.setPromptText("Sample Name");
    Button buttonNewUser = new Button("ADD");

    newUserComponent.getChildren().addAll(labelNewUser, text, buttonNewUser);
    signUpBox.getChildren().add(newUserComponent);

    buttonNewUser.setOnAction((ActionEvent e) -> {
      boolean added;
      added = socialNetwork.addUser(text.getText());
      if (added) { // Only adds to list if in network
        userList.getItems().add(text.getText());
      } else {
        ((Labeled) ((HBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Unable to add user");
      }
      ((Labeled) ((HBox) statsBox.getChildren().get(1)).getChildren().get(1))
          .setText(String.valueOf(socialNetwork.getConnectedComponents().size()));
      text.clear();
    });


    return signUpBox;

  }

  private VBox setUpCurrentUsersList() {
    VBox currentUsers = new VBox(5);

    Label currentUsersLabel = new Label("Current Users:");
    currentUsersLabel.setTextAlignment(TextAlignment.CENTER);


    TextField searchField = new TextField();
    searchField.setPromptText("Search User");
    Button search = new Button("Search");
    HBox searchComponent = new HBox(5);
    searchComponent.getChildren().add(searchField);
    searchComponent.getChildren().add(search);
    userList.setPrefHeight(200);
    userList.setPrefWidth(100);

    for (Person user : socialNetwork.getAllUsersInNetwork()) {
      userList.getItems().add(user.getName());
    }

    currentUsers.getChildren().add(currentUsersLabel);
    currentUsers.getChildren().add(searchComponent);
    currentUsers.getChildren().add(userList);

    search.setOnAction((ActionEvent e) -> {
      userList.getSelectionModel().select(searchField.getText());
      userList.getFocusModel().focus(userList.getSelectionModel().getSelectedIndex());
      userList.scrollTo(searchField.getText());
      searchField.clear();
    });

    return currentUsers;
  }

  private void setUpStatsBox() {

    HBox hboxStatus = new HBox(5); // creates HBox w/ spacing of 5px
    Label labelStatus = new Label("Status:");
    // in working implementation, this label will change
    Label labelStatusUpdate = new Label("Sample error status message.");
    labelStatusUpdate.setStyle("-fx-text-fill: red"); // make label red
    hboxStatus.getChildren().addAll(labelStatus, labelStatusUpdate);
    hboxStatus.setStyle("-fx-padding: 0 0 7 0"); // sets bottom padding to 7px

    HBox hboxGroups = new HBox(5); // creates HBox w/ spacing of 5px
    Label labelGroups = new Label("Distinct Friend Groups:");
    // in working implementation, this label will change
    Label labelGroupNum = new Label(String.valueOf(socialNetwork.getConnectedComponents().size()));
    labelGroupNum.setStyle("-fx-font-weight:bold"); // makes label bold
    hboxGroups.getChildren().addAll(labelGroups, labelGroupNum);
    hboxGroups.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding to 10px

    this.statsBox.getChildren().addAll(hboxStatus, hboxGroups);

  }

  private void setUpTwoInputBox() {

  }

  private void setUpCenterBox() {

  }

  private void setUpBottomBox() {

  }

  private MenuBar setUpMenuBox() {

    MenuBar menuBar = new MenuBar();

    Menu file = new Menu("File");
    MenuItem save = new MenuItem("Save");
    MenuItem load = new MenuItem("Load");
    file.getItems().add(save);
    file.getItems().add(load);

    Menu edit = new Menu("Edit");
    MenuItem undo = new MenuItem("Undo");
    MenuItem redo = new MenuItem("Redo");
    edit.getItems().add(undo);
    edit.getItems().add(redo);


    menuBar.getMenus().add(file);
    menuBar.getMenus().add(edit);

    return menuBar;

  }

  private void drawGraph(GraphicsContext canvas) {

  }

  private void drawNode(GraphicsContext canvas, String name, double x, double y) {

  }

  private void drawEdge(GraphicsContext canvas, double x1, double y1, double x2, double y2) {

  }

  private String getNameFromCoordinates(double x, double y) {
    return null;
  }

  private void setSelectedUser(String name) {

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
