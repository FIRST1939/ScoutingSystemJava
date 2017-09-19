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
	protected static float dPad = SWITCH_NEUTRAL;

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
				if (components[i].getPollData() == 1.0f) {
          value = true;
        }
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
		  if (!listeners.isEmpty()) {
			 for (ActionListener l : listeners) {
				  l.actionPerformed(queueControls);
		  }
		 }}
   }
    
	@Override
	public boolean isAPressed() {
		return this.a.isPressed();
	}
    
	@Override
	public boolean isBPressed() {
		return this.b.isPressed();
	}

	@Override
	public boolean isXPressed() {
		return this.x.isPressed();
	}

  @Override
	public boolean isYPressed() {
		return this.y.isPressed();
	}

	@Override
	public boolean isLBPressed() {
		return this.lb.isPressed();
	}
	@Override
	public boolean isRBPressed() {
		return this.rb.isPressed();
	}

	@Override
	public boolean isLSPressed() {
		return this.ls.isPressed();
	}
	@Override
	public boolean isRSPressed() {
		return this.rs.isPressed();
	}

	@Override
	public boolean isLTPressed() {
		return this.lt.isPressed();
	}
	@Override
	public boolean isLTHeld() {
		return this.lt.isHeld();
	}
	@Override
	public boolean isRTPressed() {
		return this.rt.isPressed();
	}
	@Override
	public boolean isRTHeld() {
		return this.rt.isHeld();
	}
	@Override
	public boolean isStartPressed() {
		return this.start.isPressed();
	}
	@Override
	public boolean isBackPressed() {
		return this.back.isPressed();
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
		// TODO try and get this to work
		return SWITCH_ERROR;
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
	public Controller getController() {
		return controller;
	}
}
