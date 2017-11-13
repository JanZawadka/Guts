package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import client.connection.Core;
import client.model.ClientModelList;
import client.view.View;

public class Controller {
	private Core con;
	private View view;
	private ClientModelList model;

	public Controller() {
		String log = JOptionPane.showInputDialog("Your login");
		con = new Core(this, log);
		model = new ClientModelList();
		view = new View(this,model);
	}
	
	public void setChat(String msg) {
		String txt = msg + view.chat.getText();
		view.chat.setText(txt);
	}

	public class SendListener implements ActionListener {
		JTextArea msgArea;

		public SendListener(JTextArea msg) {
			this.msgArea = msg;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = msgArea.getText();
			if (msg != null && !msg.equals("")) {
				if (!msg.endsWith("\n"))
					msg += "\n";
				con.sendChat(msg);
				msgArea.setText("");
			}
		}
	}

	public class EnterListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			JTextArea msgArea = (JTextArea) e.getSource();
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String msg = msgArea.getText();
				if (msg != null && !msg.equals("")) {
					if (!msg.endsWith("\n"))
						msg += "\n";
					con.sendChat(msg);
					msgArea.setText("\b");
				}
			}
		}
	}
	
}