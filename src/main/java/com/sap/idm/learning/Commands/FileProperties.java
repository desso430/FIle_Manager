package com.sap.idm.learning.Commands;

import java.io.File;
import java.util.List;

public class FileProperties extends PromptOperation {
	
	private static final int NUMBER_OF_NECESSARY_ARGUMENTS = 1;
	private static final String SPLIT_STRING_CHARACTER = ".";
	private static final int INDEX_NOT_FOUND = 0;
	

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

	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsNumberCorrect = argumentsNumberCheck(NUMBER_OF_NECESSARY_ARGUMENTS, arguments.size());
		if(areArgumentsNumberCorrect) {
			String pathToFile = arguments.get(FIRST_ARGUMENT);
			getInfo(openFile(pathToFile));
		}		
	}

}
