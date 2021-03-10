package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.BattleShipController;

/**
 * The class is to set the UI of BattleShip Application
 * @author Group 6
 *
 */
public class BattleShipView{

	private JFrame frame;
	private JPanelWithBackgroundImage background;
	
	private BattleShipController controller;
	private JButton[] buttonList;
	JButton east = new JButton();
	JButton west = new JButton();
	JLabel north = new JLabel();
	JButton south = new JButton();

	
	private final String IMAGE_FILE_BOARD = "resources/images/board.jpg";
	private final String IMAGE_FILE_SHIP = "resources/images/ship.png";
	private final String IMAGE_FILE_MISS = "resources/images/miss.png";

	/**
	 * Constructor
	 */
	public BattleShipView(BattleShipController controller) {
		this.controller = controller;
		initialize();
		this.frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Battle Ship");
		frame.setBounds(100, 100, 1040, 902);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(100, 100));
		frame.setResizable(false);
		
		try {
			background = new JPanelWithBackgroundImage(IMAGE_FILE_BOARD);
			background.setName("background");
			background.setLayout(new BorderLayout());
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().add(background, BorderLayout.CENTER);

		east = new JButton();
		west = new JButton();
		north = new JLabel();
		south = new JButton();
		east.setPreferredSize(new Dimension(177,100));
		west.setPreferredSize(new Dimension(171,100));
		north.setPreferredSize(new Dimension(100,97));
		south.setPreferredSize(new Dimension(100,90));
		east.addActionListener(this.controller);
		west.addActionListener(this.controller);
		north.addMouseListener(this.controller);
		south.addActionListener(this.controller);
		east.setBorderPainted(false);
		east.setContentAreaFilled(false);
		west.setBorderPainted(false);
		west.setContentAreaFilled(false);
		south.setBorderPainted(false);
		south.setContentAreaFilled(false);
		background.add(east, BorderLayout.EAST);
		background.add(west, BorderLayout.WEST);
		background.add(north, BorderLayout.NORTH);
		background.add(south, BorderLayout.SOUTH);

		JPanel target = new JPanel();
		target.setName("target");
		target.setLayout(new GridLayout(7,7));
		target.setOpaque(false);

		
		background.add(target, BorderLayout.CENTER);
		
		buttonList = new JButton[49];

		int k = 0;
		for(int i=(int)Math.round(Math.sqrt(buttonList.length)-1); i >=0 ; i--) {
			for(int j=0;j <Math.sqrt(buttonList.length) ; j++) {
				buttonList[k] = new JButton();
				buttonList[k].setActionCommand("" + i + j);
				buttonList[k].setBorderPainted(false);
				buttonList[k].setContentAreaFilled(false);
				buttonList[k].addActionListener(this.controller);
				target.add(buttonList[k], j);
				
				k++;
			}

		}
	}

	/**
	 * Initialize the screen
	 */
	public void clearScreen() {
		for(int i=0; i < buttonList.length; i++) {
			buttonList[i].setIcon(null);
		}	
	}

	/**
	 * Display the message when a certain message is received
	 * @param message
	 */
	public void displayMessage(String message) {
		
		if((message != "You missed") &&	(message != "HIT!")){
			JOptionPane.showMessageDialog(background, message);
		}
			
		System.out.println(message);
	}


	/**
	 * Set a corresponding image to the button according to the message received
	 * @param e
	 * @param message
	 */
	public void setImage(ActionEvent e, String message) {
		JButton buttonClicked = (JButton) e.getSource();
		if(message == "HIT!" || message == "You sank my battleship!") {			
			buttonClicked.setIcon(new ImageIcon(IMAGE_FILE_SHIP));
		} else if(message == "You missed") {
			buttonClicked.setIcon(new ImageIcon(IMAGE_FILE_MISS));
		} else if (message == "Oops, you already hit that location!") {			
		} else {
			buttonClicked.setIcon(new ImageIcon(IMAGE_FILE_SHIP));
			
		}
	}

	/**
	 * Display how many attempts the user made
	 * @param guesses
	 */
	public void displayAttempts(int guesses) {
		//this.north.setText("You only have 35 chances!!\n Current attempts: " + guesses + "/35");
		this.north.setText("You only have 35 chances!!\n Remaining attempts: " + (35-guesses));
		this.north.setFont(new Font("", Font.PLAIN, 20));
		this.north.setForeground(Color.WHITE);

	}
}
