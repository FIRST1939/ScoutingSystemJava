package elements;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
public class ControllerTest {

	boolean button1 = false;
	boolean button2 = false;
	boolean button3 = false;
	boolean button4 = false;
	boolean button5 = false;
	boolean button6 = false;
	boolean button7 = false;
	boolean button8 = false;
	boolean button9 = false;
	boolean button10 = false;
	
	public static Controller[] getAllControllers() {
		
		return ControllerEnvironment.getDefaultEnvironment().getControllers();		
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

	public static void pollController(Controller c) {

		while (true) {
			c.poll();
			Component[] components = c.getComponents();
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
			
//			System.out.println(buffer.toString());

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		UI ui = new UI();
		Controller[] controllers = getAllControllersOfType(Controller.Type.GAMEPAD);
		System.out.println(controllers.length);
//		pollController(controllers[0]);
		
		
	}
	
}
