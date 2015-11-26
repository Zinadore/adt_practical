package model;

import java.io.IOException;

import Collections.ListException;
import Collections.ListInterface;
import utility.BooleanHolder;

/**
 * 
 * @author Charalampidis Georgio
 *
 */
public interface IServer
{
	/**
	 * Starts the server and creates new authenticatedUsers, connectedDevices and ipAddresses.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void startServer() throws IOException, ClassNotFoundException;
	/**
	 * Stops the server and empties authenticatedUsers, connectedDevices and ipAddresses.
	 * @throws IOException
	 */
	public void stopServer() throws IOException;
	/**
	 * Adds a user to the complete user list.
	 * @param username Username of the user.
	 * @param password Password of the user.
	 * @throws IdentityException
	 */
	public void addUser(String username, String password) throws IdentityException;
	/**
	 * Removes a user from the complete user list. If the user is currently connected to the network, it also disconnects them.
	 * @param username Username of the user.
	 * @throws IdentityException
	 */
	public void removeUser(String username) throws IdentityException;
	/**
	 * Connects a user to the network.
	 * @param username Username of the user.
	 * @param password Password of the user.
	 * @param hostname Hostname of the device the user connected from.
	 * @throws IdentityException
	 * @throws ServerException
	 */
	public void connectUser(String username, String password, String hostname) throws IdentityException, ServerException;
	/**
	 * Attempts to ping a user in the network.
	 * @param pingInfo IP address or Hostname of the user being pinged.
	 * @param pingType Defines whether pingInfo is an IP address or a Hostname.
	 * @return boolean true if ping was successful and false if it was unsuccessful.
	 */
	public boolean ping(String pingInfo, PingType pingType);
	/**
	 * Returns whether the server is up or down.
	 * @return BooleanHolder state of the server.
	 */
	public BooleanHolder getState();
	/**
	 * Asks Identity Provider for the complete user list.
	 * @return ListInterface<User> List of all users.
	 */
	public ListInterface<User> getAllUsers();
	/**
	 * Disconnects a user from the connected users (authenticatedUsers) list.
	 * @param username Username of the user.
	 * @param hostname Hostname of the device the user is connected from.
	 * @throws ListException
	 */
	public void disconnectUser(String username, String hostname) throws ListException; 
	/**
	 * Returns a list of all the currently connected devices.
	 * @return ListInterface<INetworkDevice> List of connected devices.
	 */
	public ListInterface<INetworkDevice> getConnectedDevices();
}
