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

/**
 * Exception class for exception thrown when user does not exist in Social Network
 * 
 * @author Erik Tiedt
 * @author Ritika Mittal
 * @author Jared Horwitz
 * @author Keerthy Sudharsan
 * @author Sakuni Egodawatte
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	/**
	 * constructor passes message to be handled by Exception super class
	 * 
	 * @param message - message to return when .getMessage() invoked
	 */
	public UserNotFoundException(String message) {
		super(message);
		
	}
	
}
