package buildingBlocks.controllerElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;

/**
 * This class handles the inputs for controllers of {@link net.java.games.input.Controller.Type.Stick Controller.Type.Stick}.
 * 
 * @author Grayson Spidle
 */
public class StickController extends JController {

	protected ControllerButton a = new ControllerButton();
	protected ControllerButton b = new ControllerButton();
	protected ControllerButton x = new ControllerButton();
	protected ControllerButton y = new ControllerButton();
	protected ControllerButton lb = new ControllerButton();
	protected ControllerButton rb = new ControllerButton();
	protected ControllerButton start = new ControllerButton();
	protected ControllerButton back = new ControllerButton();
	protected ControllerButton ls = new ControllerButton();
	protected ControllerButton rs = new ControllerButton();

	protected ControllerButton lt = new ControllerButton();
	protected ControllerButton rt = new ControllerButton();

	protected static int leftStick = ANALOG_NEUTRAL;
	protected static int rightStick = ANALOG_NEUTRAL;

	private ActionListener listener;
	private ActionEvent queueControls = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find the holy grail!");

	public boolean value = false;
	public float analogValue = 0.0f;

	/**
	 * The constructor.
	 * 
	 * @param arg0
	 *            The controller from which the inputs will be parsed.
	 * @param controlsWhichRobotPanel
	 *            The designated robotPanel on the UI that this controller will control.
	 */
	public StickController(net.java.games.input.Controller arg0, int controlsWhichRobotPanel) {
		super(arg0, controlsWhichRobotPanel);
	}

	/**
	 * Call this method to get controller input (does not contain a loop)
	 */
	@Override
	public void pollControllerInput() {
		this.analogValue = 0.0f;

		this.controller.poll();
		Component[] components = this.controller.getComponents();
		for (int i = 0; i < components.length; i++) {
			boolean isAnalog = components[i].isAnalog();
			String name = components[i].getName();
			Identifier id = components[i].getIdentifier();

			if (isAnalog) {
				this.analogValue = components[i].getPollData();

				Runnable setStickAnalogValues = () -> {
					// ANALOG_LEFT Stick
					if (name.equalsIgnoreCase("Y Axis")) {
						if (StickController.this.analogValue == 1) {
							leftStick = ANALOG_DOWN;
						}
						if (StickController.this.analogValue == -1) {
							leftStick = ANALOG_UP;
						}
						if (StickController.this.analogValue != -1 && StickController.this.analogValue != 1) {
							leftStick = ANALOG_NEUTRAL;
						}
					} else if (name.equalsIgnoreCase("X Axis") && leftStick == ANALOG_NEUTRAL) {
						if (StickController.this.analogValue == 1) {
							leftStick = ANALOG_RIGHT;
						}
						if (StickController.this.analogValue == -1) {
							leftStick = ANALOG_LEFT;
						}
						if (StickController.this.analogValue != -1 && StickController.this.analogValue != 1) {
							leftStick = ANALOG_NEUTRAL;
						}
					}

					// ANALOG_RIGHT Stick
					if (name.equalsIgnoreCase("Z Rotation")) {
						if (StickController.this.analogValue == 1) {
							rightStick = ANALOG_DOWN;
						}
						if (StickController.this.analogValue == -1) {
							rightStick = ANALOG_UP;
						}
						if (StickController.this.analogValue != -1 && StickController.this.analogValue != 1) {
							rightStick = ANALOG_NEUTRAL;
						}
					}
					if (name.equalsIgnoreCase("X Rotation") && rightStick == ANALOG_NEUTRAL) {
						if (StickController.this.analogValue == 1) {
							rightStick = ANALOG_RIGHT;
						}
						if (StickController.this.analogValue == -1) {
							rightStick = ANALOG_LEFT;
						}
						if (StickController.this.analogValue != -1 && StickController.this.analogValue != 1) {
							rightStick = ANALOG_NEUTRAL;
						}
					}
				};

				setStickAnalogValues.run();
			} else {
				if (components[i].getPollData() == 1.0f) {
					this.value = true;
				} else {
					this.value = false;
				}
			}

			Runnable setButtonValues = () -> {
				if (name.equalsIgnoreCase("Button 0")) {
					StickController.this.x.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 1")) {
					StickController.this.a.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 2")) {
					StickController.this.b.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 3")) {
					StickController.this.y.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 4")) {
					StickController.this.lb.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 5")) {
					StickController.this.rb.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 6")) {
					StickController.this.lt.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 7")) {
					StickController.this.rt.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 8")) {
					StickController.this.back.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 9")) {
					StickController.this.start.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 10")) {
					StickController.this.ls.actionPerformed(StickController.this.queueControls);
				}
				if (name.equalsIgnoreCase("Button 11")) {
					StickController.this.rs.actionPerformed(StickController.this.queueControls);
				}
			};

			setButtonValues.run();
		}

		if (this.listener != null) {
			this.listener.actionPerformed(this.queueControls);
		}
	}

