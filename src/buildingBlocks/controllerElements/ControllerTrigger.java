package buildingBlocks.controllerElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class manages the values of a trigger on a controller. 
 * Typically is used for the {@link buildingBlocks.controllerElements.GamepadController GamepadController}.
 * 
 * @author Grayson Spidle
 */
public class ControllerTrigger implements ActionListener {
	
	public static final int LEFT_TRIGGER = 1;
	public static final int RIGHT_TRIGGER = -1;
	
	private boolean current = false;
	private boolean previous = current;
	private boolean isPressed = false;
	private boolean isHeld = false;
	private int maxAnalogValue = 0;
	
	/**
	 * The constructor.
	 * @param number The number that is used to distinguish which trigger has been pulled down. Use the ControllerTrigger.LEFT_TRIGGER or ControllerTrigger.RIGHT_TRIGGER.
	 */
	public ControllerTrigger(int number) throws IllegalArgumentException {
		current = false;
		previous = current;
		isPressed = false;
		isHeld = false;
		if (number != LEFT_TRIGGER && number != RIGHT_TRIGGER) {
			try {
				finalize();
				throw new IllegalArgumentException("The number parameter must be 1 or -1");
			} catch (Throwable e) {
				e = new Throwable(e.getMessage() + " \n The number parameter must be 1 or -1", e);
				e.printStackTrace();
			}
		}
		else {
			maxAnalogValue = number;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GamepadController source = (GamepadController) e.getSource();
		float analogValue = source.analogValue;
		
		if (Math.round(analogValue) == maxAnalogValue) {
			current = true;
		}
		else if (Math.round(analogValue) != maxAnalogValue) {
			current = false;
		}
		
		if (previous && !current) {
			isPressed = true;
			isHeld = false;
			previous = current;
		}
		else if (previous && current) {
			isPressed = false;
			isHeld = true;
		}
		else {
			isPressed = false;
			isHeld = false;
		}
	}
	
	public boolean isPressed() {
		return isPressed;
	}
	
	public boolean isHeld() {
		return isHeld;
	}
}
