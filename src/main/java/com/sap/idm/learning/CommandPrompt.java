package com.sap.idm.learning;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class CommandPrompt {
	private static final String WRONG_DIRECTORY = "Wrong directory!";
	private static final String FILE_NOT_FOUND_MSG = "The system cannot find the file specified";

	public static void listFiles(File directory) {
		if(isValidDirectory(directory)) {
			File[] files = directory.listFiles();

			if(files == null) return;
			
			for(File file : files) {
				if(file.isDirectory()) {
					System.out.println("--- Folder: " + file.getName());
					listFiles(file);
				} else  
				   System.out.println(file.getName());
			}
		}		
	}
	
	public static void getInfo(File file) {
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
	
	public static void copyFiles(File sourceFile, File destFile) {
		 FileChannel source = null;
		 FileChannel destination = null;
		 
		 if(isValidFile(sourceFile) && isValidFile(destFile)){
			 try {
				  source = new FileInputStream(sourceFile).getChannel();
				  destination = new FileOutputStream(destFile).getChannel();
				  destination.transferFrom(source, 0, source.size());
				  System.out.println("The file was copied successfully!");
				 } catch (IOException e) {
					 System.out.println("The file was not copied successfully!");
					e.printStackTrace();
				} finally {
				   try {
					   if(source != null) {
					     source.close();
					   }
					   if(destination != null) {
						 destination.close();
					   }	
				    } catch (IOException e) {
					     e.printStackTrace();
				    }
			   }
		 } else {
			 System.out.println("Cannot find these files!");
		 }
    }
	
	public static void moveFile(File file, File directory) {		
	     if(isValidFile(file)) {
	    	 if(isValidDirectory(directory)) {
	    		File newFileLocation = new File(directory.getAbsolutePath() + File.separator + file.getName());
		    	boolean isFileMoved = file.renameTo(newFileLocation);
		    	System.out.println(isFileMoved? "The file was moved successfully!":"The file was not moved successfully!");
	    	 } else {
	    		 System.out.println(WRONG_DIRECTORY);
	    	 }
		 } else {
			 System.out.println(FILE_NOT_FOUND_MSG);
		 }
	}
	
	public static void deleteFile(File file) {
		if(isValidFile(file)) {
			 boolean isFileDeleted = file.delete();
			 System.out.println(isFileDeleted? "The file was deleted successfully!":"The file was not deleted successfully!");
		} else {
			 System.out.println(FILE_NOT_FOUND_MSG);
		}
	}
	
	public static void openFile(String fileName) {
		try {
			SearchFile finder = new SearchFile();
			finder.find(rootDirectory(), fileName);
			Path pathToFile = finder.getPathToFile();
			finder.open(pathToFile);
		} catch (IOException e) {
			System.out.println(" Cannot open this file: " + e.getMessage());
		}	
	}
	
	private static String rootDirectory(){
        return File.listRoots()[0].getAbsolutePath();
    } 
	
	private static boolean isValidFile(File file) {
		return file != null && file.exists() && file.isFile();
	} 
	
	private static boolean isValidDirectory(File directory) {
		return directory != null && directory.exists() && directory.isDirectory();
	} 
	
	private static String getFileExtension(File file) {
		String name = file.getName();
		return name.substring(name.lastIndexOf(".") + 1);
	}
}
