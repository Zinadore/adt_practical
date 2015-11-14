package model;

public interface INetworkDevice {
	public String getHostname();
	public void setHostname(String hostname);
	public String getIp();
	public void setIp(String newIp);
	public String getUserName();
	public void changeUser(User user);
}
