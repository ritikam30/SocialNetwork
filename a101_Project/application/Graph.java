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
	
	@Override
	public boolean addNode(Person name) {
		// TODO Jared will complete
		return false;
	}

	@Override
	public boolean removeNode(Person name) {
		// TODO Jared will complete
		return false;
	}

	@Override
	public boolean addEdge(Person name1, Person name2) {
		// TODO Jared will complete
		return false;
	}

	@Override
	public boolean removeEdge(Person name1, Person name2) {
		// TODO Jared will complete
		return false;
	}

	@Override
	public Set<Person> getAllNodes() {
		// TODO Sakuni will complete
		return null;
	}

	@Override
	public Person getNode(String name) {
		// TODO Sakuni will complete
		return null;
	}

	@Override
	public Set<Person> getNeighbors(Person name) {
		// TODO Sakuni will complete
		return null;
	}

}
