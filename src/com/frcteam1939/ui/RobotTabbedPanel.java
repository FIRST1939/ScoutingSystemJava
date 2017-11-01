package com.frcteam1939.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.List;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.frcteam1939.util.FocusTraversalOnArray;



/**
 * This class manages a panel that governs the Autonomous and Teleoperated panels.
 * @author Grayson Spidle
 *
 */
public class RobotTabbedPanel<AutonomousPanel extends RobotPanel,TeleoperatedPanel extends RobotPanel> extends JTabbedPane {
	
	private static final long serialVersionUID = 4952300412598887831L;
	
	public AutonomousPanel autonomous;
	public TeleoperatedPanel teleoperated;
	
	public Color allianceColor = Color.WHITE;
	
	/**
	 * The constructor. Adds tabs for both RobotPanels.
	 * @param autonomous The autonomous panel.
	 * @param teleoperated The teleoperated panel.
	 */
	public RobotTabbedPanel(AutonomousPanel autonomous, TeleoperatedPanel teleoperated) {
		
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
	 * Gets the labels from the autonomous tab.
	 * @return Returns a List with String representations of those scores.
	 */
	public List<String> getAutonomousLabels() {
		List<String> output = new Vector<String>();
		for (ScoreField sf : autonomous.fields) {
			output.add(sf.getText());
		}
		return output;
	}
	
	/**
	 * Gets the labels from the teleoperated tab.
	 * @return Returns a List with String representations of those scores.
	 */
	public List<String> getTeleoperatedLabels() {
		List<String> output = new Vector<String>();
		for (ScoreField sf : teleoperated.fields) {
			output.add(sf.getText());
		}
		return output;
	}
	
	/**
	 * Gets the scores from the autonomous tab.
	 * @return Returns a List with String representations of those scores.
	 */
	public List<String> getAutonomousScores() {
		List<String> output = new Vector<String>();
		for (ScoreField sf : autonomous.fields) {
			output.add(sf.getText());
		}
		return output;
	}
	
	/**
	 * Gets the scores from the teleoperated tab.
	 * @return Returns a List with String representations of those scores.
	 */
	public List<String> getTeleoperatedScores() {
		List<String> output = new Vector<String>();
		for (ScoreField sf : teleoperated.fields) {
			output.add(sf.getText());
		}
		return output;
	}
	
	/**
	 * Gets the team number.
	 * @return Returns a String representation of the team number.
	 */
	public int getTeamNumber() {
		int i1 = autonomous.number.get();
		int i2 = teleoperated.number.get();
		if (i1 != i2) {
			System.err.println("Different team numbers detected." + i1 + " and " + i2 + ". Returning " + i1);
		}
		return i1;
	}
	
	public void setTeamNumber(int arg0) {
		autonomous.number.set(arg0);
		teleoperated.number.set(arg0);
	}

	
	
}
