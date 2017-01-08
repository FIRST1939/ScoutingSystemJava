package buildingBlocks.controllerElements;

import java.awt.event.ActionListener;

public abstract class JController {

	public static final int ANALOG_ERROR = -1;
	public static final int ANALOG_NEUTRAL = 0;
	public static final int ANALOG_UP = 1;
	public static final int ANALOG_DOWN = 2;
	public static final int ANALOG_LEFT = 3;
	public static final int ANALOG_RIGHT = 4;
	
	protected net.java.games.input.Controller controller;
	protected ActionListener listener;
	
	public int robotPanelNumber = -1;
	
	public JController(net.java.games.input.Controller arg0, int controlsWhichRobotPanel) {
		controller = arg0;
		robotPanelNumber = controlsWhichRobotPanel;
	}
	
	public abstract void pollControllerInput();
	
	/**
	 * Returns if a was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isAPressed();
	
	/**
	 * Returns if b was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isBPressed();

	/**
	 * Returns if x was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isXPressed();

	/**
	 * Returns if y was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isYPressed();

	/**
	 * Returns if lb was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLBPressed();

	/**
	 * Returns if rb was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRBPressed();

	/**
	 * Returns if the left stick was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLSPressed();

	/**
	 * Returns if right stick was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRSPressed();

	/**
	 * Returns if lt was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLTPressed();
	
	/**
	 * Returns if lt is held down.
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	public abstract boolean isLTHeld();
	
	/**
	 * Returns if rt was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRTPressed();
	
	/**
	 * Returns if rt is held down.
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	public abstract boolean isRTHeld();

	/**
	 * Returns if start was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isStartPressed();
	
	/**
	 * Returns if back was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isBackPressed();
	
	/**
	 * Returns the left stick's direction.
	 * @return Returns an integer reflecting the sticks direction.
	 */
	public abstract int getLeftStick();

	/**
	 * Returns the right stick's direction.
	 * @return Returns an integer reflecting the sticks direction.
	 */
	public abstract int getRightStick();
	
	public void setActionListener(ActionListener listener) {
		this.listener = listener;
	}
	
}
