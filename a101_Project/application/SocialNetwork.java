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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * creates social network framework using Graph class
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
public class SocialNetwork implements SocialNetworkADT {
  // fields
  private Graph graph;
  private ArrayList<String> log; // List of executed commands
  private ArrayList<String> theUndone; // List of commands that were undone

  /**
   * constructor, initializes graph field to new Graph object
   */
  public SocialNetwork() {
    graph = new Graph();
    log = new ArrayList<>();
    theUndone = new ArrayList<>();
  }


  /**
   * Given two stings, attempts to add a friendship between the given users. If one or both users
   * are not in the network, it adds them to the network in the process.
   * 
   * @param friend1 - name of the first friend
   * @param friend2 - name of the second friend
   * @return true if friendship was added, false otherwise.
   */
  @Override
  public boolean addFriends(String friend1, String friend2) {

    // Check for bad inputs
    if (friend1 == null || friend2 == null) {
      return false;
    }

    Person person1;
    Person person2;

    // Check if user exist in network, create if not
    if (graph.getNode(friend1) == null) {
      person1 = new Person(friend1);
    } else {
      person1 = graph.getNode(friend1);
    }
    if (graph.getNode(friend2) == null) {
      person2 = new Person(friend2);
    } else {
      person2 = graph.getNode(friend2);
    }

    graph.addEdge(person1, person2);

    return true;
  }

  /**
   * Given two names, attempts to remove the friendship between the two users. Returns true if
   * successful otherwise false.
   * 
   * @param friend1 - name of the first friend
   * @param friend2 - name of the second friend
   * @return true if friendship was removed, false otherwise.
   */
  @Override
  public boolean removeFriends(String friend1, String friend2) {

    // Check for bad inputs
    if (friend1 == null || friend2 == null) {
      return false;
    }

    Person person1 = graph.getNode(friend1);
    Person person2 = graph.getNode(friend2);

    // Check if users exist in network
    if (person1 == null || person2 == null) {
      return false;
    }


    graph.removeEdge(person1, person2);

    return true;
  }

  /**
   * Given the name of a user, attempts to add the user to the network.
   * 
   * @param user - name of user to add to the network
   * @return true if specified user was added, false otherwise
   */
  @Override
  public boolean addUser(String user) {

    // Check for bad inputs
    if (user == null) {
      return false;
    }
    if (graph.getNode(user) != null) {
      return false;
    }

    graph.addNode(new Person(user));

    return true;
  }

  /**
   * Given a user's name, removes that user from the network.
   * 
   * @param user - user to remove
   * @return true if removed successfully, otherwise false;
   */
  @Override
  public boolean removeUser(String user) {

    // Check for bad inputs
    if (user == null) {
      return false;
    }
    if (graph.getNode(user) == null) {
      return false;
    }

    graph.removeNode(graph.getNode(user));
    return true;
  }

  /**
   * returns Set of adjacent nodes to Person obj associated w/ passed in String parameter
   * 
   * @return Set<Person> - adjacent nodes to Person obj w/ name in var user
   * @throws UserNotFoundException
   */
  @Override
  public Set<Person> getFriends(String user) throws UserNotFoundException {
    // uses getNode() to retrieve Person obj associate w/ String parameter
    Person person = graph.getNode(user);

    // throws exception if Person obj w/ name user DNE
    if (person == null) {
      throw new UserNotFoundException("Person does not exist in social network.");

    }

    // returns friends of Person obj
    return graph.getNeighbors(person);
  }

  /**
   * Given the names of two users, returns a set of their mutual friends. This can be a potentially
   * empty list.
   * 
   * @param user1 - One of two users to check for mutual friends between
   * @param user2 - The other user to check for mutual friends between
   * @return Set<Person> of mutual friends between the specified users
   * @throws UserNotFoundException, IllegalArgumentException
   */
  @Override
  public Set<Person> getMutualFriends(String user1, String user2)
      throws UserNotFoundException, IllegalArgumentException {

    // Check for bad inputs
    if (user1 == null || user2 == null) {
      throw new IllegalArgumentException("Null string detected for one of the specified users");
    }
    if (graph.getNode(user1) == null) {
      throw new UserNotFoundException(user1 + " is not in the network.");
    }

    if (graph.getNode(user2) == null) {
      throw new UserNotFoundException(user2 + " is not in the network.");
    }

    // Convert sets to lists
    ArrayList<Person> p1Friends = new ArrayList<>();
    p1Friends.addAll(graph.getNode(user1).getFriends());
    ArrayList<Person> p2Friends = new ArrayList<>();
    p2Friends.addAll(graph.getNode(user2).getFriends());
    Set<Person> mutual = new HashSet<>();

    // Check for mutual friends
    for (Person friend : p1Friends) {
      if (p2Friends.contains(friend)) {
        mutual.add(friend);
      }
    }

    return mutual;
  }

