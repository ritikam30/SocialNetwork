package application;

import java.util.Set;

public class Person {
  
  private String name;
  private Set<Person> friends;
  
  public Person(String name) {
    this.name = name;
    this.friends = new HashSet<Person>();
    
  }
  
  public String getName() {
    return name;
  }
  
  public Set<Person> getFriends() {
    return friends;
  }
  
  public void addFriend(Person friend) {
      friends.add(friend);
  }
  
  public void removeFriend(Person friend) {
      friends.remove(friend);
  }
  
}
