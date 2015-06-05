package com.sap.idm.learning.Commands;

import java.io.File;
import java.util.List;

public abstract class PromptOperation {
	
	protected static final String FILE_NOT_FOUND_MSG = " The system cannot find the file specified! ";
	protected static final String NOT_ENOUGHT_ARGUMENTS = " Not enough arguments ";
	protected static final String TOO_MANY_ARGUMENTS = " Too many arguments ";
	protected static final String OPERATION_SUCCEEDED = " Operation succeeded! ";
	protected static final String WRONG_DIRECTORY = " Wrong directory! ";
	protected static final String OPERATION_FAILED = "  Operation failed! ";
	protected static final int FIRST_ARGUMENT = 0;
	protected static final int SECOND_ARGUMENT = 1;

	
	protected boolean isValidDirectory(File directory) {
		boolean isDirectory = (directory != null && directory.exists() && directory.isDirectory());
		return isDirectory;
	}
	
	protected boolean isValidFile(File file) {
		boolean isFile = (file != null && file.exists() && file.isFile());
		return isFile;
	} 
	
	protected File  openFile(String pathToFile) {
		 return new File(pathToFile);
    }
	
    protected boolean argumentsNumberCheck(int necesseryArguments, int numberOfArgument) {		
		if(numberOfArgument < necesseryArguments) {
			System.out.println(NOT_ENOUGHT_ARGUMENTS);
			return false;
		} else if (numberOfArgument == necesseryArguments) { 
			return true;
		} else {
			System.out.println(TOO_MANY_ARGUMENTS);
			return false;
		}
	}
	
	public abstract void executeOperation(List<String> arguments);
}
