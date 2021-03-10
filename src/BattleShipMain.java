import java.awt.EventQueue;

import controller.BattleShipController;

/**
 * This is the main class to execute BattleShip Application
 * @author Group 6
 *
 */
public class BattleShipMain {

	/**
	 * Execute the Application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleShipController controller = new BattleShipController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
