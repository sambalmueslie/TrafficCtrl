/**
 *
 */
package de.sambalmueslie.traffic_ctrl;

/**
 * A sensor to detect a waiting car.
 *
 * @author sambalmueslie 2015
 */
public class Sensor {

	/**
	 * @return the {@link #carDetected}
	 */
	public boolean isWaitingCarDetected() {
		return Math.random() > 0.5;
	}

}
