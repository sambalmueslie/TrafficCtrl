/**
 *
 */
package de.sambalmueslie.traffic_ctrl;

/**
 * A crossing.
 *
 * @author sambalmueslie 2015
 */
public class Crossing {

	/** the value for the lock change timer. */
	private static int LOCK_CHANGE_TIMER_VALUE = 5;

	/**
	 * Constructor.
	 */
	public Crossing() {
		a1 = new TrafficLight("A1");
		a2 = new TrafficLight("A2");
		a3 = new TrafficLight("A3");
		sensor = new Sensor();

		setGiveWayCrossingActive();
	}

	/**
	 * Update.
	 */
	public void update() {
		if (lockChangeTimer > 0) {
			lockChangeTimer--;
		} else if (isTrafficLightSwitched()) {
			updateSensorState();
		} else {
			lockChangeTimer = LOCK_CHANGE_TIMER_VALUE;
		}

		a1.update();
		a2.update();
		a3.update();
	}

	/**
	 * @return is the traffic light switchted.
	 */
	private boolean isTrafficLightSwitched() {
		if (giveWayCrossingActive) return a1.isGreenOn() && a2.isGreenOn() && a3.isOnlyRedOn();
		else return a1.isOnlyRedOn() && a2.isOnlyRedOn() && a3.isGreenOn();
	}

	/**
	 * Set the give way crossing active.
	 */
	private void setGiveWayCrossingActive() {
		System.out.println("- Give way crossing active");
		giveWayCrossingActive = true;
		a1.setCrossingAllowed();
		a2.setCrossingAllowed();
		a3.setCrossingForbidden();
	}

	/**
	 * Set the give way crossing inactive.
	 */
	private void setGiveWayCrossingInactive() {
		System.out.println("- Give way crossing inactive");
		giveWayCrossingActive = false;
		a1.setCrossingForbidden();
		a2.setCrossingForbidden();
		a3.setCrossingAllowed();
	}

	/**
	 * Update the sensor state.
	 */
	private void updateSensorState() {
		final boolean waitingCarDetected = sensor.isWaitingCarDetected();
		final boolean changed = waitingCarDetected != giveWayCrossingActive;
		if (!changed) return;
		if (waitingCarDetected) {
			setGiveWayCrossingActive();
		} else {
			setGiveWayCrossingInactive();
		}
	}

	/** a {@link TrafficLight}. */
	private final TrafficLight a1;
	/** a {@link TrafficLight}. */
	private final TrafficLight a2;
	/** a {@link TrafficLight}. */
	private final TrafficLight a3;
	/** is the give way crossing active. */
	private boolean giveWayCrossingActive;
	/** the lock change timer. */
	private int lockChangeTimer;
	/** the {@link Sensor}. */
	private final Sensor sensor;
}
