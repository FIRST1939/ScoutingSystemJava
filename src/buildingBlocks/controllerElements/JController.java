package buildingBlocks.controllerElements;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import net.java.games.input.Rumbler;

/**
 * Provides the framework to create other controllers.
 * @author Grayson Spidle
 *
 */
public abstract class JController {

	/**
	 * This value is used when there is an error
	 */
	public static final int ANALOG_ERROR = -1;
	/**
	 * This value is used when an analog stick is not pointed in a particular direction.
	 */
	public static final int ANALOG_NEUTRAL = 0;
	/**
	 * This value is used when an analog stick is pointed up.
	 */
	public static final int ANALOG_UP = 1;
	/**
	 * This value is used when an analog stick is pointed down.
	 */
	public static final int ANALOG_DOWN = 2;
	/**
	 * This value is used when an analog stick is pointed left.
	 */
	public static final int ANALOG_LEFT = 3;
	/**
	 * This value is used when an analog stick is pointed right.
	 */
	public static final int ANALOG_RIGHT = 4;
	public static final float SWITCH_ERROR = -1f;
	public static final float SWITCH_NEUTRAL = 0f;
	public static final float SWITCH_UP = 0.25f;
	public static final float SWITCH_RIGHT = 0.5f;
	public static final float SWITCH_DOWN = 0.75f;
	public static final float SWITCH_LEFT = 1f;
	
	public net.java.games.input.Controller controller;
	protected List<ActionListener> listeners = new Vector<ActionListener>();
	
	protected ActionListener listener;
	public int robotPanelNumber = -1;

	public JController(net.java.games.input.Controller arg0, int controlsWhichRobotPanel) {
		this.controller = arg0;
		this.robotPanelNumber = controlsWhichRobotPanel;
	}

	public abstract void pollControllerInput();

	/**
	 * Returns if a was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isAPressed();

	/**
	 * Returns if a was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isAHeld();

	/**
	 * Returns if b was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isBPressed();

	/**
	 * Returns if b was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isBHeld();

	/**
	 * Returns if x was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isXPressed();

	/**
	 * Returns if x was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isXHeld();

	/**
	 * Returns if y was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isYPressed();

	/**
	 * Returns if y was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isYHeld();

	/**
	 * Returns if lb was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLBPressed();

	/**
	 * Returns if lb was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isLBHeld();

	/**
	 * Returns if the right button was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRBPressed();

	/**
	 * Returns if the right button was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isRBHeld();

	/**
	 * Returns if the left stick was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLSPressed();

	/**
	 * Returns if left stick was held.
	 * 
	 * @return Returns true if the stick was held and returns false if it was not held.
	 */
	public abstract boolean isLSHeld();

	/**
	 * Returns if right stick was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRSPressed();

	/**
	 * Returns if right stick was held.
	 * 
	 * @return Returns true if the stick was held and returns false if it was not held.
	 */
	public abstract boolean isRSHeld();

	/**
	 * Returns if lt was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLTPressed();

	/**
	 * Returns if lt is held down.
	 * 
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	public abstract boolean isLTHeld();
	/**
	 * Returns if rt was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRTPressed();

	/**
	 * Returns if rt is held down.
	 * 
	 * @return Returns true if the button is held and returns false if it was not held.
	 */
	public abstract boolean isRTHeld();

	/**
	 * Returns if start was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isStartPressed();

	/**
	 * Returns if start was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isStartHeld();

	/**
	 * Returns if back was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isBackPressed();

	/**
	 * Returns if back was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isBackHeld();

	/**
	 * Returns the left stick's direction.
	 * 
	 * @return Returns an integer reflecting the sticks direction.
	 */
	public abstract int getLeftStick();

	/**
	 * Returns the right stick's direction.
	 * 
	 * @return Returns an integer reflecting the sticks direction.
	 */
	public abstract int getRightStick();

	/**
	 * Returns the dpad's direction.
	 * @return Returns a float reflecting the directional pad's direction.
	 */
	public abstract float getDPad();
	
	public void rumble(float intensity) {
		for (Rumbler r : controller.getRumblers()) {
			r.rumble(intensity);
		}
	}
	
	public void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeActionListener(ActionListener listener) {
		this.listeners.remove(listener);
	}
}
