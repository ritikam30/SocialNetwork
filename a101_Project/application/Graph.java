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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * creates undirected graph of People objects
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
public class Graph implements GraphADT {
  // fields
  private List<Person> people; // to store Person objects
  private int numPeople; // number of vertices in Graph
  private int numEdges; // number of friendships

  /**
   * constructor for Graph class, initializes list of Person objects, and fields
   */
  public Graph() {
    people = new ArrayList<Person>();
    numPeople = 0;
    numEdges = 0;

  }

  /**
   * Add new Person to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 
   * 1. Person is not null 
   * 2. Person is not already in the graph
   * 
   * @param name - the Person to be added
   */
  @Override
  public boolean addNode(Person name) {

    // Check for bad input
    if (people.contains(name) || name == null) {
      return false;
    }

    people.add(name);
    return true;
  }

  /**
   * Remove a Person and all associated edges from the graph.
   * 
   * If Person is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 
   * 1. Person is not null 
   * 2. Person is in the graph
   * 
   * @param name - the person to be removed
   */
  @Override
  public boolean removeNode(Person name) {

    // Check for bad inputs
    if (!people.contains(name) || name == null) {
      return false;
    }

    // Remove all associated edges
    for (Person friend : name.getFriends()) {
      friend.removeFriend(name);
    }

    people.remove(name);
    return true;
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is undirected and unweighted)
   * 
   * If either Person does not exist, Person IS ADDED and then edge is created. No exception is
   * thrown.
   *
   * If the edge exists in the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 
   * 1. neither Person is null 
   * 2. both Persons are in the graph 
   * 3. the edge is not in the graph
   * 
   * @param name1 - the first vertex
   * @param name2 - the second vertex
   * @return true if edge was added, false already existed or not added
   */
  @Override
  public boolean addEdge(Person name1, Person name2) {

    // Check for bad inputs
    if (name1 == null || name2 == null) {
      return false;
    }

    // Not in network
    if (!people.contains(name1)) {
      people.add(name1);
    }
    if (!people.contains(name2)) {
      people.add(name2);
    }

    // Edge exists
    if (name1.getFriends().contains(name2) || name2.getFriends().contains(name1)) {
      return false;
    }

    name1.addFriend(name2);
    name2.addFriend(name1);
    return true;
  }

  /**
   * Remove the edge from name1 to name2 from this graph. (edge is undirected and unweighted) If
   * either vertex does not exist, or if an edge from name1 to name2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 
   * 1. neither Person is null 
   * 2. both Persons are in the graph 
   * 3. the edge from name1 to name2 is in the graph
   * 
   * @param name1 - the first person
   * @param name2 - the second person
   */
  @Override
  public boolean removeEdge(Person name1, Person name2) {

    // Check for bad inputs
    if (name1 == null || name2 == null) {
      return false;
    }
    if (!people.contains(name1) || !people.contains(name2)) { // At least one user not in graph
      return false;
    }
    if (!name1.getFriends().contains(name2) && !name2.getFriends().contains(name1)) { // Not in
      return true;
    }

    name1.removeFriend(name2);
    name2.removeFriend(name1);

    return true;
  }

  /**
   * returns all nodes in graph as Set
   * 
   * @return Set<Person> - Set containing all People objects in graph
   */
  @Override
  public Set<Person> getAllNodes() {
    Set<Person> peopleSet = new HashSet<Person>(); // creates Set to store people in graph

    // iterates through List of Person objects and adds to Set
    for (int i = 0; i < people.size(); i++) {
      peopleSet.add(people.get(i));

    }

    return peopleSet;
  }

  /**
   * retrieves Person object corresponding to name parameter
   * 
   * @param name - String name of Person object
   * @return Person - node in graph corresponding with name if exists, else null
   */
  @Override
  public Person getNode(String name) {
    // iterates through list of People objects
    for (int i = 0; i < people.size(); i++) {
      // checks if Person name matches name parameter
      if (people.get(i).getName().equals(name)) {
        return people.get(i);
      }

    }

    return null;
  }

  /**
   * retrieves Person object's friends (adjacent vertices)
   * 
   * @param name - Person object to retrieve friends of
   * @return Set<Person> - contains adjacent vertices of parameter Object
   */
  @Override
  public Set<Person> getNeighbors(Person name) {
    return name.getFriends();

  }

}
