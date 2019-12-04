package application;

import java.io.File;
import java.util.List;
import java.util.Set;

public class SocialNetwork implements SocialNetworkADT {

  @Override
  public boolean addFriends(String friend1, String friend2) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeFriends(String friend1, String friend2) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addUser(String user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeUser(String user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Set<Person> getFriends(String user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Person> getMutualFriends(String user1, String user2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Person> getShortestPath(String user1, String user2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Graph> getConnectedComponents() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void loadFromFile(File fileName) {
    // TODO Auto-generated method stub

  }

  @Override
  public void saveToFile(File fileName) {
    // TODO Auto-generated method stub

  }

}
