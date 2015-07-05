package com.sap.idm.learning.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

public class CopyFile extends Operation {
	
	@Override
    public void executeOperation(List<String> arguments) {
		boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_TWO, arguments.size());
		if(areArgumentsEnough) {
			String sourceFile = arguments.get(ARGUMENTS_ZERO);
			String destinationFile = arguments.get(ARGUMENTS_ONE);			
			copyFiles(openFile(sourceFile), openFile(destinationFile));
		}
	}
	
	public void copyFiles(File sourceFile, File destFile) {
		 FileChannel source = null;
		 FileChannel destination = null;
		 
		 if(isValidFile(sourceFile)){
			 try {	 
				 if(!destFile.exists()) {
					 destFile.createNewFile();
				 }
				  source = new FileInputStream(sourceFile).getChannel();
				  destination = new FileOutputStream(destFile).getChannel();
				  destination.transferFrom(source, 0, source.size());
				  System.out.println(OPERATION_SUCCEEDED);
				 } catch (IOException e) {
					System.out.println(OPERATION_FAILED);
					e.printStackTrace();
				} finally {
					closeStream(source);
					closeStream(destination);
				}
		 } else {
			 System.out.println(FILE_NOT_FOUND_MSG);
		 }
    }
	
	private void closeStream(FileChannel channel) {
		try {
			if(channel != null) {
			     channel.close();
			   }
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
