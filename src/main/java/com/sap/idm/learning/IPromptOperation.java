package com.sap.idm.learning.file_manager;

import java.util.List;

public interface IPromptOperation {
	
	public static final String FILE_NOT_FOUND_MSG = " The system cannot find the file specified! ";
	public static final String NOT_ENOUGHT_ARGUMENTS = " Not enough arguments ";
	public static final String TOO_MANY_ARGUMENTS = " Too many arguments ";
	public static final String OPERATION_SUCCEEDED = " Operation succeeded! ";
	public static final String WRONG_DIRECTORY = " Wrong directory! ";
	public static final String OPERATION_FAILED = "  Operation failed! ";
	
	public void executeOperation(List<String> arguments);

}
