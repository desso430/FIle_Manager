package com.sap.idm.learning.server;

import java.util.List;

import com.sap.idm.learning.operations.Operation;

public class Disconnect extends Operation {

	@Override
	public void executeOperation(List<String> arguments) {
		ServerConnection.disconnect();
	}

}
