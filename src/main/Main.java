package main;

import net.java.games.input.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import elements.ControlScheme;
import elements.ControllerTest;
import elements.RobotPanel;
import elements.UIV2;

public class Main {
	
	static UIV2 ui = new UIV2();
	static Vector<ControllerTest> controllers = new Vector<ControllerTest>();
	static ActionListener autonomousControls;
	static ActionListener teleoperatedControls;
	static ControlScheme controls = new ControlScheme(ui);
	
	public static void main(String[] args) {
		
		ui.setVisible(true);
		
//		*** CONTROLS GO HERE ***
		autonomousControls = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerTest controller = (ControllerTest) e.getSource();
				RobotPanel panel = (RobotPanel) ui.getComponent(controller.robotPanelNumber);
				
				if (controller.isAButton()) {
					
					panel.autonomous.score1Field.setText("" + (Integer.parseInt(panel.autonomous.score1Field.getText()) + 1));
					
				}
				
				
				// Switches between autonomous and teleoperated and their respective control schemes
				if (controller.isBack()) {
					if (panel.getSelectedIndex()==0) panel.setSelectedIndex(1);
					else if (panel.getSelectedIndex()==1) panel.setSelectedIndex(0);
					controller.setActionListener(teleoperatedControls);
				}
				
			}
			
		};
		
		teleoperatedControls = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerTest controller = (ControllerTest) e.getSource();
				RobotPanel panel = (RobotPanel) ui.getComponent(controller.robotPanelNumber);
				
				if (controller.isAButton()) {
					
					panel.teleop.score1Field.setText("" + (Integer.parseInt(panel.teleop.score1Field.getText()) - 1));
					
				}
				
				// Switches between autonomous and teleoperated and their respective control schemes
				if (controller.isBack()) {
					if (panel.getSelectedIndex()==0) panel.setSelectedIndex(1);
					else if (panel.getSelectedIndex()==1) panel.setSelectedIndex(0);
					controller.setActionListener(autonomousControls);
				}
				
			}
			
		};
//		*** END OF CONTROLS ***
		
		
		int i = 0;
		
		try {
			for (Controller c : getAllControllersOfType(Controller.Type.GAMEPAD)) {
				controllers.add(new ControllerTest(c, i));
				i++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No controllers detected.");
			System.exit(0);
		}
		
		if (i != 6) System.err.println("Not enough controllers are connected.");
		
		for (ControllerTest ct : controllers) {
			ct.setActionListener(controls.autonomousControls);
		}
		
		while (true) { // Main Loop
			for (ControllerTest ct : controllers) {
				ct.pollControllerInput();
			}
		}
		

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
	


}
