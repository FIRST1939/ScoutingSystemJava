package main;

import net.java.games.input.*;

import java.util.Vector;

import elements.ControllerTest;
import elements.UIV2;

public class Main {

	public static void main(String[] args) {
		
		UIV2 ui = new UIV2();
		ui.setVisible(true);
		Controller[] controllers = getAllControllersOfType(Controller.Type.GAMEPAD);
		Vector<ControllerTest> testers = new Vector<ControllerTest>();
		
		for (int i = 0; i > 6; i++) testers.add(new ControllerTest(controllers[i])); // Gather all the connected controllers
		
		while (true) {
			
			for (ControllerTest ct : testers) {
				
				ct.pollControllerInput();
				
//				**** CONTROLS ****
				if (ct.isAButton()) /*do stuff*/;
				if (ct.isbButton()) /*do stuff*/;
//				**** END OF CONTROLS ****
				
			}
			
		}
		

	}
	
	public static Controller[] getAllControllersOfType(Controller.Type controllerType) {
		
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		Controller[] temp = new Controller[controllers.length];
		int outputLength = 0;
		Controller[] output = new Controller[outputLength];
		int i2 = 0;
		
		for (int i = 0; i < controllers.length; i++) {
			if (controllers[i].getType() == controllerType) {
				// Found a controller
				temp[i2] = controllers[i];
				i2++;
			}
		}
		
		// Finds how many elements in temp are not null
		for (int i = 0; i < temp.length; i++) {
			
			if (temp[i] != null) {
				
				outputLength++;
				
			}
			
		}
		
		i2 = 0;
		output = new Controller[outputLength];
		
		// Adds all elements from temp to output (that aren't null)
		for (int i = 0; i < temp.length; i++) {
			
			if (temp[i] != null) {
				
				output[i2] = temp[i];
				i2++;
				
			}
			
		}
		
		return output;
		
	}

}
