package model;

import java.io.Serializable;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
import Collections.StackInterface;
import Collections.StackReferenceBased;

@SuppressWarnings("serial")
public class Server implements Serializable
{
	private boolean state;
	private IIdentityProvider idp;
	private ListInterface<User> authenticatedUsers;
	private ListInterface<INetworkDevice> connectedLaptops;
	private StackInterface ipAdresses;

	Server(IIdentityProvider idp)
	{
		state = false; 
		authenticatedUsers = new ReferenceBasedList<User>();
		connectedLaptops = new ReferenceBasedList<INetworkDevice>();
		ipAdresses = new StackReferenceBased();
		ipAdresses.push("192.168.10.153");
		ipAdresses.push("192.168.10.171");
		ipAdresses.push("192.168.10.122");
		ipAdresses.push("192.168.10.189");
		ipAdresses.push("192.168.10.141");
		this.idp = idp;
	}

	public void startServer()
	{
		state = true;
	}

	public void addUser(String username, String password) throws IdentityException
	{
		idp.addUser(username,password);
	}

	public void removeUser(String username) throws IdentityException
	{
		idp.remove(username);
	}

	public boolean isAuthenticated(String username)
	{
		return authenticatedUsers.exists(u -> u.getUsername().equals(username));
	}

	public boolean hostnameExists(String hostname)
	{
		return connectedLaptops.exists(l -> l.getHostname().equals(hostname));
	}

	public void connectUser(String username, String password, String hostname) throws IdentityException
	{
		if (!isAuthenticated(username))
		{
			if (!hostnameExists(hostname))
			{
				connectedLaptops.append(new Laptop(hostname, giveIp()));
				authenticatedUsers.append(idp.authenticate(username, password));
			}
			else
			{
				authenticatedUsers.append(idp.authenticate(username,password));
			}

		}
		else
		{
			//throw new ServerException -- you are not allowed to log in...
		}
	}

	public boolean isStarted()
	{
		return state;
	}

	public void disconnectUser(String username, String hostname)
	{
		authenticatedUsers.removeSingle(u -> u.getUsername().equals(username));
		ipAdresses.push(connectedLaptops.findSingle(l -> l.getHostname().equals(hostname)).getIp());
		connectedLaptops.removeSingle(l -> l.getHostname().equals(hostname));
	}

	public void viewComputers()
	{
		for (int i=0; i < connectedLaptops.size(); i++)
		{
			if(connectedLaptops.get(i)!=null)
			{
				System.out.println(connectedLaptops.get(i));
			}
		}
	}

	public void stopServer()
	{
		state = false;
	}

	public String giveIp()
	// Laptop asks for an IP. ipAdresses stack pops an available IP and returns it.
	{
		return (String)ipAdresses.pop();
	}

	public String toString()
	{
		String str = "Server: ";
		if (isStarted())
		{
			str += "On ";
		}
		else
		{
			str += "Off ";
		}

		// add more things!

		return str;
	}

}