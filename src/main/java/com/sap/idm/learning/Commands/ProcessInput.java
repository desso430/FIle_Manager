package com.sap.idm.learning.Commands;

import java.util.ArrayList;
import java.util.List;

public class ProcessInput {
	private static final int INDEX_NOT_FOUND = -1;
	private static final String SPLIT_STRING_BY_QUOTES = " \"";
	private static final String SPLIT_STRING_BY_WHITESPACE = " +";
	private static final int INDEX_TO_FIRST_PATH = 1;
	private static final int INDEX_TO_SECOND_PATH = 2;

	public static String getCommand(String line) {
		final int endCommandIndex = line.trim().indexOf(" ");
		if(endCommandIndex != INDEX_NOT_FOUND) {
			String command = line.substring(0, endCommandIndex);
			return command;
		}
	  return "";
	}

	public static List<String> getArguments(String line) {
		List<String> arguments = new ArrayList<String>();
		String[] paths = null;

		trimString(line);
		if(isPathIsInQuotes(line)) {
			paths = line.split(SPLIT_STRING_BY_QUOTES);
			setPaths(arguments, paths, true);
		} else {
			paths = line.split(SPLIT_STRING_BY_WHITESPACE);
			setPaths(arguments, paths, false);
		}	
		return arguments;
	}
	
	private static void setPaths(List<String> arguments, String[] paths, boolean isInQuotes) {
		if(paths.length == 2) { 
			String firstPath = paths[INDEX_TO_FIRST_PATH];
			
			if(isInQuotes) {
				firstPath = removeLastChar(firstPath);
			} 
			
			arguments.add(firstPath);
		}
		else if(paths.length == 3) {
			String firstPath = paths[INDEX_TO_FIRST_PATH];
			String secondPath = paths[INDEX_TO_SECOND_PATH];	
			
			if(isInQuotes) {
				firstPath = removeLastChar(firstPath);
				secondPath = removeLastChar(secondPath);
			}
			
			arguments.add(firstPath);
			arguments.add(secondPath);
		}
	}
	
	private static boolean isPathIsInQuotes(String command) {	
		final char singleQuote = '"';
		for(char a: command.toCharArray()) {
			if(a == singleQuote) {
				return true;
			}
		}
	  return false;
	}
	
	// removes quote from string
	private static String removeLastChar(String str) {
		String trimmedString = str.trim();
        return trimmedString.substring(0,trimmedString.length()-1);
    }
	
	private static void trimString(String line) {
		line = line.trim();
	}
}
