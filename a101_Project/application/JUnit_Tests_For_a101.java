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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

/**
 * Tests all relevant classes in application package
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
public class JUnit_Tests_For_a101 extends TestCase {

	static Graph graph;
	static SocialNetwork network;

	@BeforeEach
	public void setUp() throws Exception {
		graph = new Graph();
		network = new SocialNetwork();
	}

	@AfterEach
	public void tearDown() throws Exception {
		graph = null;
		network = null;
	}

	// Begin tests for Graph.java
        /**
	 * checks if false is correctly returned on the addition of a null node
	 */
	@Test
	public void test000_add_null_node() {
		Person node = null;
		if (graph.addNode(node))
			fail("Error: Graph did not return false on the addition of a null node");

	}
	
        /**
	 * checks if false is correctly returned on the addition of a duplicate node
	 */
	@Test
	public void test001_add_node_already_in_graph() {
		Person node = new Person("usr1");
		graph.addNode(node);
		if (graph.addNode(node))
			fail("Error: Graph did not return false on the addition of a duplicate node");
	}
	
        /**
	 * checks if node is correctly added in the graph
	 */
	@Test
	public void test002_just_add_a_node() {
		Person node = new Person("usr1");
		graph.addNode(node);
		if (graph.getNode("usr1") == null)
			fail("Error: Graph did not add node correctly");

	}

	/**
	 * checks if many nodes are added correctly in the graph
	 */
	@Test
	public void test003_add_many_nodes() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		Person node3 = new Person("usr3");
		graph.addNode(node1);
		graph.addNode(node2);
		graph.addNode(node3);
		if (graph.getNode("usr1") == null || graph.getNode("usr2") == null
				|| graph.getNode("usr3") == null)
			fail("Error: Graph did not add multiple nodes correctly");
	}

	/**
	 * checks if false is correctly returned on the removal of a null node
	 */
	@Test
	public void test004_remove_null_node() {
		Person node = null;
		if (graph.removeNode(node))
			fail("Error: Graph did not return false when a null node was removed");

	}

	/**
	 * checks if false is correctly returned on the removal of a nonexistent node
	 */
	@Test
	public void test005_remove_node_not_in_graph() {
		Person node = new Person("usr1");
		if (graph.removeNode(node))
			fail("Error: Graph did not return false when a nonexistent node was removed");

	}

	/**
	 * checks if after removal of a node all the edges containing that node are also removed
	 */
	@Test
	public void test006_remove_node_check_edges() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		Person node3 = new Person("usr3");
		graph.addEdge(node1,node2);
		graph.addEdge(node1,node3);
		graph.removeNode(node3);
		if (!(graph.getNode("usr3") == null) || graph.getNeighbors(node1).contains(node3))
			fail("Error: Graph did not remove node correctly");
		
	}

	/**
	 * checks if Graph implicitly adds a node not in the graph when user tried to add an edge
	 */
	@Test
	public void test007_add_edge_user1_not_in_graph() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addNode(node2);
		if (!graph.addEdge(node1, node2))
			fail("Error: Graph did not add a node implicitly when user tried to add an edge");

	}

	/**
	 * checks if Graph implicitly adds a node not in the graph when user tried to add an edge
	 */
	@Test
	public void test008_add_edge_user2_not_in_graph() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addNode(node1);
		if (!graph.addEdge(node1, node2))
			fail("Error: Graph did not add a node implicitly when user tried to add an edge");

	}

	/**
	 * checks if Graph implicitly adds nodes not in the graph when user tried to add an edge
	 */
	@Test
	public void test009_add_edge_neither_user_in_graph() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		if (!graph.addEdge(node1, node2))
			fail("Error: Graph did not add the nodes implicitly when user tried to add an edge");
	}

	/**
	 * checks if graph returns false when user tries to add an edge between null nodes
	 */
	@Test
	public void test010_add_edge_null_user1() {
		Person node1 = null;
		Person node2 = new Person("usr2");
		if (graph.addEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to add an edge between null nodes");
	}

	/**
	 * checks if graph returns false when user tries to add an edge between null nodes
	 */
	@Test
	public void test011_add_edge_null_user2() {
		Person node1 = new Person("usr1");
		Person node2 = null;
		if (graph.addEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to add an edge between null nodes");
	}

	/**
	 * checks if graph returns false when user tries to add an edge between null nodes
	 */
	@Test
	public void test012_add_edge_both_null_users() {
		Person node1 = null;
		Person node2 = null;
		if (graph.addEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to add an edge between null nodes");
	}

	/**
	 * checks if graph returns false when user tries to add duplicate edges
	 */
	@Test
	public void test013_add_existing_edge() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addEdge(node1, node2);
		if (graph.addEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to add a duplicate");
	}

	/**
	 * checks if graph correctly adds edges between valid nodes
	 */
	@Test
	public void test014_add_edge_between_existing_users() {
		boolean pass = false;
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addNode(node1);
		graph.addNode(node2);
		if (!graph.addEdge(node1, node2))
			fail("Error: Graph did not return true on addition of edge between valid nodes");

		Set<Person> set = graph.getNeighbors(node1);
		Iterator<Person> itr = set.iterator();
		while (itr.hasNext()) {
			if (itr.next().getName().equals("usr2")) {
				pass = true;
				break;
			} else
				continue;

		}
		if (!pass) {
			fail("Error: edge added unsuccessfully");
		}

	}

	/**
	 * checks if graph returns false when user tries to remove edge between null nodes
	 */
	@Test
	public void test015_remove_edge_null_user1() {
		Person node1 = null;
		Person node2 = new Person("usr2");

		if (graph.removeEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to remove edge between null nodes");
	}

	/**
	 * checks if graph returns false when user tries to remove edge between null nodes
	 */
	@Test
	public void test016_remove_edge_null_user2() {
		Person node1 = new Person("usr1");
		Person node2 = null;

		if (graph.removeEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to remove edge between null nodes");
	}

	/**
	 * checks if graph returns false when user tries to remove edge between null nodes
	 */
	@Test
	public void test017_remove_edge_both_users_null() {
		Person node1 = null;
		Person node2 = null;

		if (graph.removeEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to remove edge between null nodes");
	}

	/**
	 * checks if graph returns false when user tries to remove edge between nonexistent nodes
	 */
	@Test
	public void test018_remove_edge_user1_not_in_graph() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addNode(node2);
		if (graph.removeEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to remove edge between connecting a nonexistent node");
	}

	/**
	 * checks if graph returns false when user tries to remove edge between nonexistent nodes
	 */
	@Test
	public void test019_remove_edge_user2_not_in_graph() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addNode(node1);
		if (graph.removeEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to remove edge between connecting a nonexistent node");
	}

	/**
	 * checks if graph returns false when user tries to remove edge between nonexistent nodes
	 */
	@Test
	public void test020_remove_edge_neither_user_in_graph() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");

		if (graph.removeEdge(node1, node2))
			fail("Error: Graph did not return false when user tried to remove edge between connecting a nonexistent node");
	}

	/**
	 * checks if correctly removes an edge
	 */
	@Test
	public void test021_remove_edge_and_check() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addEdge(node1, node2);
		graph.removeEdge(node1, node2);

		Set<Person> set = graph.getNeighbors(node1);
		Iterator<Person> itr = set.iterator();
		while (itr.hasNext()) {
			if (itr.next().getName().equals("usr2")) {
				fail("Error: edge removed unsuccessfully");
			}
		}

	}

	/**
	 * checks if getAllNodes() returns correct output
	 */
	@Test
	public void test022_check_get_all_nodes() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		graph.addNode(node1);
		graph.addNode(node2);

		Set<Person> set = graph.getAllNodes();
		if (!set.contains(node1) || !set.contains(node2)) {
			fail("Error: getAllNodes returned incorrect output");
		}

	}

	/**
	 * checks if getNode() returns correct output
	 */
	@Test
	public void test023_get_single_node() {
		Person node1 = new Person("usr1");
		graph.addNode(node1);
		if (!graph.getNode("usr1").getName().equals(node1.getName()))
			fail("Error: getNode returned incorrect output");
	}

	/**
	 * checks if getNeighbors() returns correct output
	 */
	@Test
	public void test024_get_neighbors() {
		Person node1 = new Person("usr1");
		Person node2 = new Person("usr2");
		Person node3 = new Person("usr3");
		Person node4 = new Person("usr4");
		graph.addEdge(node1, node2);
		graph.addEdge(node3, node1);
		graph.addEdge(node1, node4);
		Set<Person> set = graph.getNeighbors(node1);
		if (!set.contains(node2) || !set.contains(node3) || !set.contains(node4)) {
			fail("Error: getNeighbors returns incorrect output");
		}
	}

	// End tests for Graph.java

	// Begin tests for SocialNetwork.java

	/**
	 * checks if false is correctly returned on the addition of a null friend
	 */
	@Test
	public void test025_add_friend_null_user1() {
		if (network.addFriends(null, "usr2")) {
			fail("Error: Network incorrectly returned true for addition of a null user");
		}

	}
	
        /**
	 * checks if false is correctly returned on the addition of a null friend
	 */
	@Test
	public void test026_add_friend_null_user2() {
		if (network.addFriends("usr1", null)) {
			fail("Error: Network incorrectly returned true for addition of a null user");
		}
	}

	/**
	 * checks if false is correctly returned on the addition of a null friend
	 */
	@Test
	public void test027_add_friend_both_null() {
		if (network.addFriends(null, null)) {
			fail("Error: Network incorrectly returned true for addition of a null user");
		}
	}

	/**
	 * checks if node gets implicitly added as a user on the addition of a nonexistent friend
	 */
	@Test
	public void test028_add_friend_user1_not_in_network() {
		network.addUser("usr2");
		if (!network.addFriends("usr1", "usr2")) {
			fail("Error: Network did not implicity add the nonexistent user and add an edge between the two nodes");
		}
	}

	/**
	 * checks if node gets implicitly added as a user on the addition of a nonexistent friend
	 */
	@Test
	public void test029_add_friend_user2_not_in_network() {
		network.addUser("usr1");
		if (!network.addFriends("usr1", "usr2")) {
			fail("Error: Network did not implicity add the nonexistent user and add an edge between the two nodes");
		}
	}

	/**
	 * checks if nodes get implicitly added as users on the addition of nonexistent friends
	 */
	@Test
	public void test030_add_friend_both_users_not_in_network() {
		if (!network.addFriends("usr1", "usr2"))
			fail("Error: Network did not implicity add the nonexistent user and add an edge between the two nodes");
	}

	/**
	 * checks if friendship is created successfully
	 */
	@Test
	public void test031_add_friends() {
		network.addFriends("usr1", "usr2");
		network.addFriends("usr3", "usr1");
		Set<Person> set;
		try {
			set = network.getFriends("usr1");
			for (Person friend : set) {
				if (!friend.getName().equals("usr2") && !friend.getName().equals("usr3")) {
					fail("Error: Network did not add friends correctly");
				}
			}
		} catch (IllegalArgumentException e) {
			fail("Fail: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown.");
		}
	}

	/**
	 * checks if false is correctly returned on the removal of a null friend
	 */
	@Test
	public void test032_remove_friend_null_user1() {

		if (network.removeFriends(null, "usr2"))
			fail("Error: Network did not return false when a friendship between null nodes was removed");
	}

	/**
	 * checks if false is correctly returned on the removal of a null friend
	 */
	@Test
	public void test033_remove_friend_null_user2() {
		if (network.removeFriends("usr1", null))
			fail("Error: Network did not return false when a friendship between null nodes was removed");
	}

	/**
	 * checks if false is correctly returned on the removal of a null friend
	 */
	@Test
	public void test034_remove_friend_both_null() {
		if (network.removeFriends(null, null))
			fail("Error: Network did not return false when a friendship between null nodes was removed");
	}

	/**
	 * checks if false is correctly returned on the removal of a nonexistent friend
	 */
	@Test
	public void test035_remove_friend_user1_not_in_network() {
		network.addUser("usr2");
		if (network.removeFriends("usr1", "usr2"))
			fail("Error: Network did not return false when a friendship between nonexistent nodes was removed");

	}

	/**
	 * checks if false is correctly returned on the removal of a nonexistent friend
	 */
	@Test
	public void test036_remove_friend_user2_not_in_network() {
		network.addUser("usr1");
		if (network.removeFriends("usr1", "usr2"))
			fail("Error: Network did not return false when a friendship between nonexistent nodes was removed");
	}

	/**
	 * checks if false is correctly returned on the removal of a nonexistent friend
	 */
	@Test
	public void test037_remove_friend_neither_in_network() {
		if (network.removeFriends("usr1", "usr2"))
			fail("Error: Network did not return false when a friendship between nonexistent nodes was removed");
	}

	/**
	 * checks if friendship is removed correctly
	 */
	@Test
	public void test038_remove_friends() {
		network.addFriends("usr1", "usr2");
		network.removeFriends("usr2", "usr1");
		Set<Person> set;
		try {
			set = network.getFriends("usr1");
			if (set.contains(network.getUserByName("usr2")))
				fail("Error: Network did not add friends correctly");
		} catch (IllegalArgumentException e) {
			fail("Fail: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown.");
		}

	}

	/**
	 * checks if false is correctly returned on the addition of a null user
	 */
	@Test
	public void test039_add_null_user() {
		if (network.addUser(null))
			fail("Error: Network incorrectly returned true for the addition of a null user");
	}

	/**
	 * checks if false is correctly returned on the addition of a duplicate user
	 */
	@Test
	public void test040_add_user_already_in_network() {
		network.addUser("usr1");
		if (network.addUser("usr1"))
			fail("Error: Network incorrectly returned true for the addition of a duplicate user");
	}

	/**
	 * checks if a user is added correctly in the network
	 */
	@Test
	public void test041_add_user() {
		network.addUser("usr1");
		network.addUser("usr2");
		Set<Person> set = network.getAllUsersInNetwork();
		if (!set.contains(network.getUserByName("usr1"))
				|| !set.contains(network.getUserByName("usr2")))
			fail("Error: Network did not add user correctly");
	}

	/**
	 * checks if false is correctly returned on the removal of a null user
	 */
	@Test
	public void test042_remove_null_user() {
		if (network.removeUser(null))
			fail("Error: Network incorrectly returned true for removal of a null user");

	}

	/**
	 * checks if false is correctly returned on the removal of a nonexistent user
	 */
	@Test
	public void test043_remove_user_not_in_network() {
		if (network.removeUser("usr1"))
			fail("Error: Network incorrectly returned true for removal of a nonexistent user");
	}

	/**
	 * checks if a user is removed correctly from the network
	 */
	@Test
	public void test044_remove_user() {
		Person node = new Person("usr1");
		network.addUser("usr1");
		network.addUser("usr2");
		network.removeUser("usr1");
		Set<Person> set = graph.getAllNodes();
		if (set.contains(node))
			fail("Error: Network did not remove user correctly");
	}

	/**
	 * checks if excpetion is correctly thrown on getFriends() call with null user
	 */
	@Test
	public void test045_get_friends_of_null_user() {
		try {
			network.getFriends(null);
			fail("Error: Network did not throw an exception when getting friends of a null user");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown");
		} catch (IllegalArgumentException u) {
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getFriends() call with nonexistent user
	 */
	@Test
	public void test046_get_friends_user_not_in_network() {
		try {
			network.getFriends("usr1");
			fail("Error: Network did not throw an exception when getting friends of a nonexistent user");
		} catch (UserNotFoundException u) {
		}
	}

	/**
	 * checks if getFriends() returns correct output
	 */
	@Test
	public void test047_get_friends() {
		network.addFriends("usr1", "usr2");
		network.addFriends("usr3", "usr1");
		network.addFriends("usr1", "usr4");
		Set<Person> set;
		try {
			set = network.getFriends("usr1");
			if (!set.contains(network.getUserByName("usr2"))
					|| !set.contains(network.getUserByName("usr3"))
					|| !set.contains(network.getUserByName("usr4")))
				fail("Error: Network's getFriends() returned incorrect output");
		} catch (IllegalArgumentException | UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown");
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getMutualFriends() call with null user
	 */
	@Test
	public void test048_get_mutual_null_user1() {

		try {
			network.getMutualFriends(null, "usr2");
			fail("Error: Network did not throw an exception when getting friends of a null user");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown");
		} catch (IllegalArgumentException u) {
			// This is pass
		}

	}

	/**
	 * checks if excpetion is correctly thrown on getMutualFriends() call with null user
	 */
	@Test
	public void test049_get_mutual_null_user2() {
		try {
			network.getMutualFriends("usr2", null);
			fail("Error: Network did not throw an exception when getting friends of a null user");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown");
		} catch (IllegalArgumentException u) {
			// This is pass
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getMutualFriends() call with null users
	 */
	@Test
	public void test050_get_mutual_both_null() {
		try {
			network.getMutualFriends(null, null);
			fail("Error: Network did not throw an exception when getting friends of a null user");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown");
		} catch (IllegalArgumentException u) {
			// This is pass
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getMutualFriends() call with nonexistent user
	 */
	@Test
	public void test051_get_mutual_user1_not_in_network() {
		network.addUser("usr2");
		try {
			network.getMutualFriends("usr1", "usr2");
			fail("Error: Network did not throw an exception when getting friends of a nonexistent user");
		} catch (UserNotFoundException u) {
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getMutualFriends() call with nonexistent user
	 */
	@Test
	public void test052_get_mutual_user2_not_in_network() {
		network.addUser("usr1");
		try {
			network.getMutualFriends("usr1", "usr2");
			fail("Error: Network did not throw an exception when getting friends of a nonexistent user");
		} catch (UserNotFoundException u) {
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getMutualFriends() call with nonexistent users
	 */
	@Test
	public void test053_get_mutual_neither_in_network() {
		try {
			network.getMutualFriends("usr1", "usr2");
			fail("Error: Network did not throw an exception when getting friends of nonexistent users");
		} catch (UserNotFoundException u) {
		}
	}

	/**
	 * checks if getMutualFriends() call returns correct output
	 */
	@Test
	public void test054_get_mutual() {
		network.addFriends("usr1", "usr3");
		network.addFriends("usr1", "usr4");
		network.addFriends("usr1", "usr5");
		network.addFriends("usr2", "usr3");
		network.addFriends("usr2", "usr4");
		network.addFriends("usr2", "usr5");
		Set<Person> set;
		try {
			set = network.getMutualFriends("usr1", "usr2");
			if (!set.contains(network.getUserByName("usr3"))
					|| !set.contains(network.getUserByName("usr4"))
					|| !set.contains(network.getUserByName("usr5")))
				fail("Error: Network's getMutualFriends() returns incorrect output");
		} catch (IllegalArgumentException e) {
			fail("Fail: Unexpected exception thrown");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception thrown");
		}

	}

	/**
	 * checks if excpetion is correctly thrown on getShortestPath() call with null user
	 */
	@Test
	public void test055_get_shortest_user1_null() {
		try {
			network.getShortestPath(null, "1");
			fail("Error: did not throw expected exception.");
		} catch (IllegalArgumentException e) {
			// This is pass
		} catch (UserNotFoundException e) {
			fail("Error: Unexpected exception thrown.");
		}

	}
	
        /**
	 * checks if excpetion is correctly thrown on getShortestPath() call with null user
	 */
	@Test
	public void test056_get_shortest_user2_null() {
		try {
			network.getShortestPath("1", null);
			fail("Error: did not throw expected exception.");
		} catch (IllegalArgumentException e) {
			// This is pass
		} catch (UserNotFoundException e) {
			fail("Error: Unexpected exception thrown.");
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getShortestPath() call with null user
	 */
	@Test
	public void test057_get_shortest_both_users_null() {
		try {
			network.getShortestPath(null, null);
			fail("Error: did not throw expected exception.");
		} catch (IllegalArgumentException e) {
			// This is pass
		} catch (UserNotFoundException e) {
			fail("Error: Unexpected exception thrown.");
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getShortestPath() call with nonexistent user
	 */
	@Test
	public void test058_get_shortest_user1_not_in_network() {
		network.addUser("1");

		try {
			network.getShortestPath("2", "1");
			fail("Error: did not throw expected exception.");
		} catch (IllegalArgumentException e) {
			fail("Error: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			// This is pass
		}

	}

	/**
	 * checks if excpetion is correctly thrown on getShortestPath() call with nonexistent user
	 */
	@Test
	public void test059_get_shortest_user2_not_in_network() {
		network.addUser("1");

		try {
			network.getShortestPath("1", "2");
			fail("Error: did not throw expected exception.");
		} catch (IllegalArgumentException e) {
			fail("Error: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			// This is pass
		}
	}

	/**
	 * checks if excpetion is correctly thrown on getShortestPath() call with nonexistent users
	 */
	@Test
	public void test060_get_shortest_neither_user_in_network() {
		try {
			network.getShortestPath("1", "2");
			fail("Error: did not throw expected exception.");
		} catch (IllegalArgumentException e) {
			fail("Error: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			// This is pass
		}
	}

	/**
	 * checks if getShortestPath() call has correct implementation
	 */
	@Test
	public void test061_get_shortest_one_hop() {
		network.addFriends("1", "2");

		try {
			List<Person> path = network.getShortestPath("1", "2");
			if (!path.get(0).getName().equals("1") || !path.get(1).getName().equals("2")) {
				fail("Fail: Expected path 1->2  but got " + path.get(0).getName() + "->"
						+ path.get(1).getName());
			}
		} catch (IllegalArgumentException e) {
			fail("Error: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			fail("Error: Unexpected exception thrown.");
		}

	}

	/**
	 * checks if getShortestPath() call has correct implementation
	 */
	@Test
	public void test062_get_shortest_multiple_hops() {
		String expected = "12345";
		String actual = "";
		network.addFriends("1", "2");
		network.addFriends("2", "3");
		network.addFriends("3", "4");
		network.addFriends("4", "5");

		try {
			List<Person> path = network.getShortestPath("1", "5");
			for (Person hop : path) {
				actual += hop.getName();
			}
			if (!expected.equals(actual)) {
				fail("Fail: Expected path " + expected + " but got path " + actual);
			}
		} catch (IllegalArgumentException e) {
			fail("Error: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			fail("Error: Unexpected exception thrown.");
		}

	}

	/**
	 * checks if getShortestPath() call correctly handles a cycle
	 */
	@Test
	public void test063_get_shortest_cycle_exists() {

		network.addFriends("1", "2");
		network.addFriends("2", "3");
		network.addFriends("3", "4");
		network.addFriends("3", "5");
		network.addFriends("4", "6");
		network.addFriends("6", "1");
		network.addFriends("5", "7");
		network.addFriends("7", "2");
		network.addFriends("7", "8");

		String expected = "1278";
		String actual = "";

		try {
			List<Person> path = network.getShortestPath("1", "8");
			for (Person hop : path) {
				actual += hop.getName();
			}
			if (!expected.equals(actual)) {
				fail("Fail: Expected path " + expected + " but got path " + actual);
			}
		} catch (IllegalArgumentException e) {
			fail("Error: Unexpected exception thrown.");
		} catch (UserNotFoundException e) {
			fail("Error: Unexpected exception thrown.");
		}

	}

	/**
	 * checks if getShortestPath() call correctly handles a case when no path exists
	 */
	@Test
	public void test064_get_shortest_no_path_exists() {
		network.addFriends("1", "2");
		network.addFriends("2", "3");
		network.addFriends("3", "4");
		network.addUser("5");

		try {
			List<Person> path = network.getShortestPath("1", "5");
			if (path != null) {
				fail("Fail: Shortest path returned a path when none should exist");
			}
		} catch (IllegalArgumentException e) {
			fail("Fail: Unexpected exception was thrown");
		} catch (UserNotFoundException e) {
			fail("Fail: Unexpected exception was thrown");
		}

	}

	/**
	 * checks if getConnectedComponents() correctly handles the case with a single connected component
	 */
	@Test
	public void test065_get_connected_single_component() {
		network.addUser("1");

		Set<Graph> components = network.getConnectedComponents();
		if (components.size() != 1) {

			for (Graph connected : components) {
				Set<Person> elements = connected.getAllNodes();
				System.out.print("COMPONENT: ");
				for (Person node : elements) {
					System.out.print(node.getName() + " ");
				}
				System.out.println();
			}
			fail("Fail: expected 1 seperate connected components but actuals is "
					+ components.size());
		}

	}

	/**
	 * checks if getConnectedComponents() correctly handles the case with a many users having no friends
	 */
	@Test
	public void test066_get_connected_many_users_no_firends() {
		network.addUser("1");
		network.addUser("2");
		network.addUser("3");
		network.addUser("4");

		Set<Graph> components = network.getConnectedComponents();
		if (components.size() != 4) {

			for (Graph connected : components) {
				Set<Person> elements = connected.getAllNodes();
				System.out.print("COMPONENT: ");
				for (Person node : elements) {
					System.out.print(node.getName() + " ");
				}
				System.out.println();
			}
			fail("Fail: expected 4 seperate connected components but actuals is "
					+ components.size());
		}

	}

	/**
	 * checks if getConnectedComponents() correctly handles the case having multiple components with connections
	 */
	@Test
	public void test067_get_connected_multiple_components_with_connections() {
		// Add a few clusters of connected components

		// Cluster 1
		network.addFriends("1", "2");
		network.addFriends("1", "3");
		network.addFriends("3", "4");
		network.addFriends("3", "5");
		network.addFriends("2", "6");
		network.addFriends("2", "7");

		// Cluster 2
		network.addFriends("A", "B");
		network.addFriends("A", "C");
		network.addFriends("C", "D");
		network.addFriends("C", "E");
		network.addFriends("B", "F");
		network.addFriends("B", "G");

		Set<Person> numbers = new HashSet<>();
		Set<Person> letters = new HashSet<>();
		Character startLetter = 'A';
		Character startNum = '1';

		for (int i = 0; i < 7; ++i) {
			numbers.add(new Person(Character.toString(startNum + i)));
			letters.add(new Person(Character.toString(startLetter + i)));
		}

		Set<Graph> components = network.getConnectedComponents();
		if (components.size() != 2) {

			for (Graph connected : components) {
				Set<Person> elements = connected.getAllNodes();
				System.out.print("COMPONENT: ");
				for (Person node : elements) {
					System.out.print(node.getName() + " ");
				}
				System.out.println();
			}
			fail("Fail: expected 2 seperate connected components but actuals is "
					+ components.size());
		}

		ArrayList<Graph> componentsList = new ArrayList<>();
		componentsList.addAll(components);

		for (Graph component : componentsList) {
			if (letters.containsAll(component.getAllNodes())
					&& numbers.containsAll(component.getAllNodes())) {
				fail("Fail: components did not contain expected elements");
			}
		}

	}

	// End tests for SocialNetwork.java

}
