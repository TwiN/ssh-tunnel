package org.twinnation.sshtunnel;

import java.sql.*;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class SSHTunnelHandler {
	
	private static Session currentSession = null;
	

	private static Session doSSHTunnelByPassword(String sshUser, String sshPassword, String sshHost, 
		  String remoteHost, int localPort, int remotePort) throws JSchException {
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
	
	
	private static Session doSSHTunnelByKey(String sshUser, String sshKeyPath, String sshHost,
		  String remoteHost, int localPort, int remotePort) throws JSchException {
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
	
	
	public static void createSSHTunnel(TunnelConfiguration config) {
		try {
			if (config.isUsingPassword()) {
				currentSession = doSSHTunnelByPassword(config.getSshUsername(), config.getSshPassword(),
					  config.getServerIp(), "localhost", config.getLocalPort(), config.getServerPort());
			} else {
				currentSession = doSSHTunnelByKey(config.getSshUsername(), config.getSshPrivateKey().getAbsolutePath(),
					  config.getServerIp(), "localhost", config.getLocalPort(), config.getServerPort());
			}
		} catch (JSchException e) {
			e.printStackTrace();
			System.out.println("Failed to create SSH tunnel, disconnecting now");
			if (currentSession.isConnected()) {
				currentSession.disconnect();
			}
			System.exit(1);
		}
	}
	
	
	public static void closeSSHTunnel() {
		currentSession.disconnect();
	}
	
}
