package elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class for creating the controls
 * @author Grayson Spidle
 *
 */
public class ControlScheme {
	
	UIV2 ui;
	public ActionListener autonomousControls;
	public ActionListener teleoperatedControls;
	
	public ControlScheme(UIV2 arg0) {
		
		ui = arg0;
		
		autonomousControls = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerTest controller = (ControllerTest) e.getSource();
				RobotPanel panel = ui.getRobotPanel(controller.robotPanelNumber);
				
				if (controller.isAButton()) {
					int value = Integer.parseInt(panel.autonomous.score1Field.getText());
					value++;
					panel.autonomous.score1Field.setText("" + value);
					
				}
				
				
				// Switches between autonomous and teleoperated and their respective control schemes
				if (controller.isStart()) {
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
				RobotPanel panel = ui.getRobotPanel(controller.robotPanelNumber);
				
				if (controller.isAButton()) {
					int value = Integer.parseInt(panel.teleop.score1Field.getText());
					value++;
					panel.teleop.score1Field.setText("" + value);
				}
				
				// Switches between autonomous and teleoperated and their respective control schemes
				if (controller.isStart()) {
					if (panel.getSelectedIndex()==0) panel.setSelectedIndex(1);
					else if (panel.getSelectedIndex()==1) panel.setSelectedIndex(0);
					controller.setActionListener(autonomousControls);
				}
				
			}
		};
		
	}

}
