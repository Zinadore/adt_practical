package tests;

import mocks.IdentityProviderMock;
import model.IServer;
import model.IdentityException;
import model.Server;
import model.ServerException;

public class ServerTests {
	public static void main(String args[]) throws IdentityException, ServerException {
		IServer server = new Server(new IdentityProviderMock());
		
		server.connectUser("John", "12345", "John-PC");
		server.disconnectUser("John", "John-PC");
	}
}
