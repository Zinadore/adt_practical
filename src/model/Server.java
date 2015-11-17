package model;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
import Collections.StackInterface;
import Collections.StackReferenceBased;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;//delete after testing is over.


public class Server 
					
{
	public enum pingType { IP, HOSTNAME }; 
	private int maxSize = 5; //Change to select the amount of users the network will support.
	private boolean state; //Server: On or Off. Change it to BooleanHolder when ready.
	private IIdentityProvider idp;
	private ListInterface<User> authenticatedUsers; 
	private ListInterface<INetworkDevice> connectedLaptops; //Change it to ListInterface<NetworkDevice> when ready.
	private StackInterface ipAdresses; //May change it to a queue in the future.

	Server(IIdentityProvider idp)
	{
		state = false;
		authenticatedUsers = new ReferenceBasedList<User>(); //May change it to ArrayBasedList.
		connectedLaptops = new ReferenceBasedList<INetworkDevice>(); //May change it to ArrayBasedList.
		ipAdresses = new StackReferenceBased();
		fillIps(ipAdresses); 
		this.idp = idp;
	}

	public void startServer() //Starts the server.
	{
		state = true;
	}
	

	public void stopServer() throws IOException //Stops the server.
	{
		idp.saveUsers();
		state = false;
	}

	public void addUser(String username, String password) throws IdentityException
	{
		idp.addUser(username,password);
	}

	public void removeUser(String username) throws IdentityException
	{
		idp.remove(username);
	}

	private boolean isAlreadyAuthenticated(String username)
	{
		return authenticatedUsers.exists(u -> u.getUsername().equals(username));
	}

	private boolean hostnameExists(String hostname)
	{
		return connectedLaptops.exists(l -> l.getHostname().equals(hostname));
	}

	private boolean ipExists(String ip)
	{
		return connectedLaptops.exists(l -> l.getIp().equals(ip));
	}

	public void connectUser(String username, String password, String hostname) throws IdentityException
	{
		if (authenticatedUsers.size() < maxSize) //polla ifs. mporw na ta kanw me OR kai an nai pws tha xeiristw ta exceptions?
		{
			if (!isAlreadyAuthenticated(username))
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
		else
		{
			//throw new Exception -- Network is full...
		}
	}


	public boolean ping(String pingInfo, int pingType)
	{
		switch (pingType)
		{
			case 0:
				return ipExists(pingInfo);
			case 1:
				return hostnameExists(pingInfo);
			default: //can i not use default on switch?
				return false;
		}
	}

	public boolean isStarted()
	{
		return state;
	}

	public void disconnectUser(String username, String hostname) //throws ListException??
	{
		authenticatedUsers.removeSingle(u -> u.getUsername().equals(username));
		ipAdresses.push(connectedLaptops.findSingle(l -> l.getHostname().equals(hostname)).getIp());
		connectedLaptops.removeSingle(l -> l.getHostname().equals(hostname));
	}
	
	public String getUsers() //Returns a formatted string with all the connected users
	{
		String str = "";
		for (int i=0; i < authenticatedUsers.size(); i++)
		{
				str += "\n" + authenticatedUsers.get(i).getUsername();
		}
		return str;
	}

	public String getComputers() //Returns a formatted string with all the connected laptops/computers/network devices and their respective IP. need to change the name?
	{
		String str = "";
		for (int i=0; i < connectedLaptops.size(); i++)
		{
				str += "\n" + connectedLaptops.get(i).getHostname();
				str += " - " + connectedLaptops.get(i).getIp();
		}
		return str;
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
	
	public String passwordStrength(String str)
	{
		int size = str.length();
		int passwordStrength = 0;
		String passwordResult;
		boolean hasSixOrMore,hasDigit,hasLowerCase,hasUpperCase,hasSymbol;
		hasSixOrMore = hasDigit = hasLowerCase = hasUpperCase = hasSymbol = false;
		char ch;
	
		int i = 0;
	    while(i < size)
	    {
			ch = str.charAt(i);
			
			if(Character.isDigit(ch) && hasDigit == false)
			{
				hasDigit = true;
				passwordStrength++;
			}
			else if(Character.isLowerCase(ch) && hasLowerCase == false)
			{
				hasLowerCase = true;
				passwordStrength++;
			}
			else if(Character.isUpperCase(ch) && hasUpperCase == false)
			{
				hasUpperCase = true;
				passwordStrength++;
			}
			else
			{
				if(!Character.isLetter(ch) && hasSymbol == false)
				{
					hasSymbol = true;
					passwordStrength++;
				}
					
			}
			i++;
	    }
			
    	if(size >= 8)
		{
			hasSixOrMore = true;
			passwordStrength++;
		}
    	
		switch (passwordStrength)
		{
			case 1:
				passwordResult = "Password Strength: 1 (Very Weak)";
				break;
			case 2:
				passwordResult = "Password Strength: 2 (Weak)";
				break;
			case 3:
				passwordResult = "Password Strength: 3 (Sufficient)";
				break;
			case 4:
				passwordResult = "Password Strength: 4 (Strong)" ;
				break;
			case 5:
				passwordResult = "Password Strength: 5 (Exceptional)";
				break;
			default: 
				passwordResult = "Error in password strength calculation!";
				break;
		}
    	
    	return passwordResult; 
	}

	public String toString()
	{
		String str = "\nServer: ";
		if (isStarted())
		{
			str += "On ";
		}
		else
		{
			str += "Off ";
		}

		str += "\nUsers Connected: " + authenticatedUsers.size();
		str += getUsers();
		str += "\nLaptops Connected: " + connectedLaptops.size();
		str += getComputers();
		str += "\nAvailable slots left: " + Integer.toString(maxSize - authenticatedUsers.size()); 
		
		return str;
	}

	public static void main(String[] args) //Testing
	{
		Server s = new Server(new IdentityProvider(new Serializer<User>()));
		s.startServer();
	   Scanner scan = new Scanner(System.in);
	   System.out.println("Enter your password: ");
	   String str = scan.nextLine();

		System.out.println(s.toString());
		System.out.println("\nPassword Strength " + s.passwordStrength(str));
	}
}