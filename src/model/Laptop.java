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
public class Laptop implements IComputer {
    
    private String hostName;
    private String ip;

    public Laptop(String hostName, String ip) {
        this.hostName = hostName;
        this.ip = ip;
    }
    
    public String getHostName() {
        return hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
    	this.ip = ip;
    }
}
