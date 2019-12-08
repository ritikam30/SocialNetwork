package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

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

  @Test
  public void test000_add_null_node() {
    Person node = null;
    if (graph.addNode(node))
      fail("Error: Graph did not return false on the addition of a null node");

  }

  @Test
  public void test001_add_node_already_in_graph() {
    Person node = new Person("usr1");
    graph.addNode(node);
    if (graph.addNode(node))
      fail("Error: Graph did not return false on the addition of a duplicate node");
  }

  @Test
  public void test002_just_add_a_node() {
    Person node = new Person("usr1");
    graph.addNode(node);
    if (graph.getNode("usr1") == null)
      fail("Error: Graph did not add node correctly");

  }

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

  @Test
  public void test004_remove_null_node() {
    Person node = null;
    if (graph.removeNode(node))
      fail("Error: Graph did not return false when a null node was removed");


  }

  @Test
  public void test005_remove_node_not_in_graph() {
    Person node = new Person("usr1");
    if (graph.removeNode(node))
      fail("Error: Graph did not return false when a nonexistent node was removed");

  }


  @Test
  public void test006_remove_node_check_edges() {
    // TODO: complete
  }

  @Test
  public void test007_add_edge_user1_not_in_graph() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    graph.addNode(node2);
    if (!graph.addEdge(node1, node2))
      fail("Error: Graph did not add a node implicitly when user tried to add an edge");

  }

  @Test
  public void test008_add_edge_user2_not_in_graph() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    graph.addNode(node1);
    if (!graph.addEdge(node1, node2))
      fail("Error: Graph did not add a node implicitly when user tried to add an edge");

  }


  @Test
  public void test009_add_edge_neither_user_in_graph() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    if (!graph.addEdge(node1, node2))
      fail("Error: Graph did not add the nodes implicitly when user tried to add an edge");
  }

  @Test
  public void test010_add_edge_null_user1() {
    Person node1 = null;
    Person node2 = new Person("usr2");
    if (graph.addEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to add an edge between null nodes");
  }

  @Test
  public void test011_add_edge_null_user2() {
    Person node1 = new Person("usr1");
    Person node2 = null;
    if (graph.addEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to add an edge between null nodes");
  }

  @Test
  public void test012_add_edge_both_null_users() {
    Person node1 = null;
    Person node2 = null;
    if (graph.addEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to add an edge between null nodes");
  }

  @Test
  public void test013_add_existing_edge() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    graph.addEdge(node1, node2);
    if (graph.addEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to add a duplicate");
  }

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

  @Test
  public void test015_remove_edge_null_user1() {
    Person node1 = null;
    Person node2 = new Person("usr2");

    if (graph.removeEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to remove edge between null nodes");
  }

  @Test
  public void test016_remove_edge_null_user2() {
    Person node1 = new Person("usr1");
    Person node2 = null;

    if (graph.removeEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to remove edge between null nodes");
  }

  @Test
  public void test017_remove_edge_both_users_null() {
    Person node1 = null;
    Person node2 = null;

    if (graph.removeEdge(node1, node2))
      fail("Error: Graph did not return false when user tried to remove edge between null nodes");
  }

  @Test
  public void test018_remove_edge_user1_not_in_graph() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    graph.addNode(node2);
    if (graph.removeEdge(node1, node2))
      fail(
          "Error: Graph did not return false when user tried to remove edge between connecting a nonexistent node");
  }

  @Test
  public void test019_remove_edge_user2_not_in_graph() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    graph.addNode(node1);
    if (graph.removeEdge(node1, node2))
      fail(
          "Error: Graph did not return false when user tried to remove edge between connecting a nonexistent node");
  }

  @Test
  public void test020_remove_edge_neither_user_in_graph() {
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");

    if (graph.removeEdge(node1, node2))
      fail(
          "Error: Graph did not return false when user tried to remove edge between connecting a nonexistent node");
  }

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

  @Test
  public void test023_get_single_node() {
    Person node1 = new Person("usr1");
    graph.addNode(node1);
    if (!graph.getNode("usr1").getName().equals(node1.getName()))
      fail("Error: getNode returned incorrect output");
  }

  @Test
  public void test024_get_neighbors() {

  }

  // End tests for Graph.java

  // Begin tests for SocialNetwork.java

  @Test
  public void test025_add_friend_null_user1() {

  }

  @Test
  public void test026_add_friend_null_user2() {

  }

  @Test
  public void test027_add_friend_both_null() {

  }

  @Test
  public void test028_add_friend_user1_not_in_network() {

  }

  @Test
  public void test029_add_friend_user2_not_in_network() {

  }

  @Test
  public void test030_add_friend_both_users_not_in_network() {

  }

  @Test
  public void test031_add_friends() {

  }

  @Test
  public void test032_remove_friend_null_user1() {

  }

  @Test
  public void test033_remove_friend_null_user2() {

  }

  @Test
  public void test034_remove_friend_both_null() {

  }

  @Test
  public void test035_remove_friend_user1_not_in_network() {

  }

  @Test
  public void test036_remove_friend_user2_not_in_network() {

  }


  @Test
  public void test037_remove_friend_neither_in_network() {

  }

  @Test
  public void test038_remove_friends() {

  }

  @Test
  public void test039_add_null_user() {

  }

  @Test
  public void test040_add_user_already_in_network() {

  }

  @Test
  public void test041_add_user() {

  }

  @Test
  public void test042_remove_null_user() {

  }

  @Test
  public void test043_remove_user_not_in_network() {

  }

  @Test
  public void test044_remove_user() {

  }

  @Test
  public void test045_get_friends_of_null_user() {

  }

  @Test
  public void test046_get_friends_user_not_in_network() {

  }

  @Test
  public void test047_get_friends() {

  }

  @Test
  public void test048_get_mutual_null_user1() {

  }

  @Test
  public void test049_get_mutual_null_user2() {

  }

  @Test
  public void test050_get_mutual_both_null() {

  }

  @Test
  public void test051_get_mutual_user1_not_in_network() {

  }

  @Test
  public void test052_get_mutual_user2_not_in_network() {

  }

  @Test
  public void test053_get_mutual_neither_in_network() {

  }

  @Test
  public void test054_get_mutual() {

  }

  @Test
  public void test055_get_shortes_user1_null() {

  }

  @Test
  public void test056_get_shortest_user2_null() {

  }

  @Test
  public void test057_get_shortest_both_users_null() {

  }

  @Test
  public void test058_get_shortest_user1_not_in_network() {

  }

  @Test
  public void test059_get_shortest_user2_not_in_network() {

  }

  @Test
  public void test060_get_shortest_neither_user_in_network() {

  }

  @Test
  public void test061_get_shortest_one_hop() {

  }

  @Test
  public void test062_get_shortes_multiple_hops() {

  }

  @Test
  public void test063_get_shortest_cycle_exists() {

  }

  @Test
  public void test064_get_shortest_no_path_exists() {

  }

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
      fail("Fail: expected 1 seperate connected components but actuals is " + components.size());
    }

  }

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
      fail("Fail: expected 4 seperate connected components but actuals is " + components.size());
    }

  }

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
      fail("Fail: expected 2 seperate connected components but actuals is " + components.size());
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
