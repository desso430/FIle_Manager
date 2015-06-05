package com.sap.idm.learning.Commands;

import java.io.File;
import java.util.List;

public class MoveFile extends PromptOperation {
	
	private static final int NUMBER_OF_NECESSARY_ARGUMENTS = 2;
	
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

	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsNumberCorrect = argumentsNumberCheck(NUMBER_OF_NECESSARY_ARGUMENTS, arguments.size());
		if(areArgumentsNumberCorrect) {
			String pathToFile = arguments.get(FIRST_ARGUMENT);
			String directory = arguments.get(SECOND_ARGUMENT);
			moveFile(openFile(pathToFile), openFile(directory));
		}
	}
	
}
