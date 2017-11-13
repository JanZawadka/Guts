package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import client.control.Controller;
import client.model.ClientModelList;

public class View {
	private JFrame f;
	public JTextArea log;
	public JEditorPane chat;
	public JTextArea input;
	public final int x = 850;
	private final int y = 700;
	Controller ctr;
	
	public View(Controller ctr, ClientModelList cml) {
		this.ctr = ctr;
		
		// Window
		f = new JFrame();
		f.setLayout(new GridBagLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(true);
		f.setPreferredSize(new Dimension(x, y));
		f.setMinimumSize(new Dimension(x, y));
		f.setMaximumSize(new Dimension(x, y));

		GridBagConstraints gbc = new GridBagConstraints();

		// JMenuBar
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("menu");
		JMenuItem i1 = new JMenuItem("elo");
		mb.add(m1);
		m1.add(i1);
		f.setJMenuBar(mb);

		// Log
		log = makeComponent(new JTextArea(), gbc, 400, 300, 0, 0, 4, 4);
		log.setEditable(false);
		f.add(log, gbc);

		// Availables
		JList user = makeComponent(new JList(cml), gbc, 400, 300, 4, 0, 4, 4);
		f.add(user, gbc);

		// Control
		JPanel panel = makeComponent(new JPanel(), gbc, 400, 300, 0, 4, 4, 4);
		panel.setOpaque(true);
		panel.setBackground(Color.RED);
		f.add(panel, gbc);

		// Chat
		chat = makeComponent(new JEditorPane("text/html",""), gbc, 400, 250, 4, 4, 4, 3);
		f.add(chat, gbc);
		log.setEditable(false);

		// Input Chat
		input = makeComponent(new JTextArea(), gbc, 350, 50, 4, 7, 3, 1);
		input.setFocusable(true);
		input.addKeyListener(ctr.new EnterListener());
		f.add(input, gbc);

		// Send	
		JButton send = makeComponent(new JButton(), gbc, 50, 50, 7, 7, 1, 1);
		send.addActionListener(ctr.new SendListener(input));
		f.add(send, gbc);
		
		
		// Window Listener
		f.addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
		        input.requestFocus();
		    }
		});
		
		f.setVisible(true);
	}

	private <T extends Component> T makeComponent(T comp, GridBagConstraints gbc, int width, int height, int x, int y,
			int xw, int yw) {
		comp.setPreferredSize(new Dimension(width, height));
		comp.setMinimumSize(new Dimension(width, height));
		comp.setMaximumSize(new Dimension(width, height));
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = xw;
		gbc.gridheight = yw;
		return comp;
	}

}
