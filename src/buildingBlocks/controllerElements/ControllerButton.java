package buildingBlocks.controllerElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class manages the values of a button on a controller. It does this by utilizing an {@link java.awt.event.ActionListener ActionListener}.
 * It expects the {@link java.awt.event.ActionEvent ActionEvent} source to be either a {@link buildingBlocks.controllerElements.GamepadController GamepadController}
 * or a {@link buildingBlocks.controllerElements.StickController StickController}. 
 * @author Grayson Spidle
 */
public class ControllerButton implements ActionListener {
	
	private boolean current = false;
	private boolean previous = current;
	private boolean isPressed = false;
	private boolean isHeld = false;
	
	public ControllerButton() {
		current = false;
		previous = current;
		isPressed = false;
		isHeld = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			GamepadController source = (GamepadController) e.getSource();
			current = source.value;
			
			if (previous && !current) {
				isPressed = true;
				isHeld = false;
				previous = current;
			}
			else if (previous && current) {
				isPressed = false;
				isHeld = true;
				previous = current;
			}
			else {
				isPressed = false;
				isHeld = false;
				previous = current;
			}
		} catch (ClassCastException e1) {
			StickController source = (StickController) e.getSource();
			current = source.value;
			
			if (previous && !current) {
				isPressed = true;
				isHeld = false;
				previous = current;
			}
			else if (previous && current) {
				isPressed = false;
				isHeld = true;
				previous = current;
			}
			else {
				isPressed = false;
				isHeld = false;
				previous = current;
			}
		}
	}
	
	public boolean isPressed() {
		return isPressed;
	}
	
	public boolean isHeld() {
		return isHeld;
	}
}
