package model;

import java.io.IOException;

import Collections.ListException;
import Collections.ListInterface;
import utility.BooleanHolder;

public interface IServer
{

	public void startServer() throws IOException, ClassNotFoundException;
	public void stopServer() throws IOException;
	public void addUser(String username, String password) throws IdentityException;
	public void removeUser(String username) throws IdentityException;
	public void connectUser(String username, String password, String hostname) throws IdentityException, ServerException;
	public boolean ping(String pingInfo, PingType pingType);
	public BooleanHolder getState();
	public void disconnectUser(String username, String hostname) throws ListException; 
	public ListInterface<INetworkDevice> getConnectedDevices();
}
