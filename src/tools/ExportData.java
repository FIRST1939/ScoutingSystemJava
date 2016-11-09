package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;

import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.UIV3;

/**
 * This class manages the exportation of data to a csv file. 
 * @author Grayson Spidle
 *
 */
public class ExportData {
	
	private static Vector<String> teamNumbers = new Vector<String>();
	
	private static Vector<String> autonomousScoreLabels = new Vector<String>();
	private static Vector<String> teleoperatedScoreLabels = new Vector<String>();
	
	private static ArrayList<Vector<String>> autonomousScores = new ArrayList<Vector<String>>();
	private static ArrayList<Vector<String>> teleoperatedScores = new ArrayList<Vector<String>>();
	
	private static ArrayList<Vector<String>> autonomousOutput = new ArrayList<Vector<String>>();
	private static ArrayList<Vector<String>> teleoperatedOutput = new ArrayList<Vector<String>>();
	
	/**
	 * Exports the two files with the team numbers, score labels, and scores
	 * @param ui The UI to find all this information.
	 */
	public static void toCSV(UIV3 ui) {
		
		System.out.println("Exporting data to .csv");
		
		getRobotNumbers(ui);
		getScoreLabels(ui);
		getScores(ui);
		
		for (int i = 0; i < autonomousScoreLabels.size(); i++) {
			autonomousOutput.add(new Vector<String>());
			teleoperatedOutput.add(new Vector<String>());
		}
		
		String temp = " , " + teamNumbers.toString();
		temp = temp.replace("[", "");
		temp = temp.replace("]", "");
		Vector<String> temp2 = new Vector<String>();
		temp2.add(temp);
		autonomousOutput.set(0, temp2);
		
		for (int i = 0; i < autonomousScoreLabels.size() - 1; i++) {
			temp = autonomousScoreLabels.get(i+1);
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			temp2 = new Vector<String>();
			temp2.add(temp);
			autonomousOutput.set(i+1, temp2);
		}
		
		temp = " , " + teamNumbers.toString();
		temp = temp.replace("[", "");
		temp = temp.replace("]", "");
		temp2 = new Vector<String>();
		temp2.add(temp);
		teleoperatedOutput.set(0, temp2);
		
		for (int i = 0; i < teleoperatedScoreLabels.size() - 1; i++) {
			temp = teleoperatedScoreLabels.get(i+1);
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			temp2 = new Vector<String>();
			temp2.add(temp);
			teleoperatedOutput.set(i+1, temp2);
		}
		
		for (int i = 0; i < autonomousScores.size(); i++) autonomousOutput.get(i+1).addAll(autonomousScores.get(i));
		for (int i = 0; i < teleoperatedScores.size(); i++) teleoperatedOutput.get(i+1).addAll(teleoperatedScores.get(i));
		
		try {
			FileUtils.write(ui.autonomousSaveFile, autonomousOutput);
			FileUtils.write(ui.teleoperatedSaveFile, teleoperatedOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Export complete.");
		
	}
	
	/**
	 * Gets all robot numbers from the panels in the specified ui.
	 * @param ui The UI to get the robot numbers.
	 */
	private static void getRobotNumbers(UIV3 ui) {
		for (RobotTabbedPanel rp : ui.panels) {
			teamNumbers.add(rp.getTeamNumber());
		}
	}
	
	/**
	 * Gets all score labels in the specified ui from the autonomous.scoreLabels and teleoperated.scoreLabels arrays.
	 * @param ui The UI to get the score labels.
	 */
	private static void getScoreLabels(UIV3 ui) {
		autonomousScoreLabels.add("");
		teleoperatedScoreLabels.add("");
		
		for (JLabel l : ui.panels.get(0).autonomous.labels) {
			autonomousScoreLabels.add(l.getText());
		}
		for (JLabel l : ui.panels.get(0).teleoperated.labels) {
			teleoperatedScoreLabels.add(l.getText());
		}
	}
	
	/**
	 * Gets the scores from the specified ui from the ui.getAutonomousScores() and ui.getTeleoperatedScores() methods.
	 * @param ui The UI to get the score labels.
	 */
	private static void getScores(UIV3 ui) {
		
		// Autonomous
		for (int i = 0; i < ui.panels.get(0).autonomous.fields.size(); i++) {
			String temp = ui.getAutonomousScores(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			Vector<String> temp2 = new Vector<String>();
			temp2.add(temp);
			autonomousScores.add(temp2);
		}
		
		// Teleoperated
		for (int i = 0; i < ui.panels.get(0).teleoperated.fields.size(); i++) {
			String temp = ui.getTeleoperatedScores(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			Vector<String> temp2 = new Vector<String>();
			temp2.add(temp);
			teleoperatedScores.add(temp2);
		}
	}
	
}
