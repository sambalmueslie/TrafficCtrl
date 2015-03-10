/**
 *
 */
package de.sambalmueslie.traffic_ctrl;

/**
 * Traffic controller.
 *
 * @author sambalmueslie 2015
 */
public class TrafficController {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		new TrafficController();
	}

	/**
	 * Constructor.
	 */
	private TrafficController() {
		setup();
		execute();
	}

	/**
	 * Exectue.
	 */
	private void execute() {
		while (true) {
			crossing.update();
			try {
				Thread.sleep(500);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Setup.
	 */
	private void setup() {
		crossing = new Crossing();
	}

	/** the {@link Crossing}. */
	private Crossing crossing;

}
