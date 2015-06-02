package com.sap.idm.learning;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Finder extends SimpleFileVisitor<Path> {
	
	private boolean isFind;
	private String searchedFileName;
	private Path pathToFile;

    public Finder(String searchedFile) {   	 
		this.searchedFileName = searchedFile;
		isFind = false;
    }

	public Path getPathToFoundFile() {
		return pathToFile;
	}

    public boolean isFind() {
		return isFind;
	}
    
    private void find(Path file) {
        String name = file.toFile().getName();
        if (name.equals(searchedFileName)) {
            System.out.println(file.getFileName()); 
            pathToFile = file;
            isFind = true;
            return;
        }
    }

	@Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs) {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
      //  System.err.println(exc);
        return CONTINUE;
    }	
}