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

import java.io.File;
import java.util.List;
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
	
	/**
	 * constructor, initializes graph field to new Graph object
	 */
	public SocialNetwork() {
		graph = new Graph();
		
	}
	

	@Override
	public boolean addFriends(String friend1, String friend2) {
		// TODO Keerthy will complete
		return false;
	}

	@Override
	public boolean removeFriends(String friend1, String friend2) {
		// TODO Keerthy will complete
		return false;
	}

	@Override
	public boolean addUser(String user) {
		// TODO Keerthy will complete
		return false;
	}

	@Override
	public boolean removeUser(String user) {
		// TODO Keerthy will complete
		return false;
	}

	@Override
	public Set<Person> getFriends(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getMutualFriends(String user1, String user2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getShortestPath(String user1, String user2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Graph> getConnectedComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadFromFile(File fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToFile(File fileName) {
		// TODO Auto-generated method stub

	}

}
