package client.connection;

import java.io.DataInputStream;
import java.io.IOException;

import client.control.Controller;

public class Input implements Runnable {
	DataInputStream in;
	Controller ctr;

	Input(DataInputStream in, Controller ctr) {
		this.in = in;
		this.ctr = ctr;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String msg = in.readUTF();
				String[] packet;
				boolean isSplited = msg.contains(";");
				if (isSplited) {
					packet = msg.split(";");
				} else {
					packet = new String[2];
					packet[1] = msg;
					packet[0] = "chat";
				}
				switch (packet[0]) {
				case "chat":
					ctr.setChat(packet[1]);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
