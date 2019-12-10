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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
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
  public VBox twoInputBox = new VBox(5);
  public VBox centerBox = new VBox(5);
  public VBox bottomBox;
  private Person activeUser = null;

  private static final int WINDOW_WIDTH = 950; // width of pop up
  private static final int WINDOW_HEIGHT = 500; // height of pop up
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



    // // Graph :
    // // Creates a canvas that can draw shapes and text
    // Canvas canvas = new Canvas(400, 300);
    // GraphicsContext gc = canvas.getGraphicsContext2D();
    // // define position constants
    // double radius = 20;
    // double node1x = 150;
    // double node1y = 60;
    // double node2x = 70;
    // double node2y = 160;
    // double node3x = 100;
    // double node3y = 205;
    // double node4x = 280;
    // double node4y = 69;
    //
    // // Draw a line
    // // Lines use the stroke color
    // // draw lines from center to center
    // gc.setStroke(Color.BLUE);
    // gc.setLineWidth(2);
    // gc.strokeLine(node1x, node1y, node2x, node2y);
    // gc.strokeLine(node1x, node1y, node3x, node3y);
    // gc.strokeLine(node1x, node1y, node4x, node4y);
    // gc.strokeLine(node2x, node2y, node4x, node4y);
    //
    // // The circles draw from the top left, so to center them, subtract the radius
    // // from each coordinate
    // // Draw a few nodes
    // gc.setFill(Color.BLACK);
    // gc.fillOval(node1x - radius, node1y - radius, 2 * radius, 2 * radius);
    // gc.setFill(Color.RED);
    // gc.fillOval(node2x - radius, node2y - radius, 2 * radius, 2 * radius);
    // gc.setFill(Color.YELLOW);
    // gc.fillOval(node3x - radius, node3y - radius, 2 * radius, 2 * radius);
    // gc.setFill(Color.HOTPINK);
    // gc.fillOval(node4x - radius, node4y - radius, 2 * radius, 2 * radius);
    //
    // // Write some text
    // // Text is filled with the fill color
    // gc.setFill(Color.GREEN);
    // gc.setFont(new Font(18));
    // gc.fillText("Friend1", node1x - 30, node1y + 10);
    // gc.fillText("Friend2", node2x - 30, node2y + 10);
    // gc.fillText("Friend3", node3x - 30, node3y + 10);
    // gc.fillText("Friend4", node4x - 30, node4y + 10);


    // BEGIN IMPORTANT ELEMENTS FOR MAIN

    // save args example
    args = this.getParameters().getRaw();

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // creates new scene
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    // links stylesheet to scene
    mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    root.requestFocus(); // takes focus away from text fields so hints display

    this.setUpStatsBox();
    this.setUpTwoInputBox(primaryStage);

    leftPane.getChildren().add(this.setUpSignUpBox());
    leftPane.getChildren().add(this.setUpCurrentUsersList());
    leftPane.getChildren().add(this.statsBox);
    leftPane.getChildren().add(this.twoInputBox);
    leftPane.setPadding(new Insets(5, 5, 5, 5));

    root.setTop(this.setUpMenuBox());
    root.setLeft(leftPane);
    root.setCenter(centerBox);

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
    text.setPrefWidth(115);
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
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Unable to add " + text.getText() + " to the network");
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
    searchField.setPrefWidth(125);
    Button search = new Button("Search");
    Button remove = new Button("Remove");
    HBox searchComponent = new HBox(5);
    searchComponent.setAlignment(Pos.CENTER);
    searchComponent.getChildren().add(searchField);
    searchComponent.getChildren().add(search);
    searchComponent.getChildren().add(remove);
    userList.setPrefHeight(200);
    userList.setPrefWidth(100);

    for (Person user : socialNetwork.getAllUsersInNetwork()) {
      userList.getItems().add(user.getName());
    }

    currentUsers.getChildren().add(currentUsersLabel);
    currentUsers.getChildren().add(searchComponent);
    currentUsers.getChildren().add(userList);

    search.setOnAction((ActionEvent e) -> { // Define Search action
      if (!searchField.getText().isEmpty()) {
        userList.getSelectionModel().select(searchField.getText());
        userList.getFocusModel().focus(userList.getSelectionModel().getSelectedIndex());
        userList.scrollTo(searchField.getText());
        if (userList.getItems().contains(searchField.getText())) {
          activeUser = socialNetwork.getUserByName(searchField.getText());
          if (centerBox.getChildren().size() > 0) {
            centerBox.getChildren().set(0, this.makeGraph());
          } else {
            centerBox.getChildren().add(this.makeGraph());
          }
        }
      } else {
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define a user to search for.");
      }
      searchField.clear();
    });

    remove.setOnAction((ActionEvent e) -> {
      if (!searchField.getText().isEmpty()) {
        boolean removed = socialNetwork.removeUser(searchField.getText());
        if (removed) {
          userList.getItems().remove(userList.getItems().indexOf(searchField.getText()));
          ((Labeled) ((HBox) statsBox.getChildren().get(1)).getChildren().get(1))
              .setText(String.valueOf(socialNetwork.getConnectedComponents().size()));
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText(searchField.getText() + " was removed from the network");
        } else {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Unable to remove " + searchField.getText() + " from the network");
        }
      } else {
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define a user to remove.");
      }
      searchField.clear();
    });

    return currentUsers;
  }

  private void setUpStatsBox() {

    VBox hboxStatus = new VBox(5); // creates HBox w/ spacing of 5px
    Label labelStatus = new Label("Status:");
    // in working implementation, this label will change
    Label labelStatusUpdate = new Label("");
    labelStatusUpdate.setWrapText(true);
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

  private void setUpTwoInputBox(Stage primaryStage) {

    Label label0 = new Label("Multi User Options:");
    label0.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding of label to 10px

    // Labels/TextFields to enter two users to evaluate
    Label userOneLabel = new Label("User 1:");
    TextField userOneField = new TextField();
    userOneField.setPromptText("Name");
    Label userTwoLabel = new Label("User 2:");
    TextField userTwoField = new TextField();
    userTwoField.setPromptText("Name");


    Button addFriendsButton = new Button("Add Friends");
    Button mutualButton = new Button("Mutual Friends"); // display in pop up window
    Button shortButton = new Button("Shortest Path"); // Display in pop up window
    Button removeFriendButton = new Button("Remove Friends");

    // HBoxes for Label/TextField for each user
    HBox userOneBox = new HBox();
    userOneBox.getChildren().addAll(userOneLabel, userOneField);
    HBox userTwoBox = new HBox();
    userTwoBox.getChildren().addAll(userTwoLabel, userTwoField);
    userOneBox.setSpacing(5);
    userTwoBox.setSpacing(5);

    HBox addRemoveBox = new HBox(5);
    HBox specsBox = new HBox(5);
    addRemoveBox.getChildren().addAll(addFriendsButton, removeFriendButton);
    specsBox.getChildren().addAll(shortButton, mutualButton);

    twoInputBox.getChildren().addAll(userOneBox, userTwoBox, addRemoveBox, specsBox);


    addFriendsButton.setOnAction((ActionEvent e) -> { // Define Action for add friends
      if (!userOneField.getText().isBlank() && !userTwoField.getText().isBlank()) {
        boolean success = socialNetwork.addFriends(userOneField.getText(), userTwoField.getText());
        if (success) {
          // Add users to current user list if not present
          if (!userList.getItems().contains(userOneField.getText())) {
            userList.getItems().add(userOneField.getText());
          }
          if (!userList.getItems().contains(userTwoField.getText())) {
            userList.getItems().add(userTwoField.getText());
          }
          // Set Status Message
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Friendship between " + userOneField.getText() + " & "
                  + userTwoField.getText() + " added!");
          // Update Distinct Groups
          ((Labeled) ((HBox) statsBox.getChildren().get(1)).getChildren().get(1))
              .setText(String.valueOf(socialNetwork.getConnectedComponents().size()));
        } else {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Unable to add friendship between " + userOneField.getText() + " & "
                  + userTwoField.getText());
        }
      } else { // One or more user boxes were blank
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define two users to add a friendship.");
      }

      userOneField.clear();
      userTwoField.clear();
    }); // End Add Friends Action

    removeFriendButton.setOnAction((ActionEvent e) -> { // Define action for remove friends
      if (!userOneField.getText().isBlank() && !userTwoField.getText().isBlank()) {
        boolean success =
            socialNetwork.removeFriends(userOneField.getText(), userTwoField.getText());
        if (success) {
          // Set Status Message
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Friendship between " + userOneField.getText() + " & "
                  + userTwoField.getText() + " removed!");
          // Update Distinct Groups
          ((Labeled) ((HBox) statsBox.getChildren().get(1)).getChildren().get(1))
              .setText(String.valueOf(socialNetwork.getConnectedComponents().size()));
        }
      } else { // One or more user boxes were blank
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define two users to remove a friendship.");
      }

      userOneField.clear();
      userTwoField.clear();
    }); // End remove friends action

    shortButton.setOnAction((ActionEvent e) -> { // Define action for shortest path
      if (!userOneField.getText().isBlank() && !userTwoField.getText().isBlank()) {
        try {
          List<Person> path =
              socialNetwork.getShortestPath(userOneField.getText(), userTwoField.getText());

          // Build a modal pop up window
          StackPane shortPathPane = new StackPane();
          Stage shortPathWindow = new Stage();
          Scene shortPathScene = new Scene(shortPathPane, 250, 50);


          shortPathWindow.setTitle("Shortest Path");
          shortPathWindow.initModality(Modality.WINDOW_MODAL);
          shortPathWindow.setScene(shortPathScene);
          shortPathWindow.initOwner(primaryStage);
          Label shortPathLabel = new Label();
          shortPathLabel.setTextAlignment(TextAlignment.CENTER);

          // Write the path
          String pathAsString = "";

          if (path != null) {
            for (Person hop : path) {
              pathAsString += hop.getName() + "->";
            }
            pathAsString = pathAsString.substring(0, pathAsString.length() - 2);
            shortPathLabel.setText("Shortest Path: \n" + pathAsString);
          } else {
            shortPathLabel.setText("No path exists between the given users.");
          }

          shortPathPane.getChildren().add(shortPathLabel);

          shortPathWindow.show();

        } catch (IllegalArgumentException e1) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText(e1.getMessage());
        } catch (UserNotFoundException e1) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText(e1.getMessage());
        }

      } else { // One or more user boxes were blank
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define two users to get a shortest path.");
      }

      userOneField.clear();
      userTwoField.clear();
    }); // End action for shortest path

    mutualButton.setOnAction((ActionEvent e) -> { // Define Mutual Friends Action
      if (!userOneField.getText().isBlank() && !userTwoField.getText().isBlank()) {

        try {
          Set<Person> mutualFriends =
              socialNetwork.getMutualFriends(userOneField.getText(), userTwoField.getText());

          StackPane mutualFriendPane = new StackPane();
          Stage mutualFriendWindow = new Stage();
          Scene mutualFriendScene = new Scene(mutualFriendPane, 250, 250);


          mutualFriendWindow.setTitle("Mutual Friends");
          mutualFriendWindow.initModality(Modality.WINDOW_MODAL);
          mutualFriendWindow.setScene(mutualFriendScene);
          mutualFriendWindow.initOwner(primaryStage);
          ListView<String> mutual = new ListView<>();

          if (!mutualFriends.isEmpty()) {
            for (Person friend : mutualFriends) {
              mutual.getItems().add(friend.getName());
              mutualFriendPane.getChildren().add(mutual);
            }
          } else {
            Label noFriends = new Label("These users have no mutual friends.");
            mutualFriendPane.getChildren().add(noFriends);
          }

          mutualFriendWindow.show();

        } catch (IllegalArgumentException e1) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText(e1.getMessage());
        } catch (UserNotFoundException e1) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText(e1.getMessage());
        }

      } else { // One or more user boxes were blank
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define two users to see mutual friends.");
      }

      userOneField.clear();
      userTwoField.clear();
    }); // End Mutual Friends Action

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

  private Pane makeGraph() {

    Pane graphPane = new Pane();
    ArrayList<Person> friends = new ArrayList<>();
    friends.addAll(activeUser.getFriends());
    double distance = 100;
    double centerX = 350;
    double centerY = 250;
    double radius = 25;
    Circle centerUser =
        new Circle(centerX, centerY, radius, Color.BLUE);
    centerUser.setId(activeUser.getName());
    Text centerName = new Text(centerX - (radius/2), centerY, activeUser.getName());
    graphPane.getChildren().add(centerUser);
    graphPane.getChildren().add(centerName);
    
    for(int i = 0; i < friends.size(); ++i) {
      double angle = 2 * i * Math.PI / friends.size();
      double xOffset = distance * Math.cos(angle);
      double yOffset = distance * Math.sin(angle);
      double x = centerX + xOffset;
      double y = centerY + yOffset;
      Circle friend = new Circle(x, y, radius, Color.GREEN);
      friend.setId(friends.get(i).getName());
      Text friendName = new Text(x - (radius/2), y, friends.get(i).getName());
      graphPane.getChildren().add(friend);
      graphPane.getChildren().add(friendName);
      
      friend.setOnMouseClicked((MouseEvent e) ->{
        this.activeUser = socialNetwork.getUserByName(friend.getId());
        if (centerBox.getChildren().size() > 0) {
          centerBox.getChildren().set(0, this.makeGraph());
        } else {
          centerBox.getChildren().add(this.makeGraph());
        }
      });
    }
    
    
    
    
    
    
    return graphPane;

    // Group friendVerticies = new Group();
    // Pane graphCanvas = new Pane();
    // Set<Person> userFriends = activeUser.getFriends();
    // Pane centerVertex = new StackPane();
    // Circle centerCircle = new Circle(10, Color.BLUE);
    // Text centerText = new Text(activeUser.getName());
    // centerCircle.setId(activeUser.getName());
    // centerVertex.getChildren().addAll(centerCircle, centerText);
    // friendVerticies.getChildren().add(centerVertex);
    // graphCanvas.getChildren().add(centerVertex);
    // GridPane graphPane = new GridPane();
    // graphPane.getChildren().add(centerVertex);


    // for (Person friend : userFriends) {
    // Pane friendVertex = new StackPane();
    // Circle friendCircle = new Circle(10, Color.GREEN);
    // Text friendText = new Text(friend.getName());
    // friendVertex.getChildren().addAll(friendCircle, friendText);
    // friendCircle.setId(friend.getName());
    // friendVerticies.getChildren().add(friendVertex);
    // graphCanvas.getChildren().add(friendVertex);
    // graphPane.getChildren().add(friendVertex);
    // }
    //
    // return graphCanvas;
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
