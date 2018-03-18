package org.twinnation.sshtunnel;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class SSHTunnelHandler {
	
	private static Session currentSession = null;
	
	
	/**
	 * Creates the SSH tunnel with the TunnelConfiguration provided
	 */
	public static void createSSHTunnel(TunnelConfiguration config) throws Exception {
		try {
			if (!config.isValid()) {
				throw new Exception("Invalid tunnel configuration");
			}
			if (config.isUsingPassword()) {
				currentSession = doSSHTunnelByPassword(config.getSshUsername(), config.getSshPassword(),
					  config.getServerIp(), "localhost", config.getLocalPort(), config.getServerPort());
			} else {
				currentSession = doSSHTunnelByKey(config.getSshUsername(), config.getSshPrivateKey().getAbsolutePath(),
					  config.getServerIp(), "localhost", config.getLocalPort(), config.getServerPort());
			}
		} catch (JSchException e) {
			System.out.println("Failed to create SSH tunnel, disconnecting now");
			e.printStackTrace();
			if (currentSession.isConnected()) {
				currentSession.disconnect();
			}
		}
	}
	
	
	/**
	 * Closes the SSH tunnel
	 */
	public static void closeSSHTunnel() {
		currentSession.disconnect();
	}
	
	
	/**
	 * Creates the SSH tunnel by using a password
	 */
	private static Session doSSHTunnelByPassword(String sshUser, String sshPassword, String sshHost, String remoteHost,
		  int localPort, int remotePort) throws JSchException {
		final JSch jsch = new JSch();
		Session session = jsch.getSession(sshUser, sshHost, 22);
		session.setPassword(sshPassword);
		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		session.setPortForwardingL(localPort, remoteHost, remotePort);
		return session;
	}
	
	
	/**
	 * Creates the SSH tunnel by using a SSH private key
	 */
	private static Session doSSHTunnelByKey(String sshUser, String sshKeyPath, String sshHost, String remoteHost,
		  int localPort, int remotePort) throws JSchException {
		final JSch jsch = new JSch();
		Session session = jsch.getSession(sshUser, sshHost, 22);
		jsch.addIdentity(sshKeyPath);
		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		session.setPortForwardingL(localPort, remoteHost, remotePort);
		return session;
	}
	
}
