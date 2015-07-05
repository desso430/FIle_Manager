package com.sap.idm.learning.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import transport_objects.commands.Commands;
import transport_objects.request.Request;
import transport_objects.response.Response;

public class Download extends ServerOperation {

	private static final String FILE_ALREADY_EXISTS = " File has been already downloaded";
	private File directoryToDownload = null;
	private byte[] buffer = new byte[BUFFER_SIZE];
	private File file;
	private String fileName;
	
	@Override
	public void executeOperation(List<String> arguments) {
	    int argumentsNumber = arguments.size();
        boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_ONE, ARGUMENTS_TWO, argumentsNumber);
        
        if(areArgumentsEnough) { 	
        	fileName= arguments.get(ARGUMENTS_ZERO);  
        	setDirectoryToDownload(arguments);
        	file = createNewFile();
        	
        	if(file != null) {
        		sendRequest();   			 		
    			receiveResponse();
        	}
        }		
	}
	
	@Override
	public void sendRequest() {
		Request downloadRequest = new Request(Commands.download, fileName);
		transmitRequest(downloadRequest);
	}

	@Override
	public void receiveResponse() {
		Response response = getResponse();
		
		if(isConnectedToServer()) {		
			if(response.isRequestExecuted()) {	
				long fileSize = response.getFileSize();		
    			boolean isDownloaded = download(fileSize);	
    			System.out.println( isDownloaded? OPERATION_SUCCEEDED : OPERATION_FAILED );		 			
			} else {
				System.out.println(" Request was denied: " + response.getExecutionStatus() + "    " + response.getDetails());
			}	
		}
	}
		
	private boolean download(long fileSize) {	  	 
		 OutputStream outputStream = null;
		 InputStream inputStream = null;
		 
		 System.out.println(" Download file...");
		 try {
			inputStream = ServerConnection.getInputStream();
			outputStream = new FileOutputStream(file);
				
			 while(fileSize > 0) {
			   int data = inputStream.read(buffer);

				if(data < BUFFER_SIZE) {
				     byte[] buff = Arrays.copyOf(buffer, data);
				     outputStream.write(buff);
				} else {
				     outputStream.write(buffer);  
				} 	
				
				fileSize -= data;
			}  		  
		 } catch(IOException ex) {
		    ex.printStackTrace();
		    return false;
		 } finally{
		    closeStream(outputStream);            
		 }
	  return true;
	 }

	private File createNewFile() {
		File newFile = new File(directoryToDownload, fileName);			  
		 if(!newFile.exists()) {
			 try {
				 newFile.createNewFile();
			} catch (IOException e) {
				 e.printStackTrace();
			}			 			
		 } else {
			System.out.println(FILE_ALREADY_EXISTS);
			return null;
		 }
		return newFile;
	}		
	
	private void setDirectoryToDownload(List<String> arguments) {
		int argumentsCount = arguments.size();
		
		if(argumentsCount == ARGUMENTS_TWO) {
			String directory = arguments.get(ARGUMENTS_ONE);
			directoryToDownload = new File(directory);
		} else {
			directoryToDownload = DEFAULT_DOWNLOAD_FOLDER;
		}	
	}
}
