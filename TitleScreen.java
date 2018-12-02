/*
 * Code Writer: Jamie Won
 * Story Writer: Natasha Kuk, Jessie Xiong
 * December 1, 2018
 */

// Import statements
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


public class TitleScreen{
	public static void main (String args[]) {
		new TitleScreen();
	}
	JFrame menuFrame;
	JLabel nameField;
	JPanel panel;
	String name;
	
	TitleScreen () {
		menuFrame = new JFrame ("Main Menu");
	
		//configure the window
		menuFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		menuFrame.setLocationRelativeTo(null); //start the frame in the center of the screen
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton start = new JButton ("START");
		start.addActionListener(new start());
		panel = new JPanel();
		panel.add(start);
		menuFrame.add(panel);
		menuFrame.setVisible(true);
	}
	
	class start implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			name = JOptionPane.showInputDialog(menuFrame,"Please enter your name to continue:", "Type Name Here....");
			menuFrame.dispose();
		}
		
	}
}