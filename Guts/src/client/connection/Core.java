package client.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.control.Controller;

public class Core {
	DataOutputStream out;
	DataInputStream in;
	Socket soc;
	String login;
	Controller ctr;

	public Core(Controller ctr, String log) {
		this.ctr = ctr;
		this.login = log;
		try {
			soc = new Socket("localhost", 15005);
			out = new DataOutputStream(soc.getOutputStream());
			in = new DataInputStream(soc.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread input = new Thread(new Input(in, ctr));
		input.start();
		try {
			out.writeUTF(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendChat(String msg) {
		try {
			out.writeUTF("chat;" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
