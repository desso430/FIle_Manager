package com.sap.idm.learning.operations;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import com.sap.idm.learning.file_manager.IPromptOperation;

public abstract class Operation implements IPromptOperation {

	protected static final String DEFAULT_ENCODING = "UTF-8";
	protected static final int ARGUMENTS_ZERO = 0;
	protected static final int ARGUMENTS_ONE = 1;
	protected static final int ARGUMENTS_TWO= 2;

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
	
    protected boolean areArgumentsEnough(int necesseryArguments, int numberOfArgument) {		
		if(numberOfArgument == necesseryArguments) {
			return true; 
		} else if (numberOfArgument < necesseryArguments) { 
			System.out.println(NOT_ENOUGHT_ARGUMENTS);
		} else if(numberOfArgument > necesseryArguments){
			System.out.println(TOO_MANY_ARGUMENTS);
		}
		return false;
	}
    
    protected boolean areArgumentsEnough(int minArguments, int maxArguments, int numberOfArgument) {		
		if(minArguments <= numberOfArgument && numberOfArgument <= maxArguments) {
			return true; 
		} else if (numberOfArgument < minArguments) { 
			System.out.println(NOT_ENOUGHT_ARGUMENTS);
		} else if(numberOfArgument > maxArguments){
			System.out.println(TOO_MANY_ARGUMENTS);
		}
		return false;
	}
    
	protected void closeStream(Closeable stream) {
		try {
			if(stream != null) {
				stream.close();
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
