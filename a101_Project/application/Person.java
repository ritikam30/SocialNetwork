////////////////////////////////////////////////////////////////
// Title: a3 Social Network                                   // 
// Authors: Ritika Mittal,  Jared Horwitz, Keerthy Sudharsan, //
//		   Sakuni Egodawatte, Erik Tiedt					  //
// Emails: rmittal6@wisc.edu, sudharsan@wisc.edu,             //
//		  jhorwitz3@wisc.edu, egodawatte@wisc.edu			  //
//		  etiedt@wisc.edu				  					  //
// Lectures: 001, 002                                         //
// Description: creates social network visualizer with GUI    //
//				interface                                     //
// Files: Main.java, Graph.java, GraphADT.java, Person.java	  //  
//		  SocialNetwork.java, SocialNetworkADT.java			  //
////////////////////////////////////////////////////////////////
package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * creates graph node, a person, in the social network
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
public class Person {
  // fields
  private String name; // store graph node label
  private Set<Person> friends; // store adjacent graph nodes
  
  /**
   * constructor to initialize name and create empty Set to store friends
   * 
   * @param name - label of node
   */
  public Person(String name) {
    this.name = name;
    this.friends = new HashSet<Person>();
  }
  
  /**
   * gets name of person
   * 
   * @return name - field storing person's name
   */
  public String getName() {
    return name;
  }
  
  /**
   * gets adjacent nodes of person
   * 
   * @return Set<Person> - set storing friends
   */
  public Set<Person> getFriends() {
    return friends;
  }
  
  /**
   * adds node to person's adjacent nodes Set
   * 
   * @param friend - Person object to add to Set
   */
  public void addFriend(Person friend) {
      friends.add(friend);
  }
  
  /**
   * removes node from person's adjacent nodes Set
   * 
   * @param friend - Person object to remove from Set
   */
  public void removeFriend(Person friend) {
      friends.remove(friend);
  }
  
}
