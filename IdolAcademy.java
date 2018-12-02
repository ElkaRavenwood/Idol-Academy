/*
 * By: Jamie Won, Natasha Kuk, Jessie Xiong
 * Date: December 1, 2018
 */


// Import statements
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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
	private String playerName;
	private ArrayList <String> lines;
	
	
	private int currentLine = 0;
	
	public static void main (String args[]) {
		new IdolAcademy().go();
	}
	
	public void go () {
//		TitleScreen temp = new TitleScreen();
//		playerName = temp.name;
		createInitial();
		try {
			readFile("script.txt");
		} catch (Exception e){
			e.printStackTrace();
		}
		createBoys("as agf end");
//		while (true) {
//			for (int i = 0; i < lines.size(); i ++) {
//				resetScreen("vrk.png", false, lines.get(i));
//			}
//		}
		
	}
	
	//read the script text file
    public void readFile(String path)throws Exception{
    		lines = new ArrayList <String> ();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        char identifier;
        String dialogue;
        
        // creates boys
        createBoys(br.readLine());

        while ((line = br.readLine()) != null){
        		lines.add(line);
        		try {
        			identifier = line.charAt(0);
        		} catch (Exception e) {
        			e.printStackTrace();
        			break;
        		}
            dialogue = line.substring(1);
            switch(identifier){
                case '^':
                    System.out.println(">>>>Dialogue<<<<");
                    printDialogue(dialogue);
                    break;
                case '@':
                    System.out.println(">>>>Location Change<<<<");
                    printDialogue(dialogue);
                    break;
                case '?':
                    System.out.println(">>>>Options<<<<");
                    printDialogue("PRINT OPTIONS BUT THEN DO SOMETHING WITH THE GUI");
                    break;
                case ':':
                    System.out.println(">>>>Player Dialogue<<<<");
                    printDialogue("playerName:"+dialogue);
                    break;
                default:
                    System.out.println("---No identifier---");
            }
        }
        br.close();
    }
    // prints dialogue letter by letter
    public static void printDialogue(String dialogue){
        System.out.println(dialogue+"\n");
    }
	
	// Makes initial screen
	public void createInitial () {
		
		// Makes elements
		window = new JFrame ("Idol Academy");
		mainPanel = new JPanel();
		bottom = new JPanel ();
		left = new JPanel ();
		bottomLabel = new JLabel();
		
		// Sets layouts
		mainPanel.setLayout(new BorderLayout());
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
				
		
		JButton next = new JButton ("NEXT");
		next.setSize(100,100);
		JPanel east = new JPanel();
		next.addActionListener(new next());
		east.add(next);
		mainPanel.add(east, BorderLayout.EAST);
		JPanel temp2 = new JPanel();
		JPanel temp1 = new JPanel();
		mainPanel.add(temp1, BorderLayout.NORTH);
		mainPanel.add(temp2, BorderLayout.CENTER);
		
		// Styles
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight =  Toolkit.getDefaultToolkit().getScreenSize().height;
//		window.setSize(screenWidth, screenHeight);
		window.setSize(700, 500);
//		bottom.setSize(screenWidth, screenHeight/3); // ***** Doesn't rlly work
		bottom.setSize(new Dimension(700, 300)); // ***** Doesn't rlly work
		
		bottom.setBackground(new Color (0,0,0,8));
		bottom.setForeground(Color.white);
		
		// Adds elements
		bottom.add(bottomLabel);
		mainPanel.add(bottom, BorderLayout.SOUTH);
		mainPanel.add(left, BorderLayout.WEST);
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
	public void resetScreen (String boyPic, boolean option, String text) {
		
//		ImagePanel img = new ImagePanel(new ImageIcon(boyPic).getImage());
//		left = img;
//		ImageIcon boy = new ImageIcon("vrk.png");
//		leftLabel.setIcon(boy);
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight =  Toolkit.getDefaultToolkit().getScreenSize().height;
		
		bottom.setSize(screenWidth, screenHeight/3); // ***** Doesn't rlly work
		bottom.setBackground(new Color (0,0,0,8));
		bottom.setForeground(Color.white);
		
		bottomLabel.setText(text);
		
		// Repaints
		window.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	class next implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currentLine ++;
			bottomLabel.setText(lines.get(currentLine));
			window.repaint();
		}
	}
	
}
