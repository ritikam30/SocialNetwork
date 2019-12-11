////////////////////////////////////////////////////////////////
// Title: a3 Social Network
// Authors: Ritika Mittal, Jared Horwitz, Keerthy Sudharsan,
// Sakuni Egodawatte, Erik Tiedt
// Emails: rmittal6@wisc.edu, sudharsan@wisc.edu,
// jhorwitz3@wisc.edu, egodawatte@wisc.edu
// etiedt@wisc.edu
// Lectures: 001, 002
// Description: creates social network visualizer with GUI
// interface
// Files: Main.java, Graph.java, GraphADT.java, Person.java
// SocialNetwork.java, SocialNetworkADT.java, 
// JUnit_Tests_For_a101.java, UserNotFoundException.java,
// application.css
////////////////////////////////////////////////////////////////
package application;

import java.util.Set;

/**
 * A simple graph interface for the purpose of modeling a social network
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
public interface GraphADT {

	/**
	 * Add new Person to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. Person is not null 2. Person is not already in
	 * the graph
	 * 
	 * @param name - the Person to be added
	 */
	public boolean addNode(Person name);

	/**
	 * Remove a Person and all associated edges from the graph.
	 * 
	 * If Person is null or does not exist, method ends without removing a vertex,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. Person is not null 2. Person is in the graph
	 * 
	 * @param name - the person to be removed
	 */
	public boolean removeNode(Person name);

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is undirected and
	 * unweighted)
	 * 
	 * If either Person does not exist, Person IS ADDED and then edge is created. No
	 * exception is thrown.
	 *
	 * If the edge exists in the graph, no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither Person is null 2. both Persons are in
	 * the graph 3. the edge is not in the graph
	 * 
	 * @param name1 - the first vertex
	 * @param name2 - the second vertex
	 */
	public boolean addEdge(Person name1, Person name2);

	/**
	 * Remove the edge from name1 to name2 from this graph. (edge is undirected and
	 * unweighted) If either vertex does not exist, or if an edge from name1 to
	 * name2 does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither Person is null 2. both Persons are in
	 * the graph 3. the edge from name1 to name2 is in the graph
	 * 
	 * @param name1 - the first person
	 * @param name2 - the second person
	 */
	public boolean removeEdge(Person name1, Person name2);

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return a Set<Person> which contains all the vertices in the graph
	 */
	public Set<Person> getAllNodes();

	/**
	 * Given a name, returns the respective node or null if absent
	 * 
	 * @param name - name of the person who's node you're looking for
	 * @return a Person with the specified name, or null if not in the graph
	 */
	public Person getNode(String name);

	/**
	 * Get all the neighbors (adjacent-dependencies) of a vertex
	 * 
	 * For the example graph, A-[B, C] getAdjacentVerticesOf(A) should return [B,
	 * C].
	 * 
	 * In terms of packages, this list contains the immediate dependencies of A and
	 * depending on your graph structure, this could be either the predecessors or
	 * successors of A.
	 * 
	 * @param name - the specified person
	 * @return an Set<Person> of all the adjacent vertices for specified vertex
	 */
	public Set<Person> getNeighbors(Person name);

}
