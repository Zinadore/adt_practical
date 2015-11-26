package model;

/**
 * A class implementing this interface will be a device able to connect
 * and work with our network.
 * @author Ioannis Boutsikas
 *
 */
public interface INetworkDevice {
	/**
	 * Returns the hostname of the device.
	 * @return a String representing the hostname.
	 */
	public String getHostname();
	
	/**
	 * Sets the hostname of the device to the provided value.
	 * @param hostname the new hostname.
	 */
	public void setHostname(String hostname);
	
	/**
	 * Returns the ip assigned to this device.
	 * @return the ip of the device.
	 */
	public String getIp();
	
	/**
	 * Set the ip of the device to the provided value.
	 * @param newIp the new ip.
	 */
	public void setIp(String newIp);
	
	/**
	 * Returns the username of the User connected to this device.
	 * @return the username of the User matched to this device.
	 */
	public String getUserName();
	
	/**
	 * Changes the current User of the device to the one provided.
	 * @param user the new user.
	 */
	public void changeUser(User user);
}
