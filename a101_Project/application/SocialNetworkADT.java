package application;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Defines the methods required for Social Network data type
 */
public interface SocialNetworkADT {

  public boolean addFriends(String friend1, String friend2);
  
  public boolean removeFriends(String friend1, String friend2);
  
  public boolean addUser(String user);
  
  public boolean removeUser(String user);
  
  public Set<Person> getFriends(String user) throws UserNotFoundException;
  
  public Set<Person> getMutualFriends(String user1, String user2) throws UserNotFoundException;
  
  public List<Person> getShortestPath(String user1, String user2) throws UserNotFoundException;
  
  public Set<Graph> getConnectedComponents();
  
  public void loadFromFile(File fileName);
  
  public void saveToFile(File fileName);
}
