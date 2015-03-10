/**
 *
 */
package de.sambalmueslie.traffic_ctrl;

/**
 * A light.
 *
 * @author sambalmueslie 2015
 */
public class Light {

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 */
	public Light(final String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #on}
	 */
	public boolean isOn() {
		return on;
	}

	/**
	 * Switch off.
	 */
	public void switchOff() {
		if (!isOn()) return;
		on = false;
	}

	/**
	 * Switch on.
	 */
	public void switchOn() {
		if (isOn()) return;
		on = true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " - Light [name=" + name + ", on=" + on + "]";
	}

	/** the name. */
	private final String name;
	/** the on flag. */
	private boolean on;
}
