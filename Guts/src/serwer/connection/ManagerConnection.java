package serwer.connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ManagerConnection {
	private List<Connection> al; 
	
	ManagerConnection(int capacity){
		al = new ArrayList<>(capacity);
	}
	
	void addConnection(Socket x){
		al.add(new Connection(x,this));
	}
	
	public void broadcastChat(String msg) {
		List<Connection> ll = new LinkedList<Connection>(al);
		Iterator<Connection> iter = ll.iterator();
		while(iter.hasNext()){
			Connection con = iter.next();
			con.sendToChat(msg);
		}
	}
	
	public void delConnection(Connection c) {
		al.remove(c);
	}
	
}