	/**
	 * Returns if a was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isAPressed() {
		return this.a.isPressed();
	}

	/**
	 * Returns if b was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isBPressed() {
		return this.b.isPressed();
	}

	/**
	 * Returns if x was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isXPressed() {
		return this.x.isPressed();
	}

	/**
	 * Returns if y was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isYPressed() {
		return this.y.isPressed();
	}

	/**
	 * Returns if lb was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isLBPressed() {
		return this.lb.isPressed();
	}

	/**
	 * Returns if rb was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isRBPressed() {
		return this.rb.isPressed();
	}

	/**
	 * Returns if the left stick was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isLSPressed() {
		return this.ls.isPressed();
	}

	/**
	 * Returns if right stick was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isRSPressed() {
		return this.rs.isPressed();
	}

	/**
	 * Returns if lt was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isLTPressed() {
		return this.lt.isPressed();
	}

	/**
	 * Returns if lt is held down.
	 * 
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	@Override
	public boolean isLTHeld() {
		return this.lt.isHeld();
	}

	/**
	 * Returns if rt was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isRTPressed() {
		return this.rt.isPressed();
	}

	/**
	 * Returns if rt is held down.
	 * 
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	@Override
	public boolean isRTHeld() {
		return this.rt.isHeld();
	}

	/**
	 * Returns if start was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isStartPressed() {
		return this.start.isPressed();
	}

	/**
	 * Returns if back was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	@Override
	public boolean isBackPressed() {
		return this.back.isPressed();
	}

	/**
	 * Returns the left stick's direction.
	 * 
	 * @return Returns an integer reflecting the sticks direction.
	 */
	@Override
	public int getLeftStick() {
		return leftStick;
	}

	/**
	 * Returns the right stick's direction.
	 * 
	 * @return Returns an integer reflecting the sticks direction.
	 */
	@Override
	public int getRightStick() {
		return rightStick;
	}

	@Override
	public boolean isAHeld() {
		return this.a.isHeld();
	}

	@Override
	public boolean isBHeld() {
		return this.b.isHeld();
	}

	@Override
	public boolean isXHeld() {
		return this.x.isHeld();
	}

	@Override
	public boolean isYHeld() {
		return this.y.isHeld();
	}

	@Override
	public boolean isLBHeld() {
		return this.lb.isHeld();
	}

	@Override
	public boolean isRBHeld() {
		return this.rb.isHeld();
	}

	@Override
	public boolean isLSHeld() {
		return this.ls.isHeld();
	}

	@Override
	public boolean isRSHeld() {
		return this.rs.isHeld();
	}

	@Override
	public boolean isStartHeld() {
		return this.start.isHeld();
	}

	@Override
	public boolean isBackHeld() {
		return this.back.isHeld();
	}

	/**
	 * Sets the controller's input listener.
	 * 
	 * @param arg0
	 *            The specified ActionListener for the controller to output its controls.
	 */
	@Override
	public void setActionListener(ActionListener arg0) {
		this.listener = arg0;
	}

}
