package com.sap.idm.learning.server;

import java.util.List;
import com.sap.idm.learning.operations.Operation;

public class Connect extends Operation {
	
	@Override
	public void executeOperation(List<String> arguments) {
		int argumentsNumber = arguments.size();
	    boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_TWO, argumentsNumber);
	             
	    if(areArgumentsEnough) {
	    	String host = arguments.get(ARGUMENTS_ZERO);
	    	int port = Integer.parseInt(arguments.get(ARGUMENTS_ONE));
	    	
	    	ServerConnection connection = new ServerConnection(host, port);
	    	connection.connect();
	    }
	}
}