  /**
   * Given the name of two users, finds the shortest path between those two users. Returns a list
   * with the element at the first index representing the source user, and the element at the end
   * representing the target user. If there is not path between the two users, this method returns
   * null.
   * 
   * @param source - Name of starting user for the shortest path
   * @param target - Name of the target user for the shortest path
   * @return a list representing the shortest path between two users if it exists, else null
   * @throws UserNotFoundException, IllegalArgumentException
   */
  @Override
  public List<Person> getShortestPath(String source, String target)
      throws UserNotFoundException, IllegalArgumentException {

    // Check for bad inputs
    if (source == null || target == null) {
      throw new IllegalArgumentException("Null string detected for one of the specified users");
    }
    if (graph.getNode(source) == null) {
      throw new UserNotFoundException(source + " is not in the network.");
    }
    if (graph.getNode(target) == null) {
      throw new UserNotFoundException(target + " is not in the network.");
    }

    Person startPerson = graph.getNode(source);
    ArrayList<Person> queue = new ArrayList<>();
    ArrayList<Person> path = new ArrayList<>();
    HashMap<Person, Person> predecesors = new HashMap<>(); // HashMap of form <Friend, Predecessor>

    queue.add(startPerson);
    predecesors.put(startPerson, null);

    while (!queue.isEmpty()) { // BFS Loop
      Person current = queue.get(0);
      ArrayList<Person> friendsList = new ArrayList<>();
      friendsList.addAll(current.getFriends());

      for (int i = 0; i < friendsList.size(); ++i) { // Iterate through current person's friends
        Person friend = friendsList.get(i);

        if (!predecesors.containsKey(friend)) { // If not visited, add to queue
          queue.add(friend);
          predecesors.put(friend, current);

          if (friend.getName().equals(target)) {// Check if target reached
            Person toAdd = graph.getNode(target);

            while (toAdd != null) { // Get complete path
              path.add(0, toAdd);
              toAdd = predecesors.get(toAdd);
            }

            return path;
          }
        }
        queue.remove(0);
      }
    }

    return null;
  }


  @Override
  public Set<Graph> getConnectedComponents() {
    ArrayList<Person> network = new ArrayList<>();
    network.addAll(graph.getAllNodes());
    HashSet<Person> added = new HashSet<>();
    ArrayList<Person> queue;
    Person currentPerson;
    Graph connectedComponent;
    Set<Graph> allComponents = new HashSet<>();

    while (!added.containsAll(network)) { // Component Loop
      currentPerson = network.get(0);
      connectedComponent = new Graph();
      connectedComponent.addNode(currentPerson);
      queue = new ArrayList<>();
      queue.add(currentPerson);

      while (!queue.isEmpty()) { // Connections Loop
        for (Person friend : currentPerson.getFriends()) {
          connectedComponent.addEdge(currentPerson, friend);
          if (!added.contains(friend)) {
            queue.add(friend);
          }
        }

        added.add(currentPerson);
        queue.remove(0); // remove the current person
        currentPerson = queue.get(0); // set current person to next in queue;

      }

      allComponents.add(connectedComponent);

    }



    return null;
  }

  /**
   * Given a text file, reads each line as a command to perform an action within the network.
   * 
   * @param fileName - file to read the commands from.
   */
  @Override
  public void loadFromFile(File fileName) {
    // TODO: This method is still unfinished, and needs to be wrapped up later.

    try {
      Scanner fileIn = new Scanner(fileName);
      String currentCommand;
      String[] commandArray;

      while (fileIn.hasNextLine()) { // Begin file loop
        currentCommand = fileIn.nextLine();
        commandArray = currentCommand.split(" ");

        if (commandArray.length > 3) { // Invalid command
          continue;
        }

        switch (commandArray[0]) {
          case "a":
            if (commandArray.length == 2) { // Add user
              this.addUser(commandArray[1]);
            } else { // Add edge
              this.addFriends(commandArray[1], commandArray[2]);
            }
            break;
          case "r":
            if (commandArray.length == 2) { // Remove user
              this.removeUser(commandArray[1]);
            } else { // Remove edge
              this.removeFriends(commandArray[1], commandArray[2]);
            }
            break;
          case "s":
            // TODO: Need code to set focus user here. Not sure on implementation yet
            break;
          default:
            // Invalid Command. Maybe we need something here? IDK
            break;
        }

      } // End file loop
      
      fileIn.close();

    } catch (FileNotFoundException e) {
      // TODO: Figure out how we're getting this to be displayed to the user
    }


  }

  /**
   * Writes to a text file the CLI commands needed to create this network.
   * 
   * @param fileName - text file to write to
   */
  @Override
  public void saveToFile(File fileName) {
    
    if(!fileName.exists()) {
      try {
        fileName.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    try {
      PrintWriter writer = new PrintWriter(fileName);
      for(String command : log) {
        writer.println(command);
      }
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

}
