package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
