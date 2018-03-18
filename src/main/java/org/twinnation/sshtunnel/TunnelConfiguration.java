package org.twinnation.sshtunnel;

import java.io.File;


public class TunnelConfiguration {
	
	/** The IP of the server you're making a tunnel to */
	private String serverIp;
	/** The username of the SSH user */
	private String sshUsername;
	/** The password of the SSH user. You cannot use both this and sshPrivateKey at the same time */
	private String sshPassword;
	/** The file leading to the SSH key. You cannot use both this and sshPassword at the same time */
	private File sshPrivateKey;
	/** The port you're making the tunnel toward */
	private int serverPort;
	/** The port you will connect to in order to access the server */
	private int localPort;
	
	
	private TunnelConfiguration(String serverIp, String sshUsername, String sshPassword, File sshPrivateKey, int serverPort, int localPort) {
		this.serverIp = serverIp;
		this.sshUsername = sshUsername;
		this.sshPassword = sshPassword;
		this.sshPrivateKey = sshPrivateKey;
		this.serverPort = serverPort;
		this.localPort = localPort;
	}
	
	
	public TunnelConfiguration(String serverIp, String sshUsername, File sshPrivateKey, int serverPort, int localPort) {
		this(serverIp, sshUsername, null, sshPrivateKey, serverPort, localPort);
	}
	
	
	public TunnelConfiguration(String serverIp, String sshUsername, String sshPassword, int serverPort, int localPort) {
		this(serverIp, sshUsername, sshPassword, null, serverPort, localPort);
	}
	
	
	public TunnelConfiguration() {}
	
	
	public String getServerIp() {
		return serverIp;
	}
	
	
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	
	public String getSshUsername() {
		return sshUsername;
	}
	
	
	public void setSshUsername(String sshUsername) {
		this.sshUsername = sshUsername;
	}
	
	
	public String getSshPassword() {
		return sshPassword;
	}
	
	
	public void setSshPassword(String sshPassword) {
		if (sshPrivateKey != null) {
			sshPrivateKey = null;
		}
		this.sshPassword = sshPassword;
	}
	
	
	public File getSshPrivateKey() {
		return sshPrivateKey;
	}
	
	
	public void setSshPrivateKey(File sshPrivateKey) {
		if (sshPassword != null) {
			sshPassword = null;
		}
		this.sshPrivateKey = sshPrivateKey;
	}
	
	
	public int getServerPort() {
		return serverPort;
	}
	
	
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	
	public int getLocalPort() {
		return localPort;
	}
	
	
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	
	
	public boolean isUsingPassword() {
		return (sshPassword != null);
	}
	
}
