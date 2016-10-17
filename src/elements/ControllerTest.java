package elements;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
public class ControllerTest {
	
	static final int ERROR = -1;
	static final int NEUTRAL = 0;
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;
	static final int UP_LEFT = 5;
	static final int UP_RIGHT = 6;
	static final int DOWN_LEFT = 7;
	static final int DOWN_RIGHT = 8;
	
	static boolean aButton = false; 
	static boolean bButton = false;
	static boolean xButton = false;
	static boolean yButton = false;
	static boolean lb = false;
	static boolean rb = false;
	static boolean back = false;
	static boolean start = false;
	static boolean ls = false;
	static boolean rs = false;
	
	static boolean lt = false;
	static boolean rt = false;
	
	static int leftStick = NEUTRAL;
	static int rightStick = NEUTRAL;
	
	static boolean value = false;
	
	static boolean doPolling = true;
	
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
		
		doPolling = true;
		while (doPolling) {
			c.poll();
			Component[] components = c.getComponents();
			for (int i = 0; i < components.length; i++) {
				
				boolean isAnalog = components[i].isAnalog();
				String name = components[i].getName();
				
				if (isAnalog) {
					float analogValue = components[i].getPollData();
					
					Runnable setTriggerAnalogValues = new Runnable() {

						@Override
						public void run() {
							if (name.equalsIgnoreCase("Z Axis")) {
								if (Math.round(analogValue) == 1) lt = true;
								else if (Math.round(analogValue) != 1) lt = false;
								if (Math.round(analogValue)== -1) rt = true;
								else if (Math.round(analogValue)!= -1) rt = false; 
							}
						}
					};
					Runnable setStickAnalogValues = new Runnable() {

						@Override
						public void run() {
							// Left Stick
							if (name.equalsIgnoreCase("Y Axis")) {
								if (analogValue == 1) leftStick = DOWN;
								if (analogValue == -1) leftStick = UP;
								if (analogValue != -1 && analogValue != 1) leftStick = NEUTRAL;
							}
							else if (name.equalsIgnoreCase("X Axis") && leftStick == NEUTRAL) {
								if (analogValue == 1) leftStick = RIGHT;
								if (analogValue == -1) leftStick = LEFT;
								if (analogValue != -1 && analogValue != 1) leftStick = NEUTRAL;
							}
							
							// Right Stick
							if (name.equalsIgnoreCase("Y Rotation")) {
								if (analogValue == 1) rightStick = DOWN;
								if (analogValue == -1) rightStick = UP;
								if (analogValue != -1 && analogValue != 1) rightStick = NEUTRAL;
							}
							if (name.equalsIgnoreCase("X Rotation") && rightStick == NEUTRAL) {
								if (analogValue == 1) rightStick = RIGHT;
								if (analogValue == -1) rightStick = LEFT;
								if (analogValue != -1 && analogValue != 1) rightStick = NEUTRAL;
							}
						}
					};
					
					
					setTriggerAnalogValues.run();
					setStickAnalogValues.run();
				} 
				else {
					if (components[i].getPollData() == 1.0f) value = true;
					else value = false;
				}
				
				Runnable setButtonValues = new Runnable() {

					@Override
					public void run() {
						if (name.equalsIgnoreCase("Button 0")) aButton = value;
						if (name.equalsIgnoreCase("Button 1")) bButton = value;
						if (name.equalsIgnoreCase("Button 2")) xButton = value;
						if (name.equalsIgnoreCase("Button 3")) yButton = value;
						if (name.equalsIgnoreCase("Button 4")) lb = value;
						if (name.equalsIgnoreCase("Button 5")) rb = value;
						if (name.equalsIgnoreCase("Button 6")) back = value;
						if (name.equalsIgnoreCase("Button 7")) start = value;
						if (name.equalsIgnoreCase("Button 8")) ls = value;
						if (name.equalsIgnoreCase("Button 9")) rs = value;
					}
				};
				
				setButtonValues.run();
				
				outputAllVariables();
				
			}

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void outputAllVariables() {
		System.out.println();
		System.out.println("A: " + aButton);
		System.out.println("B: " + bButton);
		System.out.println("X: " + xButton);
		System.out.println("Y: " + yButton);
		System.out.println("LB: " + lb);
		System.out.println("RB: " + rb);
		System.out.println("LS: " + ls);
		System.out.println("RS: " + rs);
		System.out.println("LT: " + lt);
		System.out.println("RT: " + rt);
		System.out.println("BACK: " + back);
		System.out.println("START: " + start);
		if (leftStick == NEUTRAL) System.out.println("Left Stick: Neutral");
		else if (leftStick == UP) System.out.println("Left Stick: Up");
		else if (leftStick == DOWN) System.out.println("Left Stick: Down");
		else if (leftStick == LEFT) System.out.println("Left Stick: Left");
		else if (leftStick == RIGHT) System.out.println("Left Stick: Right");
		else if (leftStick == ERROR) System.out.println("Left Stick: Error");
		if (rightStick == NEUTRAL) System.out.println("Right Stick: Neutral");
		else if (rightStick == UP) System.out.println("Right Stick: Up");
		else if (rightStick == DOWN) System.out.println("Right Stick: Down");
		else if (rightStick == LEFT) System.out.println("Right Stick: Left");
		else if (rightStick == RIGHT) System.out.println("Right Stick: Right");
		else if (rightStick == UP_LEFT) System.out.println("Right Stick: Up Left");
		else if (rightStick == UP_RIGHT) System.out.println("Right Stick: Up Right");
		else if (rightStick == DOWN_LEFT) System.out.println("Right Stick: Down Left");
		else if (rightStick == DOWN_RIGHT) System.out.println("Right Stick: Down Right");
		else if (rightStick == ERROR) System.out.println("Right Stick: Error");
		System.out.println();
	}
	
	public static void stopPolling() {
		doPolling = false;
	}
	
	public static void main(String[] args) {
		
		UI ui = new UI();
		Controller[] controllers = getAllControllersOfType(Controller.Type.GAMEPAD);
		System.out.println(controllers.length);
		pollController(controllers[0]);
		stopPolling();
		
	}
	
}
