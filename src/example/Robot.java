package example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotPanel;
import buildingBlocks.RobotTabbedPanel;

/**
 * This class manages a panel that governs the Autonomous and Teleoperated panels.
 * @author Grayson Spidle
 *
 */
public class Robot extends RobotTabbedPanel {
	
	private static final long serialVersionUID = 4952300412598887831L;
	
	private RobotNumber number = new RobotNumber("" + null);
	
	/**
	 * The constructor.
	 * @param teamName The desired team number.
	 * @param teamColor The desired team color.
	 */
	public Robot(RobotPanel autonomous, RobotPanel teleoperated) {
		super(autonomous, teleoperated);
		number.setText(autonomous.number.getText());
	}
	
	@Override
	public String getTeamNumber() {
		return number.getText();
	}
	
	public void setEditable(boolean arg0) {
		for (JTextField field : autonomous.fields) {
			field.setEditable(arg0);
		}
		for (JTextField field : teleoperated.fields) {
			field.setEditable(arg0);
		}
	}

	@Override
	public void setTeamNumber(String arg0) {
		number.setText(arg0);
	}
	
}
