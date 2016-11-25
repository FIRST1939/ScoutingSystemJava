package buildingBlocks.controllerElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 * A class that handles the inputs of the Controller.Type Stick.
 * @author Grayson Spidle
 *
 */
public class StickController {

	public Controller controller;

	public static final int ANALOG_ERROR = -1;
	public static final int ANALOG_NEUTRAL = 0;
	public static final int ANALOG_UP = 1;
	public static final int ANALOG_DOWN = 2;
	public static final int ANALOG_LEFT = 3;
	public static final int ANALOG_RIGHT = 4;

	public ControllerButton a = new ControllerButton();
	public ControllerButton b = new ControllerButton();
	public ControllerButton x = new ControllerButton();
	public ControllerButton y = new ControllerButton();
	public ControllerButton lb = new ControllerButton();
	public ControllerButton rb = new ControllerButton();
	public ControllerButton start = new ControllerButton();
	public ControllerButton back = new ControllerButton();
	public ControllerButton ls = new ControllerButton();
	public ControllerButton rs = new ControllerButton();

	public ControllerButton lt = new ControllerButton();
	public ControllerButton rt = new ControllerButton();

	private static int leftStick = ANALOG_NEUTRAL;
	private static int rightStick = ANALOG_NEUTRAL;

	private ActionListener listener;
	private ActionEvent queueControls = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find the holy grail!");

	public int robotPanelNumber = -1;

	boolean value = false;
	public float analogValue = 0.0f;
	
	/**
	 * The constructor.
	 * @param arg0 The controller from which the inputs will be parsed.
	 * @param controlsWhichRobotPanel The designated robotPanel on the UI that this controller will control.
	 */
	public StickController(Controller arg0, int controlsWhichRobotPanel) {

		controller = arg0;

		leftStick = ANALOG_NEUTRAL;
		rightStick = ANALOG_NEUTRAL;

		robotPanelNumber = controlsWhichRobotPanel;

	}

	/**
	 * Call this method to get controller input (does not contain a loop)
	 */
	public void pollControllerInput() {
		analogValue = 0.0f;

		controller.poll();
		Component[] components = controller.getComponents();
		for (int i = 0; i < components.length; i++) {

			boolean isAnalog = components[i].isAnalog();
			String name = components[i].getName();

			if (isAnalog) {
				analogValue = components[i].getPollData();

				Runnable setStickAnalogValues = new Runnable() {
					@Override
					public void run() {
						// ANALOG_LEFT Stick
						if (name.equalsIgnoreCase("Y Axis")) {
							if (analogValue == 1) leftStick = ANALOG_DOWN;
							if (analogValue == -1) leftStick = ANALOG_UP;
							if (analogValue != -1 && analogValue != 1) leftStick = ANALOG_NEUTRAL;
						} else if (name.equalsIgnoreCase("X Axis") && leftStick == ANALOG_NEUTRAL) {
							if (analogValue == 1) leftStick = ANALOG_RIGHT;
							if (analogValue == -1) leftStick = ANALOG_LEFT;
							if (analogValue != -1 && analogValue != 1) leftStick = ANALOG_NEUTRAL;
						}

						// ANALOG_RIGHT Stick
						if (name.equalsIgnoreCase("Z Rotation")) {
							if (analogValue == 1) rightStick = ANALOG_DOWN;
							if (analogValue == -1) rightStick = ANALOG_UP;
							if (analogValue != -1 && analogValue != 1) rightStick = ANALOG_NEUTRAL;
						}
						if (name.equalsIgnoreCase("X Rotation") && rightStick == ANALOG_NEUTRAL) {
							if (analogValue == 1) rightStick = ANALOG_RIGHT;
							if (analogValue == -1) rightStick = ANALOG_LEFT;
							if (analogValue != -1 && analogValue != 1) rightStick = ANALOG_NEUTRAL;
						}
					}
				};

				setStickAnalogValues.run();
				
			} else {
				if (components[i].getPollData() == 1.0f)
					value = true;
				else
					value = false;
			}

			Runnable setButtonValues = new Runnable() {

				@Override
				public void run() {
					if (name.equalsIgnoreCase("Button 0")) x.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 1")) a.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 2")) b.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 3")) y.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 4")) lb.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 5")) rb.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 6")) lt.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 7")) rt.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 8")) back.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 9")) start.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 10")) ls.actionPerformed(queueControls);
					if (name.equalsIgnoreCase("Button 11")) rs.actionPerformed(queueControls);
				}
			};

			setButtonValues.run();
		}
		
		try {
			if (listener != null) listener.actionPerformed(queueControls);
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns if a was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isAPressed() {
		return a.isPressed();
	}
	
	/**
	 * Returns if b was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isBPressed() {
		return b.isPressed();
	}

	/**
	 * Returns if x was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isXPressed() {
		return x.isPressed();
	}

	/**
	 * Returns if y was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isYPressed() {
		return y.isPressed();
	}

	/**
	 * Returns if lb was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isLBPressed() {
		return lb.isPressed();
	}

	/**
	 * Returns if rb was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isRBPressed() {
		return rb.isPressed();
	}

	/**
	 * Returns if the left stick was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isLSPressed() {
		return ls.isPressed();
	}

	/**
	 * Returns if right stick was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isRSPressed() {
		return rs.isPressed();
	}

	/**
	 * Returns if lt was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isLTPressed() {
		return lt.isPressed();
	}
	
	/**
	 * Returns if lt is held down.
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	public boolean isLTHeld() {
		return lt.isHeld();
	}
	
	/**
	 * Returns if rt was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isRTPressed() {
		return rt.isPressed();
	}
	
	/**
	 * Returns if rt is held down.
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	public boolean isRTHeld() {
		return rt.isHeld();
	}

	/**
	 * Returns if start was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isStartPressed() {
		return start.isPressed();
	}
	
	/**
	 * Returns if back was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public boolean isBackPressed() {
		return back.isPressed();
	}
	
	/**
	 * Returns the left stick's direction.
	 * @return Returns an integer reflecting the sticks direction.
	 */
	public int getLeftStick() {
		return leftStick;
	}

	/**
	 * Returns the right stick's direction.
	 * @return Returns an integer reflecting the sticks direction.
	 */
	public int getRightStick() {
		return rightStick;
	}

	/**
	 * Sets the controller's input listener.
	 * @param arg0 The specified ActionListener for the controller to output its controls.
	 */
	public void setActionListener(ActionListener arg0) {
		listener = arg0;
	}

}
