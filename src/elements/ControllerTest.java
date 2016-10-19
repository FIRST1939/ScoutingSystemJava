package elements;
import java.util.Timer;
import java.util.TimerTask;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
public class ControllerTest {
	
	Controller controller;
	
	private static final int ERROR = -1;
	private static final int NEUTRAL = 0;
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	
	private static boolean aButton = false; 
	private static boolean bButton = false;
	private static boolean xButton = false;
	private static boolean yButton = false;
	private static boolean lb = false;
	private static boolean rb = false;
	private static boolean back = false;
	private static boolean start = false;
	private static boolean ls = false;
	private static boolean rs = false;
	
	private static boolean lt = false;
	private static boolean rt = false;
	
	private static int leftStick = NEUTRAL;
	private static int rightStick = NEUTRAL;
	
	private static boolean previousaButton = aButton; 
	private static boolean previousbButton = bButton;
	private static boolean previousxButton = xButton;
	private static boolean previousyButton = yButton;
	private static boolean previouslb = lb;
	private static boolean previousrb = rb;
	private static boolean previousback = back;
	private static boolean previousstart = start;
	private static boolean previousls = ls;
	private static boolean previousrs = rs;
	
	private static boolean previouslt = lt;
	private static boolean previousrt = rt;
	
	private static int previousLeftStick = leftStick;
	private static int previousRightStick = rightStick;
	
	
	
	static boolean value = false;
	
	static boolean doPolling = true;
	
	static UIV2 ui;
	
	public ControllerTest(Controller arg0) {
		
		controller = arg0;
		aButton = false; 
		bButton = false;
		xButton = false;
		yButton = false;
		lb = false;
		rb = false;
		back = false;
		start = false;
		ls = false;
		rs = false;
		
		lt = false;
		rt = false;
		
		leftStick = NEUTRAL;
		rightStick = NEUTRAL;
		
		previousaButton = aButton;
		previousbButton = bButton;
		previousxButton = xButton;
		previousyButton = yButton;
		previouslb = lb;
		previousrb = rb;
		previousback = back;
		previousstart = start;
		previousls = ls;
		previousrs = rs;
		
		previouslt = lt;
		previousrt = rt;
		
		previousLeftStick = leftStick;
		previousRightStick = rightStick;
		
	}
	
	public static Controller[] getAllControllers() {
		
		return ControllerEnvironment.getDefaultEnvironment().getControllers();		
	}
	

	/**
	 * Call this method to get controller input (does not contain a loop)
	 */
	public void pollControllerInput() {
		
		rememberVariables();
//		while(doPolling) {
			controller.poll();
			Component[] components = controller.getComponents();
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
				
//				outputAllVariables();
				
			}

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
	}
	
	private static void outputAllVariablesInConsole() {
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
		else if (rightStick == ERROR) System.out.println("Right Stick: Error");
		System.out.println();
	}
	
	private static void rememberVariables() {
		
		previousaButton = aButton;
		previousbButton = bButton;
		previousxButton = xButton;
		previousyButton = yButton;
		previouslb = lb;
		previousrb = rb;
		previousback = back;
		previousstart = start;
		previousls = ls;
		previousrs = rs;
		
		previouslt = lt;
		previousrt = rt;
		
		previousLeftStick = leftStick;
		previousRightStick = rightStick;
		
	}
	
	public boolean isAButton() {
		if (!aButton && previousaButton) return true;
		else return false;
	}

	public boolean isbButton() {
		if (!bButton && previousbButton) return true;
		else return false;
	}

	public boolean isxButton() {
		if (!xButton &&previousxButton) return true;
		else return false;
	}

	public boolean isyButton() {
		if (!yButton &&previousyButton) return true;
		else return false;
	}

	public boolean isLb() {
		if (!lb &&previouslb) return true;
		else return false;
	}

	public boolean isRb() {
		if (!rb &&previousrb) return true;
		else return false;	
	}

	public boolean isBack() {
		if (!back &&previousback) return true;
		else return false;
	}

	public boolean isStart() {
		if (!start &&previousstart) return true;
		else return false;
	}

	public boolean isLS() {
		if (!ls &&previousls) return true;
		else return false;
	}

	public boolean isRS() {
		if (!rs &&previousrs) return true;
		else return false;
	}

	public boolean isLt() {
		if (!lt && previouslt) return true;
		else return false;
	}

	public boolean isRt() {
		if (!rt && previousrt) return true;
		else return false;
	}

	public int getLeftStick() {
		return leftStick;
	}

	public int getRightStick() {
		return rightStick;
	}
	
}
