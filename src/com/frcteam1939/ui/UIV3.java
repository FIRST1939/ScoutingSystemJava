package com.frcteam1939.ui;

import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

/**
 * The UI class where the main UI can be found. Is a subclass to JFrame and is
 * an ActionListener.
 * 
 * @author Grayson Spidle
 *
 */
public class UIV3<Panel extends RobotTabbedPanel<? extends RobotPanel, ? extends RobotPanel>> extends JFrame {

	private static final long serialVersionUID = 8974473527856329569L;

	protected List<Panel> panels = new Vector<Panel>();

	protected File autonomousSaveFile = null;
	protected File teleoperatedSaveFile = null;
	/**
	 * This is the default location where the program will save the data.
	 */
	public File defaultSaveFile = new File(
			(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

	private boolean editability = false;

	/**
	 * Creates the frame. The frame's default close operation is to exit on close. The frame's default visibility is false. The frame's default size is the screen size.
	 */
	public UIV3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getContentPane().setName("contentPane");
		this.getContentPane().setSize(Toolkit.getDefaultToolkit().getScreenSize());

	}

	/**
	 * Gets a specified RobotTabbedPanel
	 * 
	 * @param index The index of the desired RobotTabbedPanel. Valid numbers are 0-5
	 * @return Returns a Panel which is a subclass of RobotTabbedPanel.
	 * @throws IllegalArgumentException
	 *             Thrown when the index exceeds 5 or deceeds 0.
	 */
	public final Panel getRobotTabbedPanel(int index) {
		if (index > 5) {
			throw new IllegalArgumentException("The parameter index cannot be greater than 5.");
		} else if (index < 0) {
			throw new IllegalArgumentException("The parameter index cannot be less than 0.");
		}
		return panels.get(index);
	}

	/**
	 * A convenience method for iterating over each panel in the UI and then iterating over every score field.
	 * @param panelIndex Determines which panel it will get the autonomous scores from.
	 * @return Returns a List of their String representations.
	 * @throws IllegalArgumentException Thrown when panelIndex exceeds 5 or deceeds 0.
	 */
	public final List<String> getAutonomousScores(int panelIndex) {
		if (panelIndex > 5) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be greater than 5.");
		} else if (panelIndex < 0) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be less than 0.");
		}
		return panels.get(panelIndex).getTeleoperatedScores();
	}

	/**
	 * A convenience method for iterating over each panel in the UI and then iterating over every score field.
	 * @param panelIndex Determines which panel it will get the teleoperated scores from.
	 * @return Returns a List of their String representations.
	 * @throws IllegalArgumentException Thrown when panelIndex exceeds 5 or deceeds 0.
	 */
	public final List<String> getTeleoperatedScores(int panelIndex) {
		if (panelIndex > 5) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be greater than 5.");
		} else if (panelIndex < 0) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be less than 0.");
		}
		return panels.get(panelIndex).getTeleoperatedScores();

	}

	/**
	 * A convenience method for iterating over each panel in the UI and then iterating over every label.
	 * @param panelIndex Determines which panel it will get the autonomous labels from.
	 * @return Returns a List of their String representations.
	 * @throws IllegalArgumentException Thrown when panelIndex exceeds 5 or deceeds 0.
	 */
	public final List<String> getAutonomousLabels(int panelIndex) {
		if (panelIndex > 5) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be greater than 5.");
		} else if (panelIndex < 0) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be less than 0.");
		}
		return panels.get(panelIndex).getAutonomousScores();
	}

	/**
	 * A convenience method for iterating over each panel in the UI and then iterating over every label.
	 * @param panelIndex Determines which panel it will get the teleoperated labels from.
	 * @return Returns a List of their String representations.
	 * @throws IllegalArgumentException Thrown when panelIndex exceeds 5 or deceeds 0.
	 */
	public final List<String> getTeleoperatedLabels(int panelIndex) {
		if (panelIndex > 5) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be greater than 5.");
		} else if (panelIndex < 0) {
			throw new IllegalArgumentException("The parameter panelIndex cannot be less than 0.");
		}
		return panels.get(panelIndex).getTeleoperatedLabels();
	}

	/**
	 * Loads the numbers specified into their respective panels.
	 * @param bot1 The top left panel.
	 * @param bot2 The top middle panel.
	 * @param bot3 The top right panel.
	 * @param bot4 The bottom left panel.
	 * @param bot5 The bottom middle panel.
	 * @param bot6 The bottom right panel.
	 */
	protected void loadTeamNumbers(int bot1, int bot2, int bot3, int bot4, int bot5, int bot6) {
		int[] numbers = new int[] {bot1, bot2, bot3, bot4, bot5, bot6};
		for (int i = 0; i < 6; i++) {
			panels.get(i).autonomous.number.setText(String.valueOf(numbers[i]));
			panels.get(i).teleoperated.number.setText(String.valueOf(numbers[i]));
		}
	}
	
	protected void toggleEditability() {
		editability = !editability;
		for (Panel p : panels) {
			p.setContentEditable(editability);
		}
	}

	/**
	 * Adds a RobotTabbedPanel to the UI.
	 * 
	 * @param panel
	 *            The panel to add.
	 */
	public final void add(Panel panel) {
		panels.add(panel);
		this.getContentPane().add(panel);
	}
	
	/**
	 * Removes a RobotTabbedPanel to the UI.
	 * 
	 * @param panel
	 *            The panel to remove.
	 */
	public final void remove(Panel panel) {
		panels.remove(panel);
		this.getContentPane().remove(panel);
	}
	
	public void replace(Panel panel, Panel with) {
		panels.set(panels.indexOf(panel), with);
	}

	public void reset() {
		for (Panel p : panels) {
			for (ScoreField sf : p.autonomous.fields) {
				sf.reset();
			}
			for (ScoreField sf : p.teleoperated.fields) {
				sf.reset();
			}
		}
	}
	
}
