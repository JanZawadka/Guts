package serwer.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
	Socket soc;
	DataInputStream in;
	DataOutputStream out;
	String ip;
	ManagerConnection mc;
	String login;

	boolean running = true;

	public Connection(Socket soc, ManagerConnection mc) {
		this.soc = soc;
		this.mc = mc;
		try {
			in = new DataInputStream(soc.getInputStream());
			out = new DataOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
	}

	@Override
	public void run() {
		String read;
		try {
			read = in.readUTF();
			login = read;
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (running) {
			try {
				read = in.readUTF();
				String[] packet = read.split(";");
				switch(packet[0]) {
				case "chat" : 
					String msg = packet[1];
					mc.broadcastChat("<b>"+login+"</b>: "+msg);
					break;
				}
			} catch (IOException e) {
				running=false;
				try {
					out.close();
					soc.close();
					in.close();
				} catch (IOException e1) {
					mc.delConnection(this);
					System.out.println("Problem z zamknieciem");
					break;
				}
				mc.delConnection(this);
				System.out.println("Klient wyszedl");
				break;
			}
		}
	}

	public void sendToChat(String msg) {
		try {
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendStatus(String msg) {
		try {
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
