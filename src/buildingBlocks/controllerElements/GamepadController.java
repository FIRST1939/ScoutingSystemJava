package buildingBlocks.controllerElements;

import static buildingBlocks.controllerElements.ControllerTrigger.LEFT_TRIGGER;
import static buildingBlocks.controllerElements.ControllerTrigger.RIGHT_TRIGGER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
/**
 * This class handles the inputs for controllers of {@link net.java.games.input.Controller.Type Controller.Type} Gamepad.
 * @author Grayson Spidle
 */
public class GamepadController extends JController {
	
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
	
	protected ControllerTrigger lt = new ControllerTrigger(LEFT_TRIGGER);
	protected ControllerTrigger rt = new ControllerTrigger(RIGHT_TRIGGER);
	
	protected int leftStick = ANALOG_NEUTRAL;
	protected int rightStick = ANALOG_NEUTRAL;
	protected float dPad = SWITCH_NEUTRAL;
	
	private ActionEvent queueControls = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find the holy grail!");
	
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
		if (!controller.poll()) {
			return;
		}
		Component[] components = controller.getComponents();
		for (int i = 0; i < components.length; i++) {
			
			Component comp = components[i];
			boolean isAnalog = components[i].isAnalog();
			Identifier id = components[i].getIdentifier();
				
			if (isAnalog) {
				float analogValue = components[i].getPollData();
				if (id.equals(Axis.Z)) {
					lt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(analogValue)));
					rt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(analogValue)));
				} else if (id.equals(Axis.Y) || id.equals(Axis.X) || id.equals(Axis.RY) || id.equals(Axis.RX)) {
					// Left Stick
					if (id.equals(Axis.Y)) {
						if (analogValue == 1) leftStick = ANALOG_DOWN;
						if (analogValue == -1) leftStick = ANALOG_UP;
						if (analogValue != -1 && analogValue != 1) leftStick = ANALOG_NEUTRAL;
					}
					else if (id.equals(Axis.X) && leftStick == ANALOG_NEUTRAL) {
						if (analogValue == 1) leftStick = ANALOG_RIGHT;
						if (analogValue == -1) leftStick = ANALOG_LEFT;
						if (analogValue != -1 && analogValue != 1) leftStick = ANALOG_NEUTRAL;
					}
					
					// Right Stick
					if (id.equals(Axis.RY)) {
						if (analogValue == 1) rightStick = ANALOG_DOWN;
						if (analogValue == -1) rightStick = ANALOG_UP;
						if (analogValue != -1 && analogValue != 1) rightStick = ANALOG_NEUTRAL;
					}
					else if (id.equals(Axis.RX) && rightStick == ANALOG_NEUTRAL) {
						if (analogValue == 1) rightStick = ANALOG_RIGHT;
						if (analogValue == -1) rightStick = ANALOG_LEFT;
						if (analogValue != -1 && analogValue != 1) rightStick = ANALOG_NEUTRAL;
					}
				}
				
			} else if (id.equals(Axis.POV)) { // D-Pad
				float data = comp.getPollData();
				if (data == SWITCH_NEUTRAL) dPad = SWITCH_NEUTRAL;
				else if (data == SWITCH_UP) dPad = SWITCH_UP;
				else if (data == SWITCH_LEFT) dPad = SWITCH_LEFT;
				else if (data == SWITCH_DOWN) dPad = SWITCH_DOWN;
				else if (data == SWITCH_RIGHT) dPad = SWITCH_RIGHT;
				else dPad = SWITCH_ERROR;
			} else { // Buttons
				boolean value = false;
				if (comp.getPollData() == 1.0f) value = true;
				if (id.equals(Button._0)) {
					a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._1)) {
					b.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._2)) {
					x.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._3)) {
					y.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._4)) {
					lb.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._5)) {
					rb.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._6)) {
					back.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._7)) {
					start.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._8)) {
					ls.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._9)) {
					rs.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				}
			}
		}
		
		if (!listeners.isEmpty()) {
			for (ActionListener l : listeners) {
				l.actionPerformed(queueControls);
			}
		}
		
	}
	
	@Override
	public boolean isAPressed() {
		return a.isPressed();
	}
	
	@Override
	public boolean isBPressed() {
		return b.isPressed();
	}
	
	@Override
	public boolean isXPressed() {
		return x.isPressed();
	}
	
	@Override
	public boolean isYPressed() {
		return y.isPressed();
	}
	
	@Override
	public boolean isLBPressed() {
		return lb.isPressed();
	}
	
	@Override
	public boolean isRBPressed() {
		return rb.isPressed();
	}
	
	@Override
	public boolean isLSPressed() {
		return ls.isPressed();
	}
	
	@Override
	public boolean isRSPressed() {
		return rs.isPressed();
	}
	
	@Override
	public boolean isStartPressed() {
		return start.isPressed();
	}
	
	@Override
	public boolean isLTPressed() {
		return lt.isPressed();
	}
	
	@Override
	public boolean isRTPressed() {
		return rt.isPressed();
	}
	
	@Override
	public boolean isLTHeld() {
		return lt.isHeld();
	}
	
	@Override
	public boolean isRTHeld() {
		return rt.isHeld();
	}
	
	@Override
	public int getLeftStick() {
		return leftStick;
	}

	@Override
	public int getRightStick() {
		return rightStick;
	}
	
	@Override
	public float getDPad() {
		return dPad;
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
	
	public Controller getController() {
		return controller;
	}
	
}
	
