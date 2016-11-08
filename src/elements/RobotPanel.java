package elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

@SuppressWarnings("serial")
public class RobotPanel extends JTabbedPane {
	
	public AutonomousRobotPanel autonomous;
	public TeleoperatedRobotPanel teleoperated;
	private String teamNumber = "";
	
	public RobotPanel(String teamName, Color teamColor) {
		
		teamNumber = teamName;
		autonomous = new AutonomousRobotPanel(teamName, teamColor);
		teleoperated = new TeleoperatedRobotPanel(teamName, teamColor);
		
		this.addTab("Autonomous", autonomous);
		this.addTab("Teleoperated", teleoperated);
		
		try {
			this.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent arg0) {
					JTabbedPane temp1 = (JTabbedPane) arg0.getSource();
					Container temp = ((Container) temp1.getComponentAt(temp1.getSelectedIndex()));
					temp.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{temp.getComponent(0), temp.getComponent(1), temp.getComponent(2), temp.getComponent(3), temp.getComponent(4), temp.getComponent(5)}));
				}
				
			});
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Unable to display robotPanel");
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public String getTeamNumber() {
		return teamNumber;
	}
	
}
