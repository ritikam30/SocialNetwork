package application;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Defines the methods required for Social Network data type
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
public interface SocialNetworkADT {
	/**
	 * Given two stings, attempts to add a friendship between the given users. If
	 * one or both users are not in the network, it adds them to the network in the
	 * process.
	 * 
	 * @param friend1 - name of the first friend
	 * @param friend2 - name of the second friend
	 * @return true if friendship was added, false otherwise.
	 */
	public boolean addFriends(String friend1, String friend2);

	/**
	 * Given two names, attempts to remove the friendship between the two users.
	 * Returns true if successful otherwise false.
	 * 
	 * @param friend1 - name of the first friend
	 * @param friend2 - name of the second friend
	 * @return true if friendship was removed, false otherwise.
	 */
	public boolean removeFriends(String friend1, String friend2);

	/**
	 * Given the name of a user, attempts to add the user to the network.
	 * 
	 * @param user - name of user to add to the network
	 * @return true if specified user was added, false otherwise
	 */
	public boolean addUser(String user);

	/**
	 * Given a user's name, removes that user from the network.
	 * 
	 * @param user - user to remove
	 * @return true if removed successfully, otherwise false;
	 */
	public boolean removeUser(String user);

	/**
	 * returns Set of adjacent nodes to Person obj associated w/ passed in String
	 * parameter
	 * 
	 * @return Set<Person> - adjacent nodes to Person obj w/ name in var user
	 * @throws UserNotFoundException
	 */
	public Set<Person> getFriends(String user)
			throws UserNotFoundException, IllegalArgumentException;

	/**
	 * Given the names of two users, returns a set of their mutual friends. This can
	 * be a potentially empty list.
	 * 
	 * @param user1 - One of two users to check for mutual friends between
	 * @param user2 - The other user to check for mutual friends between
	 * @return Set<Person> of mutual friends between the specified users
	 * @throws UserNotFoundException, IllegalArgumentException
	 */
	public Set<Person> getMutualFriends(String user1, String user2) throws UserNotFoundException;

	/**
	 * Given the name of two users, finds the shortest path between those two users.
	 * Returns a list with the element at the first index representing the source
	 * user, and the element at the end representing the target user. If there is
	 * not path between the two users, this method returns null.
	 * 
	 * @param source - Name of starting user for the shortest path
	 * @param target - Name of the target user for the shortest path
	 * @return a list representing the shortest path between two users if it exists,
	 *         else null
	 * @throws UserNotFoundException, IllegalArgumentException
	 */
	public List<Person> getShortestPath(String user1, String user2) throws UserNotFoundException;

	/**
	 * This method creates a Graph object for each Person in the network which
	 * contains all connected components to that person, including friends and
	 * friendships, and then adds that Graph to a final Set of Graphs
	 * 
	 * @returns a Set of Graphs of all connected components in the network
	 */
	public Set<Graph> getConnectedComponents();

	/**
	 * Given a text file, reads each line as a command to perform an action within
	 * the network.
	 * 
	 * @param fileName - file to read the commands from.
	 */
	public void loadFromFile(File fileName);

	/**
	 * Writes to a text file the CLI commands needed to create this network.
	 * 
	 * @param fileName - text file to write to
	 */
	public void saveToFile(File fileName);
}
