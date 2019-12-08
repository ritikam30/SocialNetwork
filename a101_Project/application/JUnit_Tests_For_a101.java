package application;

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
    Person node1 = new Person("usr1");
    Person node2 = new Person("usr2");
    Person node3 = new Person("usr3");
    Person node4 = new Person("usr4");
    graph.addEdge(node1,node2);
    graph.addEdge(node3,node1);
    graph.addEdge(node1,node4);
    Set<Person> set = graph.getNeighbors(node1);
    Iterator<Person> itr = set.iterator();
    if (!set.contains(node2) || !set.contains(node3) || !set.contains(node4)) {
        fail("Error: getNeighbors returns incorrect output");
      }
   }

  // End tests for Graph.java

  // Begin tests for SocialNetwork.java

  @Test
  public void test025_add_friend_null_user1() {
    if(network.addFriends(null,"usr2")){
      fail("Error: Network incorrectly returned true for addition of a null user");
    }

  }

  @Test
  public void test026_add_friend_null_user2() {
if(network.addFriends("usr1",null)){
      fail("Error: Network incorrectly returned true for addition of a null user");
    }
  }

  @Test
  public void test027_add_friend_both_null() {
if(network.addFriends(null,null)){
      fail("Error: Network incorrectly returned true for addition of a null user");
    }
  }

  @Test
  public void test028_add_friend_user1_not_in_network() {
    network.addUser("usr2");
    if(!network.addFriends("usr1","usr2")){
      fail("Error: Network did not implicity add the nonexistent user and add an edge between the two nodes");
    }
  }

  @Test
  public void test029_add_friend_user2_not_in_network() {
    network.addUser("usr1");
    if(!network.addFriends("usr1","usr2")){
      fail("Error: Network did not implicity add the nonexistent user and add an edge between the two nodes");
    }
  }

  @Test
  public void test030_add_friend_both_users_not_in_network() {
  if(!network.addFriends("usr1","usr2"))
      fail("Error: Network did not implicity add the nonexistent user and add an edge between the two nodes");
  }

  @Test
  public void test031_add_friends() {
    Person node2 = new Person("usr2");
    Person node3 = new Person("usr3");
    network.addFriends("usr1","usr2");
    network.addFriends("usr3","usr1");
    Set<Person> set = graph.getFriends("usr1");
    if(!set.contains(node2) || !set.contains(node3))
      fail("Error: Network did not add friends correctly");

  }

  @Test
  public void test032_remove_friend_null_user1() {
    
  if(network.removeFriends(null,"usr2"))
      fail("Error: Network did not return false when a friendship between null nodes was removed");
  }

  @Test
  public void test033_remove_friend_null_user2() {
if(network.removeFriends("usr1",null))
      fail("Error: Network did not return false when a friendship between null nodes was removed");
  }

  @Test
  public void test034_remove_friend_both_null() {
if(network.removeFriends(null,null))
      fail("Error: Network did not return false when a friendship between null nodes was removed");
  }

  @Test
  public void test035_remove_friend_user1_not_in_network() {
    network.addUser("usr2");
    if(network.removeFriends("usr1","usr2"))
      fail("Error: Network did not return false when a friendship between nonexistent nodes was removed");
  
  }

  @Test
  public void test036_remove_friend_user2_not_in_network() {
    network.addUser("usr1");
    if(network.removeFriends("usr1","usr2"))
      fail("Error: Network did not return false when a friendship between nonexistent nodes was removed");
  }


  @Test
  public void test037_remove_friend_neither_in_network() {
    if(network.removeFriends("usr1","usr2"))
      fail("Error: Network did not return false when a friendship between nonexistent nodes was removed");
  }

  @Test
  public void test038_remove_friends() {
    network.addUser("usr1");
    Person node2 = new Person("usr2");
    network.addFriends("usr1","usr2");
    network.removeFriends("usr2","usr1");
    Set<Person> set = graph.getFriends("usr1");
    if(set.contains(node2))
      fail("Error: Network did not remove friendship correctly");
    
  }

  @Test
  public void test039_add_null_user() {
  if(network.addUser(null))
      fail("Error: Network incorrectly returned true for the addition of a null user");
  }

  @Test
  public void test040_add_user_already_in_network() {
    network.addUser("usr1");
    if(network.addUser("usr1"))
      fail("Error: Network incorrectly returned true for the addition of a duplicate user");
  }

  @Test
  public void test041_add_user() {
    Person node = new Person("usr1");
    Person node2 = new Person("usr2");
    network.addUser("usr1");
    network.addUser("usr2");
    Set<Person> set = network.graph.getAllNodes();
    if(!set.contains(node) || !set.contains(node2))
      fail("Error: Network did not add user correctly");
  }

  @Test
  public void test042_remove_null_user() {
    if(network.removeUser(null))
      fail("Error: Network incorrectly returned true for removal of a null user");

  }

  @Test
  public void test043_remove_user_not_in_network() {
  if(network.removeUser("usr1"))
      fail("Error: Network incorrectly returned true for removal of a nonexistent user");
  }

  @Test
  public void test044_remove_user() {
    Person node = new Person("usr1");
    network.addUser("usr1");
    network.addUser("usr2");
    network.removeUser("usr1")
    Set<Person> set = network.graph.getAllNodes();
    if(set.contains(node))
      fail("Error: Network did not remove user correctly");
  }

  @Test
  public void test045_get_friends_of_null_user() {
    try{
      network.getFriends(null);
      fail("Error: Network did not throw an exception when getting friends of a null user");
    }catch(IllegalArgumentException u){
    }
  }

  @Test
  public void test046_get_friends_user_not_in_network() {
    try{
      network.getFriends("usr1");
      fail("Error: Network did not throw an exception when getting friends of a nonexistent user");
    }catch(UserNotFoundException u){
    }
  }

  @Test
  public void test047_get_friends() {
    Person node1 = new Person("usr2");
    Person node2 = new Person("usr3");
    Person node3 = new Person("usr4");
    network.addUser("usr1");
    network.addUser("usr2");
    network.addUser("usr3");
    network.addUser("usr4");
    network.addFriends("usr1","usr2");
    network.addFriends("usr3","usr1");
    network.addFriends("usr1","usr4");
    Set<Person> set = network.getFriends("usr1");
    if(!set.contains(node1) || !set.contains(node2) || !set.contains(node3) )
      fail("Error: Network's getFriends() returned incorrect output");
  }

  @Test
  public void test048_get_mutual_null_user1() {
 try{
      network.getMutualFriends(null,"usr2");
      fail("Error: Network did not throw an exception when getting friends of a null user");
    }catch(IllegalArgumentException u){
    }
  }

  @Test
  public void test049_get_mutual_null_user2() {
try{
      network.getMutualFriends("usr1",null);
      fail("Error: Network did not throw an exception when getting friends of a null user");
    }catch(IllegalArgumentException u){
    }
  }

  @Test
  public void test050_get_mutual_both_null() {
try{
      network.getMutualFriends(null,null);
      fail("Error: Network did not throw an exception when getting friends of a null user");
    }catch(IllegalArgumentException u){
    }
  }

  @Test
  public void test051_get_mutual_user1_not_in_network() {
    network.add("usr2");
    try{
      network.getMutualFriends("usr1","usr2");
      fail("Error: Network did not throw an exception when getting friends of a nonexistent user");
    }catch(UserNotFoundException u){
    }
  }

  @Test
  public void test052_get_mutual_user2_not_in_network() {
  network.add("usr1");
    try{
      network.getMutualFriends("usr1","usr2");
      fail("Error: Network did not throw an exception when getting friends of a nonexistent user");
    }catch(UserNotFoundException u){
    }
  }

  @Test
  public void test053_get_mutual_neither_in_network() {
  try{
      network.getMutualFriends("usr1","usr2");
      fail("Error: Network did not throw an exception when getting friends of nonexistent users");
    }catch(UserNotFoundException u){
    }
  }

  @Test
  public void test054_get_mutual() {
    Person node2 = new Person("usr3");
    Person node3 = new Person("usr4");
    Person node4 = new Person("usr5");
    network.addUser("usr1");
    network.addUser("usr2");
    network.addUser("usr3");
    network.addUser("usr4");
    network.addUser("usr5");
    network.addFriends("usr1","usr2");
    network.addFriends("usr3","usr1");
    network.addFriends("usr3","usr2");
    network.addFriends("usr2","usr4");
    network.addFriends("usr1","usr4");
    network.addFriends("usr5","usr1");
    Set<Person> set = network.getMutualFriends("usr1","usr2");
    if(!set.contains(node2) || !set.contains(node3) || set.contains(node4) )
      fail("Error: Network's getMutualFriends() returns incorrect output");

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

  }

  @Test
  public void test066_get_connected_many_users_no_firends() {

  }

  @Test
  public void test067_get_connected_multiple_components_with_connections() {

  }


  // End tests for SocialNetwork.java



}
