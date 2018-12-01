/*
 * By: Jamie Won, Natasha Kuk, Jessie Xiong
 * Date: December 1, 2018
 */


// Import statements
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class IdolAcademy {
	
	// Class variables
	private ArrayList <Boy> boys;
	private JFrame window;
	private JPanel mainPanel;
	private JPanel left;
	private JPanel bottom;
	private JButton buttons [];
	private JLabel bottomLabel;
	private JLabel leftLabel;
	private int currentOpt []; // for current option [who][points]
	
	public static void main (String args[]) {
		new IdolAcademy().go();
	}
	
	public void go () {
//		createInitial();
		createBoys("as agf end");
		
	}
	
	// Makes initial screen
	public void createInitial () {
		
		// Makes elements
		window = new JFrame ("Idol Academy");
		mainPanel = new JPanel();
		bottom = new JPanel ();
		left = new JPanel ();
		bottomLabel = new JLabel();
		
		// Styles
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight =  Toolkit.getDefaultToolkit().getScreenSize().height;
		window.setSize(screenWidth, screenHeight);
		bottom.setSize(screenWidth, screenHeight/3); // ***** Doesn't rlly work
		bottom.setBackground(new Color (0,0,0,8));
		bottom.setForeground(Color.white);
		
		// Sets layouts
		mainPanel.setLayout(new BorderLayout());
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		
		// Adds elements
		bottom.add(bottomLabel);
		mainPanel.add(bottom, BorderLayout.SOUTH);
		mainPanel.add(left, BorderLayout.EAST);
		window.add(mainPanel);
		
		// Sets window operations
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
	}
	
	// Creates boys
	public void createBoys (String names) {
		
		boys = new ArrayList <Boy> ();
		
		while (names.contains(" ")) {
			String name = names.substring(0, names.indexOf(" ")); // Gets a name
			names = names.substring((names.indexOf(" ")+1)); // Resets names string
			boys.add(new Boy (name));
		}
		
	}
	
	// Makes options - repaints in other method
	public void options (String [] opt) {
		// Makes and initializes
		buttons = new JButton[3];
		for (int i = 0; i < 3; i ++) {
			buttons[i] = new JButton(opt[i]);
			buttons[i].setBackground(Color.black);
			buttons[i].setForeground(Color.white);
			buttons[i].setText(opt[i]);
			buttons[i].setEnabled(true);
			buttons[i].addActionListener(new listen()); 	// Adds action Listeners
		}
		
		// Adds elements to super element
		for (int i = 0; i < 3; i ++) {
			bottom.add(buttons[i]);
		}
	}
	
	// Resets screen - when button is pressed
	public void resetScreen (String boyPic, boolean option, String[] text) {
		
		// Changes screen
		leftLabel.setIcon(new ImageIcon(boyPic));
		if (option) {
			options(text);
		} else {
			bottomLabel.setText(text[0]);
		}
		
		// Repaints
		window.repaint();
	}
	
	
	class listen implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Adds points
			boys.get(currentOpt[0]).points += currentOpt[1];
			for (int i = 0; i < 3; i ++) {
				buttons[i].setEnabled(false);
			}
			
		}
		
	}

}
