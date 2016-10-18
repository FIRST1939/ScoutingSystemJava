package com.frcteam1939.scouting.api;

import com.frcteam1939.scouting.api.controllers.JInputJoystick;

import net.java.games.input.*;

public class Main {
	
	/**
	 * I'm just messing around with this
	 * @param controllerType
	 */
	public static void pollControllerAndItsComponents(Controller.Type controllerType) {
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		// First controller of the desired type.
		Controller firstController = null;

		for (int i = 0; i < controllers.length && firstController == null; i++) {
			if (controllers[i].getType() == controllerType) {
				// Found a controller
				firstController = controllers[i];
				break;
			}
		}

		if (firstController == null) {
			// Couldn't find a controller
			System.out.println("Found no desired controller!");
			System.exit(0);
		}

		System.out.println("First controller of a desired type is: " + firstController.getName());

		while (true) {
			firstController.poll();
			Component[] components = firstController.getComponents();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < components.length; i++) {
				if (i > 0) {
					buffer.append(", ");
				}
				buffer.append(components[i].getName());
				buffer.append(": ");
				if (components[i].isAnalog()) {
					buffer.append(components[i].getPollData());
				} else {
					if (components[i].getPollData() == 1.0f) {
						buffer.append("On");
					} else {
						buffer.append("Off");
					}
				}
			}
			System.out.println(buffer.toString());

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		UI ui = new UI();
		JInputJoystick test = new JInputJoystick(Controller.Type.GAMEPAD);
//		pollControllerAndItsComponents(test.getControllerType());
		System.out.println();
		System.out.println(test.getControllerName());
		System.out.println("Number of Buttons: " + test.getNumberOfButtons());
		System.out.println("XAxisPercentage: " + test.getXAxisPercentage());
		System.out.println("YAxisPercentage: " + test.getYAxisPercentage());
		System.out.println("Button Values: " + test.getButtonsValues());
		

	}

}
