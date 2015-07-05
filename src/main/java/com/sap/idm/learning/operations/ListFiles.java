package com.sap.idm.learning.operations;

import java.io.File;
import java.util.List;

public class ListFiles extends  Operation {
	
	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_ONE, arguments.size());
		if(areArgumentsEnough) {
			String directory = arguments.get(ARGUMENTS_ZERO);
			listFiles(openFile(directory));
		}
	}	
	
	public void listFiles(File directory) {
		if(isValidDirectory(directory)) {
			File[] files = directory.listFiles();

			if(files == null) {
				return;
			}
			
			for(File file : files) {
				if(file.isDirectory()) {
					listFiles(file);
				} else  
				   System.out.println(file.getName());
			}
		}		
	}	
}
