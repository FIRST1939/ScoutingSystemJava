package buildingBlocks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

/**
 * This class manages a panel that governs the Autonomous and Teleoperated panels.
 * @author Grayson Spidle
 *
 */
public abstract class RobotTabbedPanel extends JTabbedPane {
	
	private static final long serialVersionUID = 4952300412598887831L;
	
	public RobotPanel autonomous;
	public RobotPanel teleoperated;
	
	public Color allianceColor = null;
	
	/**
	 * The constructor. Adds tabs for both RobotPanels.
	 * @param autonomous The autonomous panel.
	 * @param teamColor The teleoperated panel.
	 */
	public RobotTabbedPanel(RobotPanel autonomous, RobotPanel teleoperated) {
		
		this.autonomous = autonomous;
		this.teleoperated = teleoperated;
		
		this.addTab("Autonomous", autonomous);
		this.addTab("Teleoperated", teleoperated);
		
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				try {
					JTabbedPane source = (JTabbedPane) arg0.getSource();
					Container temp = ((Container) source.getComponentAt(source.getSelectedIndex()));
					temp.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{temp.getComponent(0), temp.getComponent(1), temp.getComponent(2), temp.getComponent(3), temp.getComponent(4), temp.getComponent(5)}));
				} catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Unable to switch the tab.");
					e.printStackTrace();
				}
				
			}
			
		});
	}
	
	public void setAllianceColor(Color arg0) {
		allianceColor = arg0;
	}
	
	public Color getAllianceColor() {
		return allianceColor;
	}
	
	/**
	 * Sets whether the ScoreFields are editable.
	 * @param arg0
	 */
	public void setContentEditable(boolean arg0) {
		for (ScoreField field : autonomous.fields) {
			field.setEditable(arg0);
		}
		for (ScoreField field : teleoperated.fields) {
			field.setEditable(arg0);
		}
	}
	
	/**
	 * Sets the displayed team number for the panel.
	 * @param arg0 The team number to change.
	 */
	public abstract void setTeamNumber(String arg0);
	
	/**
	 * Gets the team number.
	 * @return Returns a String representation of the team number.
	 */
	public abstract String getTeamNumber();
	
}
