package com.sap.idm.learning.server;


import com.sap.idm.learning.file_manager.IPromptOperation;
import com.sap.idm.learning.operations.Operation;

import transport_objects.request.Request;
import transport_objects.response.Response;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class ServerOperation extends Operation implements IPromptOperation {
	
	protected static final File DEFAULT_DOWNLOAD_FOLDER = new File("Download");
	protected static String LOST_CONNECTION = " Connection with server was lost ";
	protected static final int BUFFER_SIZE = 40_000;
	private boolean isConnectionLost = false;
	
	protected void transmitRequest(Request request) {	
		try {
			if(!isConnectionLost) {
				getOutputStream().writeObject(request);
				getOutputStream().flush();
			}		
		} catch (Exception e) {
			System.out.println(LOST_CONNECTION);
			isConnectionLost = true;
		}
	}
	
	protected Response getResponse() {	
		Response response = null;
		try {
			if(!isConnectionLost) {
				response = (Response) getInputStream().readObject();
			}					
		} catch (Exception e) {
			System.out.println(LOST_CONNECTION);
			isConnectionLost = true;
		}
		return response;
	}
	
	private ObjectOutputStream getOutputStream() {
		return ServerConnection.getSocketOut();
	}
	
	private ObjectInputStream getInputStream() {
		return ServerConnection.getSocketIn();
	}
	
	protected boolean isConnectedToServer() {
		if(isConnectionLost) {
			return false;
		} else {
			return true;
		}	
	}
	
	public abstract void sendRequest();
	public abstract void receiveResponse();
}
