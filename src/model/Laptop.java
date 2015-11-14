/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author arxidios
 */
public class Laptop implements INetworkDevice {
    
    private String hostName;
    private String ip;
    private User user;
    
    public Laptop(String hostname, String ip) {
    	setHostname(hostname);
    	setIp(ip);
    }
    
	@Override
	public String getHostname() {
		return this.hostName;
	}
	@Override
	public void setHostname(String hostname) {
		this.hostName = hostname;
	}
	@Override
	public String getIp() {
		return this.ip;
	}
	@Override
	public void setIp(String newIp) {
		this.ip = newIp;
	}
	@Override
	public String getUserName() {
		if(user != null) {
			return user.getUsername();
		}
		return null;
	}
	@Override
	public void changeUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Laptop))
			return false;
		Laptop other = (Laptop) obj;
		if (hostName == null) {
			if (other.hostName != null)
				return false;
		} else if (!hostName.equals(other.hostName))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Laptop [hostName=" + hostName + ", ip=" + ip + ", user=" + user.getUsername() + "]";
	}
}
