package buildingBlocks.controllerElements;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
/**
 * This class handles the inputs for controllers of {@link net.java.games.input.Controller.Type Controller.Type} Gamepad.
 * @author Grayson Spidle
 */
public class GamepadController extends JController {
	
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
	
	public ControllerTrigger lt = new ControllerTrigger(ControllerTrigger.LEFT_TRIGGER);
	public ControllerTrigger rt = new ControllerTrigger(ControllerTrigger.RIGHT_TRIGGER);
	
	public int leftStick = ANALOG_NEUTRAL;
	public int rightStick = ANALOG_NEUTRAL;
	
	private ActionEvent queueControls = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find the holy grail!");
	
	public int robotPanelNumber = -1;
	
	public boolean value = false;
	public float analogValue = 0.0f;
	
	/**
	 * The constructor.
	 * @param arg0 The controller from which the inputs will be parsed.
	 * @param controlsWhichRobotPanel The designated robotPanel on the UI that this controller will control.
	 */
	public GamepadController(net.java.games.input.Controller arg0, int controlsWhichRobotPanel) {
		super(arg0, controlsWhichRobotPanel);
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
				
				if (name.equalsIgnoreCase("Z Axis")) {
					lt.actionPerformed(queueControls);
					rt.actionPerformed(queueControls);
				}
				
				Runnable setStickAnalogValues = new Runnable() {

					@Override
					public void run() {
						// ANALOG_LEFT Stick
						if (name.equalsIgnoreCase("Y Axis")) {
							if (analogValue == 1) leftStick = ANALOG_DOWN;
							if (analogValue == -1) leftStick = ANALOG_UP;
							if (analogValue != -1 && analogValue != 1) leftStick = ANALOG_NEUTRAL;
						}
						else if (name.equalsIgnoreCase("X Axis") && leftStick == ANALOG_NEUTRAL) {
							if (analogValue == 1) leftStick = ANALOG_RIGHT;
							if (analogValue == -1) leftStick = ANALOG_LEFT;
							if (analogValue != -1 && analogValue != 1) leftStick = ANALOG_NEUTRAL;
						}
						
						// ANALOG_RIGHT Stick
						if (name.equalsIgnoreCase("Y Rotation")) {
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
				
			} 
			
			else {
				if (components[i].getPollData() == 1.0f) value = true;
					else value = false;
				}
			
			if (name.equalsIgnoreCase("Button 0")) a.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 1")) b.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 2")) x.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 3")) y.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 4")) lb.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 5")) rb.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 6")) back.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 7")) start.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 8")) ls.actionPerformed(queueControls);
			if (name.equalsIgnoreCase("Button 9")) rs.actionPerformed(queueControls);
			
			controller.getComponent(Identifier.Axis.POV).getPollData();
			
		}
		
		if (listener != null) listener.actionPerformed(queueControls);
		
	}
	
	public boolean isAPressed() {
		return a.isPressed();
	}
	
	public boolean isBPressed() {
		return b.isPressed();
	}
	
	public boolean isXPressed() {
		return x.isPressed();
	}
	
	public boolean isYPressed() {
		return y.isPressed();
	}
	
	public boolean isLBPressed() {
		return lb.isPressed();
	}
	
	public boolean isRBPressed() {
		return rb.isPressed();
	}
	
	public boolean isLSPressed() {
		return ls.isPressed();
	}
	
	public boolean isRSPressed() {
		return rs.isPressed();
	}
	
	public boolean isStartPressed() {
		return start.isPressed();
	}
	
	public boolean isLTPressed() {
		return lt.isPressed();
	}
	
	public boolean isRTPressed() {
		return rt.isPressed();
	}
	
	public boolean isLTHeld() {
		return lt.isHeld();
	}
	
	public boolean isRTHeld() {
		return rt.isHeld();
	}
	
	public int getLeftStick() {
		return leftStick;
	}

	public int getRightStick() {
		return rightStick;
	}
	
	@Override
	public boolean isBackPressed() {
		return back.isPressed();
	}

	@Override

	public boolean isAHeld() {
		return a.isHeld();
	}

	@Override
	public boolean isBHeld() {
		return b.isHeld();
	}

	@Override
	public boolean isXHeld() {
		return x.isHeld();
	}

	@Override
	public boolean isYHeld() {
		return y.isHeld();
	}

	@Override
	public boolean isLBHeld() {
		return lb.isHeld();
	}

	@Override
	public boolean isRBHeld() {
		return rb.isHeld();
	}

	@Override
	public boolean isLSHeld() {
		return ls.isHeld();
	}

	@Override
	public boolean isRSHeld() {
		return rs.isHeld();
	}

	@Override
	public boolean isStartHeld() {
		return start.isHeld();
	}

	@Override
	public boolean isBackHeld() {
		return back.isHeld();
	}
	
	/**
	 * Sets the controller's input listener.
	 * @param arg0 The specified ActionListener for the controller to output its controls.
	 */
	public void setActionListener(ActionListener arg0) {
		listener = arg0;
	}

	
}
	
