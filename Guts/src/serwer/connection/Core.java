package serwer.connection;

import java.io.IOException;
import java.net.ServerSocket;

public class Core {
	ServerSocket srv;
	ManagerConnection mc;
	boolean working = true;

	public Core() {
		mc = new ManagerConnection(20);
		try {
			srv = new ServerSocket(15005);
			while (working) {
				mc.addConnection(srv.accept());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}