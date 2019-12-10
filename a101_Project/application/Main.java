////////////////////////////////////////////////////////////////
// Title: a3 Social Network
// Authors: Ritika Mittal, Jared Horwitz, Keerthy Sudharsan,
// Sakuni Egodawatte, Erik Tiedt //
// Emails: rmittal6@wisc.edu, sudharsan@wisc.edu,
// jhorwitz3@wisc.edu, egodawatte@wisc.edu
// etiedt@wisc.edu
// Lectures: 001, 002
// Description: creates social network visualizer with GUI
// interface
// Files: Main.java, Graph.java, GraphADT.java, Person.java
// SocialNetwork.java, SocialNetworkADT.java
////////////////////////////////////////////////////////////////
package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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

  private SocialNetwork socialNetwork = new SocialNetwork();
  private VBox leftPane = new VBox(5);
  private ListView<String> userList = new ListView<>();
  private VBox statsBox = new VBox(5);
  private VBox twoInputBox = new VBox(5);
  private VBox centerBox = new VBox(5);
  private Person activeUser = null;
  private Pane graphPane = new Pane();

  private static final int WINDOW_WIDTH = 950; // width of pop up
  private static final int WINDOW_HEIGHT = 550; // height of pop up
  private static final String APP_TITLE = "Social Network"; // title to be displayed on window bar

  /**
   * Sets up the GUI and launches displays them to the user
   * 
   * @param primaryStage - stage that GUI elements are set on
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    // save args
    args = this.getParameters().getRaw();
    if (args != null)
      ; // Just wanted to get rid of the warning in my IDE about args not being used
    BorderPane root = new BorderPane();
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // links stylesheet to scene
    mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    root.requestFocus(); // takes focus away from text fields so hints display

    primaryStage.setOnCloseRequest(event -> { // Define Action for when program is exited
      if(!this.exit(primaryStage)) {
        event.consume();
      }
    }); // End Closing action definition


    // Initial setup of the scene's elements
    this.setUpStatsBox();
    this.setUpTwoInputBox(primaryStage);

    leftPane.getChildren().add(this.setUpSignUpBox());
    leftPane.getChildren().add(this.setUpCurrentUsersList());
    leftPane.getChildren().add(this.statsBox);
    leftPane.getChildren().add(this.twoInputBox);
    leftPane.setPadding(new Insets(5, 5, 5, 5));

    root.setTop(this.setUpMenuBox(primaryStage));
    root.setLeft(leftPane);
    root.setCenter(centerBox);



    // Set and show the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();

  }

  /**
   * Builds a GUI element for adding a new user to the netwrok.
   * 
   * @return a GUI element for signing up a new user
   */
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

    buttonNewUser.setOnAction((ActionEvent e) -> { // Define action for add button
      if (!text.getText().isEmpty()) {
        if (text.getText().contains(" ")) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Users must be a single name with no spaces");
        } else {
          boolean added;
          added = socialNetwork.addUser(text.getText());
          if (added) { // Only adds to list if in network
            userList.getItems().add(text.getText());
            if (activeUser == null) {
              activeUser = socialNetwork.getUserByName(text.getText());
              if (centerBox.getChildren().size() > 0) {
                graphPane = new Pane();
                graphPane.setPrefSize(500, 500);
                centerBox.getChildren().set(0, this.drawGraph());
              } else {
                graphPane = new Pane();
                graphPane.setPrefSize(500, 500);
                centerBox.getChildren().add(this.drawGraph());
              }
            }

          } else {
            ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
                .setText("Unable to add " + text.getText() + " to the network");
          }
        }
      } else {
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must specify a user");
      }
      updateCounts();
      text.clear();
    }); // end action for add button


    return signUpBox;

  }

  /**
   * Builds an Interactive GUI element with an active users list, a search bar, and remove button
   * 
   * @return a GUI element containing a list of active users, a search bar, and remove button
   */
  private VBox setUpCurrentUsersList() {
    VBox currentUsers = new VBox(5);
    Label currentUsersLabel = new Label("Current Users:");
    currentUsersLabel.setTextAlignment(TextAlignment.CENTER);

    userList.setOnMouseClicked((MouseEvent e) -> { // Define Action for clicking user in list
      if (userList.getSelectionModel().getSelectedItem() != null
          && !userList.getSelectionModel().getSelectedItem().isEmpty()) {

        activeUser = socialNetwork.getUserByName(userList.getSelectionModel().getSelectedItem());

        if (centerBox.getChildren().size() > 0) {
          graphPane = new Pane();
          graphPane.setPrefSize(500, 500);
          centerBox.getChildren().set(0, this.drawGraph());
        } else {
          graphPane = new Pane();
          graphPane.setPrefSize(500, 500);
          centerBox.getChildren().add(this.drawGraph());
        }
      }
    }); // end user list action


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
        if (searchField.getText().contains(" ")) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Users must be a single name, and not contain spaces.");
        } else { // Carry Out the search
          userList.getSelectionModel().select(searchField.getText());
          userList.getFocusModel().focus(userList.getSelectionModel().getSelectedIndex());
          userList.scrollTo(searchField.getText());
          if (userList.getItems().contains(searchField.getText())) {
            activeUser = socialNetwork.getUserByName(searchField.getText());
            if (centerBox.getChildren().size() > 0) {
              graphPane = new Pane();
              graphPane.setPrefSize(500, 500);
              centerBox.getChildren().set(0, this.drawGraph());
            } else {
              graphPane = new Pane();
              graphPane.setPrefSize(500, 500);
              centerBox.getChildren().add(this.drawGraph());
            }
          }
        }
      } else { // Search field was empty
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define a user to search for.");
      }
      searchField.clear();
    }); // End Search Action;

    remove.setOnAction((ActionEvent e) -> {
      if (!searchField.getText().isEmpty()) {
        boolean removed = socialNetwork.removeUser(searchField.getText());
        if (removed) {
          if (searchField.getText().equals(activeUser.getName())) {
            if (centerBox.getChildren().size() > 0) {
              centerBox.getChildren().remove(0);
            }
          }
          userList.getItems().remove(userList.getItems().indexOf(searchField.getText()));
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText(searchField.getText() + " was removed from the network");

          updateCounts();
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

  /**
   * Builds a GUI element to display different stats about the network, as well as as status message
   * for any commands or actions the user takes.
   */
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
    Label labelGroupNum = new Label(String.valueOf(socialNetwork.getConnectedComponents().size()));
    labelGroupNum.setStyle("-fx-font-weight:bold"); // makes label bold
    hboxGroups.getChildren().addAll(labelGroups, labelGroupNum);

    HBox hboxUserNumbers = new HBox(5);
    Label labelUsers = new Label("Total Users: ");
    Label labelNumUsers = new Label(String.valueOf(socialNetwork.getTotalUsers()));
    labelNumUsers.setStyle("-fx-font-weight:bold"); // makes label bold
    hboxUserNumbers.getChildren().addAll(labelUsers, labelNumUsers);

    HBox friendNumbers = new HBox(5);
    Label labelFriends = new Label("Total Friendships: ");
    Label labelNumFriends = new Label(String.valueOf(socialNetwork.getTotalFriends()));
    labelNumFriends.setStyle("-fx-font-weight:bold"); // makes label bold
    friendNumbers.getChildren().addAll(labelFriends, labelNumFriends);
    friendNumbers.setStyle("-fx-padding: 0 0 10 0"); // sets bottom padding to 10px



    this.statsBox.getChildren().addAll(hboxStatus, hboxGroups, hboxUserNumbers, friendNumbers);

  }

  /**
   * Creates an interactive GUI element that allows the user to add friendships, view mutual
   * friends, remove friendships, and view the shortest path between two users.
   * 
   * @param primaryStage - the primary stage of this application
   */
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
    Button mutualButton = new Button("Mutual Friends"); // Display in pop up window
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
      // Do some error checking of text fields
      if (!userOneField.getText().isBlank() && !userTwoField.getText().isBlank()) {
        if (userOneField.getText().contains(" ") || userTwoField.getText().contains(" ")) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Users must be a single name with no spaces");
        } else { // Carry out the add
          boolean success =
              socialNetwork.addFriends(userOneField.getText(), userTwoField.getText());
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
            updateCounts();

            // If one of the users were the active user, redraw graph
            if (activeUser == null) {
              activeUser = socialNetwork.getUserByName(userOneField.getText());
            }
            if (userOneField.getText().contentEquals(activeUser.getName())
                || userTwoField.getText().contentEquals(activeUser.getName())) {
              if (centerBox.getChildren().size() > 0) {
                graphPane = new Pane();
                graphPane.setPrefSize(500, 500);
                centerBox.getChildren().set(0, this.drawGraph());
              } else {
                graphPane = new Pane();
                graphPane.setPrefSize(500, 500);
                centerBox.getChildren().add(this.drawGraph());
              }
            }
          } else { // Failed to add friendship
            ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
                .setText("Unable to add friendship between " + userOneField.getText() + " & "
                    + userTwoField.getText());
          }
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
        if (userOneField.getText().contains(" ") || userTwoField.getText().contains(" ")) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Users must be a single name with no spaces");
        } else {
          boolean success =
              socialNetwork.removeFriends(userOneField.getText(), userTwoField.getText());
          if (success) { // Carry out the remove
            // Set Status Message
            ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
                .setText("Friendship between " + userOneField.getText() + " & "
                    + userTwoField.getText() + " removed!");
            updateCounts();
            // If one of the users were the active user, redraw graph
            if (userOneField.getText().contentEquals(activeUser.getName())
                || userTwoField.getText().contentEquals(activeUser.getName())) {
              if (centerBox.getChildren().size() > 0) {
                graphPane = new Pane();
                graphPane.setPrefSize(500, 500);
                centerBox.getChildren().set(0, this.drawGraph());
              } else {
                graphPane = new Pane();
                graphPane.setPrefSize(500, 500);
                centerBox.getChildren().add(this.drawGraph());
              }
            }
          } else { // Unable to remove friendship
            ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
                .setText("Was unable to remove friendship.");
          }
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
        if (userOneField.getText().contains(" ") || userTwoField.getText().contains(" ")) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Users must be a single name with no spaces");
        } else {
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

            // Show window
            shortPathPane.getChildren().add(shortPathLabel);
            shortPathWindow.show();

          } catch (IllegalArgumentException e1) {
            ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
                .setText(e1.getMessage());
          } catch (UserNotFoundException e1) {
            ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
                .setText(e1.getMessage());
          }
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
        if (userOneField.getText().contains(" ") || userTwoField.getText().contains(" ")) {
          ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
              .setText("Users must be a single name with no spaces");
        } else {

          try {
            Set<Person> mutualFriends =
                socialNetwork.getMutualFriends(userOneField.getText(), userTwoField.getText());

            // Create a new modal pop up window
            StackPane mutualFriendPane = new StackPane();
            Stage mutualFriendWindow = new Stage();
            Scene mutualFriendScene = new Scene(mutualFriendPane, 250, 250);
            mutualFriendWindow.setTitle("Mutual Friends");
            mutualFriendWindow.initModality(Modality.WINDOW_MODAL);
            mutualFriendWindow.setScene(mutualFriendScene);
            mutualFriendWindow.initOwner(primaryStage);


            // Populate mutual friends list
            ListView<String> mutual = new ListView<>();

            if (!mutualFriends.isEmpty()) {
              for (Person friend : mutualFriends) {
                mutual.getItems().add(friend.getName());
              }
              mutualFriendPane.getChildren().add(mutual);
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
        }

      } else { // One or more user boxes were blank
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Must define two users to see mutual friends.");
      }

      // Show Window
      userOneField.clear();
      userTwoField.clear();
    }); // End Mutual Friends Action

  }

  /**
   * Creates a menu bar with the options to save, load, and clear the network
   * 
   * @param primaryStage - the primary stage of this application
   * @return a MenuBar with the options to save, load, or clear the network
   */
  private MenuBar setUpMenuBox(Stage primaryStage) {

    MenuBar menuBar = new MenuBar();

    Menu file = new Menu("File");
    MenuItem save = new MenuItem("Save");
    MenuItem load = new MenuItem("Load");
    MenuItem clear = new MenuItem("Clear");
    file.getItems().add(save);
    file.getItems().add(load);
    file.getItems().add(clear);

    save.setOnAction((ActionEvent e) -> { // Define action for save button
      Stage saveStage = new Stage();
      FileChooser chooser = new FileChooser();
      chooser.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));
      File saveFile = chooser.showSaveDialog(saveStage);
      if (saveFile != null) {
        socialNetwork.saveToFile(saveFile);
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Save file written succesfully");
      }
    }); // End defining save action


    clear.setOnAction((ActionEvent e) -> { // Define action for clear button
      this.clear();
    }); // End defining action for clear button

    load.setOnAction((ActionEvent e) -> { // Define action for load button
      Stage loadStage = new Stage();
      FileChooser chooser = new FileChooser();
      chooser.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));
      File loadFile = chooser.showOpenDialog(loadStage);
      if (loadFile != null) {
        this.clear();
        socialNetwork.loadFromFile(loadFile);
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("File loaded succesfully");

        if (!socialNetwork.getAllUsersInNetwork().isEmpty()) { // Set an active user
          for (Person user : socialNetwork.getAllUsersInNetwork()) { // Populate user list
            userList.getItems().add(user.getName());
          }

          updateCounts();

          // I couldn't get getActiveUser to work for the 's' commands, so I chose one at random
          if (activeUser == null) {
            Person[] a = new Person[1];
            activeUser = socialNetwork.getAllUsersInNetwork().toArray(a)[0];
          }
          if (centerBox.getChildren().size() > 0) {
            graphPane = new Pane();
            graphPane.setPrefSize(500, 500);
            centerBox.getChildren().set(0, this.drawGraph());
          } else {
            graphPane = new Pane();
            graphPane.setPrefSize(500, 500);
            centerBox.getChildren().add(this.drawGraph());
          }
        }

      }

    }); // End defining load action

    // TODO: I'm not going to implement redo, too much work right now

    // Menu edit = new Menu("Edit");
    // MenuItem undo = new MenuItem("Undo");
    // MenuItem redo = new MenuItem("Redo");
    // edit.getItems().add(undo);
    // edit.getItems().add(redo);
    // menuBar.getMenus().add(edit);

    menuBar.getMenus().add(file);
    return menuBar;

  }

  /**
   * This method draws a visual representation of the active user end their friends. It is displayed
   * wit the active user as blue, surrounded by their friends displayed in green.
   * 
   * @return a visual representation of the active user and their friends.
   */
  private Pane drawGraph() {

    if (activeUser == null) {
      return new Pane();
    }

    ArrayList<Person> friends = new ArrayList<>();
    friends.addAll(activeUser.getFriends());

    // Positioning and size variables
    double distance = 5;
    double centerX = 350;
    double centerY = 250;
    double radius = 25;

    Circle centerUser = new Circle(centerX, centerY, radius, Paint.valueOf("#74a7f7")); // blue
    centerUser.setId(activeUser.getName());
    Text centerName = new Text(centerX - (radius / 2), centerY, activeUser.getName());
    graphPane.getChildren().add(centerUser);
    graphPane.getChildren().add(centerName);
    distance += (50 * ((int) (friends.size() / 7) + 1)); // increase draw distance as nodes increase

    // Make Nodes for each friend the active user has
    for (int i = 0; i < friends.size(); ++i) {

      // Positioning variables for each friend node
      double angle = 2 * i * Math.PI / friends.size(); // Set radians for positioning around center
      double xOffset = distance * Math.cos(angle);
      double yOffset = distance * Math.sin(angle);
      double x = centerX + xOffset;
      double y = centerY + yOffset;

      Circle friend = new Circle(x, y, radius, Paint.valueOf("#8fdb48")); // green
      friend.setId(friends.get(i).getName());
      Text friendName = new Text(x - (radius / 2), y, friends.get(i).getName());
      friendName.setId(friends.get(i).getName());
      graphPane.getChildren().add(friend);
      graphPane.getChildren().add(friendName);

      // Define Actions for clicking any user except central user
      // (Make clicked node the active user and redraw the graph)
      friend.setOnMouseClicked((MouseEvent e) -> {
        this.activeUser = socialNetwork.getUserByName(friend.getId());
        if (centerBox.getChildren().size() > 0) {
          this.graphPane = new Pane();
          this.graphPane.setPrefSize(500, 500);
          centerBox.getChildren().set(0, this.drawGraph());
        } else {
          this.graphPane = new Pane();
          this.graphPane.setPrefSize(500, 500);
          centerBox.getChildren().add(this.drawGraph());
        }
      });

      friendName.setOnMouseClicked((MouseEvent e) -> {
        this.activeUser = socialNetwork.getUserByName(friendName.getId());
        if (centerBox.getChildren().size() > 0) {
          this.graphPane = new Pane();
          this.graphPane.setPrefSize(500, 500);
          centerBox.getChildren().set(0, this.drawGraph());
        } else {
          this.graphPane = new Pane();
          this.graphPane.setPrefSize(500, 500);
          centerBox.getChildren().add(this.drawGraph());
        }
      });
      // End Defining actions
    }

    return graphPane;
  }

  /**
   * This method clears the entire network. Resets the graph, updates all counts back to 0, clears
   * the user list and status message.
   */
  private void clear() {
    activeUser = null;
    socialNetwork = new SocialNetwork();
    userList.getItems().clear();
    if (centerBox.getChildren().size() > 0) {
      this.graphPane = new Pane();
      this.graphPane.setPrefSize(500, 500);
      centerBox.getChildren().set(0, this.drawGraph());
    } else {
      this.graphPane = new Pane();
      this.graphPane.setPrefSize(500, 500);
      centerBox.getChildren().add(this.drawGraph());
    }
    ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1)).setText("");
    updateCounts();
  }

  /**
   * This method simply updates the displayed counts in the stats box to reflect the current counts
   * of the network.
   */
  private void updateCounts() {
    // Update connected components count
    ((Labeled) ((HBox) statsBox.getChildren().get(1)).getChildren().get(1))
        .setText(String.valueOf(socialNetwork.getConnectedComponents().size()));
    // Update total users count
    ((Labeled) ((HBox) statsBox.getChildren().get(2)).getChildren().get(1))
        .setText(String.valueOf(socialNetwork.getTotalUsers()));
    // Update total friends count
    ((Labeled) ((HBox) statsBox.getChildren().get(3)).getChildren().get(1))
        .setText(String.valueOf(socialNetwork.getTotalFriends()));
  }

  /**
   * Action definition for when the exit button is pushed.
   * 
   * @param primaryStage the primary stage of the application
   */
  private boolean exit(Stage primaryStage) {
    Alert closeConfirmation =
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
    Button saveButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
    saveButton.setText("Save & Exit");
    closeConfirmation.setHeaderText("Confirm Exit");
    closeConfirmation.initModality(Modality.APPLICATION_MODAL);
    closeConfirmation.initOwner(primaryStage);
    closeConfirmation.getButtonTypes().setAll(ButtonType.YES, ButtonType.OK, ButtonType.CANCEL);

    Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
    if (ButtonType.OK.equals(closeResponse.get())) {
      Stage saveStage = new Stage();
      FileChooser chooser = new FileChooser();
      chooser.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));
      File saveFile = chooser.showSaveDialog(saveStage);
      if (saveFile != null) {
        socialNetwork.saveToFile(saveFile);
        ((Labeled) ((VBox) statsBox.getChildren().get(0)).getChildren().get(1))
            .setText("Save file written succesfully");
      }
      Platform.exit();
    }
    if (ButtonType.YES.equals(closeResponse.get())) {
      Platform.exit();
    } else {
      return false;
    }
    return true;
  }

  /**
   * Main method to launch GUI
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
