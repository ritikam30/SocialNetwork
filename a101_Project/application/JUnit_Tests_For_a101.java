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
  
  

}
