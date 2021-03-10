package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The class is to draw a background image on a panel
 * @author Group 6
 *
 */
public class JPanelWithBackgroundImage extends JPanel {
	  private Image backgroundImage;

	  
	/**
	 * the method is to read a image
	 * @param fileName  
	 * @throws IOException
	 */
	public JPanelWithBackgroundImage(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(new File(fileName));
	  }

	/**
	 * the method is to draw a image
	 */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	}
}
