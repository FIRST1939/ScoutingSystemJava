package buildingBlocks;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import buildingBlocks.controllerElements.GamepadController;
import buildingBlocks.controllerElements.StickController;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Initialize the constructor to start your program. 
 * @author Grayson Spidle
 *
 */
public class Main {

	private UIV3 ui;
	private Vector<StickController> stickControllers = new Vector<StickController>();
	private Vector<GamepadController> gamepadControllers = new Vector<GamepadController>();
	private ControlScheme controls;

	private boolean closeRequested = false;
	
	/**
	 * Initialize me to start the program. I gather all available controllers and assign them to panels. 
	 * @param ui The UI for the program to show and modify.
	 */
	public Main(ControlScheme controls) {
		
		this.ui = controls.ui;
		this.controls = controls;
		
		ui.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
//				closeRequested = true;
//				ui.CONSOLE.out.close();
//				ui.CONSOLE.err.close();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
		});

		prepareControllers();

		// *** MAIN LOOP ***
		while (!closeRequested) {
			for (GamepadController ct : gamepadControllers) {
				ct.pollControllerInput();
			}
			for (StickController ct : stickControllers) {
				ct.pollControllerInput();
			}
		}
		// *** END OF MAIN LOOP ***
	}

	/**
	 * Discovers connected controllers, then handles the resulting errors, then
	 * adds an instance of ControlScheme to every controller.
	 */
	private void prepareControllers() {

		int i = 0;

		for (Controller c : getAllControllersOfType(Controller.Type.GAMEPAD)) {
			gamepadControllers.add(new GamepadController(c, i));
			i++;
		}

		for (Controller c : getAllControllersOfType(Controller.Type.STICK)) {
			stickControllers.add(new StickController(c, i));
			i++;
		}

		System.out.println(gamepadControllers.size() + stickControllers.size() + "/6 controllers are connected.");
		System.out.println(gamepadControllers.size() + " are Gamepad Controllers.");
		System.out.println(stickControllers.size() + " are Stick Controllers.");

		// *** ERROR HANDLING ***
		if (i < 5) {
			System.err.println("Panels " + (i + 1) + "-6 are not being controlled.");
		} else if (i >= 6) {
			System.err.println("Too many controllers are connected.");
		}
		// *** END OF ERROR HANDLING ***
		

		// Adding the ControlScheme to every controller
		for (GamepadController ct : gamepadControllers) {
			ct.setActionListener(controls.autonomous);
		}
		for (StickController ct : stickControllers) {
			ct.setActionListener(controls.autonomous);
		}
		//
	}
	
	/**
	 * Gets all controllers of the specified type.
	 * @param controllerType The specified Controller.Type
	 * @return Returns a Vector
	 */
	private Vector<Controller> getAllControllersOfType(Controller.Type controllerType) {

		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			if (c.getType() == controllerType) {
				output.add(c);
			}
		}
		return output;

	}

	/**
	 * Gets all controllers regardless of type. Includes mice and other input devices.
	 * @return Returns a Vector
	 */
	private Vector<Controller> getAllConnectedControllers() {
		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			output.add(c);
		}
		return output;
	}
}
