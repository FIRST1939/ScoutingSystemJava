package controllerElements;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerButton implements ActionListener {
	
	private boolean current = false;
	private boolean previous = current;
	private boolean isPressed = false;
	
	public ControllerButton() {
		current = false;
		previous = current;
		isPressed = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			GamepadController source = (GamepadController) e.getSource();
			current = source.value;
			
			if (previous && !current) {
				isPressed = true;
				previous = current;
			}
			else {
				isPressed = false;
				previous = current;
			}
		} catch (ClassCastException e1) {
			StickController source = (StickController) e.getSource();
			current = source.value;
			
			if (previous && !current) {
				isPressed = true;
				previous = current;
			}
			else {
				isPressed = false;
				previous = current;
			}
		}
	}
	
	public boolean isPressed() {
		return isPressed;
	}
}
