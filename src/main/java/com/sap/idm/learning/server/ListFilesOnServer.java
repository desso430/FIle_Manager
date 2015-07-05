package com.sap.idm.learning.server;

import java.util.Iterator;
import java.util.List;

import transport_objects.request.Request;
import transport_objects.response.ListFilesResponse;
import transport_objects.response.Response;
import transport_objects.commands.*;

public class ListFilesOnServer extends ServerOperation {	
	
	@Override
	public void executeOperation(List<String> arguments) {
		sendRequest();
		receiveResponse();
	}

	@Override
	public void sendRequest() {
		Request listFiles = new Request(Commands.ls);		
		transmitRequest(listFiles);	
	}

	@Override
	public void receiveResponse() {
		Response response = getResponse();
		
		if(isConnectedToServer()) {
			if(response instanceof ListFilesResponse) {
				ListFilesResponse listFilesResponse = (ListFilesResponse) response; 		
				List<String> files = listFilesResponse.getFilesName();		
				printFilesName(files); 
			} else {
				System.out.println(response.getExecutionStatus());
			}	
		}	
	}

	private void printFilesName(List<String> files) {
		int count = 1;
		
		for (Iterator<String> it = files.iterator(); it.hasNext();) {
			String fileName = it.next();
			System.out.println((count++) + ". " + fileName);
		}
	}
}
