package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import controllerElements.GamepadController;
import controllerElements.StickController;
import elements.ControlScheme;
import elements.UIV2;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import tools.ExportData;

/**
 * The main class where the main method resides. This version should seamlessly integrate the two types of controllers we use. 
 * @author Grayson Spidle
 *
 */
public class Main {
	
	private static UIV2 ui = new UIV2();
	private static Vector<StickController> stickControllers = new Vector<StickController>();
	private static Vector<GamepadController> gamepadControllers = new Vector<GamepadController>();
	private static ControlScheme controls = new ControlScheme(ui);
	
	private static boolean requestClose = true;
	
	public static void main(String[] args) {
		
		ui.setVisible(true);
		ui.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				requestClose = false;
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
		});
		
		prepareControllers();
		
//		*** MAIN LOOP ***
		while (requestClose) {
			for (GamepadController ct : gamepadControllers) {
				ct.pollControllerInput();
			}
			for (StickController ct : stickControllers) {
				ct.pollControllerInput();
			}
		}
//		*** END OF MAIN LOOP ***
		
	}
	
	/**
	 * Discovers connected controllers, then handles the resulting errors, then adds an instance of ControlScheme to every controller.
	 */
	private static void prepareControllers() {
		
		int i = 0;
		
		for (Controller c : getAllControllersOfType(Controller.Type.GAMEPAD)) {
			gamepadControllers.add(new GamepadController(c, i));
			i++;
		}
		
//		Getting all controllers of type Stick
		for (Controller c : getAllControllersOfType(Controller.Type.STICK)) {
			stickControllers.add(new StickController(c, i));
			i++;
		}
//
		
		System.out.println(gamepadControllers.size() + stickControllers.size() + "/6 controllers are connected.");
		System.out.println(gamepadControllers.size() + " are Gamepad Controllers.");
		System.out.println(stickControllers.size() + " are Stick Controllers.");
		
//		*** ERROR HANDLING ***
		if (i < 6) {
			System.err.println("Panels " + (i + 1) + "-6 are not being controlled.");
		}
		else if (i > 6) {
			System.err.println("Too many controllers are connected.");
			System.exit(0);
		}
//		*** END OF ERROR HANDLING ***
		
		
//		Adding the ControlScheme to every controller
		for (GamepadController ct : gamepadControllers) {
			ct.setActionListener(controls.autonomousControls);
		}
		for (StickController ct : stickControllers) {
			ct.setActionListener(controls.autonomousControls);
		}
//		
	}
	
	private static Vector<Controller> getAllControllersOfType(Controller.Type controllerType) {
		
		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			if (c.getType() == controllerType) {
				output.add(c);
			}
		}
		return output;
		
	}
	
	private static Vector<Controller> getAllConnectedControllers() {
		
		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			output.add(c);
		}
		
		return output;
		
	}
	
}
