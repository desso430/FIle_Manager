
package com.sap.idm.learning.file_manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sap.idm.learning.operations.*;
import com.sap.idm.learning.server.Connect;
import com.sap.idm.learning.server.Disconnect;
import com.sap.idm.learning.server.Download;
import com.sap.idm.learning.server.ListFilesOnServer;
import com.sap.idm.learning.server.Upload;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;


public class FileManagerMain {
	
	private static final String EXIT = "exit";
	private static final String QUIT = "quit";
	private static final String COMMAND_FOR_CLEAR_SCREEN = "cls";
	private static Map<String, Operation> operations = new HashMap<String, Operation>();
	
  public static void usage() {
    System.out.println("Usage: java " + FileManagerMain.class.getName() + " [none/simple/files/dictionary [trigger mask]]");
    System.out.println("  none - no completors");
    System.out.println("  simple - a simple completor that comples " + "\"foo\", \"bar\", and \"baz\"");
    System.out.println("  files - a completor that comples " + "file names");
    System.out.println("  classes - a completor that comples " + "java class names");
    System.out.println("  trigger - a special word which causes it to assume " + "the next line is a password");
    System.out.println("  mask - is the character to print in place of " + "the actual password character");
    System.out.println("  color - colored prompt and feedback");
    System.out.println("\n  E.g - java Example simple su '*'\n" + "will use the simple compleator with 'su' triggering\n"
        + "the use of '*' as a password mask.");
  }

  public static void main(String[] args) throws IOException {
    try {
      Character mask = null;
      String trigger = null;
      boolean color = false;

      ConsoleReader reader = new ConsoleReader();

      reader.setPrompt("prompt> ");

      if ((args == null) || (args.length == 0)) {
        usage();

        return;
      }

      List<Completer> completors = new LinkedList<Completer>();

      if (args.length > 0) {
        if (args[0].equals("none")) {
        } else if (args[0].equals("files")) {
          completors.add(new FileNameCompleter());
        } else if (args[0].equals("simple")) {
          completors.add(new StringsCompleter("foo", "bar", "baz"));
        } else if (args[0].equals("color")) {
          color = true;
          reader.setPrompt("\u001B[1mfoo\u001B[0m@bar\u001B[32m@baz\u001B[0m> ");
        } else {
          usage();

          return;
        }
      }

      if (args.length == 3) {
        mask = args[2].charAt(0);
        trigger = args[1];
      }

      for (Completer c : completors) {
        reader.addCompleter(c);
      }

      String line; 
      PrintWriter out = new PrintWriter(reader.getOutput());
      loadOperationObject();
      
      while ((line = reader.readLine()) != null) {
        if (color) {
          out.println("\u001B[33m======>\u001B[0m\"" + line + "\"");

        } else {
          out.println("======>\"" + line + "\"");
        }
        out.flush();

        // If we input the special word then we will mask
        // the next line.
        if ((trigger != null) && (line.compareTo(trigger) == 0)) {
          line = reader.readLine("password> ", mask);
        }
        if (line.equalsIgnoreCase(QUIT) || line.equalsIgnoreCase(EXIT)) {
          break;
        }
        
        if(line.equalsIgnoreCase(COMMAND_FOR_CLEAR_SCREEN)) {
        	reader.clearScreen();
        }
        
        if(line != null && !line.equalsIgnoreCase(COMMAND_FOR_CLEAR_SCREEN)) {
        	String command = ProcessInput.getCommand(line);

        	if(operations.containsKey(command)) {
        		IPromptOperation operationObject = operations.get(command);
        		operationObject.executeOperation(ProcessInput.getArguments(line));
        	} else {
        		System.out.println(" Unknown command ");
        	}
        }
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  private static void loadOperationObject() {		
		operations.put(PromptCommands.LIST_FILES, new ListFiles());
		operations.put(PromptCommands.PROPERTIES, new FileProperties());
		operations.put(PromptCommands.COPY_FILE, new CopyFile());
		operations.put(PromptCommands.MOVE_FILE, new MoveFile());
		operations.put(PromptCommands.DELETE_FILE, new DeleteFile());
		operations.put(PromptCommands.OPEN_FILE, new OpenFile());
		operations.put(PromptCommands.HELP, new Help());
		operations.put(PromptCommands.CONNECT, new Connect());
		operations.put(PromptCommands.DISCONNECT, new Disconnect());
		operations.put(PromptCommands.UPLOAD, new Upload());
		operations.put(PromptCommands.DOWNLOAD, new Download());
		operations.put(PromptCommands.SERVER_LIST_FILES, new ListFilesOnServer());		
	}
}
