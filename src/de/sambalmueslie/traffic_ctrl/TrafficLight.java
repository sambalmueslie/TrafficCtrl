/**
 *
 */
package de.sambalmueslie.traffic_ctrl;

/**
 * A traffic light.
 *
 * @author sambalmueslie 2015
 */
public class TrafficLight {

	/** the value for the crossing change timer. */
	private static int CROSSING_CHANGE_TIMER_VALUE = 10;

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 */
	public TrafficLight(final String name) {
		this.name = name;
		redLight = new Light(name + ":Red");
		yellowLight = new Light(name + ":Yellow");
		greenLight = new Light(name + ":Green");
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the state.
	 */
	public String getState() {
		final boolean redOn = redLight.isOn();
		final boolean yellowOn = yellowLight.isOn();
		final boolean greenOn = greenLight.isOn();

		if (redOn && !yellowOn) return "Rot";
		else if (redOn) return "Rot-Gelb";
		else if (greenOn) return "Grün";
		else if (yellowOn) return "Gelb";
		return null;
	}

	/**
	 * @return <code>true</code> if the green light is on, otherwise <code>false</code>.
	 */
	public boolean isGreenOn() {
		return greenLight.isOn();
	}

	/**
	 * @return <code>true</code> if only the red light is on, otherwise <code>false</code>.
	 */
	public boolean isOnlyRedOn() {
		return redLight.isOn() && !yellowLight.isOn();
	}

	/**
	 * Set the crossing allowed.
	 */
	public void setCrossingAllowed() {
		crossingAllowed = true;
		timer = CROSSING_CHANGE_TIMER_VALUE;
		update();
	}

	/**
	 * Set the crossing forbidden.
	 */
	public void setCrossingForbidden() {
		crossingAllowed = false;
		timer = CROSSING_CHANGE_TIMER_VALUE;
		update();
	}

	/**
	 * Update.
	 */
	public void update() {
		if (timer > 0) {
			timer--;
		}

		final boolean timerElapsed = timer == 0;

		final boolean requestGreenOn = crossingAllowed && timerElapsed;
		final boolean requestYellowOn = !timerElapsed;
		final boolean requestRedOn = (!crossingAllowed && timerElapsed) || (crossingAllowed && !timerElapsed);

		final boolean changes = requestGreenOn != greenLight.isOn() || requestRedOn != redLight.isOn() || requestYellowOn != yellowLight.isOn();
		if (!changes) return;

		updateLight(greenLight, requestGreenOn);
		updateLight(redLight, requestRedOn);
		updateLight(yellowLight, requestYellowOn);

		message();
	}

	/**
	 * Message.
	 */
	private void message() {
		final String state = getState();
		System.out.println(name + ":" + state);
	}

	/**
	 * Update a {@link Light}.
	 *
	 * @param light
	 *            the {@link Light} to update
	 * @param requestOn
	 *            the requested state
	 */
	private void updateLight(final Light light, final boolean requestOn) {
		if (light.isOn() == requestOn) return;
		if (requestOn) {
			light.switchOn();
		} else {
			light.switchOff();
		}
	}

	/** is the crossing allowed. */
	private boolean crossingAllowed;
	/** the green {@link Light}. */
	private final Light greenLight;
	/** the name. */
	private final String name;
	/** the red {@link Light}. */
	private final Light redLight;
	/** the timer. */
	private int timer;
	/** the yellow {@link Light}. */
	private final Light yellowLight;
}
