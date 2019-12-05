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

	@Override
	public Set<Person> getNeighbors(Person name) {
		// TODO Sakuni will complete
		return null;
	}

}
