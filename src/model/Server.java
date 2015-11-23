package model;

import java.io.IOException;
import java.util.Random;

import Collections.ArrayBasedList;
import Collections.ListException;
import Collections.ListInterface;
import Collections.StackInterface;
import Collections.StackReferenceBased;
import utility.BooleanHolder;


public class Server implements IServer
					
{
        
	private int maxSize = 5; //Change to select the amount of users the network will support.
	private BooleanHolder state; //Server: On or Off. Change it to BooleanHolder when ready.
	private IIdentityProvider idp;
	private ListInterface<User> authenticatedUsers; 
	private ListInterface<INetworkDevice> connectedDevices; //Change it to ListInterface<NetworkDevice> when ready.
	private StackInterface ipAdresses; //May change it to a queue in the future.

	public Server(IIdentityProvider idp)
	{
		state = new BooleanHolder(false);
		authenticatedUsers = new ArrayBasedList<User>(); 
		connectedDevices = new ArrayBasedList<INetworkDevice>(); 
		ipAdresses = new StackReferenceBased();
		fillIps(ipAdresses); 
		this.idp = idp;
	}

	public void startServer() throws IOException, ClassNotFoundException //Starts the server.
	{
		state.set(true);
		idp.loadUsers();
		authenticatedUsers = new ArrayBasedList<User>(); 
		connectedDevices = new ArrayBasedList<INetworkDevice>(); 
		ipAdresses = new StackReferenceBased();
		fillIps(ipAdresses);
	}
	

	public void stopServer() throws IOException //Stops the server.
	{
		idp.saveUsers();
		authenticatedUsers = null;
		connectedDevices = null;
		ipAdresses = null;
		state.set(false);
	}

	public void addUser(String username, String password) throws IdentityException
	{
		idp.addUser(username,password);
	}

	public void removeUser(String username) throws IdentityException
	{
		if (isAlreadyAuthenticated(username))
		{
			authenticatedUsers.removeSingle(u -> u.getUsername().equals(username));
			connectedDevices.removeSingle(l -> l.getUserName().equals(username));
		}
		idp.remove(username);
	}

	private boolean isAlreadyAuthenticated(String username)
	{
		return authenticatedUsers.exists(u -> u.getUsername().equals(username));
	}

	private boolean hostnameExists(String hostname)
	{
		return connectedDevices.exists(l -> l.getHostname().equals(hostname));
	}

	private boolean ipExists(String ip)
	{
		return connectedDevices.exists(l -> l.getIp().equals(ip));
	}

	public void connectUser(String username, String password, String hostname) throws IdentityException, ServerException
	{
		if(authenticatedUsers.size() >= maxSize) {
			throw new ServerException("Server is already at full capacity. (" + maxSize + ")");
		}
		if(isAlreadyAuthenticated(username)) {
			throw new ServerException("User is already connected");
		}
		if (!hostnameExists(hostname))
		{
			User user = idp.authenticate(username, password);
			INetworkDevice dev = new Laptop(hostname, giveIp());
			dev.changeUser(user);
			connectedDevices.append(dev);
			authenticatedUsers.append(user);
		}
		else
		{
			throw new ServerException("This hostname is already connected");
		}
		
	}


	public boolean ping(String pingInfo, PingType pingType)
	{
		switch (pingType)
		{
			case IP:
				return ipExists(pingInfo);
			case HOSTNAME:
				return hostnameExists(pingInfo);
			default: //can i not use default on switch?
                                return false;
		}
	}

	public BooleanHolder getState()
	{
		return state;
	}
	
	public ListInterface<User> getAllUsers() {
		return idp.getAllUsers();
	}
	
	public void disconnectUser(String username, String hostname) throws ListException
	{
		authenticatedUsers.removeSingle(u -> u.getUsername().equals(username));
		INetworkDevice dev = connectedDevices.findSingle(l -> l.getHostname().equals(hostname));
		ipAdresses.push(dev.getIp());
		connectedDevices.removeSingle(l -> l.getHostname().equals(hostname));
	}

	public ListInterface<INetworkDevice> getConnectedDevices() 
	{
		return this.connectedDevices;
	}

	private String giveIp()
	// Laptop asks for an IP. ipAdresses stack pops an available IP and returns it.
	{
		return (String)ipAdresses.pop();
	}

	private void fillIps(StackInterface ipAdresses)
	{
		Random rand = new Random();
		int value;
		String randIp;
		
		for (int i=0; i<maxSize; i++)
		{
			value = rand.nextInt(100)+100;
			randIp = "192.168.10." + Integer.toString(value);
			ipAdresses.push(randIp);
		}
		this.ipAdresses = ipAdresses;
	}
	
	public String toString()
	{
		String str = "\nServer: ";
		if (state.get())
		{
			str += "On ";
		}
		else
		{
			str += "Off ";
		}
		str += "\nAvailable slots left: " + Integer.toString(maxSize - authenticatedUsers.size()); 
		
		return str;
	}
}