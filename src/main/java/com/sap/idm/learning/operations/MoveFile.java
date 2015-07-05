package com.sap.idm.learning.operations;

import java.io.File;
import java.util.List;

public class MoveFile extends Operation {
	
	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_TWO, arguments.size());
		if(areArgumentsEnough) {
			String pathToFile = arguments.get(ARGUMENTS_ZERO);
			String directory = arguments.get(ARGUMENTS_ONE);
			moveFile(openFile(pathToFile), openFile(directory));
		}
	}
	
	public void moveFile(File file, File directory) {		
	     if(isValidFile(file)) {
	    	 if(isValidDirectory(directory)) {
	    		File newFileLocation = new File(directory.getAbsolutePath() + File.separator + file.getName());
	    	
		    	boolean isFileMoved = file.renameTo(newFileLocation);
		    	System.out.println( isFileMoved? OPERATION_SUCCEEDED : OPERATION_FAILED );
	    	 } else {
	    		 System.out.println(WRONG_DIRECTORY);
	    	 }
		 } else {
			 System.out.println(FILE_NOT_FOUND_MSG);
		 }
	}

	
}
