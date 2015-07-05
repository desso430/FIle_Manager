package com.sap.idm.learning.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import transport_objects.commands.Commands;
import transport_objects.request.Request;
import transport_objects.response.Response;

public class Upload extends ServerOperation {

	private byte[] buffer = new byte[BUFFER_SIZE];
	private static final int EOF = -1;
	private File file;

	@Override
	public void executeOperation(List<String> arguments) {	
        int argumentsNumber = arguments.size();
        boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_ONE, argumentsNumber);
        
		if(areArgumentsEnough) {
			String pathToFile = arguments.get(ARGUMENTS_ZERO);
			file = new File(pathToFile);
			if(isValidFile(file)) {
				sendRequest();
                receiveResponse();
			} else {
				System.out.println(FILE_NOT_FOUND_MSG);
			}
		}
	}

	@Override
	public void sendRequest() {
		Request uploadRequest = new Request(Commands.upload, file.getName(), file.length());
		transmitRequest(uploadRequest);
	}

	@Override
	public void receiveResponse() {
		Response response = getResponse();
		
		if(isConnectedToServer()) {
			if(response.isRequestExecuted()) {	
				boolean isFileUploaded = upload();
				System.out.println( isFileUploaded? OPERATION_SUCCEEDED : OPERATION_FAILED );		 		
			} else {
				System.out.println(" Request was denied: " + response.getExecutionStatus() + "    " + response.getDetails());
			}	
		}	
	}

	public boolean upload() {		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		System.out.println(" Uploading file....");
		try {
			inputStream = new FileInputStream(file);
			outputStream = ServerConnection.getOutputStream();
        
	        while(true) {
	        	int data = inputStream.read(buffer);
	        	
	        	if(data != EOF) {
	        		if(data < BUFFER_SIZE) {
	        			byte[] buff = Arrays.copyOf(buffer, data);
	        			outputStream.write(buff);
	        		} else {
	        			outputStream.write(buffer);  
	        		}      	 		
	        	}  else { 
	        		outputStream.flush();
	        		break;
	        	}
	        }
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		} finally {
			closeStream(inputStream);
		}
		return true;
	}
}
