package com.sap.idm.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SearchFile {	
	
	private Path pathToFile;
	
	public SearchFile() { }
	
	public SearchFile(Path pathToFile) {
		this.pathToFile = pathToFile;
	}

	public Path getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(Path pathToFile) {
		this.pathToFile = pathToFile;
	}

	public void  find(String dir, String fileName) throws IOException {
	    Path startingDir = Paths.get(dir);

		Finder finder = new Finder(fileName);
		Files.walkFileTree(startingDir, finder);
		pathToFile = finder.getPathToFoundFile();
	} 

	public void open(Path file) {
		 Charset charset = Charset.forName("US-ASCII");
		 try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		     String line = null;
		     while ((line = reader.readLine()) != null) {
		         System.out.println(line);
		     }
		 } catch (IOException x) {
		     System.err.format("IOException: %s%n", x);
		 }
	}
}
