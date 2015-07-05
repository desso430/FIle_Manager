package com.sap.idm.learning.operations;

import java.io.File;
import java.util.List;

public class FileProperties extends Operation {
	
	private static final String SPLIT_STRING_CHARACTER = ".";
	private static final int INDEX_NOT_FOUND = 0;
	
	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_ONE, arguments.size());
		if(areArgumentsEnough) {
			String pathToFile = arguments.get(ARGUMENTS_ZERO);
			getInfo(openFile(pathToFile));
		}		
	}

	
	public void getInfo(File file) {
		if(isValidFile(file)) {
			System.out.println("----------------------- File Info ----------------------");
			System.out.println("File name: " + file.getName());
			System.out.println("Path: " + file.getAbsolutePath());
			System.out.println("Is hidden: " + (file.isHidden()? " YES " : " NO "));
			System.out.println("Size: " + file.length());
			System.out.println("Type: " + getFileExtension(file));
		} else {
			System.out.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	private String getFileExtension(File file) {
		String name = file.getName();
		final int indexToFileExtension = name.lastIndexOf(SPLIT_STRING_CHARACTER) + 1;
		if(indexToFileExtension != INDEX_NOT_FOUND) {
			String fileExtension = name.substring(indexToFileExtension);
			return fileExtension;
		} 	 
	   return "";
	}
}
