package com.sap.idm.learning.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerConnection {
	
	static ObjectInputStream socketIn = null;
	static ObjectOutputStream socketOut = null;
	private static Socket connection = null;
	private  String host = null;
	private  int port;
	
	
	public ServerConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
    void connect()  {		
		try {
			System.out.println(" Connecting to server...");
			connection = new Socket(host, port);
		  } catch (IOException e) {
			  System.out.println(" Can't connect to surver");
			  e.printStackTrace();
	          return;
		  }	
		
		System.out.println(" Connected to server successfully");
		setStreams();
	}
    
    public static void disconnect() {
    	try {
			connection.close();
		} catch (SocketException s) {
			System.out.println(" Cannot close the connection because there I/O operations on this connection! ");
		} catch (IOException e) {
			System.out.println(" I/O error while closing the connection! ");
		}
    	System.out.println(" Disconnect successfully ");
	}
   
	void setStreams()  {
		try {
			socketIn =  new ObjectInputStream(getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		try {
			socketOut = new ObjectOutputStream(getOutputStream());
			socketOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static InputStream getInputStream() throws IOException {
		return connection.getInputStream();
	}

	public static OutputStream getOutputStream() throws IOException {
		return connection.getOutputStream();
	}

	public static ObjectInputStream getSocketIn() {
		return socketIn;
	}

	public static ObjectOutputStream getSocketOut() {
		return socketOut;
	}
	
}
