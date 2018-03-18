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
	
	
	/**
	 * Gets the server ip
	 */
	public String getServerIp() {
		return serverIp;
	}
	
	
	/**
	 * Sets the server ip
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	
	/**
	 * Gets the username of the SSH user
	 */
	public String getSshUsername() {
		return sshUsername;
	}
	
	
	/**
	 * Sets the username of the SSH user
	 */
	public void setSshUsername(String sshUsername) {
		this.sshUsername = sshUsername;
	}
	
	
	/**
	 * Gets the SSH user password
	 */
	public String getSshPassword() {
		return sshPassword;
	}
	
	
	/**
	 * Sets the SSH user password
	 * Note: This will automatically set sshPrivateKey to null
	 */
	public void setSshPassword(String sshPassword) {
		if (sshPrivateKey != null) {
			sshPrivateKey = null;
		}
		this.sshPassword = sshPassword;
	}
	
	
	/**
	 * Gets the ssh user private key file
	 */
	public File getSshPrivateKey() {
		return sshPrivateKey;
	}
	
	
	/**
	 * Sets the ssh user private key file
	 * Note: This will automatically set sshPassword to null
	 */
	public void setSshPrivateKey(File sshPrivateKey) {
		if (sshPassword != null) {
			sshPassword = null;
		}
		this.sshPrivateKey = sshPrivateKey;
	}
	
	
	/**
	 * Gets the port of the server you're connecting the tunnel to
	 */
	public int getServerPort() {
		return serverPort;
	}
	
	
	/**
	 * Sets the port of the server you're connecting the tunnel to
	 */
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	
	/**
	 * Gets the port you will locally connect to in order to access the server
	 */
	public int getLocalPort() {
		return localPort;
	}
	
	
	/**
	 * Sets the port you will locally connect to in order to access the server
	 */
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	
	
	/**
	 * Whether the TunnelConfiguration is using a password as method of authentication
	 */
	public boolean isUsingPassword() {
		return (sshPassword != null);
	}
	
	
	public boolean isValid() {
		return serverIp != null && sshUsername != null && (sshPassword != null || sshPrivateKey != null)
			  && serverPort != 0 && localPort != 0;
	}
	
}
