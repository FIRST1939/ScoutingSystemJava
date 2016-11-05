package elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllerElements.GamepadController;
import controllerElements.StickController;
import net.java.games.input.Controller;
/**
 * A class for creating the controls. This one should be flexible with the difference in controller types.
 * @author Grayson Spidle
 *
 */
public class ControlScheme {
	
	private UIV2 ui;
	private final long DELAY = 10; // Milliseconds
	public ActionListener autonomousControls;
	public ActionListener teleoperatedControls;
	public Controller.Type type;
	
	public ControlScheme(UIV2 arg0) {
		
		ui = arg0;
		autonomousControls = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					GamepadController controller = (GamepadController) e.getSource();
					RobotPanel panel = ui.getRobotPanel(controller.robotPanelNumber);
					
					if (controller.isAPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.autonomous.score1Field.getText());
						value++;
						panel.autonomous.score1Field.setText("" + value);
						
					}
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) {
						if (panel.getSelectedIndex()==0) panel.setSelectedIndex(1);
						else if (panel.getSelectedIndex()==1) panel.setSelectedIndex(0);
						controller.setActionListener(teleoperatedControls);
					}
					
				} catch (ClassCastException e1) {
					StickController controller = (StickController) e.getSource();
					RobotPanel panel = ui.getRobotPanel(controller.robotPanelNumber);
					
					if (controller.isAPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.autonomous.score1Field.getText());
						value++;
						panel.autonomous.score1Field.setText("" + value);
						
					}
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) {
						panel.setSelectedIndex(1);
						controller.setActionListener(teleoperatedControls);
					}
				}
				
				// Suspends the thread for DELAY amount of miliseconds
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
			}
		};
		
		teleoperatedControls = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GamepadController controller = (GamepadController) e.getSource();
					RobotPanel panel = ui.getRobotPanel(controller.robotPanelNumber);
					
					if (controller.a.isPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.teleoperated.score1Field.getText());
						value++;
						panel.teleoperated.score1Field.setText("" + value);
					}
					
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) { 
						panel.setSelectedIndex(0);
						controller.setActionListener(autonomousControls);
					}
					
					// Suspends the thread for DELAY amount of miliseconds
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (ClassCastException e2) {
					StickController controller = (StickController) e.getSource();
					RobotPanel panel = ui.getRobotPanel(controller.robotPanelNumber);
					
					if (controller.a.isPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.teleoperated.score1Field.getText());
						value++;
						panel.teleoperated.score1Field.setText("" + value);
					}
					
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) { 
						panel.setSelectedIndex(0);
						controller.setActionListener(autonomousControls);
					}
					
					// Suspends the thread for DELAY amount of miliseconds
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		};
		
	}
	
}
