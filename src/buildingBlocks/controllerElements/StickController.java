package buildingBlocks.controllerElements;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
/**
 * This class handles the inputs for controllers of {@link net.java.games.input.Controller.Type.Stick Controller.Type.Stick}.
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
	protected static float dPad = SWITCH_NEUTRAL;

	private ActionEvent queueControls = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find the holy grail!");

	/**
	 * The constructor.
	 * @param arg0 The controller from which the inputs will be parsed.
	 * @param controlsWhichRobotPanel The designated robotPanel on the UI that this controller will control.
	 */
	public StickController(net.java.games.input.Controller arg0, int controlsWhichRobotPanel) {
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
			boolean isAnalog = components[i].isAnalog();
			Identifier id = components[i].getIdentifier();
			if (isAnalog) { 
				float analogValue = components[i].getPollData();
				if (id.equals(Axis.Y) || id.equals(Axis.X) || id.equals(Axis.RY) || id.equals(Axis.RX)) { // TODO Make sure these work. They work with gamepad controllers but I'm not sure with stick
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
					else if (id.equals(Axis.RY)) {
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
			} else {
				boolean value = false;
				if (components[i].getPollData() == 1.0f) value = true;
				if (id.equals(Button._0)) {
					x.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._1)) {
					a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._2)) {
					b.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._3)) {
					y.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._4)) {
					lb.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._5)) {
					rb.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._6)) {
					lt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._7)) {
					rt.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._8)) {
					back.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._9)) {
					start.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._10)) {
					ls.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(value)));
				} else if (id.equals(Button._11)) {
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

	public boolean isLTPressed() {
		return lt.isPressed();
	}
	
	public boolean isLTHeld() {
		return lt.isHeld();
	}
	
	public boolean isRTPressed() {
		return rt.isPressed();
	}
	
	public boolean isRTHeld() {
		return rt.isHeld();
	}

	public boolean isStartPressed() {
		return start.isPressed();
	}
	
	public boolean isBackPressed() {
		return back.isPressed();
	}
	
	public int getLeftStick() {
		return leftStick;
	}

	public int getRightStick() {
		return rightStick;
	}
	
	@Override
	public float getDPad() {
		// TODO try and get this to work
		return SWITCH_ERROR;
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
