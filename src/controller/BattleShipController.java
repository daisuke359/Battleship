package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.BattleShipModel;
import view.BattleShipView;


/**
 * The class is to control the Model for BattleShip Application
 * @author Group 6
 *
 */
public class BattleShipController implements ActionListener, MouseListener {
	
    private int guesses;
    private int maxAttempt;
    private BattleShipModel model;
    private BattleShipView view;
    
    private final String SOUND_SANK = "resources/sound/GunShot2.wav";
    private final String SOUND_MISS = "resources/sound/GunEmpty.wav";
    private final String SOUND_ERROR = "resources/sound/GunClip.wav";
    private final String SOUND_BACKGROUND = "resources/sound/BackgroundMusic.wav";
    private final int SOUND_TYPE_BACKGROUND = 0;
    private final int SOUND_TYPE_CLICK = 1;


    /**
     * Default constructor
     */
    public BattleShipController() {
        guesses = 0;
        maxAttempt = 35;
        view = new BattleShipView(this);
        view.displayAttempts(0);
        model = new BattleShipModel();
        model.generateShipLocations();
        playSound(SOUND_BACKGROUND, SOUND_TYPE_BACKGROUND);        

    }

    /**
     * Process and compare the user guess and ship location. <br> 
     * It gets a message from a model and plays corresponding sounds, and it returns the message.
     * @param guess
     * @return hitMessage
     */
    public String processGuess(String guess) {
        String location = guess;
        if(location != null) {
            this.guesses++;
            String hitMessage = this.model.fire(location);
            boolean hit;
            view.displayAttempts(this.guesses);
            if(hitMessage == "Oops, you already hit that location!"){
            	hit = true;
            	playSound(SOUND_ERROR, SOUND_TYPE_CLICK);

            }
            if(hitMessage ==  "HIT!"
                    || hitMessage == "You sank my battleship!") {
                hit = true;
                playSound(SOUND_SANK, SOUND_TYPE_CLICK);
            } else {
                playSound(SOUND_MISS, SOUND_TYPE_CLICK);
                hit = false;
            }
            
            if(hit && this.model.shipSunk == this.model.numShips) {
            	 return "You sank all my battleships!!!\n Try again!!!";
            }
            else if (this.guesses == maxAttempt) {
                return "SORRY!!! Game Over! \nYou used all the attempts!";
            }
            else {
                return hitMessage;
            }
        }
        else {
            return "lets hit the ships!";
        }
    }

    /**
     * Deal with the click event in the UI.
 	 * Check if the click occurred outside of the game field.
 	 * Call the process method, and if the game is over it will initialize the game
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	JButton button = (JButton)e.getSource();
    	JPanel panel = (JPanel)button.getParent();
    	if (panel.getName().equals("background")) {

            playSound(SOUND_ERROR, SOUND_TYPE_CLICK);
            view.displayMessage("OUT OF AREA!!");


		} else {
			String message = this.processGuess(e.getActionCommand());
			view.setImage(e, message);			
			view.displayMessage(message);
			
			if(message.contains("You sank all my battleships")  || message.contains("You used all the attempts!")) {
		
				model.clearData();
				model.generateShipLocations();
				view.clearScreen();
				this.guesses = 0;
				view.displayAttempts(0);
			}

		}
    }


    /**
     * Play a sound according to the results of the guess
     * @param soundName
     * @param soundType
     */
    public synchronized void playSound(String soundName, int soundType) {
    
            try {
              Clip clip = AudioSystem.getClip();
              AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
              clip.open(inputStream);
              //clip.start();
              if (soundType == SOUND_TYPE_BACKGROUND) {
	              while (!clip.isRunning()) {
	            	  clip.loop(Clip.LOOP_CONTINUOUSLY);
	            	  clip.start();  	  
	              }
              } else {
            	  clip.start();
              }
            } catch (Exception e) {
              System.err.println(e.getMessage());
            }
    }
    
	/**
	 * Perform mouse click event for JLabel
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
        playSound(SOUND_ERROR, SOUND_TYPE_CLICK);
        view.displayMessage("OUT OF AREA!!");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

