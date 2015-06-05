package com.sap.idm.learning.Commands;

import java.io.File;
import java.util.List;

public class DeleteFile extends PromptOperation {

	private static final int NUMBER_OF_NECESSARY_ARGUMENTS = 1;
	
	public void deleteFile(File file) {
		if(isValidFile(file)) {
			 boolean isFileDeleted = file.delete();
			 System.out.println( isFileDeleted? OPERATION_SUCCEEDED : OPERATION_FAILED );
		} else {
			 System.out.println(FILE_NOT_FOUND_MSG);
		}
	}

	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsNumberCorrect = argumentsNumberCheck(NUMBER_OF_NECESSARY_ARGUMENTS, arguments.size());
		if(areArgumentsNumberCorrect) {
			String pathToFile = arguments.get(FIRST_ARGUMENT);
			deleteFile(openFile(pathToFile));
		}	
	}
}
