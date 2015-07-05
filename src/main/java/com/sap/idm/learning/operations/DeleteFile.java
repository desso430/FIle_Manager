package com.sap.idm.learning.operations;

import java.io.File;
import java.util.List;

public class DeleteFile extends Operation {
	
	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_ONE, arguments.size());
		if(areArgumentsEnough) {
			String pathToFile = arguments.get(ARGUMENTS_ZERO);
			deleteFile(openFile(pathToFile));
		}	
	}
	
	public void deleteFile(File file) {
		if(isValidFile(file)) {
			 boolean isFileDeleted = file.delete();
			 System.out.println( isFileDeleted? OPERATION_SUCCEEDED : OPERATION_FAILED );
		} else {
			 System.out.println(FILE_NOT_FOUND_MSG);
		}
	}


}
