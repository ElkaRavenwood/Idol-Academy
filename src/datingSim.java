/*
 * By: Jamie Won, Natasha Kuk, Jessie Xiong
 * Date: December 1, 2018
 */


// Import statements
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class datingSim {
	
	// Class variables
	static Boy [] boys;
	static JFrame window;
	static JPanel mainPanel;
	static JPanel left;
	static JPanel bottom;
	static JButton buttons [];
	static JLabel bottomLabel;
	
	public static void main (String args[]) {
		
//		createBoys(3, sda);
		createInitial();
		
	}

	// Makes initial screen
	public static void createInitial () {
		
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
	
	// Makes boys
	public static void createBoys (int numBoys, File info) {
		// Makes the "boys"
		boys = new Boy[numBoys];
		/*		// Collects scripts assuming there's only one file
		for (int i = 0; i < 3; i ++) {
			// Reads file
			// First line is name
			// Adds each line to arraylist (will be in sets of 3)
			// Makes the boy
			boys[0] = new Boy();
		} 
*/
	}
	
	// Makes options - repaints in other method
	public static void options (String [] opt) {
		// Makes and initializes
		buttons = new JButton[3];
		for (int i = 0; i < 3; i ++) {
			buttons[i] = new JButton(opt[i]);
			buttons[i].setBackground(Color.black);
			buttons[i].setForeground(Color.white);
		}
		
		// Adds action listeners
		
		// Adds elements to super element
		for (int i = 0; i < 3; i ++) {
			bottom.add(buttons[i]);
		}
	}
	
	// Resets screen - when button is pressed
	public static void resetScreen (String boyPic, boolean option, String[] text) {
		// Makes elements
		Image im = new ImageIcon(boyPic).getImage();
		if (option) {
			options(text);
		} else {
			bottomLabel.setText(text[0]);
		}
		
		// Changes screen
		
		// Repaints

	}

}
