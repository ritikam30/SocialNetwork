package application;

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

  }

  @Test
  public void test001_add_node_already_in_graph() {

  }

  @Test
  public void test002_just_add_a_node() {

  }

  @Test
  public void test003_add_many_nodes() {

  }

  @Test
  public void test004_remove_null_node() {

  }

  @Test
  public void test005_remove_node_not_in_graph() {

  }

  @Test
  public void test006_remove_node_check_edges() {

  }

  @Test
  public void test007_add_edge_user1_not_in_graph() {

  }

  @Test
  public void test008_add_edge_user2_not_in_graph() {

  }

  @Test
  public void test009_add_edge_neither_user_in_graph() {

  }

  @Test
  public void test010_add_edge_null_user1() {

  }

  @Test
  public void test011_add_edge_null_user2() {

  }

  @Test
  public void test012_add_edge_both_null_users() {

  }

  @Test
  public void test013_add_existing_edge() {

  }

  @Test
  public void test014_add_edge_between_existing_users() {

  }

  @Test
  public void test015_remove_edge_null_user1() {

  }

  @Test
  public void test016_remove_edge_null_user2() {

  }

  @Test
  public void test017_remove_edge_both_users_null() {

  }

  @Test
  public void test018_remove_edge_user1_not_in_graph() {

  }

  @Test
  public void test019_remove_edge_user2_not_in_graph() {

  }

  @Test
  public void test020_remove_edge_neither_user_in_graph() {

  }

  @Test
  public void test021_remove_edge_and_check() {

  }

  @Test
  public void test022_check_get_all_nodes() {

  }

  @Test
  public void test023_get_single_node() {

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
  public void test066_get_connected_single_component() {

  }

  @Test
  public void test067_get_connected_many_users_no_firends() {

  }

  @Test
  public void test068_get_connected_multiple_components_with_connections() {

  }


  // End tests for SocialNetwork.java



}
